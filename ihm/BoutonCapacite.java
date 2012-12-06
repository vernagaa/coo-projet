package ihm;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import moteur.Case;

/**
 *
 * @author chappelk
 */
public class BoutonCapacite extends BoutonAction implements ActionListener {

	public BoutonCapacite(final FenetreChoixPion fenetre) {
		super("capacite", 1, fenetre);
		addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (fenetre.getCase().getPion().capaciteActive()) {
			fenetre.getCase().getPion().capaciteSpeciale();
		}
		fenetre.effacerFenetre();
	}

	@Override
	public void paintComponent(Graphics g) {
		Graphics2D gd = (Graphics2D) g;
		if (survol) {
			gd.setColor(new Color(170, 170, 170, 100));
			gd.fillRect(0, 0, Case.TAILLE * 3, Case.TAILLE);
		} else {
			gd.setColor(new Color(200, 200, 200, 100));
			gd.fillRect(0, 0, Case.TAILLE * 3, Case.TAILLE);
		}
		gd.setColor(Color.WHITE);
		//TODO Placer mieux
		gd.drawString(fenetre.getCase().getPion().getNomCapacite(), Case.TAILLE / 5, Case.TAILLE / 2);
	}
}