/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ihm;

import ecouteur.EcouteurPlateau;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.Timer;
import moteur.*;

/**
 *
 * @author Kévin
 */
public class Animation {

	private AireDAnimation aire;
	private Moteur m;
	private boolean animationEnCours;
	private Timer timer;
	private EcouteurPlateau ecouteur;

	public Animation(AireDAnimation aire, Moteur m, EcouteurPlateau ecouteur) {
		this.aire = aire;
		animationEnCours = false;
		this.ecouteur = ecouteur;
		this.m = m;
	}

	public void animerFinDeTour() {
		if (!animationEnCours) {
			animationEnCours = true;
			ecouteur.desactiverEcouteur();
			aire.animationFinDeTour = true;
			timer = new Timer(42, new ActionListener() {

				int compteurInterne = 0;

				@Override
				public void actionPerformed(ActionEvent e) {
					if (compteurInterne == 0 && aire.compteurFinDeTour < 12) {
						aire.compteurFinDeTour++;
					} else if (compteurInterne < 24 && aire.compteurFinDeTour == 12) {
						compteurInterne++;
					} else if (compteurInterne == 24 && aire.compteurFinDeTour > 0) {
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

				@Override
				public void actionPerformed(ActionEvent e) {
					if (compteurInterne < 36) {
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
						}
					} else if (compteurInterne < 72) {
						if (compteurInterne == 36) {
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
						}
					}
					aire.repaint();
				}
			});
			timer.start();
		}
	}

	public void animerMouvement(final Pion p) throws IOException {
		if (!animationEnCours) {
			animationEnCours = true;
			ecouteur.desactiverEcouteur();
			aire.animationDeplacement = true;
			aire.position.x = p.getCase().getColonne() * 30;
			aire.position.y = p.getCase().getLigne() * 30;
			timer = new Timer(42, new ActionListener() {

				int deplacementCase = 15;
				int deplacementEnCours = 0;
				int caseEnCours = 0;
				Case caseTemp;
				Case caseSuiv;
				BufferedImage imageTotale = ImageIO.read(getClass().getResource(Textures.ASSASSINOISEAUPATH));
				int compteurImage = 0;
				int idPion = 2;
				boolean initialisation = true;
				int orientation;
				int compteurMouvement = 0;
				int pixelDeplacement = 2;

				@Override
				public void actionPerformed(ActionEvent e) {
//					System.out.println("Deplacement " + p.getDeplacement().size() + " " + caseEnCours);
//					System.out.println(p.getDeplacement());
					if (p.getDeplacement().size() - 1 > caseEnCours) {
//						System.out.println(deplacementEnCours + " " + deplacementCase);
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
//									orientation = Orientation.EST;
								} else if (caseSuiv.getColonne() - caseTemp.getColonne() < 0) {
									orientation = 1;
//									orientation = Orientation.OUEST;
								} else if (caseSuiv.getLigne() - caseTemp.getLigne() > 0) {
									orientation = 0;
									//orientation = Orientation.SUD;
								} else if (caseSuiv.getLigne() - caseTemp.getLigne() < 0) {
									orientation = 3;
//									orientation = Orientation.NORD;
								}
							}
						} else {
							aire.imageEnCours = imageTotale.getSubimage(32 * (compteurMouvement % 3) + 96 * p.getId().x, 32 * orientation + 128 * p.getId().y, 32, 32);
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
							if (deplacementEnCours % 2 == 0) {
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
						p.deplacerPion(m.caseCourante);
						m.utiliserAction();
						timer.stop();
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
}
