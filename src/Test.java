
import jmyparsec.*;
import static jmyparsec.Parsers.*;

public class Test {

    static final Parser<Integer> number = s -> {  // 修正
        return Integer.parseInt(many1(digit).parse(s));
    };

    public static void main(String[] args) {
        parseTest(number, "123");
    }
}
