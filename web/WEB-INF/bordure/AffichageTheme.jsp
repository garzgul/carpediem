

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" --%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div>
    <h2>Thémes</h2>
    <div>
    <ul>
       <c:forEach var="theme" begin="0" items="${themesListe}">
           <li>
            <a href="">
                <img src="" alt="puce spéciale navigation" title="puce spéciale navigation" />
                <c:out value="${theme.theme}" />
            </a>
        </li>
       </c:forEach>
    </ul>
    </div>
    <div>
        <a href="" onclick="javascript:windows.location=''; ">
            <img src="" alt="icone panier" title="icone panier" />
            <span>Panier</span>
        </a>
    </div>
    <div>
        <img src="" alt="icone cartes paiement" title="icone cartes paiement" />
    </div>
</div>
