package bean.metier;

import DAO.livre.ThemeDAO;
import bean.produit.Theme;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author eddy
 */
public class ThemesGestion implements Serializable {

    private ThemeDAO tDao;

    public ThemesGestion() throws NamingException {
        tDao = new ThemeDAO();
    }

    public List<Theme> listeThemes() throws SQLException, NamingException {
        return tDao.listeThemes();
    }

}
