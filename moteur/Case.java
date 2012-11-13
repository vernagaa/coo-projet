package moteur;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
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
    protected boolean bordure;
    private BufferedImage img;
    private Pion pion;
    private Obstacle obstacle;

    /**
     * Constructeur de base de la classe Case.
     * Prend 3 paramètres :
     *		param l entier désignant la ligne
     *		param c entier désignant la colonne
     *		param terrain où se trouve la case
     */
    public Case(int l, int c, Plateau terrain, boolean bordure) {
        super();

        lig = l;
        col = c;
        plateau = terrain;
        select = false;
        pion = null;
        obstacle = null;
        this.bordure = bordure;
        addMouseListener(this);
        try {
            img = new BufferedImage(TAILLE, TAILLE, BufferedImage.TYPE_INT_ARGB);
            img = ImageIO.read(getClass().getResource("/Image/roc.png"));
        } catch (IOException ex) {
            Logger.getLogger(Case.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Constructeur prenant une case en paramètre
     * Nécessaire afin de copier une case
     * @param c
     */
    private Case(Case c) {
        super();
        lig = c.lig;
        col = c.col;
        plateau = c.plateau;
        select = c.select;
        bordure = c.bordure;
        pion = c.pion;
        obstacle = c.obstacle;
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
        if (c.getLigne() == lig && c.getColonne() == col) {
            return true;
        }
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
     * Méthode abstraite renvoyant le <code>pion se trouvant sur la <code>case</code>.
     * @return <code>Pion</code> se trouvant sur la <code>case</code>, null s'il n'y a pas de <code>pion</code> dessus
     */
    public Pion getPion(){
		return pion;
	}
    
    /**
     * Méthode abstraite renvoyant l'<code>obstacle se trouvant sur la <code>case</code>.
     * @return <code>Obstacle</code> se trouvant sur la <code>case</code>, null s'il n'y a pas d'<code>obstacle</code> dessus
     */
    public Obstacle getObstacle(){
		return obstacle;
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
    public void paintComponent(Graphics g) {
        Graphics2D gr = (Graphics2D) g;
        gr.drawImage(img, 0, 0, null);
        
        /*
         * Quadrillage
         * */
        gr.drawLine(0, 0, TAILLE, 0);
        gr.drawLine(TAILLE - 1, 0, TAILLE - 1, TAILLE);
        if (bordure) {
            if (col == 0) {
                gr.drawLine(0, 0, 0, TAILLE);
            } else {
                gr.drawLine(0, TAILLE-1, TAILLE-1, TAILLE-1);
            }
            if(col == 0 && lig == plateau.getHeight()/TAILLE-1){
                gr.drawLine(0, TAILLE-1, TAILLE-1, TAILLE-1);
            }
        }
    }

    /**
     * Méthode qui enlève l'élément se trouvant sur la case.
     *
     */
    public  void remove(){
		pion = null;
		obstacle = null;
	}
    
    /**
     * Méthode permettant de sélectionner une case
     */
    public void setSelect(boolean b) {
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
