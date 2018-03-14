<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fld" uri="fldlib" %>
<div class="mt-1 note-div">
    <p class="mb-2"><b><label class="container-fluid text-center"><fmt:message key="note.edit-greeting"/></label></b></p>
    <form class="mb-0" name="edit-note" method="post" action="/edit-note">
        <input type="hidden" name="note-id" value="${note.noteId}">
        <fld:input type="product-name" labelType="long" name="title">
        <jsp:attribute name="label"><fmt:message key="note.title"/></jsp:attribute><jsp:body>${note.title}</jsp:body>
    </fld:input>
    <div class="mb-2 form-group">
        <label for="content" class="medium-label"><b><fmt:message key="note.content"/>: </b></label>
        <textarea class="form-control" id="content" rows="3" cols="5" name="content" required="required"
                  >${note.content}</textarea>
    </div>
        <div class="text-center"><button class="btn mt-2 text-center" type="submit" name="command" value="edit-rental">
            <fmt:message key="note.save-changes"/></button></div></form>
    <div class="text-center mt-2">
        <form class="form-inline d-inline" name="delete-note" method="post" action="/delete-note">
            <input type="hidden" name="note-id" value="${note.noteId}">
            <button class="btn mr-5" type="submit" name="command" value="cancel-rental">
                <fmt:message key="note.delete"/></button></form>
    </div>
</div>