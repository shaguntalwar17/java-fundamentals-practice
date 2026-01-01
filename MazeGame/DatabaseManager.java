import java.sql.*;

public class DatabaseManager {
    private static final String URL = "jdbc:mysql://localhost:3306/MazeGameDB";
    private static final String USER = "root"; // adjust if needed
    private static final String PASS = "";

    public static double getBestTime() {
        double best = Double.MAX_VALUE;
        try (Connection con = DriverManager.getConnection(URL, USER, PASS);
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT time_seconds FROM best_time ORDER BY time_seconds ASC LIMIT 1")) {
            if (rs.next()) best = rs.getDouble("time_seconds");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return best;
    }

    public static void saveIfBest(double time) {
        double best = getBestTime();
        if (time < best) {
            try (Connection con = DriverManager.getConnection(URL, USER, PASS);
                 PreparedStatement ps = con.prepareStatement("INSERT INTO best_time(time_seconds) VALUES (?)")) {
                ps.setDouble(1, time);
                ps.executeUpdate();
                System.out.println("🎉 New best time saved: " + time + " sec");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

