<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:setLocale value="${language}" scope="session"/>
<fmt:setBundle basename="locale" var="rb"/>

<jsp:include page="\component\header.jsp"/>

    <div class="col-sm-8  p-3 mb-2 bg-dark text-white">
      <h1><fmt:message key="adminpage.h1" bundle="${rb}"/> </h1>

      <a href="addheropage.jsp"><fmt:message key="adminpage.createhero" bundle="${rb}"/></a>
      <hr>

      <form class="active" action="/servlet" method="POST">
                      <input type="hidden" name="command" value="SHOW_DELETED_HEROES"/>
                      <button class="btn btn-light" type="submit">
                      <fmt:message key="adminpage.deletedhero" bundle="${rb}"/></button>
      </form>

      <form class="active" action="/servlet" method="POST">
                      <input type="hidden" name="command" value="SHOW_DELETED_COMMENTS"/>
                      <button class="btn btn-light" type="submit">
                      <fmt:message key="adminpage.deletedcomments" bundle="${rb}"/></button>
      </form>

      <form class="active" action="/servlet" method="POST">
                       <input type="hidden" name="command" value="SHOW_BANNED_USERS"/>
                       <button class="btn btn-light" type="submit">
                       <fmt:message key="adminpage.bannedusers" bundle="${rb}"/></button>
      </form>

      <form class="active" action="/servlet" method="POST">
                        <input type="hidden" name="command" value="SHOW_USERS"/>
                        <button class="btn btn-light" type="submit">
                        <fmt:message key="adminpage.activeusers" bundle="${rb}"/></button>
      </form>
    </div>
    <div class="col-sm-2">

    </div>


<jsp:include page="\component\footer.jsp"/>