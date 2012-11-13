package moteur;

/**
 *
 * @author Kévin
 */
public abstract class Pion  {
	private static final int CHANCEMIN = 20;
	private static final int CHANCEMAX = 30;
	
	protected int vie;
	protected int force;
	protected int precision;
	protected int vitesse;
	protected int defense;
	protected int chance;
	protected int portee;
	protected int mouvement;
	
	protected Case c;
	
	public Pion(int vie, int force, int precision, int vitesse, int defense, int portee, int mouvement, Case c) {
		this.vie = vie;
		this.force = force;
		this.precision = precision;
		this.vitesse = vitesse;
		this.defense = defense;
		this.chance = (int)(Math.random() * (CHANCEMAX - CHANCEMIN + 1) + CHANCEMIN);
		this.portee = portee;
		this.mouvement = mouvement;
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
	public void attaquerPion(Pion p) {
		//TODO
	}
	
	public void deplacerPion(Case c) {
		//TODO
	}
	
	/**
	 * 
	 * @param c 
	 */
	
	public abstract String getNom();
	
	
}
