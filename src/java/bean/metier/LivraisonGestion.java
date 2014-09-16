
package bean.metier;

import bean.commande.ModeLivraison;
import java.util.ArrayList;

public class LivraisonGestion {
    private ArrayList<String> listeLivraison;

    public LivraisonGestion() {
        listeLivraison = new ArrayList<>();
    }

    public ArrayList<String> getListeLivraison() {
        // voir avec Duc pour gerer la recuperation de l'enum
        for (ModeLivraison ml: ModeLivraison.values()){
            listeLivraison.add(ml.toString());
        }
        
        return listeLivraison;
    }
    
    
    
    
}
