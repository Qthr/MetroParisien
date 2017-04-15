/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metroparisien;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 *
 * @author quentin
 */
public class Dijkstra {

    public static final int INF = Integer.MAX_VALUE ;       // Définition d'une valeur infinie qui représentera la distance du sommet de départ au sommet non découvert.
                                                            
    private Station src, dst;                               // La station de départ et la station d'arrivée
    private int valeurPCC;                                  // Valeur de retour du plus court chemin de la station de départ à la station d'arrivée.
    private HashMap<Station, Integer> distancesPCC;         // Tableau des distances des plus courts chemins du sommet de départ aux autres sommets.
    private HashMap<Station,Station> predecesseurs;         // Tableau de l'arborescence couvrante des plus courts chemins
    private HashSet<Station> stationsTraitees;              // Ensemble des sommets traités
    private SortedSet<Station> stationsNonTraitees;         // Ensemble de stations à traiter (Elles seront triées grâce au comparator suivant)
    private Comparator comp = new Comparator(){             // Comparator qui définie la relation d'ordre suivante : triées les stations par ordre croissant de distance par rapport au sommet de départ
        @Override
        public int compare(Object t, Object t1)
	{
            Station s = (Station) t;
            Station s1 = (Station) t1;
            int res = getDistancePCC(s) - getDistancePCC(s1);
            return (res == 0) ? s.compareTo(s1) : res;
        } 
    };
            
    public Dijkstra(Station src, Station dst){
        this.src = src;
        this.dst = dst;
        this.distancesPCC = new HashMap<Station, Integer>();
        this.predecesseurs = new HashMap<Station, Station>();
        this.stationsNonTraitees = new TreeSet<Station>(comp);  // On indique que cet ensemble sera trié selon le comparator comp.
        this.stationsTraitees = new HashSet<Station>();
    }
 
    public void run(){
        init(src);
        Station s = extrairePlusProcheStation();            // On récupère le premier sommet à traiter (celui qui a la plus petite distance au sommet de départ parmi tous les sommets à taiter)
        while( s != null && !s.equals(dst)){                // On cherche le plus court chemin jusqu'à :                               
            ArrayList<Lien> liens = s.getLiens();           //         - traiter toutes les stations
            for(Lien l : liens){                            //          - atteindre la station d'arrivée (il n'y a pas de coût négatif donc cela suffit pour avoir la distance la plus courte) 
                if(!dejaTraitee(l.getStationDst()))         // On récupère tous les liens de la station
                    relacher(l);                            // On relache seulement les liens dont la station de destination n'a pas déja été traitée (si c'est le cas cela signifie qu'une plus courte distance a déjà été trouvée pour cette station)
            }
            stationsTraitees.add(s);
            s = extrairePlusProcheStation();
        }
    }
    
    public void init(Station src){          // Initialisation
        distancesPCC.clear();               
        predecesseurs.clear();              
        stationsNonTraitees.clear();        // On nettoie toutes les structures de données au cas où.
        stationsTraitees.clear();
        distancesPCC.put(src, 0);           // La distance du sommet de départ à lui même à 0
        predecesseurs.put(src, null);       // Le prédécésseur du sommet de départ est null
        stationsNonTraitees.add(src);       // On ajoute le sommet de départ dans l'ensemble des sommets à traiter.

    }
    
    public void relacher(Lien l){                                   // On relache un lien : regarde s'il y a un chemin plus court pour aller du sommet départ au sommet de destination du lien en passant par le sommet source du lien.   
        Station src = l.getStationSrc();
        Station dst = l.getStationDst();
        int duree = l.getDuree();
        int lienTendu = getDistancePCC(dst);
        int lienRelache = getDistancePCC(src) + duree;
        if(lienTendu > lienRelache){                                // Si c'est le cas on prend en compte ce nouveau chemin :
            stationsNonTraitees.remove(dst);                            
            distancesPCC.put(dst, new Integer(lienRelache));        //          - on ajoute la nouvelle plus courte distance dans le tableau des distancesPCC
            predecesseurs.put(dst, src);                            //          - la source du lien devient le père de la destination dans le tableau d'arborescance
            stationsNonTraitees.add(dst);                           // On ajoute la station destination du lien à l'ensemble des sommets à traiter (elle sera triée par ordre de distance croissant au sommet de départ grâce au comparator) 
                                                                    // Note : stationsNonTraitees.remove(dst); est utile dans le cas d'un sommet qui est déja en attente de traitement (pour lequel on a déja trouvé un chemin)
        }                                                           // Si on peut effectuer un relachement vers ce sommet, cela signifie qu'on a trouvé un nouveau plus court chemin vers ce sommet, on est obligé de le supprimer de l'ensemble, modifier sa nouvelle distance, et le rajouter dans l'ensemble pour qu'il soit trié correctement.
        valeurPCC = getDistancePCC(dst);                            // On conserve la valeur du plus cours chemin à la fin de l'algorithme. (Aussi disponible dans la table distance, mais plus propre).
    }   
    
    public Station extrairePlusProcheStation(){                      // Extrait la station actuellement la plus proche du sommet de départ, c'est à dire la première station dans l'ensemble stationsNonTraitees.
        if(stationsNonTraitees.isEmpty())                            // Et la supprime de l'ensemble.
            return null;        
        Station s = stationsNonTraitees.first();
        stationsNonTraitees.remove(s);
        return s;
    }
    
     /**
   * Détermine si oui ou non une station a déjà été visitée, c'est-à-dire
   * si une plus courte distance a déjà été trouvée.
   */
  private boolean dejaTraitee(Station station) {
	return stationsTraitees.contains(station) ;
  }
    
    
    public int getDistancePCC(Station s) {                          // Permet de récupérer dans la table de hachage la distance du plus court chemin de la station de départ à une station entrée (la clé)
        Integer distance = distancesPCC.get(s) ;                    // Si la station n'existe pas dans la table, elle n'a pas encore été découverte, on suppose que sa distance est à l'infini.
	return (distance == null) ? INF : distance.intValue() ;
    }

    
    public Trajet toTrajet(){
        ArrayList stationsTrajet = new ArrayList();
        Station s = dst;
        
	while (	s != null ) {
            stationsTrajet.add(s) ;
             s = getPredecesseur(s);
	}
        Collections.reverse(stationsTrajet);
        return new Trajet(stationsTrajet, getDistancePCC(dst));
    }
    
    public Station getPredecesseur(Station fils){
        return predecesseurs.get(fils);
    }
    
    
    // Pour appliquer Dijkstra à toutes les combinaisons possibles, fonctions nécéssaires pour TrouverTrajet() dans Metro
    // On récupèrera cette valeur pour chaque Dijktra effectué, celui qui possèdera la plus petite fera intervenir la meilleur combinaison src/dst.
    public int getValeurPCC() {
        return valeurPCC;
    }
    
}
