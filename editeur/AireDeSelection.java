/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package editeur;

import ecouteur.EcouteurEditeur;
import ecouteur.EcouteurPlateau;
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
	
	public AireDeSelection(){
	}
	
	public AireDeSelection(EcouteurEditeur p) {
		this.p = p;
	}

	@Override
	public void paintComponent(Graphics g) {
		Graphics2D gd = (Graphics2D) g;
		if(p != null && p.c1 != null){
			gd.setColor(new Color(80, 80, 80, 80));
			gd.fillRect(p.colonneMin*Case.TAILLE, p.ligneMin*Case.TAILLE, (p.colonneMax-p.colonneMin+1)*Case.TAILLE, (p.ligneMax-p.ligneMin+1)*Case.TAILLE);
		}

	}
}
