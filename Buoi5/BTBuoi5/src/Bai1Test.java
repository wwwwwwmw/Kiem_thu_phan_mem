import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Bai1Test {
    private Bai1 math;

    // @Before: Chạy trước MỖI test case
    // Giúp đảm bảo mỗi bài test đều dùng một object mới, không bị dính dữ liệu cũ
    @Before
    public void init() {
        math = new Bai1();
    }

    // @After: Chạy sau MỖI test case
    @After
    public void tearDown() {
        math = null;
    }


    @Test
    public void testCalls() {
        assertEquals(0, math.getCalls());

        math.factorial(1);
        assertEquals(1, math.getCalls());

        math.factorial(1);
        assertEquals(2, math.getCalls());
    }


    @Test
    public void testFactorial() {
        assertTrue(math.factorial(0) == 1);
        assertTrue(math.factorial(1) == 1);
        assertTrue(math.factorial(5) == 120);
    }


    @Test(expected = IllegalArgumentException.class)
    public void testFactorialNegative() {
        math.factorial(-1);
    }


    @Ignore
    @Test
    public void testTodo() {
        assertTrue(math.plus(1, 1) == 3);
    }
}