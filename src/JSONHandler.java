import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * Utility class for reading and writing JSON files
 */
public class JSONHandler {
    
    /**
     * Read graphs from JSON file
     */
    public static List<Graph> readGraphsFromJSON(String filename) throws IOException {
        return SimpleJSONParser.parseGraphs(filename);
    }
    
    /**
     * Write results to JSON file
     */
    public static void writeResultsToJSON(String filename, List<GraphResult> results) throws IOException {
        StringBuilder json = new StringBuilder();
        json.append("{\n  \"results\": [\n");
        
        for (int i = 0; i < results.size(); i++) {
            GraphResult result = results.get(i);
            json.append("    {\n");
            json.append("      \"graph_id\": ").append(result.getGraphId()).append(",\n");
            json.append("      \"input_stats\": {\n");
            json.append("        \"vertices\": ").append(result.getVertices()).append(",\n");
            json.append("        \"edges\": ").append(result.getEdges()).append("\n");
            json.append("      },\n");
            
            json.append("      \"prim\": ");
            appendAlgorithmResult(json, result.getPrimResult());
            json.append(",\n");
            
            json.append("      \"kruskal\": ");
            appendAlgorithmResult(json, result.getKruskalResult());
            json.append("\n");
            
            json.append("    }");
            if (i < results.size() - 1) {
                json.append(",");
            }
            json.append("\n");
        }
        
        json.append("  ]\n}");
        
        try (FileWriter file = new FileWriter(filename)) {
            file.write(json.toString());
        }
    }
    
    private static void appendAlgorithmResult(StringBuilder json, MSTResult result) {
        json.append("{\n");
        json.append("        \"mst_edges\": [\n");
        
        List<Edge> edges = result.getMstEdges();
        for (int i = 0; i < edges.size(); i++) {
            Edge edge = edges.get(i);
            json.append("          {\"from\": \"").append(edge.getFrom())
                .append("\", \"to\": \"").append(edge.getTo())
                .append("\", \"weight\": ").append(edge.getWeight()).append("}");
            if (i < edges.size() - 1) {
                json.append(",");
            }
            json.append("\n");
        }
        
        json.append("        ],\n");
        json.append("        \"total_cost\": ").append(result.getTotalCost()).append(",\n");
        json.append("        \"operations_count\": ").append(result.getOperationsCount()).append(",\n");
        json.append("        \"execution_time_ms\": ")
            .append(String.format("%.2f", result.getExecutionTimeMs())).append("\n");
        json.append("      }");
    }
}

/**
 * Container class for storing results of both algorithms for a single graph
 */
class GraphResult {
    private int graphId;
    private int vertices;
    private int edges;
    private MSTResult primResult;
    private MSTResult kruskalResult;
    
    public GraphResult(int graphId, int vertices, int edges, MSTResult primResult, MSTResult kruskalResult) {
        this.graphId = graphId;
        this.vertices = vertices;
        this.edges = edges;
        this.primResult = primResult;
        this.kruskalResult = kruskalResult;
    }
    
    public int getGraphId() { return graphId; }
    public int getVertices() { return vertices; }
    public int getEdges() { return edges; }
    public MSTResult getPrimResult() { return primResult; }
    public MSTResult getKruskalResult() { return kruskalResult; }
}
