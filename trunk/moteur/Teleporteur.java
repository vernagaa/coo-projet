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
public class Teleporteur {

    private Joueur joueur;
    private Case c;
    private int vie;
    private int typeTeleporteur;

    public Teleporteur(Joueur joueur, Case c) {
	this.joueur= joueur;
	vie = 50;
	this.c = c;
	if(true){
	    typeTeleporteur = Textures.SABLE;
	} else {
	    typeTeleporteur = Textures.GLACE;
	}
	//TODO Image differente selon teleporteur
    }
    
    public void diminuerVie(int force){
	this.vie -= force/2;
	if(vie < 0){
	    c.setTeleporteur(null);
	}
    }
    
    public ArrayList<Teleporteur> getListeTeleporteur(){
	return joueur.getTeleporteur();
    }
    
    public int getTypeTeleporteur(){
	return typeTeleporteur;
    }
    
    @Override
    public String toString(){
	return c.toString();
    }

    public Case getCase() {
	return c;
    }
    
    public boolean isDisponible(){
	return c.getPion() == null;
    }
}
