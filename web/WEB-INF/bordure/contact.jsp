<%-- 
    Document   : contact
    Created on : 8 sept. 2014, 15:29:17
    Author     : cdi108
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div id="formcontact">

    <form id="contact" method="post" action="Controleur" enctype="multipart/form-data">
        <input type="hidden" name="section" value="contactformulaire" />
        <label for="mail">Votre Nom :</label><!--expediteur-->
        <input id="nomcontact" type="text" name="votrenom" value="${votrenom}"><label class="erreur">${errNom}</label><br />

        <label for="mail">Votre Prénom :</label><!--expediteur-->
        <input id="prenomcontact" type="text" name="votreprenom" value="${votreprenom}>"><label class="erreur">${errPrenom}</label><br />

        <label for="mail">Votre Adresse E-mail :</label><!--expediteur-->
        <input id="mail" type="text" name="votremail" value="${votremail}"><label class="erreur">${errMail}</label><br />

        <label for='objet'>Objet de votre message :</label>
        <input id="objet" type="text" name="objet" value="${objet}"><br /> 

        <label for="messagecontact">Votre message :</label>
        <textarea id="message" name="messagecontact">${messagecontact}</textarea><br />

        <div class="clear"></div> 



    </form>

</div><!-- fin formcontact -->

<div class="clear"></div>

