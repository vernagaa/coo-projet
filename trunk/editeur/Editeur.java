package editeur;

import javax.swing.SwingUtilities;
import moteur.Plateau;

/**
 *
 * @author disavinr
 */
public class Editeur implements Runnable {

	FenetreEditeur fe;
	Plateau plateau;

	/**
	 * Accesseur du plateau d'édition
	 * @return plateau
	 */
	public Plateau getPlateau() {
		return plateau;
	}

	/**
	 * Affecteur du plateau d'édition
	 * @param plateau
	 */
	public void setPlateau(Plateau plateau) {
		this.plateau = plateau;
	}

	/**
	 * Constructeur de l'éditeur
	 */
	public Editeur() {
		plateau = new Plateau();
	}

	/**
	 * Main de l'éditeur
	 * @param args
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Editeur());
	}

	/**
	 * Création de la fenêtre d'édition
	 */
	@Override
	public void run() {
		fe = new FenetreEditeur(this);
	}
}
