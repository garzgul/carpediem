
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- <%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> -->


<div>
    <h1>Recherche</h1>
    <form action="Controleur" method="POST">
        <input type="text" name="ChampRecherche" />
        <input type="submit" name="BtnRecherche" />
        <input type="hidden" name="section" value="recherche" />
        <input type="hidden" name="action" value="rechercher" />
    </form>
    <!--mon objet liste livre se récupérera dans la session getattribute(rechercheListeLivre) -->
    
</div>
