/*
 * NAME: Zhaoyi Guo
 * PID: A15180402
 */


/**
 * class that implements Vertex
 */
public class Vertex {

    private String name; // the name of this vertex
    private int x; // the x coordinates of this vertex on map
    private int y; // the y coordinates of this vertex on map




    // TODO: add additional instance variables to work with Disjoint Set and represent the graph
    private Vertex parent; // parent keep track of the root
    private int size; // size keep track the number of children

    /**
     * initializes name, x, y, parent, and size
     * @param name
     * @param x
     * @param y
     */
    public Vertex(String name, int x, int y) {
        // initializes name, x, y, parent, size
        this.name = name;
        this.x = x;
        this.y = y;


        // TODO: initialize your new instance variables
        this.parent = this;
        this.size = 1;
    }



    public String getName() {
        return name;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    // TODO: add getters and setters for your new instance variables

    /**
     * method to get parent
     * @return parent
     */
    public Vertex getParent() {
        return parent;
    }

    /**
     * method to set parent
     * @param v
     */
    public void setParent(Vertex v) {
        parent = v;
    }

    /**
     * method to get size
     * @return
     */
    public int getSize() {
        return size;
    }

    /**
     * method to set size
     * @param s
     */
    public void setSize(int s) {
        size = s;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (!(o instanceof Vertex)) {
            return false;
        }
        Vertex oVertex = (Vertex) o;

        return name.equals(oVertex.name) && x == oVertex.x && y == oVertex.y;
    }

    @Override
    public int hashCode() {
        // we assume that each vertex has a unique name
        return name.hashCode();
    }

    public String toString() {
        return name + " (" + x + ", " + y + ")";
    }

}