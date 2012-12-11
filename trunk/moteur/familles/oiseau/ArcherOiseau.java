package moteur.familles.oiseau;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import moteur.Case;
import moteur.Noeud;
import moteur.Terrain;
import moteur.Textures;
import moteur.classes.Archer;

/**
 *
 * @author vernagaa
 */
public final class ArcherOiseau extends Archer implements Oiseau {

	private boolean vol;

	/**
	 * Constructeur d'un oiseau archer
	 * @param c
	 */
	public ArcherOiseau(Case c) {
		super(vieArcher, forceArcher, precisionArcher, vitesseArcher, defenseArcher, chanceArcher, porteeArcher, mouvementArcher, c);
		vol = false;
		nomCapaciteSpeciale = nomCapacite;
	}

	@Override
	public String getNom() {
		return nomArcher;
	}

	@Override
	public BufferedImage getImage() {
		return Textures.getPersonnage(Textures.ARCHEROISEAU, orientation);
	}

	@Override
	public boolean isEnvole() {
		return vol;
	}

	@Override
	public void enEnvole() {
		vol = true;
	}

	@Override
	public void calculDeplacementPossible() {
		listeDeplacementPossible.clear();
		deplacement.clear();
		ArrayList<Noeud> listeFerme = new ArrayList<Noeud>();
		ArrayList<Noeud> listeOuverte = new ArrayList<Noeud>();

		Noeud tmp;
		Noeud tmp2;
		Noeud noeudContenu = new Noeud(c, 0);
		Case caseVerif;

		tmp = new Noeud(c, 0);
		listeOuverte.add(tmp);
		listeDeplacementPossible.add(tmp);

		if (getTourspecial() == 3 && !c.isObstacleDeplacement()) {
			vol = false;
			setTourspecial(0);
			specialIndispo();
		}

		while (!listeOuverte.isEmpty()) {
			tmp = listeOuverte.remove(0);
			listeFerme.add(tmp);
			caseVerif = c.getPlateau().get(tmp.getC().getLigne() + 1, tmp.getC().getColonne());
			if (caseVerif != null) {
				tmp2 = new Noeud(caseVerif, tmp.getCout() + Terrain.effetDeplacement(caseVerif.getTypeTerrain()));
			} else {
				tmp2 = new Noeud(caseVerif, tmp.getCout());
			}
			recopierNoeudDansNoeud(tmp, tmp2);
			tmp2.getListeNoeud().add(tmp);
			if (caseVerif != null && contient(tmp2, listeDeplacementPossible,noeudContenu)) {
				if (tmp2.getCout() < noeudContenu.getCout()) {
					noeudContenu.setCout(tmp2.getCout());
					noeudContenu.setListeNoeud(tmp2.getListeNoeud());
				}
			} else if (caseVerif != null && (!tmp2.getC().isObstacleDeplacement() || vol) && (!tmp2.getC().contientPion() || vol) && !listeFerme.contains(tmp2)
					&& !listeOuverte.contains(tmp2) && tmp2.getCout() <= Terrain.CoutDefaut * mouvement) {
				listeDeplacementPossible.add(tmp2);
				listeOuverte.add(tmp2);
			}
			caseVerif = c.getPlateau().get(tmp.getC().getLigne() - 1, tmp.getC().getColonne());
			if (caseVerif != null) {
				tmp2 = new Noeud(caseVerif, tmp.getCout() + Terrain.effetDeplacement(caseVerif.getTypeTerrain()));
			} else {
				tmp2 = new Noeud(caseVerif, tmp.getCout());
			}
			recopierNoeudDansNoeud(tmp, tmp2);
			tmp2.getListeNoeud().add(tmp);
			if (caseVerif != null && contient(tmp2, listeDeplacementPossible,noeudContenu)) {
				if (tmp2.getCout() < noeudContenu.getCout()) {
					noeudContenu.setCout(tmp2.getCout());
					noeudContenu.setListeNoeud(tmp2.getListeNoeud());
				}
			} else if (caseVerif != null && (!tmp2.getC().isObstacleDeplacement() || vol) && (!tmp2.getC().contientPion() || vol) && !listeFerme.contains(tmp2)
					&& !listeOuverte.contains(tmp2) && tmp2.getCout() <= Terrain.CoutDefaut * mouvement) {
				listeDeplacementPossible.add(tmp2);
				listeOuverte.add(tmp2);
			}
			caseVerif = c.getPlateau().get(tmp.getC().getLigne(), tmp.getC().getColonne() + 1);
			if (caseVerif != null) {
				tmp2 = new Noeud(caseVerif, tmp.getCout() + Terrain.effetDeplacement(caseVerif.getTypeTerrain()));
			} else {
				tmp2 = new Noeud(caseVerif, tmp.getCout());
			}
			recopierNoeudDansNoeud(tmp, tmp2);
			tmp2.getListeNoeud().add(tmp);
			if (caseVerif != null && contient(tmp2, listeDeplacementPossible,noeudContenu)) {
				if (tmp2.getCout() < noeudContenu.getCout()) {
					noeudContenu.setCout(tmp2.getCout());
					noeudContenu.setListeNoeud(tmp2.getListeNoeud());
				}
			} else if (caseVerif != null && (!tmp2.getC().isObstacleDeplacement() || vol) && (!tmp2.getC().contientPion() || vol) && !listeFerme.contains(tmp2)
					&& !listeOuverte.contains(tmp2) && tmp2.getCout() <= Terrain.CoutDefaut * mouvement) {
				listeDeplacementPossible.add(tmp2);
				listeOuverte.add(tmp2);
			}
			caseVerif = c.getPlateau().get(tmp.getC().getLigne(), tmp.getC().getColonne() - 1);
			if (caseVerif != null) {
				tmp2 = new Noeud(caseVerif, tmp.getCout() + Terrain.effetDeplacement(caseVerif.getTypeTerrain()));
			} else {
				tmp2 = new Noeud(caseVerif, tmp.getCout());
			}
			recopierNoeudDansNoeud(tmp, tmp2);
			tmp2.getListeNoeud().add(tmp);
			if (caseVerif != null && contient(tmp2, listeDeplacementPossible,noeudContenu)) {
				if (tmp2.getCout() < noeudContenu.getCout()) {
					noeudContenu.setCout(tmp2.getCout());
					noeudContenu.setListeNoeud(tmp2.getListeNoeud());
				}
			} else if (caseVerif != null && (!tmp2.getC().isObstacleDeplacement() || vol) && (!tmp2.getC().contientPion() || vol) && !listeFerme.contains(tmp2)
					&& !listeOuverte.contains(tmp2) && tmp2.getCout() <= Terrain.CoutDefaut * mouvement) {
				listeDeplacementPossible.add(tmp2);
				listeOuverte.add(tmp2);
			}
		}
		if (vol) {
			ArrayList<Noeud> l = (ArrayList<Noeud>) listeDeplacementPossible.clone();
			for (Noeud n : l) {
				if (c != n.getC() && n.getC().contientPion()) {
					listeDeplacementPossible.remove(n);
				}
			}
			specialIndispo();
		}
	}

	@Override
	public void capaciteSpeciale() {
		enEnvole();
		specialIndispo();
		setTourspecial(getTourspecial() + 1);
		calculDeplacementPossible();
	}

	@Override
	public void specialIndispo() {
		setSpecial(cooldown);
	}

	@Override
	public void finDeTour() {
		if (vol && getTourspecial() < 3) {
			setTourspecial(getTourspecial() + 1);
		}
		setMouvement(getMouvementBase());
		recuperationCapacite();
	}

	@Override
	public BufferedImage getImage(int i) {
		return Textures.getPersonnage(Textures.ARCHEROISEAU, orientation, i);
	}
}
