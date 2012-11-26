package moteur;

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
    private AireDeJeu aireDeJeu;
    /*
     * Gestion de la souris
     */
    private Case caseCourante;
    private boolean afficherMouvement;
    private Case caseDebutMouvement;
    private boolean afficherChoix;
    private Case choix;
    private boolean afficherFinDeTour;
    private Case finDeTour;

    public Plateau getPlateau() {
        return plateau;
    }

    public void setPlateau(Plateau plateau) {
        this.plateau = plateau;
    }

    public Moteur() {
        plateau = new Plateau("map/map2.map");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Moteur());
    }

    @Override
    public void run() {
        fp = new FenetrePrincipale(this);
        aireDeJeu = fp.getAireDeJeu();
    }

    public void caseCliqueBoutonGauche(Case c1) {
        aireDeJeu.setAfficherChoix(afficherChoix);

        if (caseCourante != null) {
            caseCourante.setSelect(false);
        }
        caseCourante = c1;

        if (afficherChoix) {
            System.out.println("Lalalala");
            afficherChoix = false;
            aireDeJeu.setAfficherChoix(false);
        } //        Afficher Fin du tour Selectionne
        else if (afficherFinDeTour) {
//            Si clique à l'interieur
            afficherFinDeTour = false;
            aireDeJeu.setAfficherFinDeTour(afficherFinDeTour);
        }//        Deplace un Pion
        else if (afficherMouvement && caseDebutMouvement.getPion().getDeplacement().contains(caseCourante)) {
            caseDebutMouvement.getPion().deplacerPion(caseCourante);
            afficherMouvement = false;
//            Afficher Selection Orientation
//            TODO Afficher Orientation
        } //        Selectionne un Pion
        else if (c1.getPion() != null) {
            afficherMouvement = true;
            caseCourante.setSelect(true);
            caseCourante.getPion().deplacement();
            caseDebutMouvement = caseCourante;
        } //        On affiche la fin du tour
        else {
            afficherMouvement = false;
            afficherFinDeTour = true;
            if (plateau.get(caseCourante.getLigne(), caseCourante.getColonne() + 2) == null) {
                caseCourante = plateau.get(caseCourante.getLigne(), caseCourante.getColonne() - 2);
            }
            aireDeJeu.affichageFinDuTour(caseCourante);
        }

        aireDeJeu.repaint();
    }

    public void caseCliqueBoutonDroit(Case c) {
        if (caseCourante != null) {
            caseCourante.setSelect(false);
        }
        if (c.getPion() != null) {
            afficherMouvement = false;
            afficherFinDeTour = false;
            aireDeJeu.setAfficherFinDeTour(false);
            afficherChoix = true;
            choix = c;
            if (plateau.get(c.getLigne() + 1, c.getColonne()) == null) {
                choix = plateau.get(c.getLigne() - 1, c.getColonne());
            }
            if (plateau.get(c.getLigne(), c.getColonne() + 2) == null) {
                choix = plateau.get(c.getLigne(), c.getColonne() - 2);
            }
            aireDeJeu.affichageChoix(choix);
            aireDeJeu.repaint();
        }

    }

    public void caseSurvol(Case c1) {
        if (c1 != null) {

            if (afficherMouvement) {
                caseSurvolMouvement(c1);
            } else if (afficherChoix) {
                caseSurvolChoix(c1);
            } else if (afficherFinDeTour) {
//                caseSurvolFinDeTour(c1);
            }
        }
    }

    private void caseSurvolMouvement(Case c1) {
        caseCourante.getPion().afficherDeplacement(c1);
        aireDeJeu.repaint();
    }

    private void caseSurvolChoix(Case c1) {
        if (c1.getLigne() == choix.getLigne()
                && (c1.getColonne() == choix.getColonne() || c1.getColonne() == choix.getColonne() + 1 || c1.getColonne() == choix.getColonne() + 2)) {
            aireDeJeu.survolAfficherChoix(choix);
        } else if (c1.getLigne() == choix.getLigne() + 1
                && (c1.getColonne() == choix.getColonne() || c1.getColonne() == choix.getColonne() + 1 || c1.getColonne() == choix.getColonne() + 2)) {
            aireDeJeu.survolAfficherChoix(plateau.get(choix.getLigne() + 1, choix.getColonne()));
        } else {
            aireDeJeu.survolAfficherChoix(null);
        }
        aireDeJeu.repaint();

    }
}
