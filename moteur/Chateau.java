package moteur;

/**
 *
 * @author remi
 */
public final class Chateau extends Indestructible {

	protected final static long serialVersionUID = 2305011556547037810L;
	private int conquerir;
	public boolean conquis;
	private boolean joueur1;

	public Chateau(int typeObstacle, boolean joueur1) {
		super(typeObstacle);
		this.joueur1 = joueur1;
		conquerir = 3;
		conquis = false;
	}

	public void conquerir() {
		conquerir--;
		if (conquerir == 0) {
			conquis = true;
		}

	}

	public boolean isConquis() {
		return conquis;
	}

	@Override
	public boolean isChateau() {
		return true;
	}

	boolean isJoueur1() {
		return joueur1;
	}
}
