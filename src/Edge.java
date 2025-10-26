/**
 * Represents an edge in a weighted graph
 */
public class Edge implements Comparable<Edge> {
    private String from;
    private String to;
    private int weight;

    public Edge(String from, String to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public int compareTo(Edge other) {
        return Integer.compare(this.weight, other.weight);
    }

    @Override
    public String toString() {
        return String.format("{from: %s, to: %s, weight: %d}", from, to, weight);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Edge edge = (Edge) obj;
        return weight == edge.weight &&
               ((from.equals(edge.from) && to.equals(edge.to)) ||
                (from.equals(edge.to) && to.equals(edge.from)));
    }

    @Override
    public int hashCode() {
        // Ensure undirected edge comparison
        int node1 = from.compareTo(to) < 0 ? from.hashCode() : to.hashCode();
        int node2 = from.compareTo(to) < 0 ? to.hashCode() : from.hashCode();
        return 31 * (31 * node1 + node2) + weight;
    }
}
