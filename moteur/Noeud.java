/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package moteur;

import java.util.ArrayList;

/**
 *
 * @author chappelk
 */
public class Noeud {
    public Case c;
    public int cout;
    public ArrayList<Noeud> listeNoeud;
    
    public Noeud(Case c, int cout){
        this.c = c;
        this.cout = cout;
        listeNoeud = new ArrayList<Noeud>();
    }
}
