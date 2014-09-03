
package bean.produit;

/**
 *
 * @author user
 */
public class Theme {
    private String theme;
    private int id_theme;

    public Theme() {
    }

    public Theme(int id_theme) {
        this.id_theme = id_theme;
    }
    

    public Theme(String theme) {
        this.theme = theme;
    }

    public Theme(int id_theme, String theme) {
        this.theme = theme;
        this.id_theme = id_theme;
    }

    public int getId_theme() {
        return id_theme;
    }

    public void setId_theme(int id_theme) {
        this.id_theme = id_theme;
    }
    

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    @Override
    public String toString() {
        return theme;
    }
    
    
}
