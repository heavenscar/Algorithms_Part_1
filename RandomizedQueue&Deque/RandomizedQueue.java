import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
	private Item[] a;
	private int N = 0;
	
	private void resize(int max) {// move the queue
		Item[] temp = (Item[]) new Object[max];
		for (int i = 0; i < N; i++)
			temp[i] = a[i];
		a = temp;
	}

	public RandomizedQueue() { // construct an empty randomized queue
		a = (Item[]) new Object[2];
		a[0] = null;
		a[1] = null;
	}
	
	public boolean isEmpty() { // is the queue empty?
		return N == 0;
	}
	
	public int size() { // return the number of items on the queue
		return N;
	}
	
	public void enqueue(Item item) { // add the item
		if (item == null) throw new NullPointerException();
		if (N == a.length) resize(2*a.length);
		a[N++] = item;
	}
	
	public Item dequeue() { // delete and return a random item
		if (isEmpty()) throw new NoSuchElementException("Queue underflow");
		int radindex = StdRandom.uniform(N);
		Item item = a[radindex];
		a[radindex] = a[N-1];
		a[N-1] = null;
		N--;
		return item;
	}
	
	public Item sample() { // return (but do not delete) a random item
		if (isEmpty()) throw new NoSuchElementException("Queue underflow");
		int radindex = StdRandom.uniform(N);
		return a[radindex];
	}
	
	@Override
	public Iterator<Item> iterator() { 
		// return an independent iterator over items in random order
		// TODO Auto-generated method stub
		return new ListIterator();
	}
	
	private class ListIterator implements Iterator<Item> {
		private int iter_count = N;
		public boolean hasNext()
		{	return iter_count != 0;	}
		public void remove() {
			throw new UnsupportedOperationException();
		}
		public Item next() {
			if (!hasNext()) throw new NoSuchElementException();
			int radindex = StdRandom.uniform(iter_count);
			Item item = a[radindex];
			a[radindex] = a[iter_count--];
			return item;
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RandomizedQueue<String> q = new RandomizedQueue<String>();
		while(!StdIn.isEmpty()){
			String item = StdIn.readString();
            if (!item.equals("-") && !item.equals("exit")) q.enqueue(item);
            else if (item.equals("exit")) break;
            else if (!q.isEmpty()) StdOut.print(q.dequeue() + " ");
		}
		StdOut.println("(" + q.size() + " left on queue)");
	}
}
