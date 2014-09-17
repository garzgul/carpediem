<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div class="commandechoixmodelivraison">
    <p>
   <!--choix du mode de livraison -->  
        Choissisez le type de livraison : <br/>
    </p>

    <form action="Controleur" method="POST">
        <input type="hidden" name="section" value="commande"/>
        <input type="hidden" name="action" value="confirmertypelivraison"/>
        <select name="typelivraison">
            <c:forEach items="${typelivraison}" var = "tl">
                <option  value="${tl}">${tl}</option>
            </c:forEach>
        </select>
        <input type="submit" name="doit" value="confirmer la livraison"/>
    </form>
    
    <p>
        <br/>
        <br/>
        
        
    </p>
    
    
    
    
    
    
</div>
