
package bean.commande;

import bean.produit.Livre;
import java.io.Serializable;
import java.util.HashMap;

public class Panier implements Serializable{
    private HashMap<Integer,LignePanier> lignes;

    public Panier() {
        lignes = new HashMap<>();
    }

    public HashMap<Integer, LignePanier> getLignes() {
        return lignes;
    }
    
    
    
    public void setLignes(HashMap<Integer, LignePanier> lignes) {
        this.lignes = lignes;
    }
    
    public boolean update(int id, int delta){
        boolean ok = false;
        if(lignes.containsKey(id)){
            LignePanier lp = lignes.get(id);
            lp.setQte(lp.getQte()+delta);
            
            if(lp.getQte()<=0){
                lignes.remove(id);
            }else{
                if(lp.getQte()>lp.getL().getStock()){
                    lp.setQte(lp.getL().getStock());
                }
            }
            ok = true;
        }
        return ok;
    }
    
    public void addlivre(Livre l) throws Exception{
        if (l== null){
            return;
        }
        int id = l.getId();
        boolean existeDeja = update(id, 1);
        if (!existeDeja){
            LignePanier lp = new LignePanier(l);
            lignes.put(id, lp);
        }
    }
    public void enleverLivre(Livre l) throws Exception{
        if (l== null){
            return;
        }
        int id = l.getId();
        boolean existeDeja = update(id, 1);
        if (!existeDeja){
            LignePanier lp = new LignePanier(l);
            lignes.remove(id, lp);
        }
    }
    
}
