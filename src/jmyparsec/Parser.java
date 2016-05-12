package jmyparsec;

@FunctionalInterface
public interface Parser<T> {

    T parse(Source s) throws Exception;  // 例外対応
}
