<!doctype html>
<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="ctg" uri="/WEB-INF/tld/custom.tld" %>
<fmt:setLocale value="${language}" scope="session"/>
<fmt:setBundle basename="locale" var="rb"/>

<c:set var="local" value="${pageContext.response.locale}"/>

<jsp:include page="\component\header.jsp"/>
    <div class="col-sm-8  p-3 mb-2 bg-dark text-white">
      <div  id="carouselIndicators" class="carousel slide" data-ride="carousel">
        <ol class="carousel-indicators">
          <li data-target="#carouselIndicators" data-slide-to="0" class="active"></li>
          <li data-target="#carouselIndicators" data-slide-to="1"></li>
          <li data-target="#carouselIndicators" data-slide-to="2"></li>
        </ol>
        <div class="carousel-inner">
          <div class="carousel-item active">
            <img class=" rounded d-block h-60 w-100" src="../img/mortal.jpg?auto=yes&bg=777&fg=555&text=First slide" alt="First slide">
          </div>
          <div class="carousel-item">
            <img class=" rounded d-block h-60 w-100" src="../img/marvel.jpg?auto=yes&bg=666&fg=444&text=Second slide" alt="Second slide">
          </div>
          <div class="carousel-item">
            <img class="rounded d-block h-60 w-100" src="../img/Rickbg.jpg?auto=yes&bg=555&fg=333&text=Third slide" alt="Third slide">
          </div>
        </div>
        <a class="carousel-control-prev" href="#carouselIndicators" role="button" data-slide="prev">
          <span class="carousel-control-prev-icon" aria-hidden="true"></span>
          <span class="sr-only">Previous</span>
        </a>
        <a class="carousel-control-next" href="#carouselIndicators" role="button" data-slide="next">
          <span class="carousel-control-next-icon" aria-hidden="true"></span>
          <span class="sr-only">Next</span>
        </a>
      </div>
      <fmt:message key="home.text" bundle="${rb}"/>
       <ctg:greeting role="${role_status}" locale="${local}"  />

    </div>
    <div class="col-sm-2">

    </div>


<jsp:include page="\component\footer.jsp"/>

</html>