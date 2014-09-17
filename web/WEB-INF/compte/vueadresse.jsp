<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div>
    <!--detail acheteur -->
    Nom : ${Acheteur.prenomAcheteur} ${acheteur.nomAcheteur}<br/>
    
    <!--choix de l'adresse -->
    <h1>Mes adresses :</h1>
    <br/>
    <form action="Controleur" method="POST">
        <input type="hidden" name="section" value="connexion"/>
        <input type="hidden" name="action" value="vueadresse"/>
        <select name="adresse" class="adressecompte">   
            <c:forEach items="${Acheteur.listAdresseAcheteur}" var="adresse">
                <option value="ref=${adresse.idadresse}" <c:if test="${adresse.adressefav}">
                    selected
                </c:if>/> >
                    ${adresse.adresseAcheteur1} ${adresse.adresseAcheteur2} ${adresse.cpAcheteur} ${adresse.villeAcheteur} ${adresse.paysAcheteur}
                </option> 
            </c:forEach>
        </select>
        <input type="submit" name="doit" value="confirmer"/>       
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

</div>
