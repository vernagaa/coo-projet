package moteur;

import exception.MapException;
import ihm.*;
import java.io.*;
import java.util.ArrayList;
import javax.swing.SwingUtilities;
import moteur.classes.Tacticien;

/**
 *
 * @author KÃ©vin
 */
public class Moteur implements Runnable, Serializable {

	private FenetrePrincipale fp;
	private Plateau plateau;
	public AireDeJeu aireDeJeu;
	public Animation animation;
	private FenetreChoixPion fenetreChoixPion;
	NouvellePartiePanel nouvellePartie;
	private boolean debutDePartie;
	private int tour;
	/*
	 * Gestion de la souris
	 */
	public Case caseCourante;
	private boolean mouvementEnCours;
	private Case caseAncienne;
	private boolean attaqueEnCours;
	private boolean conqueteEnCours;
	private boolean poserTeleporteur;
	private Joueur joueur1;
	private Joueur joueur2;
	private Joueur JoueurElireCommandant;
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
		mapPath = "map/map6.map";
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
		lierChateaux();
		fp = new FenetrePrincipale(this);
		aireDeJeu = fp.getAireDeJeu();
		fenetreChoixPion = new FenetreChoixPion(this);
		nouvellePartie = new NouvellePartiePanel(this);
		animation = new Animation(fp.getAireDAnimation1(), this, fp.getEcouterPlateau());
	}

	public void caseCliqueBoutonGaucheNouvellePartie(Case c1) {
		aireDeJeu.setDebutDePartie(debutDePartie);
		if (nouvellePartie.getEtape() == 2 && nouvellePartie.getChoix()) {
			if (nouvellePartie.isJoueurCourant() && nouvellePartie.choixPossible(c1)) {
				Pion pion = FabriquePion.getPion(nouvellePartie.getFamille1(), nouvellePartie.getClasse(), c1);
				joueur1.ajouterPion(pion);
				if (pion.getNumClasse() == FabriquePion.TACTICIEN) {
					joueur1.setTacticien(pion);
				}
				nouvellePartie.pionAjouteJoueur1();
				nouvellePartie.setChoix(false);
			} else if (nouvellePartie.choixPossible(c1)) {
				Pion pion = FabriquePion.getPion(nouvellePartie.getFamille2(), nouvellePartie.getClasse(), c1);
				joueur2.ajouterPion(pion);
				if (pion.getNumClasse() == FabriquePion.TACTICIEN) {
					joueur2.setTacticien(pion);
				}
				nouvellePartie.pionAjouteJoueur2();
				nouvellePartie.setChoix(false);
			}

			if (nouvellePartie.getNbPions() == 4) {
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

				for (Pion p : joueur1.getListeDePions()) {
					p.setOrientation(Orientation.EST);
				}
				for (Pion p : joueur2.getListeDePions()) {
					p.setOrientation(Orientation.OUEST);
				}
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
		fenetreChoixPion.effacerFenetre();
		caseCourante = c1;
		System.out.println("Clique gauche");
		if (elireCommandant) {
			if (c1.contientPion() && JoueurElireCommandant.possede(c1.getPion())) {
				JoueurElireCommandant.setCommandant(c1.getPion());
				elireCommandant = false;
				aireDeJeu.elireUnCommandant(JoueurElireCommandant, elireCommandant);
				//TODO Affichage
			}
		} else if (getJoueurCourant().actionPossibles()) {
			// Permet de gerer l'attaque d'une unite ennemie
			if (attaqueEnCours) {
				// On verifie que le case cible est attaquable par le pion et que le pion sur cette case est un pion ennemi
				if (caseAncienne.getPion().getListeAttaquePossible().contains(caseCourante)) {
					if (caseCourante.contientPion()) {
						caseAncienne.getPion().attaquerPion(caseCourante.getPion());
						animation.animerAttaquePion(caseAncienne.getPion(), caseCourante.getPion());
						if (!caseCourante.getPion().estVivant()) {
							elireCommandant = caseAncienne.getPion().tuer(caseCourante.getPion());
							if (getJoueurAdverse().tousMort() || getJoueurCourant().tousMort()) {
								victoire();
							} else {
								if (elireCommandant) {
									JoueurElireCommandant = getJoueurAdverse();
									animation.animerElireCommandant();
								}
							}
						} else if (!caseAncienne.getPion().estVivant()) {
							elireCommandant = caseCourante.getPion().tuer(caseAncienne.getPion());
							if (getJoueurAdverse().tousMort() || getJoueurCourant().tousMort()) {
								victoire();
							} else {
								if (elireCommandant) {
									JoueurElireCommandant = getJoueurCourant();
									animation.animerElireCommandant();
								}
							}
						}
						System.out.println(elireCommandant);
					} else if (caseCourante.getObstacle() != null && caseCourante.getObstacle().isDestructible()) {
						caseAncienne.getPion().attaquerObstacle(caseCourante);
					} else if (caseCourante.contientTeleporteur(getJoueurAdverse())) {
						System.out.println("TELEPORTEUR EN COURS DE DECES");
						caseAncienne.getPion().attaquerTeleporteur(caseCourante);
					}
					utiliserAction();
				}
				// On signifie que l'attaque est fini
				attaqueEnCours = false;
				// Et qu'il ne faut plus afficher la zone d'attaque
				aireDeJeu.setAttaqueEnCours(attaqueEnCours);
			} else if (conqueteEnCours) {
				// On verifie que le case cible peut etre conquise par le pion et que le chateau est un chateau ennemi
				if (caseAncienne.getPion().getListeConquetePossible().contains(caseCourante) /*
						 * &&
						 * joueurOppose().geListeDePions().contains(caseCourante.getPion())
						 */) {
					getJoueurAdverse().conquerir(caseCourante);
					System.out.println(getJoueurAdverse().toutConquis());
					utiliserAction();
				}
				conqueteEnCours = false;
				aireDeJeu.setConqueteEnCours(conqueteEnCours);
			}// Permet de gerer l'utilisation d'une capaciteActive
			// Permet de gerer le calculDeplacementPossible d'un pion suite a un clic gauche sur celui ci
			else if (poserTeleporteur) {
				//TODO Cas a verifier ?
				System.out.println("Il faut poser un teleporteur");
				((Tacticien) caseAncienne.getPion()).poserTeleporteur(caseCourante);
				poserTeleporteur = false;
				aireDeJeu.setAfficherPoseTeleporteur(false, caseCourante);
				utiliserAction();
			} else if (teleportationEnCours) {
				if (getJoueurCourant().getTeleporteur().contains(caseCourante.getTeleporteur())
						&& !caseCourante.contientPion()) {
					caseAncienne.getPion().deplacerPionTeleportation(caseCourante);
					utiliserAction();
				}
				teleportationEnCours = false;
				aireDeJeu.afficherTeleporteurDisponible(teleportationEnCours, caseCourante);
			} else if (mouvementEnCours && caseAncienne.getPion().deplacementPossible(caseCourante)) {
				if (caseAncienne != caseCourante) {
					animation.animerMouvement(caseAncienne.getPion());
					caseAncienne.setPion(null);
				}
				// On specifie que le mouvement est termine
				mouvementEnCours = false;
				// On indique qu'il ne faut plus afficher les mouvements possibles
				aireDeJeu.afficherMouvement(mouvementEnCours, caseCourante);
				//Si fin sur une case telportation
				if (caseCourante.getTeleporteur() != null && getJoueurCourant().getTeleporteur().contains(caseCourante.getTeleporteur())) {
					System.out.println("Je peux me teleporter");
					//Surbrillance des cases teleportations
					//teleportation en cours
					caseAncienne = caseCourante;
					teleportationEnCours = true;
					aireDeJeu.afficherTeleporteurDisponible(teleportationEnCours, caseCourante);
				}
				// Afficher Selection Orientation
				//TODO Afficher Orientation
			} // Selectionne un Pion pour le deplacer
			else if (caseCourante.contientPion()) {
				if (getJoueurCourant().getListeDePions().contains(caseCourante.getPion())) {
					// On specifie qu'un mouvement est en cours
					mouvementEnCours = true;
					// On indique qu'il faut afficher les mouvements possibles
					aireDeJeu.afficherMouvement(mouvementEnCours, caseCourante);
					// On calcul les deplacements possible                  
					caseCourante.getPion().calculDeplacementPossible();
					// On memorise la case ou se trouve le pion a deplacer
					caseAncienne = caseCourante;
				}
			} else {
				fenetreChoixPion.effacerFenetre();
				// On specifie que le mouvement est termine
				mouvementEnCours = false;
				// On indique qu'il ne faut plus afficher les mouvements possibles
				aireDeJeu.afficherMouvement(mouvementEnCours, caseCourante);
			}

		} else {
			fenetreChoixPion.effacerFenetre();
			// On specifie que le mouvement est termine
			mouvementEnCours = false;
			// On indique qu'il ne faut plus afficher les mouvements possibles
			aireDeJeu.afficherMouvement(mouvementEnCours, caseCourante);
		}
		aireDeJeu.repaint();
	}

	public void caseCliqueBoutonDroit(Case c) {
		if (c.contientPion() && getJoueurCourant() == c.getPion().getJoueur()) {
			// On efface la fenetre
			fenetreChoixPion.effacerFenetre();
			// On la place a l'endroit voulu
			fenetreChoixPion.placerFenetre(c);

			// On specifie que le mouvement est terminÃ©
			mouvementEnCours = false;
			// On indique qu'il ne faut plus afficher les mouvements possibles
			aireDeJeu.afficherMouvement(mouvementEnCours, c);

			// On specifie que la tÃ©lÃ©portation est terminÃ©e
			teleportationEnCours = false;
			// On indique qu'il ne faut plus afficher les tÃ©lÃ©porteurs disponibles
			aireDeJeu.afficherTeleporteurDisponible(false, c);

			// On indique qu'il ne faut plus afficher les poses de tÃ©lÃ©porteurs possibles
			poserTeleporteur = false;
			aireDeJeu.setAfficherPoseTeleporteur(false, c);

			// On specifie que l'attaque est terminÃ©e
			attaqueEnCours = false;
			// On indique qu'il ne faut plus afficher les attaques possibles
			aireDeJeu.setAttaqueEnCours(false);

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
			} else if (conqueteEnCours) {
				caseSurvolConquete(c1);
			} else if (c1.contientPion()) {
				caseSurvolPion(c1);
			} else if (c1.getObstacle() != null && c1.getObstacle().isChateau()) {
				caseSurvolChateau(c1);
			} else {
				caseSurvolPion(c1);
			}
		} else {
			aireDeJeu.setCaseSurvol(null);
		}
		aireDeJeu.repaint();
	}

	private void caseSurvolMouvement(Case c1) {
		caseCourante.getPion().afficherDeplacement(c1);
	}

	private void caseSurvolAttaque(Case c1) {
		aireDeJeu.setCaseSurvol(c1);
	}

	private void caseSurvolConquete(Case c1) {
		aireDeJeu.setCaseSurvol(c1);
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
		if (joueurCourant) {
			tour++;
			if(tour<15){
				fp.setLabelTour(tour);
			}else {
				fp.setLabelTour();
				for(Pion p :joueur1.getListeDePions()){
					p.mortSubite();
				}
				for(Pion p :joueur2.getListeDePions()){
					p.mortSubite();
				}
			}
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
		System.out.println("Infini. Fuck.");
		getJoueurCourant().utiliserAction();
		fp.setLabelAction(getJoueurCourant().getNbActions());
		if (getJoueurCourant().getNbActions() == 0) {
			System.out.println("Fin suite à un mouvement !");
			animation.animerFinDeTour();
			changementJoueur();
		}
		if (getJoueurAdverse().toutConquis() || getJoueurAdverse().tousMort()) {
			victoire();
		}
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

	public void nouvellePartie() {
		try {
			if (isDebutDePartie()) {
				aireDeJeu.remove(nouvellePartie);
			}
			debutDePartie = true;
			plateau.init(mapPath);
			aireDeJeu.nouvellePartie();
			tour = 0;
			joueur1.init();
			joueur2.init();
			lierChateaux();
			aireDeJeu.repaint();
			nouvellePartie = new NouvellePartiePanel(this);
			nouvellePartie.updateUI();
		} catch (MapException ex) {
		}
	}

	public void lierChateaux() {
		ArrayList<Case> l = plateau.listeChateaux();
		System.out.println(l);
		ArrayList<Case> chateau = new ArrayList<Case>();
		ArrayList<ArrayList<Case>> listeChateau = new ArrayList<ArrayList<Case>>();
		int i = 0;
		int lchInt = 0;
		int lchSave = 0;
		boolean trouve = false;
		boolean ajoute = false;
		for (Case c : l) {
			if (trouve) {
				listeChateau.get(lchSave).add(c);
				trouve = false;
			} else {
				ajoute = false;
				for (ArrayList<Case> lch : listeChateau) {
					if (lch.get(0).getColonne() == c.getColonne() - 1 && lch.get(0).getLigne() == c.getLigne()
							|| lch.get(0).getColonne() == c.getColonne() - 1 && lch.get(0).getLigne() + 1 == c.getLigne()) {
						lch.add(c);
						ajoute = true;
					}
				}
				if (!ajoute) {
					chateau = new ArrayList<Case>();
					chateau.add(c);
					listeChateau.add(chateau);
					trouve = true;
					lchSave = lchInt;
					lchInt++;
				}
			}
		}

		for (ArrayList<Case> lch : listeChateau) {
			if (((Chateau) lch.get(0).getObstacle()).isJoueur1()) {
				joueur1.lierChateaux(lch);
			} else {
				joueur2.lierChateaux(lch);
			}
		}

		System.out.println(joueur1.getChateaux());
		System.out.println(joueur2.getChateaux());
	}

	public void setConqueteEnCours(boolean b) {
		conqueteEnCours = b;
	}

	private void victoire() {
		//TODO Victoire graphique
		animation.animerFinDePartie();
		System.out.println("Victoire " + getJoueurCourant().getNom() + " !!!");
	}

	private void caseSurvolChateau(Case c1) {
		boolean trouve = false;
		ArrayList<Case> tmp = null;
		for (ArrayList<Case> lc : joueur1.getChateaux()) {
			if (lc.contains(c1)) {
				trouve = true;
				tmp = lc;
				break;
			}
		}
		if (!trouve) {
			for (ArrayList<Case> lc : joueur2.getChateaux()) {
				if (lc.contains(c1)) {
					trouve = true;
					tmp = lc;
					break;
				}
			}
		}
		System.out.println(tmp);
		aireDeJeu.setCaseSurvol(tmp.get(0));
	}

	public Joueur getJoueurElireCommandant() {
		return JoueurElireCommandant;
	}
}
