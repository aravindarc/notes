<%@ page language="java" %>
<%@ page import="tools.*" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<html:html locale="true">
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <style>
            body {font-family: Arial, Helvetica, sans-serif; background: #c2c2a3;}
            form {border: 3px solid #f1f1f1; width: 100%; display:inline-block;}

            .content {
              max-width: 500px;
              margin: auto;
              background: white;
              padding: 10px;
            }

            input[type=text], input[type=password] {
                width: 100%;
                padding: 12px 20px;
                margin: 8px 0;
                display: inline-block;
                border: 1px solid #ccc;
                box-sizing: border-box;
            }

            button {
                background-color: #4CAF50;
                color: white;
                padding: 14px 20px;
                margin: 8px 0;
                border: none;
                cursor: pointer;
                width: 100%;
            }

            button:hover {
                opacity: 0.8;
            }

            .cancelbtn {
                width: auto;
                padding: 10px 18px;
                background-color: #F44336;
            }

            .imgcontainer {
                text-align: center;
                margin: 24px 0 12px 0;
            }

            img.avatar {
                width: 40%;
                border-radius: 50%;
            }

            .container {
                padding: 16px;
            }

            span.psw {
                float: right;
                padding-top: 16px;
            }
        </style>

        <title>
            <bean:message key="index.title"/>
        </title>
    </head>

    <body>
        <h2><bean:message key="signup.title"/></h2>
        <div class="content">
            <html:form action="createAuthor" method="POST">
                <div style="color:red">
                    <html:errors></html:errors>
                </div>
                <div class="container">
                    <label for="uname"><b>Username</b></label>
                    <input type="text" placeholder="Enter Username"
                        name="username" required>

                    <label for="psw"><b>Password</b></label>
                        <input type="password" placeholder="Enter Password"
                        name="password" required>

                    <button type="submit">Create Account</button>
                </div>

                <div class="container" style="backgroud-color:#F1F1F1">
                    <button onclick="location.href = 'index.jsp';" type="button" class="cancelbtn">Already have an Account?</button>
                    <span class="psw"><a href="viewlog.jsp">View Logs?</a></span>
                </div>

            </html:form>
        </div>
        <%
        java.util.logging.Logger l = LoggerInitiator.getLogger();
        l.info("signup.jsp accessed");
        %>
    </body>
</html:html>