/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecouteur;

import editeur.FenetreEditeur;
import ihm.AireDeJeu;
import java.awt.event.*;
import java.util.Timer;
import moteur.Bordure;
import moteur.Case;
import moteur.Indestructible;
import moteur.Obstacle;

/**
 *
 * @author disavinr
 */
public class EcouteurEditeur implements MouseListener, MouseMotionListener {

	private FenetreEditeur fenetreEditeur;
	public Case c1 = null;
	public Case c2 = null;
	public int ligneMin;
	public int ligneMax;
	public int colonneMin;
	public int colonneMax;
	public int x;
	public int y;

	public EcouteurEditeur(FenetreEditeur fe) {
		this.fenetreEditeur = fe;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		x = e.getX();
		y = e.getY();


		int col = x / Case.TAILLE;
		int lig = y / Case.TAILLE;
		c1 = fenetreEditeur.getAireDeJeu1().getPlateau().get(lig, col);
		c1.setSelect(true);
	}

	public void mouseReleased(MouseEvent e) {
		x = e.getX();
		y = e.getY();
		fenetreEditeur.getAire().repaint();
		int col = x / Case.TAILLE;
		int lig = y / Case.TAILLE;
		c2 = fenetreEditeur.getAireDeJeu1().getPlateau().get(lig, col);

		ligneMin = Math.min(c1.getLigne(), c2.getLigne());
		ligneMax = Math.max(c1.getLigne(), c2.getLigne());
		colonneMin = Math.min(c1.getColonne(), c2.getColonne());
		colonneMax = Math.max(c1.getColonne(), c2.getColonne());

		Case[][] c = fenetreEditeur.getAireDeJeu1().getPlateau().get();
		for (int i = ligneMin; i <= ligneMax; i++) {
			for (int j = colonneMin; j <= colonneMax; j++) {
				if (fenetreEditeur.obstacle) {
					Indestructible o = new Indestructible(fenetreEditeur.texture);
					c[i][j].setObstacle(o);
				} else if (fenetreEditeur.bordure) {
					Bordure b = new Bordure(fenetreEditeur.texture);
					c[i][j].setBordure(b);
				} else if (fenetreEditeur.suppression) {
					c[i][j].setObstacle(null);
					c[i][j].setBordure(null);
				} else {
					c[i][j].setTypeTerrain(fenetreEditeur.texture);
				}
				c[i][j].setSelect(false);
			}
		}
		fenetreEditeur.getAireDeJeu1().repaint();

	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		x = e.getX();
		y = e.getY();
		int col = x / Case.TAILLE;
		int lig = y / Case.TAILLE;
		c2 = fenetreEditeur.getAireDeJeu1().getPlateau().get(lig, col);

		ligneMin = Math.min(c1.getLigne(), c2.getLigne());
		ligneMax = Math.max(c1.getLigne(), c2.getLigne());
		colonneMin = Math.min(c1.getColonne(), c2.getColonne());
		colonneMax = Math.max(c1.getColonne(), c2.getColonne());
		fenetreEditeur.getAire().repaint();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}
}
