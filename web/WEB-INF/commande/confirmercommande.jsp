<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div class="confirmercommande">
    <h1 class="confirmercommandetitre">Confirmation de la commande</h1>
    <br/>
    <br/>
    
    
    <c:import url="${affichagecommande}"/>
    <br/>
    <br/>
    <c:if test="${empty paiement}">
        <c:url value="Controleur?section=commande&action=paiement" var="url"/>
        <a href="${url}">Paiement</a>
        
    </c:if>
        
    <c:if test="${not empty paiement}">
    <p>
        Merci pour votre commande
        
    </p>
     </c:if>
    
    
</div>