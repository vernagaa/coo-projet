package ihm;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;
import moteur.Case;
import moteur.Plateau;
import moteur.Textures;

/**
 * Prévisualisation des cartes
 * @author vernagaa
 */
public class Previsualisation extends JComponent {

	private Plateau plateau;

	/**
	 * Constructeur
	 * @param plateau plateau à prévisualiser
	 */
	public Previsualisation(Plateau plateau) {
		this.plateau = plateau;
	}

	/**
	 * Constructeur
	 */
	public Previsualisation() {
		plateau = null;
	}

	/**
	 * Getter du plateau prévisualisé
	 * @return 
	 */
	public Plateau getPlateau() {
		return plateau;
	}

	/**
	 * Setter du plateau prévisualisé
	 * La prévisualisation est ensuite actualisée
	 * @param plateau 
	 */
	public void setPlateau(Plateau plateau) {
		this.plateau = plateau;
		repaint();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D gd = (Graphics2D) g;
		
		final int caseTaille = Math.min((getWidth()-1)/Plateau.NB_COLONNE, (getHeight()-1)/Plateau.NB_LIGNE);
		final int hauteur = caseTaille * Plateau.NB_LIGNE;
		final int largeur = caseTaille * Plateau.NB_COLONNE;
		
		final int decalH = (getHeight() - hauteur) / 2;
		final int decalL = (getWidth() - largeur) / 2;
		
		if(plateau != null) {
			for (Case[] c : plateau.get()) {
				for (Case c1 : c) {
					gd.drawImage(Textures.scale(Textures.getTerrain(c1.getTypeTerrain()), caseTaille, caseTaille), c1.getColonne() * caseTaille + decalL, c1.getLigne() * caseTaille + decalH, null);
					if (c1.getBordure() != null) {
						gd.drawImage(Textures.scale(Textures.getBordure(c1.getBordure().getTypeBordure()), caseTaille, caseTaille), c1.getColonne() * caseTaille + decalL, c1.getLigne() * caseTaille + decalH, null);
					}
					if (c1.getObstacle() != null) {
					gd.drawImage(Textures.scale(c1.getObstacle().getImage(), caseTaille, caseTaille), c1.getColonne() * caseTaille + decalL, c1.getLigne() * caseTaille + decalH, null);
					}
				}
			}
		}
		
		// Construit la grille du plateau	
		gd.setColor(new Color(80, 80, 80, 40));
		
		for (int i = 0; i <= Plateau.NB_LIGNE; i++) {
			gd.drawLine(decalL, i * caseTaille + decalH, Plateau.NB_COLONNE * caseTaille + decalL, i * caseTaille + decalH);
		}
		for (int j = 0; j <= Plateau.NB_COLONNE; j ++) {
			gd.drawLine(j * caseTaille + decalL, decalH, j * caseTaille + decalL, Plateau.NB_LIGNE * caseTaille + decalH);
		}
	}
	
	
}
