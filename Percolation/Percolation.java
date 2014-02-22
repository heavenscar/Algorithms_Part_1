public class Percolation {
    private int size = 0;
    private WeightedQuickUnionUF sites;
    private WeightedQuickUnionUF checksites;
    private boolean[] open;
    
    public Percolation(int N) {
        // create N-by-N grid, with all sites blocked
        size = N;
        sites = new WeightedQuickUnionUF(N*N+2);
        checksites = new WeightedQuickUnionUF(N*N+2);
        open = new boolean[N*N+2];
        for (int i = 1; i < N*N+1; i++){
            open[i] = false;
        }
        open[0] = true;
        open[N*N+1] = true;
    }
 
    public void open(int i, int j) {
        // open site (row i, column j) if it is not already
        if (i <= 0 || i > this.size || j <= 0 || j > this.size) throw new IndexOutOfBoundsException();
        int name = size * (i - 1) + j;
        if (!open[name])
            open[name] = true; //open the site(i, j) if it is not open
        // Now union sites depending on site's location.
        if (name <= size) { // first row
            sites.union(0, name); // union entry and open-site of first row
            checksites.union(0, name);
            if (name == 1) { // corner 1 with 2 neighbours
                if (open[2]) {
                    sites.union(1, 2);
                    checksites.union(1, 2);
                }
                if (open[size + 1]) {
                    sites.union(1, size + 1);
                    checksites.union(1, size+1);
                }
            }
            else if (name == size) { // corner 2 with 2 neighbours
                if (open[size - 1]) {
                    sites.union(size, size-1);
                    checksites.union(size, size-1);
                }
                if (open[size*2]) {
                    sites.union(size, size*2);
                    checksites.union(size, size*2);
                }
            }
            else { // row 1 has 3 neighbour
                if (open[name-1]) {
                    sites.union(name, name-1);
                    checksites.union(name, name-1);
                }
                if (open[name+1]) {
                    sites.union(name, name+1);
                    checksites.union(name, name+1);
                }
                if (open[name+size]) {
                    sites.union(name, name+size);
                    checksites.union(name, name+size);
                }
            }
        }
        else if (name > size*(size-1)) { // last row
            sites.union(name, size*size+1); // union exit and open-site of last row
            if (name % size == 1) { // corner 3 with 2 neighbours
                if (open[name-size]) {
                    sites.union(name, name-size);
                    checksites.union(name, name-size);
                }
                if (open[name+1]) {
                    sites.union(name, name+1);
                    checksites.union(name, name+1);
                }
            }
            else if (name % size == 0) { // corner 4 with 2 neighbors
                if (open[name-1]) {
                    sites.union(name, name-1);
                    checksites.union(name, name-1);
                }
                if (open[name-size]) {
                    sites.union(name, name-size);
                    checksites.union(name, name-size);
                }
            }
            else { // last row has 3 neighbors
                if (open[name-1]) {
                    sites.union(name, name-1);
                    checksites.union(name, name-1);
                }
                if (open[name+1]) {
                    sites.union(name, name+1);
                    checksites.union(name, name+1);
                }
                if (open[name-size]) {
                    sites.union(name, name-size);
                    checksites.union(name, name-size);
                }
            }
        }
        else {
            if (name % size == 1) { // column 1 has 3 neighbours
                if (open[name+1]) {
                    sites.union(name, name+1);
                    checksites.union(name, name+1);
                }
                if (open[name+size]) {
                    sites.union(name, name+size);
                    checksites.union(name, name+size);
                }
                if (open[name-size]) {
                    sites.union(name, name-size);
                    checksites.union(name, name-size);
                }
            }
            else if (name % size == 0) { // last column has 3 neighbours
                if (open[name-1]) {
                    sites.union(name, name-1);
                    checksites.union(name, name-1);
                }
                if (open[name+size]) {
                    sites.union(name, name+size);
                    checksites.union(name, name+size);
                }
                if (open[name-size]) {
                    sites.union(name, name-size);
                    checksites.union(name, name-size);
                }
            }
            else { // normal sites has 4 neighbours
                if (open[name-1]) {
                    sites.union(name, name-1);
                    checksites.union(name, name-1);
                }
                if (open[name+1]) {
                    sites.union(name, name+1);
                    checksites.union(name, name+1);
                }
                if (open[name+size]) {
                    sites.union(name, name+size);
                    checksites.union(name, name+size);
                }
                if (open[name-size]) {
                    sites.union(name, name-size);
                    checksites.union(name, name-size);
                }
            }
        }
    }
 
    public boolean isOpen(int i, int j) {
        // is site (row i, column j) open?
        if (i <= 0 || i > this.size || j <= 0 || j > this.size) throw new IndexOutOfBoundsException();
        int name = this.size*(i-1)+j;
        if (this.open[name])
            return true;
        else
            return false;
    }
 
    public boolean isFull(int i, int j) {
        // is site (row i, column j) full?
        if (i <= 0 || i > this.size || j <= 0 || j > this.size) throw new IndexOutOfBoundsException();
        int name = this.size*(i-1)+j;
        if (checksites.connected(0, name))
            return true;
        else
            return false;
    }
 
    public boolean percolates() {
        // does the system percolate?
        if (sites.connected(0, this.size*this.size+1))
            return true;
        else
            return false;
    }

    public static void main(String[] args) {
        int N = 6;
        int a = 1;
        int b = 1;
        Percolation p = new Percolation(N);
        p.open(a, b);
        StdOut.println(p.isFull(a, b));
        p.open(3, 5);
        StdOut.println(p.isFull(3, 5));
        p.open(2, 4);
        StdOut.println(p.isFull(2, 4));
        p.open(3, 4);
        StdOut.println(p.isFull(3, 4));
        p.open(1, 4);
        StdOut.println(p.isFull(1, 4));
        StdOut.println(p.isFull(2, 4));
        StdOut.println(p.isFull(3, 4));
    }
}

