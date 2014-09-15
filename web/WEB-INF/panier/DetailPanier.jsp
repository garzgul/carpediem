<!-- Detail Panier -->
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
                        <c:url value="controleur?section=fichelivre&action=afficher&ref=${p.l.id}" var="url"/>
                        <td><a href="${url}">${p.l.titre}</a></td>
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