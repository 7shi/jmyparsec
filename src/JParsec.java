
public class JParsec {

    public static char anyChar(String[] s) {
        char ret = s[0].charAt(0);
        s[0] = s[0].substring(1);
        return ret;
    }

    public static void main(String[] args) {
        String[] s = {"abc"};
        System.out.println(anyChar(s));
        System.out.println(anyChar(s));
    }
}
