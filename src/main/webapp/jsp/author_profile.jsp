<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:setLocale value="${language}" scope="session"/>
<fmt:setBundle basename="locale" var="rb"/>


<c:set var="idUser" value="${userId}" scope="request"/>
<jsp:include page="\component\header.jsp"/>

    <div class="col-sm-8  p-3 mb-2 bg-dark text-white">
         <div class="row">

                <div class="col-6">
                     <table>
                            <c:out value="${photo_author}"/>
                            <fmt:message key="profile.nickname" bundle="${rb}"/><c:out value="${nickname_author}"/> <br>
                            <fmt:message key="profile.name" bundle="${rb}"/><c:out value="${name_author}"/> <br>
                            <fmt:message key="profile.surname" bundle="${rb}"/><c:out value="${surname_author}"/><br>
                     </table>
                </div>

                <div class="col-6">

                            <fmt:message key="profile.mail" bundle="${rb}"/><c:out value="${mail_author}"/> <br>
                            <fmt:message key="profile.phone" bundle="${rb}"/><c:out value="${phone_author}"/> <br>
                            <fmt:message key="profile.age" bundle="${rb}"/><c:out value="${age_author}"/> <br>
                            <fmt:message key="profile.role" bundle="${rb}"/><c:out value="${role_author}"/> <br>
                            <fmt:message key="profile.sex" bundle="${rb}"/><c:out value="${sex_author}"/> <br>
                            <fmt:message key="profile.rating" bundle="${rb}"/><c:out value="${rating_author}"/> <br>
                            <fmt:message key="profile.favoritehero" bundle="${rb}"/><c:out value="${favoritehero_author}"/> <br>
                            <hr>
                                  <c:if test="${role_status == 2}">
                                  <form class="active" action="/servlet" method="POST">
                                           <input type="hidden" name="idUser" value="${idUser}"/>
                                           <button class="btn btn-light" name="command" value="BLOCK_ACCOUNT">
                                           <fmt:message key="profile.block" bundle="${rb}"/></button> <br>
                                  </form>
                                  </c:if>
                </div>
        </div>
    </div>

    <div class="col-sm-2">

    <c:if test="${role_status > 0}">
    <div class="p-3 mb-2 text-white text-center ">
       <h3><fmt:message key="profile.changeuser" bundle="${rb}"/></h3>
       <br>

       <form class="active" action="/servlet" method="POST">
       <input type="hidden" name="idUser" value="${idUser}"/>
       <div class="btn-group btn-group-lg">
        <button  class="btn btn-success"  name="command" value="UP_USER_RATING">
         <fmt:message key="profile.up" bundle="${rb}"/></button>
        <button  class="btn btn-danger"  name="command" value="DOWN_USER_RATING">
        <fmt:message key="profile.down" bundle="${rb}"/></button>
       </div>
       </form>
    </div>
    </c:if>

    </div>


<jsp:include page="\component\footer.jsp"/>
