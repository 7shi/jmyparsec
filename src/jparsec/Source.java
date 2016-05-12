package jparsec;

public class Source {

    private final String s;
    private int pos;

    public Source(String s) {
        this.s = s;
    }

    @Override
    public Source clone() {
        Source ret = new Source(s);
        ret.pos = pos;
        return ret;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Source)) {
            return false;
        }
        Source src = (Source) obj;
        return s.equals(src.s) && pos == src.pos;
    }

    public final char peek() throws Exception {
        if (pos >= s.length()) {
            throw new Exception("too short");
        }
        return s.charAt(pos);
    }

    public final void next() {
        ++pos;
    }
}
