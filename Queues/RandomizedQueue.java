/* *****************************************************************************
 *  Name:               Kaveh Hashemi
 *  Date:               Today
 *  Description:        RandomizedQueue
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] items;
    private Deque<Integer> removed;
    private int add = 0;

    // construct an empty randomized queue
    public RandomizedQueue() {
        items = (Item[]) new Object[1];
        removed = new Deque<Integer>();
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return (items.length == 0 || removed.size() == items.length);
    }

    // return the number of items on the randomized queue
    public int size() {
        if (isEmpty()) return 0;
        else return (add - removed.size());
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException("Null");
        if (removed.size() == 0) {
            if (add == items.length) {
                doubleSize(items.length * 2);
            }
            items[add] = item;
            add++;
        }
        else {
            items[removed.removeFirst()] = item;
        }
    }

    // return a random number
    /* private int randomGenerator() {
        int random = StdRandom.uniformInt(add);
        if (items[random] == null) {
            return randomGenerator();
        }
        else {
            return random;
        }
    }*/

    private int randomGenerator() {
        int random = StdRandom.uniformInt(add);
        boolean isValid = items[random] != null;
        while (!isValid) {
            random = StdRandom.uniformInt(add);
            isValid = items[random] != null;
        }
        return random;
    }

    // remove and return a random item
    public Item dequeue() {
        if (size() == 0) throw new NoSuchElementException("queue is empty");
        if (size() > 0) {
            int random = randomGenerator();
            Item k = items[random];
            removed.addLast(random);
            items[random] = null;
            if (removed.size() == 3 * items.length / 4) quarterSize(items.length / 4);
            return k;
        }
        else throw new NoSuchElementException("There's nothing to remove");
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (size() == 0) throw new NoSuchElementException("queue is empty");
        int random = randomGenerator();
        Item k = items[random];
        return k;
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new ArrayIterator();
    }

    private class ArrayIterator implements Iterator<Item> {
        private int i = add;

        public boolean hasNext() {
            return (i != size() - 1);
        }

        public void remove() {
            throw new UnsupportedOperationException("No");
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException("Nothing's there!");
            Item temp = items[i++];
            // [INFO] RandomizedQueue.java:113: Using a loop in this method might be a performance bug. [Performance]
            while (temp == null) {
                temp = items[i++];
            }
            return temp;
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<String> rq = new RandomizedQueue<String>();
        while (!StdIn.isEmpty()) {
            String value = StdIn.readString();
            if (value.equals("-")) {
                StdOut.println(rq.dequeue());
            }
            else rq.enqueue(value);
        }
        StdOut.println("size: " + rq.size());
        StdOut.println("removed: " + rq.removed.size());
    }

    // private parts :)
    private void doubleSize(int size) {
        Item[] newArray = (Item[]) new Object[size];

        for (int i = 0; i < add; i++) {
            newArray[i] = items[i];
        }
        items = newArray;
    }

    private void quarterSize(int size) {
        Item[] newArray = (Item[]) new Object[size];

        for (int i = 0; i < add; i++) {
            if (items[i] != null) {
                newArray[i] = items[i];
            }
        }
        items = newArray;
        add = items.length;
        removed = new Deque<>();
    }
}
