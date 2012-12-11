package moteur;

import java.util.ArrayList;

/**
 *
 * @author chappelk
 */
public class Noeud {

	public Case c;
	public double cout;
	public ArrayList<Noeud> listeNoeud;

	public Noeud(Case c, double cout) {
		this.c = c;
		this.cout = cout;
		listeNoeud = new ArrayList<Noeud>();
	}
	
	@Override
	public String toString(){
		return "{"+c.toString()+" "+cout+"}";
	}
}
