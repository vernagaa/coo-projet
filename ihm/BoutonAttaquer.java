/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ihm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComponent;
import moteur.Case;
import moteur.Moteur;

/**
 *
 * @author chappelk
 */
public class BoutonAttaquer extends BoutonAction implements ActionListener {

    public BoutonAttaquer(final FenetreChoixPion fenetre) {
	super("attaquer", 0, fenetre);
	addActionListener(this);
	addMouseListener(new MouseAdapter() {

	    @Override
	    public void mouseEntered(MouseEvent e) {
		fenetre.getCase().getPion().attaque();
		fenetre.getMoteur().aireDeJeu.setAfficherPorteeAttaque(true, fenetre.getCase());
		fenetre.getMoteur().aireDeJeu.repaint();
		survol = true;
		repaint();
	    }

	    @Override
	    public void mouseExited(MouseEvent e) {
		survol = false;
		repaint();
		fenetre.getMoteur().aireDeJeu.setAfficherPorteeAttaque(false, fenetre.getCase());
		fenetre.getMoteur().aireDeJeu.repaint();
	    }
	});

    }

    @Override
    public void actionPerformed(ActionEvent e) {
	System.out.println("Attaquer");
	fenetre.getMoteur().setAttaqueEnCours(true);
	fenetre.getMoteur().aireDeJeu.setAttaqueEnCours(true);
	fenetre.effacerFenetre();
    }
}
