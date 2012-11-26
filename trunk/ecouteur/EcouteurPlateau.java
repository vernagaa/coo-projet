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

    private Moteur moteur;

    //TO DO Rajouter le moteur
    //Le moteur devra executer les actions
    public EcouteurPlateau(Moteur moteur) {
        this.moteur = moteur;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();

        int col = x / Case.TAILLE;
        int lig = y / Case.TAILLE;
        Case c = moteur.getPlateau().get(lig, col);
        System.out.println("case :" + c);
        System.out.println("lig " + lig + " col " + col);

        if (e.getButton() == MouseEvent.BUTTON1) {
            moteur.caseCliqueBoutonGauche(c);
        }
        else if (e.getButton() == MouseEvent.BUTTON3) {
            moteur.caseCliqueBoutonDroit(c);
        }
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
        int x = e.getX();
        int y = e.getY();

        int col = x / Case.TAILLE;
        int lig = y / Case.TAILLE;

        moteur.caseSurvol(moteur.getPlateau().get(lig, col));
    }
}
