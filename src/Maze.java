/*
19050111022, FURKAN DEMİR
20050111011, İBRAHİM BAHÇA
20050111034, MERTER ÇOBAN
20050111008, SELÇUK GENÇAY
21050141038, YOUSIF HARITH SUHAIL SUHAIL
*/

import java.util.List;

public class Maze {
    private final int width;
    private final int height;
    private final Graph graph;

    public Maze(int[][] horizontalWalls, int[][] verticalWalls) {
        this.width = horizontalWalls[0].length;
        this.height = verticalWalls.length;
        this.graph = new Graph(width * height);

        // fill horizontal edges
        for (int y = 0; y < height; ++y) {
            for (int x = 0; x < width - 1; ++x) {
                if (verticalWalls[y][x + 1] == 0) {
                    int f = y * width + x;
                    int t = f + 1;
                    graph.addEdge(f, t);
                    graph.addEdge(t, f);
                }
            }
        }

        // fill vertical edges
        for (int x = 0; x < width; ++x) {
            for (int y = 0; y < height - 1; ++y) {
                if (horizontalWalls[y + 1][x] == 0) {
                    int f = y * width + x;
                    int t = f + width;
                    graph.addEdge(f, t);
                    graph.addEdge(t, f);
                }
            }
        }
    }

    public List<Integer> runBFS() {
        return graph.breadthFirstSearch(0, width * height - 1);
    }

    public List<Integer> runDFS() {
        return graph.depthFirstSearch(0, width * height - 1);
    }
}
