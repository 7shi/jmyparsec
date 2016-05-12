
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

    static final Parser<Integer> expr = s -> {
        int x = number.parse(s);
        Parser<List<Function<Integer, Integer>>> fs = many(or(
            ss -> {
                char1('+').parse(ss);
                int y = number.parse(ss);   // キャプチャされる変数
                return z -> z + y;          // クロージャを返す
            }, ss -> {
                char1('-').parse(ss);
                int y = number.parse(ss);   // キャプチャされる変数
                return z -> z - y;          // クロージャを返す
            }, ss -> {                      // 追加: 掛け算
                char1('*').parse(ss);
                int y = number.parse(ss);   // キャプチャされる変数
                return z -> z * y;          // クロージャを返す
            }, ss -> {                      // 追加: 割り算
                char1('/').parse(ss);
                int y = number.parse(ss);   // キャプチャされる変数
                return z -> z / y;          // クロージャを返す
            }
        ));
        return accumulate(x, fs.parse(s));  // まとめて計算
    };

    public static void main(String[] args) {
        parseTest(number, "123"     );
        parseTest(expr  , "1+2"     );
        parseTest(expr  , "123"     );
        parseTest(expr  , "1+2+3"   );
        parseTest(expr  , "1-2-3"   );
        parseTest(expr  , "1-2+3"   );
        parseTest(expr  , "2*3+4"   );  // OK
        parseTest(expr  , "2+3*4"   );  // NG
        parseTest(expr  , "100/10/2");  // OK
    }
}
