using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace FinancialDataGrabber.Core.Actor
{
    using Akka.Actor;

    using FinancialDataGrabber.Core.Message;

    internal class ControllerActor
        : UntypedActor
    {

        private IActorRef commandActor;

        protected override void OnReceive(object message)
        {
            if (message is Start)
            {
                var s = (message as Start);
                commandActor = Context.ActorOf(Props.Create<CommandActor>(() => new CommandActor(s.UserId, s.Password)), "commandProcessor");
                Become(Running);
            }
        }

        protected void Running(object message)
        {
            if (message is Shutdown)
            {
                commandActor.Tell(message);
                Become(OnReceive);
            }
        }
    }
}
