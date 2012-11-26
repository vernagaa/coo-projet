/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package moteur;

import java.util.ArrayList;

/**
 *
 * @author chappelk
 */
public final class Joueur {

    private ArrayList<Pion> listeDePions;
    private Pion commandant;
    private ArrayList<Case> placesConquises;
    private String nom;
    
    public Joueur(String nom){
        this.nom = nom;
        listeDePions = new ArrayList<Pion>();
        placesConquises = new ArrayList<Case>();
    }

    public Pion getCommandant() {
        return commandant;
    }

    public void setCommandant(Pion commandant) {
        this.commandant = commandant;
    }

    public ArrayList<Pion> getListeDePions() {
        return listeDePions;
    }

    public String getNom() {
        return nom;
    }

    public ArrayList<Case> getPlacesConquises() {
        return placesConquises;
    }
}
