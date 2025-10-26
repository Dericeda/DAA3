# Minimum Spanning Tree (MST) Algorithms - Assignment 3

This project implements and compares two classical algorithms for finding the Minimum Spanning Tree of a weighted undirected graph: **Prim's Algorithm** and **Kruskal's Algorithm**.

## 📋 Project Overview

The city administration plans to construct roads connecting all districts with the goal of minimizing total construction costs. This problem is modeled as finding the Minimum Spanning Tree (MST) in a weighted graph where:
- Vertices represent city districts
- Edges represent potential roads
- Edge weights represent construction costs

## 🎯 Features

- ✅ Implementation of **Prim's Algorithm**
- ✅ Implementation of **Kruskal's Algorithm**
- ✅ JSON-based input/output handling
- ✅ Performance metrics tracking (execution time, operation count)
- ✅ Comprehensive test suite with **JUnit 5**
- ✅ Detailed analytical report
- ✅ Organized project structure

## 📁 Project Structure

```
MST_Project/
├── src/                        # Source code directory
│   ├── Edge.java              # Edge representation
│   ├── Graph.java             # Graph data structure
│   ├── UnionFind.java         # Disjoint set for Kruskal's
│   ├── PrimAlgorithm.java     # Prim's algorithm implementation
│   ├── KruskalAlgorithm.java  # Kruskal's algorithm implementation
│   ├── MSTResult.java         # Result container
│   ├── JSONHandler.java       # JSON input/output handler
│   ├── SimpleJSONParser.java  # Custom JSON parser
│   └── MSTSolver.java         # Main program
│
├── tests/                      # JUnit test files
│   └── MSTTest.java           # Comprehensive JUnit 5 test suite
│
├── data/                       # Input/output data files
│   ├── input.json             # Sample input data
│   └── output_results.json    # Generated results
│
├── docs/                       # Documentation files
│   ├── ANALYTICAL_REPORT.md   # Performance analysis report
│   ├── COMPILATION_INSTRUCTIONS.md
│   ├── FILE_LISTING.md
│   └── PROJECT_SUMMARY.md
│
├── run.sh                      # Linux/Mac execution script
├── run.bat                     # Windows execution script
├── .gitignore                  # Git ignore file
├── INDEX.md                    # Project navigation
├── QUICKSTART.md              # Quick start guide
└── README.md                   # This file
```

## 🚀 Quick Start

### Prerequisites
- Java Development Kit (JDK) 8 or higher
- Command line access (Terminal/Command Prompt)

### Running the Project

#### On Linux/Mac:
```bash
chmod +x run.sh
./run.sh
```

#### On Windows:
```cmd
run.bat
```

The script will automatically:
1. Compile all source files
2. Download JUnit 5 (if not present)
3. Compile test files
4. Run the main program with sample data
5. Execute all JUnit tests

## 📊 Input/Output Format

### Input JSON Structure
```json
{
  "graphs": [
    {
      "id": 1,
      "nodes": ["A", "B", "C", "D"],
      "edges": [
        {"from": "A", "to": "B", "weight": 4},
        {"from": "B", "to": "C", "weight": 2}
      ]
    }
  ]
}
```

### Output JSON Structure
```json
{
  "results": [
    {
      "graph_id": 1,
      "input_stats": {
        "vertices": 4,
        "edges": 5
      },
      "prim": {
        "mst_edges": [...],
        "total_cost": 6,
        "operations_count": 29,
        "execution_time_ms": 0.87
      },
      "kruskal": {
        "mst_edges": [...],
        "total_cost": 6,
        "operations_count": 31,
        "execution_time_ms": 0.92
      }
    }
  ]
}
```

## 🧪 Testing

The project includes comprehensive JUnit 5 tests that verify:

### Correctness Tests
- ✓ Both algorithms produce identical MST costs
- ✓ MST contains exactly V-1 edges
- ✓ MST is acyclic (no cycles)
- ✓ MST connects all vertices
- ✓ Disconnected graphs are handled properly

### Performance Tests
- ✓ Execution time is non-negative
- ✓ Operation counts are positive
- ✓ Results are reproducible
- ✓ Large graph scalability

### Running Tests Separately
```bash
# Compile tests
javac -cp "out/src:junit-platform-console-standalone-1.9.3.jar" -d out/tests tests/*.java

# Run tests
java -jar junit-platform-console-standalone-1.9.3.jar --class-path out/tests:out/src --scan-class-path
```

## 🔍 Algorithm Implementations

### Prim's Algorithm
- **Approach**: Grows MST from a starting vertex by adding minimum weight edges
- **Data Structure**: Priority Queue (Min-Heap)
- **Time Complexity**: O(E log V)
- **Best For**: Dense graphs

### Kruskal's Algorithm
- **Approach**: Sorts all edges and adds them if they don't create a cycle
- **Data Structure**: Union-Find (Disjoint Set Union)
- **Time Complexity**: O(E log E)
- **Best For**: Sparse graphs

## 📈 Performance Analysis

For detailed performance analysis and comparison, see [ANALYTICAL_REPORT.md](docs/ANALYTICAL_REPORT.md)

Key findings:
- Both algorithms produce optimal MST with identical total cost
- Prim's generally performs better on dense graphs
- Kruskal's is more efficient on sparse graphs
- Operation counts vary based on graph structure

## 📚 Documentation

- **[INDEX.md](INDEX.md)** - Main project navigation and file overview
- **[QUICKSTART.md](QUICKSTART.md)** - Quick start guide (2 minutes)
- **[COMPILATION_INSTRUCTIONS.md](docs/COMPILATION_INSTRUCTIONS.md)** - Detailed compilation guide
- **[ANALYTICAL_REPORT.md](docs/ANALYTICAL_REPORT.md)** - Performance analysis (14 pages)
- **[PROJECT_SUMMARY.md](docs/PROJECT_SUMMARY.md)** - Complete project summary
- **[FILE_LISTING.md](docs/FILE_LISTING.md)** - List of all project files

## 🎓 Assignment Requirements

This project fulfills all requirements of Assignment 3:

- ✅ Prim's algorithm implementation (25%)
- ✅ Kruskal's algorithm implementation (25%)
- ✅ Analytical report with comparisons (25%)
- ✅ Code readability and GitHub repository (15%)
- ✅ Comprehensive testing with JUnit (10%)
- ✅ **BONUS**: Custom Graph data structure (10%)

## 🛠️ Development

### Manual Compilation
```bash
# Compile source files
javac -d out/src src/*.java

# Compile tests (requires JUnit 5)
javac -cp "out/src:junit-platform-console-standalone-1.9.3.jar" -d out/tests tests/*.java

# Run main program
java -cp out/src MSTSolver data/input.json data/output_results.json

# Run tests
java -jar junit-platform-console-standalone-1.9.3.jar --class-path out/tests:out/src --scan-class-path
```

### Project Dependencies
- **JUnit 5** (junit-platform-console-standalone-1.9.3.jar)
  - Automatically downloaded by run scripts
  - Or download from: https://repo1.maven.org/maven2/org/junit/platform/junit-platform-console-standalone/1.9.3/

## 👨‍💻 Author

Created for Assignment 3: Optimization of a City Transportation Network (Minimum Spanning Tree)

## 📝 License

This project is created for educational purposes as part of a university assignment.

---

**For quick start instructions, see [QUICKSTART.md](QUICKSTART.md)**

**For complete documentation, see [INDEX.md](INDEX.md)**
