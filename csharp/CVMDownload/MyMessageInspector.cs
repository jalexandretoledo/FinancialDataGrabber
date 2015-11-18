using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CVMDownload
{
    using System.ServiceModel.Description;
    using System.ServiceModel.Dispatcher;

    /// <summary>
    ///     Source: http://stackoverflow.com/questions/3879199/intercept-soap-messages-from-and-to-a-web-service-at-the-client
    /// </summary>
    public class MyMessageInspector : IClientMessageInspector
    {
        public string LastRequestXML { get; private set; }
        public string LastResponseXML { get; private set; }
        public void AfterReceiveReply(ref System.ServiceModel.Channels.Message reply, object correlationState)
        {
            LastResponseXML = reply.ToString();
        }

        public object BeforeSendRequest(ref System.ServiceModel.Channels.Message request, System.ServiceModel.IClientChannel channel)
        {
            LastRequestXML = request.ToString();
            return request;
        }
    }

}
