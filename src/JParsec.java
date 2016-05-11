
import java.util.function.Function;

public class JParsec {

    public static <T> void parseTest(Parser<T> p, String src) {
        Source s = new Source(src);
        try {
            System.out.println(p.parse(s));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static final Parser<Character> anyChar = s -> {
        char ret = s.peek();
        s.next();
        return ret;
    };

    public static Parser<Character> satisfy(Function<Character, Boolean> f) {  // 追加
        return s -> {
            char ch = s.peek();
            if (!f.apply(ch)) {
                throw new Exception("not satisfy");
            }
            s.next();
            return ch;
        };
    }

    public static void main(String[] args) {
        parseTest(satisfy(Character::isDigit), "abc");  // NG
        parseTest(satisfy(Character::isDigit), "123");
    }
}
