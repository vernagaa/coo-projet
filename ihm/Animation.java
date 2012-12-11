package ihm;

import ecouteur.EcouteurPlateau;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.Timer;
import moteur.*;

/**
 *
 * @author KÃ©vin
 */
public class Animation {

	private AireDAnimation aire;
	private Moteur m;
	private boolean animationEnCours;
	private Timer timer;
	private EcouteurPlateau ecouteur;
	private ArrayList<Integer> listeAnimation;
	private Integer finDeTour;
	private Integer finDePartie;
	private Integer elireCommandant;
	int i = 0;

	public Animation(AireDAnimation aire, Moteur m, EcouteurPlateau ecouteur) {
		this.aire = aire;
		animationEnCours = false;
		listeAnimation = new ArrayList<Integer>();
		this.ecouteur = ecouteur;
		this.m = m;
		finDeTour = new Integer(0);
		finDePartie = new Integer(1);
		elireCommandant = new Integer(2);
	}

	public void animerFinDeTour() {
		if (!animationEnCours) {
			animationEnCours = true;
			System.out.println(animationEnCours);
			ecouteur.desactiverEcouteur();
			aire.animationFinDeTour = true;
			timer = new Timer(42, new ActionListener() {

				int compteurInterne = 0;
				int temps = 12;

				@Override
				public void actionPerformed(ActionEvent e) {
					if (compteurInterne == 0 && aire.compteurFinDeTour < temps / 2) {
						aire.compteurFinDeTour++;
					} else if (compteurInterne < temps && aire.compteurFinDeTour == temps / 2) {
						compteurInterne++;
					} else if (compteurInterne == temps && aire.compteurFinDeTour > 0) {
						aire.compteurFinDeTour--;
					} else {
						aire.compteurFinDeTour = 0;
						ecouteur.activerEcouteur();
						animationEnCours = false;
						aire.animationFinDeTour = false;
						timer.stop();
					}
					aire.repaint();
				}
			});
			timer.start();
		} else {
			if (!listeAnimation.contains(finDeTour)) {
				listeAnimation.add(finDeTour);
			}
		}
	}

	public void animerAttaquePion(final Pion pionA, final Pion pionD) {
		if (!animationEnCours) {
			animationEnCours = true;
			ecouteur.desactiverEcouteur();
			aire.animationAttaque = true;
			aire.attaquant = true;
			aire.esquive = true;
			System.out.println(pionA.degatsCombat);
			System.out.println(pionD.degatsCombat);
			System.out.println(aire.positionD);
			aire.positionA = new Point(pionA.getCase().getColonne(), pionA.getCase().getLigne());
			aire.positionD = new Point(pionD.getCase().getColonne(), pionD.getCase().getLigne());
			timer = new Timer(42, new ActionListener() {

				int compteurInterne = 0;
				int tourAttaque = 0;
				int temps = 72;

				@Override
				public void actionPerformed(ActionEvent e) {
					if (compteurInterne < temps / 2) {
						if (!pionA.degatsCombat.isEmpty()) {
							aire.esquive = false;
							aire.attaquant = true;
							aire.val = pionA.degatsCombat.get(0);
							compteurInterne++;
						} else {
							ecouteur.activerEcouteur();
							animationEnCours = false;
							aire.animationAttaque = false;
							timer.stop();
							lancerAnimation();
						}
					} else if (compteurInterne < temps) {
						if (compteurInterne == temps / 2) {
							aire.attaquant = false;
							if (!pionA.degatsCombat.isEmpty()) {
								pionA.degatsCombat.remove(0);
							}
						}
						if (!pionD.degatsCombat.isEmpty()) {
							aire.val = pionD.degatsCombat.get(0);
							compteurInterne++;
						} else {
							ecouteur.activerEcouteur();
							animationEnCours = false;
							aire.animationAttaque = false;
							timer.stop();
							lancerAnimation();

						}
					} else {
						if (!pionD.degatsCombat.isEmpty()) {
							pionD.degatsCombat.remove(0);
						}
						compteurInterne = 0;
						if (pionA.degatsCombat.isEmpty()) {
							ecouteur.activerEcouteur();
							animationEnCours = false;
							aire.animationAttaque = false;
							timer.stop();
							lancerAnimation();
						}
					}
					aire.repaint();
				}
			});
			timer.start();
		}
	}

	public void animerMouvement(final Pion p) {
		if (!animationEnCours) {
			animationEnCours = true;
			ecouteur.desactiverEcouteur();
			aire.animationDeplacement = true;
			aire.position.x = p.getCase().getColonne() * 30;
			aire.position.y = p.getCase().getLigne() * 30;
			aire.imageEnCours = p.getImage(1);
			timer = new Timer(42, new ActionListener() {

				int deplacementCase = 6;
				int deplacementEnCours = 0;
				int caseEnCours = 0;
				Case caseTemp;
				Case caseSuiv;
				int compteurImage = 0;
				int idPion = 2;
				boolean initialisation = true;
				int orientation;
				int compteurMouvement = 0;
				int pixelDeplacement = 5;

				@Override
				public void actionPerformed(ActionEvent e) {
					if (p.getDeplacement().size() - 1 > caseEnCours) {
						if (initialisation) {
							caseTemp = p.getDeplacement().get(caseEnCours);
							if (p.getDeplacement().size() > caseEnCours + 1) {
								caseSuiv = p.getDeplacement().get(caseEnCours + 1);
							} else {
								caseSuiv = caseTemp;
							}
							initialisation = false;
							if (caseSuiv != caseTemp) {
								if (caseSuiv.getColonne() - caseTemp.getColonne() > 0) {
									orientation = 2;
									p.setOrientation(Orientation.EST);
								} else if (caseSuiv.getColonne() - caseTemp.getColonne() < 0) {
									orientation = 1;
									p.setOrientation(Orientation.OUEST);
								} else if (caseSuiv.getLigne() - caseTemp.getLigne() > 0) {
									orientation = 0;
									p.setOrientation(Orientation.SUD);
								} else if (caseSuiv.getLigne() - caseTemp.getLigne() < 0) {
									orientation = 3;
									p.setOrientation(Orientation.NORD);
								}
							}
						} else {
							aire.imageEnCours = p.getImage(compteurMouvement % 3);
							switch (orientation) {
								case (0):
									aire.position.y += pixelDeplacement;
									break;
								case (1):
									aire.position.x -= pixelDeplacement;
									break;
								case (2):
									aire.position.x += pixelDeplacement;
									break;
								case (3):
									aire.position.y -= pixelDeplacement;
									break;
							}
							deplacementEnCours++;
							if (deplacementEnCours % 3 == 0) {
								compteurMouvement++;
							}
							if (deplacementEnCours == deplacementCase) {
								deplacementEnCours = 0;
								caseEnCours++;
								initialisation = true;
							}
						}
					} else {
						ecouteur.activerEcouteur();
						animationEnCours = false;
						aire.animationDeplacement = false;
						p.deplacerPion(m.getCaseCourante());
						timer.stop();
						m.utiliserAction();
//						lancerAnimation();
					}
					aire.repaint();
				}
			});
			timer.start();
		}
	}

	public boolean animationPossible() {
		return !animationEnCours;
	}

	public void animerFinDePartie() {
		if (!animationEnCours) {
			animationEnCours = true;
			ecouteur.desactiverEcouteur();
			aire.animationFin = true;
			timer = new Timer(42, new ActionListener() {

				int compteurInterne = 0;

				@Override
				public void actionPerformed(ActionEvent e) {
					if (compteurInterne < 60) {
						compteurInterne++;
					} else {
						ecouteur.activerEcouteur();
						animationEnCours = false;
						aire.animationFin = false;
						timer.stop();
						m.nouvellePartie();
						listeAnimation.clear();
					}
					aire.repaint();
				}
			});
			timer.start();
		} else {
			if (!listeAnimation.contains(finDeTour)) {
				listeAnimation.add(finDeTour);
			}
		}
	}

	public void animerElireCommandant() {
		if (!animationEnCours) {
			animationEnCours = true;
			ecouteur.desactiverEcouteur();
			aire.animationElire = true;
			System.out.println("J'anime");
			timer = new Timer(42, new ActionListener() {

				int compteurInterne = 0;

				@Override
				public void actionPerformed(ActionEvent e) {
					if (compteurInterne < 40) {
						compteurInterne++;
						aire.compteurFinDeTour++;
					} else {
						ecouteur.activerEcouteur();
						animationEnCours = false;
						aire.animationElire = false;
						aire.compteurFinDeTour = 0;
						m.getAireDeJeu().elireUnCommandant(m.getJoueurElireCommandant(), true);
						m.getAireDeJeu().repaint();
						timer.stop();
						lancerAnimation();
					}
					aire.repaint();
				}
			});
			timer.start();
		} else {
			if (!listeAnimation.contains(elireCommandant)) {
				listeAnimation.add(elireCommandant);
			}
		}
	}

	private void lancerAnimation() {
		if (!listeAnimation.isEmpty()) {
			switch (listeAnimation.get(0)) {
				case 0:
					listeAnimation.remove(0);
					animerFinDeTour();
					break;
				case 1:
					listeAnimation.remove(0);
					animerFinDePartie();
					break;
				case 2:
					listeAnimation.remove(0);
					animerElireCommandant();
					break;
			}
		}
	}
}
