import java.sql.Connection;
import java.sql.DriverManager;

public class JDBC_Connection {
    // Method to establish and return a DB connection
    public static Connection getConnection() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/student_console", "root", "12102000");
    }
}
