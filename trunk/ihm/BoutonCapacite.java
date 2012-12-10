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

	/**
	 * Constructeur bouton de capacité
	 * @param fenetre
	 */
	public BoutonCapacite(final FenetreChoixPion fenetre) {
		super("capacite", 1, fenetre);
		addActionListener(this);

	}

	/**
	 * Cas du clic sur capacité
	 * @param e 
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (fenetre.getCase().getPion().capaciteActive()) {
			fenetre.getCase().getPion().capaciteSpeciale();
			fenetre.getMoteur().utiliserAction();
		}
		fenetre.effacerFenetre();
	}

	/**
	 * Surligne le bouton ou non et affiche le nom de la capacité
	 * @param g 
	 */
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