
import java.util.List;
import java.util.function.Function;  // 追加
import jmyparsec.*;
import static jmyparsec.Parsers.*;

public class Test {

    static final Function<List<Character>, Integer> toInt              // 追加
            = s -> Integer.parseInt(Parser.toString(s));

    static final Parser<Integer> number = apply(toInt, many1(digit));  // 適用

    static final Parser<Integer> expr = s -> {
        int x = number.parse(s);
        List<Integer> xs = many(or(
                char1('+').next(    number ),
                char1('-').next(neg(number))
        )).parse(s);
        return xs.stream().reduce(x, Integer::sum);
    };

    public static void main(String[] args) {
        parseTest(number, "123"  );
        parseTest(expr  , "1+2"  );
        parseTest(expr  , "123"  );
        parseTest(expr  , "1+2+3");
        parseTest(expr  , "1-2-3");
        parseTest(expr  , "1-2+3");
    }
}
