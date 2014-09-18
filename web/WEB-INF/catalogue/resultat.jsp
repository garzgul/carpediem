<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:set var="rowsPerPage" value="5" />
<c:set var="pageNumber" value="${param.pageNumber}"/>
<c:set var="a">
    <fmt:formatNumber value="${rechercheResultat.size()/rowsPerPage}" maxFractionDigits="0"/>
</c:set>
<c:set var="b" value="${rechercheResultat.size()/rowsPerPage}" />

<c:choose>
    <c:when test="${a==0}">
        <c:set var="numberOfPages" value="1" scope="session"/>   
    </c:when>
 
    <c:when test="${b>a}">
        <c:set var="xxx" value="${b%a}"/>
        <c:if test="${xxx>0}">
            <c:set var="numberOfPages" value="${b-xxx+1}" scope="session"/>   
        </c:if>
    </c:when>
 
    <c:when test="${a>=b}">
        <c:set var="numberOfPages" value="${a}" scope="session"/>    
    </c:when>
</c:choose>

<c:set var="start" value="${pageNumber*rowsPerPage-rowsPerPage}"/>
<c:set var="stop" value="${pageNumber*rowsPerPage-1}"/>

<div>
    <h2>RESULTAT :</h2>
    <!-- Objet liste livres se récupère depuis le scope -->
    <!-- (placé précédemment avec le setAttribute(rechercheResultat) -->
    <c:if test="${not empty rechercheResultat}">
            <table border="1">
                <thead>
                    <tr>
                        <th>COUVERTURE</th>
                        <th>TITRE</th>
                        <th>AUTEUR</th>
                        <th>ISBN</th>
                        <th>PRIX (EUR)</th>
                        <th>ACTION</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${rechercheResultat}" var="l" begin="${start}" end="${stop}">   
                        <tr>
                            <td><img src="./images/${l.image}" width="100" height="100" /></td>
                            <td><a href="Controleur?section=ficheLivre&action=afficher&ref=${l.id}">${l}</a></td>
                            <td>${l.getListeAu().get(0)}</td>
                            <td>${l.isbn13}</td>
                            <td>${l.prix}</td>
                            <td><a href="Controleur?section=panier&action=add&ref=${l.id}"><img src="./images/ajouterpaniervert.png" width="40" height="40" /></a></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            
            <%--For displaying Previous link --%>
            <c:if test="${pageNumber gt 1}">
                <a href="Controleur?section=recherche&action=pagination&pageNumber=${pageNumber - 1}">Previous</a>
            </c:if>
            <c:forEach begin="1" end="${numberOfPages}" var="i">
                <c:choose>
                    <c:when test="${i!=pageNumber}">
                        <a href="Controleur?section=recherche&action=pagination&pageNumber=<c:out value="${i}"/>"><c:out value="${i}"/></a>
                    </c:when>
                    <c:otherwise>
                        <c:out value="${i}"/>
                    </c:otherwise>        
                </c:choose>       
            </c:forEach>  
            <%--For displaying Next link --%>
            <c:if test="${pageNumber lt numberOfPages}">
                <a href="Controleur?section=recherche&action=pagination&pageNumber=${pageNumber + 1}">Next</a>
            </c:if>            
    </c:if>
</div>
