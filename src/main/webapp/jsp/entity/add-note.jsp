<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fld" uri="fldlib" %>
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script src="/js/submit.js"></script>
<form class="no-resubmit" name="add-note-form" method="post" action="/add-note">
    <input type="hidden" name="submitted" value="false"/>
    <p class="mb-2 text-center"><label><b><fmt:message key="note.add-greeting"/></b></label></p>
    <c:if test="${requestScope.wrong=='wrong-info'}"><p class="mb-2"><fmt:message key="note.wrong-info"/></p></c:if>
    <fld:input type="product-name" labelType="medium" name="title">
        <jsp:attribute name="label"><fmt:message key="note.title"/></jsp:attribute><jsp:body/>
    </fld:input>
    <div class="mb-2 form-group">
        <label for="content" class="medium-label"><b><fmt:message key="note.content"/>: </b></label>
        <textarea class="form-control" id="content" rows="3" cols="5" name="content" required="required"></textarea>
    </div>
    <div class="mb-lg-2 text-right col-9">
        <button class="btn" type="submit" name="command" value="add-bicycle"><fmt:message key="note.save"/></button>
    </div>
</form>
