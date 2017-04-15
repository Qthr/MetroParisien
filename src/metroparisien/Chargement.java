/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metroparisien;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

/**
 *
 * @author quentin
 */
public class Chargement {                   // On utilise deux tables de hachage :
                                            // La premier temporaire : <IDstation, Station> permet de charger les liens lors du chargement du fichier source.
    private Path fichier;                   // La seconde permanente : <NOMStation,LISTEStations> permet une meilleur exploitation des stations d'entrée/sortie saisit par l'utilisateur. (Temps constant pour les stations sans correspondance, juste une petite liste à parcourir pour celles avec correspondances, facilite l'application de dijkstra à toutes les combinaison possible -> on parcourt juste pour chaque Station src, chaque station dst..)
    private Metro metro;
    
    public Chargement(String fichier, Metro metro){
        this.fichier= Paths.get(fichier);
        this.metro = metro;
    }
    
    public void run(){
        try{
            List<String> lignes = Files.readAllLines(this.fichier, StandardCharsets.UTF_8);
            HashMap<Integer, Station> tmp = new HashMap<>();                                // On se sert d'une table de hachage temportaire pour charger les stations et les liens.
            for(String ligne : lignes){                                                     // La clé est l'id d'une station et la valeur est la station, ce qui permet de charger ensuite chaque lien en temps constant.
                if(ligne.charAt(0) == 'V'){
                    int id = Integer.parseInt(ligne.substring(2, 6));
                    String nom = ligne.substring(7);
                    Station s = new Station(id, nom);
                    tmp.put(id, s);
                }
                else if(ligne.charAt(0) == 'E'){
                    StringTokenizer stk = new StringTokenizer(ligne.substring(2));
                    int idStation1 = Integer.parseInt(stk.nextToken());
                    int idStation2 = Integer.parseInt(stk.nextToken());
                    int duree = Integer.parseInt(stk.nextToken());
                    Station station1 = tmp.get(idStation1);
                    Station station2 = tmp.get(idStation2);
                    Lien l1 = new Lien(station1, station2, duree );
                    Lien l2 = new Lien(station2, station1, duree );
                    station1.getLiens().add(l1);
                    station2.getLiens().add(l2);
                }
            }
            metro.getStations().ajouterStations(tmp.values());                              // Si l'on souhaite obtenir la station d'arrivée et de départ dont les noms sont entrées par l'utilisateur, la table de hachage tmp n'est pas la structure la mieux adaptée.
        } catch(IOException e)                                                              // Elle impliquerait de parcourir toute la table pour rechercher les stations.
        {                                                                                   // C'est pourquoi la structure de données qu'on utilise pour stocker les stations dans un métro est une table de hachage dont la clé est le nom de la station.
            System.out.println("Erreur lors du chargement du fichier source.");             // Un même nom pouvant correspondre à plusieurs stations, la valeur de la table sera une liste de station (et non juste une station).
        }
        
        
    }
}
