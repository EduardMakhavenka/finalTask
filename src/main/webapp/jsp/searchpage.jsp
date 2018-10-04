<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:setLocale value="${language}" scope="session"/>
<fmt:setBundle basename="locale" var="rb"/>

<jsp:include page="\component\header.jsp"/>

    <div class="col-sm-8  p-3 mb-2 bg-dark text-white">
      <h1><fmt:message key="searchpage.h1" bundle="${rb}"/></h1>

      <c:forEach var="searchhero" items="${searchherolist}">
                         <div class="m-1 p-1 text-center border  border-white rounded well" style="background-image: url(../img/bg_search.jpg)">
                   <fmt:message key="profile.heroname" bundle="${rb}"/><h3><c:out value="${searchhero.name}"/></h3>
                         <form class="active" action="/servlet" method="POST">
                                        <input type="hidden" name="command" value="SHOW_DESCRIPTION_HERO"/>
                                        <button class="btn btn-light" name="id" value="${searchhero.id}">
                                        <fmt:message key="mkx.showdescription" bundle="${rb}"/></button>
                         </form>
                     </div>
                   </c:forEach>

    </div>
    <div class="col-sm-2 ">

    </div>


<jsp:include page="\component\footer.jsp"/>