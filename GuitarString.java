public class GuitarString {
    private int n; // Equally Spaced Points in Time
    RingBuffer r; // RingBuffer

    // Creates a guitar string of the specified frequency,
    // using a sampling rate of 44,100.
    public GuitarString(double frequency) {
        n = (int) (Math.ceil(44100 / frequency));
        r = new RingBuffer(n);
        for (int i = 0; i < n; i++) {
            r.enqueue(0);
        }
    }

    // Creates a guitar string whose length and initial values
    // are given by the specified array.
    public GuitarString(double[] init) {
        n = init.length;
        r = new RingBuffer(n);
        for (int i = 0; i < n; i++) {
            r.enqueue(init[i]);
        }
    }

    // Returns the number of samples in the ring buffer.
    public int length() {
        return r.size();
    }

    // Plucks this guitar string by replacing the ring buffer with white noise.
    public void pluck() {
        for (int i = 0; i < n; i++) {
            r.dequeue();
            r.enqueue(StdRandom.uniform(-0.5, 0.5));
        }
    }

    // Advances the Karplus-Strong simulation one time step.
    public void tic() {
        r.enqueue(0.996 * (r.dequeue() + r.peek()) / 2);

    }

    // Returns the current sample.
    public double sample() {
        return r.peek();
    }

    // Tests this class by directly calling both constructors
    // and all instance methods.
    public static void main(String[] args) {
        double[] samples = { 0.2, 0.4, 0.5, 0.3, -0.2, 0.4, 0.3, 0.0, -0.1, -0.3 };
        GuitarString testString = new GuitarString(samples);

        int m = 25; // number of tics
        for (int i = 0; i < m; i++) {
            double sample = testString.sample();
            StdOut.printf("%6d %8.4f\n", i, sample);
            testString.tic();
        }
    }
}
