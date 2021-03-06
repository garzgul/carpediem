
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- Header -->
<div id="header">
    
    <div id="datejour">
        <p>Bienvenue, nous sommes le <fmt:formatDate value="${today}" pattern="EEEE dd MMMM yyyy" /></p>
    </div>
    <div class="clear"></div>
    
    <!-- Header options  -->
    <div id="header-options">
        <!-- login -->		

        <c:if test="${empty Acheteur}">
            <div id="not-login">
                <c:url value="Controleur?section=connexion&action=inscriptionacheteur" var="url2"/>
                <a id="not-login-signup" title="Inscription" href="${url2}">Devenir membre <span class="gras"></span></a>
                <c:url value="Controleur?section=connexion&action=seconnecter" var="url3"/>
                <a id="not-login-login" title="Se connecter" href="${url3}">Se connecter</a>
                <c:if test="${not empty deconnexion}" >
                    <p>Vous êtes bien déconnecté</p>
                </c:if>
            </div><!-- fin de not-login -->	
            <div class="clear"></div>
        </c:if>

        <c:if test="${not empty Acheteur}">
            <div id="login">
                <a id="login-signup" title="Mon compte" href="Controleur?section=connexion&action=voircompte">Mon compte <span class="gras">></span></a>
                <a id="login-login" title="Se déconnecter" href="Controleur?section=connexion&action=deconnexion" onclick="return(confirm(\'Etes-vous sûr de vouloir vous déconnecter?\'))">Se déconnecter</a>
            </div><!-- fin de login -->	
            <div class="clear"></div>			
        </c:if>
    </div><!-- fin de Header options -->

   
    <div class="clear"></div>
</div><!-- fin de Header -->

<div class="clear"></div>



<!-- Gros logo -->		
<div id="gros-logo-div">
    <img id="logocarpediem" src="./images/carpediemlogofinalrogne.png" title="logo du site Carpe Diem" width="200" height="150" />
</div><!-- fin de Gros logo -->	


