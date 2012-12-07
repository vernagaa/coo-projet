package moteur;

/**
 *
 * @author Kévin
 */
public class Indestructible extends Obstacle {

	protected final static long serialVersionUID = -4286231189299464343L;
	public Indestructible(int typeObstacle) {
		super(typeObstacle);
	}

	@Override
	public boolean isDestructible() {
		return false;
	}
}
