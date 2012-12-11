package moteur;

/**
 *
 * @author disavinr
 */
public class Terrain {
	/**
	 * Cout par défaut d'un terrain
	 */
	public static int CoutDefaut = 2;

	/**
	 * Effet du terrain sur les déplacement
	 * @param i
	 * @return
	 */
	public static double effetDeplacement(int i) {
		switch (i) {
			case Textures.NEIGE:
			case Textures.SABLE:
				return CoutDefaut * 1.5;
			case Textures.ROUTE:
				return CoutDefaut * 0.75;
			case Textures.GLACE:
				return CoutDefaut * 0.5;
			case Textures.HERBE:
			case Textures.ROC:
			default:
				return CoutDefaut;
		}
	}
}
