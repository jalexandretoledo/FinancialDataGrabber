using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace FinancialDataGrabber.Core.Message
{
    public class UpdateFundos
    {
        public UpdateFundos() { }

        public override string ToString()
        {
            return "<UpdateFundos>";
        }
    }

    public class Shutdown
    {
        public Shutdown() { }

        public override string ToString()
        {
            return "<Shutdown>";
        }
    }

    public class Start
    {
        public Start(Int32 userId, String pwd)
        {
            UserId = userId;
            Password = pwd;
        }

        public Int32 UserId { get; private set; }
        public String Password { get; private set; }

        public override string ToString()
        {
            return "<Start>";
        }
    }

}
