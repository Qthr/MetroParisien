/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metroparisien;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 *
 * @author quentin
 */
public class Metro {
   
    private TableStations stations;
    private List<Station> trajet;

    public Metro(String fichierSource) {
        
        this.stations = new TableStations();
        this.trajet = new ArrayList<>();
        
        Chargement chargement = new Chargement(fichierSource, this);  
        chargement.run();
   
    }

    public TableStations getStations() {
        return stations;
    }
    
    
    // On prend n'importe quelle station correspondant au nom entré
    // On se souciera des problèmes de ligne lors de l'exploitation du résultat.
    // NB : La station de départ est la dernière station de même nom qui commence le trajet.
    // La station d'arrivée est la première station de même nom qui termine le le trajet.
    
    public void trouverTrajet(String src, String dst){
        Station stationSrc = stations.getByName(src);                     // Ajouter exceptions 
        Station stationDst = stations.getByName(dst);
        
        System.out.println("Départ : " +stationSrc.toString());
        System.out.println("Arrivé : " +stationDst.toString());
        System.out.println();

        Dijkstra dijkstra = new Dijkstra(stations, stationSrc, stationDst);
        dijkstra.run();
       
       Trajet trajet = dijkstra.toTrajet();
       
        trajet.itineraire();
                                                    
    }                                                       
    
    public void afficher(){
        Collection<Station> stats = stations.values();
        for(Station s : stats){
            System.out.println(s);
        }
    }
    
   
}
