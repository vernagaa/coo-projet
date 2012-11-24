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
	private boolean mouvement;
	private Case caseDebutMouvement;

	public EcouteurPlateau(AireDeJeu aire) {
		this.aire = aire;
		mouvement = false;
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

		if (caseCourante != null) {
			caseCourante.setSelect(false);
		}
		caseCourante = aire.getPlateau().get(lig, col);
		
		if (aire.getPlateau().get(lig, col).getPion() != null) {
			mouvement = true;
			caseCourante.setSelect(true);
			caseCourante.getPion().deplacementPossible(aire.getPlateau().get(lig, col));
			caseDebutMouvement = caseCourante;
		}
		if(mouvement && caseDebutMouvement.getPion().getDeplacement().contains(caseCourante)){
			System.out.println(caseCourante.toString());
			caseDebutMouvement.getPion().deplacerPion(caseCourante);
			mouvement = false;
		}
		
		aire.repaint();
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
		if (caseCourante != null && aire.getPlateau().get(lig, col) != null && caseCourante.getPion() != null) {
			caseCourante.getPion().afficherDeplacement(aire.getPlateau().get(lig, col));
			aire.repaint();
		}
	}
}
