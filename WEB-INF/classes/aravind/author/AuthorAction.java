package aravind.author;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import tools.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.logging.Logger;

public class AuthorAction extends Action {

    Logger logger = LoggerInitiator.getLogger();

    public AuthorAction() {
        logger.info("AuthorAction.java");
    }

    public ActionForward execute(ActionMapping mapping,
                                 ActionForm form, HttpServletRequest req,
                                 HttpServletResponse res)
            throws Exception
    {
        logger.info("Start execute(" + form + ") . . ." );

        return mapping.findForward("authorCreated");
    }
}

