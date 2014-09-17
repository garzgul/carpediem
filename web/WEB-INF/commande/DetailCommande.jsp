<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class ="detailcommande">
    
    <!-- affichage du panier-->
    <table border="1" cellpadding="4">
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
                    <!-- inclure le c:if pour le calcul des frais de port (change le type de données et les recupere de commande et non de panier-->
                    <!-- necessite de mettre en session l'objet commande et de demander le choix de l'adresse et du port-->
                </c:forEach>
                    <tr>
                        <td colspan="7"/>
                    </tr>
                    <tr>
                        <td colspan="6">Total commande HT </td>
                        <td><fmt:formatNumber maxFractionDigits="2" value="${commande.htCde}"/></td>
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
    
    
    <p>
        <br/>
    <!--detail acheteur -->
    Nom : ${Acheteur.prenomAcheteur} ${acheteur.nomAcheteur}<br/>
    
    <!--choix de l'adresse -->
    Choix de l'adresse:</p>
    <br/>
    <form action="Controleur" method="POST">
        <input type="hidden" name="section" value="commande"/>
        <input type="hidden" name="action" value="confirmeradresse"/>
        <select name="adresse" class="adressecommande">   
            <c:forEach items="${Acheteur.listAdresseAcheteur}" var="adresse">
                <option value="ref=${adresse.idadresse}" <c:if test="${adresse.adressefav}">
                    selected
                </c:if>/> >
                    ${adresse.adresseAcheteur1} ${adresse.adresseAcheteur2} ${adresse.cpAcheteur} ${adresse.villeAcheteur} ${adresse.paysAcheteur}
                </option> 
            </c:forEach>
        </select>
        <input type="submit" name="doit" value="confirmer l'adresse de livraison"/>       
       <% //
            //    <c:url value="Controleur?section=commande&action=confirmer" var="url"/>
             //   <a href="${url}">Confirmer la commande</a> %>
</form>        
                <c:if test="${not empty adressecommandechoisie}">
                ${adressecommande.adresseAcheteur1}
                ${adressecommande.adresseAcheteur2}
                ${adressecommande.cpAcheteur}
                ${adressecommande.villeAcheteur}
                ${adressecommande.paysAcheteur}
                    
                </c:if>
    <p>
   <!--choix du mode de livraison -->  
        Choissisez le type de livraison : <br/>
    </p>

    <form action="Controleur" method="POST">
        <input type="hidden" name="section" value="commande"/>
        <input type="hidden" name="action" value="confirmertypelivraison"/>
        <select name="typelivraison">
            <c:forEach items="${typelivraison}" var = "tl">
                <option value="ref?${tl}">${tl}</option>
            </c:forEach>
        </select>
        <input type="submit" name="doit" value="confirmer la livraison"/>
    </form>
    
    <p>
        <br/>
        <br/>
        <c:url value="Controleur?section=commande&action=confirmer" var="url"/>
        <a href="${url}"> Confirmer la commande</a>
        
    </p>
</div>