package moteur;

/**
 *
 * @author disavinr
 */
public class Terrain {
	public static double effetDeplacement(int i){
		//XXX à la base on avait : négatif pour effet ralenti, positif pour effet d'accélération
		switch(i){
			case Textures.NEIGE:
			case Textures.SABLE:
				return 3;
			case Textures.ROUTE:
				return 1.5;
			case Textures.GLACE:
				return 1.5;
			case Textures.HERBE:
			case Textures.ROC:
			default:
				return 2;
		}
	}
	public static int effetPrecision(int i){
		switch(i){
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
	public static int effetSoin(int i){
		switch(i){
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
