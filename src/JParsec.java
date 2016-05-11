
import java.util.function.Function;

public class JParsec {

    public static <T> void parseTest(Function<Source, T> p, String src) {  // 追加
        Source s = new Source(src);
        System.out.println(p.apply(s));
    }

    public static char anyChar(Source s) {
        char ret = s.peek();
        s.next();
        return ret;
    }

    public static String test1(Source s) {
        char x1 = anyChar(s);
        char x2 = anyChar(s);
        return new String(new char[]{x1, x2});
    }

    public static String test2(Source s) {
        String x1 = test1(s);
        char x2 = anyChar(s);
        return x1 + x2;
    }

    public static void main(String[] args) {
        parseTest(JParsec::anyChar, "abc");
        parseTest(JParsec::test1, "abc");
        parseTest(JParsec::test2, "abc");
    }
}
