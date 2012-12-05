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
	private Pion tacticien;
    private ArrayList<Case> placesConquises;
    private String nom;
	private String famille;
    
    public Joueur(String nom){
        this.nom = nom;
        listeDePions = new ArrayList<Pion>();
        placesConquises = new ArrayList<Case>();
    }

    public Pion getCommandant() {
        return commandant;
    }

    public void setCommandant(Pion commandant) {
	commandant.setCommandant(true);
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

	public String getFamille() {
		return famille;
	}

	public void setFamille(String famille) {
		this.famille = famille;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public void ajouterPion(Pion pion){
		listeDePions.add(pion);
	}
	
	public boolean tacticienEnVie(){
		return tacticien != null;
	}

	public Pion getTacticien() {
		return tacticien;
	}

	public void setTacticien(Pion tacticien) {
		this.tacticien = tacticien;
	}
	
	
}
