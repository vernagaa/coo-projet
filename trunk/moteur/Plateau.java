package moteur;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import moteur.familles.felin.*;
import moteur.familles.oiseau.*;
import moteur.familles.reptile.*;

/**
 *
 * @author Kévin
 */
public class Plateau implements Serializable {

    protected static final long serialVersionUID = 1L;
    /**
     * Nombre de colonne dans le plateau
     */
    private int colonne = 35;
    /**
     * Nombre de ligne dans le plateau
     */
    private int ligne = 20;
    /**
     * Tableau de Cases symbolisant la plateau.
     */
    private Case[][] plateau;
    private Terrain terrain;

    /**
     * Constructeur de la classe Plateau.
     */
    public Plateau() {
        super();
        plateau = new Case[ligne][colonne];
        /*
         * Initialisation des cases blanches
         */
        for (int i = 0; i < ligne; i++) {
            for (int j = 0; j < colonne; j++) {
                plateau[i][j] = new Case(i, j, this);
            }
        }
    }

    /**
     * Constructeur avec une carte prédéfinie
     *
     * @param f
     */
    public Plateau(String str) {
        plateau = new Case[ligne][colonne];
        try {
            FileInputStream fis = new FileInputStream(str);
            ObjectInputStream ois = null;
            ois = new ObjectInputStream(fis);
            Plateau p = (Plateau) ois.readObject();
            for (int i = 0; i < plateau.length; i++) {
                for (int j = 0; j < plateau[i].length; j++) {
                    plateau[i][j] = new Case(i, j, this);
                    plateau[i][j].setBordure(p.get(i, j).getBordure());
                    plateau[i][j].setObstacle(p.get(i, j).getObstacle());
                    plateau[i][j].setTypeTerrain(p.get(i, j).getTypeTerrain());
                }
            }
            ois.close();
            fis.close();

//            //XXX Pions test à supprimer
//			new ArcherFelin(plateau[10][12]);
//			new AssassinFelin(plateau[10][13]);
//			new GuerrierFelin(plateau[10][14]);
//			new TacticienFelin(plateau[10][15]);
//			new TankFelin(plateau[10][16]);
//
//			new ArcherOiseau(plateau[11][12]);
//			new AssassinOiseau(plateau[11][13]);
//			new GuerrierOiseau(plateau[11][14]);
//			new TacticienOiseau(plateau[11][15]);
//			new TankOiseau(plateau[11][16]);
//
//			new ArcherReptile(plateau[12][12]);
//			new AssassinReptile(plateau[12][13]);
//			new GuerrierReptile(plateau[12][14]);
//			new TacticienReptile(plateau[12][15]);
//			new TankReptile(plateau[12][16]);

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Plateau.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Plateau.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Retourne la case situé à la même position, c'est-à-dire même ligne et
     * même colonne.
     *
     * @param c Case qui est retournée
     * @return Retourne la case c.
     */
    public Case get(Case c) {
        if (c != null) {
            return plateau[c.getLigne()][c.getColonne()];
        }
        return null;
    }

    /**
     * Retourne la case se situant à la ligne l,et à la colonne c.
     *
     * @param l ligne de la case.
     * @param c colonne de la case.
     * @return Case situé en position l,c
     */
    public Case get(int l, int c) {
        //Arguments sont valides
        if (l >= 0 && l < ligne && c >= 0 && c < colonne) {
            return plateau[l][c];
        }

        return null;

    }

    public Case[][] get() {
        return plateau;
    }

    /**
     * Supprime les pieces se situant sur la plateau.
     *
     */
    public void vider() {
        for (int i = 0; i < ligne; i++) {
            for (int j = 0; j < colonne; j++) {
                get(i, j).remove();
            }
        }
    }

    public int getNbColonne() {
        return colonne;
    }

    public int getNbLigne() {
        return ligne;
    }
}
