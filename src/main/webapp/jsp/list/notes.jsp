<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<form class="container text-center pb-5 mt-3" name="bicycles-form" method="post" action="/view-note">
    <input type="hidden" name="command" value="${sessionScope.role=='administrator'?'view-bicycle':'select-bicycle'}"/>
    <c:forEach begin="0" end="${notes.size()/4+(notes.size()%4>0?1:0)-1}" varStatus="loop">
        <div class="row justify-content-start">
            <c:forEach begin="0" end="3" varStatus="element">
                <c:if test="${4*loop.index+element.index lt notes.size()}">
                    <div class="col-sm-3 pl-0 pr-0">
                        <div class="card bicycle-card">
                            <c:set var="note" value="${notes.get(4*loop.index+element.index)}"/>
                            <div class="card-header text-center text">${note.title}</div>
                            <div class="card-body">
                                <p class="text-center text mb-2">${note.content}</p>
                                <button class="btn" type="submit" name="note-id" value="${note.noteId}"
                                        <c:if test="${sessionScope.role!='user'}">hidden</c:if> >
                                    <fmt:message key="note.view"/></button>
                                <c:if test="${sessionScope.role=='administrator'}">
                                    <button class="btn" type="submit" name="note-id" value="${note.noteId}">
                                        <fmt:message key="bicycle.view"/></button>
                                </c:if>
                            </div>
                        </div>
                    </div>
                </c:if>
            </c:forEach>
        </div>
    </c:forEach>
</form>

