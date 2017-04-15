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
public class Lien {
    
    private Station stationSrc;
    private Station stationDst;
    int duree;
    
    public Lien(Station src, Station dst, int duree){
        this.stationSrc = src;
        this.stationDst = dst;
        this.duree = duree;
    }

    public Station getStationSrc() {
        return stationSrc;
    }

    public Station getStationDst() {
        return stationDst;
    }

    public int getDuree() {
        return duree;
    }

    public void setStationSrc(Station stationSrc) {
        this.stationSrc = stationSrc;
    }

    public void setStationDst(Station stationDst) {
        this.stationDst = stationDst;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }
    
    @Override
    public String toString(){
        return "-> "+this.stationDst.getNom()+" ("+this.duree+")";
    }
}
