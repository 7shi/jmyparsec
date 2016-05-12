
import java.util.List;
import jmyparsec.*;
import static jmyparsec.Parsers.*;

public class Test {

    static final Parser<Integer> number = s -> {
        return Integer.parseInt(many1(digit).parseToString(s));
    };

    static final Parser<List<Integer>> expr = s -> {
        int x = number.parse(s);
        List<Integer> xs = many(char1('+').next(number)).parse(s);  // 簡略化
        xs.add(0, x);
        return xs;
    };

    public static void main(String[] args) {
        parseTest(number, "123"  );
        parseTest(expr  , "1+2"  );
        parseTest(expr  , "123"  );
        parseTest(expr  , "1+2+3");
    }
}
