package ihm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import moteur.Case;

/**
 *
 * @author chappelk
 */
public class BoutonFinDeTour extends BoutonAction implements ActionListener {

	/**
	 * Constructeur du bouton de fin de tour
	 * @param fenetre
	 */
	public BoutonFinDeTour(final FenetreChoixPion fenetre) {
		super("Fin de tour", 6, fenetre);
		addActionListener(this);

	}

	/**
	 * Cas du clic sur fin de tour
	 * @param e 
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		fenetre.getMoteur().changementJoueur();
		fenetre.getMoteur().getAnimation().animerFinDeTour();
		fenetre.effacerFenetre();
	}

	/**
	 * Cache la fenêtre des choix
	 */
	public void effacerFenetre() {
		setVisible(false);
	}

	/**
	 * Affiche la fentre des choix à coté du pion sélectionné
	 * @param c
	 */
	public void placerFenetre(Case c) {
		setLocation((c.getColonne() + 1) * Case.TAILLE, c.getLigne() * Case.TAILLE);
		setVisible(true);
	}
}
