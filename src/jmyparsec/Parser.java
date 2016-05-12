package jmyparsec;

import java.lang.reflect.Array;
import java.util.List;

@FunctionalInterface
public interface Parser<T> {

    T parse(Source s) throws Exception;

    default String parseToString(Source s) throws Exception {
        return toString(parse(s));
    }

    static boolean isCharOrString(Object obj) {
        int len = Array.getLength(obj);
        for (int i = 0; i < len; ++i) {
            Object o = Array.get(obj, i);
            if (!(o instanceof Character || o instanceof String)) {
                return false;
            }
        }
        return true;
    }

    static String toString(Object obj) {
        if (obj.getClass().isArray()) {
            int len = Array.getLength(obj);
            StringBuilder sb = new StringBuilder();
            if (isCharOrString(obj)) {
                for (int i = 0; i < len; ++i) {
                    sb.append(Array.get(obj, i));
                }
            } else {
                for (int i = 0; i < len; ++i) {
                    sb.append(i == 0 ? "[" : ",");
                    sb.append(toString(Array.get(obj, i)));
                }
                sb.append("]");
            }
            return sb.toString();
        } else if (obj instanceof List) {
            return toString(((List) obj).toArray());
        }
        return obj.toString();
    }

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
