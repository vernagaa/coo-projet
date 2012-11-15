package moteur;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author disavinr
 */
public class Terrain {
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
	
	private BufferedImage[] images = new BufferedImage[5];

	public Terrain() {
		images[ROC] = new BufferedImage(Case.TAILLE, Case.TAILLE, BufferedImage.TYPE_INT_ARGB);
		images[EAU] = new BufferedImage(Case.TAILLE, Case.TAILLE, BufferedImage.TYPE_INT_ARGB);
		images[ROUTE] = new BufferedImage(Case.TAILLE, Case.TAILLE, BufferedImage.TYPE_INT_ARGB);
		images[SABLE] = new BufferedImage(Case.TAILLE, Case.TAILLE, BufferedImage.TYPE_INT_ARGB);
		images[HERBE] = new BufferedImage(Case.TAILLE, Case.TAILLE, BufferedImage.TYPE_INT_ARGB);
		
		try {
			images[ROC] = ImageIO.read(getClass().getResource(ROCPATH));
		} catch (IOException ex) {
			System.err.println("Image non trouvée : " + ROCPATH);
		}
		
		try {
			images[EAU] = ImageIO.read(getClass().getResource(EAUPATH));
		} catch (IOException ex) {
			System.err.println("Image non trouvée : " + EAUPATH);
		}
		
		try {
			images[ROUTE] = ImageIO.read(getClass().getResource(ROUTEPATH));
		} catch (IOException ex) {
			System.err.println("Image non trouvée : " + ROUTEPATH);
		}
		
		try {
			images[SABLE] = ImageIO.read(getClass().getResource(SABLEPATH));
		} catch (IOException ex) {
			System.err.println("Image non trouvée : " + SABLEPATH);
		}
		
		try {
			images[HERBE] = ImageIO.read(getClass().getResource(HERBEPATH));
		} catch (IOException ex) {
			System.err.println("Image non trouvée : " + HERBEPATH);
		}
	}
	
	public BufferedImage getImage(int numTerrain) {
		return images[numTerrain];
	}
}
