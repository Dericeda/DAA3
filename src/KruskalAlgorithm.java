import java.util.*;

/**
 * Implementation of Kruskal's algorithm for finding Minimum Spanning Tree
 */
public class KruskalAlgorithm {
    
    /**
     * Find MST using Kruskal's algorithm
     * @param graph Input graph
     * @return MSTResult containing MST edges and statistics
     */
    public static MSTResult findMST(Graph graph) {
        long startTime = System.nanoTime();
        int operationsCount = 0;
        
        List<String> nodes = graph.getNodes();
        List<Edge> edges = graph.getEdges();
        
        if (nodes.isEmpty() || !graph.isConnected()) {
            return new MSTResult(new ArrayList<>(), 0, 0, 0);
        }
        
        List<Edge> mstEdges = new ArrayList<>();
        
        // Sort edges by weight
        List<Edge> sortedEdges = new ArrayList<>(edges);
        Collections.sort(sortedEdges);
        operationsCount += edges.size() * (int)(Math.log(edges.size()) / Math.log(2)); // Sort complexity
        
        // Initialize Union-Find
        UnionFind uf = new UnionFind(nodes);
        
        // Process edges in sorted order
        for (Edge edge : sortedEdges) {
            operationsCount++; // Iteration
            
            String from = edge.getFrom();
            String to = edge.getTo();
            
            // Check if adding this edge creates a cycle
            if (uf.union(from, to)) {
                mstEdges.add(edge);
                operationsCount++; // List add
                
                // Stop when we have V-1 edges
                if (mstEdges.size() == nodes.size() - 1) {
                    break;
                }
            }
        }
        
        // Add Union-Find operations to total count
        operationsCount += uf.getOperationsCount();
        
        // Calculate total cost
        int totalCost = 0;
        for (Edge edge : mstEdges) {
            totalCost += edge.getWeight();
            operationsCount++; // Addition operation
        }
        
        long endTime = System.nanoTime();
        double executionTimeMs = (endTime - startTime) / 1_000_000.0;
        
        return new MSTResult(mstEdges, totalCost, operationsCount, executionTimeMs);
    }
}
