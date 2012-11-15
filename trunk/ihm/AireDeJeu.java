/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ihm;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;
import moteur.Case;
import moteur.Plateau;
import moteur.Terrain;

/**
 *
 * @author disavinr
 */
public class AireDeJeu extends JComponent {
	private Plateau plateau;
	private Terrain terrain;
	
	public AireDeJeu(Plateau plateau) {
		setPreferredSize(new Dimension(plateau.getNbColonne()*Case.TAILLE, plateau.getNbLigne()*Case.TAILLE));
		this.plateau = plateau;
		terrain = new Terrain();
	}
	
	public AireDeJeu() {
		this(new Plateau());
	}
	
	public Plateau getPlateau() {
		return plateau;
	}

	public void setPlateau(Plateau plateau) {
		this.plateau = plateau;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		Graphics2D gd = (Graphics2D)g;
		
		for(Case[] c : plateau.get()){
			for(Case c1 : c){
				gd.drawImage(terrain.getImage(c1.getTypeTerrain()), c1.getColonne()*Case.TAILLE, c1.getLigne()*Case.TAILLE, null);
			}
		}

		for(int i = 0;i< getHeight();i+=Case.TAILLE){
			gd.drawLine(0, i, getWidth(), i);
		}
		for(int j=0; j< getWidth(); j+=Case.TAILLE){
			gd.drawLine(j, 0, j, getHeight());
		}
		gd.drawLine(0, getHeight()-1, getWidth()-1, getHeight()-1);
		gd.drawLine(getWidth()-1, 0, getWidth()-1, getHeight()-1);		
	}

}
