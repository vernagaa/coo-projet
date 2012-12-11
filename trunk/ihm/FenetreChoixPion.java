package ihm;

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
	private Moteur m;
	private BoutonTacticien t;
	private BoutonConquerir conq;
	private BoutonCapacite capa;
	private BoutonAttaquer b;
	private BoutonFinDeTour finDeTour;

	/**
	 * Constructeur de la fenêtre de choix
	 * @param m
	 */
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
		m.getAireDeJeu().add(this);
		setVisible(false);
	}

	/**
	 * Affichage des boutons selon la classe, le role et le terrain 
	 * @param c
	 */
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
			if (c.getPion() == m.getJoueurCourant().getCommandant() && !c.isObstacleDeplacement() && c.getPion().conquetePossible()) {
				setSize(Case.TAILLE * 3, Case.TAILLE * (i + 1));
				conq.setPosition(i++);
				if (placement.getPlateau().get(placement.getLigne() + i, placement.getColonne()) == null) {
					placement = placement.getPlateau().get(placement.getLigne() - 1, placement.getColonne());
				}
			} else {
				conq.setPosition(6);
			}
			if (c.getPion() == m.getJoueurCourant().getTacticien() && ((Tacticien)c.getPion()).telePorterDisponible()) {
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

	/**
	 * Cache la fenetre
	 */
	public void effacerFenetre() {
		setVisible(false);
	}

	/**
	 * Accesseur du moteur
	 * @return m
	 */
	public Moteur getMoteur() {
		return m;
	}

	/**
	 * Accesseur de la case
	 * @return c
	 */
	public Case getCase() {
		return c;
	}

}
