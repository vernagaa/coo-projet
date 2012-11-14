/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecouteur;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import moteur.Case;
import moteur.Plateau;

/**
 *
 * @author disavinr
 */
public class EcouteurPlateau implements MouseListener, MouseMotionListener{

	private Plateau plateau;
	private Case caseCourante;

	public EcouteurPlateau(Plateau plateau) {
		this.plateau = plateau;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		
		int lig = x/ Case.TAILLE;
		int col = y/ Case.TAILLE;
		Case c = plateau.get(lig, col);
		System.out.println("case :" + c);
		System.out.println("lig "+lig+" col "+col);
		
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
	}
	
}
