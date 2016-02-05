using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace FinancialDataGrabber.Core.Actor
{
    using Akka.Actor;

    internal class CommandActor
        : UntypedActor
    {
        protected override void OnReceive(object message)
        {
            var str = (message as String);
            if (str != null)
            {
                if ("fundos".Equals(str, StringComparison.CurrentCultureIgnoreCase))
                {
                    Console.WriteLine("Fundos");
                } 
            }
        }
    }
}
