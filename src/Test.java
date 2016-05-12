
import jparsec.*;
import static jparsec.Parsers.*;

public class Test {

    static final Parser<Character> a = char1('a');
    static final Parser<Character> b = char1('b');
    static final Parser<Character> c = char1('c');

    static final Parser<String> test7 = or(sequence(a, b), sequence(c, b));

    public static void main(String[] args) {
        parseTest(test7, "ab");
        parseTest(test7, "cb");
        parseTest(test7, "acb");  // ???
    }
}
