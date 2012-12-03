/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package moteur;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JComponent;

/**
 *
 * @author Kévin
 */
public class FenetreAction extends JComponent implements MouseListener, MouseMotionListener {

    protected Case c;
    protected Moteur m;
    protected int hauteur;
    protected int largeur;
    protected Point Survol;

    public FenetreAction(Moteur m) {
        this.m = m;
        Survol = null;
        setVisible(false);
        addMouseListener(this);
        addMouseMotionListener(this);
        m.aireDeJeu.add(this);
    }

    public void placerFenetre(Case c) {
        this.c = c;
        setLocation((c.getColonne() + 1) * Case.TAILLE, c.getLigne() * Case.TAILLE);
        setVisible(true);
    }

    public void effacerFenetre() {
        setVisible(false);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        m.aireDeJeu.setCaseSurvol(null);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        Survol = null;
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

        Survol = new Point(col, lig);
        //TODO éviter le repaint systématique
        repaint();
    }
}
