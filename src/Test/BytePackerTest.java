package Test;
import Main.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BytePackerTest {

    @Test
    void test_incrementA()
    {
        byte num = 0x25;
        byte result = BytePacker.incrementA(num);
        assertEquals(0x35, result);
    }

    @Test
    void test_unpackSum()
    {
        byte num = BytePacker.pack(7,5);
        int result = BytePacker.unpackSum(num);
        assertEquals(12,result);
    }

    @Test
    void test_incrementB()
    {
        byte num = 0x25;
        byte result = BytePacker.incrementB(num);
        assertEquals(0x26, result);
    }

    @Test
    void test_toDouble()
    {
        byte num = BytePacker.pack(7,5);
        double  result = BytePacker.toDouble(num);
        assertEquals(0.75, result);
    }

    @Test
    void test_increasePercent_by_10()
    {
        byte num = BytePacker.pack(6,5);
        double result = BytePacker.toDouble(BytePacker.increasePercent(num));
        assertEquals(0.75, result);
    }

    @Test
    void test_increasePercent_by_0()
    {
        byte num = BytePacker.pack(7,5);
        double result = BytePacker.toDouble(BytePacker.increasePercent(num));
        assertEquals(0.75, result);
    }

    @Test
    void test_decreasePercent_by_10()
    {
        byte num = BytePacker.pack(6,5);
        double result = BytePacker.toDouble(BytePacker.decreasePercent(num));
        assertEquals(0.55, result);
    }

    @Test
    void test_decreasePercent_by_0()
    {
        byte num = BytePacker.pack(2,5);
        double result = BytePacker.toDouble(BytePacker.decreasePercent(num));
        assertEquals(0.25, result);
    }
}