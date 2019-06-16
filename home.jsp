<%@ page language="java" %>
<%@ page import="tools.*" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%
    response.setHeader("Cache-Control","no-cache"); //Forces caches to obtain a new copy of the page from the origin server
    response.setHeader("Cache-Control","no-store"); //Directs caches not to store the page under any circumstance
    response.setHeader("Cache-Control","must-revalidate"); //Directs caches not to store the page under any circumstance
    response.setDateHeader("Expires", 0); //Causes the proxy cache to see the page as "stale"
    response.setHeader("Pragma","no-cache"); //HTTP 1.0 backward compatibility

    String username = (String) session.getAttribute("username");
    if(null == username) {
        request.setAttribute("Error", "Session has ended. Please login.");
        RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
        rd.forward(request, response);
    }
%>
<html:html locale="true">
    <head>
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
              background: cornflowerblue;
            }

            .header {background: coral;}
            .footer {background: lightgreen;}
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


        <div class="flex-container">
          <header class="header">Header</header>
          <article class="main">
            <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque sed ex turpis. Cras luctus nibh lectus, in ullamcorper ex tempor eleifend. Nulla bibendum, eros a consequat vestibulum, orci massa fermentum quam, sed commodo nunc ex vitae nisl. Aliquam ullamcorper interdum est nec tincidunt.</p>
          </article>
          <footer class="footer">Footer</footer>
        </div>

        <div class="flex-container">
            <header class="header">Header</header>
            <article class="main">
            <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque sed ex turpis. Cras luctus nibh lectus, in ullamcorper ex tempor eleifend. Nulla bibendum, eros a consequat vestibulum, orci massa fermentum quam, sed commodo nunc ex vitae nisl. Aliquam ullamcorper interdum est nec tincidunt.</p>
            </article>
            <footer class="footer">Footer</footer>
        </div>

        <script>
        if ( window.history.replaceState ) {
          window.history.replaceState( null, null, window.location.href );
        }
        </script>
    </body>
</html:html>