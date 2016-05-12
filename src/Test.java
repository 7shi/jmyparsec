
import static jmyparsec.Parsers.*;

public class Test {

    public static void main(String[] args) {
        parseTest(sequence(letter, digit), "a1");
        parseTest(letter.prev(digit), "a1");
        parseTest(letter.next(digit), "a1");
    }
}
