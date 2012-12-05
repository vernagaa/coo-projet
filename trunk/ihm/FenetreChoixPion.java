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

    protected Case c;
    protected Moteur m;
    private BoutonTacticien t;
    private BoutonConquerir conq;
    private BoutonCapacite capa;
    private BoutonAttaquer b;
    private BoutonFinDeTour finDeTour;

    public FenetreChoixPion(Moteur m) {
	this.m = m;
	setSize(Case.TAILLE * 3, Case.TAILLE * 2);
	finDeTour = new BoutonFinDeTour(this);
	add(finDeTour);
	b = new BoutonAttaquer(this);
	add(b);
	capa = new BoutonCapacite(this);
	add(capa);
	t = new BoutonTacticien(this);
	add(t);
	conq = new BoutonConquerir(this);
	add(conq);
	m.aireDeJeu.add(this);
	setVisible(false);
    }

    public void placerFenetre(Case c) {
	this.c = c;
	boolean cap = false;
	Case placement = c;
	if (placement.getPlateau().get(placement.getLigne(), placement.getColonne() + 3) == null) {
	    placement = placement.getPlateau().get(placement.getLigne(), placement.getColonne() - 4);
	}
	setSize(Case.TAILLE * 3, Case.TAILLE * 1);
	b.setPosition(0);

	if (m.getJoueurCourant().getTacticien() != null) {
	    setSize(Case.TAILLE * 3, Case.TAILLE * 2);
	    capa.setPosition(1);
	    cap = true;
	    if (placement.getPlateau().get(placement.getLigne() + 1, placement.getColonne()) == null) {
		placement = placement.getPlateau().get(placement.getLigne() - 1, placement.getColonne());
	    }
	} else {
	    capa.setPosition(5);
	}
	if (c.getPion() == m.getJoueurCourant().getCommandant() && cap) {
	    setSize(Case.TAILLE * 3, Case.TAILLE * 3);
	    conq.setPosition(2);
	    t.setPosition(3);
	    if (placement.getPlateau().get(placement.getLigne() + 2, placement.getColonne()) == null) {
		placement = placement.getPlateau().get(placement.getLigne() - 1, placement.getColonne());
	    }
	    if (c.getPion() == m.getJoueurCourant().getTacticien()) {
		setSize(Case.TAILLE * 3, Case.TAILLE * 4);
		if (placement.getPlateau().get(placement.getLigne() + 3, placement.getColonne()) == null) {
		    placement = placement.getPlateau().get(placement.getLigne() - 1, placement.getColonne());
		}
	    }
	} else if (c.getPion() == m.getJoueurCourant().getCommandant()) {
	    setSize(Case.TAILLE * 3, Case.TAILLE * 2);
	    conq.setPosition(1);
	    t.setPosition(2);
	    if (placement.getPlateau().get(placement.getLigne() + 2, placement.getColonne()) == null) {
		placement = placement.getPlateau().get(placement.getLigne() - 1, placement.getColonne());
	    }
	    if (c.getPion() == m.getJoueurCourant().getTacticien()) {
		setSize(Case.TAILLE * 3, Case.TAILLE * 3);
		if (placement.getPlateau().get(placement.getLigne() + 3, placement.getColonne()) == null) {
		    placement = placement.getPlateau().get(placement.getLigne() - 1, placement.getColonne());
		}
	    }
	} else if (c.getPion() == m.getJoueurCourant().getTacticien() && cap) {
	    setSize(Case.TAILLE * 3, Case.TAILLE * 3);
	    t.setPosition(2);
	    conq.setPosition(3);
	    if (placement.getPlateau().get(placement.getLigne() + 2, placement.getColonne()) == null) {
		placement = placement.getPlateau().get(placement.getLigne() - 1, placement.getColonne());
	    }
	} else if (c.getPion() == m.getJoueurCourant().getTacticien()) {
	    setSize(Case.TAILLE * 3, Case.TAILLE * 2);
	    t.setPosition(1);
	    conq.setPosition(2);
	    if (placement.getPlateau().get(placement.getLigne() + 2, placement.getColonne()) == null) {
		placement = placement.getPlateau().get(placement.getLigne() - 1, placement.getColonne());
	    }
	}

	setLocation((placement.getColonne() + 1) * Case.TAILLE, placement.getLigne() * Case.TAILLE);
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

    public void placerFinDeTour(Case c) {
	finDeTour.setPosition(0);
	b.setPosition(2);
	setSize(Case.TAILLE * 3, Case.TAILLE * 1);
	Case placement = c;
	if (placement.getPlateau().get(placement.getLigne(), placement.getColonne() + 3) == null) {
	    placement = placement.getPlateau().get(placement.getLigne(), placement.getColonne() - 4);
	}
	setLocation((placement.getColonne() + 1) * Case.TAILLE, placement.getLigne() * Case.TAILLE);
	setVisible(true);
    }

    public void effacerinDeTour() {
	finDeTour.setPosition(6);
	b.setPosition(0);
	setVisible(false);
    }
}
