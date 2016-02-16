using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace FinancialDataGrabber.CVM.Actors
{
    using Akka.Actor;

    using FinancialDataGrabber.CVM.CVMWeb;
    using FinancialDataGrabber.CVM.Messages;

    using System.Configuration;

    public class CVMActor
        : ReceiveActor
    {

        private CVMWeb.WsDownloadInfsSoapClient client ;

        private sessaoIdHeader loginData = null;

        public CVMActor(Int32 userId, String pwd)
        {
            UserId = userId;
            Password = pwd;

            Unitialized();  
        }

        protected Int32 UserId { get; private set; }
        protected String Password { get; private set; }

        private void Unitialized()
        {
            Receive<UpdateFundos>(msg => 
                {
                    if (loginData == null)
                    {
                        BecomeAuthenticating();
                        client = new CVMWeb.WsDownloadInfsSoapClient();
                        client.LoginAsync(loginData, UserId, Password).ContinueWith<AuthenticationMessage>(z =>
                        {
                            if (z.IsFaulted)
                            {
                                return new AuthenticationFailed();
                            } 

                            if (z.IsCanceled)
                            {
                                return new AuthenticationCancelled();
                            }

                            return new AuthenticationSuccess(z.Result.sessaoIdHeader);
                        }).PipeTo(Self);
                    }

                });
        }

        private void BecomeAuthenticating()
        {
            Become(Authenticating);
        } 

        private void Authenticating()
        {
            Receive<AuthenticationSuccess>(success =>
            {
                BecomeUpdatingFundos();
                loginData = success.LoginInfo;

                /*

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

                */
            });
        }

        private void BecomeUpdatingFundos()
        {
            Become(UpdatingFundos);
        }

        private void UpdatingFundos()
        {

            Receive<Object>(msg => {
                Console.WriteLine("Mensagem recebida: " + msg);
            });
        }
    }
}
