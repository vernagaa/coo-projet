package ihm;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;
import moteur.Case;
import moteur.Plateau;
import moteur.Textures;

/**
 *
 * @author disavinr
 */
public class AireDeJeu extends JComponent {

    private Plateau plateau;

    public AireDeJeu(Plateau plateau) {
        setPreferredSize(new Dimension(plateau.getNbColonne() * Case.TAILLE + 1, plateau.getNbLigne() * Case.TAILLE + 1));
        this.plateau = plateau;
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
                    if (!c1.getPion().listeDeplacement.isEmpty()) {
                        int i = 0;
                        for (Case c2 : c1.getPion().listeDeplacement) {
                            gd.setColor(new Color(25, 150, 255, 100-i));
                            gd.fillRect(c2.getColonne() * Case.TAILLE,c2.getLigne() * Case.TAILLE, Case.TAILLE, Case.TAILLE);
                            i++;
                        }
                    }
                }

            }
        }


        gd.setColor(new Color(80, 80, 80, 40));

        for (int i = 0; i < getHeight(); i += Case.TAILLE) {
            gd.drawLine(0, i, getWidth(), i);
        }
        for (int j = 0; j < getWidth(); j += Case.TAILLE) {
            gd.drawLine(j, 0, j, getHeight());
        }


    }
}
