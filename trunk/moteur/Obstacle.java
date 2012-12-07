package moteur;

import java.awt.image.BufferedImage;
import java.io.Serializable;

/**
 *
 * @author Kévin
 */
public abstract class Obstacle implements Serializable {

	int typeObstacle;
	protected static final long serialVersionUID = -3397531558194008923L;

	public Obstacle(int typeObstacle) {
		this.typeObstacle = typeObstacle;
	}

	public void setTypeObstacle(int typeObstacle) {
		this.typeObstacle = typeObstacle;
	}

	public int getTypeObstacle() {
		return typeObstacle;
	}

	public BufferedImage getImage() {
		return Textures.getObstacle(typeObstacle);
	}
	
	public boolean isChateau(){
		return false;
	}

	public abstract boolean isDestructible();
}
