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
	private ArrayList<ArrayList<Case>> chateaux;
	private ArrayList<Teleporteur> teleporteur;
	private String nom;
	private String famille;
	private final boolean boolValue;
	private int nbActions;

	public Joueur(String nom, boolean boolValue) {
		this.nom = nom;
		this.boolValue = boolValue;
		listeDePions = new ArrayList<Pion>();
		chateaux = new ArrayList<ArrayList<Case>>();
		teleporteur = new ArrayList<Teleporteur>();
		nbActions = 0;
	}

	public Pion getCommandant() {
		return commandant;
	}

	public void setCommandant(Pion commandant) {
		System.out.println("Nomme commandant " + commandant);
		if (commandant != null) {
			commandant.setCommandant(true);
		}
		this.commandant = commandant;
	}

	public ArrayList<Pion> getListeDePions() {
		return listeDePions;
	}

	public String getNom() {
		return nom;
	}

	public ArrayList<ArrayList<Case>> getChateaux() {
		return chateaux;
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
		if (nbActions >= 30) {
			nbActions = 30;
		}
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

	boolean commandantMort() {
		return commandant == null;
	}

	boolean possede(Pion pion) {
		return listeDePions.contains(pion);
	}

	public void lierChateaux(ArrayList<Case> l) {
		ArrayList<Case> chateau = new ArrayList<Case>();
		chateau.addAll(l);
		chateaux.add(chateau);
	}

	public boolean toutConquis() {
		boolean resultat = true;
		for (ArrayList<Case> lc : chateaux) {
			resultat = resultat && ((Chateau) lc.get(0).getObstacle()).isConquis();
		}
		return resultat;
	}

	public boolean tousMort() {
		return listeDePions.isEmpty();
	}

	public void conquerir(Case c) {
		for (ArrayList<Case> l : chateaux) {
			if (l.contains(c)) {
				for (Case c1 : l) {
					((Chateau) c1.getObstacle()).conquerir();
				}
				break;
			}
		}
	}

	public boolean chateauPresent(Case c) {
		for (ArrayList<Case> l : chateaux) {
			if (l.contains(c)) {
				return true;
			}
		}
		return false;
	}
}
