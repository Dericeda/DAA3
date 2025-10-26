import java.io.IOException;
import java.util.*;

/**
 * Main class for solving MST problems using Prim's and Kruskal's algorithms
 */
public class MSTSolver {
    
    public static void main(String[] args) {
        try {
            // Read input graphs from JSON file
            String inputFile = "input.json";
            System.out.println("Reading graphs from " + inputFile + "...");
            List<Graph> graphs = JSONHandler.readGraphsFromJSON(inputFile);
            System.out.println("Loaded " + graphs.size() + " graphs.\n");
            
            // Process each graph
            List<GraphResult> results = new ArrayList<>();
            
            for (Graph graph : graphs) {
                System.out.println("=".repeat(60));
                System.out.println("Processing " + graph);
                System.out.println("=".repeat(60));
                
                if (!graph.isConnected()) {
                    System.out.println("WARNING: Graph is not connected! MST cannot be found.\n");
                    continue;
                }
                
                // Run Prim's algorithm
                System.out.println("\nRunning Prim's Algorithm...");
                MSTResult primResult = PrimAlgorithm.findMST(graph);
                System.out.println(primResult);
                
                // Run Kruskal's algorithm
                System.out.println("Running Kruskal's Algorithm...");
                MSTResult kruskalResult = KruskalAlgorithm.findMST(graph);
                System.out.println(kruskalResult);
                
                // Verify results
                System.out.println("Verification:");
                System.out.println("  Both algorithms produce same total cost: " + 
                    (primResult.getTotalCost() == kruskalResult.getTotalCost() ? "✓ PASS" : "✗ FAIL"));
                System.out.println("  Prim MST has V-1 edges: " + 
                    (primResult.getMstEdges().size() == graph.getVertexCount() - 1 ? "✓ PASS" : "✗ FAIL"));
                System.out.println("  Kruskal MST has V-1 edges: " + 
                    (kruskalResult.getMstEdges().size() == graph.getVertexCount() - 1 ? "✓ PASS" : "✗ FAIL"));
                
                // Store result
                results.add(new GraphResult(
                    graph.getId(),
                    graph.getVertexCount(),
                    graph.getEdgeCount(),
                    primResult,
                    kruskalResult
                ));
                
                System.out.println();
            }
            
            // Write results to output file
            String outputFile = "output_results.json";
            System.out.println("Writing results to " + outputFile + "...");
            JSONHandler.writeResultsToJSON(outputFile, results);
            System.out.println("Results saved successfully!");
            
            // Print summary table
            printSummaryTable(results);
            
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Print a summary table comparing Prim's and Kruskal's algorithms
     */
    private static void printSummaryTable(List<GraphResult> results) {
        System.out.println("\n" + "=".repeat(100));
        System.out.println("PERFORMANCE COMPARISON SUMMARY");
        System.out.println("=".repeat(100));
        System.out.printf("%-10s %-12s %-10s %-25s %-25s %-25s\n",
            "Graph ID", "V/E", "MST Cost",
            "Prim (ops/time ms)", "Kruskal (ops/time ms)", "Winner");
        System.out.println("-".repeat(100));
        
        for (GraphResult result : results) {
            MSTResult prim = result.getPrimResult();
            MSTResult kruskal = result.getKruskalResult();
            
            String winner = "";
            if (prim.getExecutionTimeMs() < kruskal.getExecutionTimeMs()) {
                winner = "Prim";
            } else if (kruskal.getExecutionTimeMs() < prim.getExecutionTimeMs()) {
                winner = "Kruskal";
            } else {
                winner = "Tie";
            }
            
            System.out.printf("%-10d %-12s %-10d %-25s %-25s %-25s\n",
                result.getGraphId(),
                result.getVertices() + "/" + result.getEdges(),
                prim.getTotalCost(),
                prim.getOperationsCount() + " / " + String.format("%.2f", prim.getExecutionTimeMs()),
                kruskal.getOperationsCount() + " / " + String.format("%.2f", kruskal.getExecutionTimeMs()),
                winner);
        }
        System.out.println("=".repeat(100));
    }
}
