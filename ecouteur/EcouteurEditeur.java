/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecouteur;

import editeur.ChoixTexture;
import editeur.FenetreEditeur;
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
	private FenetreEditeur fenetreEditeur;
	private Case case1 = null;
	private Case case2 = null;

	public EcouteurEditeur(FenetreEditeur fe) {
		this.fenetreEditeur = fe;
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
		case1 = fenetreEditeur.getAireDeJeu1().getPlateau().get(lig, col);
		System.out.println("case :" + case1);
		System.out.println("lig "+lig+" col "+col);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		
		int col = x/ Case.TAILLE;
		int lig = y/ Case.TAILLE;
		case2 = fenetreEditeur.getAireDeJeu1().getPlateau().get(lig, col);
		System.out.println("case :" + case2);
		System.out.println("lig "+lig+" col "+col);
		fenetreEditeur.choix(case1,case2);
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
