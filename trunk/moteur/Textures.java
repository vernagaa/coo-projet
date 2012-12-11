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
	public static final String EAUPATH = "/images/obstacle/eau/eau.png";
	public static final String EAUDROITPATH = "/images/obstacle/eau/eauDroit.png";
	public static final String EAUFONDBASPATH = "/images/obstacle/eau/eauFondBas.png";
	public static final String EAUFONDDROITPATH = "/images/obstacle/eau/eauFondDroit.png";
	public static final String EAUFONDGAUCHEPATH = "/images/obstacle/eau/eauFondGauche.png";
	public static final String EAUGAUCHEPATH = "/images/obstacle/eau/eauGauche.png";
	public static final String EAUHAUTPATH = "/images/obstacle/eau/eauHaut.png";
	public static final String EAUHAUTDROITPATH = "/images/obstacle/eau/eauHautDroit.png";
	public static final String EAUHAUTGAUCHEPATH = "/images/obstacle/eau/eauHautGauche.png";
	public static final String EAUVIRAGEBASDROITPATH = "/images/obstacle/eau/eauVirageBasDroit.png";
	public static final String EAUVIRAGEBASGAUCHEPATH = "/images/obstacle/eau/eauVirageBasGauche.png";
	public static final String EAUVIRAGEHAUTDROITPATH = "/images/obstacle/eau/eauVirageHautDroit.png";
	public static final String EAUVIRAGEHAUTGAUCHEPATH = "/images/obstacle/eau/eauVirageHautGauche.png";
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
	public static final String BORDUREEAUBASPATH = "/images/texture/bordureEauBas.png";
	public static final String BORDUREEAUDROITPATH = "/images/texture/bordureEauDroit.png";
	public static final String BORDUREEAUGAUCHEPATH = "/images/texture/bordureEauGauche.png";
	public static final int BORDUREEAUBAS = 0;
	public static final int BORDUREEAUDROIT = 1;
	public static final int BORDUREEAUGAUCHE = 2;
	//Chateaux
	public static final String CHATEAU1 = "/images/obstacle/chateau/chateau1.png";
	public static final String CHATEAU2 = "/images/obstacle/chateau/chateau2.png";
	public static final int CHATEAU1HAUTGAUCHE = 22;
	public static final int CHATEAU1HAUTDROIT = 23;
	public static final int CHATEAU1BASGAUCHE = 24;
	public static final int CHATEAU1BASDROIT = 25;
	public static final int CHATEAU2HAUTGAUCHE = 26;
	public static final int CHATEAU2HAUTDROIT = 27;
	public static final int CHATEAU2BASGAUCHE = 28;
	public static final int CHATEAU2BASDROIT = 29;

	/*
	 * ------ Tile Montagne nb : 9 ------ Ce sont des obstacles indestructibles
	 */
	public static final String MONTAGNEHAUTDROITPATH = "/images/obstacle/montagne/montagneHautDroite.png";
	public static final String MONTAGNEHAUTPATH = "/images/obstacle/montagne/montagneHaut.png";
	public static final String MONTAGNEHAUTGAUCHEPATH = "/images/obstacle/montagne/montagneHautGauche.png";
	public static final String MONTAGNECENTREDROITPATH = "/images/obstacle/montagne/montagneCentreDroit.png";
	public static final String MONTAGNECENTREPATH = "/images/obstacle/montagne/montagneCentre.png";
	public static final String MONTAGNECENTREGAUCHEPATH = "/images/obstacle/montagne/montagneCentreGauche.png";
	public static final String MONTAGNEBASGAUCHEPATH = "/images/obstacle/montagne/montagneBasDroit.png";
	public static final String MONTAGNEBASPATH = "/images/obstacle/montagne/montagneBas.png";
	public static final String MONTAGNEBASDROITPATH = "/images/obstacle/montagne/montagneBasGauche.png";
	public static final String MONTAGNECORNERDROITPATH = "/images/obstacle/montagne/montagneCornerDroit.png";
	public static final String MONTAGNECORNERGAUCHEPATH = "/images/obstacle/montagne/montagneCornerGauche.png";
	public static final int MONTAGNEHAUTDROIT = 13;
	public static final int MONTAGNEHAUT = 14;
	public static final int MONTAGNEHAUTGAUCHE = 15;
	public static final int MONTAGNECENTREDROIT = 16;
	public static final int MONTAGNECENTRE = 17;
	public static final int MONTAGNECENTREGAUCHE = 18;
	public static final int MONTAGNEBASGAUCHE = 19;
	public static final int MONTAGNEBAS = 20;
	public static final int MONTAGNEBASDROIT = 21;
	public static final int MONTAGNECORNERDROIT = 30;
	public static final int MONTAGNECORNERGAUCHE = 31;
	/*
	 * Tile Bordure Sable : 12
	 */
	public static final String BORDURESABLEBASPATH = "/images/texture/bordureSableBas.png";
	public static final String BORDURESABLEBASDROITPATH = "/images/texture/bordureSableBasDroit.png";
	public static final String BORDURESABLEBASGAUCHEPATH = "/images/texture/bordureSableBasGauche.png";
	public static final String BORDURESABLEDROITPATH = "/images/texture/bordureSableDroit.png";
	public static final String BORDURESABLEGAUCHEPATH = "/images/texture/bordureSableGauche.png";
	public static final String BORDURESABLEHAUTPATH = "/images/texture/bordureSableHaut.png";
	public static final String BORDURESABLEHAUTDROITPATH = "/images/texture/bordureSableHautDroit.png";
	public static final String BORDURESABLEHAUTGAUCHEPATH = "/images/texture/bordureSableHautGauche.png";
	public static final String BORDURESABLECOINBASDROITPATH = "/images/texture/bordureSableCoinBasDroit.png";
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
	public static final String ROCPATH = "/images/texture/roc.png";
	public static final String ROUTEPATH = "/images/texture/route.png";
	public static final String SABLEPATH = "/images/texture/sableCentre.png";
	public static final String HERBEPATH = "/images/texture/herbemod.png";
	public static final String NEIGEPATH = "/images/texture/neige.png";
	public static final String GLACEPATH = "/images/texture/glace.png";
	public static final int ROC = 0;
	public static final int ROUTE = 1;
	public static final int SABLE = 2;
	public static final int HERBE = 3;
	public static final int NEIGE = 4;
	public static final int GLACE = 5;
	// Les obstacles destructibles
	/**
	 * Barriere
	 */
	public static final String BARRIERECOINBASDROITPATH = "/images/obstacle/barriere/barriereCoinBasDroit.png";
	public static final String BARRIERECOINHAUTDROITPATH = "/images/obstacle/barriere/barriereCoinHautDroit.png";
	public static final String BARRIERECOINFINDROITPATH = "/images/obstacle/barriere/barriereCoinFinDroit.png";
	public static final String BARRIEREDROITFINPATH = "/images/obstacle/barriere/barriereDroitFin.png";
	public static final String BARRIEREDROITPATH = "/images/obstacle/barriere/barriereDroit.png";
	public static final String BARRIEREGAUCHEFINPATH = "/images/obstacle/barriere/barriereGaucheFin.png";
	public static final String BARRIERECOINFINGAUCHEPATH = "/images/obstacle/barriere/barriereCoinFinGauche.png";
	public static final String BARRIEREBASPATH = "/images/obstacle/barriere/barriereBas.png";
	public static final String BARRIEREGAUCHEPATH = "/images/obstacle/barriere/barriereGauche.png";
	public static final String BARRIERECOINBASGAUCHEPATH = "/images/obstacle/barriere/barriereCoinBasGauche.png";
	public static final String BARRIERECOINHAUTGAUCHEPATH = "/images/obstacle/barriere/barriereCoinHautGauche.png";
	public static final int BARRIERECOINBASDROIT = 32;
	public static final int BARRIERECOINHAUTDROIT = 33;
	public static final int BARRIERECOINFINDROIT = 34;
	public static final int BARRIEREFINDROIT = 35;
	public static final int BARRIEREDROIT = 36;
	public static final int BARRIEREGAUCHEFIN = 37;
	public static final int BARRIERECOINFINGAUCHE = 38;
	public static final int BARRIEREBAS = 39;
	public static final int BARRIEREGAUCHE = 40;
	public static final int BARRIERECOINBASGAUCHE = 41;
	public static final int BARRIERECOINHAUTGAUCHE = 42;
	/**
	 * TÃ©lÃ©porteur
	 */
	public static final String TELEPORTEURPATH = "/images/obstacle/teleporteur/portal.png";
	public static final int TELEPORTEUR1 = 43;
	public static final int TELEPORTEUR2 = 44;
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
	// Images des personages
	public static final String ASSASSINOISEAUPATH = "/images/perso/oiseau/oiseau_test2.png";
	public static final String TACTICIENOISEAUPATH = "/images/perso/oiseau/grand_oiseau.png";
	public static final String ARCHERREPTILEPATH = "/images/perso/reptile/cameleon.png";
	public static final String ASSASSINREPTILEPATH = "/images/perso/reptile/serpent.png";
	public static final String GUERRIERREPTILEPATH = "/images/perso/reptile/crocodile2.png";
	public static final String TACTICIENREPTILEPATH = "/images/perso/reptile/grenouille.png";
	public static final String TANKREPTILEPATH = "/images/perso/reptile/tortue.png";
	public static final String ASSASSINFELINPATH = "/images/perso/felin/felin_test.png";
	public static final String TANKFELINPATH = "/images/perso/felin/lion2.png";
	
	public static final String ETOILEPATH = "/images/perso/etoileCommandant.png";
	// Attributs
	private BufferedImage[] terrain = new BufferedImage[7];
	private BufferedImage[] bordureTerrain = new BufferedImage[12];
	private BufferedImage[][][] perso = new BufferedImage[15][4][3];
	private BufferedImage[] obstacle = new BufferedImage[45];
	private BufferedImage etoileCommandant;
	private static Textures singleton = new Textures();

	/**
	 * Constructeur de Texture
	 */
	public Textures() {
//		for (int i = 0; i < terrain.length; i++) {
//			terrain[i] = new BufferedImage(Case.TAILLE, Case.TAILLE, BufferedImage.TYPE_INT_ARGB);
//		}
//		for (int i = 0; i < bordureTerrain.length; i++) {
//			bordureTerrain[i] = new BufferedImage(Case.TAILLE, Case.TAILLE, BufferedImage.TYPE_INT_ARGB);
//		}
//		for (int i = 0; i < perso.length; i++) {
//			for (int j = 0; j < perso[i].length; j++) {
//				perso[i][j] = new BufferedImage(Case.TAILLE, Case.TAILLE, BufferedImage.TYPE_INT_ARGB);
//			}
//		}
//		for (int i = 0; i < obstacle.length; i++) {
//			obstacle[i] = new BufferedImage(Case.TAILLE, Case.TAILLE, BufferedImage.TYPE_INT_ARGB);
//		}

		try {
			etoileCommandant = ImageIO.read(getClass().getResource(ETOILEPATH));
		} catch (IOException ex) {
			System.err.println("Image non trouvÃ©e : " + ETOILEPATH);
		}


		try {
			terrain[ROC] = ImageIO.read(getClass().getResource(ROCPATH));
		} catch (IOException ex) {
			System.err.println("Image non trouvÃ©e : " + ROCPATH);
		}
		try {
			terrain[ROUTE] = ImageIO.read(getClass().getResource(ROUTEPATH));
		} catch (IOException ex) {
			System.err.println("Image non trouvÃ©e : " + ROUTEPATH);
		}

		try {
			terrain[HERBE] = ImageIO.read(getClass().getResource(HERBEPATH));
		} catch (IOException ex) {
			System.err.println("Image non trouvÃ©e : " + HERBEPATH);
		}
		try {
			terrain[NEIGE] = ImageIO.read(getClass().getResource(NEIGEPATH));
		} catch (IOException ex) {
			System.err.println("Image non trouvÃ©e : " + NEIGEPATH);
		}
		try {
			terrain[GLACE] = ImageIO.read(getClass().getResource(GLACEPATH));
		} catch (IOException ex) {
			System.err.println("Image non trouvÃ©e : " + GLACEPATH);
		}
		try {
			BufferedImage img = scale(ImageIO.read(getClass().getResource(TELEPORTEURPATH)), 2 * 32, 32);
			obstacle[TELEPORTEUR1] = img.getSubimage(0, 0, 32, 32);
			obstacle[TELEPORTEUR2] = img.getSubimage(32, 0, 32, 32);
		} catch (IOException ex) {
			System.err.println("Image non trouvÃ©e : " + TELEPORTEURPATH);
		}

		tileEau();
		tileMontagne();
		tileSable();
		tileChateau();
		tileBarriere();
		initPersos();
	}
	
	public static BufferedImage getEtoileCommandant(){
		return singleton.etoileCommandant;
	}

	public static BufferedImage getPersonnage(int numPerso, Orientation orientation) {
		return singleton.getPerso(numPerso, orientation);
	}

	public static BufferedImage getPersonnage(int numPerso, Orientation orientation, int i) {
		return singleton.getPerso(numPerso, orientation, i);
	}

	/**
	 * Renvoie l'image d'un personnage avec la bonne orientation
	 * @param famille Famille dans FabriquePion
	 * @param classe Classe dans FabriquePion
	 * @param orientation
	 * @return
	 */
	public static BufferedImage getPersonnage(int famille, int classe, Orientation orientation) {
		return getPersonnage(famille * 5 + classe, orientation);
	}

	/**
	 * Renvoie l'image du personnage pour l'animation
	 * @param famille
	 * @param classe
	 * @param orientation
	 * @param i
	 * @return 
	 */
	public static BufferedImage getPersonnage(int famille, int classe, Orientation orientation, int i) {
		return getPersonnage(famille * 5 + classe, orientation, i);
	}

	
	private BufferedImage getPerso(int numPerso, Orientation orientation) {
		switch (orientation) {
			case NORD:
				return perso[numPerso][0][1];
			case SUD:
				return perso[numPerso][1][1];
			case EST:
				return perso[numPerso][2][1];
			case OUEST:
				return perso[numPerso][3][1];
			default:
				return null;
		}
	}

	private BufferedImage getPerso(int numPerso, Orientation orientation, int i) {
		switch (orientation) {
			case NORD:
				return perso[numPerso][0][i];
			case SUD:
				return perso[numPerso][1][i];
			case EST:
				return perso[numPerso][2][i];
			case OUEST:
				return perso[numPerso][3][i];
			default:
				return null;
		}
	}

	/**
	 * Renvoie l'image du terrain
	 * @param numTerrain
	 * @return 
	 */
	public static BufferedImage getTerrain(int numTerrain) {
		return singleton.getTerr(numTerrain);
	}

	private BufferedImage getTerr(int numTerrain) {
		return terrain[numTerrain];
	}

	/**
	 * Renvoie l'image de l'obstacle
	 * @param numObstacle
	 * @return 
	 */
	public static BufferedImage getObstacle(int numObstacle) {
		return singleton.getObst(numObstacle);
	}

	private BufferedImage getObst(int numObstacle) {
		return obstacle[numObstacle];
	}

	/**
	 * Renvoie l'image de la bordure
	 * @param numBordure
	 * @return 
	 */
	public static BufferedImage getBordure(int numBordure) {
		return singleton.getBord(numBordure);
	}

	private BufferedImage getBord(int numBordure) {
		return bordureTerrain[numBordure];
	}

	private void tileEau() {

		try {
			bordureTerrain[BORDUREEAUBAS] = ImageIO.read(getClass().getResource(BORDUREEAUBASPATH));
		} catch (IOException ex) {
			System.err.println("Image non trouvÃ©e : " + BORDUREEAUBASPATH);
		}
		try {
			bordureTerrain[BORDUREEAUGAUCHE] = ImageIO.read(getClass().getResource(BORDUREEAUGAUCHEPATH));
		} catch (IOException ex) {
			System.err.println("Image non trouvÃ©e : " + BORDUREEAUGAUCHEPATH);
		}
		try {
			bordureTerrain[BORDUREEAUDROIT] = ImageIO.read(getClass().getResource(BORDUREEAUDROITPATH));
		} catch (IOException ex) {
			System.err.println("Image non trouvÃ©e : " + BORDUREEAUDROITPATH);
		}

		try {
			obstacle[EAUVIRAGEHAUTGAUCHE] = ImageIO.read(getClass().getResource(EAUVIRAGEHAUTGAUCHEPATH));
		} catch (IOException ex) {
			System.err.println("Image non trouvÃ©e : " + EAUVIRAGEHAUTGAUCHEPATH);
		}
		try {
			obstacle[EAUVIRAGEHAUTDROIT] = ImageIO.read(getClass().getResource(EAUVIRAGEHAUTDROITPATH));
		} catch (IOException ex) {
			System.err.println("Image non trouvÃ©e : " + EAUVIRAGEHAUTDROITPATH);
		}
		try {
			obstacle[EAUVIRAGEBASGAUCHE] = ImageIO.read(getClass().getResource(EAUVIRAGEBASGAUCHEPATH));
		} catch (IOException ex) {
			System.err.println("Image non trouvÃ©e : " + EAUVIRAGEBASGAUCHEPATH);
		}
		try {
			obstacle[EAUVIRAGEBASDROIT] = ImageIO.read(getClass().getResource(EAUVIRAGEBASDROITPATH));
		} catch (IOException ex) {
			System.err.println("Image non trouvÃ©e : " + EAUVIRAGEBASDROITPATH);
		}
		try {
			obstacle[EAUHAUTGAUCHE] = ImageIO.read(getClass().getResource(EAUHAUTGAUCHEPATH));
		} catch (IOException ex) {
			System.err.println("Image non trouvÃ©e : " + EAUHAUTGAUCHEPATH);
		}
		try {
			obstacle[EAUHAUTDROIT] = ImageIO.read(getClass().getResource(EAUHAUTDROITPATH));
		} catch (IOException ex) {
			System.err.println("Image non trouvÃ©e : " + EAUHAUTDROITPATH);
		}
		try {
			obstacle[EAUHAUT] = ImageIO.read(getClass().getResource(EAUHAUTPATH));
		} catch (IOException ex) {
			System.err.println("Image non trouvÃ©e : " + EAUHAUTPATH);
		}
		try {
			obstacle[EAUGAUCHE] = ImageIO.read(getClass().getResource(EAUGAUCHEPATH));
		} catch (IOException ex) {
			System.err.println("Image non trouvÃ©e : " + EAUGAUCHEPATH);
		}
		try {
			obstacle[EAUFONDGAUCHE] = ImageIO.read(getClass().getResource(EAUFONDGAUCHEPATH));
		} catch (IOException ex) {
			System.err.println("Image non trouvÃ©e : " + EAUFONDGAUCHEPATH);
		}
		try {
			obstacle[EAUFONDDROIT] = ImageIO.read(getClass().getResource(EAUFONDDROITPATH));
		} catch (IOException ex) {
			System.err.println("Image non trouvÃ©e : " + EAUFONDDROITPATH);
		}
		try {
			obstacle[EAUFONDBAS] = ImageIO.read(getClass().getResource(EAUFONDBASPATH));
		} catch (IOException ex) {
			System.err.println("Image non trouvÃ©e : " + EAUFONDBASPATH);
		}
		try {
			obstacle[EAUDROIT] = ImageIO.read(getClass().getResource(EAUDROITPATH));
		} catch (IOException ex) {
			System.err.println("Image non trouvÃ©e : " + EAUDROITPATH);
		}
		try {
			obstacle[EAU] = ImageIO.read(getClass().getResource(EAUPATH));
		} catch (IOException ex) {
			System.err.println("Image non trouvÃ©e : " + EAUPATH);
		}
	}

	private void tileChateau() {

		try {
			BufferedImage img1 = ImageIO.read(getClass().getResource(CHATEAU1));

			obstacle[CHATEAU1HAUTGAUCHE] = img1.getSubimage(0, 0, 30, 30);
			obstacle[CHATEAU1HAUTDROIT] = img1.getSubimage(30, 0, 30, 30);
			obstacle[CHATEAU1BASGAUCHE] = img1.getSubimage(0, 30, 30, 30);
			obstacle[CHATEAU1BASDROIT] = img1.getSubimage(30, 30, 30, 30);

		} catch (IOException ex) {
			System.err.println("Image non trouvÃ©e : " + CHATEAU1);
		}
		try {
			BufferedImage img2 = ImageIO.read(getClass().getResource(CHATEAU2));
			obstacle[CHATEAU2HAUTGAUCHE] = img2.getSubimage(0, 0, 30, 30);
			obstacle[CHATEAU2HAUTDROIT] = img2.getSubimage(30, 0, 30, 30);
			obstacle[CHATEAU2BASGAUCHE] = img2.getSubimage(0, 30, 30, 30);
			obstacle[CHATEAU2BASDROIT] = img2.getSubimage(30, 30, 30, 30);

		} catch (IOException ex) {
			System.err.println("Image non trouvÃ©e : " + CHATEAU2);
		}
	}

	private void tileMontagne() {
		try {
			obstacle[MONTAGNEHAUTDROIT] = ImageIO.read(getClass().getResource(MONTAGNEHAUTDROITPATH));
		} catch (IOException ex) {
			System.err.println("Image non trouvÃ©e : " + MONTAGNEHAUTDROITPATH);
		}
		try {
			obstacle[MONTAGNEHAUT] = ImageIO.read(getClass().getResource(MONTAGNEHAUTPATH));
		} catch (IOException ex) {
			System.err.println("Image non trouvÃ©e : " + MONTAGNEHAUTPATH);
		}
		try {
			obstacle[MONTAGNEHAUTGAUCHE] = ImageIO.read(getClass().getResource(MONTAGNEHAUTGAUCHEPATH));
		} catch (IOException ex) {
			System.err.println("Image non trouvÃ©e : " + MONTAGNEHAUTGAUCHEPATH);
		}
		try {
			obstacle[MONTAGNECENTREDROIT] = ImageIO.read(getClass().getResource(MONTAGNECENTREDROITPATH));
		} catch (IOException ex) {
			System.err.println("Image non trouvÃ©e : " + MONTAGNECENTREDROITPATH);
		}
		try {
			obstacle[MONTAGNECENTRE] = ImageIO.read(getClass().getResource(MONTAGNECENTREPATH));
		} catch (IOException ex) {
			System.err.println("Image non trouvÃ©e : " + MONTAGNECENTREPATH);
		}
		try {
			obstacle[MONTAGNECENTREGAUCHE] = ImageIO.read(getClass().getResource(MONTAGNECENTREGAUCHEPATH));
		} catch (IOException ex) {
			System.err.println("Image non trouvÃ©e : " + MONTAGNECENTREGAUCHEPATH);
		}
		try {
			obstacle[MONTAGNEBASDROIT] = ImageIO.read(getClass().getResource(MONTAGNEBASDROITPATH));
		} catch (IOException ex) {
			System.err.println("Image non trouvÃ©e : " + MONTAGNEBASDROITPATH);
		}
		try {
			obstacle[MONTAGNEBAS] = ImageIO.read(getClass().getResource(MONTAGNEBASPATH));
		} catch (IOException ex) {
			System.err.println("Image non trouvÃ©e : " + MONTAGNEBASPATH);
		}
		try {
			obstacle[MONTAGNEBASGAUCHE] = ImageIO.read(getClass().getResource(MONTAGNEBASGAUCHEPATH));
		} catch (IOException ex) {
			System.err.println("Image non trouvÃ©e : " + MONTAGNEBASGAUCHEPATH);
		}
		try {
			obstacle[MONTAGNECORNERGAUCHE] = ImageIO.read(getClass().getResource(MONTAGNECORNERGAUCHEPATH));
		} catch (IOException ex) {
			System.err.println("Image non trouvÃ©e : " + MONTAGNECORNERGAUCHEPATH);
		}
		try {
			obstacle[MONTAGNECORNERDROIT] = ImageIO.read(getClass().getResource(MONTAGNECORNERDROITPATH));
		} catch (IOException ex) {
			System.err.println("Image non trouvÃ©e : " + MONTAGNECORNERDROITPATH);
		}
	}

	private void tileSable() {
		try {
			bordureTerrain[BORDURESABLECOINBASDROIT] = ImageIO.read(getClass().getResource(BORDURESABLECOINBASDROITPATH));
		} catch (IOException ex) {
			System.err.println("Image non trouvÃ©e : " + BORDURESABLECOINBASDROITPATH);
		}
		try {
			bordureTerrain[BORDURESABLEHAUTGAUCHE] = ImageIO.read(getClass().getResource(BORDURESABLEHAUTGAUCHEPATH));
		} catch (IOException ex) {
			System.err.println("Image non trouvÃ©e : " + BORDURESABLEHAUTGAUCHEPATH);
		}
		try {
			bordureTerrain[BORDURESABLEHAUTDROIT] = ImageIO.read(getClass().getResource(BORDURESABLEHAUTDROITPATH));
		} catch (IOException ex) {
			System.err.println("Image non trouvÃ©e : " + BORDURESABLEHAUTDROITPATH);
		}
		try {
			bordureTerrain[BORDURESABLEHAUT] = ImageIO.read(getClass().getResource(BORDURESABLEHAUTPATH));
		} catch (IOException ex) {
			System.err.println("Image non trouvÃ©e : " + BORDURESABLEHAUTPATH);
		}
		try {
			bordureTerrain[BORDURESABLEGAUCHE] = ImageIO.read(getClass().getResource(BORDURESABLEGAUCHEPATH));
		} catch (IOException ex) {
			System.err.println("Image non trouvÃ©e : " + BORDURESABLEGAUCHEPATH);
		}
		try {
			bordureTerrain[BORDURESABLEDROIT] = ImageIO.read(getClass().getResource(BORDURESABLEDROITPATH));
		} catch (IOException ex) {
			System.err.println("Image non trouvÃ©e : " + BORDURESABLEDROITPATH);
		}
		try {
			bordureTerrain[BORDURESABLEBASGAUCHE] = ImageIO.read(getClass().getResource(BORDURESABLEBASGAUCHEPATH));
		} catch (IOException ex) {
			System.err.println("Image non trouvÃ©e : " + BORDURESABLEBASGAUCHEPATH);
		}
		try {
			bordureTerrain[BORDURESABLEBASDROIT] = ImageIO.read(getClass().getResource(BORDURESABLEBASDROITPATH));
		} catch (IOException ex) {
			System.err.println("Image non trouvÃ©e : " + BORDURESABLEBASDROITPATH);
		}
		try {
			bordureTerrain[BORDURESABLEBAS] = ImageIO.read(getClass().getResource(BORDURESABLEBASPATH));
		} catch (IOException ex) {
			System.err.println("Image non trouvÃ©e : " + BORDURESABLEBASPATH);
		}

		try {
			terrain[SABLE] = ImageIO.read(getClass().getResource(SABLEPATH));
		} catch (IOException ex) {
			System.err.println("Image non trouvÃ©e : " + SABLEPATH);
		}
	}

	private void initPersos() {
		// OISEAU
		try {
			BufferedImage img = ImageIO.read(getClass().getResource(ASSASSINOISEAUPATH));

			for (int i = 0; i < 3; i++) {
				perso[ARCHEROISEAU][1][i] = img.getSubimage(32 * 6 + 32 * i, 0, 32, 32);
				perso[ARCHEROISEAU][3][i] = img.getSubimage(32 * 6 + 32 * i, 32, 32, 32);
				perso[ARCHEROISEAU][2][i] = img.getSubimage(32 * 6 + 32 * i, 64, 32, 32);
				perso[ARCHEROISEAU][0][i] = img.getSubimage(32 * 6 + 32 * i, 96, 32, 32);

				perso[ASSASSINOISEAU][1][i] = img.getSubimage(32 * 3 + 32 * i, 0, 32, 32);
				perso[ASSASSINOISEAU][3][i] = img.getSubimage(32 * 3 + 32 * i, 32, 32, 32);
				perso[ASSASSINOISEAU][2][i] = img.getSubimage(32 * 3 + 32 * i, 64, 32, 32);
				perso[ASSASSINOISEAU][0][i] = img.getSubimage(32 * 3 + 32 * i, 96, 32, 32);

				perso[GUERRIEROISEAU][1][i] = img.getSubimage(32 * 6 + 32 * i, 128, 32, 32);
				perso[GUERRIEROISEAU][3][i] = img.getSubimage(32 * 6 + 32 * i, 160, 32, 32);
				perso[GUERRIEROISEAU][2][i] = img.getSubimage(32 * 6 + 32 * i, 192, 32, 32);
				perso[GUERRIEROISEAU][0][i] = img.getSubimage(32 * 6 + 32 * i, 224, 32, 32);

				perso[TANKOISEAU][1][i] = img.getSubimage(32 * 3 + 32 * i, 128, 32, 32);
				perso[TANKOISEAU][3][i] = img.getSubimage(32 * 3 + 32 * i, 160, 32, 32);
				perso[TANKOISEAU][2][i] = img.getSubimage(32 * 3 + 32 * i, 192, 32, 32);
				perso[TANKOISEAU][0][i] = img.getSubimage(32 * 3 + 32 * i, 224, 32, 32);
			}
		} catch (IOException ex) {
			System.err.println("Image non trouvÃ©e : " + ASSASSINOISEAUPATH);
		}
		try {
			BufferedImage img = scale(ImageIO.read(getClass().getResource(TACTICIENOISEAUPATH)), 32 * 3, 32 * 4);
			for (int i = 0; i < 3; i++) {
				perso[TACTICIENOISEAU][1][i] = img.getSubimage(32 * i, 0, 32, 32);
				perso[TACTICIENOISEAU][3][i] = img.getSubimage(32 * i, 32, 32, 32);
				perso[TACTICIENOISEAU][2][i] = img.getSubimage(32 * i, 64, 32, 32);
				perso[TACTICIENOISEAU][0][i] = img.getSubimage(32 * i, 96, 32, 32);
			}
		} catch (IOException ex) {
			System.err.println("Image non trouvÃ©e : " + ASSASSINOISEAUPATH);
		}

		// REPTILE
		try {
			BufferedImage img = scale(ImageIO.read(getClass().getResource(ARCHERREPTILEPATH)), 32 * 3, 32 * 4);
			for (int i = 0; i < 3; i++) {
				perso[ARCHERREPTILE][1][i] = img.getSubimage(32 * i, 0, 32, 32);
				perso[ARCHERREPTILE][3][i] = img.getSubimage(32 * i, 32, 32, 32);
				perso[ARCHERREPTILE][2][i] = img.getSubimage(32 * i, 64, 32, 32);
				perso[ARCHERREPTILE][0][i] = img.getSubimage(32 * i, 96, 32, 32);
			}
		} catch (IOException ex) {
			System.err.println("Image non trouvÃ©e : " + ARCHERREPTILEPATH);
		}
		try {
			BufferedImage img = ImageIO.read(getClass().getResource(TANKREPTILEPATH));
			for (int i = 0; i < 3; i++) {
				perso[TANKREPTILE][1][i] = img.getSubimage(0, 0, 32, 32);
				perso[TANKREPTILE][0][i] = img.getSubimage(0, 32, 32, 32);
				perso[TANKREPTILE][3][i] = img.getSubimage(32, 0, 32, 32);
				perso[TANKREPTILE][2][i] = img.getSubimage(32, 32, 32, 32);
			}
		} catch (IOException ex) {
			System.err.println("Image non trouvÃ©e : " + TANKREPTILEPATH);
		}
		try {
			BufferedImage img = scale(ImageIO.read(getClass().getResource(GUERRIERREPTILEPATH)), 3 * 32, 4 * 32);
			for (int i = 0; i < 3; i++) {
				perso[GUERRIERREPTILE][1][i] = img.getSubimage(32 * i, 0, 32, 32);
				perso[GUERRIERREPTILE][3][i] = img.getSubimage(32 * i, 32, 32, 32);
				perso[GUERRIERREPTILE][2][i] = img.getSubimage(32 * i, 64, 32, 32);
				perso[GUERRIERREPTILE][0][i] = img.getSubimage(32 * i, 96, 32, 32);
			}
		} catch (IOException ex) {
			System.err.println("Image non trouvÃ©e : " + GUERRIERREPTILEPATH);
		}
		try {
			BufferedImage img = ImageIO.read(getClass().getResource(ASSASSINREPTILEPATH));
			for (int i = 0; i < 3; i++) {
				perso[ASSASSINREPTILE][1][i] = img.getSubimage(32 * i, 0, 32, 32);
				perso[ASSASSINREPTILE][3][i] = img.getSubimage(32 * i, 32, 32, 32);
				perso[ASSASSINREPTILE][0][i] = img.getSubimage(32 * i, 64, 32, 32);
				perso[ASSASSINREPTILE][2][i] = img.getSubimage(32 * i, 96, 32, 32);
			}
		} catch (IOException ex) {
			System.err.println("Image non trouvÃ©e : " + ASSASSINREPTILEPATH);
		}
		try {
			BufferedImage img = ImageIO.read(getClass().getResource(TACTICIENREPTILEPATH));
			for (int i = 0; i < 3; i++) {
				perso[TACTICIENREPTILE][1][i] = img.getSubimage(32 * i, 0, 32, 32);
				perso[TACTICIENREPTILE][3][i] = img.getSubimage(32 * i, 32, 32, 32);
				perso[TACTICIENREPTILE][2][i] = img.getSubimage(32 * i, 64, 32, 32);
				perso[TACTICIENREPTILE][0][i] = img.getSubimage(32 * i, 96, 32, 32);
			}
		} catch (IOException ex) {
			System.err.println("Image non trouvÃ©e : " + TACTICIENREPTILEPATH);
		}

		// FÃ‰LIN
		try {
			BufferedImage img = ImageIO.read(getClass().getResource(ASSASSINFELINPATH));
			for (int i = 0; i < 3; i++) {
				perso[ASSASSINFELIN][1][i] = img.getSubimage(192 + 32 * i, 2, 32, 30);
				perso[ASSASSINFELIN][3][i] = img.getSubimage(192 + 32 * i, 34, 32, 30);
				perso[ASSASSINFELIN][2][i] = img.getSubimage(192 + 32 * i, 66, 32, 30);
				perso[ASSASSINFELIN][0][i] = img.getSubimage(192 + 32 * i, 98, 32, 30);

				perso[ARCHERFELIN][1][i] = img.getSubimage(32 * i, 2, 32, 30);
				perso[ARCHERFELIN][3][i] = img.getSubimage(32 * i, 34, 32, 30);
				perso[ARCHERFELIN][2][i] = img.getSubimage(32 * i, 66, 32, 30);
				perso[ARCHERFELIN][0][i] = img.getSubimage(32 * i, 98, 32, 30);

				perso[GUERRIERFELIN][1][i] = img.getSubimage(96 + 32 * i, 2, 32, 30);
				perso[GUERRIERFELIN][3][i] = img.getSubimage(96 + 32 * i, 34, 32, 30);
				perso[GUERRIERFELIN][2][i] = img.getSubimage(96 + 32 * i, 66, 32, 30);
				perso[GUERRIERFELIN][0][i] = img.getSubimage(96 + 32 * i, 98, 32, 30);

				perso[TACTICIENFELIN][1][i] = img.getSubimage(288 + 32 * i, 2, 32, 30);
				perso[TACTICIENFELIN][3][i] = img.getSubimage(288 + 32 * i, 34, 32, 30);
				perso[TACTICIENFELIN][2][i] = img.getSubimage(288 + 32 * i, 66, 32, 30);
				perso[TACTICIENFELIN][0][i] = img.getSubimage(288 + 32 * i, 98, 32, 30);
			}
		} catch (IOException ex) {
			System.err.println("Image non trouvÃ©e : " + ASSASSINFELINPATH);
		}
		try {
			BufferedImage img = scale(ImageIO.read(getClass().getResource(TANKFELINPATH)), 3 * 32, 4 * 32);
			for (int i = 0; i < 3; i++) {
				perso[TANKFELIN][1][i] = img.getSubimage(32 * i, 0, 32, 30);
				perso[TANKFELIN][3][i] = img.getSubimage(32 * i, 34, 32, 30);
				perso[TANKFELIN][2][i] = img.getSubimage(32 * i, 66, 32, 30);
				perso[TANKFELIN][0][i] = img.getSubimage(32 * i, 98, 32, 30);
			}

		} catch (IOException ex) {
			System.err.println("Image non trouvÃ©e : " + TANKFELINPATH);
		}
	}

	private void tileBarriere() {
		try {
			obstacle[BARRIERECOINBASDROIT] = ImageIO.read(getClass().getResource(BARRIERECOINBASDROITPATH));
		} catch (IOException ex) {
			System.err.println("Image non trouvÃ©e : " + BARRIERECOINBASDROITPATH);
		}
		try {
			obstacle[BARRIERECOINHAUTDROIT] = ImageIO.read(getClass().getResource(BARRIERECOINHAUTDROITPATH));
		} catch (IOException ex) {
			System.err.println("Image non trouvÃ©e : " + BARRIERECOINHAUTDROITPATH);
		}
		try {
			obstacle[BARRIERECOINFINDROIT] = ImageIO.read(getClass().getResource(BARRIERECOINFINDROITPATH));
		} catch (IOException ex) {
			System.err.println("Image non trouvÃ©e : " + BARRIERECOINFINDROITPATH);
		}
		try {
			obstacle[BARRIEREFINDROIT] = ImageIO.read(getClass().getResource(BARRIEREDROITFINPATH));
		} catch (IOException ex) {
			System.err.println("Image non trouvÃ©e : " + BARRIEREDROITFINPATH);
		}
		try {
			obstacle[BARRIEREDROIT] = ImageIO.read(getClass().getResource(BARRIEREDROITPATH));
		} catch (IOException ex) {
			System.err.println("Image non trouvÃ©e : " + BARRIEREDROITPATH);
		}
		try {
			obstacle[BARRIEREGAUCHEFIN] = ImageIO.read(getClass().getResource(BARRIEREGAUCHEFINPATH));
		} catch (IOException ex) {
			System.err.println("Image non trouvÃ©e : " + BARRIEREGAUCHEFINPATH);
		}
		try {
			obstacle[BARRIERECOINFINGAUCHE] = ImageIO.read(getClass().getResource(BARRIERECOINFINGAUCHEPATH));
		} catch (IOException ex) {
			System.err.println("Image non trouvÃ©e : " + BARRIERECOINFINGAUCHEPATH);
		}
		try {
			obstacle[BARRIEREBAS] = ImageIO.read(getClass().getResource(BARRIEREBASPATH));
		} catch (IOException ex) {
			System.err.println("Image non trouvÃ©e : " + BARRIEREBASPATH);
		}
		try {
			obstacle[BARRIEREGAUCHE] = ImageIO.read(getClass().getResource(BARRIEREGAUCHEPATH));
		} catch (IOException ex) {
			System.err.println("Image non trouvÃ©e : " + BARRIEREGAUCHEPATH);
		}
		try {
			obstacle[BARRIERECOINBASGAUCHE] = ImageIO.read(getClass().getResource(BARRIERECOINBASGAUCHEPATH));
		} catch (IOException ex) {
			System.err.println("Image non trouvÃ©e : " + BARRIERECOINBASGAUCHEPATH);
		}
		try {
			obstacle[BARRIERECOINHAUTGAUCHE] = ImageIO.read(getClass().getResource(BARRIERECOINHAUTGAUCHEPATH));
		} catch (IOException ex) {
			System.err.println("Image non trouvÃ©e : " + BARRIERECOINHAUTGAUCHEPATH);
		}

	}

	/**
	 * Redimensionne une image.
	 *
	 * @param source Image Ã Â redimensionner.
	 * @param width Largeur de l'image cible.
	 * @param height Hauteur de l'image cible.
	 * @return Image redimensionnÃ©e.
	 */
	public static BufferedImage scale(Image source, int width, int height) {
		/*
		 * On crÃ©Ã© une nouvelle image aux bonnes dimensions.
		 */
		BufferedImage buf = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		if (source.getWidth(null) != width || source.getHeight(null) != height) {
			/*
			 * On dessine sur le Graphics de l'image bufferisÃ©e.
			 */
			Graphics2D g = buf.createGraphics();
			g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
			g.drawImage(source, 0, 0, width, height, null);
			g.dispose();
		}
		/*
		 * On retourne l'image bufferisÃ©e, qui est une image.
		 */
		return buf;
	}
}
