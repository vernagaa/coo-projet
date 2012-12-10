package editeur;

import ecouteur.EcouteurEditeur;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;
import moteur.Case;

/**
 *
 * @author Kévin
 */
public class AireDeSelection extends JComponent {

	EcouteurEditeur p;

	/**
	 * Constructeur sans paramètres pour le java FORM
	 */
	public AireDeSelection() {
	}

	/**
	 * Constructeur avec l'écouteur de l'éditeur
	 * @param p
	 */
	public AireDeSelection(EcouteurEditeur p) {
		this.p = p;
	}

	/**
	 * Dessine le rectangle de selection
	 * @param g 
	 */
	@Override
	public void paintComponent(Graphics g) {
		Graphics2D gd = (Graphics2D) g;
		if (p != null && p.c1 != null) {
			gd.setColor(new Color(255, 0, 0, 40));
			gd.fillRect(p.colonneMin * Case.TAILLE, p.ligneMin * Case.TAILLE, (p.colonneMax - p.colonneMin + 1) * Case.TAILLE, (p.ligneMax - p.ligneMin + 1) * Case.TAILLE);
		}

	}
}
