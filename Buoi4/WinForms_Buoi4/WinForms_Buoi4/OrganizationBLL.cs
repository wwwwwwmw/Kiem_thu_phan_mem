using System;
using System.Data.SqlClient; 
using System.Text.RegularExpressions;

namespace WinForms_Buoi4
{
    public class OrganizationBLL
    {
        // LƯU Ý: Đảm bảo Server Name đúng với máy bạn (DESKTOP-ENDTJTR)
        private string connectionString = @"Data Source=DESKTOP-ENDTJTR;Initial Catalog=OrganizationDB;Integrated Security=True";

        // Sửa kiểu trả về thành string? (cho phép trả về null nếu không có lỗi)
        public string? ValidateOrganization(Organization org)
        {
            if (string.IsNullOrWhiteSpace(org.OrgName)) return "Organization Name cannot be empty.";
            if (org.OrgName!.Length < 3 || org.OrgName.Length > 255) return "Name must be 3-255 chars.";

            if (!string.IsNullOrEmpty(org.Phone) && !Regex.IsMatch(org.Phone, @"^\d{9,12}$"))
                return "Phone must be 9-12 digits.";

            if (!string.IsNullOrEmpty(org.Email))
            {
                try { var addr = new System.Net.Mail.MailAddress(org.Email); }
                catch { return "Invalid Email format."; }
            }
            return null;
        }

        public bool IsOrgNameExists(string? orgName)
        {
            if (string.IsNullOrEmpty(orgName)) return false;

            using (SqlConnection conn = new SqlConnection(connectionString))
            {
                conn.Open();
                SqlCommand cmd = new SqlCommand("SELECT COUNT(*) FROM ORGANIZATION WHERE OrgName = @Name", conn);
                cmd.Parameters.AddWithValue("@Name", orgName);
                return (int)cmd.ExecuteScalar() > 0;
            }
        }

        public bool SaveOrganization(Organization org, out string message)
        {
            // Bước 1: Validate
            string? error = ValidateOrganization(org);
            if (error != null)
            {
                message = error;
                return false;
            }

            // Bước 2: Check trùng
            if (IsOrgNameExists(org.OrgName))
            {
                message = "Organization Name already exists";
                return false;
            }

            // Bước 3: Lưu DB
            try
            {
                using (SqlConnection conn = new SqlConnection(connectionString))
                {
                    conn.Open();
                    string query = @"INSERT INTO ORGANIZATION (OrgName, Address, Phone, Email) 
                                     VALUES (@Name, @Address, @Phone, @Email)";
                    SqlCommand cmd = new SqlCommand(query, conn);

                    // Xử lý null an toàn
                    cmd.Parameters.AddWithValue("@Name", org.OrgName);
                    cmd.Parameters.AddWithValue("@Address", (object?)org.Address ?? DBNull.Value);
                    cmd.Parameters.AddWithValue("@Phone", (object?)org.Phone ?? DBNull.Value);
                    cmd.Parameters.AddWithValue("@Email", (object?)org.Email ?? DBNull.Value);

                    cmd.ExecuteNonQuery();
                }
                message = "Save successfully";
                return true;
            }
            catch (Exception ex) { message = "Error: " + ex.Message; return false; }
        }
    }
}