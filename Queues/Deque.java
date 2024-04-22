/* *****************************************************************************
 *  Name:               Kaveh Hashemi
 *  Date:               Today
 *  Description:        Deque
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private Item[] s;
    private int first = -1;
    private int last = -1;
    private int addFirst = 0;
    private int addLast = 1;

    // construct an empty deque
    public Deque() {
        s = (Item[]) new Object[2];
    }

    // is the deque empty?
    public boolean isEmpty() {
        return (addFirst + 1 == addLast);
    }

    // return the number of items on the deque
    public int size() {
        return addLast - addFirst - 1;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Null entered");
        }
        else {
            if (addFirst < 0) {
                doubleSize(s.length * 2);
            }
            s[addFirst] = item;
            first = addFirst;
            addFirst--;
        }
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (last == -1 && first == -1 || size() == 0) {
            throw new NoSuchElementException("Nothing to remove!");
        }
        if (addLast - addFirst - 1 == s.length / 4) {
            quarterSize(s.length / 4);
        }
        if (first != -1) {
            Item a = s[first];
            s[first] = null;
            addFirst = first;
            first++;
            return a;
        }
        else if (last != -1) {
            Item a = s[addFirst + 1];
            s[addFirst + 1] = null;
            addFirst++;
            first = addFirst + 1;
            return a;
        }
        return null;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Null entered");
        }
        else {
            if (addLast > s.length - 1) {
                doubleSize(s.length * 2);
            }
            s[addLast] = item;
            last = addLast;
            addLast++;
        }
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (last == -1 && first == -1) {
            throw new NoSuchElementException("Nothing to remove!");
        }
        // else {
        if (addLast - addFirst - 1 == s.length / 4) {
            quarterSize(s.length / 4);
        }
        if (last != -1) {
            Item a = s[last];
            s[last] = null;
            addLast = last;
            last--;
            return a;
        }
        else if (first != -1) {
            Item a = s[addLast - 1];
            s[addLast - 1] = null;
            addLast--;
            last = addLast - 1;
            return a;
        }
        // }
        return null;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new ArrayIterator();
    }

    private class ArrayIterator implements Iterator<Item> {
        private int i = first;

        public boolean hasNext() {
            return i != last;
        }

        public void remove() {
            throw new UnsupportedOperationException("No");
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Nothing's there!");
            }
            else return s[++i];
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<String> dq = new Deque<String>();
        int k = 1;
        while (!StdIn.isEmpty()) {
            String value = StdIn.readString();
            // if (k % 2 == 0) {
            //     if (value.equals("-")) dq.removeLast();
            //     else dq.addLast(value);
            // }
            // else {
            //     if (value.equals("-")) dq.removeFirst();
            //     else dq.addFirst(value);
            // }
            if (value.equals("-")) dq.removeFirst();
            else dq.addFirst(value);
            k++;
        }
        StdOut.println("size: " + dq.size());
        StdOut.println("addfirst: " + dq.addFirst);
        StdOut.println("first: " + dq.first);
        StdOut.println("last: " + dq.last);
        StdOut.println("addlast: " + dq.addLast);
        if (dq.last == -2) StdOut.println("first: " + dq.removeLast());
    }


    /// private parts :)
    private void doubleSize(int size) {
        Item[] newArray = (Item[]) new Object[size];

        for (int i = addFirst + 1; i < addLast; i++) {
            newArray[i + (size / 4)] = s[i];
        }
        s = newArray;

        addFirst = addFirst + (size / 4);
        if (first != -1) first = first + (size / 4);
        if (last != -1) last = last + (size / 4);
        addLast = addLast + (size / 4);
    }

    private void quarterSize(int size) {
        Item[] newArray = (Item[]) new Object[size];
        int idx = 0;
        for (int i = addFirst + 1; i < addLast; i++) {
            newArray[idx++] = s[i];
        }
        s = newArray;

        addFirst = -1;
        first = 0;
        last = s.length - 1;
        addLast = s.length;
    }


}
