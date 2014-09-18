
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" --%>


<div id="formrecherchegdiv">
    <h2 id="toutes_les_mentionsh2">Recherche</h2>
    <form id="formrechercheg" action="Controleur" method="POST">
        <input type="hidden" name="section" value="recherche" />
        <input type="hidden" name="action" value="rechercher" />
        <label for="ChampRecherche"><img src="./images/pucelivre.png" alt="pucelivre" title="pucelivre" width="40" height="40" /></label>
        <input id="rech-rapide" type="text" name="ChampRecherche" placeholder="Rechercher par titre, auteur, isbn, etc." />
             
        <input type="submit" name="BtnRecherche" value="Rechercher" />
    </form>
    <div class="clear"></div> 
</div>
<div class="clear"></div>