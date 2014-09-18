<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div>
    <!--detail acheteur -->
    Nom : ${Acheteur.prenomAcheteur} ${Acheteur.nomAcheteur}<br/>

    <!--choix de l'adresse -->
    <h1>Mes adresses :</h1>
    <br/>
    <form action="Controleur" method="POST">
        <input type="hidden" name="section" value="connexion"/>
        <input type="hidden" name="action" value="VoirAdresse"/>
        <select name="adresse" class="adressecompte">   
            <c:forEach items="${Acheteur.listAdresseAcheteur}" var="adresse">
                <option value="${adresse.idAdresse}" 
            <c:if test="${adresse.adresseFav}">
                selected
            </c:if>
            >
            ${adresse.adresseAcheteur1} ${adresse.adresseAcheteur2} ${adresse.cpAcheteur} ${adresse.villeAcheteur} ${adresse.paysAcheteur}
            </option> 
        </c:forEach>
    </select>
    <input type="submit" name="doit" value="confirmer"/>       
    <% //
           //    <c:url value="Controleur?section=commande&action=confirmer" var="url"/>
           //   <a href="${url}">Confirmer la commande</a> %>
</form>
<p>
    <!-- finir le test avec un parametre sinon ca va pas faire ce qui est prevu-->
    <!-- exemple l'id de l'adresse a afficher a mettre en request -->
<c:if test="${not empty lesAdresses}">
    <c:forEach items ="${listAdresseAcheteur}" var="adresse">
        <c:if test="${adresse.idAdresse} == ${lesAdresses}">
    ${adresse.adresseAcheteur1}<br/>
    ${adresse.adresseAcheteur2}<br/>
    ${adresse.cpAcheteur}<br/>
    ${adresse.villeAcheteur}<br/>
    ${adresse.paysAcheteur}<br/>
    </c:if>
</c:forEach>
</c:if>
</p>

</div>
