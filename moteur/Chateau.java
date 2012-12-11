package moteur;

/**
 *
 * @author remi
 */
public final class Chateau extends Indestructible {

	/**
	 * ID de serrializable
	 */
	protected final static long serialVersionUID = 2305011556547037810L;
	private int conquerir;
	private boolean conquis;
	private boolean joueur1;

	/**
	 * Constructeur d'un chateau
	 * @param typeObstacle
	 * @param joueur1
	 */
	public Chateau(int typeObstacle, boolean joueur1) {
		super(typeObstacle);
		this.joueur1 = joueur1;
		conquerir = 3;
		conquis = false;
	}

	/**
	 * Décrémente de un la conquete
	 * Si conquérir vaut 0 alors le chateau est conquis
	 * pré-cond : conquerir >=0
	 */
	public void conquerir() {
		conquerir--;
		if (conquerir == 0) {
			conquis = true;
		}

	}

	/**
	 * Renvoie si le chateau est conquis ou non
	 * @return
	 */
	public boolean isConquis() {
		return conquis;
	}

	/**
	 * Renvoie qu'il est un chateau
	 * @return
	 */
	@Override
	public boolean isChateau() {
		return true;
	}

	/**
	 * Renvoie l'apartenance du chateau
	 * @return 
	 */
	boolean isJoueur1() {
		return joueur1;
	}

	/**
	 * Accesseur de conquerir
	 * @return
	 */
	public int getConquerir() {
		return conquerir;
	}
}
