package JParsec;

import java.util.function.Function;

public class Parsers {

    public static <T> void parseTest(Parser<T> p, String src) {
        Source s = new Source(src);
        try {
            System.out.println(p.parse(s));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static final Parser<Character> anyChar = satisfy(s -> true);  // 変更

    public static final Parser<Character> satisfy(Function<Character, Boolean> f) {
        return s -> {
            char ch = s.peek();
            if (!f.apply(ch)) {
                throw new Exception("not satisfy");
            }
            s.next();
            return ch;
        };
    }

    public static final Parser<Character> char1(char ch) {
        return satisfy(c -> c == ch);
    }

    public static final boolean isAlphaNum(char ch) {
        return Character.isAlphabetic(ch) || Character.isDigit(ch);
    }

    public static final Parser<Character> digit = satisfy(Character::isDigit);
    public static final Parser<Character> upper = satisfy(Character::isUpperCase);
    public static final Parser<Character> lower = satisfy(Character::isLowerCase);
    public static final Parser<Character> alpha = satisfy(Character::isAlphabetic);
    public static final Parser<Character> alphaNum = satisfy(Parsers::isAlphaNum);
    public static final Parser<Character> letter = satisfy(Character::isLetter);
}
