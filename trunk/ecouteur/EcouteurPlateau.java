/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecouteur;

import ihm.AireDeJeu;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import moteur.Case;
import moteur.Moteur;

/**
 *
 * @author disavinr
 */
public class EcouteurPlateau implements MouseListener, MouseMotionListener {

    private AireDeJeu aire;
    private Moteur moteur;
    
    
    //TO DO Rajouter le moteur
    //Le moteur devra executer les actions

    public EcouteurPlateau(AireDeJeu aire, Moteur moteur) {
        this.aire = aire;
        this.moteur = moteur;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();

        int col = x / Case.TAILLE;
        int lig = y / Case.TAILLE;
        Case c = aire.getPlateau().get(lig, col);
        System.out.println("case :" + c);
        System.out.println("lig " + lig + " col " + col);
        System.out.println("obstacle :" + c.isObstacle());

//        if (e.getButton() == MouseEvent.BUTTON1) {
            moteur.caseCliqueBoutonGauche(c);
//
//            if (caseCourante != null) {
//                caseCourante.setSelect(false);
//            }
//            caseCourante = aire.getPlateau().get(lig, col);
//
//
//            if(aire.finDeDeplacement){
//                System.out.println("=D");
//            } else if (aire.afficherChoix) {
//                aire.afficherChoix = false;
//                if (aire.contientCoordonnees(new Point(x, y), aire.pointAfficherChoix, 3 * Case.TAILLE, Case.TAILLE)) {
//                    System.out.println("J'attaque");
//                } else if (aire.contientCoordonnees(new Point(x, y), new Point(aire.pointAfficherChoix.x, aire.pointAfficherChoix.y + Case.TAILLE), 3 * Case.TAILLE, Case.TAILLE)) {
//                    System.out.println("Capacite speciale");
//                }
//            } //            Permet de cliquer dans fin du tour si celui ci est afficher
//            else if (aire.afficherFinDeTour && x < aire.pointFinDeTour.x + 3 * Case.TAILLE && y < aire.pointFinDeTour.y + Case.TAILLE && aire.afficherFinDeTour
//                    && x > aire.pointFinDeTour.x && y > aire.pointFinDeTour.y) {
//                System.out.println("Fin du tour");
//                aire.afficherFinDeTour = false;
////           Permet de deplacer le pion selectionné
//            } else if (mouvement && caseDebutMouvement.getPion().getDeplacement().contains(caseCourante)) {
//                System.out.println(caseCourante.toString());
//                caseDebutMouvement.getPion().deplacerPion(caseCourante);
//                mouvement = false;
//                aire.afficherFinDeTour = false;
//                aire.finDeDeplacement = true;
//                aire.pionDeplace = caseCourante;
////           Selectionne un pion
//            } else if (aire.getPlateau().get(lig, col).getPion() != null) {
//                mouvement = true;
//                caseCourante.setSelect(true);
//                caseCourante.getPion().deplacementPossible(aire.getPlateau().get(lig, col));
//                caseDebutMouvement = caseCourante;
//                aire.afficherFinDeTour = false;
////             Permet d'afficher la fin de tour 
//            } else {
//                aire.pointFinDeTour.x = (col + 1) * Case.TAILLE;
//                aire.pointFinDeTour.y = (lig - 1) * Case.TAILLE;
//
//                if (aire.getPlateau().get(lig - 1, col + 3) == null) {
//                    aire.pointFinDeTour.x = (col - 3) * Case.TAILLE;
//                } else if (aire.getPlateau().get(lig - 1, col - 3) == null) {
//                    aire.pointFinDeTour.x = (col + 1) * Case.TAILLE;
//                }
//                if (aire.getPlateau().get(lig + 1, col) == null) {
//                    aire.pointFinDeTour.y = (lig - 1) * Case.TAILLE;
//                } else if (aire.getPlateau().get(lig - 1, col) == null) {
//                    aire.pointFinDeTour.y = (lig + 1) * Case.TAILLE;
//                }
//                if (col == 0 && lig == 0) {
//                    aire.pointFinDeTour.x = (col + 1) * Case.TAILLE;
//                    aire.pointFinDeTour.y = (lig + 1) * Case.TAILLE;
//                }
//
//                aire.afficherFinDeTour = true;
//            }
//
//        } else if (e.getButton() == MouseEvent.BUTTON3) {
//            aire.afficherFinDeTour = false;
//            if (caseCourante != null) {
//                caseCourante.setSelect(false);
//            }
//            mouvement = false;
//            if (aire.getPlateau().get(lig, col).getPion() != null) {
//                aire.afficherChoix = true;
//                aire.pointAfficherChoix.x = col * Case.TAILLE;
//                aire.pointAfficherChoix.y = lig * Case.TAILLE;
//                if (aire.getPlateau().get(lig + 1, col) == null) {
//                    aire.pointAfficherChoix.y = (lig - 1) * Case.TAILLE;
//                }
//                if (aire.getPlateau().get(lig, col + 2) == null) {
//                    aire.pointAfficherChoix.x = (col - 2) * Case.TAILLE;
//                }
//            }
//        }
//
//        aire.repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
//        int x = e.getX();
//        int y = e.getY();
//
//        int col = x / Case.TAILLE;
//        int lig = y / Case.TAILLE;
//        if (caseCourante != null && aire.getPlateau().get(lig, col) != null && caseCourante.getPion() != null) {
//            caseCourante.getPion().afficherDeplacement(aire.getPlateau().get(lig, col));
//            aire.repaint();
//        }
//        if (aire.afficherChoix) {
//            aire.survolAfficherChoix.x = x;
//            aire.survolAfficherChoix.y = y;
//            aire.repaint();
//        }
    }
}
