<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class ="detailcommande">
    
    <!-- affichage du panier-->
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
                        
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    
    
    <p>
    <!--detail acheteur -->
    Nom : ${prenomAcheteur} ${nomAcheteur}<br/>
    
    <!--choix de l'adresse -->
    Choix de l'adresse:
        adresse par defaut :
        ${AdresseAcheteur1}
        ${AdresseAcheteur2}
        ${cpAcheteur}
        ${villeAcheteur}
        ${patsAcheteur}
    
    Si vous souhaitez l'envoyer a une autre adresse, veuillez selectionner l'adresse ci dessous :
    
        
    <!--choix du mode de livraison -->
    
    <!--calcul du cout de la livraison-->
    
    
    
    </p>
    
</div>