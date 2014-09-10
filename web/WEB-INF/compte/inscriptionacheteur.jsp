<%@page contentType="text/html" pageEncoding="UTF-8"%>
        <div>
            <h1>Formulaire d'inscription</h1>
            <form action ="Controleur" method="POST">
                <input type="hidden" name="section" value="inscription"/>
                <label>Nom :</label>
                <input type="text" name="nom" value="${nomFourni}"/><label class="erreur">${errNom}</label><br />
                <label>Prenom :</label>
                <input type="text" name="prenom" value="${prenomFourni}"/><label class="erreur">${errPrenom}</label><br/>
                <label>Pseudo :</label>
<<<<<<< HEAD
                <input type="text" name="pseudo" value="${pseudoFourni}"/><label class="erreur">${errPseudo}</label><br/>
=======
                <input type="text" name="pseudo" value="${pseudoFourni}"/><label class="erreurs">${errPseudo}</label><br/>
>>>>>>> master
                <label>Email :</label>
                <input type="email" name="email" value="${emailFourni}"/><label class="erreur">${errEmail}</label><br/>
                <label>Téléphone :</label>
<<<<<<< HEAD
                <input type="tel" name="tel" value="${telFourni}"/><label class="erreur">${errTel}</label><br/>
                <label>Mot de passe :</label>
                <input type="password" name="mdp" value="${mdpFourni}"/><label class="erreur">${errMDP}</label><br/>
                <label>Confirmer mot de passe :</label>
                <input type="password" name="confirmdp" value="${confirmdpFourni}"/><label class="erreur">${errConfMDP}</label><br/>
                <input type="submit" name="DoIt" value="Créer mon compte"/>
=======
                <input type="tel" name="tele" value="${telFourni}"/><label class="erreurs">${errTel}</label><br/>
                <label>Mot de passe :</label>
                <input type="password" name="mdp" value="${mdpFourni}"/><label class="erreurs">${errMDP}</label><br/>
                <label>Confirmer mot de passe :</label>
                <input type="password" name="confirmdp" value="${confirmdpFourni}"/><label class="erreurs">${errConfMDP}</label>
                <input type="submit" name="DoIt" value="Enregistrer"/>
>>>>>>> master
                
            </form>            
        </div>
       