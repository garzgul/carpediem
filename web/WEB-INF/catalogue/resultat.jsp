<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div>
    <h2>RESULTAT :</h2>
    <!-- Objet liste livres se récupère depuis le scope -->
    <!-- (placé précédemment avec le setAttribute(rechercheResultat) -->
    <c:if test="${not empty rechercheResultat}">
            <table border="1">
                <thead>
                    <tr>
                        <th>Couverture</th>
                        <th>Titre</th>
                        <th>Auteur</th>
                        <th>Prix (eur)</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${rechercheResultat}" var="l">
                        <tr>
                            <td><img src="./images/${l.image}" width="100" height="100" /></td>
                            <td><a href="Controleur?section=ficheLivre&action=afficher&ref=${l.id}">${l}</a></td>
                            <td>${l.getListeAu().get(0)}</td>
                            <td>${l.prix}</td>
                            <td><a href="Controleur?section=panier&action=add&ref=${l.id}">Ajout panier</a></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
    </c:if>
</div
