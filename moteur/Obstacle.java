package moteur;

import java.awt.image.BufferedImage;
import java.io.Serializable;

/**
 *
 * @author Kévin
 */
public abstract class Obstacle implements Serializable {

	private int typeObstacle;
	/**
	 * ID de serializable
	 */
	protected static final long serialVersionUID = -3397531558194008923L;

	/**
	 * Constructeur d'obstacle
	 * @param typeObstacle
	 */
	public Obstacle(int typeObstacle) {
		this.typeObstacle = typeObstacle;
	}

	/**
	 * Affecteur du type d'obstacle
	 * @param typeObstacle
	 */
	public void setTypeObstacle(int typeObstacle) {
		this.typeObstacle = typeObstacle;
	}

	/**
	 * Renvoie le type d'obstacle
	 * @return
	 */
	public int getTypeObstacle() {
		return typeObstacle;
	}

	/**
	 * Renvoie l'image de l'obstacle
	 * @return
	 */
	public BufferedImage getImage() {
		return Textures.getObstacle(typeObstacle);
	}
	
	/**
	 * Renvoie si c'est un chateau ou non
	 * @return
	 */
	public boolean isChateau(){
		return false;
	}

	/**
	 * Renvoie s'il est destructible ou non
	 * @return
	 */
	public boolean isDestructible() {
		return false;
	}
}
