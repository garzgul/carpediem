
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<div>
    <h1>Resultat</h1>
    <!--mon objet liste livre se récupérera dans la session ou request getattribute(rechercheResultat) -->
    <c:if test="${not empty rechercheResultat}">
        <c:if test="${not empty rechercheListeLivre}">
            <table>
                <tbody>
                    <c:forEach items="${rechercheListeLivre}" var="l">

                        <tr>
                            <td>&{l.get(l)}</td>
                        </tr>

                    </c:forEach>
                </tbody>
            </table>
        </c:if>
    </c:if>
</div>