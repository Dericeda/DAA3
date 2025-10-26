import java.util.*;

/**
 * Implementation of Prim's algorithm for finding Minimum Spanning Tree
 */
public class PrimAlgorithm {
    
    /**
     * Find MST using Prim's algorithm
     * @param graph Input graph
     * @return MSTResult containing MST edges and statistics
     */
    public static MSTResult findMST(Graph graph) {
        long startTime = System.nanoTime();
        int operationsCount = 0;
        
        List<String> nodes = graph.getNodes();
        Map<String, List<Edge>> adjacencyList = graph.getAdjacencyList();
        
        if (nodes.isEmpty() || !graph.isConnected()) {
            return new MSTResult(new ArrayList<>(), 0, 0, 0);
        }
        
        List<Edge> mstEdges = new ArrayList<>();
        Set<String> inMST = new HashSet<>();
        PriorityQueue<Edge> minHeap = new PriorityQueue<>();
        
        // Start from the first node
        String startNode = nodes.get(0);
        inMST.add(startNode);
        operationsCount++; // Add operation
        
        // Add all edges from start node to the heap
        for (Edge edge : adjacencyList.get(startNode)) {
            minHeap.offer(edge);
            operationsCount++; // Heap insertion
        }
        
        // Process until we have V-1 edges in MST
        while (!minHeap.isEmpty() && mstEdges.size() < nodes.size() - 1) {
            Edge minEdge = minHeap.poll();
            operationsCount++; // Heap extraction
            
            String from = minEdge.getFrom();
            String to = minEdge.getTo();
            
            // Check if this edge creates a cycle
            operationsCount++; // Set lookup
            if (inMST.contains(to)) {
                continue; // Skip this edge
            }
            
            // Add edge to MST
            mstEdges.add(minEdge);
            inMST.add(to);
            operationsCount += 2; // List add + Set add
            
            // Add all edges from the newly added vertex
            for (Edge edge : adjacencyList.get(to)) {
                operationsCount++; // Set lookup
                if (!inMST.contains(edge.getTo())) {
                    minHeap.offer(edge);
                    operationsCount++; // Heap insertion
                }
            }
        }
        
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
