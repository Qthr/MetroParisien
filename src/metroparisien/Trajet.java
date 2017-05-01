/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metroparisien;

import java.time.Duration;
import java.util.ArrayList;

/**
 *
 * @author quentin
 */
public class Trajet {
    
    private ArrayList<Station> stations;
    private Duration duree;      // Mettre un type de durée.
    
    
    public Trajet(ArrayList<Station> stations, Duration duree){
        this.stations = stations;
        this.duree = duree;              
    }
    
    public void itineraire(){
        
        String itineraire = "Le parcours est : \n";
        for(Station s : stations){
            itineraire += " -> "+s.getNom();
        }
        System.out.println(itineraire);
        System.out.println("La durée du parcours est : "+duree.toMinutes()+" minute(s)");     // On affiche la durée de trajet en minutes (les secondes restantes n'ont pas une importance significative)

    }

    
}

