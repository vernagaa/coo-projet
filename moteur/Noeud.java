package moteur;

import java.util.ArrayList;

/**
 *
 * @author chappelk
 */
public class Noeud {

	private Case c;
	private double cout;
	private ArrayList<Noeud> listeNoeud;

	/**
	 * Constructeur de noeud
	 * @param c
	 * @param cout
	 */
	public Noeud(Case c, double cout) {
		this.c = c;
		this.cout = cout;
		listeNoeud = new ArrayList<Noeud>();
	}
	
	@Override
	public String toString(){
		return "{"+c.toString()+" "+cout+"}";
	}

	/**
	 * Accesseur de c
	 * @return 
	 */
	public Case getC() {
		return c;
	}

	/**
	 * Accesseur de Cout
	 * @return 
	 */
	public double getCout() {
		return cout;
	}

	/**
	 * Accesseur ListeNoeud
	 * @return 
	 */
	public ArrayList<Noeud> getListeNoeud() {
		return listeNoeud;
	}

	/**
	 * Affecteur Cout
	 * @param cout 
	 */
	public void setCout(double cout) {
		this.cout = cout;
	}

	/**
	 * Affecteur ListeNoeud
	 * @param listeNoeud 
	 */
	public void setListeNoeud(ArrayList<Noeud> listeNoeud) {
		this.listeNoeud = listeNoeud;
	}
	
	
}
