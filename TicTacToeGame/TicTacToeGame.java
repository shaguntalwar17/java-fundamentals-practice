import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeGame extends JFrame {
    private Board board;
    private Player player1, player2, currentPlayer;
    private JButton[][] buttons;
    private ScoreManager scoreManager;
    private int score;

    public TicTacToeGame() {
        setTitle("Tic-Tac-Toe Game");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        board = new Board();
        scoreManager = new ScoreManager();

        // Create players
        player1 = new Player("Player 1", 'X');
        player2 = new Player("Player 2", 'O');
        currentPlayer = player1;

        // Create UI components
        buttons = new JButton[3][3];
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 3));

        // Add buttons to grid
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton("-");
                buttons[i][j].setFont(new Font("Arial", Font.PLAIN, 40));
                buttons[i][j].setFocusPainted(false);
                buttons[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JButton source = (JButton) e.getSource();
                        int row = -1, col = -1;
                        for (int i = 0; i < 3; i++) {
                            for (int j = 0; j < 3; j++) {
                                if (buttons[i][j] == source) {
                                    row = i;
                                    col = j;
                                    break;
                                }
                            }
                        }

                        if (board.placeMove(row, col, currentPlayer.getSymbol())) {
                            source.setText(String.valueOf(currentPlayer.getSymbol()));
                            if (board.checkWin(currentPlayer.getSymbol())) {
                                JOptionPane.showMessageDialog(null, currentPlayer.getName() + " wins!");
                                score++;
                                scoreManager.saveScore(currentPlayer.getName(), score);
                                scoreManager.displayScores();
                                resetBoard();
                            } else {
                                currentPlayer = (currentPlayer == player1) ? player2 : player1;
                            }
                        }
                    }
                });
                panel.add(buttons[i][j]);
            }
        }

        add(panel);
    }

    public void resetBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("-");
            }
        }
        board = new Board();
        currentPlayer = player1;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                TicTacToeGame game = new TicTacToeGame();
                game.setVisible(true);
            }
        });
    }
}

