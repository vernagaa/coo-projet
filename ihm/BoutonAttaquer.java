package ihm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author chappelk
 */
public class BoutonAttaquer extends BoutonAction implements ActionListener {

	/**
	 * Constructeur du bouton attaquer
	 * @param fenetre
	 */
	public BoutonAttaquer(final FenetreChoixPion fenetre) {
		super("Attaquer", 0, fenetre);
		addActionListener(this);
		addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent e) {
				fenetre.getCase().getPion().attaque();
				fenetre.getMoteur().getAireDeJeu().setAfficherPorteeAttaque(true, fenetre.getCase());
				fenetre.getMoteur().getAireDeJeu().repaint();
				survol = true;
				repaint();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				survol = false;
				repaint();
				fenetre.getMoteur().getAireDeJeu().setAfficherPorteeAttaque(false, fenetre.getCase());
				fenetre.getMoteur().getAireDeJeu().repaint();
			}
		});

	}

	/**
	 * Cas du clic sur attaquer
	 * @param e 
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		fenetre.getMoteur().setAttaqueEnCours(true);
		fenetre.getMoteur().getAireDeJeu().setAttaqueEnCours(true);
		fenetre.effacerFenetre();
	}
}
