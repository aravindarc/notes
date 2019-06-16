package aravind.login;

import aravind.author.*;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import tools.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.logging.Logger;

public class LoginAction extends Action {

    Logger logger = LoggerInitiator.getLogger();

    public LoginAction() {
        logger.info("LoginAction.java");
    }

    public ActionForward execute(ActionMapping mapping,
                                 ActionForm form, HttpServletRequest req,
                                 HttpServletResponse res)
            throws Exception
    {
        logger.info("Start execute(" + form + ") . . ." );
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        Author author = new Author();
        author.setUsername(username);
        author.setPassword(password);
        req.setAttribute("AUTHOR", author);

        req.getSession().setAttribute("username", author.getUsername());

        return mapping.findForward("loginSuccess");
    }
}

