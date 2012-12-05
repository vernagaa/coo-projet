package ihm;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import javax.swing.JComponent;
import moteur.Case;
import moteur.Moteur;
import moteur.classes.Tacticien;

/**
 *
 * @author Kévin
 */
public final class FenetreChoixPion extends JComponent {

    private Case c;
    protected Moteur m;
    private BoutonTacticien t;
    private BoutonConquerir conq;

    public FenetreChoixPion(Moteur m) {
	this.m = m;
	setSize(Case.TAILLE * 3, Case.TAILLE * 2);
	BoutonAttaquer b = new BoutonAttaquer(this);
	add(b);
	BoutonCapacite c = new BoutonCapacite(this);
	add(c);
	t = new BoutonTacticien(this);
	add(t);
	conq = new BoutonConquerir(this);
	add(conq);
	m.aireDeJeu.add(this);
	setVisible(false);
    }

    public void placerFenetre(Case c) {
	this.c = c;
	setSize(Case.TAILLE * 3, Case.TAILLE * 2);
	if (c.getPion() == m.getJoueurCourant().getCommandant()) {
	    System.out.println("Je suis un commandant");
	    setSize(Case.TAILLE * 3, Case.TAILLE * 3);
	    conq.setPosition(2);
	    t.setPosition(3);
	    if (c.getPion() == m.getJoueurCourant().getTacticien()) {
		System.out.println("Je suis un tacticien commandant");
		setSize(Case.TAILLE * 3, Case.TAILLE * 4);
	    }
	} else if (c.getPion() == m.getJoueurCourant().getTacticien()) {
	    System.out.println("Je suis un tacticien");
	    setSize(Case.TAILLE * 3, Case.TAILLE * 3);
	    t.setPosition(2);
	    conq.setPosition(3);
	}
	setLocation((c.getColonne() + 1) * Case.TAILLE, c.getLigne() * Case.TAILLE);
	setVisible(true);

    }

    public void effacerFenetre() {
	setVisible(false);
    }

    public Moteur getMoteur() {
	return m;
    }

    public Case getCase() {
	return c;
    }
}
