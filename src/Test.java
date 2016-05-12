
import jmyparsec.*;
import static jmyparsec.Parsers.*;

public class Test {

    static final Parser<Character> a = char1('a');
    static final Parser<Character> b = char1('b');
    static final Parser<Character> c = char1('c');

    static final Parser<String> test8 = or(sequence(a, b), sequence(a, c));
    static final Parser<String> test9 = sequence(a, or(b, c));
    static final Parser<String> test10 = or(tryp(sequence(a, b)), sequence(a, c));

    public static void main(String[] args) {
        parseTest(test8 , "ab");
        parseTest(test8 , "ac");  // NG
        parseTest(test9 , "ab");
        parseTest(test9 , "ac");
        parseTest(test10, "ab");
        parseTest(test10, "ac");
    }
}
