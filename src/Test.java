
import java.util.List;
import jmyparsec.*;
import static jmyparsec.Parsers.*;

public class Test {

    static final Parser<Integer> number = s -> {
        return Integer.parseInt(many1(digit).parseToString(s));
    };

    static final Parser<List<Integer>> expr = s -> {
        int x = number.parse(s);
        List<Integer> xs = many(ss -> {
            char1('+').parse(ss);
            return number.parse(ss);
        }).parse(s);
        xs.add(0, x);  // 連結
        return xs;
    };

    public static void main(String[] args) {
        parseTest(number, "123"  );
        parseTest(expr  , "1+2"  );  // '+'が1個
        parseTest(expr  , "123"  );  // '+'が0個
        parseTest(expr  , "1+2+3");  // '+'が2個
    }
}
