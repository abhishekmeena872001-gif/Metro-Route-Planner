# Metro-Route-Planner
Java-based Metro Route Planner using Graph Data Structures, Dijkstra’s Algorithm, BFS, and DFS to find optimal metro routes based on distance, cost, and path selection.
# 🚇 Metro Route Planner

The **Metro Route Planner** is a Java-based application designed to help users find optimal routes between metro stations. The project represents a metro network as a **weighted graph** and uses different graph traversal and shortest-path algorithms to determine suitable routes based on distance, cost, and path selection.

This project demonstrates the practical application of **Data Structures and Algorithms (DSA)**, particularly graph representation, Dijkstra's Algorithm, Breadth-First Search (BFS), and Depth-First Search (DFS).

---

## 📌 Project Description

The Metro Route Planner models metro stations as **vertices (nodes)** and connections between stations as **edges**.

Each station contains information such as:

* Station ID
* Station Name
* Metro Line Number

Each connection contains:

* Source station
* Destination station
* Distance
* Travel cost

The application calculates different routes between a selected source and destination station.

---

## ✨ Features

### 1. Shortest Path

Uses **Dijkstra's Algorithm** to find the route with the minimum total distance between two metro stations.

**Criteria:**

* Minimum travel distance
* Suitable for finding the geographically shortest route

---

### 2. Cheapest Path

Uses a **BFS-based approach** to evaluate routes based primarily on distance and cost as a tie-breaker.

**Criteria:**

* Minimum accumulated distance
* Lower cost when distances are equal

> **Note:** In a weighted graph, standard BFS is not mathematically guaranteed to find the minimum-cost path. A priority-queue-based algorithm such as Dijkstra's Algorithm is more appropriate for a true cheapest-path implementation.

---

### 3. Best Path

Uses **DFS (Depth-First Search)** to explore possible routes between the source and destination.

The implementation selects the path with the **fewest number of stations/edges** among the explored paths and calculates its total distance and cost.

---

## 🧠 Algorithms Used

| Algorithm                | Purpose               | Main Criterion              |
| ------------------------ | --------------------- | --------------------------- |
| **Dijkstra's Algorithm** | Shortest Path         | Minimum Distance            |
| **BFS**                  | Route Traversal       | Distance + Cost Tie-Breaker |
| **DFS**                  | Best Path Exploration | Minimum Number of Stations  |

---

## 🗺️ Example Metro Network

The project uses a hypothetical metro network containing seven stations:

```text
Station A
Station B
Station C
Station D
Station E
Station F
Station G
```

The stations are distributed across different metro lines.

### Station Information

| ID | Station   | Line |
| -: | --------- | ---: |
|  0 | Station A |    1 |
|  1 | Station B |    1 |
|  2 | Station C |    2 |
|  3 | Station D |    2 |
|  4 | Station E |    3 |
|  5 | Station F |    3 |
|  6 | Station G |    1 |

---

## 🔗 Graph Representation

The metro network is represented using an **Adjacency List**.

Each edge contains:

```text
Source → Destination
Distance
Cost
```

For example:

```java
graph.get(4).add(
    new Edge(4, 2, 20, 30)
);
```

This represents:

```text
Station E → Station C
Distance = 20
Cost = 30
```

---

## 📂 Code Structure

The entire application is implemented in:

```text
MetroPathFinder.java
```

### `Station`

Represents a metro station.

```java
class Station {
    int id;
    String name;
    int line;
}
```

---

### `Edge`

Represents a connection between two stations.

```java
class Edge {
    int source;
    int destination;
    int distance;
    int cost;
}
```

---

### `dijkstra()`

Finds the shortest-distance route using Dijkstra's Algorithm.

```java
static List<Integer> dijkstra(...)
```

It maintains:

* Distance array
* Cost array
* Parent array
* Visited array

The parent array is used to reconstruct the final route.

---

### `bfs()`

Performs Breadth-First Search to traverse the graph and calculate route distance and cost.

```java
static List<Integer> bfs(...)
```

A queue is used to explore stations level by level.

---

### `dfs()`

Performs Depth-First Search to explore possible routes.

```java
static List<Integer> dfs(...)
```

The algorithm uses recursion and backtracking to explore different paths.

---

## 🛠️ Technologies Used

* **Java**
* **Java Collections Framework**
* `ArrayList`
* `Queue`
* `LinkedList`
* `Arrays`
* Graph Data Structure
* Dijkstra's Algorithm
* Breadth-First Search
* Depth-First Search

---

## 💻 Requirements

To run this project, you need:

* **Java JDK 8 or higher**
* Command Prompt / Terminal
* Any Java-compatible IDE such as:

  * IntelliJ IDEA
  * Eclipse
  * Visual Studio Code
  * NetBeans

Check your Java installation using:

```bash
java -version
```

Check the Java compiler using:

```bash
javac -version
```

---

## ▶️ How to Run

### 1. Clone the Repository

```bash
git clone https://github.com/your-username/Metro-Route-Planner.git
```

Navigate to the project directory:

```bash
cd Metro-Route-Planner
```

---

### 2. Compile the Java Program

If the source file is named:

```text
MetroPathFinder.java
```

compile it using:

```bash
javac MetroPathFinder.java
```

---

### 3. Run the Program

```bash
java MetroPathFinder
```

---

## 📊 Example Configuration

The program currently uses:

```java
int source = 4;
int destination = 1;
```

Therefore:

```text
Source      = Station E
Destination = Station B
```

You can change these values to test different routes.

For example:

```java
int source = 0;
int destination = 5;
```

---

## 📤 Example Output

The program displays the calculated routes in the following format:

```text
Shortest Path (Distance: XX, Cost: XX): Station E -> Station C -> Station B

Cheapest Path (Distance: XX, Cost: XX): Station E -> Station C -> Station B

Best Path (Distance: XX, Cost: XX): Station E -> Station C -> Station B
```

The exact result depends on the graph and source/destination stations.

---

## 📈 Time Complexity

### Dijkstra's Algorithm

The current implementation searches for the minimum-distance unvisited station using a loop.

```text
Time Complexity: O(V² + E)
Space Complexity: O(V + E)
```

where:

* `V` = number of stations
* `E` = number of connections

---

### BFS

For an adjacency-list representation:

```text
Time Complexity: O(V + E)
Space Complexity: O(V)
```

However, because the graph contains weighted edges, BFS is **not the ideal algorithm for a true minimum-cost path**.

---

### DFS

DFS explores possible paths recursively.

In the worst case, the number of possible paths can grow exponentially:

```text
Time Complexity: O(V!)
```

for exhaustive simple-path exploration in a dense graph.

Space complexity due to recursion and visited/path arrays is approximately:

```text
Space Complexity: O(V)
```

excluding storage for the graph and the resulting path.

---

## 🎯 Learning Objectives

This project demonstrates the following DSA concepts:

* Graph representation
* Adjacency Lists
* Weighted Graphs
* Graph traversal
* Recursion
* Backtracking
* Shortest-path algorithms
* Queue data structure
* Arrays and Lists
* Path reconstruction
* Object-oriented programming in Java

---

## 🔮 Future Enhancements

The project can be extended with the following features:

### 🚉 Real Metro Data

Replace the hypothetical stations with actual metro networks and station information.

### 🗺️ Interactive Map

Add a graphical map showing:

* Metro stations
* Metro lines
* Connections
* Selected route

### ⏱️ Travel Time

Add travel time to every edge and allow users to find the fastest route.

### 💰 Improved Fare Calculation

Implement a proper fare calculation system based on:

* Distance
* Number of stations
* Metro zones
* Interchange stations

### 🔄 Line Interchange

Add support for changing between different metro lines.

### 🖥️ Graphical User Interface

Create a GUI where users can select:

```text
Source Station
       ↓
Destination Station
       ↓
Route Preference
       ↓
Recommended Route
```

### 📡 Real-Time Data

Future versions could integrate real-time information such as:

* Train delays
* Station closures
* Service disruptions
* Live train availability

---

## 📁 Suggested Project Structure

```text
Metro-Route-Planner/
│
├── MetroPathFinder.java
├── README.md
└── .gitignore
```

A larger version of the project could be organized as:

```text
Metro-Route-Planner/
│
├── src/
│   ├── MetroPathFinder.java
│   ├── Station.java
│   └── Edge.java
│
├── README.md
├── .gitignore
└── LICENSE
```

---

## 🤝 Contributions

Contributions are welcome!

To contribute:

1. Fork the repository.
2. Create a new branch.
3. Make your changes.
4. Commit your changes.
5. Push the branch.
6. Create a Pull Request.

Example:

```bash
git checkout -b feature/new-route-algorithm
git add .
git commit -m "Add improved route algorithm"
git push origin feature/new-route-algorithm
```

---

## 📜 License

This project is open-source and can be used for educational and learning purposes.

---

## 👨‍💻 Author

**Samar**

A Java-based DSA project demonstrating graph algorithms through a practical **Metro Route Planning** application.

---

## ⭐ If You Like This Project

If you found this project useful for learning **Java, DSA, and Graph Algorithms**, consider giving the repository a ⭐ on GitHub.
