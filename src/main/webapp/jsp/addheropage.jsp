<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:setLocale value="${language}" scope="session"/>
<fmt:setBundle basename="locale" var="rb"/>

<jsp:include page="\component\header.jsp"/>

    <div class="col-sm-8  p-3 mb-2 bg-dark text-white">
      <h1 class="p-2 mb-1"><fmt:message key="addhero.h1" bundle="${rb}"/></h1>
      <div class="mb-2">
       <form class="active" action="/servlet" method="POST">
             <input class="m-1 p-1" type="text" maxlength ="20" pattern="[A-Za-zА-Яа-я-0-9]{1,20}"  name="addNameHero" placeholder="<fmt:message key="addhero.name" bundle="${rb}" />" required autofocus />  <br>
             <input class="m-1 p-1" type="text" maxlength ="4" pattern="[0-9]{1,4}" name="addRatingHero" placeholder="<fmt:message key="addhero.rating" bundle="${rb}"/>" required />  <br>
             <input class="m-1 p-1" type="text" maxlength ="20" pattern="[A-Za-zА-Яа-я]{1,20}" name="addTypeHero" placeholder="<fmt:message key="addhero.type" bundle="${rb}"/>" required/>   <br>
              <fmt:message key="addhero.description" bundle="${rb}"/><br><textarea maxlength ="2000" class="m-1 p-1" type="text" name="addDescriptionHero" required></textarea>
            <hr>
              <button class="btn btn-light" type="submit" name="command" value="Add_hero">
              <fmt:message key="addhero.add" bundle="${rb}"/></button>
       </form>
       </div>
       <hr>
     <hr>
    </div>
    <div class="col-sm-2 ">

    </div>


<jsp:include page="\component\footer.jsp"/>