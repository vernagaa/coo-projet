/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ihm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
	System.out.println("Capacite");
	fenetre.effacerFenetre();
    }
}