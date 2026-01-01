import java.util.*;

public class Maze {
    private int rows;
    private int cols;
    private int[][] grid;
    private final Random rand = new Random();

    public Maze(int level) {
        this.rows = 7 + level * 2;
        this.cols = 7 + level * 2;
        grid = new int[rows][cols];
        generateMaze();
    }

    private void generateMaze() {
        for (int r = 0; r < rows; r++) Arrays.fill(grid[r], 1);
        carvePath(1, 1);
        grid[rows - 2][cols - 2] = 3;
    }

    private void carvePath(int r, int c) {
        int[][] dirs = {{0,-2},{0,2},{-2,0},{2,0}};
        Collections.shuffle(Arrays.asList(dirs));
        grid[r][c] = 0;

        for (int[] d : dirs) {
            int nr = r + d[0], nc = c + d[1];
            if (isValid(nr, nc) && grid[nr][nc] == 1) {
                grid[r + d[0]/2][c + d[1]/2] = 0;
                carvePath(nr, nc);
            }
        }
    }

    private boolean isValid(int r, int c) {
        return r > 0 && r < rows - 1 && c > 0 && c < cols - 1;
    }

    public int getCell(int r, int c) { return grid[r][c]; }
    public int getRows() { return rows; }
    public int getCols() { return cols; }
}


