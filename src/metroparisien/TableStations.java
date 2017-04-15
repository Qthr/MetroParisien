/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metroparisien;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

/**
 *
 * @author quentin
 */
public class TableStations extends HashMap<String,ArrayList<Station>> {
    
    public void ajouterStations(Collection<Station> ensembleStation){
        for(Station s : ensembleStation){
            if(!this.containsKey(s.getNom())){
                ArrayList<Station> liste = new ArrayList<>();
                this.put(s.getNom(), liste);
                liste.add(s);
            }
            else
                this.get(s.getNom()).add(s);
        }
    }
    
    
}
