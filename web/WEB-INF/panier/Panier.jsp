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
                        <td><c:url value="controleur?section=affichagelivre&action=affichage&ref=${p.l.id}">${p.l.titre}</c:url></td>
                        <td><fmt:formatNumber maxFractionDigits="2" value="${p.prixHT}"/></td>
                        <td><fmt:formatNumber maxFractionDigits="2" value="${p.tva}"/></td>
                        <td><fmt:formatNumber maxFractionDigits="2" value="${p.prixTTC}"/></td>
                        <td>${p.qte}</td>
                        <td><fmt:formatNumber maxFractionDigits="2" value="${p.prix}"/></td>
                        <td>
                            <c:url value="Controleur?section=panier&action=plus&ref=${p.l.id}">+</c:url> <c:url value="Controleur?section=panier&action=moins&ref=${p.l.id}">-</c:url> <c:url value="Controleur?section=panier&action=remove&ref=${p.l.id}">sup</c:url>
                            
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <p> <c:url value="controleur?section=commande&action=validercommande">Validez la commande</c:url></p>
    </c:if>
</div>
