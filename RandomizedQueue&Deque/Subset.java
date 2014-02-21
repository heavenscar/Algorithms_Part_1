import java.util.Iterator;

public class Subset {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final int k = Integer.parseInt(args[0]);
		RandomizedQueue<String> q = new RandomizedQueue<String>();
		
		while (!StdIn.isEmpty()) {
			String sInput = StdIn.readString();
			q.enqueue(sInput);
		}
		
		int i = 0;
		for (Iterator<String> iter = q.iterator(); iter.hasNext() && i < k; i++)
		{	StdOut.println(iter.next()); }
	}

}
