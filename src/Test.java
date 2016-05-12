
import jmyparsec.*;
import static jmyparsec.Parsers.*;

public class Test {

    public static void main(String[] args) {
        parseTest(                            letter , "a");
        parseTest(apply(ch -> (char)(ch + 1), letter), "a");
    }
}
