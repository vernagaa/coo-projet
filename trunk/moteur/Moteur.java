package moteur;

import exception.MapException;
import ihm.NouvellePartieGraphique;
import ihm.FenetreChoixPion;
import ihm.AireDeJeu;
import ihm.FenetrePrincipale;
import ihm.NouvellePartiePanel;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;
import moteur.classes.Tacticien;

/**
 *
 * @author Kévin
 */
public class Moteur implements Runnable, Serializable {

	private FenetrePrincipale fp;
	private Plateau plateau;
	public AireDeJeu aireDeJeu;
	private FenetreChoixPion fenetreChoixPion;
	NouvellePartieGraphique nouvellePartie;
	private boolean debutDePartie;
	private int tour;
	/*
	 * Gestion de la souris
	 */
	private boolean mouvementEnCours;
	private Case caseAncienne;
	private boolean attaqueEnCours;
	private boolean poserTeleporteur;
	private Joueur joueur1;
	private Joueur joueur2;
	private boolean joueurCourant;
	private boolean teleportationEnCours;
	private String mapPath;
	private boolean elireCommandant;

	public Plateau getPlateau() {
		return plateau;
	}

	public void setPlateau(Plateau plateau) {
		this.plateau = plateau;
	}

	public Moteur() {
		mapPath = "map/map5.map";
		plateau = new Plateau(mapPath);
		joueur1 = new Joueur("joueur1", true);
		joueur2 = new Joueur("joueur2", false);
		debutDePartie = true;
		joueurCourant = true;
		tour = 0;
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Moteur());
	}

	@Override
	public void run() {
		fp = new FenetrePrincipale(this);
		aireDeJeu = fp.getAireDeJeu();
		fenetreChoixPion = new FenetreChoixPion(this);
		nouvellePartie = new NouvellePartieGraphique(this);
//		NouvellePartiePanel np = new NouvellePartiePanel();
//		aireDeJeu.add(np);
//		np.setLocation(Case.TAILLE * 6, 0);
//		np.setSize(Case.TAILLE * 23, Case.TAILLE * 20);
//		np.setVisible(true);
	}

	public void caseCliqueBoutonGaucheNouvellePartie(Case c1) {
		aireDeJeu.setDebutDePartie(debutDePartie);
		if (nouvellePartie.getEtape() == 2 && nouvellePartie.getChoix()) {
			if (nouvellePartie.isJoueurCourant() && nouvellePartie.choixPossible(c1)) {
				Pion pion = FabriquePion.getPion(nouvellePartie.getFamille1(), nouvellePartie.getClasse(), c1);
				joueur1.ajouterPion(pion);
				if (pion instanceof Tacticien) {
					joueur1.setTacticien(pion);
				}
				nouvellePartie.pionAjouteJoueur1();
				nouvellePartie.setChoix(false);
			} else if (nouvellePartie.choixPossible(c1)) {
				Pion pion = FabriquePion.getPion(nouvellePartie.getFamille2(), nouvellePartie.getClasse(), c1);
				joueur2.ajouterPion(pion);
				if (pion instanceof Tacticien) {
					joueur2.setTacticien(pion);
				}
				nouvellePartie.pionAjouteJoueur2();
				nouvellePartie.setChoix(false);
			}

			if (nouvellePartie.getNbPions() == 8) {
				nouvellePartie.elireCommandant();
			}
		} else if (nouvellePartie.getEtape() == 3) {
			if (nouvellePartie.isJoueurCourant() && nouvellePartie.choixPossible(c1)) {
				joueur1.setCommandant(c1.getPion());
				nouvellePartie.elireCommandant();
			} else if (nouvellePartie.choixPossible(c1)) {
				joueur2.setCommandant(c1.getPion());
				aireDeJeu.setDebutDePartie(false);
				debutDePartie = false;
				aireDeJeu.remove(nouvellePartie);
				aireDeJeu.setJoueurCourant(joueurCourant);
			}
		}
		getJoueurCourant().setNbActions(10);
		fp.setLabelTour(tour);
		majInfosTour();
	}

	public boolean isDebutDePartie() {
		return debutDePartie;
	}

	public void caseCliqueBoutonGauche(Case c1) {
		aireDeJeu.setCaseSurvol(null);
		fenetreChoixPion.effacerFinDeTour();
		System.out.println("Clique gauche");
		if (elireCommandant) {
			if(c1.contientPion() && getJoueurCourant().possede(c1.getPion())){
				getJoueurCourant().setCommandant(c1.getPion());
				elireCommandant = false;
				//TODO Affichage
			}
			
		} else if (getJoueurCourant().actionPossibles()) {
			// Permet de gerer l'attaque d'une unite ennemie
			if (attaqueEnCours) {
				// On verifie que le case cible est attaquable par le pion et que le pion sur cette case est un pion ennemi
				if (caseAncienne.getPion().getListeAttaquePossible().contains(c1) /*
						 * &&
						 * joueurOppose().geListeDePions().contains(caseCourante.getPion())
						 */) {
					if (c1.contientPion()) {
						caseAncienne.getPion().attaquerPion(c1.getPion());
						if (getJoueurCourant().commandantMort()) {
							//TODO Elire un nouveau commandant
							elireCommandant = true;
							System.out.println("Commandant Mort");
						}
					} else if (c1.getObstacle() != null && c1.getObstacle().isDestructible()) {
						caseAncienne.getPion().attaquerObstacle(c1);
					} else if (c1.contientTeleporteur(getJoueurAdverse())) {
						System.out.println("TELEPORTEUR EN COURS DE DECES");
						caseAncienne.getPion().attaquerTeleporteur(c1);
					}
					utiliserAction();
				}
				// On signifie que l'attaque est fini
				attaqueEnCours = false;
				// Et qu'il ne faut plus afficher la zone d'attaque
				aireDeJeu.setAttaqueEnCours(attaqueEnCours);
			} // Permet de gerer l'utilisation d'une capaciteActive
			// Permet de gerer le calculDeplacementPossible d'un pion suite a un clic gauche sur celui ci
			else if (poserTeleporteur) {
				//TODO Cas a verifier ?
				System.out.println("Il faut poser un teleporteur");
				((Tacticien) caseAncienne.getPion()).poserTeleporteur(c1);
				poserTeleporteur = false;
				aireDeJeu.setAfficherPoseTeleporteur(false, c1);
				utiliserAction();
			} else if (teleportationEnCours) {
				if (getJoueurCourant().getTeleporteur().contains(c1.getTeleporteur())
						&& c1.contientPion()) {
					caseAncienne.getPion().deplacerPionTeleportation(c1);
					utiliserAction();
				}
				teleportationEnCours = false;
				aireDeJeu.afficherTeleporteurDisponible(teleportationEnCours, c1);
			} else if (mouvementEnCours && caseAncienne.getPion().deplacementPossible(c1)) {
				if (caseAncienne != c1) {
					caseAncienne.getPion().deplacerPion(c1);
					utiliserAction();
				}
				// On specifie que le mouvement est termine
				mouvementEnCours = false;
				// On indique qu'il ne faut plus afficher les mouvements possibles
				aireDeJeu.afficherMouvement(mouvementEnCours, c1);

				//Si fin sur une case telportation
				if (c1.getTeleporteur() != null) {
					System.out.println("Je peux me teleporter");
					//Surbrillance des cases teleportations
					//teleportation en cours
					caseAncienne = c1;
					teleportationEnCours = true;
					aireDeJeu.afficherTeleporteurDisponible(teleportationEnCours, c1);
				}
				// Afficher Selection Orientation
				//TODO Afficher Orientation
			} // Selectionne un Pion pour le deplacer
			else if (c1.contientPion()) {
				if (getJoueurCourant().getListeDePions().contains(c1.getPion())) {
					// On specifie qu'un mouvement est en cours
					mouvementEnCours = true;
					// On indique qu'il faut afficher les mouvements possibles
					aireDeJeu.afficherMouvement(mouvementEnCours, c1);
					// On calcul les deplacements possible                  
					c1.getPion().calculDeplacementPossible();
					// On memorise la case ou se trouve le pion a deplacer
					caseAncienne = c1;
				}
			} else {
				fenetreChoixPion.effacerFenetre();
				// On specifie que le mouvement est termine
				mouvementEnCours = false;
				// On indique qu'il ne faut plus afficher les mouvements possibles
				aireDeJeu.afficherMouvement(mouvementEnCours, c1);
			}

		} else {
			fenetreChoixPion.effacerFenetre();
			// On specifie que le mouvement est termine
			mouvementEnCours = false;
			// On indique qu'il ne faut plus afficher les mouvements possibles
			aireDeJeu.afficherMouvement(mouvementEnCours, c1);
		}
		aireDeJeu.repaint();
	}

	public void caseCliqueBoutonDroit(Case c) {
		fenetreChoixPion.effacerFinDeTour();
		if (c.contientPion() && getJoueurCourant() == c.getPion().getJoueur()) {
			caseAncienne = c;
			// On efface la fenetre
			fenetreChoixPion.effacerFenetre();
			// On la place a l'endroit voulu
			fenetreChoixPion.placerFenetre(c);
			// On specifie que le mouvement est termine
			mouvementEnCours = false;
			// On indique qu'il ne faut plus afficher les mouvements possibles
			aireDeJeu.afficherMouvement(mouvementEnCours, c);
			// On memorise la case choisie lors du clic
			caseAncienne = c;
		}
	}

	public void caseSurvol(Case c1) {
//		aireDeJeu.setCaseSurvol(null);
		if (c1 != null) {
			if (mouvementEnCours) {
				caseSurvolMouvement(c1);
			} else if (attaqueEnCours) {
				caseSurvolAttaque(c1);
			} else if (c1.contientPion()) {
				caseSurvolPion(c1);
			} else {
				aireDeJeu.setCaseSurvol(c1);
			}
		} else {
			aireDeJeu.setCaseSurvol(null);
		}
		aireDeJeu.repaint();
	}

	private void caseSurvolMouvement(Case c1) {
		c1.getPion().afficherDeplacement(c1);
	}

	private void caseSurvolAttaque(Case c1) {
		aireDeJeu.suvolAfficherAttaque(c1);
	}

	private void caseSurvolPion(Case c1) {
		aireDeJeu.setCaseSurvol(c1);
	}

	public void setAttaqueEnCours(boolean b) {
		attaqueEnCours = b;
	}

	public void setPoseTeleporteur(boolean b) {
		poserTeleporteur = b;
	}

	public Joueur getJoueur1() {
		return joueur1;
	}

	public void setJoueur1(Joueur joueur1) {
		this.joueur1 = joueur1;
	}

	public Joueur getJoueur2() {
		return joueur2;
	}

	public void setJoueur2(Joueur joueur2) {
		this.joueur2 = joueur2;
	}

	public void changementJoueur() {
		joueurCourant = !joueurCourant;
		aireDeJeu.setJoueurCourant(joueurCourant);

		getJoueurCourant().finDeTour();

		if (getJoueurCourant().getTacticien() != null) {
			((Tacticien) getJoueurCourant().getTacticien()).decrementerCDTeleporteur();
		}
		if (joueurCourant) {
			tour++;
			fp.setLabelTour(tour);
		}
		majInfosTour();
	}

	public Joueur getJoueurCourant() {
		if (joueurCourant) {
			return joueur1;
		} else {
			return joueur2;
		}
	}

	public Joueur getJoueurAdverse() {
		if (joueurCourant) {
			return joueur2;
		} else {
			return joueur1;
		}
	}

	public void utiliserAction() {
		getJoueurCourant().utiliserAction();
		fp.setLabelAction(getJoueurCourant().getNbActions());
	}

	public void majInfosTour() {
		fp.setLabelJoueur(getJoueurCourant().getNom());
		fp.setLabelAction(getJoueurCourant().getNbActions());
	}

	//TODO RAJOUTER DES TEXTURES
	//TODO Actuellement teleporteur incompatible avec les bordures, changer comportement en cas de bordure
	public void setDebutDePartie(boolean debutDePartie) {
		this.debutDePartie = debutDePartie;
	}

	public void setNouvellePartie(NouvellePartieGraphique nouvellePartie) {
		this.nouvellePartie = nouvellePartie;
	}

	public void nouvellePartie() {
		try {
			if (isDebutDePartie()) {
				nouvellePartie.setVisible(false);
			}
			debutDePartie = true;
			plateau.init(mapPath);
			aireDeJeu.nouvellePartie();
			tour = 0;
			joueur2.setNbActions(0);
			nouvellePartie = new NouvellePartieGraphique(this);
		} catch (MapException ex) {
		}
	}
	//TODO Conquerir
	//TODO Conquerir
}
