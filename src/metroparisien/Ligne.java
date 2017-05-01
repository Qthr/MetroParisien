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
public class Ligne {
    
    private String nom;
    private ArrayList<Station> stations;
    
    public Ligne(String nom){
        this.nom = nom;
        this.stations = new ArrayList<Station>();
    }
    
   /*public Station trouverTerminus(Station precedente, Station suivante){
       
   }*/
}
