
import jmyparsec.*;
import static jmyparsec.Parsers.*;

public class Test {

    static final Parser<String> test1 = sequence(anyChar, anyChar);
    static final Parser<String> test2 = sequence(test1, anyChar);
    static final Parser<String> test3 = sequence(letter, replicate(2, digit));
    static final Parser<String> test4 = many(alpha);

    public static void main(String[] args) throws Exception {
        parseTest(anyChar   , "abc"   );
        parseTest(test1     , "abc"   );
        parseTest(test2     , "abc"   );
        parseTest(test2     , "12"    );  // NG
        parseTest(test2     , "123"   );
        parseTest(char1('a'), "abc"   );
        parseTest(char1('a'), "123"   );  // NG
        parseTest(digit     , "abc"   );  // NG
        parseTest(digit     , "123"   );
        parseTest(letter    , "abc"   );
        parseTest(letter    , "123"   );  // NG
        parseTest(test3     , "abc"   );  // NG
        parseTest(test3     , "123"   );  // NG
        parseTest(test3     , "a23"   );
        parseTest(test3     , "a234"  );
        parseTest(test4     , "abc123");
        parseTest(test4     , "123abc");

        Source s1 = new Source("abc123");
        String s1a = many(alpha).parse(s1);
        String s1b = many(digit).parse(s1);
        System.out.println(s1a + "," + s1b);

        Source s2 = new Source("abcde9");
        String s2a = many(alpha).parse(s2);
        String s2b = many(digit).parse(s2);
        System.out.println(s2a + "," + s2b);
    }
}
