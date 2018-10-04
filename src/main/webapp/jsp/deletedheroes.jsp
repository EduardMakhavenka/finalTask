<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:setLocale value="${language}" scope="session"/>
<fmt:setBundle basename="locale" var="rb"/>

<jsp:include page="\component\header.jsp"/>

    <div class="col-sm-8 p-3 mb-2 bg-dark text-white">
      <h1><fmt:message key="deletedheroes.h1" bundle="${rb}"/></h1>
       <c:forEach var="List" items="${deletedHeroes}">
                          <div class="mt-1 p-1 border  border-white rounded well">

                          <fmt:message key="deletedheroes.id" bundle="${rb}"/> <c:out value="${List.name}"/>
                          <fmt:message key="deletedheroes.rating" bundle="${rb}"/> <c:out value="${List.rating}"/> <br>
                          <fmt:message key="deletedheroes.description" bundle="${rb}"/> <c:out value="${List.description}"/> <br>
                          <fmt:message key="deletedheroes.type" bundle="${rb}"/> <c:out value="${List.type}"/><br>
                           <hr>
                           <form class="active" action="/servlet" method="POST">
                                     <input type="hidden" name="command" value="restore_hero"/>
                                     <button class="btn btn-light" name="idDeletedHero" value="${List.id}">
                                     <fmt:message key="deletedheroes.restore" bundle="${rb}"/></button>
                           </form>
                          </div>

       </c:forEach>

    </div>
    <div class="col-sm-2 ">

    </div>


<jsp:include page="\component\footer.jsp"/>