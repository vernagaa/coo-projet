package ihm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author chappelk
 */
public class BoutonTacticien extends BoutonAction implements ActionListener {

	/**
	 * Constructeur du bouton tacticien
	 * @param fenetre
	 */
	public BoutonTacticien(final FenetreChoixPion fenetre) {
		super("Teleporteur", 3, fenetre);
		addActionListener(this);

	}

	/**
	 * Cas du clic sur teleporteur
	 * @param e 
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		fenetre.getMoteur().setPoseTeleporteur(true);
		fenetre.getMoteur().getAireDeJeu().setAfficherPoseTeleporteur(true, fenetre.getCase());
		fenetre.getMoteur().getAireDeJeu().repaint();
		fenetre.effacerFenetre();
	}
}
