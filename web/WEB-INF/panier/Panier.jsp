<!-- main jsp pour le panier -->
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div>
    <H1>Votre panier :</H1>
        <c:if test="${empty maliste}">
        <p>Votre panier est vide !</p>
    </c:if>
    <c:if test="${not empty maliste}">
        <table border="2" cellpadding="2">
            <thead>
                <tr>
                    <th colspan="2"></th>
                    <th>Titre</th>
                    <th>Prix HT</th>
                    <th>Prix TTC</th>
                    <th>Quantité</th>
                    <th>Prix Total</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${maliste}" var ="p">
                    <tr>
                        <td ><img> </td>
                        <td>${p.l.titre}</td>
                        <td><fmt:formatNumber maxFractionDigits="2" value="${p.prixHT}"/></td>
                        <td><fmt:formatNumber maxFractionDigits="2" value="${p.tva}"/></td>
                        <td><fmt:formatNumber maxFractionDigits="2" value="${p.prixTTC}"/></td>
                        <td>${p.qte}</td>
                        <td><fmt:formatNumber maxFractionDigits="2" value="${p.prix}"/></td>
                        <td><a href="Controleur?section=panier&action=add&ref=${p.l.id}">+</a> <a href="#">-</a> <a href="#">sup</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:if>
</div>
