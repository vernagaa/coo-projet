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
public class EcouteurEditeur  implements MouseListener, MouseMotionListener{
	private AireDeJeu aire;
	private Case case1 = null;
	private Case case2 = null;

	public EcouteurEditeur(AireDeJeu aire) {
		this.aire = aire;
	}

	@Override
	public void mouseClicked(MouseEvent e) {

		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		
		int col = x/ Case.TAILLE;
		int lig = y/ Case.TAILLE;
		case1 = aire.getPlateau().get(lig, col);
		System.out.println("case :" + case1);
		System.out.println("lig "+lig+" col "+col);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		
		int col = x/ Case.TAILLE;
		int lig = y/ Case.TAILLE;
		case2 = aire.getPlateau().get(lig, col);
		System.out.println("case :" + case2);
		System.out.println("lig "+lig+" col "+col);
		
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
	}
}
