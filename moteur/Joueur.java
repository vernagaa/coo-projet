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
	private ArrayList<Teleporteur> teleporteur;
	private String nom;
	private String famille;
	private final boolean boolValue;
	private int nbActions;

	public Joueur(String nom, boolean boolValue) {
		this.nom = nom;
		this.boolValue = boolValue;
		listeDePions = new ArrayList<Pion>();
		placesConquises = new ArrayList<Case>();
		teleporteur = new ArrayList<Teleporteur>();
		nbActions = 0;
	}

	public Pion getCommandant() {
		return commandant;
	}

	public void setCommandant(Pion commandant) {
		System.out.println("Nomme commandant " + commandant);
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

	public ArrayList<Teleporteur> getTeleporteur() {
		return teleporteur;
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

	public void ajouterPion(Pion pion) {
		listeDePions.add(pion);
		pion.setJoueur(this);
	}

	public void enleverPion(Pion pion) {
		listeDePions.remove(pion);
		pion = null;
	}

	public boolean tacticienEnVie() {
		return tacticien != null;
	}

	public Pion getTacticien() {
		return tacticien;
	}

	public void setTacticien(Pion tacticien) {
		this.tacticien = tacticien;
	}

	public void ajouterTeleporteur(Teleporteur teleporteur) {
		this.teleporteur.add(teleporteur);
	}

	public void enleverTeleporteur(Teleporteur teleporteur) {
		this.teleporteur.remove(teleporteur);
		teleporteur = null;
	}

	public boolean getBoolValue() {
		return boolValue;
	}

	public void finDeTour() {
		nbActions += 10;
		for (Pion p : listeDePions) {
			p.finDeTour();
		}
	}

	public void utiliserAction() {
		nbActions--;
	}

	public boolean actionPossibles() {
		return nbActions > 0;
	}

	public int getNbActions() {
		return nbActions;
	}

	public void setNbActions(int nbActions) {
		this.nbActions = nbActions;
	}
}
