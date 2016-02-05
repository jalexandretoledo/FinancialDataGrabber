using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace FinancialDataGrabber.Core
{
    using Akka.Actor;

    using FinancialDataGrabber.Core.Actor;

    public class Bootstrap
    {

        private static Bootstrap instance;
        private static Object lockControl = new Object();
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

        private Bootstrap()
        {
            FDGActorSystem = ActorSystem.Create("FinancialDataGrabber");
            controller = FDGActorSystem.ActorOf(Props.Create(() => new ControllerActor()));
        }

        public void Initialize()
        {
            controller.Tell("start");
        }

        public void FundosUpdate(String path)
        {
            FDGActorSystem.ActorSelection(path).Tell("fundos");
        }

        public void Stop()
        {
            controller.Tell("stop");
        }
    }
}
