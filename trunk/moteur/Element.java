/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package moteur;

import java.awt.Graphics;
import javax.swing.JComponent;

/**
 *
 * @author Kévin
 */
public abstract class Element extends JComponent{
	protected int vie;
	protected int armure;
	
	public Element(int vie, int armure){
		this.vie = vie;
		this.armure = armure;
	}
	
	public void diminuerVie(int val){
		vie = vie - val;
	}
	
	public int getVie(){
		return vie;
	}
	
	public abstract void paintComponent(Graphics g);
	
}
