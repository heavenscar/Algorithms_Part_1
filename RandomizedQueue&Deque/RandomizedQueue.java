import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
	private Item[] a;
	private int N = 0;
	
	public RandomizedQueue() { // construct an empty randomized queue
		a = (Item[]) new Object[1];
	}
	
	private void resize(int max) {// move the queue
		Item[] temp = (Item[]) new Object[max];
		for (int i = 0; i < N; i++)
			temp[i] = a[i];
		a = temp;
	}

	public boolean isEmpty() { // is the queue empty?
		return N == 0;
	}
	
	public int size() { // return the number of items on the queue
		return N;
	}
	
	public void enqueue(Item item) { // add the item
		if (item == null) 
			throw new NullPointerException();
		
		if (N == a.length) resize(2*a.length);
		a[N++] = item;
	}
	
	public Item dequeue() { // delete and return a random item
		if (isEmpty()) 
			throw new NoSuchElementException("Queue underflow");
		
		int radindex = StdRandom.uniform(N);
		Item item = a[radindex];
		a[radindex] = a[--N];
		a[N] = null;
		if (N > 0 && N == a.length/4) resize(a.length/2);
		
		return item;
	}
	
	public Item sample() { // return (but do not delete) a random item
		if (isEmpty()) 
			throw new NoSuchElementException("Queue underflow");
		
		int radindex = StdRandom.uniform(N);
		return a[radindex];
	}
	
	@Override
	public Iterator<Item> iterator() { 
		// return an independent iterator over items in random order
		// TODO Auto-generated method stub
		return new RandomizedQueueIterator();
	}
	
	private class RandomizedQueueIterator implements Iterator<Item> {
		private int current = 0;
		private int[] shuffledIndexes = new int[N];
		
		public boolean hasNext() {
			if (current == 0) {
				for (int i = 0; i < N; i++)
					shuffledIndexes[i] = i;
				StdRandom.shuffle(shuffledIndexes);
			}
			return current < N;
		}
		
		public void remove() {
 			throw new UnsupportedOperationException("Not supported");
 		}
			
 		public Item next() {
			if (current == 0) {
				for (int i = 0; i < N; i++)
					shuffledIndexes[i] = i;
				StdRandom.shuffle(shuffledIndexes);
			}
			if (current >= N || size() == 0)  
				throw new NoSuchElementException();
			return a[shuffledIndexes[current++]];
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
