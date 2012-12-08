package ihm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import moteur.Case;

/**
 *
 * @author chappelk
 */
public class BoutonFinDeTour extends BoutonAction implements ActionListener {

	public BoutonFinDeTour(final FenetreChoixPion fenetre) {
		super("Fin de tour", 6, fenetre);
		addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		fenetre.m.changementJoueur();
		fenetre.m.animation.animerFinDeTour();
		fenetre.effacerFenetre();
	}

	public void effacerFenetre() {
		setVisible(false);
	}

	public void placerFenetre(Case c) {
		setLocation((c.getColonne() + 1) * Case.TAILLE, c.getLigne() * Case.TAILLE);
		setVisible(true);
	}
}
