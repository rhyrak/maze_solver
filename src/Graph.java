/*
19050111022, FURKAN DEMİR
20050111011, İBRAHİM BAHÇA
20050111034, MERTER ÇOBAN
20050111008, SELÇUK GENÇAY
21050141038, YOUSIF HARITH SUHAIL SUHAIL
*/

import java.util.*;

public class Graph {
    private final int vertexCount;
    private final boolean[][] edges;

    public Graph(int vertexCount) {
        this.vertexCount = vertexCount;
        this.edges = new boolean[vertexCount][vertexCount];
    }
    // check there is edge
    public boolean hasEdge(int from, int to) {
        if (from < 0 || from >= vertexCount || to < 0 || to >= vertexCount) return false;
        return edges[from][to];
    }

    // add edges
    public void addEdge(int from, int to) {
        if (from < 0 || from >= vertexCount || to < 0 || to >= vertexCount) return;
        edges[from][to] = true;
    }

    public void removeEdge(int from, int to) {
        if (from < 0 || from >= vertexCount || to < 0 || to >= vertexCount) return;
        edges[from][to] = false;
    }

    /**
     * @param from Start vertex
     * @param to End vertex
     * {@code breathFirstSearch} do BFS process
     * */
    public List<Integer> breadthFirstSearch(int from, int to) {
        int[] level = new int[vertexCount];
        int[] parent = new int[vertexCount];

        // assign all vertex level -1 and has no parent
        Arrays.fill(level, -1);
        level[from] = 0;
        Arrays.fill(parent, -1);

        List<Integer> path = new ArrayList<>();

        int l = 1;
        // keeps vertexes at one level
        Queue<Integer> frontier = new LinkedList<>();
        frontier.add(from);

        System.out.println("Vertex sequence for BFS: ");
        while (!frontier.isEmpty()) {
            // keep next reachable vertexes
            Queue<Integer> next = new LinkedList<>();

            while (!frontier.isEmpty()) {
                // take the vertex has the smallest number first
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
                            constructPath(i, parent, path);
                            System.out.print(i);
                            return path;
                        }
                    }
                }
            }
            // pass next level
            frontier = next;
            l++;
        }
        return path;
    }

    /**
     * @param from start vertex
     * @param to end vertex
     * {@code depthFirstSeach} starts DFS process
     * */
    public List<Integer> depthFirstSearch(int from, int to) {
        boolean[] visited = new boolean[vertexCount];
        int[] parent = new int[vertexCount];
        Arrays.fill(parent, -1);

        List<Integer> path = new ArrayList<>();

        System.out.println("Vertex sequence for DFS:");
        dfsHelper(from, to, visited, parent, path);

        return path;
    }

    /**
     * @param visited keeps visited nodes
     * @param parent keeps parent of each node
     * @param path keeps path at the end of the function we return path
     * {@code DFShelper} makes DFS process
     * */
    private void dfsHelper(int current, int to, boolean[] visited, int[] parent, List<Integer> path) {
        visited[current] = true;
        System.out.print(current + " ");

        // take the vertex has the smallest number first
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

    /**
     * @param current end vertex
     * @param parent keeps parent of vertexes
     * @param path keeps path
     * {@code constructhPath} crates path
     * */
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