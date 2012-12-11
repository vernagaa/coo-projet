package moteur;

/**
 *
 * @author Kévin
 */
public enum Orientation {

	/**
	 * Orientation nord
	 */
	NORD(1),
	/**
	 * orientation ouest
	 */
	OUEST(2),
	/**
	 * orientation sud
	 */
	SUD(-1),
	/**
	 * orientation est
	 */
	EST(-2);
	private final int valeur;

	Orientation(int valeur) {
		this.valeur = valeur;
	}

	/**
	 * Renvoie vrai si o est la meme orientation que this
	 * @param o
	 * @return
	 */
	public boolean equals(Orientation o) {
		return this == o;
	}

	/**
	 * Renvoie si o est l'orientation opposée
	 * @param o
	 * @return
	 */
	public boolean equalsOp(Orientation o) {
		return this.valeur == -o.valeur;
	}

}
