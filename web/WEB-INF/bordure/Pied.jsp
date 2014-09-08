<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div id="footersite">      
		<div id="footer-wrap">
			<div id="footer-wrap-home"></div>   
			<div id="footer-liens-wrap">
				<div id="footer-menu-wrap">
					<ul>
          	<li>
						  <a title="Présentation de Carpe Diem" href="">Carpe Diem</a>
					  </li>
						<li>
							<span id="sansseparateur"><a class="footer-menu-a" href="mentionslegales.jsp">Mentions légales</a></span>
						</li>
						<li>
							<a class="footer-menu-a" href="cgv.jsp">C.G.V.</a>
						</li>
						<li>
							<a class="footer-menu-a" href="plansite.jsp">Plan du site</a>
						</li>
						<li>
							<a class="footer-menu-a" href="index.jsp?page=imprimer_page" onclick="window.print(); return false;">Imprimer la page</a>
						</li>
						<li>
							<a class="footer-menu-a" href="newsletter.jsp">Newsletter</a>
						</li>
						<li>
							<a class="footer-menu-a" href="contact.jsp">Contact</a>
						</li>
					</ul>
				</div><!-- fin div footer-menu-wrap -->
				<div id="footer-wrap-social">
					<ul>
						<li>
							<img id="twitter" title="Twitter" alt="logo twitter" src="" />
						</li>
						<li>
							<img id="facebook" title="Facebook" alt="logo facebook" src="" />
						</li>
					</ul>
				</div><!-- fin div footer-wrap-social -->
			</div><!-- fin div footer-liens-wrap -->	
		</div><!-- fin div footer-wrap -->
		
		<div id="copyright">
			<p>
			&copy; 2014 - <fmt:formatDate value="${today}" pattern="yyyy" /> Tous droits réservés | <a href="">Carpe Diem</a>
			</p>
		</div><!-- fin div copyright -->
		
	</div><!-- fin div footer -->

<div class="clear"></div>


