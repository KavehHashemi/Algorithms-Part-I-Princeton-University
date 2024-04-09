/* *****************************************************************************
 *  Name:               Kaveh Hashemi
 *  Date:               Today
 *  Description:        RandomizedQueue
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] items;
    private Deque<Integer> removed;
    private int add = 0;

    // construct an empty randomized queue
    public RandomizedQueue() {
        items = (Item[]) new Object[1];
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return false;
    }

    // return the number of items on the randomized queue
    public int size() {
        return 0;
    }

    // add the item
    public void enqueue(Item item) {
        if (removed.size() == 0) {
            items[add] = item;
        }
        else {
            items[removed.removeFirst()] = item;
        }
    }

    // return a random number
    private int randomGenerator() {
        int random = StdRandom.uniformInt(add);
        if (items[random] == null) {
            randomGenerator();
            return -1;
        }
        else return random;
    }

    // remove and return a random item
    public Item dequeue() {
        int random = randomGenerator();
        Item k = items[random];
        removed.addLast(random);
        items[random] = null;
        return k;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        int random = randomGenerator();
        Item k = items[random];
        return k;
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new ArrayIterator();
    }

    private class ArrayIterator implements Iterator<Item> {
        private int i = 0;

        public boolean hasNext() {
            return false;
        }

        public void remove() {
            throw new UnsupportedOperationException("No");
        }

        public Item next() {
            return null;
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
    }
}
