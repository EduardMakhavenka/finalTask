<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:setLocale value="${language}" scope="session"/>
<fmt:setBundle basename="locale" var="rb"/>
<html>

<head>
   <meta charset="utf-8">
   <title><fmt:message key="register.title" bundle="${rb}"/></title>
   <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
   <link href="../css/signin.css" rel="stylesheet"/>
   <link href="../css/bootstrap.css" rel="stylesheet"/>
   <!--link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous"-->

    <fmt:message bundle="${rb}" key="register.name" var="field_name"/>
    <fmt:message bundle="${rb}" key="register.surname" var="field_surname"/>
    <fmt:message bundle="${rb}" key="register.login" var="field_login"/>
    <fmt:message bundle="${rb}" key="register.nickname" var="field_nickname"/>
    <fmt:message bundle="${rb}" key="register.email" var="field_email"/>
    <fmt:message bundle="${rb}" key="register.password" var="field_password"/>
    <fmt:message bundle="${rb}" key="register.repeatpassword" var="field_repeatpassword"/>

</head>
     <body class="text-center">

        <div class="form-signin">
        <form  action="/servlet" method="POST">
        <input type="hidden" name="command" value="REGISTRATION"/>
            <h1 class="h3 mb-3 font-weight-normal"><fmt:message key="register.h1" bundle="${rb}"/></h1>
            <div class="row" >
                                    <div class="col">
                                      <input type="text" class="form-control" maxlength ="20" pattern="[A-Za-zА-Яа-я]{1,20}" name="register.name" placeholder="${field_name}" required autofocus >
                                    </div>
                                    <div class="col">
                                      <input type="text" class="form-control" maxlength ="20" pattern="[A-Za-zА-Яа-я]{1,20}" name="register.surname" placeholder="${field_surname}" required >
                                    </div>
            </div>

            <br>

            <input type="email" maxlength ="30" id="inputEmail" name="register.email" class="form-control" placeholder="${field_email}"  required >

             <input type="text" maxlength ="20" pattern="[A-Za-z-0-9]{1,20}" id="inputNickName" name="register.nickname" class="form-control" placeholder="${field_nickname}" required >

            <input type="text" maxlength ="20" pattern="[A-Za-z-0-9]{1,20}" id="inputLogin" name="register.login" class="form-control" placeholder="${field_login}" required >

            <input type="password" maxlength ="20" pattern="[A-Za-z-0-9]{1,20}" id="inputPassword" name="register.password" class="form-control" placeholder="${field_password}" required >

             ${errorRegPassMessage}
              </br>


            <button class="btn btn-lg btn-primary btn-block"  type="submit"><fmt:message key="register.singin" bundle="${rb}"/>
            </button>
            <br>
            </form>

         <!--   <form action="/servlet" method="post">
            <input type="hidden" name="command" value="locale"/>
            <div class="btn-group btn-group-sm" role="group" aria-label="languages">
              <button type="input" class="btn btn-sm btn-primary" name="lang" value="en"><fmt:message key="register.en" bundle="${rb}"/></button>
              <button type="input" class="btn btn-sm btn-primary" name="lang" value="en"><fmt:message key="register.ru" bundle="${rb}"/></button>
              <button type="input" class="btn btn-sm btn-primary" name="lang" value="en"><fmt:message key="register.fr" bundle="${rb}"/></button>
            </div>
            </form> -->
            <br>
            <p class="mb-3 font-weight-normal"><fmt:message key="register.copy" bundle="${rb}"/></p>
       </div>
     </body>
</html>
