package moteur;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JComponent;

/**
 *
 * @author Kévin
 */
public class Plateau extends JComponent {
	
	/**
	 * Nombre de colonne dans le plateau
	 */
    private static int colonne = 20;
	
	/**
	 * Nombre de ligne dans le plateau
	 */
	private static int ligne = 12;
	
    /**
     * Tableau de Cases symbolisant la plateau.
     */
    private Case[][] plateau;
    
    /**
     * Constructeur de la classe Plateau.
     */
    public Plateau() {
        super();
        this.setPreferredSize(new Dimension((colonne)*Case.TAILLE,(ligne)*Case.TAILLE));
        plateau = new Case[ligne][colonne];
        /* Initialisation des cases blanches */
        for(int i=0;i<ligne;i++) {
            for(int j=0;j<colonne;j++) {
				if(i == ligne-1 || j == colonne-1){
					plateau[i][j] = new Case(i,j,this, true);
				}
				else
					plateau[i][j] = new Case(i,j,this, false);
                get(i,j).setBounds(j*Case.TAILLE,i*Case.TAILLE,Case.TAILLE,Case.TAILLE);
                add(get(i,j));
            }
        }
    }
	
    /**
     * Retourne la case situé à la même position, c'est-à-dire même ligne et même colonne. 
     * @param c Case qui est retournée
     * @return Retourne la case c.
     */
    public Case get(Case c) {
        if(c!=null) {
            return plateau[c.getLigne()][c.getColonne()];
        }
        return null;
    }
    
    /**
     * Retourne la case se situant à la ligne l,et à la colonne c.
     * @param l ligne de la case.
     * @param c colonne de la case.
     * @return Case situé en position l,c
     */
    public Case get(int l, int c) {
        //Arguments sont valides
        if(l>=0 && l<ligne && c>=0 && c<colonne)
            return plateau[l][c];
        
        return null;
        
    }
    
    /**
     * Méthode retournant la hauteur du plateau
     * @return hauteur du plateau
     */
	@Override
    public int getHeight() {
        return ligne*Case.TAILLE;
    }
    
    /**
     * Retourne la largeur du tableau
     * @return largeur du tableau
     */
	@Override
    public int getWidth() {
        return colonne*Case.TAILLE;
    }
	
    
    /**
     * Supprime les pieces se situant sur la plateau.
     *
     */
    public void vider() {
        for(int i=0;i<ligne;i++)
            for(int j=0;j<colonne;j++)
                get(i,j).remove();
    }
	
	
	@Override
    public void paintComponent(Graphics g){
		for(int i=0;i<ligne;i++)
            for(int j=0;j<colonne;j++)
				plateau[i][j].repaint();
	}
}
