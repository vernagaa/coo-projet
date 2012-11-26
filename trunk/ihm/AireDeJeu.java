package ihm;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
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
    public Point pointFinDeTour;
    public Point survolFinDeTour;
    public boolean afficherFinDeTour;
    public Point pointAfficherChoix;
    public Point survolAfficherChoix;
    public boolean afficherChoix;
    public Case pionDeplace;
    public boolean finDeDeplacement;

    public AireDeJeu(Plateau plateau) {
        setPreferredSize(new Dimension(plateau.getNbColonne() * Case.TAILLE + 1, plateau.getNbLigne() * Case.TAILLE + 1));
        this.plateau = plateau;
        pointFinDeTour = new Point();
        afficherFinDeTour = false;
        pointAfficherChoix = new Point();
        survolAfficherChoix = new Point();
        afficherChoix = false;
        finDeDeplacement = false;
        pionDeplace = null;
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

        for (Case[] c : plateau.get()) {
            for (Case c1 : c) {
                gd.drawImage(Textures.getTerrain(c1.getTypeTerrain()), c1.getColonne() * Case.TAILLE, c1.getLigne() * Case.TAILLE, null);
                if (c1.getBordure() != null) {
                    gd.drawImage(Textures.getBordure(c1.getBordure().getTypeBordure()), c1.getColonne() * Case.TAILLE, c1.getLigne() * Case.TAILLE, null);
                }
                if (c1.getObstacle() != null) {
                    gd.drawImage(Textures.getObstacle(c1.getObstacle().getTypeObstacle()), c1.getColonne() * Case.TAILLE, c1.getLigne() * Case.TAILLE, null);
                }
                if (c1.getPion() != null) {
                    gd.drawImage(c1.getPion().getImage(), c1.getColonne() * Case.TAILLE, c1.getLigne() * Case.TAILLE, null);
                }
            }
        }

        for (Case[] c : plateau.get()) {
            for (Case c1 : c) {
                if (c1.getPion() != null) {
                    if (!c1.getPion().listeDeplacementPossible.isEmpty() && c1.getSelect()) {
                        int i = 0;
                        for (Noeud c2 : c1.getPion().listeDeplacementPossible) {
                            gd.setColor(new Color(25, 150, 255, 255 - i * 3));
                            gd.fillRect(c2.c.getColonne() * Case.TAILLE, c2.c.getLigne() * Case.TAILLE, Case.TAILLE, Case.TAILLE);
                            i++;
                        }
                        for (Case c2 : c1.getPion().listeAttaquePossible) {
                            gd.setColor(new Color(255, 100, 0, 255));
                            gd.fillRect(c2.getColonne() * Case.TAILLE, c2.getLigne() * Case.TAILLE, Case.TAILLE, Case.TAILLE);
                            i++;
                        }
                        for (Case c2 : c1.getPion().getDeplacement()) {
                            gd.setColor(new Color(50, 50, 100, 200));
                            gd.fillRect(c2.getColonne() * Case.TAILLE, c2.getLigne() * Case.TAILLE, Case.TAILLE, Case.TAILLE);
                        }
                    }
                    gd.drawImage(c1.getPion().getImage(), c1.getColonne() * Case.TAILLE, c1.getLigne() * Case.TAILLE, null);
                }

            }
        }

        if (finDeDeplacement) {
            gd.setColor(new Color(50, 200, 100, 200));
            if (plateau.get(pionDeplace.getLigne() - 1, pionDeplace.getColonne()) != null) {
                gd.fillRect(pionDeplace.getColonne() * Case.TAILLE, (pionDeplace.getLigne() - 1) * Case.TAILLE, Case.TAILLE, Case.TAILLE);
            }
            if (plateau.get(pionDeplace.getLigne() + 1, pionDeplace.getColonne()) != null) {
                gd.fillRect(pionDeplace.getColonne() * Case.TAILLE, (pionDeplace.getLigne() + 1) * Case.TAILLE, Case.TAILLE, Case.TAILLE);
            }
            if (plateau.get(pionDeplace.getLigne(), pionDeplace.getColonne() - 1) != null) {
                gd.fillRect((pionDeplace.getColonne() - 1) * Case.TAILLE, pionDeplace.getLigne() * Case.TAILLE, Case.TAILLE, Case.TAILLE);
            }
            if (plateau.get(pionDeplace.getLigne(), pionDeplace.getColonne() + 1) != null) {
                gd.fillRect((pionDeplace.getColonne() + 1) * Case.TAILLE, pionDeplace.getLigne() * Case.TAILLE, Case.TAILLE, Case.TAILLE);
            }
        }


        gd.setColor(new Color(80, 80, 80, 40));

        for (int i = 0; i < getHeight(); i += Case.TAILLE) {
            gd.drawLine(0, i, getWidth(), i);
        }
        for (int j = 0; j < getWidth(); j += Case.TAILLE) {
            gd.drawLine(j, 0, j, getHeight());
        }

        if (afficherFinDeTour) {
            gd.setColor(new Color(80, 80, 80, 200));
            gd.fillRect(pointFinDeTour.x, pointFinDeTour.y, 3 * Case.TAILLE, Case.TAILLE);
            gd.setColor(Color.WHITE);
            gd.drawString("Fin du tour", pointFinDeTour.x + 10, pointFinDeTour.y + 20);
        }
        if (afficherChoix) {
            gd.setColor(new Color(80, 80, 80, 200));
            gd.fillRect(pointAfficherChoix.x, pointAfficherChoix.y, 3 * Case.TAILLE, 2 * Case.TAILLE);
            if (contientCoordonnees(survolAfficherChoix, pointAfficherChoix, 3 * Case.TAILLE, Case.TAILLE)) {
                gd.setColor(new Color(170, 170, 170, 200));
                gd.fillRect(pointAfficherChoix.x, pointAfficherChoix.y, 3 * Case.TAILLE, Case.TAILLE);
            } else if (contientCoordonnees(survolAfficherChoix, new Point(pointAfficherChoix.x, pointAfficherChoix.y + Case.TAILLE),
                    3 * Case.TAILLE, 2 * Case.TAILLE)) {
                gd.setColor(new Color(170, 170, 170, 200));
                gd.fillRect(pointAfficherChoix.x, pointAfficherChoix.y + Case.TAILLE, 3 * Case.TAILLE, Case.TAILLE);
            }
            gd.setColor(Color.WHITE);
            gd.drawString("Attaquer", pointAfficherChoix.x + 10, pointAfficherChoix.y + 20);
            gd.drawString("Capacité", pointAfficherChoix.x + 10, pointAfficherChoix.y + 20 + Case.TAILLE);
        }

    }

    public boolean contientCoordonnees(Point survol, Point rect, int ligne, int colonne) {
        return survol.x > rect.x && survol.y > rect.y && survol.x < rect.x + ligne && survol.y < rect.y + colonne;
    }
}
