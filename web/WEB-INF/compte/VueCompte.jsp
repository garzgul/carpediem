<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div>
    <h1>Mon compte</h1>
    <form action="Controleur" method="POST">
        <input type="hidden" name="section" value="connexion"/>
        <input type="hidden" name="action" value="voircompte"/>
        <input type="hidden" name="action" value="inscription"/>
        
        <c:if test="${not empty Acheteur}">
            <label>Nom :</label>
            <input type="text" name="nom" value="${Acheteur.nomAcheteur}"/><br/>
            <label>Prenom :</label>
            <input type="text" name="prenom" value="${Acheteur.prenomAcheteur}"/><br/>
            <label>Pseudo :</label>
            <input type="text" name="pseudo" value="${Acheteur.pseudoAcheteur}"/><br/>
            <label>Mot de passe :</label>
            <input type="password" name="mdp" value="${Acheteur.mdpAcheteur}"/><br/>
            <label>Email :</label>
            <input type="text" name="mail" value="${Acheteur.emailAcheteur}"/><br/>
            <label>Téléphone :</label>
            <input type="text" name="tel" value="${Acheteur.telAcheteur}"/><br/>
            <input type="submit" name="voiradresse" value="Voir adresse"/>
            <input type="submit" name="modifiercompte" value="Modifier compte"/>
            <input type="submit" name="supprimercompte" value="Supprimer compte"/>
            
        </c:if>
        
        
    </form>
</div>