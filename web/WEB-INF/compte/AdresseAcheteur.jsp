<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div>
    <h1>Ajout d'une adresse</h1>
    <form action="Controleur" method="POST">
        <input type="hidden" name="section" value="connexion"/>
        <input type="hidden" name="action" value="ajoutAdresse"/>
        <label>Adresse :</label>
        <input type="text" name="adresse" value="${adresseFourni}"/>
        <label>Complément d'adresse :</label>
        <input type="text" name="complement" value="${complementFourni}"/>
        <label>Code postal :</label>
        <input type="text" name="cp" value="${cpFourni}"/>
        <label>Ville :</label>
        <input type="text" name="ville" value="${villeFourni}"/>
        <label>Pays :</label>
        <input type="text" name="pays" value="${paysFourni}"/><br/>
        
        <select name="select" onchange="updated(this)">
            <option value="1">Favori</option>
            <option value="2">Non favori</option>
        </select><br/>
        <input type="submit" name="valider" value="Créer adresse"/>


    </form>
</div>