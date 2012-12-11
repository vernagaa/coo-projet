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

	/**
	 * Constructeur du joueur
	 * @param nom
	 * @param boolValue
	 */
	public Joueur(String nom, boolean boolValue) {
		this.nom = nom;
		this.boolValue = boolValue;
		listeDePions = new ArrayList<Pion>();
		chateaux = new ArrayList<ArrayList<Case>>();
		teleporteur = new ArrayList<Teleporteur>();
		nbActions = 0;
	}

	/**
	 * Accesseur du commandant du joueur
	 * @return
	 */
	public Pion getCommandant() {
		return commandant;
	}

	/**
	 * Affecteur du commandant du joueur
	 * @param commandant
	 */
	public void setCommandant(Pion commandant) {
		if (commandant != null) {
			commandant.setCommandant(true);
		}
		this.commandant = commandant;
	}

	/**
	 * Accesseur de la liste des pions du joueurs
	 * @return
	 */
	public ArrayList<Pion> getListeDePions() {
		return listeDePions;
	}

	/**
	 * Accesseur du pseudo du joueur
	 * @return
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * Accesseur des chateaux du joueurs
	 * @return
	 */
	public ArrayList<ArrayList<Case>> getChateaux() {
		return chateaux;
	}

	/**
	 * Accesseur des téléporteurs du joueur
	 * @return
	 */
	public ArrayList<Teleporteur> getTeleporteur() {
		return teleporteur;
	}

	/**
	 * Accesseur de la famille du joueur
	 * @return
	 */
	public String getFamille() {
		return famille;
	}

	/**
	 * Affecteur de la famille du joueur
	 * @param famille
	 */
	public void setFamille(String famille) {
		this.famille = famille;
	}

	/**
	 * Affecteur du pseudo du joueur
	 * @param nom
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * Ajoute un pion à la liste des pions
	 * @param pion
	 */
	public void ajouterPion(Pion pion) {
		listeDePions.add(pion);
		pion.setJoueur(this);
	}

	/**
	 * Retire un pion à la liste des pions
	 * @param pion
	 */
	public void enleverPion(Pion pion) {
		listeDePions.remove(pion);
		pion = null;
	}

	/**
	 * Renvoie si le tacticien est toujours en vie
	 * @return
	 */
	public boolean tacticienEnVie() {
		return tacticien != null;
	}

	/**
	 * Renvoie le tacticien du joueur
	 * @return
	 */
	public Pion getTacticien() {
		return tacticien;
	}

	/**
	 * Affecteur du tacticien du joueur
	 * @param tacticien
	 */
	public void setTacticien(Pion tacticien) {
		this.tacticien = tacticien;
	}

	/**
	 * Ajoute un téléporteur à la liste des téléporteur du joueur
	 * @param teleporteur
	 */
	public void ajouterTeleporteur(Teleporteur teleporteur) {
		this.teleporteur.add(teleporteur);
	}

	/**
	 * Reitire un téléporteur à la liste des téléporteurs
	 * @param teleporteur
	 */
	public void enleverTeleporteur(Teleporteur teleporteur) {
		this.teleporteur.remove(teleporteur);
		teleporteur = null;
	}

	/**
	 * Vrai joueur 1 faux joueur 2
	 * @return
	 */
	public boolean getBoolValue() {
		return boolValue;
	}

	/**
	 * Fini le tour en re créditant les actions et les mouvements
	 * pré-cond : nbActions >=0
	 * post-cond : 0 <= nbAction <= 30
	 */
	public void finDeTour() {

		nbActions += 10;
		if (nbActions >= 30) {
			nbActions = 30;
		}
		for (Pion p : listeDePions) {
			p.finDeTour();
		}
	}

	/**
	 * Utilise une action
	 * pré-cond : nbActions > 0
	 * post-cond : nbActions >= 0
	 */
	public void utiliserAction() {
		nbActions--;
	}

	/**
	 * Renvoie vrai s'il reste des actions possibles
	 * @return
	 */
	public boolean actionPossibles() {
		return nbActions > 0;
	}

	/**
	 * Renvoie le nombre d'actions
	 * @return
	 */
	public int getNbActions() {
		return nbActions;
	}

	/**
	 * Affecteur du nombre d'actions
	 * @param nbActions
	 */
	public void setNbActions(int nbActions) {
		this.nbActions = nbActions;
	}

	/**
	 * Renvoie si le commandant est mort
	 * @return 
	 */
	public boolean commandantMort() {
		return commandant == null;
	}

	/**
	 * Renvoie si le pion appartient au joueur
	 * @param pion
	 * @return 
	 */
	public boolean possede(Pion pion) {
		return listeDePions.contains(pion);
	}

	/**
	 * lie des chateaux au joueur
	 * @param l
	 */
	public void lierChateaux(ArrayList<Case> l) {
		ArrayList<Case> chateau = new ArrayList<Case>();
		chateau.addAll(l);
		chateaux.add(chateau);
	}

	/**
	 * Renvoie si tous les chateaux du joueur ont été conquis
	 * @return
	 */
	public boolean toutConquis() {
		
		if(chateaux.isEmpty()) {
			return false;
		}
		for (ArrayList<Case> lc : chateaux) {
			if(!((Chateau) lc.get(0).getObstacle()).isConquis()) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Renvoie si tous les pions du joueur sont morts
	 * @return
	 */
	public boolean tousMort() {
		return listeDePions.isEmpty();
	}

	/**
	 * Conquiert toute les case d'un chateau
	 * @param c
	 */
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

	/**
	 * Renvoie si le chateau appartient au joueur
	 * @param c
	 * @return
	 */
	public boolean chateauPresent(Case c) {
		for (ArrayList<Case> l : chateaux) {
			if (l.contains(c)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Réinitialise le joueur
	 */
	public void init(){
		listeDePions = new ArrayList<Pion>();
		chateaux = new ArrayList<ArrayList<Case>>();
		teleporteur = new ArrayList<Teleporteur>();
		nbActions = 0;
		tacticien = null;
		commandant = null;
	}
}
