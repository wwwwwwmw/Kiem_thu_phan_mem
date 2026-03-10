package org.example.bai6;

import org.example.utils.PhoneValidator;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class PhoneValidatorTest {

    @DataProvider(name = "phoneData")
    public Object[][] phoneData() {
        return new Object[][]{
                {"TC01 - So hop le dang 0xxxxxxxxx", "0912345678", true},
                {"TC02 - So hop le dang +84", "+84912345678", true},
                {"TC03 - So hop le co khoang trang", "+84 912 345 678", true},
                {"TC04 - Rong", "", false},
                {"TC05 - Null", null, false},
                {"TC06 - Sai dau so 01", "0112345678", false},
                {"TC07 - Qua ngan", "091234567", false},
                {"TC08 - Qua dai", "09123456789", false},
                {"TC09 - Co ky tu dac biet", "09123-45678", false},
                {"TC10 - Email-like", "09abc45678", false},
                {"TC11 - +84 nhung khong du do dai", "+8491234567", false},
                {"TC12 - Dau + sai vi tri", "09+12345678", false},
                {"TC13 - Chi la khoang trang", "   ", false},
                {"TC14 - Dau so 03 hop le", "0398765432", true},
                {"TC15 - Dau so 07 hop le", "0798765432", true}
        };
    }

    @Test(dataProvider = "phoneData", description = "Basis Path + Boundary test cho PhoneValidator")
    public void testPhoneValidation(String moTa, String phone, boolean expected) {
        boolean actual = PhoneValidator.isValid(phone);
        Assert.assertEquals(actual, expected, "Sai ket qua voi: " + moTa);
    }

    @Test(description = "Boundary - dung 10 chu so sau chuan hoa")
    public void testBoundary_Exactly10Digits() {
        Assert.assertTrue(
                PhoneValidator.isValid("0912345678"),
                "So 10 chu so hop le phai pass."
        );
    }

    @Test(description = "Boundary - 9 chu so sau chuan hoa")
    public void testBoundary_9Digits() {
        Assert.assertFalse(
                PhoneValidator.isValid("091234567"),
                "So 9 chu so phai fail."
        );
    }

    @Test(description = "Boundary - 11 chu so sau chuan hoa")
    public void testBoundary_11Digits() {
        Assert.assertFalse(
                PhoneValidator.isValid("09123456789"),
                "So 11 chu so phai fail."
        );
    }
}