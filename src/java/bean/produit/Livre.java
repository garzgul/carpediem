
package bean.produit;

import bean.commande.Taxe;
import java.util.ArrayList;
import java.util.Date;

/** gael
 *
 * 
 */
public class Livre {
    private Editeur ed;
    private ArrayList<Auteur> ListeAu;
    private Fournisseur fourn;
    private String titre;
    private String soustitre;
    private String isbn10;
    private String isbn13;
    private TypeFormatLivre format;
    private int nbpage;
    private Date parution;
    private int edition;
    private String dimension;
    private float poids;
    private String resume;
    private String image;
    private float prix;
    private int stock;
    private boolean actifLivre;
    private ArrayList<MotClef> listmc;
    private SousTheme sousTheme;
    private Taxe tva;
    

    public Livre() {
    }

    

    public Livre(Editeur ed, ArrayList<Auteur> ListeAu, String titre, float prix, int stock, boolean actifLivre) {
        this.ed = ed;
        this.ListeAu = ListeAu;
        this.titre = titre;
        this.prix = prix;
        this.stock = stock;
        this.actifLivre = actifLivre;
    }

    public Editeur getEd() {
        return ed;
    }

    public void setEd(Editeur ed) {
        this.ed = ed;
    }

    public ArrayList<Auteur> getListeAu() {
        return ListeAu;
    }

    public void setListeAu(ArrayList<Auteur> ListeAu) {
        this.ListeAu = ListeAu;
    }

    

    public Fournisseur getFourn() {
        return fourn;
    }

    public void setFourn(Fournisseur fourn) {
        this.fourn = fourn;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getSoustitre() {
        return soustitre;
    }

    public void setSoustitre(String soustitre) {
        this.soustitre = soustitre;
    }

    public String getIsbn10() {
        return isbn10;
    }

    public void setIsbn10(String isbn10) {
        this.isbn10 = isbn10;
    }

    public String getIsbn13() {
        return isbn13;
    }

    public void setIsbn13(String isbn13) {
        this.isbn13 = isbn13;
    }

    public TypeFormatLivre getFormat() {
        return format;
    }

    public void setFormat(TypeFormatLivre format) {
        this.format = format;
    }

    
    public int getNbpage() {
        return nbpage;
    }

    public void setNbpage(int nbpage) {
        this.nbpage = nbpage;
    }

    public Date getParution() {
        return parution;
    }

    public void setParution(Date parution) {
        this.parution = parution;
    }

    public int getEdition() {
        return edition;
    }

    public void setEdition(int edition) {
        this.edition = edition;
    }

    public String getDimension() {
        return dimension;
    }

    public void setDimension(String dimension) {
        this.dimension = dimension;
    }

    public float getPoids() {
        return poids;
    }

    public void setPoids(float poids) {
        this.poids = poids;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    

    

    public boolean getActifLivre() {
        return actifLivre;
    }

    public void setActifLivre(boolean actifLivre) {
        this.actifLivre = actifLivre;
    }

    public ArrayList<MotClef> getListmc() {
        return listmc;
    }

    public void setListmc(ArrayList<MotClef> listmc) {
        this.listmc = listmc;
    }

    public SousTheme getSousTheme() {
        return sousTheme;
    }

    public void setSousTheme(SousTheme sousTheme) {
        this.sousTheme = sousTheme;
    }

    public Taxe getTva() {
        return tva;
    }

    public void setTva(Taxe tva) {
        this.tva = tva;
    }
    
     @Override
    public String toString() {
        return titre;
    }

 
    
    
           
    
}
