using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace Sample
{
    using Akka.Actor;

    using FinancialDataGrabber.Core;

    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
        }

        private void button1_Click(object sender, EventArgs e)
        {

            var userId = Properties.Settings.Default.UserId;
            var password = Properties.Settings.Default.Password;

            Bootstrap.Instance.Initialize(userId, password);

        }

        private void button3_Click(object sender, EventArgs e)
        {
            Bootstrap.Instance.FundosUpdate();
        }

        private void button2_Click(object sender, EventArgs e)
        {
            Bootstrap.Instance.Stop();
        }
    }
}
