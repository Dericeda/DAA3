# Compilation Instructions

This document provides detailed instructions for compiling and running the MST project.

## Table of Contents
1. [Prerequisites](#prerequisites)
2. [Project Structure](#project-structure)
3. [Automated Compilation](#automated-compilation)
4. [Manual Compilation](#manual-compilation)
5. [Running Tests](#running-tests)
6. [Troubleshooting](#troubleshooting)

## Prerequisites

### Required Software
- **Java Development Kit (JDK)** version 8 or higher
  - Download from: https://www.oracle.com/java/technologies/downloads/
  - Verify installation: `java -version` and `javac -version`

### Optional Tools
- **Git** (for cloning the repository)
- **IDE** (IntelliJ IDEA, Eclipse, or VS Code with Java extensions)

## Project Structure

```
MST_Project/
├── src/                        # Java source files
│   ├── Edge.java
│   ├── Graph.java
│   ├── UnionFind.java
│   ├── PrimAlgorithm.java
│   ├── KruskalAlgorithm.java
│   ├── MSTResult.java
│   ├── JSONHandler.java
│   ├── SimpleJSONParser.java
│   └── MSTSolver.java
│
├── tests/                      # JUnit test files
│   └── MSTTest.java
│
├── data/                       # Input/output data
│   ├── input.json
│   └── output_results.json
│
├── docs/                       # Documentation
├── run.sh                      # Linux/Mac script
└── run.bat                     # Windows script
```

## Automated Compilation

The easiest way to compile and run the project is using the provided scripts.

### On Linux/macOS

```bash
# Make the script executable (first time only)
chmod +x run.sh

# Run the script
./run.sh
```

The script will:
1. Create output directories (`out/src` and `out/tests`)
2. Compile all source files from `src/` to `out/src/`
3. Download JUnit 5 (if not already present)
4. Compile test files from `tests/` to `out/tests/`
5. Run the main program with sample data
6. Execute all JUnit tests

### On Windows

```cmd
run.bat
```

The batch file performs the same steps as the Linux script.

## Manual Compilation

If you prefer to compile manually or the scripts don't work:

### Step 1: Create Output Directories

```bash
# Linux/macOS
mkdir -p out/src out/tests

# Windows
mkdir out\src
mkdir out\tests
```

### Step 2: Compile Source Files

```bash
# Linux/macOS
javac -d out/src src/*.java

# Windows
javac -d out\src src\*.java
```

This compiles all `.java` files from the `src/` directory and places the `.class` files in `out/src/`.

### Step 3: Download JUnit 5 (if needed)

```bash
# Linux/macOS
wget https://repo1.maven.org/maven2/org/junit/platform/junit-platform-console-standalone/1.9.3/junit-platform-console-standalone-1.9.3.jar

# Windows (using PowerShell)
Invoke-WebRequest -Uri "https://repo1.maven.org/maven2/org/junit/platform/junit-platform-console-standalone/1.9.3/junit-platform-console-standalone-1.9.3.jar" -OutFile "junit-platform-console-standalone-1.9.3.jar"
```

Or download manually from:
https://repo1.maven.org/maven2/org/junit/platform/junit-platform-console-standalone/1.9.3/

### Step 4: Compile Test Files

```bash
# Linux/macOS
javac -cp "out/src:junit-platform-console-standalone-1.9.3.jar" -d out/tests tests/*.java

# Windows
javac -cp "out\src;junit-platform-console-standalone-1.9.3.jar" -d out\tests tests\*.java
```

### Step 5: Run the Main Program

```bash
# Linux/macOS
java -cp out/src MSTSolver data/input.json data/output_results.json

# Windows
java -cp out\src MSTSolver data\input.json data\output_results.json
```

### Step 6: Run JUnit Tests

```bash
# Linux/macOS
java -jar junit-platform-console-standalone-1.9.3.jar --class-path out/tests:out/src --scan-class-path

# Windows
java -jar junit-platform-console-standalone-1.9.3.jar --class-path out\tests;out\src --scan-class-path
```

## Running Tests

### Running All Tests

```bash
java -jar junit-platform-console-standalone-1.9.3.jar --class-path out/tests:out/src --scan-class-path
```

### Running Specific Test Class

```bash
java -jar junit-platform-console-standalone-1.9.3.jar --class-path out/tests:out/src --select-class MSTTest
```

### Expected Test Output

```
=============================================================
Running MST Algorithm Tests with JUnit 5
=============================================================

Test 1: Both algorithms produce same total cost ✓ PASS
Test 2: MST has V-1 edges ✓ PASS
Test 3: MST is acyclic ✓ PASS
Test 4: MST connects all vertices ✓ PASS
Test 5: Disconnected graph handling ✓ PASS
Test 6: Execution time is non-negative ✓ PASS
Test 7: Operations count is positive ✓ PASS
Test 8: Results are reproducible ✓ PASS
Test 9: Single vertex graph ✓ PASS
Test 10: Large graph performance ✓ PASS

=============================================================
All tests completed
=============================================================
```

## Using an IDE

### IntelliJ IDEA

1. Open IntelliJ IDEA
2. Select `File > Open` and choose the `MST_Project` directory
3. Wait for IntelliJ to index the project
4. Right-click on `src` folder and select `Mark Directory as > Sources Root`
5. Right-click on `tests` folder and select `Mark Directory as > Test Sources Root`
6. Add JUnit 5 to the project:
   - `File > Project Structure > Libraries`
   - Click `+` and add `junit-platform-console-standalone-1.9.3.jar`
7. Right-click on `MSTSolver.java` and select `Run 'MSTSolver.main()'`
8. Right-click on `MSTTest.java` and select `Run 'MSTTest'`

### Eclipse

1. Open Eclipse
2. Select `File > Import > General > Existing Projects into Workspace`
3. Choose the `MST_Project` directory
4. Add JUnit 5:
   - Right-click project > `Build Path > Configure Build Path`
   - Select `Libraries` tab > `Add External JARs`
   - Add `junit-platform-console-standalone-1.9.3.jar`
5. Right-click on `MSTSolver.java` and select `Run As > Java Application`
6. Right-click on `MSTTest.java` and select `Run As > JUnit Test`

### VS Code

1. Install Java Extension Pack from VS Code marketplace
2. Open the `MST_Project` folder in VS Code
3. Add JUnit 5 to `.classpath` (VS Code will prompt)
4. Click `Run` above the `main` method in `MSTSolver.java`
5. Click `Run Test` above each test method in `MSTTest.java`

## Troubleshooting

### Problem: "javac: command not found" or "'javac' is not recognized"

**Solution**: Java JDK is not installed or not in PATH.

- **Install JDK**: Download from https://www.oracle.com/java/technologies/downloads/
- **Add to PATH**:
  - Linux/macOS: Add `export PATH=/path/to/jdk/bin:$PATH` to `~/.bashrc` or `~/.zshrc`
  - Windows: Add JDK `bin` directory to System Environment Variables PATH

### Problem: Compilation errors about missing classes

**Solution**: Make sure you're in the correct directory and all source files are present.

```bash
# Verify you're in the project root
ls -la
# Should show: src/, tests/, data/, docs/, run.sh, run.bat, README.md

# Verify source files exist
ls src/
# Should show: Edge.java, Graph.java, etc.
```

### Problem: "Could not find or load main class MSTSolver"

**Solution**: Make sure the classpath is set correctly.

```bash
# Use absolute path if relative doesn't work
java -cp /full/path/to/MST_Project/out/src MSTSolver data/input.json data/output_results.json
```

### Problem: JUnit tests won't run

**Solution**: Verify JUnit jar is downloaded and classpath is correct.

```bash
# Check if JUnit jar exists
ls -l junit-platform-console-standalone-1.9.3.jar

# Verify test compilation
ls out/tests/
# Should show: MSTTest.class and other .class files

# Try running with explicit paths
java -jar ./junit-platform-console-standalone-1.9.3.jar \
  --class-path ./out/tests:./out/src \
  --scan-class-path
```

### Problem: "Exception in thread ... NoClassDefFoundError"

**Solution**: Dependencies are missing from classpath.

- For tests: Ensure both `out/tests` and `out/src` are in classpath
- For main program: Ensure `out/src` is in classpath

### Problem: Tests compile but won't discover

**Solution**: Check JUnit version compatibility.

```bash
# Verify Java version (should be 8+)
java -version

# Re-download JUnit if necessary
rm junit-platform-console-standalone-1.9.3.jar
wget https://repo1.maven.org/maven2/org/junit/platform/junit-platform-console-standalone/1.9.3/junit-platform-console-standalone-1.9.3.jar
```

### Problem: Permission denied on Linux/macOS

**Solution**: Make scripts executable.

```bash
chmod +x run.sh
```

### Problem: Output files not created

**Solution**: Ensure `data/` directory exists and you have write permissions.

```bash
# Create directory if missing
mkdir -p data

# Check permissions
ls -ld data/
# Should show: drwxr-xr-x (writable)

# Grant permissions if needed
chmod 755 data/
```

## Custom Input Files

To use your own input data:

1. Create a JSON file in the `data/` directory
2. Follow the format in `data/input.json`
3. Run the program with your file:

```bash
java -cp out/src MSTSolver data/your_input.json data/your_output.json
```

Example input format:
```json
{
  "graphs": [
    {
      "id": 1,
      "nodes": ["A", "B", "C"],
      "edges": [
        {"from": "A", "to": "B", "weight": 5},
        {"from": "B", "to": "C", "weight": 3}
      ]
    }
  ]
}
```

## Performance Tips

1. **Use Scripts**: The automated scripts are optimized and tested
2. **Compile Once**: Recompilation is only needed when code changes
3. **Large Graphs**: For graphs with 100+ vertices, increase JVM heap size:
   ```bash
   java -Xmx2g -cp out/src MSTSolver data/large_input.json data/output.json
   ```
4. **Batch Processing**: Process multiple graphs in one input file for efficiency

## Additional Resources

- **JUnit 5 User Guide**: https://junit.org/junit5/docs/current/user-guide/
- **Java Documentation**: https://docs.oracle.com/en/java/
- **Project Documentation**: See [INDEX.md](../../../Downloads/MST_Project_Updated/MST_Project/INDEX.md) for all documentation

## Summary

**Quickest Method**: Use `./run.sh` (Linux/macOS) or `run.bat` (Windows)

**Manual Steps**:
1. Compile source: `javac -d out/src src/*.java`
2. Download JUnit (if testing)
3. Compile tests: `javac -cp "out/src:junit.jar" -d out/tests tests/*.java`
4. Run program: `java -cp out/src MSTSolver data/input.json data/output.json`
5. Run tests: `java -jar junit.jar --class-path out/tests:out/src --scan-class-path`

For questions or issues, refer to the [troubleshooting section](#troubleshooting) above.
