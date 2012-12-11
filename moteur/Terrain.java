package moteur;

/**
 *
 * @author disavinr
 */
public class Terrain {
	public static int CoutDefaut = 2;

	public static double effetDeplacement(int i) {
		switch (i) {
			case Textures.NEIGE:
			case Textures.SABLE:
				return CoutDefaut * 2;
			case Textures.ROUTE:
				return CoutDefaut * 3/4;
			case Textures.GLACE:
				return CoutDefaut * 1/2;
			case Textures.HERBE:
			case Textures.ROC:
			default:
				return CoutDefaut;
		}
	}

	public static int effetPrecision(int i) {
		switch (i) {
			case Textures.GLACE:
			case Textures.SABLE:
				return -2;
			case Textures.NEIGE:
			case Textures.ROUTE:
			case Textures.HERBE:
			case Textures.ROC:
			default:
				return 0;
		}
	}

	public static int effetSoin(int i) {
		switch (i) {
			case Textures.NEIGE:
			case Textures.GLACE:
			case Textures.SABLE:
			case Textures.ROUTE:
			case Textures.HERBE:
			case Textures.ROC:
			default:
				return 0;
		}
	}
}
