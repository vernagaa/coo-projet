package moteur;

import ihm.FenetrePrincipale;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;

/**
 *
 * @author Kévin
 */
public class Moteur implements Runnable, Serializable {

    FenetrePrincipale fp;
    Plateau plateau;

    public Plateau getPlateau() {
        return plateau;
    }

    public void setPlateau(Plateau plateau) {
        this.plateau = plateau;
    }

    public Moteur() {
        try {
            FileReader fr = new FileReader("map/map1.map");
            plateau = new Plateau(fr);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Moteur.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Moteur());
    }

    @Override
    public void run() {
        fp = new FenetrePrincipale(this);
    }
}
