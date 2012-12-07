package ihm;

import javax.swing.JComponent;
import moteur.Case;
import moteur.Moteur;

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
		int i = 0;
		Case placement = c;
		if (m.getJoueurCourant().actionPossibles()) {
			if (placement.getPlateau().get(placement.getLigne(), placement.getColonne() + 3) == null) {
				placement = placement.getPlateau().get(placement.getLigne(), placement.getColonne() - 4);
			}
			if (!c.isObstacleDeplacement()) {
				setSize(Case.TAILLE * 3, Case.TAILLE * (i + 1));
				b.setPosition(i++);
			} else {
				b.setPosition(6);
			}
			if (m.getJoueurCourant().getTacticien() != null && c.getPion().capaciteActive()) {
				setSize(Case.TAILLE * 3, Case.TAILLE * (i + 1));
				capa.setPosition(i++);
				if (placement.getPlateau().get(placement.getLigne() + i, placement.getColonne()) == null) {
					placement = placement.getPlateau().get(placement.getLigne() - 1, placement.getColonne());
				}
			} else {
				capa.setPosition(6);
			}
			if (c.getPion() == m.getJoueurCourant().getCommandant()) {
				setSize(Case.TAILLE * 3, Case.TAILLE * (i + 1));
				conq.setPosition(i++);
				if (placement.getPlateau().get(placement.getLigne() + i, placement.getColonne()) == null) {
					placement = placement.getPlateau().get(placement.getLigne() - 1, placement.getColonne());
				}
			} else {
				conq.setPosition(6);
			}
			if (c.getPion() == m.getJoueurCourant().getTacticien()) {
				setSize(Case.TAILLE * 3, Case.TAILLE * (i + 1));
				t.setPosition(i++);
				if (placement.getPlateau().get(placement.getLigne() + i, placement.getColonne()) == null) {
					placement = placement.getPlateau().get(placement.getLigne() - 1, placement.getColonne());
				}
			} else {
				t.setPosition(6);
			}
			setSize(Case.TAILLE * 3, Case.TAILLE * (i + 1));
			finDeTour.setPosition(i++);
			if (placement.getPlateau().get(placement.getLigne() + i, placement.getColonne()) == null) {
				placement = placement.getPlateau().get(placement.getLigne() - 1, placement.getColonne());
			}
		} else {
			setSize(Case.TAILLE * 3, Case.TAILLE * (i + 1));
			finDeTour.setPosition(i++);
			b.setPosition(6);
			t.setPosition(6);
			conq.setPosition(6);
			capa.setPosition(6);
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

	public void effacerFinDeTour() {
		finDeTour.setPosition(6);
		b.setPosition(0);
		setVisible(false);
	}
}
