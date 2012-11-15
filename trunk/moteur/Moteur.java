package moteur;

import ihm.FenetrePrincipale;
import java.io.Serializable;
import javax.swing.SwingUtilities;

/**
 *
 * @author Kévin
 */
public class Moteur implements Runnable, Serializable{

	FenetrePrincipale fp;
	Plateau plateau;

	public Plateau getPlateau() {
		return plateau;
	}

	public void setPlateau(Plateau plateau) {
		this.plateau = plateau;
	}
	
	public Moteur() {
		plateau = new Plateau();
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Moteur());
	}

	@Override
	public void run() {
		fp = new FenetrePrincipale(this);
	}
}
