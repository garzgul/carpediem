<%-- 
    Document   : Catalogue
    Created on : 3 sept. 2014, 09:41:00
    Author     : cdi116
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div>
    <h2>Liste de produits</h2></br> </br>
    <c:forEach var="b" items="${listelivre}">
        <table>
            <thead>

                <tr>
                    <th class="hidden">Id</th>
                    <th>Couverture</th>
                    <th>Titre</th>
                    <th>Sous-titre</th>
                    <th>Auteurs</th>
                    <th>Editeur</th>
                    <th>Format</th>
                    <th>Date de parution</th>
                    <th>Résumé</th>
                    <th>ISBN10</th>
                    <th>ISBN13</th>
                    <th>Nombre de page</th>
                    <th>Dimensions</th>
                    <th>Poids</th>
                    <th>Edition</th>
                    <th>Prix</th>
                </tr>
            </thead>

            <tbody>
                <tr>
                    <td class="hidden"><c:out value="b.id"/></td>
                    <td>${b.image}</td>
                    <td>${b.titre}</td>
                    <td>${b.soustitre}</td>
                    <td>${b.ListeAu}</td>
                    <td>${b.ed}</td>
                    <td>${b.format}</td>
                    <td>${b.parution}</td>
                    <td>${b.resume}</td>
                    <td>${b.isbn10}</td>
                    <td>${b.isbn13}</td>
                    <td>${b.nbpage}</td>
                    <td>${b.dimension}</td>
                    <td>${b.poids}</td>
                    <td>${b.edition}</td>
                    <td>${b.prix}</td>
                </tr>
            </tbody>
        </table>
    </c:forEach>

</div>

