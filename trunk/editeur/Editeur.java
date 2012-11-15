/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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

	public Plateau getPlateau() {
		return plateau;
	}

	public void setPlateau(Plateau plateau) {
		this.plateau = plateau;
	}
	
	public Editeur() {
		plateau = new Plateau();
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Editeur());
	}

	@Override
	public void run() {
		fe = new FenetreEditeur(this);
	}
}
