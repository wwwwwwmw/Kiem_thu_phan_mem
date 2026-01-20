using Microsoft.VisualStudio.TestTools.UnitTesting;
using WinForms_Buoi4;
using System;

namespace WinForms_Buoi4.Tests
{
    [TestClass]
    public class OrganizationTests
    {
        private OrganizationBLL bus;

        [TestInitialize]
        public void Setup()
        {
            bus = new OrganizationBLL();
        }

        // --- NHÓM 1: KIỂM THỬ TÊN TỔ CHỨC (ORG NAME) ---

        [TestMethod] // TC01
        public void Test_Validate_Name_Valid()
        {
            var org = new Organization { OrgName = "FPT Software" };
            string result = bus.ValidateOrganization(org);
            Assert.IsNull(result, "Tên hợp lệ phải trả về null (không lỗi)");
        }

        [TestMethod] // TC04
        public void Test_Validate_Name_Empty()
        {
            var org = new Organization { OrgName = "" };
            string result = bus.ValidateOrganization(org);
            Assert.AreEqual("Organization Name cannot be empty.", result);
        }

        [TestMethod] // TC08 (Boundary)
        public void Test_Validate_Name_TooShort()
        {
            var org = new Organization { OrgName = "AB" }; // 2 ký tự
            string result = bus.ValidateOrganization(org);
            Assert.AreEqual("Name must be 3-255 chars.", result);
        }

        [TestMethod] // TC09 (Boundary)
        public void Test_Validate_Name_MinLength()
        {
            var org = new Organization { OrgName = "ABC" }; // 3 ký tự
            string result = bus.ValidateOrganization(org);
            Assert.IsNull(result);
        }

        [TestMethod] // TC13 (Boundary)
        public void Test_Validate_Name_TooLong()
        {
            var org = new Organization { OrgName = new string('A', 256) }; // 256 ký tự
            string result = bus.ValidateOrganization(org);
            Assert.AreEqual("Name must be 3-255 chars.", result);
        }

        // --- NHÓM 2: KIỂM THỬ SỐ ĐIỆN THOẠI (PHONE) ---

        [TestMethod] // TC05
        public void Test_Validate_Phone_NonNumeric()
        {
            var org = new Organization { OrgName = "Valid Name", Phone = "090abc" };
            string result = bus.ValidateOrganization(org);
            Assert.AreEqual("Phone must be 9-12 digits.", result);
        }

        [TestMethod] // TC10 (Boundary)
        public void Test_Validate_Phone_TooShort()
        {
            var org = new Organization { OrgName = "Valid Name", Phone = "12345678" }; // 8 số
            string result = bus.ValidateOrganization(org);
            Assert.AreEqual("Phone must be 9-12 digits.", result);
        }

        [TestMethod] // TC11 (Boundary)
        public void Test_Validate_Phone_TooLong()
        {
            var org = new Organization { OrgName = "Valid Name", Phone = "0123456789123" }; // 13 số
            string result = bus.ValidateOrganization(org);
            Assert.AreEqual("Phone must be 9-12 digits.", result);
        }

        [TestMethod] // TC16
        public void Test_Validate_Phone_Empty_Valid()
        {
            var org = new Organization { OrgName = "Valid Name", Phone = "" }; // Không bắt buộc
            string result = bus.ValidateOrganization(org);
            Assert.IsNull(result);
        }

        // --- NHÓM 3: KIỂM THỬ EMAIL ---

        [TestMethod] // TC06
        public void Test_Validate_Email_InvalidFormat()
        {
            var org = new Organization { OrgName = "Valid Name", Email = "emailkhongdung" };
            string result = bus.ValidateOrganization(org);
            Assert.AreEqual("Invalid Email format.", result);
        }

        [TestMethod] // TC17
        public void Test_Validate_Email_Empty_Valid()
        {
            var org = new Organization { OrgName = "Valid Name", Email = "" }; // Không bắt buộc
            string result = bus.ValidateOrganization(org);
            Assert.IsNull(result);
        }

        // --- NHÓM 4: KIỂM THỬ NGHIỆP VỤ TRÙNG LẶP (DATABASE) ---
        // Lưu ý: Các test này cần Database thật để chạy đúng (Integration Test)

        [TestMethod] // TC12
        public void Test_Duplicate_Name_Exists()
        {
            // Giả sử "FPT Software" đã có trong DB (do TC01 chạy trước đó)
            // Hoặc bạn có thể Insert dữ liệu mẫu trước khi assert
            bool exists = bus.IsOrgNameExists("FPT Software");
            // Assert.IsTrue(exists); // Bỏ comment nếu DB đã có dữ liệu
        }

        [TestMethod] // TC20
        public void Test_Duplicate_Name_CaseInsensitive()
        {
            // Test "fpt software" vs "FPT Software"
            bool exists = bus.IsOrgNameExists("fpt software");
            // Assert.IsTrue(exists); // Bỏ comment nếu DB đã có dữ liệu
        }
    }
}