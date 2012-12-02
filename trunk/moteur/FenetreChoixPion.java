/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package moteur;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

/**
 *
 * @author Kévin
 */
public final class FenetreChoixPion extends FenetreAction {

	public FenetreChoixPion(Moteur m) {
		super(m);
//		if(c.getPion().isTacticien() || c.getPion().isCommandant()){
//			hauteur = Case.TAILLE*3;
//			largeur = Case.TAILLE*3;
//		} else {
		hauteur = Case.TAILLE * 2;
		largeur = Case.TAILLE * 3;
//		}
		setSize(largeur, hauteur);
	}

	@Override
	public void paintComponent(Graphics g) {
		Graphics2D gd = (Graphics2D) g;
		gd.setColor(new Color(170, 170, 170, 100));
		gd.fillRect(0, 0, largeur, hauteur);
		m.aireDeJeu.setAfficherPorteeAttaque(false, c);
		m.aireDeJeu.repaint();
		gd.setColor(Color.WHITE);
		if (Survol != null) {
			gd.setColor(new Color(200, 200, 200, 100));
			gd.fillRect(0, Survol.y * Case.TAILLE, Case.TAILLE * 3, Case.TAILLE);
			if (Survol.y == 0) {
				c.getPion().attaque();
				m.aireDeJeu.setAfficherPorteeAttaque(true, c);
				m.aireDeJeu.repaint();
				gd.setColor(Color.RED);
			} else {
				gd.setColor(Color.WHITE);
			}
			gd.drawString("Attaquer", 0 + 10, 0 + 20);
			if (Survol.y == 1) {
				gd.setColor(Color.BLUE);
			} else {
				gd.setColor(Color.WHITE);
			}
			gd.drawString("Capacité", 0 + 10, Case.TAILLE + 20);

		} else {
			gd.setColor(Color.WHITE);
			gd.drawString("Attaquer", 0 + 10, 0 + 20);
			gd.drawString("Capacité", 0 + 10, Case.TAILLE + 20);

		}

	}

	@Override
	public void mouseExited(MouseEvent e) {
		Survol = null;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int y = e.getY();
		int lig = y / Case.TAILLE;

//	On indique qu'il ne faudra plus afficher la portee d'attaque du pion
		m.aireDeJeu.setAfficherPorteeAttaque(false, c);
		if (lig == 0) {
			System.out.println("Attaquer");
			m.setAttaqueEnCours(true);
			m.aireDeJeu.setAttaqueEnCours(true);
		} else if (lig == 1) {
			System.out.println("Capacité");
		} else if (lig == 2) {
			System.out.println("Autres");
		}
		effacerFenetre();
	}
}
