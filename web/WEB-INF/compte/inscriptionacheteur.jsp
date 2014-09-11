<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
        <div>
            <h1>Formulaire d'inscription</h1>
            <form action ="Controleur" method="POST">
                <input type="hidden" name="section" value="connexion"/>
                <input type="hidden" name="action" value="inscription"/>
                <label>Nom :</label>
                <input type="text" name="nom" value="${nomFourni}"/><label class="erreur">${errNom}</label><br />
                <label>Prenom :</label>
                <input type="text" name="prenom" value="${prenomFourni}"/><label class="erreur">${errPrenom}</label><br/>
                <label>Pseudo :</label>
                <input type="text" name="pseudo" value="${pseudoFourni}"/><label class="erreur">${errPseudo}</label><br/>
                <label>Email :</label>
                <input type="text" name="email" value="${emailFourni}"/><label class="erreur">${errEmail}</label><br/>
                <label>Téléphone :</label>
                <input type="text" name="tel" value="${telFourni}"/><label class="erreur">${errTel}</label><br/>
                <label>Mot de passe :</label>
                <input type="password" name="mdp" value="${mdpFourni}"/><label class="erreur">${errMDP}</label><br/>
                <label>Confirmer mot de passe :</label>
                <input type="password" name="confirmdp" value="${confirmdpFourni}"/><label class="erreur">${errConfMDP}</label>
                <input type="submit" name="DoIt" value="Créer compte"/>
            </form>            
        </div>
       
