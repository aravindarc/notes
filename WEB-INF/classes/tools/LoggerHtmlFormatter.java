package tools;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;

public class LoggerHtmlFormatter extends Formatter {

    public String format(LogRecord rec) {
        try
        {
            StringBuffer buf = new StringBuffer(1024);

            buf.append("<table border=\"1\">");
            buf.append("<tr>\n");

            if(rec.getLevel().intValue() >= Level.WARNING.intValue()) {

                buf.append("\t<td style=\"color:red\"");
                buf.append("<b>");
                buf.append(rec.getLevel());
                buf.append("</b>");
            }
            else {
                buf.append("\t<td>");
                buf.append(rec.getLevel());
            }

            buf.append("</td>\n");
            buf.append("\t<td>");
            buf.append(getTime(rec.getMillis()));
            buf.append("</td>\n");
            buf.append("\t<td>");
            buf.append(" " + rec.getSourceClassName() + "."
                    + rec.getSourceMethodName() + ": ");
            buf.append("</td>\n");
            buf.append("\t<td>");
            buf.append("<b>" + formatMessage(rec) + "</b>");
            buf.append("</td>\n");
            buf.append("</tr>\n");
            buf.append("</table>");
            return buf.toString();
        }
        catch(Exception ex)
        {
            System.err.println("Error:" + ex);
            return "";
        }
    }

    public String getTime(long time)
    {
        String format = "yyyy-MM-dd HH:mm:ssSS";
        Locale currentLocale = new Locale("DEU", "DEU");
        Date today = new Date(time);
        DateFormat dateFormatter = DateFormat.getDateInstance(
                DateFormat.DEFAULT, currentLocale);
        SimpleDateFormat formatter;
        formatter = new SimpleDateFormat(format, currentLocale);
        return formatter.format(today);
    }
    public String getHead(Handler handler)
    {
        return "<html><head><title>Logging: "
                + getTime(System.currentTimeMillis())
                + "</title></head><body><pre>\n";
    }
    public String getTail(Handler handler)
    {
        return "</pre></body></html>\n";
    }
}

