/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metroparisien;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author quentin
 */
public class Station {
    
    private int id;
    private String nom;
    private ArrayList<Lien> liens;
    
    public Station(int id, String nom){
        this.id = id;
        this.nom = nom;
        this.liens = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public ArrayList<Lien> getLiens() {
        return liens;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setLiens(ArrayList<Lien> liens) {
        this.liens = liens;
    }
    
    
   /* @Override
    public String toString(){
        String str ="";
        for(Lien l : this.liens)
            str += l+" | ";
        str = "Station n°"+id+"\n Nom : "+nom+"\n Liens : " +str;
        return str;
    }*/    
      @Override
    public String toString(){
        return getNom();
    }

    int compareTo(Station s1) {
        return this.getId() - s1.getId();
    }
    
    
}
