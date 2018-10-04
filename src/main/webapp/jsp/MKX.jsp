<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<fmt:setLocale value="${language}" scope="session"/>
<fmt:setBundle basename="locale" var="rb"/>


<jsp:include page="\component\header.jsp"/>

    <div class="col-sm-8  p-3 mb-2 bg-dark text-white">
      <h1><fmt:message key="mkx.h1" bundle="${rb}"/></h1>
       <div>
             <c:forEach var="hero" items="${heroes}">
                    <div class="m-1 p-1 text-center border  border-white rounded well" style="background-image: url(../img/mkx_bg_description.jpg)">

                   <h4> <fmt:message key="mkx.heroname" bundle="${rb}"/> <c:out value="${hero.name}"/></h4>
                    <fmt:message key="mkx.rating" bundle="${rb}"/><c:out value="${hero.rating}"/><br><br>

                    <form class="active" action="/servlet" method="POST">
                       <input type="hidden" name="command" value="SHOW_DESCRIPTION_HERO"/>
                          <button class="btn btn-light" name="id" value="${hero.id}">
                          <fmt:message key="mkx.showdescription" bundle="${rb}"/></button>
                    </form>

               </div>
             </c:forEach>

       </div>
    </div>

    <div class="col-sm-2">
    </div>


<jsp:include page="\component\footer.jsp"/>