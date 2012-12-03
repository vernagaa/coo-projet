package ihm;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;
import moteur.Case;
import moteur.Noeud;
import moteur.Plateau;
import moteur.Textures;

/**
 *
 * @author disavinr
 */
public class AireDeJeu extends JComponent {

	private Plateau plateau;
	private boolean attaqueEnCours;
	private boolean afficherPorteeAttaque;
	private boolean mouvementEnCours;
	private String affichageVie;
	private Case caseEnCours;
	public Case caseSurvol;
	private boolean debutDePartie;
	private boolean joueurCourant;

	public AireDeJeu(Plateau plateau) {
		setPreferredSize(new Dimension(plateau.getNbColonne() * Case.TAILLE + 1, plateau.getNbLigne() * Case.TAILLE + 1));
		this.plateau = plateau;
		debutDePartie = true;
		joueurCourant = true;
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
				if (c1.getObstacle() != null) {
					gd.drawImage(Textures.getObstacle(c1.getObstacle().getTypeObstacle()), c1.getColonne() * Case.TAILLE, c1.getLigne() * Case.TAILLE, null);
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

			// Affiche la porte d'attaque du pion en caseEnCours
			if (afficherPorteeAttaque) {
				afficherPorteAttaque(gd);
			}

			// Affiche le deplacement du pion en caseEnCours
			if (mouvementEnCours) {
				afficherMouvementPossible(gd);
			}

			if (!attaqueEnCours && caseSurvol != null) {
				gd.setColor(new Color(255, 255, 50, 100));
				gd.fillRect(caseSurvol.getColonne() * Case.TAILLE, caseSurvol.getLigne() * Case.TAILLE, Case.TAILLE, Case.TAILLE);
			}
		} 

		// Permet d'afficher les pions		
		for (Case[] c : plateau.get()) {
			for (Case c1 : c) {
				if (c1.getPion() != null) {
					gd.drawImage(c1.getPion().getImage(), c1.getColonne() * Case.TAILLE, c1.getLigne() * Case.TAILLE, null);
				}
			}
		}
		
		if(debutDePartie){
			if(joueurCourant){
				gd.setColor(new Color(50,50,50,200));
				gd.fillRect(0, 0, Case.TAILLE * 6, plateau.getNbColonne() * Case.TAILLE);
			}
		}

		if (caseSurvol != null && caseSurvol.getPion() != null) {
			construireSurvolPion(gd);
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

	private void afficherAttaquePossible(Graphics2D gd) {
		int i = 0;
		for (Case c : caseEnCours.getPion().getListeAttaquePossible()) {
			gd.setColor(new Color(255, 0, 0, 150));
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

	public void survolPion(Case c1) {
		caseSurvol = c1;
	}

	private void construireSurvolPion(Graphics gd) {
		Case affVie;
		affichageVie = caseSurvol.getPion().getVieRestante();
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
		gd.setColor(new Color(0, 255, 0, 200));
		gd.fillRect(affVie.getColonne() * Case.TAILLE, affVie.getLigne() * Case.TAILLE, Case.TAILLE, Case.TAILLE);
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
			gd.setColor(new Color(25, 150, 255, 255 - i * 3));
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
	}
}
