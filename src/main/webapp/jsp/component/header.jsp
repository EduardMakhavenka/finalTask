
<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:setLocale value="${language}" scope="session"/>
<fmt:setBundle basename="locale" var="rb"/>

<c:set var="local" value="${pageContext.response.locale}"/>
<html>

<head>
<title> <fmt:message key="header.title" bundle="${rb}"/></title>
<meta charset="UTF-8">
<style>
body{background-image: url(/img/mainbg.jpg);
background-attachment:fixed;}

</style>
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
<fmt:message bundle="${rb}" key="header.Search" var="header_Search"/>
</head>

<body>

   <div >
     <div ><nav class="navbar navbar-expand-lg navbar-dark bg-dark">
                        <a  href="/jsp/Home.jsp"><img  class="mr-2 img-thumbnail rounded-circle "  width="38" height="38" src="../img/logo.png" ></a>
                        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo02" aria-controls="navbarTogglerDemo02" aria-expanded="false" aria-label="Toggle navigation">
                          <span class="navbar-toggler-icon"></span>
                        </button>

                        <div class="collapse navbar-collapse" id="navbarTogglerDemo02">
                          <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
                            <li class="nav-item active">
                              <a class="nav-link" href="/jsp/Home.jsp"><fmt:message key="header.Home" bundle="${rb}"/>
                              <span class="sr-only">(current)</span></a>
                            </li>

                            <c:if test="${role_status!=null}">
                            <li class="nav-item">
                              <a class="nav-link" href="/jsp/Profile.jsp"><fmt:message key="header.Profile" bundle="${rb}"/></a>
                            </li>
                            </c:if>

                            <li class="nav-item">
                             <a class="nav-link" href="/jsp/About.jsp"><fmt:message key="header.About" bundle="${rb}"/></a>
                            </li>
                            <li class="nav-item">
                             <a class="nav-link" href="/jsp/Contact.jsp"><fmt:message key="header.Contact" bundle="${rb}"/></a>
                            </li>

                            <c:if test="${role_status == 2}">
                            <li class="nav-item">
                             <a class="nav-link" href="/jsp/adminpage.jsp"><fmt:message key="header.Adminpage" bundle="${rb}"/>
                             </a>
                            </li>
                            </c:if>

                          </ul>
                          <form class="form-inline my-2 my-lg-0"  action="/servlet" method="POST">
                            <input class="form-control mr-sm-2" maxlength ="20"  type="search" name="searchHero" placeholder="${header_Search}">
                            <button class="mr-3 btn btn-outline-success my-2 my-sm-0" name="command" value="SEARCH_HERO" type="submit">
                            <fmt:message key="header.Search" bundle="${rb}"/></button>
                          </form>
                          <ul class="nav navbar-nav navbar-right">
                                  <li class="navbar-brand"><c:out value="${usernickname}"/></li>

                                  <c:if test="${role_status == null}">
                                  <li><a class="nav-link" href="/jsp/login.jsp"><fmt:message key="header.Login" bundle="${rb}"/>
                                  </a></li>
                                  </c:if>

                                  <c:if test="${role_status !=null}">
                                  <li ><form class="form-inline my-2 my-lg-0" action="/servlet" method="POST">
                                  <input type="hidden" name="mainLocale" value="${local}"/>
                                  <button class="btn  btn-light btn-m" name="command" value="LOGOUT">
                                  <fmt:message key="header.Logout" bundle="${rb}"/></button>
                                  </form>
                                  </li>
                                  </c:if>

                          </ul>
                        </div>
                      </nav>
                      </div>
     <div class="container-fluid">
     <div class="row">

        <div class="col-sm-2 mt-3">
                <form class="active" action="/servlet" method="POST">
                               <div class="p-2 m-2 btn-group-vertical">

                                         <button class="btn btn-light btn-lg" name="command" value="SHOW_MKX_CONTENT">
                                         <fmt:message key="header.MKX" bundle="${rb}"/>
                                         </button>

                                         <button class="btn btn-light btn-lg" name="command" value="SHOW_MARVEL_CONTENT">
                                         <fmt:message key="header.Marvel" bundle="${rb}"/>
                                         </button>

                                         <button class="btn btn-light btn-lg" name="command" value="SHOW_RICK_AND_MORTY_CONTENT">
                                         <fmt:message key="header.Rick" bundle="${rb}"/>
                                         </button>
                                </div>
                              </form>
        </div>