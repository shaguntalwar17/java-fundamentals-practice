public class Player {
    public int row = 1;
    public int col = 1;

    public void move(int dx, int dy, Maze maze) {
        int newRow = row + dy;
        int newCol = col + dx;
        if (maze.getCell(newRow, newCol) != 1) {
            row = newRow;
            col = newCol;
        }
    }
}



