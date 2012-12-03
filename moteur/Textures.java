package moteur;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author disavinr
 */
public final class Textures {
	/*
	 * ------ Tile Eau nb : 16 dont 3 bordures ------ Ce sont des obstacles
	 * indestructibles
	 */
	
	// Les obstacles indestructibles
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
	private static final String MONTAGNEHAUTDROITPATH = "/images/montagneHautDroite.png";
	private static final String MONTAGNEHAUTPATH = "/images/montagneHaut.png";
	private static final String MONTAGNEHAUTGAUCHEPATH = "/images/montagneHautGauche.png";
	private static final String MONTAGNECENTREDROITPATH = "/images/montagneCentreDroit.png";
	private static final String MONTAGNECENTREPATH = "/images/montagneCentre.png";
	private static final String MONTAGNECENTREGAUCHEPATH = "/images/montagneCentreGauche.png";
	private static final String MONTAGNEBASGAUCHEPATH = "/images/montagneBasDroit.png";
	private static final String MONTAGNEBASPATH = "/images/montagneBas.png";
	private static final String MONTAGNEBASDROITPATH = "/images/montagneBasGauche.png";
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
	private static final String BORDURESABLEBASPATH = "/images/bordureSableBas.png";
	private static final String BORDURESABLEBASDROITPATH = "/images/bordureSableBasDroit.png";
	private static final String BORDURESABLEBASGAUCHEPATH = "/images/bordureSableBasGauche.png";
	private static final String BORDURESABLEDROITPATH = "/images/bordureSableDroit.png";
	private static final String BORDURESABLEGAUCHEPATH = "/images/bordureSableGauche.png";
	private static final String BORDURESABLEHAUTPATH = "/images/bordureSableHaut.png";
	private static final String BORDURESABLEHAUTDROITPATH = "/images/bordureSableHautDroit.png";
	private static final String BORDURESABLEHAUTGAUCHEPATH = "/images/bordureSableHautGauche.png";
	private static final String BORDURESABLECOINBASDROITPATH = "/images/bordureSableCoinBasDroit.png";
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
	private static final String SABLEPATH = "/images/sableCentre.png";
	private static final String HERBEPATH = "/images/herbemod.png";
	private static final String NEIGEPATH = "/images/neige.png";
	private static final String GLACEPATH = "/images/glace.png";
	
	public static final int ROC = 0;
	public static final int ROUTE = 1;
	public static final int SABLE = 2;
	public static final int HERBE = 3;
	public static final int NEIGE = 4;
	public static final int GLACE = 5;
	
	/**
	 * Personnages
	 */
	public static final int ARCHERFELIN = 0;
	public static final int ASSASSINFELIN = 1;
	public static final int GUERRIERFELIN = 2;
	public static final int TACTICIENFELIN = 3;
	public static final int TANKFELIN = 4;
	
	public static final int ARCHEROISEAU = 5;
	public static final int ASSASSINOISEAU = 6;
	public static final int GUERRIEROISEAU = 7;
	public static final int TACTICIENOISEAU = 8;
	public static final int TANKOISEAU = 9;
	
	public static final int ARCHERREPTILE = 10;
	public static final int ASSASSINREPTILE = 11;
	public static final int GUERRIERREPTILE = 12;
	public static final int TACTICIENREPTILE = 13;
	public static final int TANKREPTILE = 14;
	
	private static final String ASSASSINOISEAUPATH = "/images/perso/oiseau/oiseau_test.png";
	private static final String ASSASSINREPTILEPATH = "/images/perso/reptile/reptile_test.png";
	private static final String ASSASSINFELINPATH = "/images/perso/felin/felin_test.png";
	private static final String TANKFELINPATH = "/images/perso/felin/lion.png";
	
	private BufferedImage[] terrain = new BufferedImage[6];
	private BufferedImage[] bordureTerrain = new BufferedImage[12];
	private BufferedImage[][] perso = new BufferedImage[15][4];
	private BufferedImage[] obstacle = new BufferedImage[22];

	private static Textures singleton;
	
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
			terrain[HERBE] = ImageIO.read(getClass().getResource(HERBEPATH));
		} catch (IOException ex) {
			System.err.println("Image non trouvée : " + HERBEPATH);
		}
		try {
			terrain[NEIGE] = ImageIO.read(getClass().getResource(NEIGEPATH));
		} catch (IOException ex) {
			System.err.println("Image non trouvée : " + NEIGEPATH);
		}
		try {
			terrain[GLACE] = ImageIO.read(getClass().getResource(GLACEPATH));
		} catch (IOException ex) {
			System.err.println("Image non trouvée : " + GLACEPATH);
		}

		tileEau();
		tileMontagne();
		tileSable();

		initPersos();
	}

	public static BufferedImage getPersonnage(int numPerso, Orientation orientation) {
		if(singleton == null) {
			singleton = new Textures();
		}
		return singleton.getPerso(numPerso, orientation);
	}
	
	private BufferedImage getPerso(int numPerso, Orientation orientation) {
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

	public static BufferedImage getTerrain(int numTerrain) {
		if(singleton == null) {
			singleton = new Textures();
		}
		return singleton.getTerr(numTerrain);
	}
	
	private BufferedImage getTerr(int numTerrain) {
		return terrain[numTerrain];
	}

	public static BufferedImage getObstacle(int numObstacle) {
		if(singleton == null) {
			singleton = new Textures();
		}
		return singleton.getObst(numObstacle);
	}
	
	private BufferedImage getObst(int numObstacle) {
		return obstacle[numObstacle];
	}

	public static BufferedImage getBordure(int numBordure) {
		if(singleton == null) {
			singleton = new Textures();
		}
		return singleton.getBord(numBordure);
	}

	private BufferedImage getBord(int numBordure) {
		return bordureTerrain[numBordure];
	}

	/**
	 * Certaines de ces images qui servent a construire les plans d'eau sont
	 * consideres comme de type HERBE et non EAU. Le type EAU est
	 * infranchissable, contrairement au type HERBE
	 */
	private void tileEau() {

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

	private void tileMontagne() {
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
			terrain[SABLE] = ImageIO.read(getClass().getResource(SABLEPATH));
		} catch (IOException ex) {
			System.err.println("Image non trouvée : " + SABLEPATH);
		}
	}

	private void initPersos() {
		//TODO textures personnages
		// OISEAU
		try {
			BufferedImage img = ImageIO.read(getClass().getResource(ASSASSINOISEAUPATH));
			perso[ASSASSINOISEAU][1] = img.getSubimage(0, 192, 32, 32);
			perso[ASSASSINOISEAU][3] = img.getSubimage(0, 236, 32, 32);
			perso[ASSASSINOISEAU][2] = img.getSubimage(0, 286, 32, 32);
			perso[ASSASSINOISEAU][0] = img.getSubimage(0, 334, 32, 32);
		} catch (IOException ex) {
			System.err.println("Image non trouvée : " + ASSASSINOISEAUPATH);
		}
		
		// REPTILE
		try {
			BufferedImage img = ImageIO.read(getClass().getResource(ASSASSINREPTILEPATH));
			perso[ASSASSINREPTILE][1] = img.getSubimage(33, 0, 32, 32);
			perso[ASSASSINREPTILE][2] = img.getSubimage(33, 32, 32, 32);
			perso[ASSASSINREPTILE][3] = img.getSubimage(33, 64, 32, 32);
			perso[ASSASSINREPTILE][0] = img.getSubimage(33, 96, 32, 32);
		} catch (IOException ex) {
			System.err.println("Image non trouvée : " + ASSASSINREPTILEPATH);
		}
		
		// FÉLIN
		try {
			BufferedImage img = ImageIO.read(getClass().getResource(ASSASSINFELINPATH));
			
			perso[ASSASSINFELIN][1] = img.getSubimage(224, 128, 32, 32);
			perso[ASSASSINFELIN][2] = img.getSubimage(224, 160, 32, 32);
			perso[ASSASSINFELIN][3] = img.getSubimage(224, 192, 32, 32);
			perso[ASSASSINFELIN][0] = img.getSubimage(224, 224, 32, 32);
			
			perso[ARCHERFELIN][1] = img.getSubimage(32, 128, 32, 32);
			perso[ARCHERFELIN][2] = img.getSubimage(32, 160, 32, 32);
			perso[ARCHERFELIN][3] = img.getSubimage(32, 192, 32, 32);
			perso[ARCHERFELIN][0] = img.getSubimage(32, 224, 32, 32);
			
			perso[GUERRIERFELIN][1] = img.getSubimage(128, 128, 32, 32);
			perso[GUERRIERFELIN][2] = img.getSubimage(128, 160, 32, 32);
			perso[GUERRIERFELIN][3] = img.getSubimage(128, 192, 32, 32);
			perso[GUERRIERFELIN][0] = img.getSubimage(128, 224, 32, 32);
			
			perso[TACTICIENFELIN][1] = img.getSubimage(320, 128, 32, 32);
			perso[TACTICIENFELIN][2] = img.getSubimage(320, 160, 32, 32);
			perso[TACTICIENFELIN][3] = img.getSubimage(320, 192, 32, 32);
			perso[TACTICIENFELIN][0] = img.getSubimage(320, 224, 32, 32);
		} catch (IOException ex) {
			System.err.println("Image non trouvée : " + ASSASSINFELINPATH);
		}
		try {
			BufferedImage img = scale(ImageIO.read(getClass().getResource(TANKFELINPATH)),3*32,4*32);
			
			perso[TANKFELIN][1] = img.getSubimage(32, 0, 32, 32);
			perso[TANKFELIN][2] = img.getSubimage(32, 32, 32, 32);
			perso[TANKFELIN][3] = img.getSubimage(32, 64, 32, 32);
			perso[TANKFELIN][0] = img.getSubimage(32, 96, 32, 32);
			
		} catch (IOException ex) {
			System.err.println("Image non trouvée : " + TANKFELINPATH);
		}
	}
	
	/** 
	 * Redimensionne une image.
	 * 
	 * @param source Image à redimensionner.
	 * @param width Largeur de l'image cible.
	 * @param height Hauteur de l'image cible.
	 * @return Image redimensionnée.
	 */
	public static BufferedImage scale(Image source, int width, int height) {
		/* On créé une nouvelle image aux bonnes dimensions. */
		BufferedImage buf = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

		/* On dessine sur le Graphics de l'image bufferisée. */
		Graphics2D g = buf.createGraphics();
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g.drawImage(source, 0, 0, width, height, null);
		g.dispose();

		/* On retourne l'image bufferisée, qui est une image. */
		return buf;
	}
}
