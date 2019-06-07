/*
 * NAME: Zhaoyi Guo
 * PID: A15180402
 */


/**
 * class that implements disjoint set
 */
public class DisjointSet {
    public int size[];

    /**
     * Returns the “sentinel node” representing the set that this
     * vertex belongs to. You must apply path compression here to optimize the runtime.
     * @param v
     * @return
     */
    public Vertex find(Vertex v) {
        
        // get the parent of the current vertex
        Vertex parent = v.getParent();
        while(parent.getParent() != parent)
        {
            //chase parent of current element until it reaches root
            parent = parent.getParent();
        }
        v.setParent(parent);
        return parent;
    }

    /**
     * Merges the two sets that two given vertices belongs to.
     * If two given vertices belongs to the same set, return.
     * Otherwise, apply union by size: make the
     * “sentinel node” of smaller set as child of “sentinel node” of larger set.
     *
     * (The union operation here can be done using either union by height
     * or union by size. The choice here is up to you,
     * but we suggest using union by size here as it is slightly easier to implement. )
     *
     * @param v1
     * @param v2
     */
    public void union(Vertex v1, Vertex v2) {

        // get the root of vertex v1 and v2
        Vertex root_A = find(v1);
        Vertex root_B = find(v2);
        // if v1 and v2 have the same root return
        if (root_A == root_B) {
            return;
        }
        // otherwise put one as the other's child
        // while implement the parents' size
        if(root_A.getSize() < root_B.getSize())
        {
            root_A.setParent(root_B);
            root_B.setSize(root_B.getSize() + root_A.getSize()) ;
        }
        else
        {
            root_B.setParent(root_A);
            root_A.setSize(root_B.getSize() + root_A.getSize()) ;
        }

    }
}
