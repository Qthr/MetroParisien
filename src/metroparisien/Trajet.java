/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metroparisien;

import java.util.ArrayList;

/**
 *
 * @author quentin
 */
public class Trajet {
    
    private ArrayList<Station> stations;
    private int duree;      // Mettre un type de durée.
    
    
    public Trajet(ArrayList<Station> stations, int duree){
        this.stations = stations;
        this.duree = duree;              
    }
    
    public void itineraire(){
        
        String itineraire = "Le parcours est : \n";
        for(Station s : stations){
            itineraire += " -> "+s.getNom();
        }
        System.out.println(itineraire);
        System.out.println("La durée du parcours est : "+duree);

    }

    
}

