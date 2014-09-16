<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta content="text/html;charset=UTF-8" http-equiv="Content-type">
        <title>Carpe Diem - librairie en ligne</title>
        <meta content="fr" http-equiv="Content-Language" />
        <meta lang="fr" content="Carpe Diem promeut les livres. Les livres que vous achetez en ligne sont disponibles et livrables rapidement." name="description">
        <meta content="livres, livre, livres neufs, livre neuf, littérature, roman policier, science-fiction, fantasy, vie pratique, architecture, urbanisme, histoire, philosophie, essai, dictionnaire, scolaire, BD, manga, jeunesse, livraison gratuite, avis, conseils, critiques, coups de coeur, nouveautés, meilleures ventes" name="keywords">
        <meta content="ALL,INDEX,FOLLOW,NOODP" name="robots">
        <link type="text/css" rel="stylesheet" href="styles/carpediemcss.css" />
        <link type="text/css" rel="stylesheet" href="styles/entete.css" />
        <link type="text/css" rel="stylesheet" href="styles/footer.css" />
        <link type="text/css" rel="stylesheet" href="styles/contenu.css" />
        <link type="text/css" rel="stylesheet" href="styles/menuhorizontal.css" />
        <link type="text/css" rel="stylesheet" href="styles/menuvertical.css" />
        <!-- <script type="text/javascript" src="inc/jquery-1.11.0.min.js"></script>
         <script type="text/javascript" src="inc/jquery.cycle.all.js"></script>
         <script type="text/javascript" src="inc/manehou.js"></script> -->
    </head>

    <body id="index"> <!-- ouverture balise body  -->
        <div id="global"><!-- ouverture balise div id="global"  -->
            <c:import url="${entete}" />
            <c:import url="${menu}" />
            <c:import url="/WEB-INF/catalogue/recherche.jsp" />


            <!-- Contenu titre --> 
            <div id="contenu">
                <div id="contentcontenu">
                    <h1 class="titreh1">>>titre de la page ou fil ariane interactif</h1>

                    <c:if test="${not empty recherche}">  
                        <c:import url="${recherche}" />
                    </c:if>

                    <c:if test="${not empty affichageLivre}">
                        <c:import url="${affichageLivre}" />
                    </c:if>

                    <c:if test ="${not empty affichagecompte}">
                        <c:import url="${affichagecompte}"/>
                    </c:if>


                    <c:if test="${not empty affichagepanier}">

                        <c:import url="${affichagepanier}"/> 

                    </c:if>

                    <c:if test="${not empty voircompte}">
                        <c:import url="${voircompte}"/>
                    </c:if>
                    
                    <c:if test="${not empty commande}">
                        <c:import url="${commande}"/>
                    </c:if>
                    

                    <c:if test="${not empty fragement}">
                        <c:import url="${fragement}"/>
                    </c:if>

                    

                </div><!-- fin div contentcontenu -->
            </div><!-- Fin contenu -->



            <div class="clear"></div>


            <c:import url="${pied}" />


        </div><!-- fermeture balise div id="global"  -->
    </body><!-- fermeture balise body  -->
</html><!-- fermeture balise html  -->
