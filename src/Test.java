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
    
    static final <T> Parser<T> eval(Parser<T> m, Parser<List<Function<T, T>>> fs) {
        return s -> {
            T x = m.parse(s);
            return accumulate(x, fs.parse(s));
        };
    }

    // ダミーのラッパー
    static final Parser<Integer> factor = new Parser<Integer>() {
        @Override
        public Integer parse(Source s) throws Exception {
            return factor_.parse(s);
        }
    };

    // term = factor, {("*", factor) | ("/", factor)}
    static final Parser<Integer> term = Test.<Integer>eval(factor, many(or(
            char1('*').next(apply((y, z) -> z * y, factor)),
            char1('/').next(apply((y, z) -> z / y, factor))
    )));

    // expr = term, {("+", term) | ("-", term)}
    static final Parser<Integer> expr = Test.<Integer>eval(term, many(or(
            char1('+').next(apply((y, z) -> z + y, term)),
            char1('-').next(apply((y, z) -> z - y, term))
    )));

    // factor = factor = [spaces], ("(", expr, ")") | number, [spaces]
    static final Parser<Integer> factor_ = spaces.next(
            or(char1('(').next(expr).prev(char1(')')), number)
    ).prev(spaces);

    public static void main(String[] args) {
        parseTest(number, "123"          );
        parseTest(expr  , "1 + 2"        );
        parseTest(expr  , "123"          );
        parseTest(expr  , "1 + 2 + 3"    );
        parseTest(expr  , "1 - 2 - 3"    );
        parseTest(expr  , "1 - 2 + 3"    );
        parseTest(expr  , "2 * 3 + 4"    );
        parseTest(expr  , "2 + 3 * 4"    );
        parseTest(expr  , "100 / 10 / 2" );
        parseTest(expr  , "( 2 + 3 ) * 4");
    }
}
