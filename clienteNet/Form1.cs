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
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
        }

        private void bAddSonda(object sender, EventArgs e)
        {

            localhost1.Sensor sensor = new localhost1.Sensor();


            sensor.readSensor("C://Users//EPS//Desktop//SD//clienteNet//Sensor1.txt");

            string s = "Sensor " + sensor.getFecha() + " " + sensor.getLed(); 

            descAddSonda.Text = s;
        }


        private void Form1_Load(object sender, EventArgs e)
        {
            
        }

        private void Button2_Click(object sender, EventArgs e)
        {

        }

        private void TextBox3_TextChanged(object sender, EventArgs e)
        {

        }

        private void ComboBox2_SelectedIndexChanged(object sender, EventArgs e)
        {

        }

        private void ComboBox1_SelectedIndexChanged(object sender, EventArgs e)
        {

        }



        private void Button3_Click(object sender, EventArgs e)
        {

        }

        private void Label1_Click(object sender, EventArgs e)
        {

        }

        private void Button1_Click(object sender, EventArgs e)
        {


           

            descAddSonda.Text = s;
        }
    }
}
