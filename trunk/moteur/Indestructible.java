/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
