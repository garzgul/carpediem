<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div>
    <h1>Mon compte</h1>
    <form action="Controleur" method="POST">
        <input type="hidden" name="section" value="connexion"/>
        <!--<c:if test="${not empty Acheteur}"> -->
        <input type="hidden" name="action" value="voircompte"/>
        <!-- </c:if> -->
        <input type="hidden" name="action" value="inscription"/>
      
        <c:if test="${not empty Acheteur}">
            <label>Nom : </label>
            <input type="text" name="nom" value="${Acheteur.nomAcheteur}" disabled="disabled"/><br/>
            <input type="text" name="prenom" value="${Acheteur.prenomAcheteur}" disabled="disabled"/><br/>
            <label>Pseudo :</label>
            <input type="text" name="pseudo" value="${Acheteur.pseudoAcheteur}" disabled="disabled"/><br/>
            <label>Mot de passe :</label>
            <input type="password" name="mdp" value="${Acheteur.mdpAcheteur}" disabled="disabled"/><br/>
            <label>Email :</label>
            <input type="text" name="mail" value="${Acheteur.emailAcheteur}" disabled="disabled"/><br/>
            <label>Téléphone :</label>
            <input type="text" name="tel" value="${Acheteur.telAcheteur}" disabled="disabled"/><br/>
            <input type="submit" name="modifiercompte" value="Modifier compte"/>
            <input type="submit" name="supprimercompte" value="Supprimer compte"/>
            
        </c:if>
            <c:if test="${not empty acheteurModif}">
            <label>Nom : </label>
            <input type="text" name="nom" value="${acheteurModif.nomAcheteur}" /><br/>
            <input type="text" name="prenom" value="${acheteurModif.prenomAcheteur}" /><br/>
            <label>Pseudo :</label>
            <input type="text" name="pseudo" value="${acheteurModif.pseudoAcheteur}" /><br/>
            <label>Mot de passe :</label>
            <input type="password" name="mdp" value="${acheteurModif.mdpAcheteur}" /><br/>
            <label>Email :</label>
            <input type="text" name="mail" value="${acheteurModif.emailAcheteur}" /><br/>
            <label>Téléphone :</label>
            <input type="text" name="tel" value="${acheteurModif.telAcheteur}" /><br/>
            <input type="submit" name="modifiercompte" value="Modifier compte"/>
            <input type="submit" name="supprimercompte" value="Supprimer compte"/>
            
        </c:if>
    </form>
    <p>
       <br/>
       <br/>  
    </p>
    <c:url value="Controleur?section=connexion&action=voiradresse" var="url"/>
    <a href="${url}">Voir adresse</a>
        <c:if test="${not empty Acheteur.listAdresseAcheteur}">
                <c:import url="${vueadresse}"/>
                
        </c:if>
        <c:if test="${empty Acheteur.listAdresseAcheteur}">
            <c:import url="${ajouteradresse}"/>
        </c:if>
        
        
        
    
</div>