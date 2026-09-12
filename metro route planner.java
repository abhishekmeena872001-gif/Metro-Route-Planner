import java.util.*;

// Structure to represent a station
class Station {
    int id;
    String name;
    int line;

    Station(int id, String name, int line) {
        this.id = id;
        this.name = name;
        this.line = line;
    }
}

// Structure to represent an edge between two stations
class Edge {
    int source;
    int destination;
    int distance;
    int cost;

    Edge(int source, int destination, int distance, int cost) {
        this.source = source;
        this.destination = destination;
        this.distance = distance;
        this.cost = cost;
    }
}

public class MetroPathFinder {

    // Function to find shortest path using Dijkstra's algorithm
    static List<Integer> dijkstra(
            List<List<Edge>> graph,
            int source,
            int destination,
            List<Station> stations,
            int[] result) {

        int numStations = graph.size();

        int[] distance = new int[numStations];
        int[] cost = new int[numStations];
        int[] parent = new int[numStations];
        boolean[] visited = new boolean[numStations];

        Arrays.fill(distance, Integer.MAX_VALUE);
        Arrays.fill(cost, Integer.MAX_VALUE);
        Arrays.fill(parent, -1);

        distance[source] = 0;
        cost[source] = 0;

        for (int i = 0; i < numStations - 1; i++) {

            int minDistance = Integer.MAX_VALUE;
            int currentStation = -1;

            // Find station having minimum distance
            for (int j = 0; j < numStations; j++) {
                if (!visited[j] && distance[j] < minDistance) {
                    minDistance = distance[j];
                    currentStation = j;
                }
            }

            if (currentStation == -1)
                break;

            visited[currentStation] = true;

            // Update neighboring stations
            for (Edge edge : graph.get(currentStation)) {

                int neighbor = edge.destination;

                int newDistance =
                        distance[currentStation] + edge.distance;

                int newCost =
                        cost[currentStation] + edge.cost;

                if (!visited[neighbor] &&
                        newDistance < distance[neighbor]) {

                    distance[neighbor] = newDistance;
                    cost[neighbor] = newCost;
                    parent[neighbor] = currentStation;
                }
            }
        }

        result[0] = distance[destination];
        result[1] = cost[destination];

        // Reconstruct path
        List<Integer> path = new ArrayList<>();

        int current = destination;

        while (current != -1) {
            path.add(current);
            current = parent[current];
        }

        Collections.reverse(path);

        return path;
    }


    // Function to find cheapest path using BFS
    static List<Integer> bfs(
            List<List<Edge>> graph,
            int source,
            int destination,
            List<Station> stations,
            int[] result) {

        int numStations = graph.size();

        int[] distance = new int[numStations];
        int[] cost = new int[numStations];
        int[] parent = new int[numStations];

        Arrays.fill(distance, Integer.MAX_VALUE);
        Arrays.fill(cost, Integer.MAX_VALUE);
        Arrays.fill(parent, -1);

        distance[source] = 0;
        cost[source] = 0;

        Queue<Integer> queue = new LinkedList<>();
        queue.add(source);

        while (!queue.isEmpty()) {

            int currentStation = queue.poll();

            for (Edge edge : graph.get(currentStation)) {

                int neighbor = edge.destination;

                int newDistance =
                        distance[currentStation] + edge.distance;

                int newCost =
                        cost[currentStation] + edge.cost;

                if (newDistance < distance[neighbor] ||
                        (newDistance == distance[neighbor]
                                && newCost < cost[neighbor])) {

                    distance[neighbor] = newDistance;
                    cost[neighbor] = newCost;
                    parent[neighbor] = currentStation;

                    queue.add(neighbor);
                }
            }
        }

        result[0] = distance[destination];
        result[1] = cost[destination];

        // Reconstruct path
        List<Integer> path = new ArrayList<>();

        int current = destination;

        while (current != -1) {
            path.add(current);
            current = parent[current];
        }

        Collections.reverse(path);

        return path;
    }


    // DFS utility function
    static void dfsUtil(
            List<List<Edge>> graph,
            int current,
            int destination,
            boolean[] visited,
            List<Integer> path,
            List<Integer> bestPath,
            int[] bestResult) {

        visited[current] = true;
        path.add(current);

        // Destination reached
        if (current == destination &&
                (bestPath.isEmpty() ||
                        path.size() < bestPath.size())) {

            bestPath.clear();
            bestPath.addAll(path);

            int bestDistance = 0;
            int bestCost = 0;

            // Calculate total distance and cost
            for (int i = 1; i < path.size(); i++) {

                int source = path.get(i - 1);
                int dest = path.get(i);

                for (Edge edge : graph.get(source)) {

                    if (edge.destination == dest) {

                        bestDistance += edge.distance;
                        bestCost += edge.cost;

                        break;
                    }
                }
            }

            bestResult[0] = bestDistance;
            bestResult[1] = bestCost;
        }

        // Explore neighboring stations
        for (Edge edge : graph.get(current)) {

            int neighbor = edge.destination;

            if (!visited[neighbor]) {

                dfsUtil(
                        graph,
                        neighbor,
                        destination,
                        visited,
                        path,
                        bestPath,
                        bestResult
                );
            }
        }

        visited[current] = false;
        path.remove(path.size() - 1);
    }


    // Function to find best path using DFS
    static List<Integer> dfs(
            List<List<Edge>> graph,
            int source,
            int destination,
            List<Station> stations,
            int[] result) {

        int numStations = graph.size();

        boolean[] visited = new boolean[numStations];

        List<Integer> path = new ArrayList<>();
        List<Integer> bestPath = new ArrayList<>();

        dfsUtil(
                graph,
                source,
                destination,
                visited,
                path,
                bestPath,
                result
        );

        return bestPath;
    }


    // Function to print path
    static void printPath(
            String title,
            List<Integer> path,
            List<Station> stations,
            int distance,
            int cost) {

        System.out.print(
                title +
                " (Distance: " +
                distance +
                ", Cost: " +
                cost +
                "): "
        );

        for (int i = 0; i < path.size(); i++) {

            System.out.print(
                    stations.get(path.get(i)).name
            );

            if (i != path.size() - 1) {
                System.out.print(" -> ");
            }
        }

        System.out.println();
    }


    public static void main(String[] args) {

        // Number of stations
        int numStations = 7;

        // Create graph
        List<List<Edge>> graph = new ArrayList<>();

        for (int i = 0; i < numStations; i++) {
            graph.add(new ArrayList<>());
        }

        // Create stations
        List<Station> stations = Arrays.asList(

                new Station(0, "Station A", 1),
                new Station(1, "Station B", 1),
                new Station(2, "Station C", 2),
                new Station(3, "Station D", 2),
                new Station(4, "Station E", 3),
                new Station(5, "Station F", 3),
                new Station(6, "Station G", 1)
        );


        // Add edges

        graph.get(0).add(
                new Edge(0, 1, 10, 50)
        );

        graph.get(0).add(
                new Edge(0, 2, 20, 30)
        );

        graph.get(1).add(
                new Edge(1, 2, 5, 10)
        );

        graph.get(1).add(
                new Edge(1, 3, 15, 40)
        );

        graph.get(2).add(
                new Edge(2, 1, 5, 20)
        );

        graph.get(2).add(
                new Edge(2, 3, 10, 25)
        );

        graph.get(2).add(
                new Edge(2, 4, 20, 50)
        );

        graph.get(3).add(
                new Edge(3, 2, 10, 10)
        );

        graph.get(3).add(
                new Edge(3, 5, 20, 30)
        );

        graph.get(4).add(
                new Edge(4, 2, 20, 30)
        );

        graph.get(4).add(
                new Edge(4, 5, 10, 20)
        );

        graph.get(4).add(
                new Edge(4, 6, 5, 10)
        );

        graph.get(5).add(
                new Edge(5, 3, 20, 10)
        );

        graph.get(5).add(
                new Edge(5, 4, 10, 10)
        );

        graph.get(5).add(
                new Edge(5, 6, 15, 20)
        );


        // Source and destination
        int source = 4;
        int destination = 1;


        // -----------------------------
        // Dijkstra
        // -----------------------------

        int[] shortestResult = new int[2];

        List<Integer> shortestPath =
                dijkstra(
                        graph,
                        source,
                        destination,
                        stations,
                        shortestResult
                );

        printPath(
                "Shortest Path",
                shortestPath,
                stations,
                shortestResult[0],
                shortestResult[1]
        );


        // -----------------------------
        // BFS
        // -----------------------------

        int[] cheapestResult = new int[2];

        List<Integer> cheapestPath =
                bfs(
                        graph,
                        source,
                        destination,
                        stations,
                        cheapestResult
                );

        printPath(
                "Cheapest Path",
                cheapestPath,
                stations,
                cheapestResult[0],
                cheapestResult[1]
        );


        // -----------------------------
        // DFS
        // -----------------------------

        int[] bestResult = new int[2];

        List<Integer> bestPath =
                dfs(
                        graph,
                        source,
                        destination,
                        stations,
                        bestResult
                );

        printPath(
                "Best Path",
                bestPath,
                stations,
                bestResult[0],
                bestResult[1]
        );
    }
}
