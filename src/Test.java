
import jmyparsec.*;
import static jmyparsec.Parsers.*;

public class Test {

    static final Parser<Character> a = char1('a');
    static final Parser<Character> b = char1('b');
    static final Parser<Character> c = char1('c');

    static final Parser<Character> test5 = or(letter, digit);
    static final Parser<String> test6 = many(or(letter, digit));
    static final Parser<String> test7 = or(sequence(a, b), sequence(c, b));
    static final Parser<String> test8 = or(sequence(a, b), sequence(a, c));
    static final Parser<String> test9 = sequence(a, or(b, c));
    static final Parser<String> test10 = or(tryp(sequence(a, b)), sequence(a, c));
    static final Parser<String> test11 = or(string("ab"), string("ac"));
    static final Parser<String> test12 = or(tryp(string("ab")), string("ac"));

    public static void main(String[] args) {
        parseTest(test5 , "a"     );
        parseTest(test5 , "1"     );
        parseTest(test5 , "!"     );  // NG
        parseTest(test6 , "abc123");
        parseTest(test6 , "123abc");
        parseTest(test7 , "ab"    );
        parseTest(test7 , "cb"    );
        parseTest(test7 , "acb"   );  // NG
        parseTest(test8 , "ab"    );
        parseTest(test8 , "ac"    );  // NG
        parseTest(test9 , "ab"    );
        parseTest(test9 , "ac"    );
        parseTest(test10, "ab"    );
        parseTest(test10, "ac"    );
        parseTest(test11, "ab"    );
        parseTest(test11, "ac"    );  // NG
        parseTest(test12, "ab"    );
        parseTest(test12, "ac"    );
    }
}
