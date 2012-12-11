package ihm;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;
import moteur.Case;
import moteur.Plateau;
import moteur.Textures;

/**
 *
 * @author vernagaa
 */
public class Previsualisation extends JComponent {

	private Plateau plateau;

	public Previsualisation(Plateau plateau) {
		this.plateau = plateau;
	}

	public Previsualisation() {
		this(new Plateau("map/map5.map"));
	}

	public Plateau getPlateau() {
		return plateau;
	}

	public void setPlateau(Plateau plateau) {
		this.plateau = plateau;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
//		super.paintComponent(g);
		Graphics2D gd = (Graphics2D) g;
		int caseTaille = Math.min((getWidth()-1)/Plateau.NB_COLONNE, (getHeight()-1)/Plateau.NB_LIGNE);
		
		if(plateau != null) {
			for (Case[] c : plateau.get()) {
				for (Case c1 : c) {
					gd.drawImage(Textures.scale(Textures.getTerrain(c1.getTypeTerrain()), caseTaille, caseTaille), c1.getColonne() * caseTaille, c1.getLigne() * caseTaille, null);
					if (c1.getBordure() != null) {
						gd.drawImage(Textures.scale(Textures.getBordure(c1.getBordure().getTypeBordure()), caseTaille, caseTaille), c1.getColonne() * caseTaille, c1.getLigne() * caseTaille, null);
					}
					if (c1.getObstacle() != null) {
					gd.drawImage(Textures.scale(c1.getObstacle().getImage(), caseTaille, caseTaille), c1.getColonne() * caseTaille, c1.getLigne() * caseTaille, null);
				}
				}
			}
		}
		
		// Construit la grille du plateau	
		gd.setColor(new Color(80, 80, 80, 40));
//		gd.setColor(Color.BLACK);
		for (int i = 0; i <= Plateau.NB_LIGNE; i++) {
			gd.drawLine(0, i * caseTaille, Plateau.NB_COLONNE * caseTaille, i * caseTaille);
		}
		for (int j = 0; j <= Plateau.NB_COLONNE; j ++) {
			gd.drawLine(j * caseTaille, 0, j * caseTaille, Plateau.NB_LIGNE * caseTaille);
		}
		
		
	}
	
}
