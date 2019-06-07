/*
 * NAME: Zhaoyi Guo
 * PID: A15180402
 */
import static org.junit.Assert.*;

/**
 * class that tests disjoint set
 */
public class DisjointSetTester {
    DisjointSet test1;
    Vertex v1;
    Vertex v2;
    Vertex v3;
    Vertex v4;
    @org.junit.Before
    public void setUp() throws Exception {
        test1 = new DisjointSet();
        v1 = new Vertex("test1", 5, 7);
        v2 = new Vertex("test2", 5, 7);
        v3 = new Vertex("test3", 5, 7);
        v4 = new Vertex("test4", 5, 7);
    }

    @org.junit.Test
    public void find() {
        test1.union(v1, v2);
        assertEquals(v1, test1.find(v2));
        assertEquals(2, test1.find(v2).getSize());
        test1.union(v1, v3);
        assertEquals(v1, test1.find(v3));
        assertEquals(3, test1.find(v3).getSize());
        test1.union(v2, v3);
        assertEquals(3, test1.find(v3).getSize());
    }

    @org.junit.Test
    public void union() {
        test1.union(v1, v2);
        assertEquals(v1, test1.find(v2));
        assertEquals(2, test1.find(v2).getSize());
        test1.union(v3, v4);
        assertEquals(v3, test1.find(v4));
        assertEquals(2, test1.find(v4).getSize());
        test1.union(v1, v3);
        assertEquals(v1, test1.find(v4));
        assertEquals(v1, test1.find(v3));
        assertEquals(v1, test1.find(v2));
        assertEquals(4, test1.find(v4).getSize());
    }
}