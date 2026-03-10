package org.example.bai7;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.Story;
import org.example.utils.Item;
import org.example.utils.OrderProcessor;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static io.qameta.allure.SeverityLevel.CRITICAL;
import static io.qameta.allure.SeverityLevel.NORMAL;

@Feature("Bai 7 - OrderProcessor")
public class OrderProcessorTest {

    private final OrderProcessor processor = new OrderProcessor();

    private List<Item> items(double... prices) {
        return Arrays.stream(prices)
                .mapToObj(p -> new Item("Item-" + p, p))
                .toList();
    }

    @Test
    @Story("Basis Path")
    @Severity(CRITICAL)
    @Description("P1 - Giỏ hàng rỗng phải ném exception")
    public void testP1_EmptyCart() {
        Assert.assertThrows(
                IllegalArgumentException.class,
                () -> processor.calculateTotal(Collections.emptyList(), null, "SILVER", "CARD")
        );
    }

    @Test
    @Story("Basis Path")
    @Severity(NORMAL)
    @Description("P2 - Không coupon, không member, online, total < 500k")
    public void testP2_NoCoupon_NoMember_Online_Ship30k() {
        double actual = processor.calculateTotal(
                items(100_000, 200_000),
                null,
                "SILVER",
                "CARD"
        );

        Assert.assertEquals(actual, 330_000, 0.01);
    }

    @Test
    @Story("Basis Path")
    @Severity(NORMAL)
    @Description("P3 - Không coupon, không member, COD, total < 500k")
    public void testP3_NoCoupon_NoMember_COD_Ship20k() {
        double actual = processor.calculateTotal(
                items(100_000, 200_000),
                null,
                "SILVER",
                "COD"
        );

        Assert.assertEquals(actual, 320_000, 0.01);
    }

    @Test
    @Story("Basis Path")
    @Severity(CRITICAL)
    @Description("P4 - SALE10 + GOLD + online")
    public void testP4_SALE10_GOLD_Online() {
        double actual = processor.calculateTotal(
                items(200_000, 100_000),
                "SALE10",
                "GOLD",
                "CARD"
        );

        // subtotal = 300000
        // discount = 30000
        // memberDiscount = 13500
        // total = 256500
        // ship online = 30000
        Assert.assertEquals(actual, 286_500, 0.01);
    }

    @Test
    @Story("Basis Path")
    @Severity(CRITICAL)
    @Description("P5 - SALE20 + PLATINUM + COD")
    public void testP5_SALE20_PLATINUM_COD() {
        double actual = processor.calculateTotal(
                items(200_000, 100_000),
                "SALE20",
                "PLATINUM",
                "COD"
        );

        // subtotal = 300000
        // discount = 60000
        // memberDiscount = 24000
        // total = 216000
        // COD ship = 20000
        Assert.assertEquals(actual, 236_000, 0.01);
    }

    @Test
    @Story("Basis Path")
    @Severity(CRITICAL)
    @Description("P6 - Coupon không hợp lệ phải ném exception")
    public void testP6_InvalidCoupon() {
        Assert.assertThrows(
                IllegalArgumentException.class,
                () -> processor.calculateTotal(items(100_000, 100_000), "ABC", "SILVER", "CARD")
        );
    }

    @Test
    @Story("Basis Path")
    @Severity(NORMAL)
    @Description("P7 - Không coupon, GOLD, total >= 500k, không tính ship")
    public void testP7_NoCoupon_GOLD_NoShip() {
        double actual = processor.calculateTotal(
                items(400_000, 200_000),
                null,
                "GOLD",
                "CARD"
        );

        // subtotal = 600000
        // memberDiscount = 30000
        Assert.assertEquals(actual, 570_000, 0.01);
    }

    @Test
    @Story("Basis Path")
    @Severity(NORMAL)
    @Description("P8 - Không coupon, PLATINUM, total >= 500k, không tính ship")
    public void testP8_NoCoupon_PLATINUM_NoShip() {
        double actual = processor.calculateTotal(
                items(400_000, 200_000),
                null,
                "PLATINUM",
                "CARD"
        );

        // subtotal = 600000
        // memberDiscount = 60000
        Assert.assertEquals(actual, 540_000, 0.01);
    }

    @Test
    @Story("Basis Path")
    @Severity(NORMAL)
    @Description("P9 - SALE10, không member, total >= 500k, không tính ship")
    public void testP9_SALE10_NoMember_NoShip() {
        double actual = processor.calculateTotal(
                items(400_000, 200_000),
                "SALE10",
                "SILVER",
                "CARD"
        );

        // subtotal = 600000
        // discount = 60000
        Assert.assertEquals(actual, 540_000, 0.01);
    }

    @Test
    @Story("MC/DC Coupon")
    @Severity(CRITICAL)
    @Description("M1 - A=T, B=T, C=T: coupon SALE10 hợp lệ")
    public void testM1_Coupon_SALE10() {
        double actual = processor.calculateTotal(
                items(100_000, 100_000),
                "SALE10",
                "SILVER",
                "CARD"
        );

        // 200000 - 10% = 180000 + 30000 ship
        Assert.assertEquals(actual, 210_000, 0.01);
    }

    @Test
    @Story("MC/DC Coupon")
    @Severity(CRITICAL)
    @Description("M2 - A=F: coupon null, không áp dụng giảm giá")
    public void testM2_Coupon_Null() {
        double actual = processor.calculateTotal(
                items(100_000, 100_000),
                null,
                "SILVER",
                "CARD"
        );

        Assert.assertEquals(actual, 230_000, 0.01);
    }

    @Test
    @Story("MC/DC Coupon")
    @Severity(CRITICAL)
    @Description("M3 - B=F: coupon rỗng, không áp dụng giảm giá")
    public void testM3_Coupon_Empty() {
        double actual = processor.calculateTotal(
                items(100_000, 100_000),
                "",
                "SILVER",
                "CARD"
        );

        Assert.assertEquals(actual, 230_000, 0.01);
    }

    @Test
    @Story("MC/DC Coupon")
    @Severity(CRITICAL)
    @Description("M4 - C=F: coupon SALE20 đi sang nhánh else-if D4")
    public void testM4_Coupon_SALE20() {
        double actual = processor.calculateTotal(
                items(100_000, 100_000),
                "SALE20",
                "SILVER",
                "CARD"
        );

        // 200000 - 40000 = 160000 + 30000 ship
        Assert.assertEquals(actual, 190_000, 0.01);
    }
}