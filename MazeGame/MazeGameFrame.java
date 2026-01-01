import javax.swing.*;

public class MazeGameFrame {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Maze Game - Level Up!");
        MazePanel panel = new MazePanel();
        frame.add(panel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}

