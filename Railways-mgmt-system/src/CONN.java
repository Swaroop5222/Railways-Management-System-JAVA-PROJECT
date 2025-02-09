import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class  CONN {
    private static final String URL = "jdbc:mysql:///RailwaySystem";
    private static final String USER = "root";
    private static final String PASS = "Swaroop@5222";
    private Connection c;
    public Statement s;

    public CONN() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            c = DriverManager.getConnection(URL, USER, PASS);
            s = c.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}
