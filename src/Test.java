
import jparsec.*;
import static jparsec.Parsers.*;

public class Test {

    static final Parser<String> test6 = many(or(letter, digit));

    public static void main(String[] args) {
        parseTest(test6, "abc123");
        parseTest(test6, "123abc");
    }
}
