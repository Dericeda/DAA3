import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

/**
 * Unit tests for MST algorithms using JUnit 5
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MSTTest {
    
    @BeforeAll
    public static void setUpClass() {
        System.out.println("=".repeat(60));
        System.out.println("Running MST Algorithm Tests with JUnit 5");
        System.out.println("=".repeat(60));
    }
    
    @AfterAll
    public static void tearDownClass() {
        System.out.println("=".repeat(60));
        System.out.println("All tests completed");
        System.out.println("=".repeat(60));
    }
    
    @Test
    @Order(1)
    @DisplayName("Test 1: Both algorithms produce same total cost")
    public void test1_SameTotalCost() {
        List<String> nodes = Arrays.asList("A", "B", "C", "D");
        List<Edge> edges = Arrays.asList(
            new Edge("A", "B", 1),
            new Edge("A", "C", 4),
            new Edge("B", "C", 2),
            new Edge("C", "D", 3),
            new Edge("B", "D", 5)
        );
        Graph graph = new Graph(1, nodes, edges);
        
        MSTResult primResult = PrimAlgorithm.findMST(graph);
        MSTResult kruskalResult = KruskalAlgorithm.findMST(graph);
        
        assertEquals(primResult.getTotalCost(), kruskalResult.getTotalCost(),
            "Both algorithms should produce the same MST cost");
    }
    
    @Test
    @Order(2)
    @DisplayName("Test 2: MST has V-1 edges")
    public void test2_MSTEdgeCount() {
        List<String> nodes = Arrays.asList("A", "B", "C", "D", "E");
        List<Edge> edges = Arrays.asList(
            new Edge("A", "B", 4),
            new Edge("A", "C", 3),
            new Edge("B", "C", 2),
            new Edge("B", "D", 5),
            new Edge("C", "D", 7),
            new Edge("C", "E", 8),
            new Edge("D", "E", 6)
        );
        Graph graph = new Graph(1, nodes, edges);
        
        MSTResult primResult = PrimAlgorithm.findMST(graph);
        MSTResult kruskalResult = KruskalAlgorithm.findMST(graph);
        
        int expectedEdges = nodes.size() - 1;
        assertEquals(expectedEdges, primResult.getMstEdges().size(),
            "Prim's MST should have V-1 edges");
        assertEquals(expectedEdges, kruskalResult.getMstEdges().size(),
            "Kruskal's MST should have V-1 edges");
    }
    
    @Test
    @Order(3)
    @DisplayName("Test 3: MST is acyclic")
    public void test3_MSTAcyclic() {
        List<String> nodes = Arrays.asList("A", "B", "C", "D");
        List<Edge> edges = Arrays.asList(
            new Edge("A", "B", 1),
            new Edge("B", "C", 2),
            new Edge("C", "D", 3),
            new Edge("D", "A", 4)
        );
        Graph graph = new Graph(1, nodes, edges);
        
        MSTResult primResult = PrimAlgorithm.findMST(graph);
        MSTResult kruskalResult = KruskalAlgorithm.findMST(graph);
        
        assertTrue(isAcyclic(primResult.getMstEdges(), nodes),
            "Prim's MST should be acyclic");
        assertTrue(isAcyclic(kruskalResult.getMstEdges(), nodes),
            "Kruskal's MST should be acyclic");
    }
    
    @Test
    @Order(4)
    @DisplayName("Test 4: MST connects all vertices")
    public void test4_MSTConnectivity() {
        List<String> nodes = Arrays.asList("A", "B", "C", "D", "E");
        List<Edge> edges = Arrays.asList(
            new Edge("A", "B", 1),
            new Edge("B", "C", 2),
            new Edge("C", "D", 3),
            new Edge("D", "E", 4)
        );
        Graph graph = new Graph(1, nodes, edges);
        
        MSTResult primResult = PrimAlgorithm.findMST(graph);
        MSTResult kruskalResult = KruskalAlgorithm.findMST(graph);
        
        assertTrue(isConnected(primResult.getMstEdges(), nodes),
            "Prim's MST should connect all vertices");
        assertTrue(isConnected(kruskalResult.getMstEdges(), nodes),
            "Kruskal's MST should connect all vertices");
    }
    
    @Test
    @Order(5)
    @DisplayName("Test 5: Disconnected graph handling")
    public void test5_DisconnectedGraph() {
        List<String> nodes = Arrays.asList("A", "B", "C", "D");
        List<Edge> edges = Arrays.asList(
            new Edge("A", "B", 1),
            new Edge("C", "D", 2)
        );
        Graph graph = new Graph(1, nodes, edges);
        
        assertFalse(graph.isConnected(), "Graph should be disconnected");
        
        MSTResult primResult = PrimAlgorithm.findMST(graph);
        MSTResult kruskalResult = KruskalAlgorithm.findMST(graph);
        
        assertTrue(primResult.getMstEdges().isEmpty(),
            "Prim's should return empty MST for disconnected graph");
        assertTrue(kruskalResult.getMstEdges().isEmpty(),
            "Kruskal's should return empty MST for disconnected graph");
    }
    
    @Test
    @Order(6)
    @DisplayName("Test 6: Execution time is non-negative")
    public void test6_ExecutionTime() {
        List<String> nodes = Arrays.asList("A", "B", "C", "D");
        List<Edge> edges = Arrays.asList(
            new Edge("A", "B", 1),
            new Edge("B", "C", 2),
            new Edge("C", "D", 3)
        );
        Graph graph = new Graph(1, nodes, edges);
        
        MSTResult primResult = PrimAlgorithm.findMST(graph);
        MSTResult kruskalResult = KruskalAlgorithm.findMST(graph);
        
        assertTrue(primResult.getExecutionTimeMs() >= 0,
            "Prim's execution time should be non-negative");
        assertTrue(kruskalResult.getExecutionTimeMs() >= 0,
            "Kruskal's execution time should be non-negative");
    }
    
    @Test
    @Order(7)
    @DisplayName("Test 7: Operations count is positive")
    public void test7_OperationsCount() {
        List<String> nodes = Arrays.asList("A", "B", "C");
        List<Edge> edges = Arrays.asList(
            new Edge("A", "B", 1),
            new Edge("B", "C", 2)
        );
        Graph graph = new Graph(1, nodes, edges);
        
        MSTResult primResult = PrimAlgorithm.findMST(graph);
        MSTResult kruskalResult = KruskalAlgorithm.findMST(graph);
        
        assertTrue(primResult.getOperationsCount() > 0,
            "Prim's operations count should be positive");
        assertTrue(kruskalResult.getOperationsCount() > 0,
            "Kruskal's operations count should be positive");
    }
    
    @Test
    @Order(8)
    @DisplayName("Test 8: Results are reproducible")
    public void test8_Reproducibility() {
        List<String> nodes = Arrays.asList("A", "B", "C", "D");
        List<Edge> edges = Arrays.asList(
            new Edge("A", "B", 2),
            new Edge("B", "C", 3),
            new Edge("C", "D", 1),
            new Edge("A", "D", 4)
        );
        Graph graph = new Graph(1, nodes, edges);
        
        MSTResult prim1 = PrimAlgorithm.findMST(graph);
        MSTResult prim2 = PrimAlgorithm.findMST(graph);
        MSTResult kruskal1 = KruskalAlgorithm.findMST(graph);
        MSTResult kruskal2 = KruskalAlgorithm.findMST(graph);
        
        assertEquals(prim1.getTotalCost(), prim2.getTotalCost(),
            "Prim's should produce consistent results");
        assertEquals(kruskal1.getTotalCost(), kruskal2.getTotalCost(),
            "Kruskal's should produce consistent results");
    }
    
    @Test
    @Order(9)
    @DisplayName("Test 9: Single vertex graph")
    public void test9_SingleVertex() {
        List<String> nodes = Arrays.asList("A");
        List<Edge> edges = new ArrayList<>();
        Graph graph = new Graph(1, nodes, edges);
        
        MSTResult primResult = PrimAlgorithm.findMST(graph);
        MSTResult kruskalResult = KruskalAlgorithm.findMST(graph);
        
        assertEquals(0, primResult.getTotalCost(), "Cost should be 0");
        assertEquals(0, kruskalResult.getTotalCost(), "Cost should be 0");
        assertTrue(primResult.getMstEdges().isEmpty(), "No edges expected");
        assertTrue(kruskalResult.getMstEdges().isEmpty(), "No edges expected");
    }
    
    @Test
    @Order(10)
    @DisplayName("Test 10: Large graph performance")
    public void test10_LargeGraph() {
        List<String> nodes = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            nodes.add("V" + i);
        }
        
        List<Edge> edges = new ArrayList<>();
        Random random = new Random(42);
        for (int i = 0; i < nodes.size(); i++) {
            for (int j = i + 1; j < nodes.size(); j++) {
                edges.add(new Edge(nodes.get(i), nodes.get(j), random.nextInt(100) + 1));
            }
        }
        
        Graph graph = new Graph(1, nodes, edges);
        
        MSTResult primResult = PrimAlgorithm.findMST(graph);
        MSTResult kruskalResult = KruskalAlgorithm.findMST(graph);
        
        assertEquals(primResult.getTotalCost(), kruskalResult.getTotalCost(),
            "Costs should match");
        assertEquals(19, primResult.getMstEdges().size(), "Should have 19 edges");
        assertEquals(19, kruskalResult.getMstEdges().size(), "Should have 19 edges");
        
        System.out.println("\nLarge graph (20 vertices, " + edges.size() + " edges):");
        System.out.println("  Prim: " + String.format("%.2f", primResult.getExecutionTimeMs()) + 
                         " ms, " + primResult.getOperationsCount() + " operations");
        System.out.println("  Kruskal: " + String.format("%.2f", kruskalResult.getExecutionTimeMs()) + 
                         " ms, " + kruskalResult.getOperationsCount() + " operations");
    }
    
    // Helper methods
    private boolean isAcyclic(List<Edge> edges, List<String> nodes) {
        UnionFind uf = new UnionFind(nodes);
        for (Edge edge : edges) {
            if (!uf.union(edge.getFrom(), edge.getTo())) {
                return false;
            }
        }
        return true;
    }
    
    private boolean isConnected(List<Edge> edges, List<String> nodes) {
        if (nodes.isEmpty()) return true;
        
        Map<String, List<String>> adjacency = new HashMap<>();
        for (String node : nodes) {
            adjacency.put(node, new ArrayList<>());
        }
        
        for (Edge edge : edges) {
            adjacency.get(edge.getFrom()).add(edge.getTo());
            adjacency.get(edge.getTo()).add(edge.getFrom());
        }
        
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        queue.offer(nodes.get(0));
        visited.add(nodes.get(0));
        
        while (!queue.isEmpty()) {
            String current = queue.poll();
            for (String neighbor : adjacency.get(current)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.offer(neighbor);
                }
            }
        }
        
        return visited.size() == nodes.size();
    }
}
