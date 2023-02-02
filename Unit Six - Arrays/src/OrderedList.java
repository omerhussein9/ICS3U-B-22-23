public class OrderedList {
    private int[] data;
    private int manyItems;

    private final int DEFAULT_CAPACITY = 10;

    public OrderedList() {
        this.data = new int[DEFAULT_CAPACITY];
        this.manyItems = 0;
    }

    public OrderedList(int capacity) {
        if(capacity < 1)
            throw new IllegalArgumentException("Capacity must be at least 1");

        this.data = new int[capacity];
        this.manyItems = 0;
    }

    public void add(int val) {
        if(manyItems == data.length) {
            updateCapacity(data.length * 2);
        }

        data[manyItems] = val;
        manyItems++;
    }

    public void add(int index, int val) {
        if(index > manyItems || index < 0)
            throw new IndexOutOfBoundsException("Index " + index + " is not allowed.");

        if(manyItems == data.length) {
            updateCapacity(data.length * 2);
        }

        for (int i = manyItems; i > index ; i--) {
            data[i] = data[i - 1];
        }

        data[index] = val;
        manyItems++;
    }

    public int remove(int index) {
        if(index >= manyItems || index < 0)
            throw new IndexOutOfBoundsException("Index " + index + " is not allowed.");

        int temp = data[index];
        for (int i = index; i <= manyItems; i++) {
            data[i] = data[i + 1];
        }

        manyItems--;
        return temp;
    }

    // get the element at the specified index
    public int get(int index) {
        if(index >= manyItems || index < 0)
            throw new IndexOutOfBoundsException("Index " + index + " is not allowed.");

        return data[index];
    }

    // returns the integer that was replaced
    public int set(int index, int val) {
        if(index >= manyItems || index < 0)
            throw new IndexOutOfBoundsException("Index " + index + " is not allowed.");
        
        int temp = data[index];
        data[index] = val;
        return temp;
    }

    private void updateCapacity(int capacity) {
        int[] temp = new int[capacity];

        for (int i = 0; i < data.length; i++) {
            temp[i] = data[i];
        }

        data = temp;
    }

    public int size() {
        return manyItems;
    }
}
