<!-- Main page commande-->
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:if test="${not empty maliste}">
<div class="detailcommande">
    <h1 class ="commandetitre">Votre commande</h1>
    <br/>
    <br/>
    
    <c:import url="${affichagecommande}"/>
    
    
    
</c:if>    
</div>

