
import java.util.List;
import java.util.function.Function;
import jmyparsec.*;
import static jmyparsec.Parsers.*;

public class Test {

    static final Function<List<Character>, Integer> toInt
            = s -> Integer.parseInt(Parser.toString(s));

    static final Parser<Integer> number = apply(toInt, many1(digit));

    static final <T> T accumulate(T x, List<Function<T, T>> fs) {
        for (Function<T, T> f : fs) {
            x = f.apply(x);
        }
        return x;
    }

    static final Parser<Integer> term = s -> {
        int x = number.parse(s);
        Parser<List<Function<Integer, Integer>>> fs = many(or(
            char1('*').next(apply((y, z) -> z * y, number)),  // 修正
            char1('/').next(apply((y, z) -> z / y, number))   // 修正
        ));
        return accumulate(x, fs.parse(s));
    };

    static final Parser<Integer> expr = s -> {
        int x = term.parse(s);           // 項を取得
        Parser<List<Function<Integer, Integer>>> fs = many(or(
            char1('+').next(apply((y, z) -> z + y, term)),    // 修正
            char1('-').next(apply((y, z) -> z - y, term))     // 修正
        ));
        return accumulate(x, fs.parse(s));
    };

    public static void main(String[] args) {
        parseTest(number, "123"     );
        parseTest(expr  , "1+2"     );
        parseTest(expr  , "123"     );
        parseTest(expr  , "1+2+3"   );
        parseTest(expr  , "1-2-3"   );
        parseTest(expr  , "1-2+3"   );
        parseTest(expr  , "2*3+4"   );
        parseTest(expr  , "2+3*4"   );
        parseTest(expr  , "100/10/2");
    }
}
