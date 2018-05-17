public class DataItem { //Data class
    private int key;
    private long value;

    public DataItem(int k, long v) {
        key = k;
        value = v;
    }

    public int getKey(){
        return key;
    }

    public long getValue(){
        return value;
    }
}
