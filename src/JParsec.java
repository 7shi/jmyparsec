
public class JParsec {

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

    public static String test2(Source s) {  // 追加
        String x1 = test1(s);
        char x2 = anyChar(s);
        return x1 + x2;
    }

    public static void main(String[] args) {
        Source s1 = new Source("abc");
        System.out.println(anyChar(s1));
        Source s2 = new Source("abc");
        System.out.println(test1(s2));
        Source s3 = new Source("abc");
        System.out.println(test2(s3));
    }
}
