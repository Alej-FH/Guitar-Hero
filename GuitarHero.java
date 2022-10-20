public class GuitarHero {

    public static void main(String[] args) {

        // create two guitar strings, for concert A and concert C
        // double CONCERT_A = 440.0;
        // double CONCERT_C = CONCERT_A * Math.pow(2, 3.0 / 12.0);
        // GuitarString stringA = new GuitarString(CONCERT_A);
        // GuitarString stringC = new GuitarString(CONCERT_C);

        String keyboardString = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";

        // Generalize to n strings;
        GuitarString[] strings = new GuitarString[keyboardString.length()];
        for (int i = 0; i < keyboardString.length(); i++) {
            strings[i] = new GuitarString(440 * Math.pow(2, (i - 24) / 12.0));
        }

        // the main input loop
        Keyboard keyboard = new Keyboard();
        while (true) {

            // check if the user has played a key; if so, process it
            if (keyboard.hasNextKeyPlayed()) {

                // the key the user played
                char key = keyboard.nextKeyPlayed();

                // pluck the corresponding string
                int index = keyboardString.indexOf(key);
                if (index == -1) {
                }
                else {
                    strings[index].pluck();
                }

            }

            // compute the superposition of the generalized samples
            double sampleG = 0;
            for (int i = 0; i < keyboardString.length(); i++) {
                sampleG += strings[i].sample();
            }

            // play the sample on standard audio
            StdAudio.play(sampleG);

            for (int i = 0; i < keyboardString.length(); i++) {
                strings[i].tic();
            }

        }
    }

}
