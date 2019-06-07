/*
 * NAME: Zhaoyi Guo
 * PID: A15180402
 */
import java.util.*;

/*
 * class that implements graph for minimum spanning tre
 */
public class Graph {
    // define a data structure to store all the vertices with fast access
    private ArrayList<Edge> allUndirectedEdges;
    private ArrayList<Edge> resultMST;
    private DisjointSet ds;
    private boolean edgesGiven;
    static int powerFactor = 2;
    static double powerFactor1 = 1.0/2.0;
    private HashMap<Vertex, List<Edge>> myEdgeList;
    private HashMap<String, Vertex> myVertex;

    /**
     * Constructor for Graph
     */
    public Graph(boolean edgesGiven) {
        // initialize allUndirectedEdges, resultMST, myEdgeList, and myVertex
        allUndirectedEdges = new ArrayList<Edge>();
        resultMST = new ArrayList<Edge>();
        myEdgeList = new HashMap<Vertex, List<Edge>>();
        myVertex = new HashMap<String, Vertex>();
        this.edgesGiven = edgesGiven;
        ds = new DisjointSet();
    }

    /**
     * get all the edges from the myEdgeList
     * @return Collection<List<Edge>>
     */
    public ArrayList<Edge> getEdges() {
        // get all the directed edges from hashmap myEdgeList
        ArrayList<Edge> result = new ArrayList<>();
        for (Vertex v: getVertices()) {
            for (Edge e: myEdgeList.get(v))
                result.add(e);
        }
        return result;
    }

    /**
     * getter to get all the edges in the graph
     * @return
     */
    public ArrayList<Edge> getAllUndirectedEdges() {
        // get the all undirected edges
        return allUndirectedEdges;
    }

    /**
     * getter for vertex
     * return: ArrayList<Vertex> result
     */
    public ArrayList getVertexes() {
        // create a new array list, and add vertex to that array list
        // using for loop
        ArrayList<Vertex> result = new ArrayList<>();
        for (Vertex vertex: myEdgeList.keySet()) {
            result.add(vertex);
        }
        return result;
    }

    /**
     * Gets the vertex object with the given name
     *
     * @param name name of the vertex object requested
     * @return vertex object associated with the name
     */
    public Vertex getVertex(String name) {
        // get the vertex by string, using myVertex
        return myVertex.get(name);
    }

    /**
     * Adds a vertex to the graph. Throws IllegalArgumentException if given vertex
     * already exist in the graph.
     *
     * @param v vertex to be added to the graph
     * @throws IllegalArgumentException if two vertices with the same name are added.
     */
    public void addVertex(Vertex v) throws IllegalArgumentException {
        // if v is already exist, throw exception
        if (myEdgeList != null && myEdgeList.containsKey(v))
            throw new IllegalArgumentException();
        // other wise, add the v to both myEdgeList and myVertex
        myEdgeList.put(v, new ArrayList<>());
        myVertex.put(v.getName(), v);
    }

    /**
     * Gets a collection of all the vertices in the graph
     *
     * @return collection of all the vertices in the graph
     */
    public Collection<Vertex> getVertices() {
        // gets a collection of all the vertices in the graph
        return myEdgeList.keySet();
    }

    /**
     * Adds a directed edge from vertex u to vertex v, Throws IllegalArgumentException if one of
     * the vertex does not exist. If edgesGiven is false, directly return at first.
     *
     * @param nameU name of vertex u
     * @param nameV name of vertex v
     * @param weight weight of the edge between vertex u and v
     * @throws IllegalArgumentException if one of the vertex does not exist
     */
    public void addEdge(String nameU, String nameV, Double weight) throws IllegalArgumentException {
        
        // if edgesGiven return false don't do anything
        if (!edgesGiven)
            return;
        // add a new edge from u to v if myVertex contains both nameU and nameV
        if (!myVertex.containsKey(nameU) || !myVertex.containsKey(nameV)
                || nameU.equals(nameV)) {
            throw new IllegalArgumentException();
        }
        Vertex u = myVertex.get(nameU);
        Vertex v = myVertex.get(nameV);
        // create edges from both ways
        Edge newEdgeU = new Edge(u, v, weight);
        myEdgeList.get(u).add(newEdgeU);

    }

    /**
     * Adds an undirected edge between vertex u and vertex v by adding a directed
     * edge from u to v, then a directed edge from v to u. Then updates the allUndirectedEdges.
     * If edgesGiven is false, directly return at first.
     *
     * @param nameU name of vertex u
     * @param nameV name of vertex v
     * @param weight  weight of the edge between vertex u and v
     */
    public void addUndirectedEdge(String nameU, String nameV, double weight) {
        // call addEdge twice to get u to v direction edge
        // and v to u direction edge
        addEdge(nameU, nameV, weight);
        addEdge(nameV, nameU, weight);
        // add one edge from u to v to allUndirectedEdges
        Edge e = new Edge(myVertex.get(nameU), myVertex.get(nameV), weight);
        allUndirectedEdges.add(e);

    }

    /**
     * Calculates the euclidean distance for all edges in the graph and all edges in 
     * allUndirectedEdges. If edgesGiven is false, directly return at first.
     */
    public void computeAllEuclideanDistances() {
        
        // if edgesGiven is false, return immediately
        if (!edgesGiven)
            return;
        // compute all the distances, and update it to the edges from myEdgeList
        for (Edge edge: allUndirectedEdges) {
                double distance = Math.pow((Math.pow((edge.getSource().getX() -
                        edge.getTarget().getX()), powerFactor) +
                        Math.pow((edge.getSource().getY() -
                                edge.getTarget().getY()),
                                powerFactor)), powerFactor1);
                // set the distance
                edge.setDistance(distance);
            }

    }


    /**
     * Populate all possible edges from all vertices in the graph. Only works when edgesGiven 
     * is false. If edgesGiven is true, directly return at first.
     */
    public void populateAllEdges() {
        boolean add = true;
        if (edgesGiven)
            return;
        // 1. initialize a list of undirected edges and go through all directed edges
        for (Edge edge: getEdges()) {
            // 2. before adding each directed edge to the list of undirected edges,
            // check if an directed edge that is opposite of the current directed edge was already added
            for (Edge e: allUndirectedEdges) {
                // 3. to check if an opposite edge already exists,
                // loop through all edges e in your list of undirected edges,
                // and check if e's source is equal to the current edge's dest
                // and if e's dest is equal to the current edge's source
                if (e.getSource() == edge.getTarget() && e.getTarget() == edge.getSource())
                    add = false;
                if (e.getSource() == edge.getSource() && e.getTarget() == edge.getTarget())
                    add = false;
            }
            if (add)
                allUndirectedEdges.add(edge);
        }
    }

    /**
     * method that implements kruskal algorithm
     */
    public ArrayList<Edge> runKruskalsAlg() {
        // if resultMST is already computed, return the resultMST at first
        if (resultMST.size() == myVertex.size() - 1)
            return resultMST;

        // sort the allUndirectedEdges
        Collections.sort(allUndirectedEdges, Comparator.comparingDouble(e -> e.getDistance()));
        for (Edge edge: allUndirectedEdges) {
            // if resultMST is already computed, return the resultMST at first
            if (resultMST.size() == myVertex.size() - 1)
                return resultMST;
            Vertex v1 = edge.getSource();
            Vertex v2 = edge.getTarget();
            Vertex root1 = ds.find(v1);
            Vertex root2 = ds.find(v2);
            // if v1 and v2 share the same parent, don't do anything, and iterate next
            if (root1 == root2)
                continue;
            else {
                // union v1 and v2
                ds.union(v1, v2);
                resultMST.add(edge);
            }
        }
        return resultMST;
    }
}
