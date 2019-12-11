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

        string user = "a";
        string pass = "a";


        public Login()
        {
            InitializeComponent();
            tbPassword.UseSystemPasswordChar = true;

        }

        private void Button1_Click(object sender, EventArgs e)
        {
            if (user == tbUser.Text && pass == tbPassword.Text)
            {
                this.Hide();
                Form1 f1 = new Form1();
                f1.Show();
            }
            else {
                Console.WriteLine(user + "/" + tbUser.Text);
                Console.WriteLine(pass + "/" + tbPassword.Text);
            }


        }

        public bool validar(string user, string password) {
            return true;
        }

        private void Login_Load(object sender, EventArgs e)
        {
            string[] lines = System.IO.File.ReadAllLines(@"C:\Users\EPS\Desktop\SD\clienteNet\users.txt");
            user = lines[0];
            pass = lines[1];
        }
    }
}
