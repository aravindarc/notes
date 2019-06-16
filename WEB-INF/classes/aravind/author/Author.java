package aravind.author;
import java.sql.*;
import java.util.logging.Logger;

import org.apache.struts.action.ActionForm;
import tools.*;

public class Author {

    private String username;
    private String password;
    private static PropertiesSingleton config;
    Logger logger = LoggerInitiator.getLogger();

    public Author() {
        config = PropertiesSingleton.getInstance();
    }

    public Author(String username) throws SQLException {
        this();
        this.username = username;
        load();
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    private Connection createConnection() throws SQLException {
        if(username.length() == 0)
            throw new SQLException("Please provide username");
        try {
            Class.forName(config.getProperty("db.db"));
        } catch (ClassNotFoundException e) {
            throw new SQLException("Could not load DB Driver!");
        }

        logger.info("Attempting to create connection with: " + config.getProperty("db.driver") + ", " + config.getProperty("db.user") + ", " + config.getProperty("db.password"));
        Connection connection = DriverManager.getConnection(
                config.getProperty("db.driver"),
                config.getProperty("db.user"),
                config.getProperty("db.password")
        );

        return connection;
    }

    public void contruct() {

        String sql = "INSERT INTO author(username, password) "
                + "VALUES('" + username + "', '" + password + "');";
        try {
            logger.info("Attempting to insert into DB using query: " + sql);
            createConnection().createStatement().execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void load() throws SQLException {

        String sql = "SELECT * FROM author WHERE username = '" + username + "';";

        ResultSet r = createConnection().createStatement().executeQuery(sql);

        if(r.next()) {
            password = r.getString("password");
        }
        else {
            throw new SQLException("No user with username=" + username + " found!");
        }
    }

    public boolean checkIfUsernameIsUnique() {

        String sql = "SELECT * FROM author WHERE username = '" + username + "';";


        try {
            ResultSet r = createConnection().createStatement().executeQuery(sql);

            if (r.next()) {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }

    public boolean matchPassword() {

        String sql = "SELECT * FROM author WHERE username = '" + username + "';";

        logger.info("sql query used: " + sql);

        try {
            ResultSet r = createConnection().createStatement().executeQuery(sql);

            if (r.next()) {

                String dbPassword = r.getString("password");

                logger.warning("dbPassword: " + dbPassword + ", " + "password: " + password);
                if(dbPassword.equals(password))
                    return true;
                else
                    return false;
            }
            else
                logger.warning("no username entry");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
