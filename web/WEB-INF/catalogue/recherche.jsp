
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" --%>


<div>
    <h1>Recherche</h1>
    <form action="Controleur" method="POST">
        <input type="hidden" name="section" value="recherche" />
        <input type="hidden" name="action" value="rechercher" />
        <input type="text" name="ChampRecherche" />
        <input type="submit" name="BtnRecherche" />
    </form>
    <!--mon objet liste livre se récupérera dans la session getattribute(rechercheListeLivre) -->
    <c:if test="${not empty rechercheListeLivre}">
        <table>
            <tbody>
                <c:forEach items="${rechercheListeLivre}" var="l">

                    

                </c:forEach>
            </tbody>
        </table>
    </c:if>
    
</div>
