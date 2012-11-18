package moteur;

import java.io.Serializable;

/**
 *
 * @author Kévin
 */
public abstract class Obstacle implements Serializable{
	int typeObstacle;
	
	public Obstacle(int typeObstacle){
		this.typeObstacle = typeObstacle;
	}
	
	public void setTypeObstacle(int typeObstacle){
		this.typeObstacle = typeObstacle;
	}
	
	public int getTypeObstacle(){
		return typeObstacle;
	}
	
	
	public abstract boolean isDestructible();
}
