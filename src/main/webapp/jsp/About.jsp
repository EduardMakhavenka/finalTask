<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:setLocale value="${language}" scope="session"/>
<fmt:setBundle basename="locale" var="rb"/>

<jsp:include page="\component\header.jsp"/>

    <div class="col-sm-8  p-3 mb-2 bg-dark text-white">
       <fmt:message key="about.text" bundle="${rb}"/>

      <hr>

    </div>
    <div class="col-sm-2 ">
    </div>


<jsp:include page="\component\footer.jsp"/>