package jmyparsec;

import java.util.function.Function;

public class Parsers {

    public static void parseTest(Parser p, String src) {
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
                throw new Exception(s.ex("not satisfy"));
            }
            s.next();
            return ch;
        };
    }

    public static final Parser<Character> char1(char ch) {
        return or(satisfy(c -> c == ch), left("not char '" + ch + "'"));
    }

    public static final boolean isAlphaNum(char ch) {
        return Character.isAlphabetic(ch) || Character.isDigit(ch);
    }

    public static final Parser<Character> digit    = or(satisfy(Character::isDigit)     , left("not digit"   ));
    public static final Parser<Character> upper    = or(satisfy(Character::isUpperCase) , left("not upper"   ));
    public static final Parser<Character> lower    = or(satisfy(Character::isLowerCase) , left("not lower"   ));
    public static final Parser<Character> alpha    = or(satisfy(Character::isAlphabetic), left("not alpha"   ));
    public static final Parser<Character> alphaNum = or(satisfy(Parsers  ::isAlphaNum)  , left("not alphaNum"));
    public static final Parser<Character> letter   = or(satisfy(Character::isLetter)    , left("not letter"  ));

    public static final Parser<String> sequence(Parser... args) {
        return s -> {
            StringBuilder sb = new StringBuilder();
            for (Parser arg : args) {
                sb.append(arg.parse(s));
            }
            return sb.toString();
        };
    }

    public static final Parser<String> replicate(int n, Parser p) {
        return s -> {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < n; ++i) {
                sb.append(p.parse(s));
            }
            return sb.toString();
        };
    }

    public static final Parser<String> many(Parser p) {
        return s -> {
            StringBuilder sb = new StringBuilder();
            try {
                for (;;) {
                    sb.append(p.parse(s));
                }
            } catch (Exception e) {
            }
            return sb.toString();
        };
    }

    public static final <T> Parser<T> or(Parser<T> p1, Parser<T> p2) {
        return s -> {
            T ret;
            Source bak = s.clone();    // 追加
            try {
                ret = p1.parse(s);
            } catch (Exception e) {
                if (!s.equals(bak)) {  // 追加
                    throw e;
                }
                ret = p2.parse(s);
            }
            return ret;
        };
    }

    public static final <T> Parser<T> tryp(Parser<T> p) {
        return s -> {
            T ret;
            Source bak = s.clone();
            try {
                ret = p.parse(s);
            } catch (Exception e) {
                s.revert(bak);
                throw e;
            }
            return ret;
        };
    }

    public static final Parser<String> string(String str) {
        return s -> {
            for (int i = 0; i < str.length(); ++i) {
                or(char1(str.charAt(i)), left("not string \"" + str + "\"")).parse(s);
            }
            return str;
        };
    }

    public static final <T> Parser<T> left(String e) {
        return s -> {
            throw new Exception(s.ex(e));
        };
    }
}
