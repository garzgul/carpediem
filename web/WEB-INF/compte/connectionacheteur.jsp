<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<div>
    <h1>Connection</h1>
    <form action="Controleur" method="POST">
        <input type="hidden" name="section" value="connexion"/>
        <input type="hidden" name="action" value="connection"/>
        <label>Email :</label>
        <input type="text" name="email" value="${mailFourni}"/><label class="erreur">${errConnection}</label><br/>
        <label>Mot de passe :</label>
        <input type="password" name="mdp"/><label class="erreur">${errDeMDP}</label><br/>
        <input type="submit" name="connection" value="connection"/>
        
        
    </form> 
</div>


     
    

