/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package moteur;

/**
 *
 * @author Kévin
 */
public final class Destructible extends Obstacle{
	int vie;
	
	public Destructible(int typeObstacle, int vie){
		super(typeObstacle);
		this.vie = vie;
	}
	
	@Override
	public boolean isDestructible() {
		return true;
	}
	
}
