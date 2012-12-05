/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ihm;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;
import moteur.Case;
import moteur.Moteur;

/**
 *
 * @author chappelk
 */
public class CompteurNombreAction extends JComponent {

    private Moteur m;
    private Integer tour;

    public CompteurNombreAction(Moteur m) {
	this.m = m;
	tour = 0;
	setSize(2 * Case.TAILLE, 2 * Case.TAILLE);
	setLocation((m.getPlateau().getNbColonne() - 2) * Case.TAILLE, 0);
	m.aireDeJeu.add(this);
    }

    @Override
    public void paintComponent(Graphics g) {
	Graphics2D gd = (Graphics2D) g;
	gd.setColor(Color.BLACK);
	gd.fillRect(0, 0, getWidth(), getHeight());
	gd.setColor(Color.WHITE);
	gd.drawString(tour.toString(), getWidth() / 2, getHeight() / 2);
    }
}
