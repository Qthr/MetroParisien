/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metroparisien;

import java.util.Collection;
import java.util.HashMap;

/**
 *
 * @author quentin
 */
public class TableStations extends HashMap<Integer,Station> {
    
    public Station getByName(String nom){
        Collection<Station> ensembleStations = this.values();
        for(Station s : ensembleStations){
            if(s.getNom().equals(nom))
                return s;
        }
        return null;
    }
    
    
}
