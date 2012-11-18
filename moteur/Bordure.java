/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package moteur;

/**
 *
 * @author Kévin
 */
public final class Bordure {
	private int typeBordure;
	
	public Bordure(int typeBordure){
		this.typeBordure = typeBordure;
	}
	
	public void setTypeBordure(int typeBordure){
		this.typeBordure = typeBordure;
	}
	 public int getTypeBordure(){
		 return typeBordure;
	 }
}
