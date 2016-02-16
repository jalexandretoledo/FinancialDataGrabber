using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace FinancialDataGrabber.Core.Actor
{
    using Akka.Actor;

    using FinancialDataGrabber.Core.Message;
    using FinancialDataGrabber.CVM;
    using FinancialDataGrabber.CVM.Actors;
    using FinancialDataGrabber.CVM.Messages;

    public class CommandActor
        : UntypedActor
    {
        private IActorRef cvmActor;

        public CommandActor(Int32 userId, String pwd)
        {
            UserId = userId;
            Password = pwd;
        }

        protected Int32 UserId { get; private set; }
        protected String Password { get; private set; }


        protected override void OnReceive(object message)
        {
            if (message is FinancialDataGrabber.Core.Message.UpdateFundos)
            {
                cvmActor = Context.ActorOf(Props.Create<CVMActor>(() => new CVMActor(UserId, Password)), "cvmActor");
                cvmActor.Tell(new FinancialDataGrabber.CVM.Messages.UpdateFundos());
            }
            else if (message is Shutdown)
            {
                Console.WriteLine("Shutting down...");
            }
            else
            {
                Console.WriteLine("Mensagem desconhecida: " + message);
            }
        }
    }
}
