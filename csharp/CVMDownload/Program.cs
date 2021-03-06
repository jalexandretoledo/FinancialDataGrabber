﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CVMDownload
{
    using System.Web;
    using System.Net;

    class Program
    {

        private static Int32 idSistema = 0;
        private static String senha = null;
        private static Int32 dias = 0;
        private static Boolean downloadCotas = false;

        private const Int32 ID_TIPO_INFORME_DIARIO = 209;

        [STAThread]
        static void Main(string[] args)
        {
            var client = new CVMWebCotas.WsDownloadInfsSoapClient();

            if (args.Length != 4)
            {
                PrintHelp();
                return;
            }

            try
            {
                idSistema = Int32.Parse(args[0]);
                senha = args[1];
                dias = Math.Abs(Int32.Parse(args[2])) * -1;
            }
            catch (Exception ex)
            {
                Console.WriteLine("Erro: {0}", ex.Message);
                PrintHelp();
                return;
            }

            var requestInterceptor = new InspectorBehavior();
            client.Endpoint.EndpointBehaviors.Add(requestInterceptor);

            CVMWebCotas.sessaoIdHeader login = null;
            client.Login(ref login, idSistema, senha);
            Console.WriteLine("REQUEST:" + requestInterceptor.LastRequestXML);
            Console.WriteLine("RESPONSE:" + requestInterceptor.LastResponseXML);

            var strData = DateTime.Today.AddDays(dias).ToString("yyyy-MM-dd");
            Console.WriteLine("Looking for file from {0}...", strData);

            if ("FUNDO".Equals(args[3]))
            {
                DownloadFundos(client, requestInterceptor, login, strData);
            }
            else if ("COTAS".Equals(args[3]))
            {
                DownloadCotas(client, requestInterceptor, login, strData);
            }
            else if ("ANUAL".Equals(args[3]))
            {
                DownloadAnual(client, requestInterceptor, login);
            }
            else
            {
                PrintHelp();
                return;
            }

            Console.WriteLine("Pressione ENTER...");
            Console.ReadLine();

        }

        private static void DownloadFundos(
            CVMWebCotas.WsDownloadInfsSoapClient client,
            InspectorBehavior requestInspector,
            CVMWebCotas.sessaoIdHeader login,
            String strData)
        {
            var url = client.solicAutorizDownloadCadastro(ref login, strData, "Teste de Sistema");
            Console.WriteLine("REQUEST:" + requestInspector.LastRequestXML);

            var hdrs = requestInspector.LastRequestHeaders;
            if (hdrs != null)
            {
                Console.WriteLine("-- Headers ------------------------------------------------------------------------");
                foreach (var h in hdrs)
                {
                    Console.WriteLine(h);
                }
                Console.WriteLine("------------------------------------------------------------");
            }

            Console.WriteLine("RESPONSE:" + requestInspector.LastResponseXML);


            Console.WriteLine("URL: {0}", url);

            System.Windows.Forms.Clipboard.SetText(url);
            var filename = String.Format(@"C:\Temp\Fundos-{0}.zip", strData);

            DoDownload(url, filename);

            Console.WriteLine("Downloaded!");
        }

        private static void DownloadCotas(
            CVMWebCotas.WsDownloadInfsSoapClient client,
            InspectorBehavior requestInspector,
            CVMWebCotas.sessaoIdHeader login,
            String strData)
        {
            /*
            var lista = client.retornaListaComptcDocs(ref login, ID_TIPO_INFORME_DIARIO, strData);
            if (lista != null)
            {
                foreach (var item in lista)
                {
                    Console.WriteLine(item);
                }
            }
            else
            {
                Console.WriteLine("Nada retornado");
            }
            */

            Console.WriteLine("Pressione ENTER...");
            Console.ReadLine();
            var url = client.solicAutorizDownloadArqComptc(ref login, ID_TIPO_INFORME_DIARIO, strData, "Teste de sistema");
            Console.WriteLine("REQUEST:" + requestInspector.LastRequestXML);
            Console.WriteLine("RESPONSE:" + requestInspector.LastResponseXML);
            Console.WriteLine("URL: {0}", url);

            System.Windows.Forms.Clipboard.SetText(url);

            var filename = String.Format(@"C:\Temp\Cotas-{0}.zip", strData);
            DoDownload(url, filename);

            Console.WriteLine("Downloaded!");
        }

        private static void DownloadAnual(
            CVMWebCotas.WsDownloadInfsSoapClient client,
            InspectorBehavior requestInspector,
            CVMWebCotas.sessaoIdHeader login)
        {
            var url = client.solicAutorizDownloadArqAnual(ref login, ID_TIPO_INFORME_DIARIO, "Teste de sistema");
            Console.WriteLine("REQUEST:" + requestInspector.LastRequestXML);
            Console.WriteLine("RESPONSE:" + requestInspector.LastResponseXML);
            Console.WriteLine("URL: {0}", url);

            System.Windows.Forms.Clipboard.SetText(url);

            var filename = String.Format(@"C:\Temp\Anual-{0:yyyyMMddHHmmss}.zip", DateTime.Now);
            DoDownload(url, filename);

            Console.WriteLine("Downloaded!");
        }

        private static void DoDownload(string url, string filename)
        {
            using (var wc = new WebClient())
            {
                Console.WriteLine("Downloading [{0}] to [{1}]...", url, filename);
                var task = wc.DownloadFileTaskAsync(url, filename);
                while (task.Status == TaskStatus.Running)
                {
                    Console.Write(".");
                }
            }
        }

        private static void PrintHelp()
        {
            Console.WriteLine("{0} <id sistema> <senha> <dias> FUNDO|COTAS|ANUAL", System.Diagnostics.Process.GetCurrentProcess().MainModule.FileName);
            Console.WriteLine("Pressione ENTER...");
            Console.ReadLine();
        }
    }
}
