/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ihm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import moteur.Case;
import moteur.classes.Tacticien;

/**
 *
 * @author chappelk
 */
public class BoutonTacticien extends BoutonAction implements ActionListener {

    public BoutonTacticien(final FenetreChoixPion fenetre) {
	super("Teleporteur", 3, fenetre);
	addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
	System.out.println("Placer Teleporteur");
	fenetre.m.setPoseTeleporteur(true);
	fenetre.m.aireDeJeu.setAfficherPoseTeleporteur(true, fenetre.c);
	fenetre.m.aireDeJeu.repaint();
	fenetre.effacerFenetre();
    }
}
