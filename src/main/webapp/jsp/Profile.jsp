<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:setLocale value="${language}" scope="session"/>
<fmt:setBundle basename="locale" var="rb"/>


<c:set var="idUser" value="${userId}" scope="request"/>
<jsp:include page="\component\header.jsp"/>

    <div class="col-sm-8  p-3 mb-2 bg-dark text-white">

        <div>
                <div class="mr-2 mb-2 mt-2"><fmt:message key="profile.click" bundle="${rb}"/>
                </div>
                <div>
                      <form class="active" action="/servlet" method="POST">
                               <input type="hidden" name="command" value="show_profile"/>
                                       <button class="btn btn-light">
                                       <fmt:message key="profile.showinfo" bundle="${rb}"/></button>
                      </form>
                </div>
           </div>
         <div class="row">

                <div class="col-6">
                     <table>
                            <c:out value="${photo}"/>
                            <fmt:message key="profile.nickname" bundle="${rb}"/><c:out value="${nickname}"/> <br>
                            <fmt:message key="profile.name" bundle="${rb}"/><c:out value="${nameU}"/> <br>
                            <fmt:message key="profile.surname" bundle="${rb}"/><c:out value="${surname}"/><br>
                     </table>
                </div>

                <div class="col-6">

                            <fmt:message key="profile.mail" bundle="${rb}"/><c:out value="${mail}"/> <br>
                            <fmt:message key="profile.phone" bundle="${rb}"/><c:out value="${phone}"/> <br>
                            <fmt:message key="profile.age" bundle="${rb}"/><c:out value="${age}"/> <br>
                            <fmt:message key="profile.role" bundle="${rb}"/><c:out value="${role}"/> <br>
                            <fmt:message key="profile.sex" bundle="${rb}"/><c:out value="${sex}"/> <br>
                            <fmt:message key="profile.rating" bundle="${rb}"/><c:out value="${ratingU}"/> <br>
                            <fmt:message key="profile.favoritehero" bundle="${rb}"/><c:out value="${favoritehero}"/> <br>
                            <hr>

                            <a  href="/jsp/editProfile.jsp"/><fmt:message key="profile.edit" bundle="${rb}"/></a>

                            <hr>
                          <%--
                            <form class="active" action="/servlet" method="POST">
                                   <input type="hidden" name="idUser" value="${idUser}"/>
                                   <button class="btn btn-light" name="command" value="BLOCK_ACCOUNT">
                                   <fmt:message key="profile.block" bundle="${rb}"/></button> <br>
                            </form> --%>
                          <hr>

                </div>


        </div>
           <div>
               <h4><fmt:message key="profile.h4" bundle="${rb}"/></h4>
               <c:forEach var="commentList" items="${comments}">
                 <div class="mt-1 p-1 border  border-white rounded well">
                  <fmt:message key="profile.heroname" bundle="${rb}"/><c:out value="${commentList.value}"/><br>
                  <fmt:message key="profile.commentscontent" bundle="${rb}"/><c:out value="${commentList.key}"/><br>
                 </div>
               </c:forEach>
           </div>

    </div>

    <div class="col-sm-2">
    <div class="p-3 mb-2 text-white text-center ">

    </div>


<jsp:include page="\component\footer.jsp"/>
