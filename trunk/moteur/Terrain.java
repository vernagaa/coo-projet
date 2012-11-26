package moteur;

/**
 *
 * @author disavinr
 */
public class Terrain {
	public static int effetDeplacement(int i){
		switch(i){
			case Textures.NEIGE:
			case Textures.SABLE:
				return -2;
			case Textures.ROUTE:
				return +2;
			case Textures.GLACE:
				return +4;
			case Textures.HERBE:
			case Textures.ROC:
			default:
				return 0;
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
