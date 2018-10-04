<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:setLocale value="${language}" scope="session"/>
<fmt:setBundle basename="locale" var="rb"/>

<jsp:include page="\component\header.jsp"/>

    <div class="col-sm-8  p-3 mb-2 bg-dark text-white">
      <h1><fmt:message key="activeusers.h1" bundle="${rb}"/></h1>
       <c:forEach var="List" items="${activeUsers}">
                          <div class="mt-1 p-1 border  border-white rounded well">
                           <fmt:message key="activeusers.name" bundle="${rb}"/> <c:out value="${List.name}"/> <br>
                           <fmt:message key="activeusers.surname" bundle="${rb}"/> <c:out value="${List.surname}"/> <br>
                           <fmt:message key="activeusers.nickname" bundle="${rb}"/> <c:out value="${List.nickName}"/> <br>
                           <fmt:message key="activeusers.rating" bundle="${rb}"/> <c:out value="${List.rating}"/> <br>
                           <hr>
                           <form class="active" action="/servlet" method="POST">
                                     <input type="hidden" name="command" value="BLOCK_ACCOUNT"/>
                                     <button class="btn btn-light" name="idUser" value="${List.id}">
                                     <fmt:message key="activeusers.block" bundle="${rb}"/></button>
                           </form>
                          </div>

       </c:forEach>

    </div>
    <div class="col-sm-2">

    </div>


<jsp:include page="\component\footer.jsp"/>