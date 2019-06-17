<%@ page language="java" %>
<%@ page import="tools.*" %>
<%@ page import="aravind.note.Note " %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.util.logging.Logger" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%
    String username = (String) session.getAttribute("username");
    if(null == username) {
        request.setAttribute("Error", "Session has ended. Please login.");
        RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
        rd.forward(request, response);
    }
%>
<html:html locale="true">

    <head>
        <meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate" />
        <meta http-equiv="Pragma" content="no-cache" />
        <meta http-equiv="Expires" content="0" />
        <style>
            ul {
              list-style-type: none;
              margin: 0;
              padding: 0;
              overflow: hidden;
              background-color: #333333;
            }

            li {
              float: left;
            }

            li a {
              display: block;
              color: white;
              text-align: center;
              padding: 16px;
              text-decoration: none;
            }

            li a:hover {
              background-color: #111111;
            }

            .flex-container {
              display: -webkit-flex;
              display: flex;
              -webkit-flex-flow: row wrap;
              flex-flow: row wrap;
              font-weight: bold;
              text-align: center;
              margin-top: 20px;
              margin-bottom: 20px;
            }

            .flex-container > * {
              padding: 10px;
              flex: 1 100%;
            }

            .main {
              text-align: left;
              background: darkgray;
            }

            .header {background: silver;}
            .footer {background: gray;}
            .aside {background: moccasin;}

            @media all and (min-width: 768px) {
              .aside { flex: 1 auto; }
              .main    { flex: 3 0px; }
              .aside { order: 1; }
              .main    { order: 2; }
              .footer  { order: 4; }
            }

        </style>
    </head>

    <body>
        <ul>
          <li><a href="home.jsp">Home</a></li>
          <li><a href="new.jsp">New Note</a></li>
          <li><a href="index.jsp">Logout</a></li>
        </ul>

        <%

            ResultSet r = null;
            Logger logger = LoggerInitiator.getLogger();
            try {
                logger.info(session.getAttribute("username").toString());
                r = new Note().getNotes(session.getAttribute("username").toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
            if(r != null)
                while(r.next()) {
                    out.println("<div class=\"flex-container\">");
                    out.println("<header class=\"header\">" + r.getString("title") + "</header>");
                    out.println("<article class=\"main\">");
                    out.println("<p>" + r.getString("content") + "</p>");
                    out.println("</article>");
                    try {
                    out.println("<footer class=\"footer\">" + "Created On: " + r.getDate("created_time") + "\n" + "Edited On: " + r.getDate("edited_time") + "</footer>");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    out.println("</div>");
                }
            else {
                logger.info("r is null");
            }

        %>

        <!--
        <div class="flex-container">
          <header class="header">Header</header>
          <article class="main">
            <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque sed ex turpis. Cras luctus nibh lectus, in ullamcorper ex tempor eleifend. Nulla bibendum, eros a consequat vestibulum, orci massa fermentum quam, sed commodo nunc ex vitae nisl. Aliquam ullamcorper interdum est nec tincidunt.</p>
          </article>
          <footer class="footer">Footer</footer>
        </div>
        -->

        <script>
        if ( window.history.replaceState ) {
          window.history.replaceState( null, null, window.location.href );
        }
        </script>
    </body>
</html:html>