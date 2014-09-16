<!-- Detail Panier -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
        <table border="2" cellpadding="2">
            <thead>
                <tr>
                    <th>Couverture</th>
                    <th>Titre</th>
                    <th>Prix HT</th>
                    <th>Montant TVA</th>
                    <th>Prix TTC</th>
                    <th>Quantité</th>
                    <th>Prix Total</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${maliste}" var ="p">
                    <tr>
                        <td ><img src="./images/${p.l.image}" width="100" height="100" /></td>
                        <c:url value="controleur?section=fichelivre&action=afficher&ref=${p.l.id}" var="url"/>
                        <td><a href="${url}">${p.l.titre}</a></td>
                        <td><fmt:formatNumber maxFractionDigits="2" value="${p.prixHT}"/></td>
                        <td><fmt:formatNumber maxFractionDigits="2" value="${p.tva}"/></td>
                        <td><fmt:formatNumber maxFractionDigits="2" value="${p.prixTTC}"/></td>
                        <td>${p.qte}</td>
                        <td><fmt:formatNumber maxFractionDigits="2" value="${p.prix}"/></td>
                        <td>
                        <c:url value="Controleur?section=panier&action=plus&ref=${p.l.id}" var="url1"/> <a href="${url1}">+</a>
                        <c:url value="Controleur?section=panier&action=moins&ref=${p.l.id}" var="url2"/> <a href="${url2}">-</a>
                        <c:url value="Controleur?section=panier&action=remove&ref=${p.l.id}" var="url3"/> <a href="${url3}">sup</a>
                            
                        </td>
                    </tr>
                </c:forEach>
                    <tr>
                        <td colspan="6">Total commande HT </td>
                        <td><fmt:formatNumber maxFractionDigits="2" value="${panier.prixHTTotal}"/></td>
                    </tr>
                    <tr>
                        <td colspan="6">Total tva commande  </td>
                        <td><fmt:formatNumber maxFractionDigits="2" value="${panier.tvaTotal}"/></td>
                    </tr>
                    <tr>
                        <td colspan="6">Total commande TTC </td>
                        <td><fmt:formatNumber maxFractionDigits="2" value="${panier.prixTTCTotal}"/></td>
                    </tr>
            </tbody>
        </table>