import java.util.*;

/**
 * Union-Find (Disjoint Set Union) data structure with path compression and union by rank
 */
public class UnionFind {
    private Map<String, String> parent;
    private Map<String, Integer> rank;
    private int operationsCount;

    public UnionFind(List<String> nodes) {
        parent = new HashMap<>();
        rank = new HashMap<>();
        operationsCount = 0;
        
        for (String node : nodes) {
            parent.put(node, node);
            rank.put(node, 0);
            operationsCount += 2; // Two map insertions
        }
    }

    /**
     * Find the root of the set containing the given node (with path compression)
     */
    public String find(String node) {
        operationsCount++; // Map lookup
        if (!parent.get(node).equals(node)) {
            operationsCount++; // Comparison
            parent.put(node, find(parent.get(node))); // Path compression
            operationsCount++; // Map update
        }
        return parent.get(node);
    }

    /**
     * Union two sets containing the given nodes (by rank)
     */
    public boolean union(String node1, String node2) {
        String root1 = find(node1);
        String root2 = find(node2);
        
        operationsCount++; // Comparison
        if (root1.equals(root2)) {
            return false; // Already in the same set
        }
        
        // Union by rank
        int rank1 = rank.get(root1);
        int rank2 = rank.get(root2);
        operationsCount += 3; // Two map lookups + comparison
        
        if (rank1 < rank2) {
            parent.put(root1, root2);
            operationsCount++; // Map update
        } else if (rank1 > rank2) {
            parent.put(root2, root1);
            operationsCount++; // Map update
        } else {
            parent.put(root2, root1);
            rank.put(root1, rank1 + 1);
            operationsCount += 2; // Two map updates
        }
        
        return true;
    }

    public int getOperationsCount() {
        return operationsCount;
    }
}
