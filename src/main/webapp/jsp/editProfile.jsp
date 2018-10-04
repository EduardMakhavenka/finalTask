<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:setLocale value="${language}" scope="session"/>
<fmt:setBundle basename="locale" var="rb"/>

<c:set var="idUser" value="${userId}" scope="request"/>
<jsp:include page="\component\header.jsp"/>

    <div class="col-sm-8  p-3 mb-2 bg-dark text-white">
      <form class="active" action="/servlet" method="POST">
        <input class="mt-1 mb-1 col-4" type="text" maxlength ="20" pattern="[A-Za-z-0-9]{0,20}" name="editnicknameuser" placeholder="<fmt:message key="editprofile.nickname" bundle="${rb}"/>">  <br>
        <input class="mt-1 mb-1 col-4" type="text" maxlength ="20" pattern="[A-Za-zА-Яа-я]{0,20}" name="editnameuser" placeholder="<fmt:message key="editprofile.name" bundle="${rb}"/>"/>  <br>
        <input class="mt-1 mb-1 col-4" type="text" maxlength ="20" pattern="[A-Za-zА-Яа-я]{0,20}" name="editsurnameuser" placeholder="<fmt:message key="editprofile.surname" bundle="${rb}"/>"/>  <br>
        <input class="mt-1 mb-1 col-4" type="email" maxlength ="30" name="editmailuser" placeholder="<fmt:message key="editprofile.mail" bundle="${rb}"/>"/>  <br>
        <input class="mt-1 mb-1 col-4" type="text" maxlength ="20" pattern="[0-9-+]{0,20}" name="editphoneuser" placeholder="<fmt:message key="editprofile.phone" bundle="${rb}"/>"/>  <br>
        <input class="mt-1 mb-1 col-4" type="number" maxlength ="20" pattern="[0-9]{0,2}" name="editageuser" placeholder="<fmt:message key="editprofile.age" bundle="${rb}"/>"/>  <br>
        <input class="mt-1 mb-1 col-4" type="text" maxlength ="20" pattern="[A-Za-zА-Яа-я]{0,20}" name="editsexuser" placeholder="<fmt:message key="editprofile.sex" bundle="${rb}"/>"/>  <br>
       <%-- <input class="mt-1 mb-1 col-4" type="digit" maxlength ="4" pattern="[0-9]{0,4}" name="editratinguser" placeholder="<fmt:message key="editprofile.rating" bundle="${rb}"/>"/> <br> --%>
        <input class="mt-1 mb-1 col-4" type="text" maxlength ="20" pattern="[A-Za-zА-Яа-я]{0,20}" name="editfavoriteuser" placeholder="<fmt:message key="editprofile.favorite" bundle="${rb}"/>"/>  <br>
      <hr>
               <input type="hidden" name="idUser" value="${idUser}"/>
               <button class="btn btn-light" type="submit" name="command" value="Edit_profile">
               <fmt:message key="editprofile.save" bundle="${rb}"/></button>
      </form>
    </div>

    <div class="col-sm-2">

    </div>
<jsp:include page="\component\footer.jsp"/>