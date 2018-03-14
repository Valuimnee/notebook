<%--
  Author: TsalapovaMD
  Date: 5/28/2017
  Time: 2:33 PM
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=utf-8" language="java" pageEncoding="UTF-8" %>
<nav class="navbar navbar-expand-md navbar-collapse collapse navbar-light bd-color pl-md-4">
    <div class="navbar-collapse container-fluid pr-0">
        <c:if test="${sessionScope.role=='user'}">
            <ul class="navbar-nav  mr-auto pr-0">
                <li class="nav-item dropdown mr-md-2">
                    <form class="form-inline" name="notes" method="post" action="/notes">
                        <button type="submit" name="command" value="locations" class="btn btn-primary">
                        <fmt:message key="menubar.notes"/></button>
                    </form>
                </li>
                <li class="nav-item dropdown mr-md-2">
                    <form class="form-inline" name="take-note" method="post" action="/take-note">
                        <button type="submit" name="command" value="locations" class="btn btn-primary">
                            <fmt:message key="menubar.take-note"/></button>
                    </form>
                </li>
            </ul>
            <div class="nav-item dropdown">
                <button type="button" class="btn btn-primary dropdown-toggle dropdown-toggle-split menu-user" data-toggle="dropdown"
                        aria-haspopup="true" aria-expanded="false">${sessionScope.login}</button>
                <div class="dropdown-menu dropdown-menu-right">
                    <div><form class="form-inline" name="take-note" method="post" action="/account">
                    <button class="dropdown-item" type="submit" name="command" value="user-account">
                        <fmt:message key="menubar.account"/></button></form></div>
                    <div><form class="form-inline" name="take-note" method="post" action="/delete-account">
                        <button class="dropdown-item" type="submit" name="command" value="delete-account">
                            <fmt:message key="menubar.delete-account"/></button></form></div>
                </div>
            </div>
        </c:if>
    </div>
</nav>
