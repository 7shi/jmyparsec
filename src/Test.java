
import jmyparsec.*;
import static jmyparsec.Parsers.*;

public class Test {

    static final Parser<Character> a = char1('a');
    static final Parser<Character> b = char1('b');
    static final Parser<Character> c = char1('c');

    static final Parser<String> test1 = sequence(anyChar, anyChar);
    static final Parser<String> test2 = sequence(test1, anyChar);
    static final Parser<String> test3 = sequence(letter, replicate(2, digit));
    static final Parser<Character> test5 = or(letter, digit);
    static final Parser<String> test7 = or(sequence(a, b), sequence(c, b));
    static final Parser<String> test8 = or(sequence(a, b), sequence(a, c));
    static final Parser<String> test11 = or(string("ab"), string("ac"));

    public static void main(String[] args) {
        parseTest(test2     , "12" );  // NG
        parseTest(char1('a'), "123");  // NG
        parseTest(digit     , "abc");  // NG
        parseTest(letter    , "123");  // NG
        parseTest(test3     , "abc");  // NG
        parseTest(test3     , "123");  // NG
        parseTest(test5     , "!"  );  // NG
        parseTest(test7     , "acb");  // NG
        parseTest(test8     , "ac" );  // NG
        parseTest(test11    , "ac" );  // NG
    }
}
