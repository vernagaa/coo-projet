package ecouteur;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import moteur.Case;
import moteur.Moteur;

/**
 *
 * @author disavinr
 */
public class EcouteurPlateau implements MouseListener, MouseMotionListener {

	private Moteur moteur;
	private int lastLig, lastCol;

	public EcouteurPlateau(Moteur moteur) {
		this.moteur = moteur;
		lastLig = -1;
		lastCol = -1;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();

		int col = x / Case.TAILLE;
		int lig = y / Case.TAILLE;
		Case c = moteur.getPlateau().get(lig, col);
		System.out.println("case : " + c);

		if (c != null) {
			if (moteur.isDebutDePartie()) {
				moteur.caseCliqueBoutonGaucheNouvellePartie(c);
			} else if (e.getButton() == MouseEvent.BUTTON1) {
				moteur.caseCliqueBoutonGauche(c);
			} else if (e.getButton() == MouseEvent.BUTTON3) {
				moteur.caseCliqueBoutonDroit(c);
			}
		}
		lastCol = -1;
		mouseMoved(e);
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
		moteur.aireDeJeu.setCaseSurvol(null);
		lastCol = -1;
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

		if (moteur.getPlateau().get(lig, col) != null) {
			if (col != lastCol || lig != lastLig) {
				lastCol = col;
				lastLig = lig;
				moteur.caseSurvol(moteur.getPlateau().get(lig, col));
			}
		}
	}
}
