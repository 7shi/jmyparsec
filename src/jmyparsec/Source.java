package jmyparsec;

public class Source {

    private final String s;
    private int pos, line, col;

    public Source(String s) {
        this.s = s;
        line = col = 1;
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
            throw new Exception(ex("too short"));
        }
        return s.charAt(pos);
    }

    public final void next() throws Exception {
        char ch = peek();
        if (ch == '\n') {
            ++line;
            col = 0;
        }
        ++pos;
        ++col;
    }

    public final void revert(Source src) throws Exception {
        if (!s.equals(src.s)) {
            throw new Exception(ex("can not revert"));
        }
        pos = src.pos;
    }

    public final String ex(String e) {
        String ret = "[line " + line + ",col " + col + "] " + e;
        if (s != null && 0 <= pos && pos < s.length()) {
            ret += ": '" + s.charAt(pos) + "'";
        }
        return ret;
    }
}
