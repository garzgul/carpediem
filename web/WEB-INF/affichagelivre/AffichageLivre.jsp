<%-- 
    Document   : AffichageLivre
    Author     : eddy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div>
    <h2>Fiche</h2>
    <c:if test="${not empty ficheLivreCourant}">
        <div>Livre : ${ficheLivreCourant}</div>
        <div>Couverture : <img src="./images/${ficheLivreCourant.image}" width="200" height="200" /></div>
        <div>Sous-titre : ${ficheLivreCourant.soustitre}</div>
        <div>ISBN10 : ${ficheLivreCourant.isbn10}</div>
        <div>ISBN13 : ${ficheLivreCourant.isbn13}</div>
        <div>Format : ${ficheLivreCourant.format}</div>
        <div>Nombre de pages : ${ficheLivreCourant.nbpage}</div>
        <div>Parution : ${ficheLivreCourant.parution}</div>
        <div>Numéro d'édition : ${ficheLivreCourant.edition}</div>
        <div>Dimensions : ${ficheLivreCourant.dimension}</div>
        <div>Poids : ${ficheLivreCourant.poids}</div>
        <div>Résumé : ${ficheLivreCourant.resume}</div>
        <div>Prix : ${ficheLivreCourant.prix}</div>
        <div>Stock disponible : ${ficheLivreCourant.stock}</div>
        
    </c:if>
</div>
    
<%--
<div class="proddethaut">
    <div class="proddetprod">
        <div class="proddethautimg"><img src="$//{}" alt="" title="" width="200" height="200" /></div>
        <div class="proddet">
            <div class="proddethauttitre">$//{}</div>
            <div class="proddethautref">$//{}</div>
            <div class="proddethautresu">$//{}</div>
            <div class="proddethautprix">$//{}</div>
            <div class="proddethautstatut">$//{}</div>

            <div class="proddethautnote_produit">
                Appréciation : /5 (moyenne sur  avis)
            </div>
        </div>
    </div>




    <div class="pdescriptionproddethaut">$//{}</div>
    <div class="descriptionproddethaut">
        <div class="proddethautcouleur"><span class="gras">Couleur : </span>$//{}</div>
        <div class="proddettiret">-</div>
        <div class="proddethauttaille"><span class="gras">Taille : </span>$//{}</div>
        <div class="proddettiret">-</div>
        <div class="proddethautsexe"><span class="gras">Sexe : </span>$//{}</div>
        <div class="proddettiret">-</div>';
        <div class="proddethautpoids"><span class="gras">Poids : </span>$//{}</div>
        <p class="pproddetinfocompl">*Ce prix est T.T.C.</p>
    </div>
--%>

