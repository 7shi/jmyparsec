package jmyparsec;

@FunctionalInterface
public interface Parser<T> {

    T parse(Source s) throws Exception;

    default Parser<T> or(Parser<T> p) {
        return s -> {
            T ret;
            Source bak = s.clone();
            try {
                ret = parse(s);
            } catch (Exception e) {
                if (!s.equals(bak)) {
                    throw e;
                }
                ret = p.parse(s);
            }
            return ret;
        };
    }

    default Parser<T> left(String e) {
        return or(s -> {
            throw new Exception(s.ex(e));
        });
    }
}
