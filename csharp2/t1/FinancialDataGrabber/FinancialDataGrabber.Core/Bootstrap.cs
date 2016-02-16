using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace FinancialDataGrabber.Core
{
    using Akka.Actor;

    using FinancialDataGrabber.Core.Actor;
    using FinancialDataGrabber.Core.Message;

    public class Bootstrap
    {

        private static Bootstrap instance;
        private static Object lockControl = new Object();
        private static String commandPath = "/user/controllerActor/commandProcessor";

        public static Bootstrap Instance
        {
            get
            {
                if (instance == null)
                {
                    lock (lockControl)
                    {
                        if (instance == null)
                        {
                            instance = new Bootstrap();
                        }
                    }
                }

                return instance;
            }
        }

        private ActorSystem FDGActorSystem;
        private IActorRef controller;

        private Boolean isInitialized = false;

        private Bootstrap()
        {
            FDGActorSystem = ActorSystem.Create("FinancialDataGrabber");
            controller = FDGActorSystem.ActorOf(Props.Create(() => new ControllerActor()), "controllerActor");
        }

        public void Initialize(Int32 userId, String pwd)
        {
            
            controller.Tell(new Start(userId, pwd));
            isInitialized = true;
        }

        public void FundosUpdate()
        {
            if (!isInitialized)
            {
                throw new Exception("Not initialized");
            }

            FDGActorSystem.ActorSelection(commandPath).Tell(new UpdateFundos());
        }

        public void Stop()
        {
            controller.Tell(new Shutdown());
            isInitialized = false;
        }
    }
}
