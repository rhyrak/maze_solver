/*
19050111022, FURKAN DEMİR
20050111011, İBRAHİM BAHÇA
20050111034, MERTER ÇOBAN
20050111008, SELÇUK GENÇAY
21050141038, YOUSIF HARITH SUHAIL SUHAIL
*/

import java.util.List;

public class Maze {
    private final int dimension;
    private final Graph graph;

    public Maze(int[][] horizontalWalls, int[][] verticalWalls, int dimension) {
        this.dimension = dimension;
        this.graph = new Graph(dimension * dimension);

        // fill horizontal edges
        for (int y = 0; y < dimension; ++y) {
            for (int x = 0; x < dimension - 1; ++x) {
                int f = y * dimension + x;
                int t = f + 1;
                if (verticalWalls[f / dimension][t % dimension] == 0) {
                    graph.addEdge(f, t);
                    graph.addEdge(t, f);
                }
            }
        }

        // fill vertical edges
        for (int x = 0; x < dimension; ++x) {
            for (int y = 0; y < dimension - 1; ++y) {
                int f = y * dimension + x;
                int t = f + dimension;
                if (horizontalWalls[t / dimension][f % dimension] == 0) {
                    graph.addEdge(f, t);
                    graph.addEdge(t, f);
                }
            }
        }
    }

    public List<Integer> runBFS() {
        return graph.breadthFirstSearch(0, dimension * dimension - 1);
    }

    public List<Integer> runDFS() {
        return graph.depthFirstSearch(0, dimension * dimension - 1);
    }
}
