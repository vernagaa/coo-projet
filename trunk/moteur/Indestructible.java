package moteur;

/**
 *
 * @author Kévin
 */
public final class Indestructible extends Obstacle {

	public Indestructible(int typeObstacle) {
		super(typeObstacle);
	}

	@Override
	public boolean isDestructible() {
		return false;
	}
}
