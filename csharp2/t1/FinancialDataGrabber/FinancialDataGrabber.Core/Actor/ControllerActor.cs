using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace FinancialDataGrabber.Core.Actor
{
    using Akka.Actor;

    internal class ControllerActor
        : UntypedActor
    {

        private IActorRef commandActor;

        protected override void OnReceive(object message)
        {
            var str = (message as String);
            if (str != null)
            {
                if ("start".Equals(str, StringComparison.CurrentCultureIgnoreCase))
                {
                    commandActor = Context.ActorOf(Props.Create<CommandActor>(), "commandProcessor");
                    Become(Running);
                }
            }
        }

        protected void Running(object message)
        {
            var str = (message as String);
            if (str != null)
            {
                if ("stop".Equals(str, StringComparison.CurrentCultureIgnoreCase))
                {
                    commandActor.Tell("kill");
                    Become(OnReceive);
                }
            }
        }
    }
}
