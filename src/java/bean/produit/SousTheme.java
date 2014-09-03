
package bean.produit;

import bean.produit.Theme;

/**
 *
 * @author user
 */
public class SousTheme {
    private String sousTheme;
    private Theme theme;
    private int idSousTheme;

    public SousTheme() {
    }

    public SousTheme(int idSousTheme,String sousTheme) {
        this.sousTheme = sousTheme;
        this.idSousTheme = idSousTheme;
    }
    

    public SousTheme(String sousTheme, Theme theme) {
        this.sousTheme = sousTheme;
        this.theme = theme;
    }

    public String getSousTheme() {
        return sousTheme;
    }

    public void setSousTheme(String sousTheme) {
        this.sousTheme = sousTheme;
    }

    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }

    public int getIdSousTheme() {
        return idSousTheme;
    }

    public void setIdSousTheme(int idSousTheme) {
        this.idSousTheme = idSousTheme;
    }

    @Override
    public String toString() {
        return sousTheme;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + this.idSousTheme;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SousTheme other = (SousTheme) obj;
        if (this.idSousTheme != other.idSousTheme) {
            return false;
        }
        return true;
    }
    
    
    
}
