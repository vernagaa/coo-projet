package moteur;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import moteur.classes.Archer;
import moteur.familles.felin.Felin;
import moteur.familles.oiseau.Oiseau;
import moteur.familles.reptile.Reptile;

/**
 *
 * @author chappelk
 */
public class NouvellePartieGraphique extends JComponent implements MouseListener, MouseMotionListener {

    private Moteur m;
    private ArrayList<Case> caseJoueur1;
    private boolean joueurCourant;
    private int etape;
    /*
     * Pour le joueur 1
     */
    private JTextField nomJoueur1;
    private JButton validez1;
    private JButton reptile1;
    private JButton oiseau1;
    private JButton felin1;
    private int famille1;
    private JButton archer1;
    private JButton guerrier1;
    private JButton tank1;
    private JButton assassin1;
    private JButton tacticien1;
    private JLabel vie1;
    private JLabel attaque1;
    private JLabel defense1;
    private JLabel vitesse1;
    private JLabel precision1;
    private JLabel portee1;
    private JLabel mouvement1;
    /*
     * Pour le joueur 2
     */
    private JTextField nomJoueur2;
    private JButton validez2;
    private JButton reptile2;
    private JButton oiseau2;
    private JButton felin2;
    private int famille2;
    private JButton archer2;
    private JButton guerrier2;
    private JButton tank2;
    private JButton assassin2;
    private JButton tacticien2;
    private JLabel vie2;
    private JLabel attaque2;
    private JLabel defense2;
    private JLabel vitesse2;
    private JLabel precision2;
    private JLabel portee2;
    private JLabel mouvement2;
    private int nbPions;
    private int classe;

    public NouvellePartieGraphique(final Moteur m) {
        this.m = m;
        joueurCourant = true;
        etape = 0;
        nbPions = 0;

        addMouseListener(this);
        addMouseMotionListener(this);

        caseJoueur1 = new ArrayList<Case>();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < m.getPlateau().getNbColonne(); j++) {
                if (!m.getPlateau().get(i, j).isObstacleDeplacement()) {
                    caseJoueur1.add(m.getPlateau().get(i, j));
                }
            }
        }

        JLabel joueur1 = new JLabel("Entrez le nom du joueur :");
        joueur1.setBounds(Case.TAILLE * 3, Case.TAILLE, Case.TAILLE * 5, Case.TAILLE);
        add(joueur1);
        nomJoueur1 = new JTextField("Joueur 1");
        nomJoueur1.setMargin(new Insets(0, 5, 0, 5));
        nomJoueur1.setBounds(Case.TAILLE * 2, Case.TAILLE * 2, Case.TAILLE * 5, Case.TAILLE);
        add(nomJoueur1);
        validez1 = new JButton("Ok");
        validez1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                nomJoueur1.setEditable(false);
                validez1.setEnabled(false);
                joueurCourant = !joueurCourant;
                m.getJoueur1().setNom(nomJoueur1.getText());
                System.out.println(m.getJoueur1().getNom());
                repaint();
            }
        });
        validez1.setBounds(Case.TAILLE * 8, Case.TAILLE * 2, Case.TAILLE * 2, Case.TAILLE);
        add(validez1);

        JLabel joueur2 = new JLabel("Entrez le nom du joueur :");
        joueur2.setBounds(Case.TAILLE * 3 + Case.TAILLE * 12, Case.TAILLE, Case.TAILLE * 5, Case.TAILLE);
        add(joueur2);
        nomJoueur2 = new JTextField("Joueur 2");
        nomJoueur2.setMargin(new Insets(0, 5, 0, 5));
        nomJoueur2.setBounds(Case.TAILLE * 2 + Case.TAILLE * 12, Case.TAILLE * 2, Case.TAILLE * 5, Case.TAILLE);
        add(nomJoueur2);
        validez2 = new JButton("Ok");
        validez2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                nomJoueur2.setEditable(false);
                validez2.setEnabled(false);
                joueurCourant = !joueurCourant;
                etape++;
                m.getJoueur2().setNom(nomJoueur2.getText());
                felin1.setEnabled(true);
                oiseau1.setEnabled(true);
                reptile1.setEnabled(true);
                System.out.println(m.getJoueur2().getNom());
                repaint();
            }
        });
        validez2.setBounds(Case.TAILLE * 8 + Case.TAILLE * 12, Case.TAILLE * 2, Case.TAILLE * 2, Case.TAILLE);
        add(validez2);

        reptile1 = new JButton();
        reptile1.setIcon(new ImageIcon(Textures.getPersonnage(Textures.ASSASSINREPTILE, Orientation.SUD)));
        reptile1.setBounds(Case.TAILLE + Case.TAILLE / 2, Case.TAILLE * 5, Case.TAILLE * 2, Case.TAILLE * 2);
        reptile1.setToolTipText("Reptile : vie (+3), force (+2), vitesse (-2)");
        reptile1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                choixFamilleJoueur1(FabriquePion.REPTILE);

            }
        });
        reptile1.setEnabled(false);
        add(reptile1);
        oiseau1 = new JButton();
        oiseau1.setIcon(new ImageIcon(Textures.getPersonnage(Textures.ASSASSINOISEAU, Orientation.SUD)));
        oiseau1.setBounds(Case.TAILLE * 4 + Case.TAILLE / 2, Case.TAILLE * 5, Case.TAILLE * 2, Case.TAILLE * 2);
        oiseau1.setEnabled(false);
        oiseau1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                choixFamilleJoueur1(FabriquePion.OISEAU);
            }
        });
        add(oiseau1);
        felin1 = new JButton();
        felin1.setBounds(Case.TAILLE * 7 + Case.TAILLE / 2, Case.TAILLE * 5, Case.TAILLE * 2, Case.TAILLE * 2);
        felin1.setIcon(new ImageIcon(Textures.getPersonnage(Textures.ASSASSINFELIN, Orientation.SUD)));
        felin1.setEnabled(false);
        felin1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                choixFamilleJoueur1(FabriquePion.FELIN);
            }
        });
        add(felin1);


        reptile2 = new JButton();
        reptile2.setIcon(new ImageIcon(Textures.getPersonnage(Textures.ASSASSINREPTILE, Orientation.SUD)));
        reptile2.setBounds(Case.TAILLE + Case.TAILLE / 2 + Case.TAILLE * 12, Case.TAILLE * 5, Case.TAILLE * 2, Case.TAILLE * 2);
        reptile2.setToolTipText("Reptile : vie (+3), force (+2), vitesse (-2)");
        reptile2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                choixFamilleJoueur2(FabriquePion.REPTILE);
            }
        });
        reptile2.setEnabled(false);
        add(reptile2);
        oiseau2 = new JButton();
        oiseau2.setIcon(new ImageIcon(Textures.getPersonnage(Textures.ASSASSINOISEAU, Orientation.SUD)));
        oiseau2.setBounds(Case.TAILLE * 4 + Case.TAILLE / 2 + Case.TAILLE * 12, Case.TAILLE * 5, Case.TAILLE * 2, Case.TAILLE * 2);
        oiseau2.setEnabled(false);
        oiseau2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                choixFamilleJoueur2(FabriquePion.OISEAU);
            }
        });
        add(oiseau2);
        felin2 = new JButton();
        felin2.setBounds(Case.TAILLE * 7 + Case.TAILLE / 2 + Case.TAILLE * 12, Case.TAILLE * 5, Case.TAILLE * 2, Case.TAILLE * 2);
        felin2.setIcon(new ImageIcon(Textures.getPersonnage(Textures.ASSASSINFELIN, Orientation.SUD)));
        felin2.setEnabled(false);
        felin2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                choixFamilleJoueur2(FabriquePion.OISEAU);
            }
        });
        add(felin2);


        archer1 = new JButton("archer 1");
        archer1.setBounds(Case.TAILLE, Case.TAILLE * 9, Case.TAILLE, Case.TAILLE);
        archer1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                classe = FabriquePion.ARCHER;
            }
        });
        archer1.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
            //TODO implémenter la modif des label
            }

            @Override
            public void focusLost(FocusEvent e) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });
        archer1.setEnabled(false);
        add(archer1);
        guerrier1 = new JButton("guerrier 1");
        guerrier1.setBounds(Case.TAILLE * 3, Case.TAILLE * 9, Case.TAILLE, Case.TAILLE);
        guerrier1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                classe = FabriquePion.GUERRIER;
            }
        });
        guerrier1.setEnabled(false);
        add(guerrier1);
        tank1 = new JButton("tank 1");
        tank1.setBounds(Case.TAILLE * 5, Case.TAILLE * 9, Case.TAILLE, Case.TAILLE);
        tank1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                classe = FabriquePion.TANK;
            }
        });
        tank1.setEnabled(false);
        add(tank1);
        assassin1 = new JButton("guerrier 1");
        assassin1.setBounds(Case.TAILLE * 7, Case.TAILLE * 9, Case.TAILLE, Case.TAILLE);
        assassin1.setEnabled(false);
        assassin1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                classe = FabriquePion.ASSASSIN;
            }
        });
        add(assassin1);
        tacticien1 = new JButton("tacticien 1");
        tacticien1.setBounds(Case.TAILLE * 9, Case.TAILLE * 9, Case.TAILLE, Case.TAILLE);
        tacticien1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                classe = FabriquePion.TACTICIEN;
            }
        });
        tacticien1.setEnabled(false);
        add(tacticien1);


        archer2 = new JButton("archer 1");
        archer2.setBounds(Case.TAILLE + Case.TAILLE * 12, Case.TAILLE * 9, Case.TAILLE, Case.TAILLE);
        archer2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                classe = FabriquePion.ARCHER;
            }
        });
        archer2.setEnabled(false);
        add(archer2);
        guerrier2 = new JButton("guerrier 1");
        guerrier2.setBounds(Case.TAILLE * 3 + Case.TAILLE * 12, Case.TAILLE * 9, Case.TAILLE, Case.TAILLE);
        guerrier2.setEnabled(false);
        add(guerrier2);
        tank2 = new JButton("tank 1");
        tank2.setBounds(Case.TAILLE * 5 + Case.TAILLE * 12, Case.TAILLE * 9, Case.TAILLE, Case.TAILLE);
        tank2.setEnabled(false);
        add(tank2);
        assassin2 = new JButton("guerrier 1");
        assassin2.setBounds(Case.TAILLE * 7 + Case.TAILLE * 12, Case.TAILLE * 9, Case.TAILLE, Case.TAILLE);
        assassin2.setEnabled(false);
        add(assassin2);
        tacticien2 = new JButton("tacticien 1");
        tacticien2.setBounds(Case.TAILLE * 9 + Case.TAILLE * 12, Case.TAILLE * 9, Case.TAILLE, Case.TAILLE);
        tacticien2.setEnabled(false);
        add(tacticien2);



        vie1 = new JLabel("vie 1");
        vie1.setBounds(Case.TAILLE * 3, Case.TAILLE * 12, Case.TAILLE * 5, Case.TAILLE);
        add(vie1);
        attaque1 = new JLabel("attaque 1");
        attaque1.setBounds(Case.TAILLE * 3, Case.TAILLE * 13, Case.TAILLE * 5, Case.TAILLE);
        add(attaque1);
        defense1 = new JLabel("defense 1");
        defense1.setBounds(Case.TAILLE * 3, Case.TAILLE * 14, Case.TAILLE * 5, Case.TAILLE);
        add(defense1);
        precision1 = new JLabel("precision 1");
        precision1.setBounds(Case.TAILLE * 3, Case.TAILLE * 15, Case.TAILLE * 5, Case.TAILLE);
        add(precision1);
        vitesse1 = new JLabel("vitesse 1");
        vitesse1.setBounds(Case.TAILLE * 3, Case.TAILLE * 16, Case.TAILLE * 5, Case.TAILLE);
        add(vitesse1);
        portee1 = new JLabel("portee 1");
        portee1.setBounds(Case.TAILLE * 3, Case.TAILLE * 17, Case.TAILLE * 5, Case.TAILLE);
        add(portee1);
        mouvement1 = new JLabel("mouvement 1");
        mouvement1.setBounds(Case.TAILLE * 3, Case.TAILLE * 18, Case.TAILLE * 5, Case.TAILLE);
        add(mouvement1);





        m.aireDeJeu.add(this);
        setLocation(Case.TAILLE * 6, 0);
        setVisible(true);
        setSize(Case.TAILLE * 23, Case.TAILLE * 20);
    }

    public void paintComponent(Graphics g) {
        g.fillRect(0, 0, Case.TAILLE * 23, Case.TAILLE * 20);
        g.setColor(Color.GRAY);

        g.fillRect(Case.TAILLE / 2 + Case.TAILLE * 12, Case.TAILLE / 2, Case.TAILLE * 10, Case.TAILLE * 3);
        g.fillRect(Case.TAILLE / 2 + Case.TAILLE * 12, Case.TAILLE * 4, Case.TAILLE * 10, Case.TAILLE * 4);
        g.fillRect(Case.TAILLE / 2 + Case.TAILLE * 12, Case.TAILLE * 8 + Case.TAILLE / 2, Case.TAILLE * 10, Case.TAILLE * 2);
        g.fillRect(Case.TAILLE / 2 + Case.TAILLE * 12, Case.TAILLE * 11 + Case.TAILLE / 2, Case.TAILLE * 10, Case.TAILLE * 8);

        g.fillRect(Case.TAILLE / 2, Case.TAILLE / 2, Case.TAILLE * 10, Case.TAILLE * 3);
        g.fillRect(Case.TAILLE / 2, Case.TAILLE * 4, Case.TAILLE * 10, Case.TAILLE * 4);
        g.fillRect(Case.TAILLE / 2, Case.TAILLE * 8 + Case.TAILLE / 2, Case.TAILLE * 10, Case.TAILLE * 2);
        g.fillRect(Case.TAILLE / 2, Case.TAILLE * 11 + Case.TAILLE / 2, Case.TAILLE * 10, Case.TAILLE * 8);

        g.setColor(new Color(255, 0, 0, 150));
        if (etape == 0) {
            if (joueurCourant) {
                g.fillRect(Case.TAILLE / 2, Case.TAILLE / 2, Case.TAILLE * 10, Case.TAILLE * 3);
            } else {
                g.fillRect(Case.TAILLE / 2 + Case.TAILLE * 12, Case.TAILLE / 2, Case.TAILLE * 10, Case.TAILLE * 3);
            }
        } else if (etape == 1) {
            if (joueurCourant) {
                g.fillRect(Case.TAILLE / 2, Case.TAILLE * 4, Case.TAILLE * 10, Case.TAILLE * 4);
            } else {
                g.fillRect(Case.TAILLE / 2 + Case.TAILLE * 12, Case.TAILLE * 4, Case.TAILLE * 10, Case.TAILLE * 4);
            }
        } else {
            if (joueurCourant) {
                g.fillRect(Case.TAILLE / 2, Case.TAILLE * 8 + Case.TAILLE / 2, Case.TAILLE * 10, Case.TAILLE * 2);
            } else {
                g.fillRect(Case.TAILLE / 2 + Case.TAILLE * 12, Case.TAILLE * 8 + Case.TAILLE / 2, Case.TAILLE * 10, Case.TAILLE * 2);
            }
        }

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

    public int getEtape() {
        return etape;
    }

    public boolean isJoueurCourant() {
        return joueurCourant;
    }

    public void setJoueurCourant(boolean joueurCourant) {
        this.joueurCourant = joueurCourant;
    }

    private void choixFamilleJoueur2(int famille) {
        reptile2.setEnabled(false);
        felin2.setEnabled(false);
        oiseau2.setEnabled(false);
        archer1.setEnabled(true);
        guerrier1.setEnabled(true);
        tank1.setEnabled(true);
        tacticien1.setEnabled(true);
        assassin1.setEnabled(true);
        joueurCourant = !joueurCourant;
        famille2 = famille;
        m.aireDeJeu.setJoueurCourant(joueurCourant);
        m.aireDeJeu.repaint();
        etape++;
        repaint();
    }

    private void choixFamilleJoueur1(int famille) {
        reptile1.setEnabled(false);
        felin1.setEnabled(false);
        oiseau1.setEnabled(false);
        reptile2.setEnabled(true);
        felin2.setEnabled(true);
        oiseau2.setEnabled(true);
        joueurCourant = !joueurCourant;
        famille1 = famille;
        repaint();
    }

    public void pionAjouteJoueur1() {
        nbPions++;
        if (nbPions % 2 == 1) {
            archer1.setEnabled(false);
            guerrier1.setEnabled(false);
            assassin1.setEnabled(false);
            tank1.setEnabled(false);
            tacticien1.setEnabled(false);
            archer2.setEnabled(true);
            guerrier2.setEnabled(true);
            assassin2.setEnabled(true);
            tank2.setEnabled(true);
            tacticien2.setEnabled(true);
            joueurCourant = !joueurCourant;
            m.aireDeJeu.setJoueurCourant(joueurCourant);
            m.aireDeJeu.repaint();
        }
    }

    public int getFamille1() {
        return famille1;
    }

    public int getFamille2() {
        return famille2;
    }

    public int getClasse() {
        return classe;
    }
    
    private ArrayList<Integer> archer(int famille){
        ArrayList<Integer> stat = new ArrayList<Integer>();
        switch(famille){
            case FabriquePion.REPTILE:
                stat.add(Archer.VIE + Reptile.vieArcher);
                stat.add(Archer.FORCE + Reptile.forceArcher);
                stat.add(Archer.DEFENSE + Reptile.defenseArcher);
                stat.add(Archer.VITESSE + Reptile.vitesseArcher);
                stat.add(Archer.PRECISION + Reptile.precisionArcher);
                stat.add(Archer.PORTEE + Reptile.porteeArcher);
                stat.add(Archer.MOUVEMENT + Reptile.mouvementArcher);
                break;
            case FabriquePion.OISEAU:
                stat.add(Archer.VIE + Oiseau.vieArcher);
                stat.add(Archer.FORCE + Oiseau.forceArcher);
                stat.add(Archer.DEFENSE + Oiseau.defenseArcher);
                stat.add(Archer.VITESSE + Oiseau.vitesseArcher);
                stat.add(Archer.PRECISION + Oiseau.precisionArcher);
                stat.add(Archer.PORTEE + Oiseau.porteeArcher);
                stat.add(Archer.MOUVEMENT + Oiseau.mouvementArcher);
                break;
            case FabriquePion.FELIN:
                stat.add(Archer.VIE + Felin.vieArcher);
                stat.add(Archer.FORCE + Felin.forceArcher);
                stat.add(Archer.DEFENSE + Felin.defenseArcher);
                stat.add(Archer.VITESSE + Felin.vitesseArcher);
                stat.add(Archer.PRECISION + Felin.precisionArcher);
                stat.add(Archer.PORTEE + Felin.porteeArcher);
                stat.add(Archer.MOUVEMENT + Felin.mouvementArcher);
                break;
        }
        return stat;
    }
}
