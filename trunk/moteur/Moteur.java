package moteur;

import ihm.NouvellePartieGraphique;
//import ihm.FenetreAction;
import ihm.FenetreChoixPion;
import ihm.AireDeJeu;
import ihm.BoutonFinDeTour;
import ihm.CompteurNombreAction;
import ihm.CompteurTour;
import ihm.FenetrePrincipale;
import java.io.*;
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
    private CompteurTour compteurTour;
    private CompteurNombreAction compteurAction;
    /*
     * Gestion de la souris
     */
    private Case caseCourante;
    private boolean mouvementEnCours;
    private Case caseAncienne;
    private boolean attaqueEnCours;
    private boolean capaciteActive;
    private boolean poserTeleporteur;
    private Joueur joueur1;
    private Joueur joueur2;
    private boolean joueurCourant;
    private boolean teleportationEnCours;

    public Plateau getPlateau() {
	return plateau;
    }

    public void setPlateau(Plateau plateau) {
	this.plateau = plateau;
    }

    public Moteur() {
	plateau = new Plateau("map/map3.map");
	joueur1 = new Joueur("joueur1");
	joueur2 = new Joueur("joueur2");
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
	compteurTour = new CompteurTour(this);
	compteurAction = new CompteurNombreAction(this);
    }

    public void caseCliqueBoutonGaucheNouvellePartie(Case c1) {
	aireDeJeu.setDebutDePartie(debutDePartie);
	if (nouvellePartie.getEtape() == 2 && nouvellePartie.getChoix()) {
	    if (nouvellePartie.isJoueurCourant() && nouvellePartie.choixPossible(c1)) {
		Pion pion = FabriquePion.getPion(nouvellePartie.getFamille1(), nouvellePartie.getClasse(), c1);
		joueur1.ajouterPion(pion);
		nouvellePartie.pionAjouteJoueur1();
		if (pion instanceof Tacticien) {
		    joueur1.setTacticien(pion);
		}
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
	    }
	}
    }

    public boolean isDebutDePartie() {
	return debutDePartie;
    }

    public void caseCliqueBoutonGauche(Case c1) {
	aireDeJeu.setCaseSurvol(null);
//	fenetreChoixPion.effacerFenetre();
	caseCourante = c1;
	fenetreChoixPion.effacerinDeTour();
	System.out.println("Clique gauche");
	// Permet de gerer l'attaque d'une unite ennemie
	if (attaqueEnCours) {
	    // On verifie que le case cible est attaquable par le pion et que le pion sur cette case est un pion ennemi
	    if (caseAncienne.getPion().getListeAttaquePossible().contains(caseCourante) /*
		     * &&
		     * joueurOppose().geListeDePions().contains(caseCourante.getPion())
		     */) {
		caseAncienne.getPion().attaquerPion(caseCourante.getPion());
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
	    ((Tacticien) caseAncienne.getPion()).poserTeleporteur(caseCourante);
	    poserTeleporteur = false;
	    aireDeJeu.setAfficherPoseTeleporteur(false, caseCourante);
	} else if(teleportationEnCours){
	    if(getJoueurCourant().getTeleporteur().contains(caseCourante)){
		caseAncienne.getPion().deplacerPionTeleportation(caseCourante);
	    }
	    teleportationEnCours = false;
	} else if (mouvementEnCours && caseAncienne.getPion().deplacementPossible(caseCourante)) {
	    if (caseAncienne != caseCourante) {
		caseAncienne.getPion().deplacerPion(caseCourante);
	    }
	    // On specifie que le mouvement est termine
	    mouvementEnCours = false;
	    // On indique qu'il ne faut plus afficher les mouvements possibles
	    aireDeJeu.afficherMouvement(mouvementEnCours, caseCourante);
	    
	    //Si fin sur une case telportation
	    if(caseCourante.isTeleporteur(getJoueurCourant())){
		System.out.println("Je peux me teleporter");
		//Surbrillance des cases teleportations
		//teleportation en cours
		caseAncienne = caseCourante;
		teleportationEnCours = true;
	    }
	    // Afficher Selection Orientation
	    //TODO Afficher Orientation
	} // Selectionne un Pion pour le deplacer
	else if (caseCourante.getPion() != null) {
	    if (joueurCourant) {
		System.out.println("Joueur 1");
		if (joueur1.getListeDePions().contains(caseCourante.getPion())) {
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
		if (joueur2.getListeDePions().contains(caseCourante.getPion())) {
		    // On specifie qu'un mouvement est en cours
		    mouvementEnCours = true;
		    // On indique qu'il faut afficher les mouvements possibles
		    aireDeJeu.afficherMouvement(mouvementEnCours, caseCourante);
		    // On calcul les deplacements possible                  
		    caseCourante.getPion().calculDeplacementPossible();
		    // On memorise la case ou se trouve le pion a deplacer
		    caseAncienne = caseCourante;
		}
	    }

	} else {
	    fenetreChoixPion.effacerFenetre();
	    // On specifie que le mouvement est termine
	    mouvementEnCours = false;
	    // On indique qu'il ne faut plus afficher les mouvements possibles
	    fenetreChoixPion.placerFinDeTour(caseCourante);
	    aireDeJeu.afficherMouvement(mouvementEnCours, caseCourante);
	}
    }

    public void caseCliqueBoutonDroit(Case c) {
	fenetreChoixPion.effacerinDeTour();
	if (c.getPion() != null && getJoueurCourant() == c.getPion().getJoueur()) {
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
	    } else if (c1.getPion() != null) {
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
	caseCourante.getPion().afficherDeplacement(c1);
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
	for (Pion p : getJoueurCourant().getListeDePions()) {
	    p.finDeTour();
	}
	if (joueurCourant) {
	    tour++;
	}
    }

    public Joueur getJoueurCourant() {
	if (joueurCourant) {
	    return joueur1;
	} else {
	    return joueur2;
	}
    }
}
