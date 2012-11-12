/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ihm;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.*;
import moteur.Plateau;

/**
 *
 * @author Kévin
 */
public class DisgaeaWindow extends JFrame{

	 /**
     * Hauteur de la fenêtre
     */
    private static final float hauteur = (float) 2/3;
    /**
     * Largeur de la fenêtre
     */
    private static final float largeur = (float) 2/3;
    /**
     * Position X de la fenêtre
     */
    private static final float posX = (float) 1/6;
    /**
     * Position Y de la fenêtre
     */
    private static final float posY = (float) 1/6;
	
	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		DisgaeaWindow jeuWindow = new DisgaeaWindow();
		jeuWindow.setVisible(true);
	}
	
	 /**
     * JPanel où est dessiné les composants de la fenêtre
     */
    private JPanel pane = null;
    /**
     * Plateau représentant la damier
     * 
     */
    private Plateau plateau;
	
	 public DisgaeaWindow() {
        //Initialisation de la fenêtre
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Jeu de Dames");
        
        //Taille et position de la fenetre
        Toolkit k = Toolkit.getDefaultToolkit();
        Dimension tailleEcran = k.getScreenSize();
        int largeurEcran = tailleEcran.width;
        int hauteurEcran = tailleEcran.height;
        setSize((int) (largeurEcran*hauteur), (int) (hauteurEcran*largeur));
        setLocation((int) (largeurEcran*posX), (int) (hauteurEcran*posY));
        
        //Menu
        JMenuBar menuBar = new JMenuBar();
        JMenu mFichier = new JMenu("Fichier");
        mFichier.setMnemonic(KeyEvent.VK_F);
        
       //Item Nouveau
        JMenuItem iNouveau = new JMenuItem("Nouvelle Partie",KeyEvent.VK_N);
        iNouveau.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N,java.awt.event.InputEvent.CTRL_MASK));
        iNouveau.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent ae) {nouvellePartie();}
                });
        
        mFichier.add(iNouveau);
		
        //Ajout de tous les éléments
        menuBar.add(mFichier);
        
        //Ajout du Menu
        getRootPane().setJMenuBar(menuBar);
        
        
        pane = new JPanel();
        pane.setLayout(new GridBagLayout());
        
        // Création du plateau
        plateau = new Plateau();
        
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.NONE;
        c.gridx = 0;
        c.gridy= 0;
        /*c.weightx = 0.5;
         c.weighty = 0.5;*/
        c.gridheight = 2;
        
        pane.add(plateau,c);
        setContentPane(pane);
        
    }

    public void nouvellePartie() {
        NouvellePartieWindow2 nouvellePartieWindow2 = new NouvellePartieWindow2(this, true);
    }
}
