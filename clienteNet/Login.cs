using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace clienteNet
{
    public partial class Login : Form
    {


        public Login()
        {
            InitializeComponent();
            tbPassword.UseSystemPasswordChar = true;
        }

        private void Button1_Click(object sender, EventArgs e)
        {
            string user = tbUser.Text;
            string password = tbPassword.Text;


            Console.Write(user + " / "+  password);

            this.Hide();
            Form1 f1 = new Form1();
            f1.Show();
        }

        public bool validar(string user, string password) {
            return true;
        }
    }
}
