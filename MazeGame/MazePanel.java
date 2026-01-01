import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MazePanel extends JPanel implements KeyListener {
    int level = 1;
    final int maxLevel = 20;
    Maze maze;
    Player player;
    boolean gameOver = false;
    boolean gameCompleted = false;
    int cellSize = 25;
    long startTime;
    double elapsedTime;
    double bestTime = DatabaseManager.getBestTime();

    public MazePanel() {
        setFocusable(true);
        addKeyListener(this);
        startLevel();
    }

    private void startLevel() {
        maze = new Maze(level);
        player = new Player();
         
        int maxDimension = 700;
        int rows = maze.getRows();
        int cols = maze.getCols();
        cellSize = Math.min(maxDimension / rows, maxDimension / cols);

        setPreferredSize(new Dimension(cols * cellSize, rows * cellSize));
        
        revalidate();
        startTime = System.currentTimeMillis();
        gameOver = false;
        gameCompleted = false;
        repaint();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int r = 0; r < maze.getRows(); r++) {
            for (int c = 0; c < maze.getCols(); c++) {
                int cell = maze.getCell(r, c);
                if (cell == 1) g.setColor(Color.BLACK);
                else if (cell == 3) g.setColor(Color.GREEN);
                else g.setColor(Color.WHITE);
                g.fillRect(c * cellSize, r * cellSize, cellSize, cellSize);
            }
        }

        g.setColor(Color.BLUE);
        g.fillOval(player.col * cellSize + 5, player.row * cellSize + 5, 20, 20);

        g.setColor(Color.MAGENTA);
        g.setFont(new Font("Arial", Font.BOLD, 16));
        g.drawString("Level: " + level, 10, 20);

        if (gameOver) {
            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 18));
            if (gameCompleted) {
                g.drawString("🏁 ALL 20 LEVELS COMPLETE!", 30, 50);
                g.drawString("Final Time: " + elapsedTime + " sec", 30, 80);
                g.drawString("Best Time: " + bestTime + " sec", 30, 110);
            } else {
                g.drawString("Level " + level + " Complete!", 30, 50);
            }
        }
    }

    public void keyPressed(KeyEvent e) {
        if (!gameOver) {
            int key = e.getKeyCode();
            if (key == KeyEvent.VK_LEFT) player.move(-1, 0, maze);
            if (key == KeyEvent.VK_RIGHT) player.move(1, 0, maze);
            if (key == KeyEvent.VK_UP) player.move(0, -1, maze);
            if (key == KeyEvent.VK_DOWN) player.move(0, 1, maze);

            if (maze.getCell(player.row, player.col) == 3) {
                elapsedTime = (System.currentTimeMillis() - startTime) / 1000.0;
                gameOver = true;

                if (level == maxLevel) {
                    gameCompleted = true;
                    DatabaseManager.saveIfBest(elapsedTime);
                } else {
                    Timer timer = new Timer(1000, evt -> {
                        level++;
                        startLevel();
                    });
                    timer.setRepeats(false);
                    timer.start();
                }
            }
            repaint();
        }
    }

    public void keyReleased(KeyEvent e) {}
    public void keyTyped(KeyEvent e) {}
}

