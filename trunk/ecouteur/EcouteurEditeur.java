package ecouteur;

import editeur.FenetreEditeur;
import java.awt.event.*;
import moteur.*;

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

	/**
	 * Constructeur de l'écouteur de l'éditeur
	 * @param fe Fenetre de l'éditeur
	 */
	public EcouteurEditeur(FenetreEditeur fe) {
		this.fenetreEditeur = fe;
	}

	/**
	 * Evenement d'un simple clic neutralisé
	 * @param e 
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
	}

	/**
	 * Evenement lorsqu'un bouton de la souris est enfoncé
	 * Retiens la case cliquée
	 * @param e 
	 * pré-cond : L'aire de jeu et le plateau sont non null ET le clic est sur une case existante
	 * post-cond : Les coordonées de la case sont enregistrées
	 */
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

	/**
	 * Evenement lorsqu'un bouton est relaché
	 * Récupère le coin haut gauche et le coin bas droit du rectangle de selection
	 * Demande le changement de texture pour ces cases
	 * @param e 
	 * pre-cond : c1, aire de jeu et le plateau sont non null ligneMin/Max et colonneMin/Max contiennent des int
	 * post cond : le rectangle de sélection change de texture
	 */
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
				} else if (fenetreEditeur.chateau) {
					if (fenetreEditeur.texture == Textures.CHATEAU1BASDROIT
							|| fenetreEditeur.texture == Textures.CHATEAU1BASGAUCHE
							|| fenetreEditeur.texture == Textures.CHATEAU1HAUTDROIT
							|| fenetreEditeur.texture == Textures.CHATEAU1HAUTGAUCHE) {
						Chateau o = new Chateau(fenetreEditeur.texture, true);
						c[i][j].setObstacle(o);
					} else {
						Chateau o = new Chateau(fenetreEditeur.texture, false);
						c[i][j].setObstacle(o);
					}

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

	/**
	 * Evenement lorsque la souris entre dans l'aire d'édition
	 * @param e 
	 */
	@Override
	public void mouseEntered(MouseEvent e) {
	}

	/**
	 * Evenement lorsque la souris bouge avec le clic enfoncé
	 * Calcule le rectangle de selection et l'affiche
	 * @param e 
	 */
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

	/**
	 * Evenement lorsque la souris bouge sur l'aire d'édition
	 * Met en valeur la case pointé par le curseur
	 * @param e 
	 */
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

	/**
	 * Evenement lorsque la souris quitte l'aire de sélection
	 * @param e 
	 */
	@Override
	public void mouseExited(MouseEvent e) {
	}
}
