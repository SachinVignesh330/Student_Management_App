import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Student Console");
        while (true) {
            System.out.println("1. Add New Student");
            System.out.println("2. See All Students");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Existing Student");
            System.out.println("5. Exit");
            System.out.print("Choose option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    insert_student(scanner);
                    break;
                case 2:
                    select_student();
                    break;
                case 3:
                    update_student(scanner);
                    break;
                case 4:
                    delete_student(scanner);
                    break;
                case 5:
                    System.out.println("Bye....Bye");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Nope!!");
            }
        }
    }



    // Insert
    public static void insert_student(Scanner scanner) {
        try (Connection con = JDBC_Connection.getConnection()) {

            System.out.print("Enter name: ");
            String name = scanner.nextLine();
            System.out.print("Enter age: ");
            int age = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Enter course: ");
            String course = scanner.nextLine();
            System.out.print("Enter email: ");
            String email = scanner.nextLine();

            //SQL statement
            String sql = "INSERT INTO students_info (name, age, course, email) VALUES (?, ?, ?, ?)";
            PreparedStatement pstmt = con.prepareStatement(sql);

            pstmt.setString(1, name);
            pstmt.setInt(2, age);
            pstmt.setString(3, course);
            pstmt.setString(4, email);

            int result = pstmt.executeUpdate();

            if (result > 0) {
                System.out.println("student added");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    // Select
    public static void select_student() {
        try (Connection con = JDBC_Connection.getConnection()){

            Statement stmt = con.createStatement();

            // SQL
            String sql = "SELECT * FROM students_info";
            ResultSet rs = stmt.executeQuery(sql);

            System.out.println("\n--- All Students ---");
            System.out.printf("%-5s %-20s %-5s %-15s %-25s%n", "ID", "Name", "Age", "Course", "Email");
            while (rs.next()) {
                System.out.printf("%-5d %-20s %-5d %-15s %-25s%n",
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getString("course"),
                        rs.getString("email"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    // Update
    public static void update_student(Scanner scanner) {
        try (Connection con = JDBC_Connection.getConnection()) {

            System.out.print("Enter ID: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Enter new name: ");
            String name = scanner.nextLine();

            System.out.print("Enter new age: ");
            int age = scanner.nextInt();

            scanner.nextLine();
            System.out.print("Enter new course: ");
            String course = scanner.nextLine();

            System.out.print("Enter new email: ");
            String email = scanner.nextLine();

            // SQL
            String sql = "UPDATE students_info SET name = ?, age = ?, course = ?, email = ? WHERE id = ?";
            PreparedStatement pstmt = con.prepareStatement(sql);

            pstmt.setString(1, name);
            pstmt.setInt(2, age);
            pstmt.setString(3, course);
            pstmt.setString(4, email);
            pstmt.setInt(5, id);

            int result = pstmt.executeUpdate();

            if (result > 0) {
                System.out.println("student updated!");
            } else {
                System.out.println("student not found!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Delete
    public static void delete_student(Scanner scanner) {
        try (Connection con = JDBC_Connection.getConnection()) {

            System.out.print("Enter ID: ");
            int id = scanner.nextInt();

            //SQL
            String sql = "DELETE FROM students_info WHERE id = ?";
            PreparedStatement pstmt = con.prepareStatement(sql);

            pstmt.setInt(1, id);

            int result = pstmt.executeUpdate();

            if (result > 0) {
                System.out.println("Student deleted");
            } else {
                System.out.println("student not found!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
