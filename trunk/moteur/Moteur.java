package moteur;

import ihm.FenetreAction;
import ihm.FenetreChoixPion;
import ihm.AireDeJeu;
import ihm.FenetrePrincipale;
import java.io.*;
import javax.swing.SwingUtilities;

/**
 *
 * @author Kévin
 */
public class Moteur implements Runnable, Serializable {

    private FenetrePrincipale fp;
    private Plateau plateau;
    public AireDeJeu aireDeJeu;
    private FenetreAction fenetreChoixPion;
    NouvellePartieGraphique nouvellePartie;
    private boolean debutDePartie;
    /*
     * Gestion de la souris
     */
    private Case caseCourante;
    private boolean mouvementEnCours;
    private Case caseAncienne;
    private boolean finirTour;
    private boolean attaqueEnCours;
    private boolean capaciteActive;
    private Joueur joueur1;
    private Joueur joueur2;
    private boolean joueurCourant;

    public Plateau getPlateau() {
	return plateau;
    }

    public void setPlateau(Plateau plateau) {
	this.plateau = plateau;
    }

    public Moteur() {
	plateau = new Plateau("map/map4.map");
	joueur1 = new Joueur("joueur1");
	joueur2 = new Joueur("joueur2");
	debutDePartie = true;
	joueurCourant = true;
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
    }

    public void caseCliqueBoutonGaucheNouvellePartie(Case c1) {
	aireDeJeu.setDebutDePartie(debutDePartie);
	if (nouvellePartie.getEtape() == 2 && nouvellePartie.getChoix()) {
	    if (nouvellePartie.isJoueurCourant() && nouvellePartie.choixPossible(c1)) {
		joueur1.ajouterPion(FabriquePion.getPion(nouvellePartie.getFamille1(), nouvellePartie.getClasse(), c1));
		nouvellePartie.pionAjouteJoueur1();
		nouvellePartie.setChoix(false);
	    } else if (nouvellePartie.choixPossible(c1)) {
		joueur2.ajouterPion(FabriquePion.getPion(nouvellePartie.getFamille2(), nouvellePartie.getClasse(), c1));
		nouvellePartie.pionAjouteJoueur2();
		nouvellePartie.setChoix(false);
	    }

	    if (nouvellePartie.getNbPions() == 2) {
		nouvellePartie.elireCommandant();
	    }
	} else if (nouvellePartie.getEtape() == 3) {
	    if (nouvellePartie.isJoueurCourant() && nouvellePartie.choixPossible(c1)) {
		joueur1.setCommandant(c1.getPion());
		nouvellePartie.elireCommandant();
	    } else if (nouvellePartie.choixPossible(c1)) {
		joueur1.setCommandant(c1.getPion());
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
	fenetreChoixPion.effacerFenetre();
	caseCourante = c1;

	if (joueurCourant && joueur1.getListeDePions().contains(c1.getPion())) {
	    System.out.println("C'est mon pion");
	    // Selectionne un Pion pour le deplacer
	    if (c1.getPion() != null) {
		// On specifie qu'un mouvement est en cours
		mouvementEnCours = true;
		// On indique qu'il faut afficher les mouvements possibles
		aireDeJeu.afficherMouvement(mouvementEnCours, c1);
		// On calcul les deplacements possible			
		caseCourante.getPion().calculDeplacementPossible();
		// On memorise la case ou se trouve le pion a deplacer
		caseAncienne = caseCourante;
	    }

	} else if (mouvementEnCours && caseAncienne.getPion().deplacementPossible(caseCourante)) {
	    if (caseAncienne != caseCourante) {
		caseAncienne.getPion().deplacerPion(caseCourante);
	    }
	    // On specifie que le mouvement est termine
	    mouvementEnCours = false;
	    // On indique qu'il ne faut plus afficher les mouvements possibles
	    aireDeJeu.afficherMouvement(mouvementEnCours, c1);

	    // Afficher Selection Orientation
	    //TODO Afficher Orientation
	}//	 Permet de gerer l'attaque d'une unite ennemie
	else if (attaqueEnCours) {
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
	else if (capaciteActive) {
//			choix.getPion().capaciteActive():
	    // On signifie que la capacite a ete utilise
	    capaciteActive = false;
	} // Permet de gerer le calculDeplacementPossible d'un pion suite a un clic gauche sur celui ci
	else {
	    // On specifie que le mouvement est termine
	    mouvementEnCours = false;
	    // On indique qu'il ne faut plus afficher les mouvements possibles
	    aireDeJeu.afficherMouvement(mouvementEnCours, c1);
	}
	aireDeJeu.repaint();
    }

    public void caseCliqueBoutonDroit(Case c) {
	if (c.getPion() != null) {
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
}
