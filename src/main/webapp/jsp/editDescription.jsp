<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:setLocale value="${language}" scope="session"/>
<fmt:setBundle basename="locale" var="rb"/>

<c:set var="idhero" value="${heroId}" scope="request"/>
<jsp:include page="\component\header.jsp"/>

    <div class="col-sm-8  p-3 mb-2 bg-dark text-white">
      <form class="active" action="/servlet" method="POST">
        <input class="m-1 p-1" type="text" maxlength ="20" pattern="[A-Za-zА-Яа-я-0-9]{0,20}" name="editNameHero" placeholder="<fmt:message key="editdescrip.name" bundle="${rb}"/>"/>  <br>
        <input class="m-1 p-1" type="text" maxlength ="4" pattern="[0-9]{0,4}" name="editRatingHero" placeholder="<fmt:message key="editdescrip.rating" bundle="${rb}"/>"/>  <br>
        <input class="m-1 p-1" type="text" maxlength ="20" pattern="[A-Za-zА-Яа-я]{0,20}" name="editTypeHero" placeholder="<fmt:message key="editdescrip.type" bundle="${rb}"/>"/>  <br>
        <textarea  maxlength ="2000"  class="m-1 p-1" type="text" name="editDescriptionHero" />
        <fmt:message key="editdescrip.description" bundle="${rb}" />
        </textarea> <br>
      <hr>
               <input type="hidden" name="idhero" value="${idhero}"/>
               <button class="btn btn-light" type="submit" name="command" value="Edit_hero">
               <fmt:message key="editdescrip.save" bundle="${rb}"/></button>
      </form>
      <hr>
      <div class="p-3 mb-2 bg-dark text-white">
            <h3><fmt:message key="addhero.addimage" bundle="${rb}"/></h3>
             <form action="${request.getContextPath}/uploadImage" method="post" enctype="multipart/form-data">
              <div class="custom-file">
              <input type = "file"  name ="image" class="custom-file-input text-dark" id="customFile"/>
              <input type="hidden" name="idhero" value="${idhero}"/>
              <label class="custom-file-label" for="customFile"></label>
              <button type ="submit" id="enterInfo"  class="btn mt-2 btn-light">
              <fmt:message key="addhero.addimagebutton" bundle="${rb}"/></button>
              </div>
             </form>
            </div>
            <br>
    </div>

    <div class="col-sm-2 ">

    </div>
<jsp:include page="\component\footer.jsp"/>