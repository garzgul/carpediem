<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<div class="paiement">
    <h1 class ="titrepaiement">Paiement</h1>
    <br/>
    <br/>
    
    <c:url value="Controleur?section=commande&action=confirmer" var="url"/>
    <a href="${url}">Confirmer la commande</a>
    <c:url value="Controleur?section=defaut" var="url2"/>
    <a href="${url2}">Annuler la commande</a>
    
</div>