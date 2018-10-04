<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:setLocale value="${language}" scope="session"/>
<fmt:setBundle basename="locale" var="rb"/>

<jsp:include page="\component\header.jsp"/>

    <div class="col-sm-8  p-3 mb-2 bg-dark text-white">
      <h1><fmt:message key="deletedcomments.h1" bundle="${rb}"/></h1>
       <c:forEach var="List" items="${deletedComments}">
                          <div class="mt-1 p-1 border  border-white rounded well">
                          <fmt:message key="deletedcomments.id" bundle="${rb}"/>  <c:out value="${List.id}"/>
                          <fmt:message key="deletedcomments.content" bundle="${rb}"/>  <c:out value="${List.content}"/><br>
                          <fmt:message key="deletedcomments.user" bundle="${rb}"/>  <c:out value="${List.userId}"/>
                          <fmt:message key="deletedcomments.hero" bundle="${rb}"/>  <c:out value="${List.heroId}"/><br>
                          </div>
       </c:forEach>

    </div>
    <div class="col-sm-2 ">

    </div>


<jsp:include page="\component\footer.jsp"/>