
import static JParsec.Parsers.*;

public class Test {

    public static void main(String[] args) {
        parseTest(digit, "abc");  // NG
        parseTest(digit, "123");
        parseTest(letter, "abc");
        parseTest(letter, "123");  // NG
    }
}
