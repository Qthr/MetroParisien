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
import java.util.Map;
import java.util.StringTokenizer;

/**
 *
 * @author quentin
 */
public class Chargement {
    
    private Path fichier;
    private Metro metro;
    
    public Chargement(String fichier, Metro metro){
        this.fichier= Paths.get(fichier);
        this.metro = metro;
    }
    
    public void run(){
        try{
            List<String> lignes = Files.readAllLines(this.fichier, StandardCharsets.UTF_8);
            for(String ligne : lignes){
                if(ligne.charAt(0) == 'V'){
                    int id = Integer.parseInt(ligne.substring(2, 6));
                    String nom = ligne.substring(7);
                    Station s = new Station(id, nom);
                    metro.getStations().put(id, s);
                }
                else if(ligne.charAt(0) == 'E'){
                    StringTokenizer stk = new StringTokenizer(ligne.substring(2));
                    int idStation1 = Integer.parseInt(stk.nextToken());
                    int idStation2 = Integer.parseInt(stk.nextToken());
                    int duree = Integer.parseInt(stk.nextToken());
                    Station station1 = metro.getStations().get(idStation1);
                    Station station2 = metro.getStations().get(idStation2);
                    Lien l1 = new Lien(station1, station2, duree );
                    Lien l2 = new Lien(station2, station1, duree );
                    station1.getLiens().add(l1);
                    station2.getLiens().add(l2);

                    
                }
            }   
        } catch(IOException e)
        {
            System.out.println("Erreur lors du chargement du fichier source.");
        }
        
        
    }
}
