package bean.metier;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.print.PageFormat;
import static java.awt.print.Printable.NO_SUCH_PAGE;
import static java.awt.print.Printable.PAGE_EXISTS;
import java.awt.print.PrinterException;

/**
 *
 * @author cdi108
 */
public class ImpressionPage {
    
	public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
		if (pageIndex > 0) {
			return NO_SUCH_PAGE;
		}
		/* On définit une marge pour l'impression */
		int marge=30;

		/* On récupère les coordonnées des bords de la page */
		int x = (int)pageFormat.getImageableX();
		int y = (int)pageFormat.getImageableY();
		int w = (int)pageFormat.getImageableWidth();
		int h = (int)pageFormat.getImageableHeight();

		/* Dessin d'un cadre gris clair*/
		graphics.setColor(Color.LIGHT_GRAY);
		graphics.fillRect(x+10, y+10, w-20, h-20);

		/* On écrit une ligne de titre en rouge, en gras de taille 18 */
		graphics.setFont(new Font("Arial", Font.BOLD, 18));
		graphics.setColor(Color.RED);
		graphics.drawString("Rapport\n", x + marge, y+marge);

		/* On écrit une ligne en noir de taille 14 */
		graphics.setFont(new Font("Arial", Font.PLAIN, 14));
		graphics.setColor(Color.BLACK);
		graphics.drawString("Première ligne du rapport", x+marge, y+marge+20);

		return PAGE_EXISTS;
	
}
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
