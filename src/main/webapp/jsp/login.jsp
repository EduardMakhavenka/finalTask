<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:setLocale value="${language}" scope="session"/>
<fmt:setBundle basename="locale" var="rb"/>
<html>

<head>
   <title><fmt:message  bundle="${rb}" key="login.title"/></title>
   <meta charset="utf-8">
   <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
   <link href="../css/signin.css" rel="stylesheet"/>
   <link href="../css/bootstrap.css" rel="stylesheet"/>

   <fmt:message bundle="${rb}" key="login.email" var="field_login"/>
   <fmt:message bundle="${rb}" key="login.password" var="field_password"/>

</head>
     <body class="text-center">

        <div class="form-signin">
        <form  action="/servlet" method="POST">
            <input type="hidden" name="command" value="login"/>
            <h1 class="h3 mb-3 font-weight-normal"><fmt:message key="login.h1" bundle="${rb}"/></h1>
            <br>
            <input type="text" name="login.email" maxlength ="20"
            class="form-control"  pattern="[A-Za-z-0-9]{1,20}"
              placeholder="${field_login}" required autofocus/>
            <input type="password" maxlength ="20" pattern="[A-Za-z-0-9]{1,20}"
             name="login.password" class="form-control" placeholder="${field_password}"  required / >


            ${errorLoginPassMessage}
            </br>

            <button class="btn btn-lg btn-primary btn-block" type="submit"><fmt:message key="login.login" bundle="${rb}"/></button>
            <br>
            <p class="h6 mb-3 font-weight-normal"><fmt:message key="login.donthaveanaccount" bundle="${rb}"/></p>
            <p class="mb-3 font-weight-normal" >
            <a href="/jsp/register.jsp"><fmt:message key="login.registernow" bundle="${rb}"/></a></p>
            </form>

            <form action="/servlet" method="post">
            <input type="hidden" name="command" value="locale"/>
            <div class="btn-group btn-group-sm" role="group" aria-label="languages" >
                <button type="input" class="btn btn-sm btn-primary" name="lang" value="en">
                <fmt:message key="login.en" bundle="${rb}"/>
                </button>
                <button type="input" class="btn btn-sm btn-primary" name="lang" value="ru">
                <fmt:message key="login.ru" bundle="${rb}"/>
                </button>
                <button type="input" class="btn btn-sm btn-primary" name="lang" value="fr">
                <fmt:message key="login.fr" bundle="${rb}"/>
                </button>
            </div>
            </form>
            <br>
            <p class="mb-3 font-weight-normal"> <fmt:message key="login.copy" bundle="${rb}"/></p>

         </div>

     </body>
</html>
