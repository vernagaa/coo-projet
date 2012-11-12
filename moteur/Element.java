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
	
	@Override
	public abstract void paintComponent(Graphics g);
	
}
