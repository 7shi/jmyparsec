package jmyparsec;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Parsers {

    public static final <T> void parseTest(Parser<T> p, String src) {
        Source s = new Source(src);
        try {
            System.out.println(p.parseToString(s));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static final Parser<Character> anyChar = satisfy(s -> true);

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
        return satisfy(c -> c == ch).left("not char '" + ch + "'");
    }

    public static final boolean isAlphaNum(char ch) {
        return Character.isAlphabetic(ch) || Character.isDigit(ch);
    }

    public static final Parser<Character> digit    = satisfy(Character::isDigit     ).left("not digit"   );
    public static final Parser<Character> upper    = satisfy(Character::isUpperCase ).left("not upper"   );
    public static final Parser<Character> lower    = satisfy(Character::isLowerCase ).left("not lower"   );
    public static final Parser<Character> alpha    = satisfy(Character::isAlphabetic).left("not alpha"   );
    public static final Parser<Character> alphaNum = satisfy(Parsers  ::isAlphaNum  ).left("not alphaNum");
    public static final Parser<Character> letter   = satisfy(Character::isLetter    ).left("not letter"  );

    public static final Parser<String> sequence(Parser... args) {
        return s -> {
            StringBuilder sb = new StringBuilder();
            for (Parser arg : args) {
                sb.append(arg.parse(s));
            }
            return sb.toString();
        };
    }

    public static final <T> Parser<List<T>> replicate(int n, Parser<T> p) {
        return s -> {
            List<T> list = new ArrayList<>();
            for (int i = 0; i < n; ++i) {
                list.add(p.parse(s));
            }
            return list;
        };
    }

    public static final <T> Parser<List<T>> many(Parser<T> p) {
        return s -> {
            List<T> list = new ArrayList<>();
            try {
                for (;;) {
                    list.add(p.parse(s));
                }
            } catch (Exception e) {
            }
            return list;
        };
    }

    public static final <T> Parser<List<T>> many1(Parser<T> p) {
        return s -> {
            T first = p.parse(s);
            List<T> ret = many(p).parse(s);
            ret.add(0, first);
            return ret;
        };
    }

    public static final <T> Parser<T> or(Parser<T> p1, Parser<T> p2) {
        return p1.or(p2);
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
                char1(str.charAt(i)).left("not string \"" + str + "\"").parse(s);
            }
            return str;
        };
    }

    public static final <T> Parser<T> left(String e) {
        return s -> {
            throw new Exception(s.ex(e));
        };
    }

    public static final <T1, T2> Parser<T1> apply(Function<T2, T1> f, Parser<T2> p) {
        return s -> f.apply(p.parse(s));
    }
    
    public static final Parser<Integer> neg(Parser<Integer> p) {
        return apply(x -> -x, p);
    }
}
