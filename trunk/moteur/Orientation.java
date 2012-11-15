package moteur;

/**
 *
 * @author Kévin
 */
public enum Orientation {
	NORD(1),
	OUEST(2),
	SUD(-1),
	EST(-2);
	
    private final int valeur;
 
    Orientation(int valeur){ 
		this.valeur = valeur; 
	}
	
	public boolean equals(Orientation o){
		return this == o;
	}
	
	public boolean equalsOp(Orientation o){
		return this.getValeur() == -o.getValeur();
	}
	
	private int getValeur(){
		return valeur;
	}
 
}
