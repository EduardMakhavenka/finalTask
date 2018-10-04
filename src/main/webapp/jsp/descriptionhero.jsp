<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:setLocale value="${language}" scope="session"/>
<fmt:setBundle basename="locale" var="rb"/>

<c:set var="idhero" value="${heroId}" scope="request"/>
<jsp:include page="\component\header.jsp"/>

    <div class="col-sm-8  p-3 mb-2 bg-dark text-white">
      <h1><c:out value="${name}"/></h1>
      <hr>
      <div id = "photo" class="text-center">
      <img  class=" img-thumbnail rounded"  width="600" height="550" src="../img/${image}.png" ></div>
      <hr>
      <fmt:message key="descrip.herorating" bundle="${rb}"/> <c:out value="${rating}"/><br>
      <fmt:message key="descrip.type" bundle="${rb}"/> <c:out value="${type}"/><br>
      <fmt:message key="descrip.description" bundle="${rb}"/> <c:out value="${description}"/><br>

      <c:if test="${role_status == 2}">
      <a  href="/jsp/editDescription.jsp" /><fmt:message key="descrip.edit" bundle="${rb}"/></a><br>
      </c:if>

      <c:if test ="${role_status == 2}">
      <form class="active" action="/servlet" method="POST">
                   <input type="hidden" name="idhero" value="${idhero}"/>
                   <button class="btn btn-light" type="submit" name="command" value="DELETE_HERO">
                   <fmt:message key="descrip.delete" bundle="${rb}"/></button>
      </form>
      </c:if>

      <hr>

      <c:if test="${role_status != null}">
      <form class="active" action="/servlet" method="POST">
             <input type="hidden" name="idhero" value="${idhero}"/>
             <textarea maxlength ="355" name="comment"></textarea><br><br>
             <button class="btn btn-light" name="command" value="ADD_COMMENT">
             <fmt:message key="descrip.addcomment" bundle="${rb}"/></button>
      </form>
      </c:if>

      <hr>
      <h3><fmt:message key="descrip.comments" bundle="${rb}"/></h3>

      <c:forEach  var="comment" items="${commentshero}">
                          <div  class="m-1 p-1 border  border-white rounded well">
                        <div class="mb-2 mt-2">
                        <fmt:message key="descrip.comment" bundle="${rb}"/>
                        <c:out value="${comment.content}"/><br>
                        </div>
                        <div class="row">
                          <div class="col-6">
                                  <form class="active" action="/servlet" method="POST">
                                        <input type="hidden" name="command" value="SHOW_AUTHOR"/>
                                        <button class="btn-sm btn-light" name="idcomment" value="${comment.id}">
                                        <fmt:message key="descrip.showprofile" bundle="${rb}"/></button>
                                  </form>
                           </div>
                          <div class="col-6">

                          <c:if test="${role_status==2}">
                          <form class="active" action="/servlet" method="POST">
                             <input type="hidden" name="command" value="DELETE_COMMENT"/>
                             <input type="hidden" name="idhero" value="${idhero}"/>
                                <button class="btn-sm btn-light" name="idcomment" value="${comment.id}">
                                 <fmt:message key="descrip.deletecomment" bundle="${rb}"/></button>
                          </form>
                          </c:if>

                          </div>
                        </div>
                          </div>
      </c:forEach>
    </div>
        <div class="col-sm-2 p-3 mb-2 text-white text-center">
            <h3><fmt:message key="descrip.changerating" bundle="${rb}"/></h3>
             <br>
          <form class="active" action="/servlet" method="POST">
           <input type="hidden" name="idhero" value="${idhero}"/>
          <div class="btn-group btn-group-lg">
             <button class="btn btn-success"  class="btn btn-success" name="command" value="UP_HERO_RATING">
              <fmt:message key="descrip.up" bundle="${rb}"/> </button>
             <button class="btn btn-danger"  class="btn btn-danger" name="command" value="DOWN_HERO_RATING">
             <fmt:message key="descrip.down" bundle="${rb}"/></button>
          </div>
          </form>

        </div>


<jsp:include page="\component\footer.jsp"/>