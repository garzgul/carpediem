<%-- 
    Document   : contact
    Created on : 8 sept. 2014, 15:29:17
    Author     : cdi108
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<h2 id="toutes_les_mentionsh2">Contact</h2>
<div id="formcontact">

    <form id="contact" method="GET" action="Controleur">
        <input type="hidden" name="section" value="contactformulaire" />
        <label for="mail">Votre Nom :</label><!--expediteur-->
        <input id="nomcontact" type="text" name="votrenom" value="${votrenom}"><label class="erreur">${errNom}</label><br />

        <label for="mail">Votre Prénom :</label><!--expediteur-->
        <input id="prenomcontact" type="text" name="votreprenom" value="${votreprenom}"><label class="erreur">${errPrenom}</label><br />

        <label for="mail">Votre Adresse E-mail :</label><!--expediteur-->
        <input id="mail" type="text" name="votremail" value="${votremail}"><label class="erreur">${errMail}</label><br />

        <label for='objet'>Objet de votre message :</label>
        <input id="objet" type="text" name="objetcontact" value="${objetcontact}"><br /> 

        <label for="messagecontact">Votre message :</label>
        <textarea id="message" name="messagecontact">${messagecontact}</textarea><br />

        <div class="clear"></div> 

        <div><input type="submit" name="envoicontactformulaire" value="Envoyer"/></div>

    </form>

</div><!-- fin formcontact -->

<div class="clear"></div>

<h2 id="toutes_les_mentionsh2">Contact avec pièce jointe</h2>
<div id="formcontact">

    <form id="contact" method="GET" action="Controleur">
        <input type="hidden" name="section" value="contactformulairepj" />
        <label for="mail">Votre Nom :</label><!--expediteur-->
        <input id="nomcontact" type="text" name="votrenompj" value="${votrenompj}"><label class="erreur">${errNom}</label><br />

        <label for="mail">Votre Prénom :</label><!--expediteur-->
        <input id="prenomcontact" type="text" name="votreprenompj" value="${votreprenompj}"><label class="erreur">${errPrenom}</label><br />

        <label for="mail">Votre Adresse E-mail :</label><!--expediteur-->
        <input id="mail" type="text" name="votremailpj" value="${votremailpj}"><label class="erreur">${errMail}</label><br />

        <label for='objet'>Objet de votre message :</label>
        <input id="objet" type="text" name="objetcontactpj" value="${objetcontactpj}"><br /> 
        
        <!-- <label for='fichier'>Fichier joint :</label> -->
         <!-- <input id="fichier" type="text" name="fichierpj" value="${fichierpj}"><br /> -->

        <label for="messagecontact">Votre message :</label>
        <textarea id="message" name="messagecontactpj">${messagecontactpj}</textarea><br />

        <div class="clear"></div> 

        <div><input type="submit" name="envoicontactformulairepj" value="Envoyer"/></div>

    </form>

</div><!-- fin formcontact -->

<div class="clear"></div>