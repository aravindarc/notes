package aravind.note;

import aravind.author.*;

import org.apache.struts.Globals;
import org.apache.struts.action.*;
import tools.LoggerInitiator;

import javax.servlet.http.HttpServletRequest;
import java.awt.print.Book;
import java.sql.SQLException;
import java.util.logging.Logger;

public class NoteForm extends ActionForm {

    private Note note = new Note();
    private Logger logger =LoggerInitiator.getLogger();

    public void setTitle(String title) {
        logger.info("setTitle(\"" + title + "\")");
        note.setTitle(title);
    }

    public String getTitle() {
        logger.info("getTitle()");
        return note.getTitle();
    }

    public void setContent(String content) {
        logger.info("setContent(\"" + content + "\")");
        note.setContent(content);
    }

    public String getUsername() {
        logger.info("getUsername()");
        return note.getUsername();
    }

    public void setNote(Note note) {
        this.note = note;
    }

    public Note getNote() {

        return note;
    }

    public void reset(ActionMapping mapping, HttpServletRequest request)
    {
        this.note = new Note();
    }

    public ActionErrors validate(ActionMapping mapping,
                                 HttpServletRequest request)
    {
        ActionErrors errors = new ActionErrors();

        logger.info("in validate");

        if(note.getTitle() == null || note.getContent() == null) {
            logger.warning("in validate() title or content is null");
            errors.add("null", new ActionError("error.new.null"));
        }
        else {
            note.setUsername(request.getSession().getAttribute("username").toString());

            note.load();
        }

        return errors;
    }
}
