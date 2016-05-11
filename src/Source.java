
public class Source {

    private String s;

    public Source(String s) {
        this.s = s;
    }

    public final char peek() {
        return s.charAt(0);
    }

    public final void next() {
        s = s.substring(1);
    }
}
