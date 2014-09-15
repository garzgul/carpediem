<!-- Main page commande-->
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:if test="${not empty maliste}">
<div class="detailcommande">
    <h1 class ="commandetitre">Votre commande</h1>
    
    <c:import url="${affichagecommande}"/>
    
    
    
    
</div>
</c:if>
<div class="totalcommande">
    <c:url value="Controleur?section=commande&action=confirmer" var="url"/>
    <a href="${url}">Confirmer la commande</a>
</div>

