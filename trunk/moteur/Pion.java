package moteur;

import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Kévin
 */
public abstract class Pion implements Serializable {

    protected static final long serialVersionUID = 1L;
    /*
     * La partie Statistique
     */
    private static final int CHANCEMIN = 20;
    private static final int CHANCEMAX = 30;
    private static final int TAUXRIPOSTE = 95;
    protected int vie;
    protected int force;
    protected int precision;
    protected int vitesse;
    protected int defense;
    protected int chance;
    protected int portee;
    protected int mouvement;
    protected Orientation orientation;
    public ArrayList<Noeud> listeDeplacementPossible;
    public ArrayList<Case> listeAttaquePossible;
    public ArrayList<Case> listeAttaqueAire;
    private ArrayList<Case> deplacement;
    private Noeud noeudContenu;
    private Case c;
		

    public Pion(int vie, int force, int precision, int vitesse, int defense, int bonusChance, int portee, int mouvement, Case c) {
        this.vie = vie;
        this.force = force;
        this.precision = precision;
        this.vitesse = vitesse;
        this.defense = defense;
        this.chance = (int) (Math.random() * (CHANCEMAX - CHANCEMIN + 1) + CHANCEMIN) + bonusChance;
        this.portee = portee;
        this.mouvement = mouvement;
        this.c = c;
        c.getPlateau().get(c).setPion(this);
        if (c.getColonne() < 5) {
            orientation = Orientation.OUEST;
        } else {
            orientation = Orientation.EST;
        }
        listeDeplacementPossible = new ArrayList<Noeud>();
        deplacement = new ArrayList<Case>();
        listeAttaquePossible = new ArrayList<Case>();
        listeAttaqueAire = new ArrayList<Case>();

        //XXX orientation SUD pour les tests
        orientation = Orientation.SUD;
    }

    public void deplacerPion(Case c1) {
        System.out.println("Je suis en " + c.toString());
        mouvement -= distanceManhattan(c1);

        c.setPion(null);
        c1.setPion(this);
        c = c1;
		// orientation
		Case last = deplacement.get(deplacement.size()-2);
		if(c1.getColonne() - last.getColonne() > 0) {
			orientation = Orientation.EST;
		}
		else if(c1.getColonne() - last.getColonne() < 0) {
			orientation = Orientation.OUEST;
		}
		else if(c1.getLigne() - last.getLigne() > 0) {
			orientation = Orientation.SUD;
		}
		else if(c1.getLigne() - last.getLigne() < 0) {
			orientation = Orientation.NORD;
		}
        System.out.println("Je suis allé en " + c.toString() + " il me reste nb mouvement " + mouvement);
        listeAttaquePossible.clear();
        listeDeplacementPossible.clear();
        listeAttaqueAire.clear();
        deplacement.clear();
        //TODO Joueur.getPions().effacerTout();

    }

    /**
     *
     */
    public void attaquerPion(Pion p) {
        attaquerPion(p, TAUXRIPOSTE);
    }

    private void attaquerPion(Pion p, int tauxRiposte) {
        float orient = dosCoteFace(p);
        int degatInflige = (int) ((force + (int) (force * janken(p))) * orient);
        int seDefend = p.seDefendre(p);
        int hit = hit();
        int esquive = esquiveEnnemi(p);
        int aleaEsquive = (int) (Math.random() * (101));
        int aleaRiposte = (int) (Math.random() * (101));
        int aleaCoupCritique = (int) (Math.random() * (101));
        System.out.println(this.toString() + " attaque " + p.toString());
        System.out.println("Il a %chance de toucher " + (hit - esquive));
        if (esquive < 100 && aleaEsquive < (hit - esquive)) {
            System.out.println("%chance coup critique " + coupCritiques());
            if (aleaCoupCritique < coupCritiques()) {
                System.out.println("Coup Critique");
                System.out.println("J'attaque avec " + (1.2*degatInflige - seDefend));
                p.recevoirDegat((int) 1.2*degatInflige - seDefend);
            } else if (degatInflige > seDefend) {
                System.out.println("J'attaque avec " + (degatInflige - seDefend));
                p.recevoirDegat(degatInflige - seDefend);
            } else {
                System.out.println("Attaque de 1");
                p.recevoirDegat(1);
            }

            p.attaque();
            if (p.getListeAttaquePossible().contains(this.c)) {
                System.out.println("Riposte");
                if (aleaRiposte < tauxRiposte) {
                    System.out.println("Il riposte");
                    p.attaquerPion(this, tauxRiposte / 2);
                }
            }
        } else {
            System.out.println("J'esquive");
        }
    }

    private void recevoirDegat(int degatInflige) {
        vie -= degatInflige;
    }

    private int seDefendre(Pion p) {
        return defense + (int) (defense * cooperation());
    }

    /*
     * Les methodes suivantes sont destines a calculer les paramatres du combat.
     */
    protected abstract float janken(Pion p);

    private float dosCoteFace(Pion p) {
        if (orientation.equals(p.getOrientation())) {
            return 15 / 10;
        } else if (orientation.equalsOp(p.getOrientation())) {
            return 1;
        }
        return 12 / 10;
    }

    private int hit() {
        return precision * 4;
    }

    private int esquiveEnnemi(Pion p) {
        return ((int) (p.vitesse * 1.5) + p.chance) + (int) (p.cooperation() * ((int) (p.vitesse * 1.5) + p.chance) / 4)
                + (int) (janken(p) * ((int) (p.vitesse * 1.5) + p.chance) / 2) /*
                 * + (int) (p.getCase().getType.getBonus()*(p.vitesse * 1.5) +
                 * p.chance)/3)
                 */;
    }

    private float coupCritiques() {
        return precision * 4 * vitesse / 150;
    }

    private float cooperation() {
        int resultat = 0;
        if (c.getPlateau().get(c.getLigne() + 1, c.getColonne() + 1) != null) {
            resultat += 1;
        }
        if (c.getPlateau().get(c.getLigne() - 1, c.getColonne() + 1) != null) {
            resultat += 1;
        }
        if (c.getPlateau().get(c.getLigne() + 1, c.getColonne() - 1) != null) {
            resultat += 1;
        }
        if (c.getPlateau().get(c.getLigne() - 1, c.getColonne() - 1) != null) {
            resultat += 1;
        }
        return (resultat * 7) / 100;
    }

    public abstract String getNom();

    @Override
    public String toString() {
        return getNom() + " ("+getClass().getSimpleName()+", "+vie+"pv)";
    }

    public Case getCase() {
        return c;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public abstract BufferedImage getImage();


    public boolean deplacementPossible(Case c) {
		return getDeplacement().contains(c);
    }

    public void calculDeplacementPossible() {
        listeDeplacementPossible.clear();
        deplacement.clear();
        ArrayList<Noeud> listeFerme = new ArrayList<Noeud>();
        ArrayList<Noeud> listeOuverte = new ArrayList<Noeud>();

        Noeud tmp;
        Noeud tmp2;
        Case caseVerif;

        tmp = new Noeud(c, 0);
        listeOuverte.add(tmp);
        listeDeplacementPossible.add(tmp);
        while (!listeOuverte.isEmpty()) {
            tmp = listeOuverte.remove(0);
            listeFerme.add(tmp);
            caseVerif = c.getPlateau().get(tmp.c.getLigne() + 1, tmp.c.getColonne());
            if (caseVerif != null) {
                tmp2 = new Noeud(caseVerif, tmp.cout + Terrain.effetDeplacement(caseVerif.getTypeTerrain()));
            } else {
                tmp2 = new Noeud(caseVerif, tmp.cout);
            }
            recopierNoeudDansNoeud(tmp, tmp2);
            tmp2.listeNoeud.add(tmp);
            if (caseVerif != null && contient(tmp2, listeDeplacementPossible)) {
                if (tmp2.cout < noeudContenu.cout) {
                    noeudContenu.cout = tmp2.cout;
                    noeudContenu.listeNoeud = tmp2.listeNoeud;
                }
            } else if (caseVerif != null && !tmp2.c.isObstacleDeplacement() && !listeFerme.contains(tmp2)
                    && !listeOuverte.contains(tmp2) && tmp2.cout <= 2 * mouvement) {
                listeDeplacementPossible.add(tmp2);
                listeOuverte.add(tmp2);
            }
            caseVerif = c.getPlateau().get(tmp.c.getLigne() - 1, tmp.c.getColonne());
            if (caseVerif != null) {
                tmp2 = new Noeud(caseVerif, tmp.cout + Terrain.effetDeplacement(caseVerif.getTypeTerrain()));
            } else {
                tmp2 = new Noeud(caseVerif, tmp.cout);
            }
            recopierNoeudDansNoeud(tmp, tmp2);
            tmp2.listeNoeud.add(tmp);
            if (caseVerif != null && contient(tmp2, listeDeplacementPossible)) {
                if (tmp2.cout < noeudContenu.cout) {
                    noeudContenu.cout = tmp2.cout;
                    noeudContenu.listeNoeud = tmp2.listeNoeud;
                }
            } else if (caseVerif != null && !tmp2.c.isObstacleDeplacement() && !listeFerme.contains(tmp2)
                    && !listeOuverte.contains(tmp2) && tmp2.cout <= 2 * mouvement) {
                listeDeplacementPossible.add(tmp2);
                listeOuverte.add(tmp2);
            }
            caseVerif = c.getPlateau().get(tmp.c.getLigne(), tmp.c.getColonne() + 1);
            if (caseVerif != null) {
                tmp2 = new Noeud(caseVerif, tmp.cout + Terrain.effetDeplacement(caseVerif.getTypeTerrain()));
            } else {
                tmp2 = new Noeud(caseVerif, tmp.cout);
            }
            recopierNoeudDansNoeud(tmp, tmp2);
            tmp2.listeNoeud.add(tmp);
            if (caseVerif != null && contient(tmp2, listeDeplacementPossible)) {
                if (tmp2.cout < noeudContenu.cout) {
                    noeudContenu.cout = tmp2.cout;
                    noeudContenu.listeNoeud = tmp2.listeNoeud;
                }
            } else if (caseVerif != null && !tmp2.c.isObstacleDeplacement() && !listeFerme.contains(tmp2)
                    && !listeOuverte.contains(tmp2) && tmp2.cout <= 2 * mouvement) {
                listeDeplacementPossible.add(tmp2);
                listeOuverte.add(tmp2);
            }
            caseVerif = c.getPlateau().get(tmp.c.getLigne(), tmp.c.getColonne() - 1);
            if (caseVerif != null) {
                tmp2 = new Noeud(caseVerif, tmp.cout + Terrain.effetDeplacement(caseVerif.getTypeTerrain()));
            } else {
                tmp2 = new Noeud(caseVerif, tmp.cout);
            }
            recopierNoeudDansNoeud(tmp, tmp2);
            tmp2.listeNoeud.add(tmp);
            if (caseVerif != null && contient(tmp2, listeDeplacementPossible)) {
                if (tmp2.cout < noeudContenu.cout) {
                    noeudContenu.cout = tmp2.cout;
                    noeudContenu.listeNoeud = tmp2.listeNoeud;
                }
            } else if (caseVerif != null && !tmp2.c.isObstacleDeplacement() && !listeFerme.contains(tmp2)
                    && !listeOuverte.contains(tmp2) && tmp2.cout <= 2 * mouvement) {
                listeDeplacementPossible.add(tmp2);
                listeOuverte.add(tmp2);
            }
        }
    }

    public void attaque() {
        listeAttaquePossible.clear();
        listeAttaqueAire.clear();
        ArrayList<Case> listeFerme = new ArrayList<Case>();
        ArrayList<Case> listeOuverte = new ArrayList<Case>();

        Case tmp;
        Case caseVerif;

        tmp = c;
        listeOuverte.add(tmp);
        listeAttaquePossible.add(tmp);
        while (!listeOuverte.isEmpty()) {
            tmp = listeOuverte.remove(0);
            listeFerme.add(tmp);
            caseVerif = c.getPlateau().get(tmp.getLigne() + 1, tmp.getColonne());

            if (caseVerif != null && !caseVerif.isObstacleAttaque() && !listeFerme.contains(caseVerif)
                    && !listeOuverte.contains(caseVerif) && distanceManhattan(caseVerif) <= portee) {
                if (caseVerif.getPion() != null) {
                    listeAttaquePossible.add(caseVerif);
                }
                listeAttaqueAire.add(caseVerif);
                listeOuverte.add(caseVerif);
            }
            caseVerif = c.getPlateau().get(tmp.getLigne() - 1, tmp.getColonne());
            if (caseVerif != null && !caseVerif.isObstacleAttaque() && !listeFerme.contains(caseVerif)
                    && !listeOuverte.contains(caseVerif) && distanceManhattan(caseVerif) <= portee) {
                if (caseVerif.getPion() != null) {
                    listeAttaquePossible.add(caseVerif);
                }
                listeAttaqueAire.add(caseVerif);
                listeOuverte.add(caseVerif);
            }
            caseVerif = c.getPlateau().get(tmp.getLigne(), tmp.getColonne() + 1);
            if (caseVerif != null && !caseVerif.isObstacleAttaque() && !listeFerme.contains(caseVerif)
                    && !listeOuverte.contains(caseVerif) && distanceManhattan(caseVerif) <= portee) {
                if (caseVerif.getPion() != null) {
                    listeAttaquePossible.add(caseVerif);
                }
                listeAttaqueAire.add(caseVerif);
                listeOuverte.add(caseVerif);
            }
            caseVerif = c.getPlateau().get(tmp.getLigne(), tmp.getColonne() - 1);
            if (caseVerif != null && !caseVerif.isObstacleAttaque() && !listeFerme.contains(caseVerif)
                    && !listeOuverte.contains(caseVerif) && distanceManhattan(caseVerif) <= portee) {
                if (caseVerif.getPion() != null) {
                    listeAttaquePossible.add(caseVerif);
                }
                listeAttaqueAire.add(caseVerif);
                listeOuverte.add(caseVerif);
            }
        }
        listeAttaquePossible.remove(c);
    }

    private int distanceManhattan(Case c1) {
        return Math.abs(c1.getLigne() - c.getLigne()) + Math.abs(c1.getColonne() - c.getColonne());
    }

    private boolean contient(Noeud n, ArrayList<Noeud> ln) {
        for (Noeud n1 : ln) {
            if (n1.c.compare(n.c)) {
                noeudContenu = n1;
                return true;
            }
        }
        return false;
    }

    private void recopierNoeudDansNoeud(Noeud n1, Noeud n2) {
        for (Noeud nk : n1.listeNoeud) {
            n2.listeNoeud.add(nk);
        }
    }

    public void afficherDeplacement(Case c2) {
        deplacement.clear();
        for (Noeud n1 : listeDeplacementPossible) {
            if (n1.c.compare(c2)) {
                for (Noeud n2 : n1.listeNoeud) {
                    deplacement.add(n2.c);
                }
                deplacement.add(c2);
            }
        }
    }

    public ArrayList<Case> getDeplacement() {
        return deplacement;
    }

    public ArrayList<Case> getListeAttaquePossible() {
        return listeAttaquePossible;
    }

    public ArrayList<Case> getListeAttaqueAire() {
        return listeAttaqueAire;
    }

    public String getVieRestante() {
        //TODO affichage vie à modifier
        return vie + "pv";
    }
}
