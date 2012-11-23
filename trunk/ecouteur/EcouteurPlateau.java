/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecouteur;

import ihm.AireDeJeu;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import moteur.Case;

/**
 *
 * @author disavinr
 */
public class EcouteurPlateau implements MouseListener, MouseMotionListener {

	private AireDeJeu aire;
	private Case caseCourante;

	public EcouteurPlateau(AireDeJeu aire) {
		this.aire = aire;
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

		/*
		 * Test du chemin trouve !
		 */

		if (aire.getPlateau().get(lig, col).getPion() != null) {
			if (caseCourante != null) {
				caseCourante.setSelect(false);
			}
			caseCourante = aire.getPlateau().get(lig, col);
			caseCourante.setSelect(true);
			caseCourante.getPion().deplacementPossible(aire.getPlateau().get(lig, col));
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
		if (caseCourante != null && aire.getPlateau().get(lig, col) != null) {
			caseCourante.getPion().afficherDeplacement(aire.getPlateau().get(lig, col));
			aire.repaint();
		}
	}
}
