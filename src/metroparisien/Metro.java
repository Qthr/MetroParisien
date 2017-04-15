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
    private Trajet trajet;

    public Metro(String fichierSource) {
        
        this.stations = new TableStations();
        
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
    
    public void trouverTrajet(String strSrc, String strDst){
                       // Ajouter exceptions 
        
        
        ArrayList<Station> stationsSrc = stations.get(strSrc);
        ArrayList<Station> stationsDst = stations.get(strDst);
        Dijkstra dijkstraFinal = null;
        Station dstFinal = null;
        int i = 0;
        for(Station src : stationsSrc){
            for(Station dst : stationsDst){
                Dijkstra courant = new Dijkstra(src, dst);
                courant.run();
                System.out.println("\nTrajet n°"+i);
                Trajet trajet = courant.toTrajet();       
                trajet.itineraire();
                i++;
                if( dijkstraFinal == null || dijkstraFinal.getDistancePCC(dstFinal) > courant.getDistancePCC(dst)){
                    dijkstraFinal = courant;
                    dstFinal = dst;
                }
                    
            }
        }
        
        System.out.println("Version 2 ");
        System.out.println("Départ : ");
        System.out.println("Arrivé : ");
        System.out.println();
       
       Trajet trajet = dijkstraFinal.toTrajet();       
        trajet.itineraire();
                                                    
    }                                                       
    /*
    public void afficher(){
        Collection<Station> stats = stations.values();
        for(Station s : stats){
            System.out.println(s);
        }
    }
    */
   
}
