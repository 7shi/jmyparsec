
import java.util.List;
import jmyparsec.*;
import static jmyparsec.Parsers.*;

public class Test {

    static final Parser<Integer> number = s -> {
        return Integer.parseInt(many1(digit).parseToString(s));
    };

    static final Parser<Integer> expr = s -> {  // 戻り値の型 -> Integer
        int x = number.parse(s);
        List<Integer> xs = many(char1('+').next(number)).parse(s);
        return xs.stream().reduce(x, Integer::sum);  // 合計
    };

    public static void main(String[] args) {
        parseTest(number, "123"  );
        parseTest(expr  , "1+2"  );  // OK
        parseTest(expr  , "123"  );  // OK
        parseTest(expr  , "1+2+3");  // OK
    }
}
