package moteur;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JComponent;

/**
 *
 * @author Kévin
 */
public class Case extends JComponent implements MouseListener{
	/**
	 * Constante qui défiint la taille en pixel de la case
	 */
	public static final int TAILLE = 30;
	
	/**
	 * Entier contenant la colonne où se trouve la case
	 */
	protected int col;
		
	/**
	 * Entier contenant la ligne où se trouve la case
	 */
	protected int lig;
	
	/**
	 * Représente le plateau sur lequel se trouve la case
	 */
	protected Plateau plateau;
	
	/**
	 *Cette variable permet de mettre la case en surbrillance dans 2 cas :
	 *		- Si la case est selectionné
	 *		- Si la case est accessible est survolé
	 */
	protected boolean select;
	
	/**
	 * Variable contenant l'element présent sur la case, 3 cas :
	 *		- null, signifie qu'il n'y a pas d'élément
	 *		- Pion, signifie qu'il y a un personnage jouable
	 *		- Obstacle, signifie qu'il y a un obsatcle non jouable
	 */
	protected Element element;
	
	protected Color color;
	
	protected boolean bordure;
	
	/**
	 * Constructeur de base de la classe Case.
	 * Prend 3 paramètres :
	 *		param l entier désignant la ligne
	 *		param c entier désignant la colonne
	 *		param terrain où se trouve la case
	 */
	public Case(int l, int c, Plateau terrain, boolean bordure){
		super();
		lig = l;
		col = c;
		plateau = terrain;
		select = false;
		element = null;
		color = Color.WHITE;
		this.bordure = bordure;
		addMouseListener(this);
	}
	/**
	 * Constructeur prenant une case en paramètre
	 * Nécessaire afin de copier une case
	 * @param c
	 */
	private Case(Case c){
		super();
		lig = c.lig;
		col = c.col;
		plateau = c.plateau;
		select = c.select;
		element = c.element;
		bordure = c.bordure;
	}

	
	/**
	 * Méthode qui permet l'ajout d'un élément à la case
	 */
	@Override
    public Component add(Component comp) {
        return super.add(comp);
    }
	
	/**
	 * Méthode qui compare 2 case grace à la ligne et à la colonne
	 * @param c la case à comparé
	 * @return false si les cases sont différentes, sinon true
	 */
	    public boolean compare(Case c) {
        if(c.getLigne() == lig && c.getColonne() == col)
            return true;
        return false;
    }

	/**
	 * Méthode abstraite qui retourne la copie d'une case
	 * @param p le plateau où est la case
	 * @return Case copie de this
	 */
	/*
    public Case copie(Plateau p){
		return Case(this);
	}
	*/ 
		
    /**
     * Retourne le numéro de la colonne de la case
     * @return Colonne de la case
     */
    public int getColonne() {
        return col;
    }
    
    /**
     * Retourne le numéro de la ligne de la case
     * @return Ligne de la case
     */
    public int getLigne() {
        return lig;
    }
    
    /**
     * Méthode abstraite renvoyant la <code>pièce se trouvant sur la <code>case</code>.
     * @return <code>Pièce</code> se trouvant sur la <code>case</code>, null s'il n'y a pas de <code>pièce</code> dessus
     */
    public Element getElement(){
		return element;
	}
    
    /**
     * Retourne le plateau
     * @return Plateau où est la case
     */
    public Plateau getPlateau() {
        return plateau;
    }
    
    /**
	 * 
     */
	@Override
    public void paintComponent(Graphics g){
		Graphics2D gr = (Graphics2D)g;
		gr.setColor(Color.BLACK);
		gr.fillRect(0,0,Case.TAILLE,Case.TAILLE);
		gr.setColor(color);
		if(bordure){
			if(plateau.getHeight()/TAILLE == lig+1 && plateau.getWidth()/TAILLE == col+1){
				gr.fillRect(1,1,Case.TAILLE-2,Case.TAILLE-2);
			} else if(plateau.getHeight()/TAILLE == lig+1){
				gr.fillRect(1,1,Case.TAILLE-1,Case.TAILLE-2);
			} else if(plateau.getWidth()/TAILLE == col+1){
				gr.fillRect(1,1,Case.TAILLE-2,Case.TAILLE-1);
			}
			
		}else if(!bordure){
			gr.fillRect(1,1,Case.TAILLE-1,Case.TAILLE-1);
		}
	}
    
    /**
     * Méthode qui enlève l'élément se trouvant sur la case.
     *
     */
    public  void remove(){
		element = null;
	}
    
    
    /**
     * Méthode permettant de sélectionner une case
     */
    public void setSelect(boolean b){
		select = b;
	}
	
    /**
     * Afficher le couple (ligne,colonne) de la case
     */
	@Override
    public String toString() {
        return lig + "," + col;
    }

	@Override
	public void mouseClicked(MouseEvent e) {
//		System.out.println(toString());
	}

	@Override
	public void mousePressed(MouseEvent e) {
		System.out.println(toString());
		color = Color.RED;
		repaint();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
//		System.out.println("BLIBLI");
	}

	@Override
	public void mouseEntered(MouseEvent e) {
//		System.out.println("Lalalala");
	}

	@Override
	public void mouseExited(MouseEvent e) {
//		System.out.println("Exited");
	}
	

}
