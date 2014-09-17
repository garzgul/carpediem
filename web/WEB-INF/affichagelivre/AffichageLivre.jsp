<%-- 
    Document   : AffichageLivre
    Author     : eddy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div>
    <h2>Fiche</h2>
    <div>
        <c:if test="${not empty ficheLivreCourant}">

            <p>Livre : ${ficheLivreCourant}</p>
            <p>Couverture : <img src="./images/${ficheLivreCourant.image}" width="200" height="200" /></p>
            <p>Sous-titre : ${ficheLivreCourant.soustitre}</p>
            <p>ISBN10 : ${ficheLivreCourant.isbn10}</p>
            <p>ISBN13 : ${ficheLivreCourant.isbn13}</p>
            <p>Format : ${ficheLivreCourant.format}</p>
            <p>Nombre de pages : ${ficheLivreCourant.nbpage}</p>
            <p>Parution : <fmt:formatDate pattern="dd/MM/yyyy" value="${ficheLivreCourant.parution}" /></p>
            <p>Numéro d'édition : ${ficheLivreCourant.edition}</p>
            <p>Dimensions : ${ficheLivreCourant.dimension}</p>
            <p>Poids : ${ficheLivreCourant.poids}</p>
            <p>Résumé : ${ficheLivreCourant.resume}</p>
            <p>Prix (eur) : ${ficheLivreCourant.prix}</p>
            <p>Stock disponible : ${ficheLivreCourant.stock}</p>
            <p><a href="Controleur?section=panier&action=add&ref=${ficheLivreCourant.id}">Ajout panier</a></p>
        </c:if>
    </div>
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
        <div class="proddethauttaille"><span class="gras">Format : </span>$//{}</div>
        <div class="proddettiret">-</div>
        <div class="proddethautsexe"><span class="gras">edition : </span>$//{}</div>
        <div class="proddettiret">-</div>';
        <div class="proddethautpoids"><span class="gras">Poids : </span>$//{}</div>
        <p class="pproddetinfocompl">*Ce prix est T.T.C.</p>
    </div>
--%>

