/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metroparisien;

/**
 *
 * @author quentin
 */
public class Application {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       Metro metro = new Metro("fichiers/metro.txt");
      // metro.afficher();
      // metro.trouverTrajet(args[1], args[2]);
      //metro.trouverTrajet("Bastille", "Couronnes");
     // metro.trouverTrajet("Montparnasse Bienvenue", "Châtelet");  Montparnasse : 4 correspondances et  Châtelet : 5 correspondances   -> 20 itérations sur Dijkstra
      metro.trouverTrajet("République", "Châtelet");  // République : 5 correspondances et  Châtelet : 5 correspondances   -> 25 itérations sur Dijkstra

      
    }
    
}
