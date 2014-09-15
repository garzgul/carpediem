
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- Header -->
<div id="header">


    <!-- Gros logo -->		
    <div id="gros-logo-div">
        <div id="groslogocentre">
            <img id="logocarpediem" src="" title="logo du site Carpe Diem" />
        </div>
    </div><!-- fin de Gros logo -->	

    <div class="clear"></div>   





    <!-- Header options panier log -->
    <div id="header-options">
        <div id="panier">
            <c:url value="Controleur?section=panier&action=affichage" var="url"/>
            <a href="${url}">votre panier</a>

        </div>
        <!-- login -->		

        <!-- if()//si utilisateur pas connecte
        { -->

        <div id="not-login">
            <c:url value="Controleur?section=connexion&action=inscriptionacheteur" var="url2"/>
            <a id="not-login-signup" title="Inscription" href="${url2}">Devenir membre <span class="gras">></span></a>
            <c:url value="Controleur?section=connexion&action=seconnecter" var="url3"/>
            <a id="not-login-login" title="Se connecter" href="${url3}">Se connecter</a>
        </div><!-- fin de not-login -->	

        <div class="clear"></div>


        <!-- }
        else
        { -->

        <div id="login">
            <a id="login-signup" title="Mon compte" href="Controleur?section=moncompte">Mon compte <span class="gras">></span></a>
            <a id="login-login" title="Se déconnecter" href="Controleur?section=deconnexion" onclick="return(confirm(\'Etes-vous sûr de vouloir vous déconnecter?\'))">Se déconnecter</a>
        </div><!-- fin de login -->	

        <div class="clear"></div>			


        <!--}-->
    </div><!-- fin de Header options panier log -->

    <div class="clear"></div>		


</div><!-- fin de Header -->

<div class="clear"></div>

<div id="datejour">
    <p>Bienvenue, nous sommes le <fmt:formatDate value="${today}" pattern="EEEE dd MMMM yyyy" /></p>
    <hr />
</div>

