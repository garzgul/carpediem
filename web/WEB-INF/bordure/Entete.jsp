<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!-- Header -->
	<div id="header">
	
<!-- Header options panier log -->
		<div id="header-options">
	
<!-- login -->		

			if()//si utilisateur pas connecte
			{

			<div id="not-login">
				<a id="not-login-signup" title="Devenir membre" href="inscription.jsp">Devenir membre <span class="gras">></span></a>
				<a id="not-login-login" title="Se connecter" href="connexion.jsp">Se connecter</a>
			</div><!-- fin de not-login -->	
			
<div class="clear"></div>


			}
			else
			{

			<div id="login">
				<a id="login-signup" title="Mon compte" href="profil.jsp">Mon compte <span class="gras">></span></a>
				<a id="login-login" title="Se déconnecter" href="logout.jsp?action=logout" onclick="return(confirm(\'Etes-vous sûr de vouloir vous déconnecter?\'))">Se déconnecter</a>
			</div><!-- fin de login -->	

<div class="clear"></div>			
			

			}

		
			<div class="panier">
			 
			</div>

		</div><!-- fin de Header options panier log -->

<div class="clear"></div>		
		
	
<!-- Gros logo -->		
		<div id="gros-logo-div">
			<div id="groslogocentre">
				<img id="logocarpediem" src="" title="logo du site Carpe Diem" />
			</div>
		</div><!-- fin de Gros logo -->	

<div class="clear"></div>
		
	</div><!-- fin de Header -->

<div class="clear"></div>

<div>
    <p>Bienvenue, nous sommes le <!-- ${today} on enleve le today ici attention de laisser un espace ou de retourner a la ligne -->
    <fmt:formatDate value="${today}" pattern="EEEE dd MMMM yyyy" /></p>
    <hr />
</div>

