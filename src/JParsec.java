
public class JParsec {

    public static <T> void parseTest(Parser<T> p, String src) {  // 使用箇所
        Source s = new Source(src);
        System.out.println(p.parse(s));
    }

    public static final Parser<Character> anyChar = s -> {  // ラムダ式化
        char ret = s.peek();
        s.next();
        return ret;
    };                                                      // セミコロン

    public static final Parser<String> test1 = s -> {       // ラムダ式化
        char x1 = anyChar.parse(s);                         // .parse
        char x2 = anyChar.parse(s);                         // .parse
        return new String(new char[]{x1, x2});
    };                                                      // セミコロン

    public static final Parser<String> test2 = s -> {       // ラムダ式化
        String x1 = test1.parse(s);                         // .parse
        char x2 = anyChar.parse(s);                         // .parse
        return x1 + x2;
    };                                                      // セミコロン

    public static void main(String[] args) {
        parseTest(anyChar, "abc");  // 単純化
        parseTest(test1, "abc");    // 単純化
        parseTest(test2, "abc");    // 単純化
    }
}
