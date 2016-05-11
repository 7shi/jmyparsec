
import static JParsec.Parsers.*;

public class Test {

    public static void main(String[] args) {
        parseTest(char1('a'), "abc");
        parseTest(char1('a'), "123");  // NG
    }
}
