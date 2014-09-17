<!-- main jsp pour le panier -->
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div>
    <H1>Votre panier :</H1>
        <c:if test="${empty maliste}">
        <p>Votre panier est vide !</p>
    </c:if>
    <c:if test="${not empty maliste}">
        
        <c:import url="${affichagedetailpanier}"/>
        
        <p>
            <br/>
            <br/>
            <c:url value="Controleur?section=commande&action=validercommande" var="url"/>
            <a href="${url}">Validez la commande</a>
        </p>
        
    </c:if>
</div>
