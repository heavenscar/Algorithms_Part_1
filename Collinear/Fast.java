/* Fast is the quick Method to find all line contained more than 4 given points
 * The time complexity should be O(N^2logN)
 * 
 * Author: Andong Wang
 * Date: 2014/2/28
 * This version implemented required situation without redundancy examination
 */
import java.util.Arrays;

public class Fast {

    public static void main(String[] args) {
        // TODO Auto-generated method stub    
        Stopwatch t = new Stopwatch();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        StdDraw.show();
        
        // Read in input as the brute method
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
        
        // The fast method is meant to find lines by sort slope order for each points
        Quick.sort(points);
        int count = 1;  // a count for how many points are in the same line
        int oldline = 0;
        for (int p = 0; p < N - 3; p++) {
            Quick.sort(points);
            Arrays.sort(points, p+1, N, points[p].SLOPE_ORDER);
            for (int q = p + 1; q < N - 1; q++) {
                if (points[p].slopeTo(points[q]) == points[p].slopeTo(points[q+1])) {
                    count++;
                    if (q+1 == N-1) {
                        if (count >= 3){
                            for (int r = 0; r < p; r++) {
                                if (points[r].slopeTo(points[q]) == points[p].slopeTo(points[q+1]))
                                    oldline = 1;
                            }
                            if (oldline == 0) {
                                StdOut.print(points[p].toString());
                                for (int i = count; i > 0; i--) {
                                    StdOut.print(" -> " + points[q-i+2].toString());
                                }
                                points[p].drawTo(points[q+1]);
                                StdOut.println();
                            }
                        }
                        count = 1;
                        oldline = 0;
                    }
                }
                else {
                    if (count >= 3) {
                            for (int r = 0; r < p; r++) {
                                if (points[r].slopeTo(points[q]) == points[p].slopeTo(points[q]))
                                    oldline = 1;
                            }
                            if (oldline == 0) {
                                StdOut.print(points[p].toString());
                                for (int i = count; i > 0; i--) {
                                    StdOut.print(" -> " + points[q-i+1].toString());
                                }
                                points[p].drawTo(points[q]);
                                StdOut.println();
                            }
                        }
                    count = 1;
                    oldline = 0;
                }
            }
        }          
        // display
        StdDraw.show(0);  
        StdOut.println(t.elapsedTime());
    }

}