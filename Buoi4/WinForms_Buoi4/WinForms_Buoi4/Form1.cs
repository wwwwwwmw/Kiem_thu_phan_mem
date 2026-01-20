using System;
using System.Windows.Forms;
using WinForms_Buoi4;

namespace WinForms_Buoi4
{
    public partial class Form1 : Form
    {
        private OrganizationBLL bus;

        public Form1()
        {
            InitializeComponent();
            bus = new OrganizationBLL();
            // Nút Director đã được set Enabled = false trong file Designer 
        }

        // Sự kiện nút Save
        private void btnSave_Click(object sender, EventArgs e)
        {
            Organization org = new Organization
            {
                OrgName = txtOrgName.Text.Trim(),
                Address = txtAddress.Text.Trim(),
                Phone = txtPhone.Text.Trim(),
                Email = txtEmail.Text.Trim()
            };

            string message;
            bool isSuccess = bus.SaveOrganization(org, out message);

            MessageBox.Show(message, isSuccess ? "Success" : "Error",
                MessageBoxButtons.OK, isSuccess ? MessageBoxIcon.Information : MessageBoxIcon.Error);

            if (isSuccess)
            {
                btnDirector.Enabled = true; //  Enable nút Director
            }
        }

        // Sự kiện nút Back
        private void btnBack_Click(object sender, EventArgs e)
        {
            this.Close(); //  Đóng form
        }

        // Sự kiện nút Director
        private void btnDirector_Click(object sender, EventArgs e)
        {
            MessageBox.Show("Mở Form Director (Chức năng này chưa yêu cầu code chi tiết).");
            // Logic mở form Director sẽ đặt ở đây 
        }
    }
}