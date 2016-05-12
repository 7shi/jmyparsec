package jparsec;

public class Source {

    private final String s;
    private int pos;

    public Source(String s) {
        this.s = s;
    }

    public final char peek() throws Exception {  // 例外対応
        if (pos >= s.length()) {
            throw new Exception("too short");
        }
        return s.charAt(pos);
    }

    public final void next() {
        ++pos;
    }
}
