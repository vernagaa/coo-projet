package moteur;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author disavinr
 */
public class Textures {
	/*
	 * ------ Tile Eau nb : 16 dont 3 bordures ------ Ce sont des obstacles
	 * indestructible
	 */
	// Les obstacle indestructibles

	private static final String EAUPATH = "/images/eau.png";
	private static final String EAUDROITPATH = "/images/eauDroit.png";
	private static final String EAUFONDBASPATH = "/images/eauFondBas.png";
	private static final String EAUFONDDROITPATH = "/images/eauFondDroit.png";
	private static final String EAUFONDGAUCHEPATH = "/images/eauFondGauche.png";
	private static final String EAUGAUCHEPATH = "/images/eauGauche.png";
	private static final String EAUHAUTPATH = "/images/eauHaut.png";
	private static final String EAUHAUTDROITPATH = "/images/eauHautDroit.png";
	private static final String EAUHAUTGAUCHEPATH = "/images/eauHautGauche.png";
	private static final String EAUVIRAGEBASDROITPATH = "/images/eauVirageBasDroit.png";
	private static final String EAUVIRAGEBASGAUCHEPATH = "/images/eauVirageBasGauche.png";
	private static final String EAUVIRAGEHAUTDROITPATH = "/images/eauVirageHautDroit.png";
	private static final String EAUVIRAGEHAUTGAUCHEPATH = "/images/eauVirageHautGauche.png";
	public static final int EAU = 0;
	public static final int EAUDROIT = 1;
	public static final int EAUFONDBAS = 2;
	public static final int EAUFONDDROIT = 3;
	public static final int EAUFONDGAUCHE = 4;
	public static final int EAUGAUCHE = 5;
	public static final int EAUHAUT = 6;
	public static final int EAUHAUTDROIT = 7;
	public static final int EAUHAUTGAUCHE = 8;
	public static final int EAUVIRAGEBASDROIT = 9;
	public static final int EAUVIRAGEBASGAUCHE = 10;
	public static final int EAUVIRAGEHAUTDROIT = 11;
	public static final int EAUVIRAGEHAUTGAUCHE = 12;
	//Bordures
	private static final String BORDUREEAUBASPATH = "/images/bordureEauBas.png";
	private static final String BORDUREEAUDROITPATH = "/images/bordureEauDroit.png";
	private static final String BORDUREEAUGAUCHEPATH = "/images/bordureEauGauche.png";
	public static final int BORDUREEAUBAS = 0;
	public static final int BORDUREEAUDROIT = 1;
	public static final int BORDUREEAUGAUCHE = 2;

	/*
	 * ------ Tile Montagne nb : 9 ------ Ce sont des obstacles indestructibles
	 */
	public static final String MONTAGNEHAUTDROITPATH = "/images/montagneHautDroite.png";
	public static final String MONTAGNEHAUTPATH = "/images/montagneHaut.png";
	public static final String MONTAGNEHAUTGAUCHEPATH = "/images/montagneHautGauche.png";
	public static final String MONTAGNECENTREDROITPATH = "/images/montagneCentreDroit.png";
	public static final String MONTAGNECENTREPATH = "/images/montagneCentre.png";
	public static final String MONTAGNECENTREGAUCHEPATH = "/images/montagneCentreGauche.png";
	public static final String MONTAGNEBASGAUCHEPATH = "/images/montagneBasDroit.png";
	public static final String MONTAGNEBASPATH = "/images/montagneBas.png";
	public static final String MONTAGNEBASDROITPATH = "/images/montagneBasGauche.png";
	public static final int MONTAGNEHAUTDROIT = 13;
	public static final int MONTAGNEHAUT = 14;
	public static final int MONTAGNEHAUTGAUCHE = 15;
	public static final int MONTAGNECENTREDROIT = 16;
	public static final int MONTAGNECENTRE = 17;
	public static final int MONTAGNECENTREGAUCHE = 18;
	public static final int MONTAGNEBASGAUCHE = 19;
	public static final int MONTAGNEBAS = 20;
	public static final int MONTAGNEBASDROIT = 21;
	/*
	 * Tile Bordure Sable : 12
	 */
	public static final String BORDURESABLEBASPATH = "/images/bordureSableBas.png";
	public static final String BORDURESABLEBASDROITPATH = "/images/bordureSableBasDroit.png";
	public static final String BORDURESABLEBASGAUCHEPATH = "/images/bordureSableBasGauche.png";
	public static final String BORDURESABLEDROITPATH = "/images/bordureSableDroit.png";
	public static final String BORDURESABLEGAUCHEPATH = "/images/bordureSableGauche.png";
	public static final String BORDURESABLEHAUTPATH = "/images/bordureSableHaut.png";
	public static final String BORDURESABLEHAUTDROITPATH = "/images/bordureSableHautDroit.png";
	public static final String BORDURESABLEHAUTGAUCHEPATH = "/images/bordureSableHautGauche.png";
	public static final String BORDURESABLECOINBASDROITPATH = "/images/bordureSableCoinBasDroit.png";
	public static final int BORDURESABLEBAS = 3;
	public static final int BORDURESABLEBASDROIT = 4;
	public static final int BORDURESABLEBASGAUCHE = 5;
	public static final int BORDURESABLEDROIT = 6;
	public static final int BORDURESABLEGAUCHE = 7;
	public static final int BORDURESABLEHAUT = 8;
	public static final int BORDURESABLEHAUTDROIT = 9;
	public static final int BORDURESABLEHAUTGAUCHE = 10;
	public static final int BORDURESABLECOINBASDROIT = 11;
	/*
	 * Terrain
	 */
	private static final String ROCPATH = "/images/roc.png";
	private static final String ROUTEPATH = "/images/route.png";
	private static final String SABLECENTREPATH = "/images/sableCentre.png";
	private static final String HERBECENTREPATH = "/images/herbe.png";
	
	public static final int ROC = 0;
	public static final int ROUTE = 1;
	public static final int SABLECENTRE = 2;
	public static final int HERBECENTRE = 3;
	
	private BufferedImage[] terrain = new BufferedImage[4];
	private BufferedImage[] bordureTerrain = new BufferedImage[12];
	private BufferedImage[][] perso = new BufferedImage[15][4];//TODO les initialiser
	private BufferedImage[] obstacle = new BufferedImage[22];//TODO les initialiser

	public Textures() {
		for (int i = 0; i < terrain.length; i++) {
			terrain[i] = new BufferedImage(Case.TAILLE, Case.TAILLE, BufferedImage.TYPE_INT_ARGB);
		}
		for (int i = 0; i < bordureTerrain.length; i++) {
			bordureTerrain[i] = new BufferedImage(Case.TAILLE, Case.TAILLE, BufferedImage.TYPE_INT_ARGB);
		}
		for (int i = 0; i < perso.length; i++) {
			for (int j = 0; j < perso[i].length; j++) {
				perso[i][j] = new BufferedImage(Case.TAILLE, Case.TAILLE, BufferedImage.TYPE_INT_ARGB);
			}
		}
		for (int i = 0; i < obstacle.length; i++) {
			obstacle[i] = new BufferedImage(Case.TAILLE, Case.TAILLE, BufferedImage.TYPE_INT_ARGB);
		}



		try {
			terrain[ROC] = ImageIO.read(getClass().getResource(ROCPATH));
		} catch (IOException ex) {
			System.err.println("Image non trouvée : " + ROCPATH);
		}
		try {
			terrain[ROUTE] = ImageIO.read(getClass().getResource(ROUTEPATH));
		} catch (IOException ex) {
			System.err.println("Image non trouvée : " + ROUTEPATH);
		}

		try {
			terrain[HERBECENTRE] = ImageIO.read(getClass().getResource(HERBECENTREPATH));
		} catch (IOException ex) {
			System.err.println("Image non trouvée : " + HERBECENTREPATH);
		}

		tileEau();
		tileMontagne();
		tileSable();

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

	public BufferedImage getTerrain(int numTerrain) {
		return terrain[numTerrain];
	}

	public BufferedImage getObstacle(int numObstacle) {
		return obstacle[numObstacle];
	}

	public BufferedImage getBordure(int numBordure) {
		return bordureTerrain[numBordure];
	}

	/*
	 * Certaines de ces images qui servent a construire les plans d'eau sont
	 * consideres comme de type HERBE et non EAU. Le type EAU est
	 * infranchissable, contrairement au type HERBE
	 */
	public void tileEau() {

		try {
			bordureTerrain[BORDUREEAUBAS] = ImageIO.read(getClass().getResource(BORDUREEAUBASPATH));
		} catch (IOException ex) {
			System.err.println("Image non trouvée : " + BORDUREEAUBASPATH);
		}
		try {
			bordureTerrain[BORDUREEAUGAUCHE] = ImageIO.read(getClass().getResource(BORDUREEAUGAUCHEPATH));
		} catch (IOException ex) {
			System.err.println("Image non trouvée : " + BORDUREEAUGAUCHEPATH);
		}
		try {
			bordureTerrain[BORDUREEAUDROIT] = ImageIO.read(getClass().getResource(BORDUREEAUDROITPATH));
		} catch (IOException ex) {
			System.err.println("Image non trouvée : " + BORDUREEAUDROITPATH);
		}

		try {
			obstacle[EAUVIRAGEHAUTGAUCHE] = ImageIO.read(getClass().getResource(EAUVIRAGEHAUTGAUCHEPATH));
		} catch (IOException ex) {
			System.err.println("Image non trouvée : " + EAUVIRAGEHAUTGAUCHEPATH);
		}
		try {
			obstacle[EAUVIRAGEHAUTDROIT] = ImageIO.read(getClass().getResource(EAUVIRAGEHAUTDROITPATH));
		} catch (IOException ex) {
			System.err.println("Image non trouvée : " + EAUVIRAGEHAUTDROITPATH);
		}
		try {
			obstacle[EAUVIRAGEBASGAUCHE] = ImageIO.read(getClass().getResource(EAUVIRAGEBASGAUCHEPATH));
		} catch (IOException ex) {
			System.err.println("Image non trouvée : " + EAUVIRAGEBASGAUCHEPATH);
		}
		try {
			obstacle[EAUVIRAGEBASDROIT] = ImageIO.read(getClass().getResource(EAUVIRAGEBASDROITPATH));
		} catch (IOException ex) {
			System.err.println("Image non trouvée : " + EAUVIRAGEBASDROITPATH);
		}
		try {
			obstacle[EAUHAUTGAUCHE] = ImageIO.read(getClass().getResource(EAUHAUTGAUCHEPATH));
		} catch (IOException ex) {
			System.err.println("Image non trouvée : " + EAUHAUTGAUCHEPATH);
		}
		try {
			obstacle[EAUHAUTDROIT] = ImageIO.read(getClass().getResource(EAUHAUTDROITPATH));
		} catch (IOException ex) {
			System.err.println("Image non trouvée : " + EAUHAUTDROITPATH);
		}
		try {
			obstacle[EAUHAUT] = ImageIO.read(getClass().getResource(EAUHAUTPATH));
		} catch (IOException ex) {
			System.err.println("Image non trouvée : " + EAUHAUTPATH);
		}
		try {
			obstacle[EAUGAUCHE] = ImageIO.read(getClass().getResource(EAUGAUCHEPATH));
		} catch (IOException ex) {
			System.err.println("Image non trouvée : " + EAUGAUCHEPATH);
		}
		try {
			obstacle[EAUFONDGAUCHE] = ImageIO.read(getClass().getResource(EAUFONDGAUCHEPATH));
		} catch (IOException ex) {
			System.err.println("Image non trouvée : " + EAUFONDGAUCHEPATH);
		}
		try {
			obstacle[EAUFONDDROIT] = ImageIO.read(getClass().getResource(EAUFONDDROITPATH));
		} catch (IOException ex) {
			System.err.println("Image non trouvée : " + EAUFONDDROITPATH);
		}
		try {
			obstacle[EAUFONDBAS] = ImageIO.read(getClass().getResource(EAUFONDBASPATH));
		} catch (IOException ex) {
			System.err.println("Image non trouvée : " + EAUFONDBASPATH);
		}
		try {
			obstacle[EAUDROIT] = ImageIO.read(getClass().getResource(EAUDROITPATH));
		} catch (IOException ex) {
			System.err.println("Image non trouvée : " + EAUDROITPATH);
		}
		try {
			obstacle[EAU] = ImageIO.read(getClass().getResource(EAUPATH));
		} catch (IOException ex) {
			System.err.println("Image non trouvée : " + EAUPATH);
		}
	}

	public void tileMontagne() {
		try {
			obstacle[MONTAGNEHAUTDROIT] = ImageIO.read(getClass().getResource(MONTAGNEHAUTDROITPATH));
		} catch (IOException ex) {
			System.err.println("Image non trouvée : " + MONTAGNEHAUTDROITPATH);
		}
		try {
			obstacle[MONTAGNEHAUT] = ImageIO.read(getClass().getResource(MONTAGNEHAUTPATH));
		} catch (IOException ex) {
			System.err.println("Image non trouvée : " + MONTAGNEHAUTPATH);
		}
		try {
			obstacle[MONTAGNEHAUTGAUCHE] = ImageIO.read(getClass().getResource(MONTAGNEHAUTGAUCHEPATH));
		} catch (IOException ex) {
			System.err.println("Image non trouvée : " + MONTAGNEHAUTGAUCHEPATH);
		}
		try {
			obstacle[MONTAGNECENTREDROIT] = ImageIO.read(getClass().getResource(MONTAGNECENTREDROITPATH));
		} catch (IOException ex) {
			System.err.println("Image non trouvée : " + MONTAGNECENTREDROITPATH);
		}
		try {
			obstacle[MONTAGNECENTRE] = ImageIO.read(getClass().getResource(MONTAGNECENTREPATH));
		} catch (IOException ex) {
			System.err.println("Image non trouvée : " + MONTAGNECENTREPATH);
		}
		try {
			obstacle[MONTAGNECENTREGAUCHE] = ImageIO.read(getClass().getResource(MONTAGNECENTREGAUCHEPATH));
		} catch (IOException ex) {
			System.err.println("Image non trouvée : " + MONTAGNECENTREGAUCHEPATH);
		}
		try {
			obstacle[MONTAGNEBASDROIT] = ImageIO.read(getClass().getResource(MONTAGNEBASDROITPATH));
		} catch (IOException ex) {
			System.err.println("Image non trouvée : " + MONTAGNEBASDROITPATH);
		}
		try {
			obstacle[MONTAGNEBAS] = ImageIO.read(getClass().getResource(MONTAGNEBASPATH));
		} catch (IOException ex) {
			System.err.println("Image non trouvée : " + MONTAGNEBASPATH);
		}
		try {
			obstacle[MONTAGNEBASGAUCHE] = ImageIO.read(getClass().getResource(MONTAGNEBASGAUCHEPATH));
		} catch (IOException ex) {
			System.err.println("Image non trouvée : " + MONTAGNEBASGAUCHEPATH);
		}
	}

	private void tileSable() {
		try {
			bordureTerrain[BORDURESABLECOINBASDROIT] = ImageIO.read(getClass().getResource(BORDURESABLECOINBASDROITPATH));
		} catch (IOException ex) {
			System.err.println("Image non trouvée : " + BORDURESABLECOINBASDROITPATH);
		}
		try {
			bordureTerrain[BORDURESABLEHAUTGAUCHE] = ImageIO.read(getClass().getResource(BORDURESABLEHAUTGAUCHEPATH));
		} catch (IOException ex) {
			System.err.println("Image non trouvée : " + BORDURESABLEHAUTGAUCHEPATH);
		}
		try {
			bordureTerrain[BORDURESABLEHAUTDROIT] = ImageIO.read(getClass().getResource(BORDURESABLEHAUTDROITPATH));
		} catch (IOException ex) {
			System.err.println("Image non trouvée : " + BORDURESABLEHAUTDROITPATH);
		}
		try {
			bordureTerrain[BORDURESABLEHAUT] = ImageIO.read(getClass().getResource(BORDURESABLEHAUTPATH));
		} catch (IOException ex) {
			System.err.println("Image non trouvée : " + BORDURESABLEHAUTPATH);
		}
		try {
			bordureTerrain[BORDURESABLEGAUCHE] = ImageIO.read(getClass().getResource(BORDURESABLEGAUCHEPATH));
		} catch (IOException ex) {
			System.err.println("Image non trouvée : " + BORDURESABLEGAUCHEPATH);
		}
		try {
			bordureTerrain[BORDURESABLEDROIT] = ImageIO.read(getClass().getResource(BORDURESABLEDROITPATH));
		} catch (IOException ex) {
			System.err.println("Image non trouvée : " + BORDURESABLEDROITPATH);
		}
		try {
			bordureTerrain[BORDURESABLEBASGAUCHE] = ImageIO.read(getClass().getResource(BORDURESABLEBASGAUCHEPATH));
		} catch (IOException ex) {
			System.err.println("Image non trouvée : " + BORDURESABLEBASGAUCHEPATH);
		}
		try {
			bordureTerrain[BORDURESABLEBASDROIT] = ImageIO.read(getClass().getResource(BORDURESABLEBASDROITPATH));
		} catch (IOException ex) {
			System.err.println("Image non trouvée : " + BORDURESABLEBASDROITPATH);
		}
		try {
			bordureTerrain[BORDURESABLEBAS] = ImageIO.read(getClass().getResource(BORDURESABLEBASPATH));
		} catch (IOException ex) {
			System.err.println("Image non trouvée : " + BORDURESABLEBASPATH);
		}
		
		try {
			terrain[SABLECENTRE] = ImageIO.read(getClass().getResource(SABLECENTREPATH));
		} catch (IOException ex) {
			System.err.println("Image non trouvée : " + SABLECENTREPATH);
		}
	}
}
