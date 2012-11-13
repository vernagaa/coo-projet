package moteur;

/**
 *
 * @author Kévin
 */
public abstract class Pion extends Element {
	
	protected int attaque;
	protected int precision;
	protected int esquive;
	protected int coupCritique;
	protected int depl;
	
	protected Case c;
	
	public Pion(int vie, int armure, int attaque, int precision, int esquive, int coupCritique, int depl, Case c){
		super(vie, armure);
		this.attaque = attaque;
		this.precision = precision;
		this.esquive = esquive;
		this.coupCritique = coupCritique;
		this.depl = depl;
		this.c = c;
	}
	
	/**
	 * Pré - condition : La case c contient un élément, 2 cas :
	 *		- Soit c'est un obstacle destructible
	 *		- Soit c'est pion adverse
	 * @param c une case du plateau contenant un element
	 * Post - condition :
	 *		- L'élément de la case c a perdu de la vie.
	 */
	public abstract void attaquerPion(Case c);
	
	/**
	 * 
	 * @param c 
	 */
	public abstract void seDeplacer(Case c);
	
	public abstract String getNom();
	
	
}
