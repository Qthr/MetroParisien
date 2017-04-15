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
public class Metro {
   
    private TableStations stations;             // Table de hachage : La clé est le nom des stations, elle permet l'accès à une liste de stations de même nom.
    private Trajet trajet;

    public Metro(String fichierSource) {
        
        this.stations = new TableStations();
        
        Chargement chargement = new Chargement(fichierSource, this);  
        chargement.run();
   
    }

    public TableStations getStations() {
        return stations;
    }
    
    
   
    public void trouverTrajet(String strSrc, String strDst){        // Ajouter exceptions 
                       
        ArrayList<Station> stationsSrc = stations.get(strSrc);      // On récupère toutes les stations de départ correspondant au nom entrée.
        ArrayList<Station> stationsDst = stations.get(strDst);      // On récupère toutes les stations d'arrivée correspondant au nom entrée.
        Dijkstra dijkstraFinal = null;
        int i = 0;
        for(Station src : stationsSrc){                             // On va appliquer Dijktra pour toutes les combinaisons station de départ/station d'arrivée possibles.
            for(Station dst : stationsDst){                         // On conservera les structures de données du Dijkstra qui a donné le meilleur résultat (la combinaison qui a le plus court chemin entre un src et un dst).
                Dijkstra dijkstra = new Dijkstra(src, dst);
                dijkstra.run();
              /*  System.out.println("\nTrajet n°"+i);      ->        Pour afficher tous les chemins trouvés
                Trajet trajet = dijkstra.toTrajet();                                           
                trajet.itineraire();
                i++;*/
                if( dijkstraFinal == null || dijkstraFinal.getValeurPCC() > dijkstra.getValeurPCC())        // On effectue les comparaisons sur le plus court chemin trouvé pour chaque combinaison.
                    dijkstraFinal = dijkstra;                                                                               // Si on trouve une meilleur combinaison on conserve le Dijkstra correspondant.
                
                    
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
