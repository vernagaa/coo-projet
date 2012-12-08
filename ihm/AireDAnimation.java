/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ihm;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.JComponent;
import moteur.Case;
import moteur.Moteur;
import moteur.Plateau;

/**
 *
 * @author Kévin
 */
public class AireDAnimation extends JComponent {

	private Moteur moteur;
	public boolean animationLancement;
	/*
	 * Animation Fin De Tour
	 */
	public boolean animationFinDeTour;
	public int compteurFinDeTour;
	/*
	 * Animation Attaque
	 */
	public boolean animationAttaque;
	public boolean attaquant;
	public boolean esquive;
	public int compteurAttaque;
	public int val;
	public Point positionA;
	public Point positionD;
	/*
	 * Animation Deplacement (ne marche que pour les oiseaux)
	 */
	public BufferedImage imageEnCours;
	public Point position;
	public boolean animationDeplacement;

	public AireDAnimation() {
	}

	public AireDAnimation(Moteur moteur) {
		this.moteur = moteur;
		positionA = new Point();
		positionD = new Point();
		position = new Point();
	}

	public void paintComponent(Graphics g) {
		Graphics2D gd = (Graphics2D) g;
		if (animationLancement) {
		} else if (animationAttaque) {
			gd.setColor(Color.RED);
			if (!esquive) {
				if (attaquant) {
					gd.setFont(new Font("Arial", Font.BOLD, 16));
					gd.drawString("-" + val, positionD.x * Case.TAILLE + Case.TAILLE / 10, positionD.y * Case.TAILLE + Case.TAILLE / 2);
				} else {
					gd.setFont(new Font("Arial", Font.BOLD, 16));
					gd.drawString("-" + val, positionA.x * Case.TAILLE + Case.TAILLE / 10, positionA.y * Case.TAILLE + Case.TAILLE / 2);
				}
			}
		} else if (animationDeplacement) {		
			gd.drawImage(imageEnCours, position.x, position.y, null);
		} else if (animationFinDeTour) {
			gd.setColor(new Color(0, 0, 0, 15 + 6 * compteurFinDeTour));
			gd.fillRect(0, 0, getWidth(), getHeight());
			gd.setColor(new Color(255, 255, 255, 15 + 6 * compteurFinDeTour));
			gd.setFont(new Font("impact", Font.BOLD, 20));
			gd.drawString("Fin du tour !", getWidth() / 2 - 50, getHeight() / 2);
			gd.drawString("A " + moteur.getJoueurCourant().getNom() + " de jouer !", getWidth() / 2 - 50, getHeight() / 2 + 30);

		}

	}
}
