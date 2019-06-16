package temp;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBTest {

    public static void main(String args[]) throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/notes", "root", "root@123");
        connection.createStatement().execute("INSERT INTO author(username, password) VALUES('Aravinda Kumar', 'Scoutfinch123');");
    }
}
