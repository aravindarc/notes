package aravind.note;
import java.sql.*;
import java.util.Set;
import java.util.logging.Logger;

import org.apache.struts.action.ActionForm;
import tools.*;

public class Note {

    private String title;
    private String content;
    private String username;
    private static PropertiesSingleton config;
    Logger logger = LoggerInitiator.getLogger();

    public Note() {
        config = PropertiesSingleton.getInstance();
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return this.content;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    private Connection createConnection() throws SQLException {
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

    public void load() {

        String sql = "INSERT INTO notes(title, content, username) "
                + "VALUES('" + title + "', '" + content.replace("'", "\\'") + "', '" + username +"');";

        try {
            logger.info("Attempting to insert into DB using query: " + sql);
            createConnection().createStatement().execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet getNotes(String username) {

        String sql = "SELECT * FROM notes WHERE username = '" + username + "';";
        ResultSet r = null;
        logger.info(sql);

        try {
            r = createConnection().createStatement().executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return r;
    }
}
