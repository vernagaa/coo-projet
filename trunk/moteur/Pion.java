/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package moteur;

import moteur.Case;
import moteur.Element;

/**
 *
 * @author Kévin
 */
public abstract class Pion extends Element{
	
	short attaque;
	
	short depl;
	
	Case c;
	
	public Pion(int vie, int armure, short attaque, short depl, Case c){
		super(vie, armure);
		this.attaque = attaque;
		this.depl = depl;
		this.c = c;
	}
	
	/**
	 * Pré - condition : La case c contient un élément, 2 cas :
	 *		- Soit c'est un obstacle destructible
	 *		- Soit c'est pion adverse
	 * @param c une case du plateau contenant un element
	 * Post - condition :
	 *		- L'élément de la case c a perdu de la vie.
	 */
	public abstract void attaquerPion(Case c);
	
	/**
	 * 
	 * @param c 
	 */
	public abstract void seDeplacer(Case c);
	
	
	
}
