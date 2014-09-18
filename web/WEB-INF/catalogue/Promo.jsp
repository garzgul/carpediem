<%-- 
    Document   : Promo
    Created on : 4 sept. 2014, 16:49:52
    Author     : cdi116
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div>
    <h2>Liste des 3 produits en promotion</h2></br>
    <c:forEach var="llpa3" items="${Liste3dernierslivrespromo}">
        <table>
            <thead>

                <tr>
                    <th class="hidden">Id</th>
                    <th>Couverture</th>
                    <th>Titre</th>
                    <th>Auteurs</th>
                    <th>Prix</th>
                </tr>
            </thead>

            <tbody>
                <tr>
                    <td class="hidden"><c:out value="b.id"/></td>
                    <td>${llpa3.image}</td>
                    <td>${llpa3.titre}</td>
                    <th>${llpa3.auteur}</th>
                    <td>${llpa3.prix}</td>
                </tr>
            </tbody>
        </table>
    </c:forEach>
</div>
