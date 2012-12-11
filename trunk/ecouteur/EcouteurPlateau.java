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
	private boolean active;

	/**
	 * Constructeur de l'écouteur du plateau
	 * @param moteur 
	 */
	public EcouteurPlateau(Moteur moteur) {
		this.moteur = moteur;
		active = true;
		lastLig = -1;
		lastCol = -1;
	}

	/**
	 * Evenement lors d'un clic
	 * Selon le clic et si on est en début de partie ou non  appelle la methode de moteur appropriée
	 * @param e 
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();

		int col = x / Case.TAILLE;
		int lig = y / Case.TAILLE;
		Case c = moteur.getPlateau().get(lig, col);

		if (c != null && active) {
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

	/**
	 * Evenement lorsqu'un bouton de la souris est enfoncé
	 * @param e 
	 */
	@Override
	public void mousePressed(MouseEvent e) {
	}
	
	/**
	 * Evenement lorsqu'un bouton de la souris est relachée
	 * @param e 
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
	}

	/**
	 * Evenement lorsque la souris entre dans l'aire de jeu
	 * @param e 
	 */
	@Override
	public void mouseEntered(MouseEvent e) {
	}

	/**
	 * Evenement lorsque la souris quitte l'aire de jeu
	 * La case survolé est mise à null
	 * @param e 
	 */
	@Override
	public void mouseExited(MouseEvent e) {
		moteur.getAireDeJeu().setCaseSurvol(null);
		lastCol = -1;
	}

	/**
	 * Evenement lorsqu'un bouton est enfoncé et que la souris est déplacée
	 * @param e 
	 */
	@Override
	public void mouseDragged(MouseEvent e) {
	}

	/**
	 * Evenement lorsque la souris est déplacée
	 * Met à jour la case survolée s'il n'y a pas d'animation en cours
	 * @param e 
	 */
	@Override
	public void mouseMoved(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();

		int col = x / Case.TAILLE;
		int lig = y / Case.TAILLE;

		if (moteur.getPlateau().get(lig, col) != null && active) {
			if (col != lastCol || lig != lastLig) {
				lastCol = col;
				lastLig = lig;
				moteur.caseSurvol(moteur.getPlateau().get(lig, col));
			}
		}
	}

	/**
	 * Désactiver l'écouteur
	 */
	public void desactiverEcouteur() {
		active = false;
	}

	/**
	 * Activer l'écouteur
	 */
	public void activerEcouteur() {
		active = true;
	}
}
