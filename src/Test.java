
import jparsec.*;
import static jparsec.Parsers.*;

public class Test {

    static final Parser<String> test3 = sequence(letter, replicate(2, digit));

    public static void main(String[] args) {
        parseTest(test3, "abc");  // NG
        parseTest(test3, "123");  // NG
        parseTest(test3, "a23");
        parseTest(test3, "a234");
    }
}
