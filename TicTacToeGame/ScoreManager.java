import java.sql.*;

public class ScoreManager {
    private Connection connection;

    public ScoreManager() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/tictactoe", "root", "password"); // replace with your password
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveScore(String playerName, int score) {
        try {
            String query = "INSERT INTO scores (player, score) VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, playerName);
            preparedStatement.setInt(2, score);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void displayScores() {
        try {
            String query = "SELECT * FROM scores ORDER BY score DESC";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                System.out.println("Player: " + resultSet.getString("player") + " | Score: " + resultSet.getInt("score"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

