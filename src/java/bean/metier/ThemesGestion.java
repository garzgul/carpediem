package bean.metier;

import DAO.livre.SousThemeDAO;
import DAO.livre.ThemeDAO;
import bean.produit.SousTheme;
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

    private SousThemeDAO stDao;

    public ThemesGestion() throws NamingException {
        stDao = new SousThemeDAO();
    }

    public List<SousTheme> listeSousThemes() throws SQLException, NamingException {
        return stDao.listeSousThemes();
    }

}
