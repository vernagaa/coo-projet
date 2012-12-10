package ihm;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import javax.swing.JComponent;
import moteur.Case;
import moteur.Noeud;
import moteur.Plateau;
import moteur.Teleporteur;
import moteur.Textures;

/**
 *
 * @author disavinr
 */
public class AireDeJeu extends JComponent {
	//TODO constantes pour les couleurs

	private Plateau plateau;
	private boolean attaqueEnCours;
	private boolean conqueteEnCours;
	private boolean afficherPorteeAttaque;
	private boolean afficherPorteeConquerir;
	private boolean mouvementEnCours;
	private Case caseEnCours;
	public Case caseSurvol;
	private boolean debutDePartie;
	private boolean joueurCourant;
	private boolean firstTime;
	private ArrayList<Case> listeCase;
	private boolean afficherPoseTeleporteur;
	private boolean teleportationEnCours;

	public AireDeJeu(Plateau plateau) {
		setPreferredSize(new Dimension(plateau.getNbColonne() * Case.TAILLE + 1, plateau.getNbLigne() * Case.TAILLE + 1));
		this.plateau = plateau;
		joueurCourant = true;
		nouvellePartie();
	}

	public AireDeJeu() {
		this(new Plateau());
	}

	public Plateau getPlateau() {
		return plateau;
	}

	public void setPlateau(Plateau plateau) {
		this.plateau = plateau;
	}

	@Override
	public void paintComponent(Graphics g) {
		Graphics2D gd = (Graphics2D) g;
		// Permet d'afficher la carte sans les effets, ni les pions
		for (Case[] c : plateau.get()) {
			for (Case c1 : c) {
				gd.drawImage(Textures.getTerrain(c1.getTypeTerrain()), c1.getColonne() * Case.TAILLE, c1.getLigne() * Case.TAILLE, null);
				if (c1.getBordure() != null) {
					gd.drawImage(Textures.getBordure(c1.getBordure().getTypeBordure()), c1.getColonne() * Case.TAILLE, c1.getLigne() * Case.TAILLE, null);
				}
			}
		}
//		if (finDeDeplacement) {
//			gd.setColor(new Color(50, 200, 100, 200));
//			if (plateau.get(pionDeplace.getLigne() - 1, pionDeplace.getColonne()) != null) {
//				gd.fillRect(pionDeplace.getColonne() * Case.TAILLE, (pionDeplace.getLigne() - 1) * Case.TAILLE, Case.TAILLE, Case.TAILLE);
//			}
//			if (plateau.get(pionDeplace.getLigne() + 1, pionDeplace.getColonne()) != null) {
//				gd.fillRect(pionDeplace.getColonne() * Case.TAILLE, (pionDeplace.getLigne() + 1) * Case.TAILLE, Case.TAILLE, Case.TAILLE);
//			}
//			if (plateau.get(pionDeplace.getLigne(), pionDeplace.getColonne() - 1) != null) {
//				gd.fillRect((pionDeplace.getColonne() - 1) * Case.TAILLE, pionDeplace.getLigne() * Case.TAILLE, Case.TAILLE, Case.TAILLE);
//			}
//			if (plateau.get(pionDeplace.getLigne(), pionDeplace.getColonne() + 1) != null) {
//				gd.fillRect((pionDeplace.getColonne() + 1) * Case.TAILLE, pionDeplace.getLigne() * Case.TAILLE, Case.TAILLE, Case.TAILLE);
//			}
//		}

		// Construit la grille du plateau	
		gd.setColor(new Color(80, 80, 80, 40));
		for (int i = 0; i < getHeight(); i += Case.TAILLE) {
			gd.drawLine(0, i, getWidth(), i);
		}
		for (int j = 0; j < getWidth(); j += Case.TAILLE) {
			gd.drawLine(j, 0, j, getHeight());
		}
//		if (caseSurvolPion != null) {
//			construireSurvolPion(gd);
//		}


		if (!debutDePartie) {
			// Affiche les pions attaquable par le pion en caseEnCours, et affiche aussi le survol d'un pion attaquable
			if (attaqueEnCours) {
				afficherAttaquePossible(gd);
				construireSurvolAttaque(gd);
			}
			
			if(conqueteEnCours){
				afficherConquetePossible(gd);
				construireSurvolConquete(gd);
			}

			// Affiche la porte d'attaque du pion en caseEnCours
			if (afficherPorteeAttaque) {
				afficherPorteAttaque(gd);
			}

			if (afficherPorteeConquerir) {
				afficherPorteConquerir(gd);
			}
		}

		if (!attaqueEnCours && caseSurvol != null) {
			gd.setColor(new Color(255, 255, 50, 100));
			gd.fillRect(caseSurvol.getColonne() * Case.TAILLE, caseSurvol.getLigne() * Case.TAILLE, Case.TAILLE, Case.TAILLE);
		}

		// Permet d'afficher les pions	
		//TODO ne pas parcourir toutes les cases du plateau
		for (Case[] c : plateau.get()) {
			for (Case c1 : c) {
				// Affichage des obstacles
				if (c1.getObstacle() != null) {
					gd.drawImage(c1.getObstacle().getImage(), c1.getColonne() * Case.TAILLE, c1.getLigne() * Case.TAILLE, null);
				}
				// Affichage de la couleur des pions alliés
				if (c1.getPion() != null && !debutDePartie && c1.getPion().getJoueur().getBoolValue() == joueurCourant) {
					gd.setColor(new Color(0, 255, 0, 100));
					gd.fillRect(c1.getColonne() * Case.TAILLE, c1.getLigne() * Case.TAILLE, Case.TAILLE, Case.TAILLE);
				}
				// Affichage des téléporteurs
				if (c1.getTeleporteur() != null) {
					gd.drawImage(c1.getTeleporteur().getImage(), c1.getColonne() * Case.TAILLE, c1.getLigne() * Case.TAILLE, null);
				}
				// Affichage des pions
				if (c1.getPion() != null) {
					gd.drawImage(c1.getPion().getImage(), c1.getColonne() * Case.TAILLE, c1.getLigne() * Case.TAILLE, null);
				}
			}
		}

		if (!debutDePartie) {
			// Affiche le deplacement du pion en caseEnCours
			//FIXME l'affichage des déplacements masque les téléporteurs : les rendre moins opaques
			if (mouvementEnCours) {
				afficherMouvementPossible(gd);
			}

			//TODO a modifier
			if (afficherPoseTeleporteur) {
				gd.setColor(new Color(200, 0, 200, 150));
				if (plateau.get(caseEnCours.getLigne() - 1, caseEnCours.getColonne()) != null
						&& plateau.get(caseEnCours.getLigne() - 1, caseEnCours.getColonne()).getPion() == null
						&& plateau.get(caseEnCours.getLigne() - 1, caseEnCours.getColonne()).getObstacle() == null) {
					gd.fillRect(caseEnCours.getColonne() * Case.TAILLE, (caseEnCours.getLigne() - 1) * Case.TAILLE, Case.TAILLE, Case.TAILLE);
				}
				if (plateau.get(caseEnCours.getLigne() + 1, caseEnCours.getColonne()) != null
						&& plateau.get(caseEnCours.getLigne() + 1, caseEnCours.getColonne()).getPion() == null
						&& plateau.get(caseEnCours.getLigne() + 1, caseEnCours.getColonne()).getObstacle() == null) {
					gd.fillRect(caseEnCours.getColonne() * Case.TAILLE, (caseEnCours.getLigne() + 1) * Case.TAILLE, Case.TAILLE, Case.TAILLE);
				}

				if (plateau.get(caseEnCours.getLigne(), caseEnCours.getColonne() - 1) != null
						&& plateau.get(caseEnCours.getLigne(), caseEnCours.getColonne() - 1).getPion() == null
						&& plateau.get(caseEnCours.getLigne(), caseEnCours.getColonne() - 1).getObstacle() == null) {
					gd.fillRect((caseEnCours.getColonne() - 1) * Case.TAILLE, caseEnCours.getLigne() * Case.TAILLE, Case.TAILLE, Case.TAILLE);
				}
				if (plateau.get(caseEnCours.getLigne(), caseEnCours.getColonne() + 1) != null
						&& plateau.get(caseEnCours.getLigne(), caseEnCours.getColonne() + 1).getPion() == null
						&& plateau.get(caseEnCours.getLigne(), caseEnCours.getColonne() + 1).getObstacle() == null) {
					gd.fillRect((caseEnCours.getColonne() + 1) * Case.TAILLE, caseEnCours.getLigne() * Case.TAILLE, Case.TAILLE, Case.TAILLE);
				}
			}

			// Mise en valeur des téléporteurs communiquants
			//FIXME ne rien mettre en valeur si on est sur un téléporteur ennemi
			if (teleportationEnCours) {
				gd.setColor(new Color(200, 0, 200, 100));
				for (Teleporteur t : caseEnCours.getTeleporteur().getListeTeleporteur()) {
					// On ne met pas en valeur le téléporteur courant
					if (t != caseEnCours.getTeleporteur()) {
						gd.fillRect((t.getCase().getColonne()) * Case.TAILLE, t.getCase().getLigne() * Case.TAILLE, Case.TAILLE, Case.TAILLE);
					}
				}
			}
		}


		if (caseSurvol != null && caseSurvol.getPion() != null) {
			construireSurvolPion(gd);
		}

		if (debutDePartie) {
			if (!firstTime) {
				if (joueurCourant) {
					gd.clearRect(Case.TAILLE * (plateau.getNbColonne() - 6), 0, 6 * Case.TAILLE + 1, plateau.getNbLigne() * Case.TAILLE + 1);
				} else {
					gd.clearRect(0, 0, Case.TAILLE * 6, plateau.getNbLigne() * Case.TAILLE + 1);
				}
				if (listeCase != null) {
					for (Case c : listeCase) {
						gd.setColor(new Color(50, 255, 50, 100));
						gd.fillRect(c.getColonne() * Case.TAILLE, c.getLigne() * Case.TAILLE, Case.TAILLE, Case.TAILLE);
					}
				}
			} else {
				gd.clearRect(0, 0, Case.TAILLE * 6, plateau.getNbLigne() * Case.TAILLE + 1);
				gd.clearRect(Case.TAILLE * (plateau.getNbColonne() - 6), 0, 6 * Case.TAILLE + 1, plateau.getNbLigne() * Case.TAILLE + 1);
			}
		}

	}

//	Les methodes suivantes concernent l'affichage de l'attaque
	private void afficherPorteAttaque(Graphics2D gd) {
		int i = 0;
		for (Case c : caseEnCours.getPion().getListeAttaqueAire()) {
			gd.setColor(new Color(255, 0, 0, 150));
			gd.fillRect(c.getColonne() * Case.TAILLE, c.getLigne() * Case.TAILLE, Case.TAILLE, Case.TAILLE);
			i++;
		}
	}

	private void afficherPorteConquerir(Graphics2D gd) {
		int i = 0;
		for (Case c : caseEnCours.getPion().getListeConquetePossible()) {
			gd.setColor(new Color(255, 0, 255, 150));
			gd.fillRect(c.getColonne() * Case.TAILLE, c.getLigne() * Case.TAILLE, Case.TAILLE, Case.TAILLE);
			i++;
		}
	}

	private void afficherAttaquePossible(Graphics2D gd) {
		int i = 0;
		for (Case c : caseEnCours.getPion().getListeAttaquePossible()) {
			gd.setColor(new Color(255, 0, 0, 150));
			gd.fillRect(c.getColonne() * Case.TAILLE, c.getLigne() * Case.TAILLE, Case.TAILLE, Case.TAILLE);
			i++;
		}
	}
	private void afficherConquetePossible(Graphics2D gd) {
		int i = 0;
		for (Case c : caseEnCours.getPion().getListeConquetePossible()) {
			gd.setColor(new Color(255, 0, 255, 150));
			gd.fillRect(c.getColonne() * Case.TAILLE, c.getLigne() * Case.TAILLE, Case.TAILLE, Case.TAILLE);
			i++;
		}
	}

	private void construireSurvolAttaque(Graphics2D gd) {
		gd.setColor(new Color(255, 0, 0, 200));
		if (caseEnCours.getPion().getListeAttaquePossible().contains(caseSurvol)) {
			gd.fillRect(caseSurvol.getColonne() * Case.TAILLE, caseSurvol.getLigne() * Case.TAILLE, Case.TAILLE, Case.TAILLE);
		}
	}
	
	private void construireSurvolConquete(Graphics2D gd) {
		gd.setColor(new Color(255, 0, 255, 200));
		if (caseEnCours.getPion().getListeConquetePossible().contains(caseSurvol)) {
			gd.fillRect(caseSurvol.getColonne() * Case.TAILLE, caseSurvol.getLigne() * Case.TAILLE, Case.TAILLE, Case.TAILLE);
		}
	}

	public void suvolAfficherAttaque(Case c1) {
		caseSurvol = c1;
	}

	public void setAttaqueEnCours(boolean attaque) {
		this.attaqueEnCours = attaque;
	}

	public void setAfficherPorteeAttaque(boolean b, Case c) {
		afficherPorteeAttaque = b;
		caseEnCours = c;
	}

	public void setAfficherPorteeConquerir(boolean b, Case c) {
		afficherPorteeConquerir = b;
		caseEnCours = c;
	}

	public void survolPion(Case c1) {
		caseSurvol = c1;
	}

	private void construireSurvolPion(Graphics gd) {
		Case affVie;
		String affichageVie = caseSurvol.getPion().getVieRestante() + "pv";
		int vie = caseSurvol.getPion().getVieRestante();
		if (plateau.get(caseSurvol.getLigne() - 1, caseSurvol.getColonne()) == null) {
			affVie = plateau.get(caseSurvol.getLigne() + 1, caseSurvol.getColonne());
		} else if (plateau.get(caseSurvol.getLigne() + 1, caseSurvol.getColonne()) == null) {
			affVie = plateau.get(caseSurvol.getLigne() - 1, caseSurvol.getColonne());
		} else if (plateau.get(caseSurvol.getLigne(), caseSurvol.getColonne() + 1) == null) {
			affVie = plateau.get(caseSurvol.getLigne(), caseSurvol.getColonne() - 1);
		} else if (plateau.get(caseSurvol.getLigne(), caseSurvol.getColonne() - 1) == null) {
			affVie = plateau.get(caseSurvol.getLigne(), caseSurvol.getColonne() + 1);
		} else {
			affVie = plateau.get(caseSurvol.getLigne() - 1, caseSurvol.getColonne());
		}
		if (vie > 20) {
			gd.setColor(new Color(0, 255, 68, 200));
		} else if (vie > 10) {
			gd.setColor(new Color(255, 204, 68, 200));
		} else {
			gd.setColor(new Color(255, 34, 17, 200));
		}
		gd.fillRect(affVie.getColonne() * Case.TAILLE, affVie.getLigne() * Case.TAILLE, vie, 5);
		gd.setColor(Color.WHITE);
		gd.drawString(affichageVie, affVie.getColonne() * Case.TAILLE, affVie.getLigne() * Case.TAILLE + 20);
	}

	public void setCaseSurvol(Case c) {
		caseSurvol = c;
	}

//	Les methodes suivantes concernent l'affichage du mouvement
	private void afficherMouvementPossible(Graphics2D gd) {
		int i = 0;
		for (Noeud c2 : caseEnCours.getPion().listeDeplacementPossible) {
			gd.setColor(new Color(25, 150, 255, 255 /*- i * 2*/));
			gd.fillRect(c2.c.getColonne() * Case.TAILLE, c2.c.getLigne() * Case.TAILLE, Case.TAILLE, Case.TAILLE);
			i++;
		}
		for (Case c2 : caseEnCours.getPion().getDeplacement()) {
			gd.setColor(new Color(50, 50, 100, 200));
			gd.fillRect(c2.getColonne() * Case.TAILLE, c2.getLigne() * Case.TAILLE, Case.TAILLE, Case.TAILLE);
		}
		gd.drawImage(caseEnCours.getPion().getImage(), caseEnCours.getColonne() * Case.TAILLE, caseEnCours.getLigne() * Case.TAILLE, null);
	}

	public void afficherMouvement(boolean b, Case c1) {
		mouvementEnCours = b;
		caseEnCours = c1;
	}

	public void setDebutDePartie(boolean debutDePartie) {
		this.debutDePartie = debutDePartie;
	}

	public void setJoueurCourant(boolean joueurCourant) {
		this.joueurCourant = joueurCourant;
		firstTime = false;
	}

	public void setListeCase(ArrayList<Case> caseJoueur) {
		listeCase = caseJoueur;
	}

	public void setAfficherPoseTeleporteur(boolean b, Case c) {
		afficherPoseTeleporteur = b;
		caseEnCours = c;
	}

	public void afficherTeleporteurDisponible(boolean teleportationEnCours, Case c) {
		this.teleportationEnCours = teleportationEnCours;
		caseEnCours = c;

	}

	public final void nouvellePartie() {
		setDebutDePartie(true);
		firstTime = true;
		repaint();
	}

	public void setConqueteEnCours(boolean b) {
		conqueteEnCours = b;
	}

	public void suvolAfficherConquete(Case c1) {
		caseSurvol = c1;
	}
}
