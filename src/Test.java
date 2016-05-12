
import jmyparsec.*;
import static jmyparsec.Parsers.*;

public class Test {

    static final Parser<String> number = many1(digit);

    public static void main(String[] args) {
        parseTest(number, "123");
    }
}
