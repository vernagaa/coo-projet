package ihm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author chappelk
 */
public class BoutonConquerir extends BoutonAction implements ActionListener {

	public BoutonConquerir(final FenetreChoixPion fenetre) {
		super("Conquerir", 2, fenetre);
		addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("Conquerir");
		fenetre.effacerFenetre();
	}
}
