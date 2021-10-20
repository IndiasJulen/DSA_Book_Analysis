package maps;

public class LexItemCount {
    private String lexItem;
    private int count;

    public LexItemCount() {
        this.lexItem = "FFF";
        this.count = 0;
    }

    public void initializeLexItem(String lema, int kop) {
        this.lexItem = lema;
        this.count = kop;
    }

    public int getCount() {
        return this.count;
    }

    public String getLexItem() {
        return this.lexItem;
    }

    @Override
    public String toString() {
        return (this.lexItem + ": " + this.count);
    }
}
