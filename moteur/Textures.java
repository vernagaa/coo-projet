package moteur;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author disavinr
 */
public class Textures {

    private static final String ROCPATH = "/images/roc.png";
    private static final String EAUPATH = "/images/eau.png";
    private static final String ROUTEPATH = "/images/route.png";
    private static final String SABLEPATH = "/images/sable.png";
    private static final String HERBEPATH = "/images/herbe.png";
    public static final int ROC = 0;
    public static final int EAU = 1;
    public static final int ROUTE = 2;
    public static final int SABLE = 3;
    public static final int HERBE = 4;
    private BufferedImage[] terrain = new BufferedImage[5];
    private BufferedImage[][] perso = new BufferedImage[15][4];//TODO les initialiser
    private BufferedImage[] obstacle = new BufferedImage[2];//TODO les initialiser

    public Textures() {
        terrain[ROC] = new BufferedImage(Case.TAILLE, Case.TAILLE, BufferedImage.TYPE_INT_ARGB);
        terrain[EAU] = new BufferedImage(Case.TAILLE, Case.TAILLE, BufferedImage.TYPE_INT_ARGB);
        terrain[ROUTE] = new BufferedImage(Case.TAILLE, Case.TAILLE, BufferedImage.TYPE_INT_ARGB);
        terrain[SABLE] = new BufferedImage(Case.TAILLE, Case.TAILLE, BufferedImage.TYPE_INT_ARGB);
        terrain[HERBE] = new BufferedImage(Case.TAILLE, Case.TAILLE, BufferedImage.TYPE_INT_ARGB);

        try {
            terrain[ROC] = ImageIO.read(getClass().getResource(ROCPATH));
        } catch (IOException ex) {
            System.err.println("Image non trouvée : " + ROCPATH);
        }

        try {
            terrain[EAU] = ImageIO.read(getClass().getResource(EAUPATH));
        } catch (IOException ex) {
            System.err.println("Image non trouvée : " + EAUPATH);
        }

        try {
            terrain[ROUTE] = ImageIO.read(getClass().getResource(ROUTEPATH));
        } catch (IOException ex) {
            System.err.println("Image non trouvée : " + ROUTEPATH);
        }

        try {
            terrain[SABLE] = ImageIO.read(getClass().getResource(SABLEPATH));
        } catch (IOException ex) {
            System.err.println("Image non trouvée : " + SABLEPATH);
        }

        try {
            terrain[HERBE] = ImageIO.read(getClass().getResource(HERBEPATH));
        } catch (IOException ex) {
            System.err.println("Image non trouvée : " + HERBEPATH);
        }
    }

    public BufferedImage getTerrain(int numTerrain) {
        return terrain[numTerrain];
    }

    public BufferedImage getPersonnage(int numPerso, Orientation orientation) {
        switch (orientation) {
            case NORD:
                return perso[numPerso][0];
            case SUD:
                return perso[numPerso][1];
            case EST:
                return perso[numPerso][2];
            case OUEST:
                return perso[numPerso][3];
            default:
                return null;
        }
    }

    public BufferedImage getObstacle(int numObstacle) {
        return obstacle[numObstacle];
    }
}
