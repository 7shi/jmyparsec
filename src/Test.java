
import java.util.Arrays;
import java.util.List;
import jmyparsec.*;
import static jmyparsec.Parsers.*;

public class Test {

    static final Parser<Integer> number = s -> {
        return Integer.parseInt(many1(digit).parse(s));
    };

    static final Parser<List<Integer>> expr = s -> {  // 追加
        int x = number.parse(s);
        char1('+').parse(s);
        int y = number.parse(s);
        return Arrays.asList(x, y);
    };

    public static void main(String[] args) {
        parseTest(number, "123");
        parseTest(expr  , "1+2");  // 追加
    }
}
