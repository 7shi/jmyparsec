
public class JParsec {

    public static char anyChar(Source s) {
        char ret = s.peek();
        s.next();
        return ret;
    }

    public static String test1(Source s) {  // メソッド化
        char x1 = anyChar(s);
        char x2 = anyChar(s);
        return new String(new char[]{x1, x2});
    }

    public static void main(String[] args) {
        Source s = new Source("abc");
        System.out.println(anyChar(s));
        System.out.println(test1(s));
    }
}
