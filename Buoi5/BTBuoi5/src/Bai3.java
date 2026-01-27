import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import java.util.ArrayList;
import org.junit.*;

public class Bai3 {
    private ArrayList<String> list;


    @BeforeClass
    public static void beforeClass() {
        System.out.println("=== @BeforeClass: Khởi động (VD: Kết nối DB) ===");
    }


    @AfterClass
    public static void afterClass() {
        System.out.println("=== @AfterClass: Dọn dẹp (VD: Ngắt kết nối DB) ===");
    }


    @Before
    public void before() {
        list = new ArrayList<String>();
        System.out.println("\n--> @Before: Tạo mới mảng list");
    }


    @After
    public void after() {
        list.clear();
        System.out.println("--> @After: Xóa sạch mảng list");
    }


    @Test
    public void testThemPhanTu() {
        list.add("test");
        assertFalse(list.isEmpty());
        assertEquals(1, list.size());
        System.out.println("    [Chạy Test 1]: testThemPhanTu");
    }


    @Ignore
    @Test
    public void testBoQua() {
        System.out.println("    [Chạy Test 2]: Test này sẽ không được in ra");
    }


    @Test(timeout = 10)
    public void testTimeout() {
        System.out.println("    [Chạy Test 3]: Kiểm tra tốc độ xử lý");
    }


    @Test(expected = NoSuchMethodException.class)
    public void testNgoaiLe() throws NoSuchMethodException {
        System.out.println("    [Chạy Test 4]: Kiểm tra bắt lỗi Exception");
        throw new NoSuchMethodException();
    }
}