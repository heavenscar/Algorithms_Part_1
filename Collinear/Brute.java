/* Brute is the straightforward Method to find all line contained more than 4 given points
 * The time complexity should be O(N^4)
 * 
 * Author: Andong Wang
 * Date: 2014/2/28
 */

public class Brute {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        StdDraw.show();
  
        // read in the input
        String file = args[0];
        In in = new In(file);
        int N = in.readInt();
  
        // Initialize input point
        Point[] points = new Point[N];
  
        for (int i = 0; i < N; i++) {
            int x = in.readInt();
            int y = in.readInt();
            Point p = new Point(x, y);
            p.draw();
            points[i] = p;
        }
  
        // brute force is always the first choice
        Quick.sort(points);    // sort points by coordinates
        for (int p = 0; p < N - 3; p++) {
            for (int q = p + 1; q < N - 2; q++) {
                for (int r = q + 1; r < N - 1; r++) {
                    for (int s = r + 1; s < N; s++) {
                        if (points[p].slopeTo(points[q]) == points[p].slopeTo(points[r]) &&
                            points[p].slopeTo(points[r]) == points[p].slopeTo(points[s])) {
                            StdOut.println(points[p].toString() + " -> " + points[q].toString()
                                               + " -> " + points[r].toString() + " -> "
                                               + points[s].toString());
                            points[p].drawTo(points[s]);
                        }
                    }
                }
            }
        }
        
        // display
        StdDraw.show(0);
    }

}
