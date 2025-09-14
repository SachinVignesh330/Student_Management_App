import java.sql.*;

public class Create_Table {
    public static void main(String[] args) {
        try (Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/student_console", "root", "12102000");
             Statement stmt = con.createStatement()) {
            String sql = "CREATE TABLE students_info (" +
                    "id INT PRIMARY KEY AUTO_INCREMENT," +
                    "name VARCHAR(50)," +
                    "age INT," +
                    "course VARCHAR(50)," +
                    "email VARCHAR(50))" ;



            stmt.executeUpdate(sql);
            System.out.println("Table created successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

