
public class JParsec {

    public static char anyChar(Source s) {
        char ret = s.peek();
        s.next();
        return ret;
    }

    public static void main(String[] args) {
        Source s = new Source("abc");
        System.out.println(anyChar(s));
        System.out.println(anyChar(s));
    }
}
