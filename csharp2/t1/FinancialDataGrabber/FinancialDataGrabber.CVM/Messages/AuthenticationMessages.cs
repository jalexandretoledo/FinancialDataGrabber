using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace FinancialDataGrabber.CVM.Messages
{
    using FinancialDataGrabber.CVM.CVMWeb;

    public abstract class AuthenticationMessage { }

    public class AuthenticationSuccess
        : AuthenticationMessage
    {
        public AuthenticationSuccess(sessaoIdHeader login)
        {
            LoginInfo = login;
        }

        public sessaoIdHeader LoginInfo { get; private set; }
    }

    public class AuthenticationFailed: AuthenticationMessage { }

    public class AuthenticationCancelled: AuthenticationMessage { }

}
