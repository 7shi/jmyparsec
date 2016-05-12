
import jparsec.*;
import static jparsec.Parsers.*;

public class Test {

    static final Parser<String> test4 = many(alpha);

    public static void main(String[] args) {
        parseTest(test4, "abc123");
        parseTest(test4, "123abc");
    }
}
