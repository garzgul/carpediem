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
        <link type="text/css" rel="stylesheet" href="styles/global.css" />
       <!-- <script type="text/javascript" src="inc/jquery-1.11.0.min.js"></script>
        <script type="text/javascript" src="inc/jquery.cycle.all.js"></script>
        <script type="text/javascript" src="inc/manehou.js"></script> -->
    </head>
	
    <body id="index"> <!-- ouverture balise body  -->
        <div id="global"><!-- ouverture balise div id="global"  -->
	
	
           
            <c:import url="/Controleur?section=Entete" />

           
            <c:import url="/Controleur?section=Menu" />
            
            
            
            <c:import url="/Controleur?section=recherche" />
            
            
            <!-- Contenu titre --> 
            <div id="contenu">
                    <div id="contentcontenu">
                    <h1 class="titreh1">>>titre de la page ou fil ariane interactif</h1>
                    






                </div><!-- fin div contentcontenu -->
            </div><!-- Fin contenu -->

<div class="clear"></div>
            
            
             <c:import url="/Controleur?section=Pied" />
        
        
        </div><!-- fermeture balise div id="global"  -->
    </body><!-- fermeture balise body  -->
</html><!-- fermeture balise html  -->
