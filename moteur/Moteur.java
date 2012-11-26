package moteur;

import ihm.AireDeJeu;
import ihm.FenetrePrincipale;
import java.io.*;
import javax.swing.SwingUtilities;

/**
 *
 * @author Kévin
 */
public class Moteur implements Runnable, Serializable {

    private FenetrePrincipale fp;
    private Plateau plateau;
    private AireDeJeu aireDeJeu;
    
    private Case caseCourante;
    private Case caseDebutMouvement;
    boolean mouvement;

    public Plateau getPlateau() {
        return plateau;
    }

    public void setPlateau(Plateau plateau) {
        this.plateau = plateau;
    }

    public Moteur() {
        plateau = new Plateau("map/map2.map");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Moteur());
    }

    @Override
    public void run() {
        fp = new FenetrePrincipale(this);
        aireDeJeu = fp.getAireDeJeu();
    }

    public void caseCliqueBoutonGauche(Case c1) {
        if (caseCourante != null) {
            caseCourante.setSelect(false);
        }
        
        caseCourante = c1;
        
//        Selectionne un Pion
        if(c1.getPion() != null){
            mouvement = true;
            caseCourante.setSelect(true);
            caseDebutMouvement = caseCourante;
        }
        
        aireDeJeu.repaint();
    }
}
