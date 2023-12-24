import java.util.*;

public class Graph {
    private int vertexCount;
    private boolean[][] edges;

    public Graph(int vertexCount) {
        this.vertexCount = vertexCount;
        this.edges = new boolean[vertexCount][vertexCount];
    }

    public boolean hasEdge(int from, int to) {
        if (from < 0 || from >= vertexCount || to < 0 || to >= vertexCount) return false;
        return edges[from][to];
    }

    public void addEdge(int from, int to) {
        if (from < 0 || from >= vertexCount || to < 0 || to >= vertexCount) return;
        edges[from][to] = true;
    }

    public void removeEdge(int from, int to) {
        if (from < 0 || from >= vertexCount || to < 0 || to >= vertexCount) return;
        edges[from][to] = false;
    }

    public List<Integer> breadthFirstSearch(int from, int to) {
        int[] level = new int[vertexCount];
        int[] parent = new int[vertexCount];
        Arrays.fill(level, -1);
        level[from] = 0;
        Arrays.fill(parent, -1);

        List<Integer> path = new ArrayList<>();

        int l = 1;
        Queue<Integer> frontier = new LinkedList<>();
        frontier.add(from);

        System.out.println("Vertex sequence for BFS: ");
        while (!frontier.isEmpty()) {
            Queue<Integer> next = new LinkedList<>();

            if (false) {
                System.out.println("Frontier #" + l + ": " + frontier);
            }

            while (!frontier.isEmpty()) {
                int u = frontier.poll();
                System.out.print(u + " ");
                for (int i = 0; i < vertexCount; ++i) {
                    if (hasEdge(u, i)) {
                        if (level[i] == -1) {
                            level[i] = l;
                            parent[i] = u;
                            next.add(i);
                        }
                        if (i == to) {
                            Stack<Integer> tempPath = new Stack<>();
                            tempPath.push(i);
                            int iter = parent[i];
                            while (iter != -1) {
                                tempPath.push(iter);
                                iter = parent[iter];
                            }
                            while (!tempPath.isEmpty()) {
                                path.add(tempPath.pop());
                            }
                            return path;
                        }
                    }
                }
            }
            frontier = next;
            l++;
        }
        return path;
    }

    public List<Integer> depthFirstSearch(int from, int to) {
        boolean[] visited = new boolean[vertexCount];
        int[] parent = new int[vertexCount];
        Arrays.fill(parent, -1);

        List<Integer> path = new ArrayList<>();

        System.out.println("\nVertex sequence for DFS:");
        dfsHelper(from, to, visited, parent, path);

        return path;
    }

    private void dfsHelper(int current, int to, boolean[] visited, int[] parent, List<Integer> path) {
        visited[current] = true;
        System.out.print(current + " ");

        for (int i = 0; i < vertexCount; ++i) {
            if (hasEdge(current, i) && !visited[i]) {
                parent[i] = current;
                if (i == to) {
                    constructPath(i, parent, path);
                    return;
                }
                dfsHelper(i, to, visited, parent, path);
            }
        }
    }

    private void constructPath(int current, int[] parent, List<Integer> path) {
        Stack<Integer> tempPath = new Stack<>();
        tempPath.push(current);

        int iter = parent[current];
        while (iter != -1) {
            tempPath.push(iter);
            iter = parent[iter];
        }

        while (!tempPath.isEmpty()) {
            path.add(tempPath.pop());
        }
    }
}

