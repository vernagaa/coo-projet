package ecouteur;

import editeur.FenetreEditeur;
import java.awt.event.*;
import moteur.Bordure;
import moteur.Case;
import moteur.Destructible;
import moteur.Indestructible;

/**
 *
 * @author disavinr
 */
public class EcouteurEditeur implements MouseListener, MouseMotionListener {

	private FenetreEditeur fenetreEditeur;
	public Case c1 = null;
	private Case c1old = null;
	public Case c2 = null;
	public Case c2old = null;
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
		ligneMin = c1.getLigne();
		ligneMax = c1.getLigne();
		colonneMin = c1.getColonne();
		colonneMax = c1.getColonne();
		fenetreEditeur.getAire().repaint();

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		x = e.getX();
		y = e.getY();
		int col = x / Case.TAILLE;
		int lig = y / Case.TAILLE;
		c2 = fenetreEditeur.getAireDeJeu1().getPlateau().get(lig, col);
		if (c2 == null) {
			c2 = c2old;
		}
		ligneMin = Math.min(c1.getLigne(), c2.getLigne());
		ligneMax = Math.max(c1.getLigne(), c2.getLigne());
		colonneMin = Math.min(c1.getColonne(), c2.getColonne());
		colonneMax = Math.max(c1.getColonne(), c2.getColonne());

		Case[][] c = fenetreEditeur.getAireDeJeu1().getPlateau().get();
		for (int i = ligneMin; i <= ligneMax; i++) {
			for (int j = colonneMin; j <= colonneMax; j++) {
				if (fenetreEditeur.indestructible) {
					Indestructible o = new Indestructible(fenetreEditeur.texture);
					c[i][j].setObstacle(o);
				} else if (fenetreEditeur.bordure) {
					Bordure b = new Bordure(fenetreEditeur.texture);
					c[i][j].setBordure(b);
				} else if (fenetreEditeur.suppression) {
					c[i][j].setObstacle(null);
					c[i][j].setBordure(null);
				} else if (fenetreEditeur.destructible) {
					Destructible o = new Destructible(fenetreEditeur.texture, 25);
					c[i][j].setObstacle(o);
				} else {
					c[i][j].setTypeTerrain(fenetreEditeur.texture);
				}
			}
		}
		c1 = null;
		c2 = null;
		c2old = null;
		fenetreEditeur.getAireDeJeu1().repaint();
		fenetreEditeur.getAire().repaint();
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

		if (c1 != null && c2 != null && (c2old == null || c2old.getLigne() != c2.getLigne() || c2old.getColonne() != c2.getColonne())) {
			ligneMin = Math.min(c1.getLigne(), c2.getLigne());
			ligneMax = Math.max(c1.getLigne(), c2.getLigne());
			colonneMin = Math.min(c1.getColonne(), c2.getColonne());
			colonneMax = Math.max(c1.getColonne(), c2.getColonne());
			fenetreEditeur.getAire().repaint();
		}
		if (c2 != null) {
			c2old = c2;
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		x = e.getX();
		y = e.getY();

		int col = x / Case.TAILLE;
		int lig = y / Case.TAILLE;
		c1 = fenetreEditeur.getAireDeJeu1().getPlateau().get(lig, col);

		if (c1 != null && (c1old == null || c1old.getLigne() != c1.getLigne() || c1old.getColonne() != c1.getColonne())) {
			ligneMin = c1.getLigne();
			ligneMax = c1.getLigne();
			colonneMin = c1.getColonne();
			colonneMax = c1.getColonne();
			fenetreEditeur.getAire().repaint();
		}

		c1old = c1;

	}

	@Override
	public void mouseExited(MouseEvent e) {
	}
}
