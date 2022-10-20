public class RingBuffer {
    private double[] rb;   // items in the buffer
    private int first;     // index for the next dequeue or peek
    private int last;      // index for the next enqueue
    private int size;      // number of items in the buffer

    // Creates an empty ring buffer with the specified capacity.
    public RingBuffer(int capacity) {
        rb = new double[capacity];
        size = 0;
        first = 0;
        last = 0;
    }

    // Helper debugger method
    private void printRB() {
        System.out.println("capacity = " + capacity());
        System.out.println("first = " + first);
        System.out.println("last = " + last);
        System.out.println("size = " + size + ".");
    }

    // Returns the capacity of this ring buffer.
    public int capacity() {
        return rb.length;
    }

    // Returns the number of items currently in this ring buffer.
    public int size() {
        return size;
    }

    // Is this ring buffer empty (size equals zero)?
    public boolean isEmpty() {
        return size == 0;
    }

    // Is this ring buffer full (size equals capacity)?
    public boolean isFull() {
        return size == capacity();
    }

    // Adds item x to the end of this ring buffer.
    public void enqueue(double x) {
        if (isFull()) { // check if ring buffer is full
            throw new RuntimeException("RingBuffer is full!");
        }
        rb[last] = x; // if not full, enqueue
        size++;
        last++;
        if (last == capacity()) { // wrap around
            last = 0;
        }


        // printRB();

    }

    // Deletes and returns the item at the front of this ring buffer.
    public double dequeue() {
        if (isEmpty()) { // check if buffer is empty
            throw new RuntimeException("RingBuffer is empty!");
        }
        double x = rb[first];
        size--;
        first++;
        if (first == capacity()) {
            first = 0;
        }


        // printRB();
        return x;
    }

    // Returns the item at the front of this ring buffer.
    public double peek() {
        if (isEmpty()) {
            throw new RuntimeException("Ring Buffer Empty!");
        }
        else {
            return rb[first];
        }
    }

    // Tests this class by directly calling all instance methods.
    public static void main(String[] args) {
        // RingBuffer r = new RingBuffer(4);
        // r.enqueue(.2);
        // r.enqueue(.5);
        // double value = r.dequeue();
        // System.out.println(value);
        // value = r.dequeue();
        // System.out.println(value);
        // r.enqueue(.4);
        // r.enqueue(.1);
        // r.enqueue(.7);
        int n = Integer.parseInt(args[0]);

        RingBuffer buffer = new RingBuffer(n);
        for (int i = 1; i <= n; i++) {
            buffer.enqueue(i);
        }
        double t = buffer.dequeue();
        buffer.enqueue(t);
        StdOut.println("Size after wrap-around is " + buffer.size());

        while (buffer.size() >= 2) {
            double x = buffer.dequeue();
            double y = buffer.dequeue();
            buffer.enqueue(x + y);
        }

        // for (int i = 0; i < n; i++) {
        //     System.out.println(RingBuffer.rb[i]);
        // }
        StdOut.println(buffer.peek());
    }
}
