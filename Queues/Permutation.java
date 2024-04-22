/* *****************************************************************************
 *  Name:           Kaveh Hashemi
 *  Date:           Today
 *  Description:    Permutation
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {
    private RandomizedQueue<String> stringSequence;

    private Permutation() {
        stringSequence = new RandomizedQueue<String>();
    }

    private void read() {
        while (!StdIn.isEmpty()) {
            stringSequence.enqueue(StdIn.readString());
        }
    }

    private void write(int number) {
        for (int i = 0; i < number; i++) {
            StdOut.println(stringSequence.dequeue());
        }
    }

    public static void main(String[] args) {
        int number = Integer.parseInt(args[0]);
        Permutation perm = new Permutation();
        perm.read();
        perm.write(number);
    }
}
