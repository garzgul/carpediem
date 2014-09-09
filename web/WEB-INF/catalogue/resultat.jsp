
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div>
    <h1>Resultat</h1>
    <!--mon objet liste livre se récupérera dans la session ou request getattribute(rechercheResultat) -->
    <c:if test="${not empty rechercheResultat}">

            <table>
                <tbody>
                    <c:forEach items="${rechercheResultat}" var="l">
                        <tr>
                            <td>${l.image}</td>
                            <td>${l}</td>
                            <td>${l.resume}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

    </c:if>
</div>