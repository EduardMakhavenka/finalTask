<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:setLocale value="${language}" scope="session"/>
<fmt:setBundle basename="locale" var="rb"/>

<jsp:include page="\component\header.jsp"/>

    <div class="col-sm-8  p-3 mb-2 bg-dark text-white">
      <h1><fmt:message key="banneduser.h1" bundle="${rb}"/> </h1>
       <c:forEach var="bannedList" items="${bannedList}">
                          <div class="mt-1 p-1 border  border-white rounded well">
                          <fmt:message key="banneduser.nickname" bundle="${rb}"/> <c:out value="${bannedList.nickName}"/>
                          <fmt:message key="banneduser.name" bundle="${rb}"/>  <c:out value="${bannedList.name}"/> <br>
                          <fmt:message key="banneduser.surname" bundle="${rb}"/>  <c:out value="${bannedList.surname}"/>
                          <fmt:message key="banneduser.rating" bundle="${rb}"/>   <c:out value="${bannedList.rating}"/><br>
                          <hr>
                          <form class="active" action="/servlet" method="POST">
                                <input type="hidden" name="command" value="Unblock_Account"/>
                                <button class="btn btn-light" name="idBanUser" value="${bannedList.id}">
                                <fmt:message key="banneduser.unblock" bundle="${rb}"/></button>
                          </form>
                          </div>
       </c:forEach>

    </div>
    <div class="col-sm-2">

    </div>


<jsp:include page="\component\footer.jsp"/>