package moteur;

import exception.MapException;
import java.io.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kévin
 */
public class Plateau implements Serializable {

	protected static final long serialVersionUID = 1L;
	/**
	 * Nombre de colonne dans le plateau
	 */
	public final static int NB_COLONNE = 35;
	/**
	 * Nombre de ligne dans le plateau
	 */
	public final static int NB_LIGNE = 20;
	/**
	 * Tableau de Cases symbolisant la plateau.
	 */
	private Case[][] plateau;

	/**
	 * Constructeur de la classe Plateau.
	 */
	public Plateau() {
		super();
		plateau = new Case[NB_LIGNE][NB_COLONNE];
		/*
		 * Initialisation des cases blanches
		 */
		for (int i = 0; i < NB_LIGNE; i++) {
			for (int j = 0; j < NB_COLONNE; j++) {
				plateau[i][j] = new Case(i, j, this);
			}
		}
	}

	/**
	 * Constructeur avec une carte donnée
	 *
	 * @param f
	 */
	public Plateau(String str) {
		try {
			plateau = new Case[NB_LIGNE][NB_COLONNE];
			init(str);
		} catch (MapException ex) {}
	}

	public final void init(String str) throws MapException{
		try {
			FileInputStream fis = new FileInputStream(str);
			ObjectInputStream ois = new ObjectInputStream(fis);
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

		} catch (ClassNotFoundException ex) {
			Logger.getLogger(Plateau.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IOException ex) {
				throw new MapException(str);
		}
	}

	/**
	 * Retourne la case située à la même position, c'est-à-dire même ligne et
	 * même colonne.
	 *
	 * @param c Case qui est retournee
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
	 * @return Case située en position l,c
	 */
	public Case get(int l, int c) {
		//Arguments sont valides
		if (l >= 0 && l < NB_LIGNE && c >= 0 && c < NB_COLONNE) {
			return plateau[l][c];
		}

		return null;

	}

	/**
	 * Renvoie le plateau
	 * @return 
	 */
	public Case[][] get() {
		return plateau;
	}

	/**
	 * Renvoie la liste des chateau trouvés sur la carte
	 * @return 
	 */
	public ArrayList<Case> listeChateaux(){
		ArrayList<Case> l = new ArrayList<Case>();
		for(int i=0;i<NB_COLONNE;i++){
			for(int j=0;j<NB_LIGNE;j++){
				if(plateau[j][i].getObstacle()!=null && plateau[j][i].getObstacle().isChateau()){
					l.add(plateau[j][i]);
				}
			}
		}
		return l;
	}
}
