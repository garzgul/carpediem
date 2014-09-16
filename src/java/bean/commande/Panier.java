
package bean.commande;

import bean.produit.Livre;
import java.io.Serializable;
import java.util.HashMap;

public class Panier implements Serializable{
    private HashMap<Integer,LignePanier> lignes;
    private float prixHTTotal;
    private float tvaTotal;
    private float prixTTCTotal;

    public Panier() {
        lignes = new HashMap<>();
        prixHTTotal=0.0f;
        tvaTotal=0.0f;
        prixTTCTotal=0.0f;
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
                    lp.setQte(lp.getQte());
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
        if (existeDeja){
            
            lignes.remove(id);
        }
    }
    
    public float getPrixHTTotal(){
        float prixHT = 0.0f;
        for (LignePanier l:lignes.values()){
            prixHT+= l.getPrixHT()*l.getQte();
        }
        return prixHT;
    }
    
    public float getTvaTotal(){
        float tva = 0.0f;
        for (LignePanier l:lignes.values()){
            tva+= l.getTva()*l.getQte();
        }
        return tva;
    }
    
    public float getPrixTTCTotal(){
        float prixTTC = 0.0f;
        for (LignePanier l:lignes.values()){
            prixTTC+= l.getPrix();
        }
        return prixTTC;
    }
}
