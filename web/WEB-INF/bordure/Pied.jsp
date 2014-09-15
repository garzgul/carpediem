<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="footersite">      
    <div id="footer-wrap">
         <div id="footer-liens-wrap">
            <div id="footer-menu-wrap">
                <ul>
                    <li><c:url var="url" value="Controleur?section=fragement&action=carpediem" />
                        <a title="Présentation de Carpe Diem" href="${url}">Carpe Diem</a>

                    </li>
                    <li>
                        <c:url var="url" value="Controleur?section=fragement&action=mentionslegales" />
                        <span id="sansseparateur"><a class="footer-menu-a" href="${url}">Mentions légales</a></span>
                    </li>
                    <li>
                        <c:url var="url" value="Controleur?section=fragement&action=cgv" />
                        <a class="footer-menu-a" href="${url}">C.G.V.</a>
                    </li>
                    <li>
                        <c:url var="url" value="Controleur?section=fragement&action=plansite" />
                        <a class="footer-menu-a" href="${url}">Plan du site</a>
                    </li>
                    <li>
                        <c:url var="url" value="Controleur?section=bordure&action=imprimerpageactuelle" />
                        <a class="footer-menu-a" href="${url}" onclick="window.print();
                                return false;">Imprimer la page</a>
                    </li>
                    <li>
                        <c:url var="url" value="Controleur?section=fragement&action=newsletter" />
                        <a class="footer-menu-a" href="${url}">Newsletter</a>
                    </li>
                    <li>
                        <c:url var="url" value="Controleur?section=fragement&action=contact" />
                        <a class="footer-menu-a" href="${url}">Contact</a>
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
        <c:url var="url" value="Controleur?section=fragement&action=carpediem" />
        <p>                         
            &copy; 2014 - <fmt:formatDate value="${today}" pattern="yyyy" /> Tous droits réservés | <a href="${url}">Carpe Diem</a>
        </p>
    </div><!-- fin div copyright -->

</div><!-- fin div footer -->

<div class="clear"></div>


