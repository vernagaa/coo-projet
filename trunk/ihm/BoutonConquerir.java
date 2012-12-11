package ihm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author chappelk
 */
public class BoutonConquerir extends BoutonAction implements ActionListener {

	/**
	 * Constructeur du bouton conquérir
	 * @param fenetre
	 */
	public BoutonConquerir(final FenetreChoixPion fenetre) {
		super("Conquerir", 2, fenetre);
		addActionListener(this);
		addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent e) {
				fenetre.getCase().getPion().conquerir();
				fenetre.getMoteur().getAireDeJeu().setAfficherPorteeConquerir(true, fenetre.getCase());
				fenetre.getMoteur().getAireDeJeu().repaint();
				survol = true;
				repaint();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				survol = false;
				repaint();
				fenetre.getMoteur().getAireDeJeu().setAfficherPorteeConquerir(false, fenetre.getCase());
				fenetre.getMoteur().getAireDeJeu().repaint();
			}
		});
	}

	/**
	 * Cas du clic sur conquerir
	 * @param e 
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		fenetre.getMoteur().setConqueteEnCours(true);
		fenetre.getMoteur().getAireDeJeu().setConqueteEnCours(true);
		fenetre.effacerFenetre();
	}
}
