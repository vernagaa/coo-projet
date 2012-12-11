package moteur;

/**
 *
 * @author Kévin
 */
public final class Destructible extends Obstacle {

	private final static long serialVersionUID = -6990856900230380003L;
	private int vie;

	/**
	 * 
	 * @param typeObstacle
	 * @param vie
	 */
	public Destructible(int typeObstacle, int vie) {
		super(typeObstacle);
		this.vie = vie;
	}

	/**
	 * Renvoie que le l'obstacle est destructible
	 * @return
	 */
	@Override
	public boolean isDestructible() {
		return true;
	}

	/**
	 * Renvoie la vie de l'obstacle destructible
	 * @return 
	 */
	public int getVie() {
		return vie;
	}

	/**
	 * Décrémente la vie de l'obstacle
	 * @param vie 
	 */
	public void setVie(int vie) {
		this.vie -= vie;
	}
	
	
}
