package dtm.data;

import org.testng.annotations.DataProvider;

public class BlogspotData {

    @DataProvider(name = "data_blogspot")
    public Object[][] getData() {
        return new Object[][] {
                // {Name, Email, Phone, Address, Gender, Days, Country, Expected, Description}
                {"Nguyen Van A", "a@test.com", "0987654321", "123 Street, Hanoi", "male", "monday", "Vietnam", "PASS", "TC_001: Hợp lệ"},
                {"", "b@test.com", "0912345678", "Hanoi", "female", "tuesday", "Canada", "FAIL", "TC_002: Name trống"},
                {"A", "c@test.com", "0912345678", "Hanoi", "male", "wednesday", "Japan", "PASS", "TC_003: Name 1 ký tự"},
                {"Ten Qua Dai Nguyen Van A Nguyen Van A Nguyen Van A", "d@test.com", "0912345678", "Hanoi", "female", "thursday", "United Kingdom", "PASS", "TC_004: Name 50 ký tự"},
                {"Tran B", "invalid-email", "0912345678", "HCM", "male", "friday", "Germany", "FAIL", "TC_005: Email sai định dạng"},
                {"Le C", "", "0912345678", "Danang", "female", "saturday", "France", "FAIL", "TC_006: Email trống"},
                {"Hoang D", "d@test.com", "12345", "Can Tho", "male", "sunday", "Australia", "FAIL", "TC_007: Phone ngắn (5 số)"},
                {"Phan E", "e@test.com", "09876543210", "Hai Phong", "female", "monday", "India", "FAIL", "TC_008: Phone dài (11 số)"},
                {"Vu F", "f@test.com", "0912abc345", "Bac Ninh", "male", "tuesday", "Vietnam", "FAIL", "TC_009: Phone chứa chữ"},
                {"Do G", "g@test.com", "", "Address", "female", "wednesday", "Canada", "FAIL", "TC_010: Phone trống"},
                {"Bui H", "h@test.com", "0912345678", "", "male", "thursday", "Japan", "FAIL", "TC_011: Address trống"},
                {"@#$%", "i@test.com", "0912345678", "Address", "female", "friday", "Germany", "PASS", "TC_012: Name ký tự đặc biệt"},
                {"Nguyen I", "i@test.com", "0912345678", "Short", "male", "saturday", "France", "PASS", "TC_013: Address ngắn"},
                {"Tran J", "j@test.com", "0912345678", "Address", "female", "monday", "Vietnam", "PASS", "TC_014: Chỉnh giới tính Female"},
                {"Le K", "k@test.com", "0912345678", "Address", "male", "tuesday", "Canada", "PASS", "TC_015: Chọn Tuesday"},
                {"Hoang L", "l@test.com", "0912345678", "Address", "female", "wednesday", "Japan", "PASS", "TC_016: Chọn Wednesday"},
                {"Phan M", "m@test.com", "0912345678", "Address", "male", "thursday", "Germany", "PASS", "TC_017: Chọn Thursday"},
                {"Vu N", "n@test.com", "0912345678", "Address", "female", "friday", "France", "PASS", "TC_018: Chọn Friday"},
                {"Do O", "o@test.com", "0912345678", "Address", "male", "saturday", "Australia", "PASS", "TC_019: Chọn Saturday"},
                {"Bui P", "p@test.com", "0912345678", "Address", "female", "sunday", "India", "PASS", "TC_020: Chọn Sunday"},
                {"Nguyen Q", "q@test.com", "0912345678", "Address", "male", "monday", "Vietnam", "PASS", "TC_021: Country Vietnam"},
                {"Tran R", "r@test.com", "0912345678", "Address", "female", "tuesday", "United Kingdom", "PASS", "TC_022: Country UK"},
                {"Le S", "s@test.com", "0912345678", "Address", "male", "wednesday", "USA", "PASS", "TC_023: Country USA"},
                {"Hoang T", "t@test.com", "0912345678", "Address", "female", "thursday", "Australia", "PASS", "TC_024: Country Australia"},
                {"Phan U", "u@test.com", "0912345678", "Address", "male", "friday", "Japan", "PASS", "TC_025: Country Japan"},
                {"Vu V", "v@test.com", "0912345678", "Address", "female", "saturday", "Canada", "PASS", "TC_026: Country Canada"},
                {"Full Test", "full@test.com", "0333444555", "Full Address 123", "male", "friday", "Vietnam", "PASS", "TC_027: Kiểm tra tổng thể"}
        };
    }
}