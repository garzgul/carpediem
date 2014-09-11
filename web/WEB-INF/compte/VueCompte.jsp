<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div>
    <h1>Mon compte</h1>
    <form action="Controleur" method="POST">
        <input type="hidden" name="section" value="connection"/>
        <input type="hidden" name="action" value="voircompte"/>
        
        <c:if test="${empty ach}">
            
        </c:if>
        
        
    </form>
</div>