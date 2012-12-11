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

/**
 *
 * @author Kévin
 */
public class AireDAnimation extends JComponent {

	private Moteur moteur;
	public boolean animationFin;
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
	public BufferedImage imageEnCours;
	public Point position;
	public boolean animationDeplacement;
	public boolean animationElire;

	/**
	 * Constructeur pour le form
	 * Jamais appelé mais nécessaire
	 */
	public AireDAnimation() {
	}

	/**
	 * Constructeur de l'aire d'animation
	 * @param moteur 
	 */
	public AireDAnimation(Moteur moteur) {
		this.moteur = moteur;
		positionA = new Point();
		positionD = new Point();
		position = new Point();
	}

	/**
	 * Affiche les animations
	 * @param g 
	 */
	@Override
	public void paintComponent(Graphics g) {
		Graphics2D gd = (Graphics2D) g;
		if (animationFin) {
			gd.setColor(new Color(0, 0, 0, 200));
			gd.fillRect(0, 0, getWidth(), getHeight());
			gd.setColor(Color.WHITE);
			gd.drawString("Le joueur " + moteur.getJoueurCourant().getNom() + " remporte la partie", getWidth() / 2 - 100, getHeight() / 2);
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
			gd.setColor(new Color(0, 0, 0, 15 + 8 * compteurFinDeTour));
			gd.fillRect(0, 0, getWidth(), getHeight());
			gd.setColor(Color.WHITE);
			gd.setFont(new Font("impact", Font.BOLD, 20));
			gd.drawString("Fin du tour !", getWidth() / 2 - 50, getHeight() / 2);
			gd.drawString("A " + moteur.getJoueurCourant().getNom() + " de jouer !", getWidth() / 2 - 50, getHeight() / 2 + 30);
		} else if (animationElire) {
			gd.setColor(new Color(0, 0, 0, 6 * compteurFinDeTour));
			gd.fillRect(0, 0, getWidth(), getHeight());
			gd.setColor(Color.WHITE);
			gd.setFont(new Font("impact", Font.BOLD, 20));
			gd.drawString("Le commandant du joueur " + moteur.getJoueurElireCommandant().getNom() + " est mort.", getWidth() / 2 - 150, getHeight() / 2);
			gd.drawString("Il doit en choisir un nouveau !", getWidth() / 2 - 100, getHeight() / 2 + 30);


		}

	}
}
