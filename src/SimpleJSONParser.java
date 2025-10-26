import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * Simple JSON parser specifically for our graph data format
 */
public class SimpleJSONParser {
    
    public static List<Graph> parseGraphs(String filename) throws IOException {
        String content = new String(Files.readAllBytes(Paths.get(filename)));
        content = content.replaceAll("\\s+", " "); // Normalize whitespace
        
        List<Graph> graphs = new ArrayList<>();
        
        // Find "graphs" array
        int graphsStart = content.indexOf("\"graphs\"");
        if (graphsStart == -1) return graphs;
        
        // Find the opening bracket of graphs array
        int arrayStart = content.indexOf("[", graphsStart);
        int arrayEnd = findMatchingBracket(content, arrayStart, '[', ']');
        
        String graphsArray = content.substring(arrayStart + 1, arrayEnd);
        
        // Parse each graph
        int pos = 0;
        while (pos < graphsArray.length()) {
            int graphStart = graphsArray.indexOf("{", pos);
            if (graphStart == -1) break;
            
            int graphEnd = findMatchingBracket(graphsArray, graphStart, '{', '}');
            String graphStr = graphsArray.substring(graphStart, graphEnd + 1);
            
            graphs.add(parseGraph(graphStr));
            pos = graphEnd + 1;
        }
        
        return graphs;
    }
    
    private static Graph parseGraph(String graphStr) {
        int id = parseInt(graphStr, "\"id\"");
        List<String> nodes = parseStringArray(graphStr, "\"nodes\"");
        List<Edge> edges = parseEdges(graphStr);
        return new Graph(id, nodes, edges);
    }
    
    private static int parseInt(String str, String key) {
        int keyPos = str.indexOf(key);
        if (keyPos == -1) return 0;
        
        int colonPos = str.indexOf(":", keyPos);
        int commaPos = str.indexOf(",", colonPos);
        int bracePos = str.indexOf("}", colonPos);
        
        int endPos = (commaPos != -1 && commaPos < bracePos) ? commaPos : bracePos;
        
        String numStr = str.substring(colonPos + 1, endPos).trim();
        return Integer.parseInt(numStr);
    }
    
    private static List<String> parseStringArray(String str, String key) {
        List<String> result = new ArrayList<>();
        
        int keyPos = str.indexOf(key);
        if (keyPos == -1) return result;
        
        int arrayStart = str.indexOf("[", keyPos);
        int arrayEnd = findMatchingBracket(str, arrayStart, '[', ']');
        
        String arrayContent = str.substring(arrayStart + 1, arrayEnd);
        
        int pos = 0;
        while (pos < arrayContent.length()) {
            int quoteStart = arrayContent.indexOf("\"", pos);
            if (quoteStart == -1) break;
            
            int quoteEnd = arrayContent.indexOf("\"", quoteStart + 1);
            result.add(arrayContent.substring(quoteStart + 1, quoteEnd));
            pos = quoteEnd + 1;
        }
        
        return result;
    }
    
    private static List<Edge> parseEdges(String str) {
        List<Edge> result = new ArrayList<>();
        
        int edgesPos = str.indexOf("\"edges\"");
        if (edgesPos == -1) return result;
        
        int arrayStart = str.indexOf("[", edgesPos);
        int arrayEnd = findMatchingBracket(str, arrayStart, '[', ']');
        
        String arrayContent = str.substring(arrayStart + 1, arrayEnd);
        
        int pos = 0;
        while (pos < arrayContent.length()) {
            int edgeStart = arrayContent.indexOf("{", pos);
            if (edgeStart == -1) break;
            
            int edgeEnd = findMatchingBracket(arrayContent, edgeStart, '{', '}');
            String edgeStr = arrayContent.substring(edgeStart, edgeEnd + 1);
            
            String from = parseString(edgeStr, "\"from\"");
            String to = parseString(edgeStr, "\"to\"");
            int weight = parseInt(edgeStr, "\"weight\"");
            
            result.add(new Edge(from, to, weight));
            pos = edgeEnd + 1;
        }
        
        return result;
    }
    
    private static String parseString(String str, String key) {
        int keyPos = str.indexOf(key);
        if (keyPos == -1) return "";
        
        int colonPos = str.indexOf(":", keyPos);
        int quoteStart = str.indexOf("\"", colonPos);
        int quoteEnd = str.indexOf("\"", quoteStart + 1);
        
        return str.substring(quoteStart + 1, quoteEnd);
    }
    
    private static int findMatchingBracket(String str, int start, char open, char close) {
        int count = 1;
        for (int i = start + 1; i < str.length(); i++) {
            if (str.charAt(i) == open) count++;
            if (str.charAt(i) == close) count--;
            if (count == 0) return i;
        }
        return -1;
    }
}
