
import JParsec.*;
import static JParsec.Parsers.*;

public class Test {

    public static final Parser<String> test3 = s -> {
        char x1 = letter.parse(s);
        char x2 = digit.parse(s);
        char x3 = digit.parse(s);
        return new String(new char[]{x1, x2, x3});
    };

    public static void main(String[] args) {
        parseTest(test3, "abc");  // NG
        parseTest(test3, "123");  // NG
        parseTest(test3, "a23");
        parseTest(test3, "a234");
    }
}
