package moteur;

import java.io.Serializable;

/**
 *
 * @author Kévin
 */
public final class Bordure implements Serializable {

	/**
	 * ID de serrializable
	 */
	protected static final long serialVersionUID = 1L;
	private int typeBordure;

	/**
	 * Constructeur de bordure
	 * @param typeBordure
	 */
	public Bordure(int typeBordure) {
		this.typeBordure = typeBordure;
	}

	/**
	 * Affecteur de typeBordure
	 * @param typeBordure
	 */
	public void setTypeBordure(int typeBordure) {
		this.typeBordure = typeBordure;
	}

	/**
	 * Accesseur de typeBordure
	 * @return
	 */
	public int getTypeBordure() {
		return typeBordure;
	}
}
