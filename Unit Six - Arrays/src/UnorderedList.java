public class UnorderedList {
    private int[] data;
    private int manyItems;

    private final int DEFAULT_CAPACITY = 10;

    public UnorderedList() {
        this.data = new int[DEFAULT_CAPACITY];
        this.manyItems = 0;
    }

    public UnorderedList(int capacity) {
        if(capacity < 1)
            throw new IllegalArgumentException("Capacity must be at least 1");

        this.data = new int[capacity];
        this.manyItems = 0;
    }
}