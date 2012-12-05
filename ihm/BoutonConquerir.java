/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ihm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import moteur.Case;

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
	nom = fenetre.c.getPion().getNomCapacite();
	System.out.println("Conquerir");
	fenetre.effacerFenetre();
    }
}