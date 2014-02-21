import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
	
	private Node<Item> first;
	private Node<Item> last;
	private int N = 0; // N stands for numbers of elements in the deque.
	
	public Deque() { // construct an empty deque
		first = new Node<Item>();
		first.item = null;
		first.next = null;
		first.previous = null;
		last = new Node<Item>();
		last.item = null;
		last.next = null;
		last.previous = null;
		N = 0;
	}
	
	private static class Node<Item> {
		Item item;
		Node<Item> previous;
		Node<Item> next;
	}
	
	public boolean isEmpty() { // is the deque empty?
		return this.N == 0;
	}
	
	public int size() { // return the number of items on the deque
		return this.N;
	}
	
	public void addFirst(Item item) { // insert the item at the front
		if (item == null) throw new NullPointerException();
		Node<Item> oldfirst = first;
		first = new Node<Item>();
		first.item = item;
		first.next = oldfirst;
		first.previous = null;
		oldfirst.previous = first;
		if (isEmpty())	last = first;
		N++;
	}

	public void addLast(Item item) { // insert the item at the end
		if (item == null) throw new NullPointerException();
		Node<Item> oldlast = last;
		last = new Node<Item>();
		last.item = item;
		last.previous = oldlast;
		oldlast.next = last;
		last.next = null;
		if (isEmpty())	first = last;
		N++;
	}
	
	public Item removeFirst() { // delete and return the item at the front
		if (isEmpty()) throw new NoSuchElementException("Queue underflow");
		Item item = first.item;
		first = first.next;
		first.previous = null;
		N--;
		return item;
	}
	
	public Item removeLast() { // delete and return the item at the end
		if (isEmpty()) throw new NoSuchElementException("Queue underflow");
		Item item = last.item;
		last = last.previous;
		last.next = null;
		N--;
		return item;
	}
	
	public Iterator<Item> iterator() { //return an iterator over items in order from front to end
		return new ListIterator();
	}
	
	private class ListIterator implements Iterator<Item> {
		private Node<Item> current = first;
		public boolean hasNext()
		{	return current !=null;	}
		public void remove() {
			throw new UnsupportedOperationException();
		}
		public Item next() {
			if (!hasNext()) throw new NoSuchElementException();
			Item item = current.item;
			current = current.next;
			return item;
		}
	}
	
	public static void main(String[] args) { // unit testing
		// TODO Auto-generated method stub
		Deque<String> deq = new Deque<String>();
		while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-")) deq.addFirst(item);
            else if (!deq.isEmpty()) StdOut.print(deq.removeFirst() + " ");
        }
        StdOut.println("(" + deq.size() + " left on queue)");
        String lastitem = "lasttest";
        deq.addLast(lastitem);
        deq.removeLast();
        deq.removeLast();
        StdOut.println("(Now " + deq.size() + " left on queue)");
	}

}
