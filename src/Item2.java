import java.math.BigDecimal;
import java.util.Arrays;

public class Item2 {

    public static void main(String[] args) {
        int number1 = 123_456_7;
        int a = 0b10000111;
        System.out.println(number1);
        System.out.println(a);
        System.out.println(a>>1);
        System.out.println(Integer.MAX_VALUE);
        BigDecimal bigDecimal = new BigDecimal("0.1");
        Integer i = 100;
    }
}
