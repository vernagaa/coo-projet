package moteur.familles.oiseau;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import moteur.Case;
import moteur.Noeud;
import moteur.Terrain;
import moteur.Textures;
import moteur.classes.Guerrier;

/**
 *
 * @author vernagaa
 */
public final class GuerrierOiseau extends Guerrier implements Oiseau {

	private boolean vol;

	public GuerrierOiseau(Case c) {
		super(vieGuerrier, forceGuerrier, precisionGuerrier, vitesseGuerrier, defenseGuerrier, chanceGuerrier, porteeGuerrier, mouvementGuerrier, c);
		vol = false;
		nomCapaciteSpeciale = nomCapacite;
	}

	@Override
	public String getNom() {
		return nomGuerrier;
	}

	@Override
	public BufferedImage getImage() {
		return Textures.getPersonnage(Textures.GUERRIEROISEAU, orientation);
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
			caseVerif = c.getPlateau().get(tmp.c.getLigne() + 1, tmp.c.getColonne());
			if (caseVerif != null) {
				tmp2 = new Noeud(caseVerif, tmp.cout + Terrain.effetDeplacement(caseVerif.getTypeTerrain()));
			} else {
				tmp2 = new Noeud(caseVerif, tmp.cout);
			}
			recopierNoeudDansNoeud(tmp, tmp2);
			tmp2.listeNoeud.add(tmp);
			if (caseVerif != null && contient(tmp2, listeDeplacementPossible)) {
				if (tmp2.cout < noeudContenu.cout) {
					noeudContenu.cout = tmp2.cout;
					noeudContenu.listeNoeud = tmp2.listeNoeud;
				}
			} else if (caseVerif != null && (!tmp2.c.isObstacleDeplacement() || vol) && (!tmp2.c.isOccupee() || vol) && !listeFerme.contains(tmp2)
					&& !listeOuverte.contains(tmp2) && tmp2.cout <= 2 * mouvement) {
				listeDeplacementPossible.add(tmp2);
				listeOuverte.add(tmp2);
			}
			caseVerif = c.getPlateau().get(tmp.c.getLigne() - 1, tmp.c.getColonne());
			if (caseVerif != null) {
				tmp2 = new Noeud(caseVerif, tmp.cout + Terrain.effetDeplacement(caseVerif.getTypeTerrain()));
			} else {
				tmp2 = new Noeud(caseVerif, tmp.cout);
			}
			recopierNoeudDansNoeud(tmp, tmp2);
			tmp2.listeNoeud.add(tmp);
			if (caseVerif != null && contient(tmp2, listeDeplacementPossible)) {
				if (tmp2.cout < noeudContenu.cout) {
					noeudContenu.cout = tmp2.cout;
					noeudContenu.listeNoeud = tmp2.listeNoeud;
				}
			} else if (caseVerif != null && (!tmp2.c.isObstacleDeplacement() || vol) && (!tmp2.c.isOccupee() || vol) && !listeFerme.contains(tmp2)
					&& !listeOuverte.contains(tmp2) && tmp2.cout <= 2 * mouvement) {
				listeDeplacementPossible.add(tmp2);
				listeOuverte.add(tmp2);
			}
			caseVerif = c.getPlateau().get(tmp.c.getLigne(), tmp.c.getColonne() + 1);
			if (caseVerif != null) {
				tmp2 = new Noeud(caseVerif, tmp.cout + Terrain.effetDeplacement(caseVerif.getTypeTerrain()));
			} else {
				tmp2 = new Noeud(caseVerif, tmp.cout);
			}
			recopierNoeudDansNoeud(tmp, tmp2);
			tmp2.listeNoeud.add(tmp);
			if (caseVerif != null && contient(tmp2, listeDeplacementPossible)) {
				if (tmp2.cout < noeudContenu.cout) {
					noeudContenu.cout = tmp2.cout;
					noeudContenu.listeNoeud = tmp2.listeNoeud;
				}
			} else if (caseVerif != null && (!tmp2.c.isObstacleDeplacement() || vol) && (!tmp2.c.isOccupee() || vol) && !listeFerme.contains(tmp2)
					&& !listeOuverte.contains(tmp2) && tmp2.cout <= 2 * mouvement) {
				listeDeplacementPossible.add(tmp2);
				listeOuverte.add(tmp2);
			}
			caseVerif = c.getPlateau().get(tmp.c.getLigne(), tmp.c.getColonne() - 1);
			if (caseVerif != null) {
				tmp2 = new Noeud(caseVerif, tmp.cout + Terrain.effetDeplacement(caseVerif.getTypeTerrain()));
			} else {
				tmp2 = new Noeud(caseVerif, tmp.cout);
			}
			recopierNoeudDansNoeud(tmp, tmp2);
			tmp2.listeNoeud.add(tmp);
			if (caseVerif != null && contient(tmp2, listeDeplacementPossible)) {
				if (tmp2.cout < noeudContenu.cout) {
					noeudContenu.cout = tmp2.cout;
					noeudContenu.listeNoeud = tmp2.listeNoeud;
				}
			} else if (caseVerif != null && (!tmp2.c.isObstacleDeplacement() || vol) && (!tmp2.c.isOccupee() || vol) && !listeFerme.contains(tmp2)
					&& !listeOuverte.contains(tmp2) && tmp2.cout <= 2 * mouvement) {
				listeDeplacementPossible.add(tmp2);
				listeOuverte.add(tmp2);
			}
		}
		if (vol) {
			ArrayList<Noeud> l = (ArrayList<Noeud>) listeDeplacementPossible.clone();
			for (Noeud n : l) {
				if (c != n.c && n.c.isOccupee()) {
					listeDeplacementPossible.remove(n);
				}
			}
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
	public BufferedImage getImageMouvement(int i) {
		return Textures.getPersonnage(Textures.GUERRIEROISEAU, orientation, i);
	}
}
