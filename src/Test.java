
import jparsec.*;
import static jparsec.Parsers.*;

public class Test {

    public static void main(String[] args) throws Exception {
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
