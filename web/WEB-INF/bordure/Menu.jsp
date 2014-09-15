
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!-- Menu horizontal -->
<nav id="menuhorizontal">
    <div id="menu">
        <div id="primarylinks">
            <ul>
                <li>
                    <c:url value="Controleur?section=defaut" var ="url"/> 
                    <a title="Accueil" href="${url}">Accueil</a>
                </li>
                <li>
                    <a title="" href="">Promotions</a>
                </li>
                <li>
                    <a title="" href="">Nouveautés</a>
                </li>
                <li>
                    <a title="" href="">Rubriques</a>
                </li>
            </ul>
        </div><!-- fin div primarylinks -->
    </div><!-- fin de Menu  -->
</nav> <!-- fin de Menu horizontal -->

<div class="clear"></div>

