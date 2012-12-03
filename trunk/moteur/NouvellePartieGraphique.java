/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package moteur;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author chappelk
 */
public class NouvellePartieGraphique extends JComponent implements MouseListener, MouseMotionListener {

    private final Moteur m;

//    http://bbclone.developpez.com/fr/java/tutoriels/uiswing/gridbaglayout/
//    Pour apprendre le gridBagLayout
    public NouvellePartieGraphique(Moteur m) {
        this.m = m;

        JLabel joueur1 = new JLabel("Joueur 1");
        joueur1.setForeground(Color.WHITE);
        joueur1.setBounds(140, 30, 60, 30);
        add(joueur1);

        JTextField nomJoueur1 = new JTextField("joueur 1");
        nomJoueur1.setBounds(70, 60, 180, 30);
        nomJoueur1.setMargin(new Insets(0, 5, 0, 0));
        add(nomJoueur1);
        
        JLabel felin1 = new JLabel();
        felin1.setIcon(new ImageIcon(Textures.getPersonnage(Textures.ASSASSINFELIN, Orientation.SUD)));
        felin1.setBounds(70, 90, 60, 60);
        add(felin1);
        





        m.aireDeJeu.add(this);
        setLocation(Case.TAILLE * 6, 0);
        setVisible(true);
        setSize(Case.TAILLE * 23, Case.TAILLE * 20);
    }

    public void paintComponent(Graphics g) {
        g.fillRect(0, 0, Case.TAILLE * 23, Case.TAILLE * 20);
        g.setColor(Color.GRAY);
        g.fillRect(Case.TAILLE * 11, 0, Case.TAILLE, Case.TAILLE * 20);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }
}
