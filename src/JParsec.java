
public class JParsec {

    public static <T> void parseTest(Parser<T> p, String src) {
        Source s = new Source(src);
        try {
            System.out.println(p.parse(s));
        } catch (Exception e) {  // 例外処理
            System.out.println(e.getMessage());
        }
    }

    public static final Parser<Character> anyChar = s -> {
        char ret = s.peek();
        s.next();
        return ret;
    };

    public static final Parser<String> test1 = s -> {
        char x1 = anyChar.parse(s);
        char x2 = anyChar.parse(s);
        return new String(new char[]{x1, x2});
    };

    public static final Parser<String> test2 = s -> {
        String x1 = test1.parse(s);
        char x2 = anyChar.parse(s);
        return x1 + x2;
    };

    public static void main(String[] args) {
        parseTest(test2, "12");  // 文字数不足
        parseTest(test2, "123");
    }
}
