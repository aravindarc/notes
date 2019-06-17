package aravind.login;

import aravind.author.*;

import org.apache.struts.Globals;
import org.apache.struts.action.*;
import tools.LoggerInitiator;

import javax.servlet.http.HttpServletRequest;
import java.awt.print.Book;
import java.sql.SQLException;
import java.util.logging.Logger;

public class LoginForm extends ActionForm {

    private Author author = new Author();
    private Logger logger =LoggerInitiator.getLogger();

    public void setPassword(String password) {
        logger.info("setPassword(\"" + password + "\")");
        author.setPassword(password);
    }

    public String getPassword() {
        logger.info("getPassword()");
        return author.getPassword();
    }

    public void setUsername(String username) {
        logger.info("setUsername(\"" + username + "\")");
        author.setUsername(username);
    }

    public String getUsername() {
        logger.info("getUsername()");
        return author.getUsername();
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Author getAuthor() {

        return author;
    }

    public void reset(ActionMapping mapping, HttpServletRequest request)
    {
        this.author = new Author();
    }

    public ActionErrors validate(ActionMapping mapping,
                                 HttpServletRequest request)
    {
        ActionErrors errors = new ActionErrors();

        logger.info("in validate");

        if(author.getUsername() == null || author.getPassword() == null) {
            logger.warning("in validate() username or password is null");
            errors.add("null", new ActionError("error.login.null"));
        }
        else {
            if(author.checkIfUsernameIsUnique()) {
                errors.add("not_exists", new ActionError("error.login.not.exists"));
                logger.info("No such username: " + author.getUsername());
            }
            else {
                if (author.matchPassword())
                    logger.info("It's a match for username: " + author.getUsername());
                else {
                    logger.warning("Wrong password for username: " + author.getUsername() + " (" + author.getPassword() + ")");
                    errors.add("password_no_match", new ActionError("error.login.pass.fail"));
                }
            }
        }

        return errors;
    }
}
