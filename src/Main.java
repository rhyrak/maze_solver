/*
19050111022, FURKAN DEMİR
20050111011, İBRAHİM BAHÇA
20050111034, MERTER ÇOBAN
20050111008, SELÇUK GENÇAY
21050141038, YOUSIF HARITH SUHAIL SUHAIL
*/

import java.util.List;

public class Main {
    public static void main(String[] args) {
        int caseNum = 0;
        for (TestCase testCase : TestCase.getTestCases()) {
            System.out.println("--- Test Case #" + caseNum++ + " ---");
            Maze maze = new Maze(testCase.getHorizontalWalls(), testCase.getVerticalWalls());
            int width = testCase.getWidth();

            List<Integer> bfsPath = maze.runBFS();
            System.out.println("\nPath for BFS:");
            if (bfsPath.isEmpty()) {
                System.out.println("*** No solution ***");
            } else {
                printPath(bfsPath, width);
                System.out.println();
            }

            List<Integer> dfsPath = maze.runDFS();
            System.out.println("\nPath for DFS:");
            if (dfsPath.isEmpty()) {
                System.out.println("*** No solution ***");
            } else {
                printPath(dfsPath, width);
                System.out.println();
            }
        }
    }

    private static void printPath(List<Integer> path, int width) {
        for (int i = 0; i < path.size(); ++i) {
            int col = path.get(i) % width;
            int row = path.get(i) / width;
            System.out.print("(" + row + ", " + col + ")");
            if (i == path.size() - 1) {
                System.out.println();
            } else {
                System.out.print(" -> ");
            }
        }
    }
}