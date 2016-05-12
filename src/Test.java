
import java.util.List;
import jmyparsec.*;
import static jmyparsec.Parsers.*;

public class Test {

    static final Parser<Integer> number = s -> {
        return Integer.parseInt(many1(digit).parseToString(s));
    };

    static final Parser<Integer> expr = s -> {
        int x = number.parse(s);
        List<Integer> xs = many(or(
                char1('+').next(    number ),
                char1('-').next(neg(number))  // 適用
        )).parse(s);
        return xs.stream().reduce(x, Integer::sum);
    };

    public static void main(String[] args) {
        parseTest(number, "123"  );
        parseTest(expr  , "1+2"  );
        parseTest(expr  , "123"  );
        parseTest(expr  , "1+2+3");
        parseTest(expr  , "1-2-3");  // OK
        parseTest(expr  , "1-2+3");  // OK
    }
}
