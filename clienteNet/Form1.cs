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


        List<localhost.Sensor> listaSensores = new List<localhost.Sensor>();


        private void Button1_Click(object sender, EventArgs e)
        {

            String ip_port = tbAddSonda.Text;

            localhost.Sensor sensor = new localhost.Sensor();

            sensor.Url = "http://" + ip_port + "/Sensor/services/Sensor.SensorHttpSoap11Endpoint/";

            listaSensores.Add(sensor);

            escribirSondas();

        }

        private void escribirSondas() {

            cbAtributos.Items.Clear();


            descAddSonda.Text = "";
            for (int i = 0; i < listaSensores.Count; i++)
            {
                descAddSonda.Text = "Sensor: " + listaSensores[i].getNombre() + "\r\n";
                cbSondasConsultar.Items.Add(listaSensores[i].getNombre());
            }

            cbAtributos.Items.Add("Volumen");
            cbAtributos.Items.Add("Fecha");
            cbAtributos.Items.Add("Led");
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


    }
}
