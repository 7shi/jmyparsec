
import jparsec.*;
import static jparsec.Parsers.*;

public class Test {

    static final Parser<Character> test5 = or(letter, digit);

    public static void main(String[] args) {
        parseTest(test5, "a");
        parseTest(test5, "1");
        parseTest(test5, "!");  // NG
    }
}
