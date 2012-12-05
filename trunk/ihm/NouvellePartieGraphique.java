package ihm;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import moteur.Case;
import moteur.FabriquePion;
import moteur.Moteur;
import moteur.Orientation;
import moteur.Textures;
import moteur.classes.Archer;
import moteur.classes.Assassin;
import moteur.classes.Guerrier;
import moteur.classes.Tacticien;
import moteur.classes.Tank;
import moteur.familles.felin.Felin;
import moteur.familles.oiseau.Oiseau;
import moteur.familles.reptile.Reptile;

/**
 *
 * @author chappelk
 */
// implements utile pour empêcher l'aireDeJeu de reçevoir les clics
public class NouvellePartieGraphique extends JComponent implements MouseListener, MouseMotionListener {
	
	private static final String FELIN_DESCR = "<html><p align=\"justify\">Les félins procurent un bonus en défense et en chance, et un malus en vie."
					+ "<br/><br/>Le tacticien procure aux unités 1 de mouvement supplémentaire."
					+ "<br/>Il procure la capacité spéciale \"Rage\" à chaque unité. La prochaine attaque sera un coup critique à coup sûr, au prochain tour, il aura une précision de 0. "
					+ "Elle ne peut s'utiliser qu'une fois tout les trois tours</p></html>";
	private static final String OISEAU_DESCR = "<html><p align=\"justify\">Les oiseaux procurent un bonus en attaque et en vie, et un malus en vitesse."
					+ "<br/><br/>Le tacticien procure aux unités 1 de portée supplémentaire."
					+ "<br/>Il procure la capacité spéciale \"Envol\" à chaque unité. L'oiseau vol alors jusqu'à la fin de son prochain tour, il est inattaquable durant ce temps. "
					+ "Elle ne peut s'utiliser qu'une fois tout les trois tours</p></html>";
	private static final String REPTILE_DESCR = "<html><p align=\"justify\">Les reptiles procurent un bonus en attaque et en vie, et un malus en vitesse."
					+ "<br/><br/>Le tacticien procure au joueur un bonus de 3 actions,"
					+ "<br/>Il procure la capacité spéciale \"Mue\" à chaque unité. Elle rend 7 pv à chaque utilisation. "
					+ "Elle ne peut s'utiliser qu'une fois tout les trois tours</p></html>";
	
	private Moteur m;
	private ArrayList<Case> caseJoueur;
	private boolean joueurCourant;
	private final boolean JOUEUR1 = true;
	private final boolean JOUEUR2 = false;
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
	private JLabel afficherInfo1;
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
	private int classeSelect;
	private boolean choix;
	private JLabel afficherInfo2;
	
	public NouvellePartieGraphique(final Moteur m) {
		this.m = m;
		joueurCourant = JOUEUR1;
		choix = false;
		etape = 0;
		nbPions = 0;
		
		addMouseListener(this);
		addMouseMotionListener(this);
		
		caseJoueur = new ArrayList<Case>();

		class EcouteurBoutonFamille extends MouseAdapter implements ActionListener {

			private boolean joueur;
			private int famille;

			public EcouteurBoutonFamille(boolean joueur, int famille) {
				this.joueur = joueur;
				this.famille = famille;
			}

			@Override
			public void actionPerformed(ActionEvent e) {
				if(joueur == JOUEUR1) {
					choixFamilleJoueur1(famille);
				}
				else {
					choixFamilleJoueur2(famille);
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
//				if (joueur == joueurCourant && etape == 1) {
				if(e.getComponent().isEnabled()) {
					afficherFamille(joueur, famille);
				}
			}

			@Override
			public void mouseExited(MouseEvent e) {
//				if (joueur == joueurCourant && etape == 1) {
				if(e.getComponent().isEnabled()) {
					cacherFamille(joueur);
				}
			}
		}

		class EcouteurBoutonClasse extends MouseAdapter implements ActionListener {

			private int famille;
			private int classe;

			public EcouteurBoutonClasse(int famille, int classe) {
				this.famille = famille;
				this.classe = classe;
			}

			@Override
			public void actionPerformed(ActionEvent e) {
				classeSelect = classe;
				choix = true;
				setListeAireDejeu();
				m.aireDeJeu.repaint();
				getParent().repaint();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				if (!choix && e.getComponent().isEnabled()) {
					afficherStatPerso(famille, classe);
				}
			}

			@Override
			public void mouseExited(MouseEvent e) {
				if (!choix && e.getComponent().isEnabled()) {
					cacherStatPerso();
				}
			}
		}

		JLabel joueur1 = new JLabel("<html>Entrez le nom du joueur :</html>");
		joueur1.setBounds(Case.TAILLE * 3, 3 * Case.TAILLE / 4, Case.TAILLE * 5, Case.TAILLE);
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
		
		JLabel joueur2 = new JLabel("<html>Entrez le nom du joueur :</html>");
		joueur2.setBounds(Case.TAILLE * 3 + Case.TAILLE * 12, 3 * Case.TAILLE / 4, Case.TAILLE * 5, Case.TAILLE);
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
				afficherInfo1.setVisible(true);
				System.out.println(m.getJoueur2().getNom());
				repaint();
			}
		});
		validez2.setBounds(Case.TAILLE * 8 + Case.TAILLE * 12, Case.TAILLE * 2, Case.TAILLE * 2, Case.TAILLE);
		add(validez2);
		
		EcouteurBoutonFamille ebf;
		
		reptile1 = new JButton();
		reptile1.setIcon(new ImageIcon(Textures.getPersonnage(Textures.ASSASSINREPTILE, Orientation.SUD)));
		reptile1.setBounds(Case.TAILLE + Case.TAILLE / 2, Case.TAILLE * 5 + Case.TAILLE / 2, Case.TAILLE * 2, Case.TAILLE * 2);
		ebf = new EcouteurBoutonFamille(JOUEUR1, FabriquePion.REPTILE);
		reptile1.addActionListener(ebf);
		reptile1.addMouseListener(ebf);
		
		reptile1.setEnabled(false);
		add(reptile1);
		
		oiseau1 = new JButton();
		oiseau1.setIcon(new ImageIcon(Textures.getPersonnage(Textures.ASSASSINOISEAU, Orientation.SUD)));
		oiseau1.setBounds(Case.TAILLE * 4 + Case.TAILLE / 2, Case.TAILLE * 5 + Case.TAILLE / 2, Case.TAILLE * 2, Case.TAILLE * 2);
		oiseau1.setEnabled(false);
		ebf = new EcouteurBoutonFamille(JOUEUR1, FabriquePion.OISEAU);
		oiseau1.addActionListener(ebf);
		oiseau1.addMouseListener(ebf);
		add(oiseau1);
		felin1 = new JButton();
		felin1.setBounds(Case.TAILLE * 7 + Case.TAILLE / 2, Case.TAILLE * 5 + Case.TAILLE / 2, Case.TAILLE * 2, Case.TAILLE * 2);
		felin1.setIcon(new ImageIcon(Textures.getPersonnage(Textures.ASSASSINFELIN, Orientation.SUD)));
		felin1.setEnabled(false);
		ebf = new EcouteurBoutonFamille(JOUEUR1, FabriquePion.FELIN);
		felin1.addActionListener(ebf);
		felin1.addMouseListener(ebf);
		add(felin1);
		
		JLabel choixFamille1 = new JLabel("<html>Choisissez une famille :</html>");
		choixFamille1.setBounds(Case.TAILLE * 3, Case.TAILLE * 4, Case.TAILLE * 6, Case.TAILLE);
		add(choixFamille1);
		JLabel choixFamille2 = new JLabel("<html>Choisissez une famille :</html>");
		choixFamille2.setBounds(Case.TAILLE * 3 + Case.TAILLE * 12, Case.TAILLE * 4, Case.TAILLE * 6, Case.TAILLE);
		add(choixFamille2);
		
		reptile2 = new JButton();
		reptile2.setIcon(new ImageIcon(Textures.getPersonnage(Textures.ASSASSINREPTILE, Orientation.SUD)));
		reptile2.setBounds(Case.TAILLE + Case.TAILLE / 2 + Case.TAILLE * 12, Case.TAILLE * 5 + Case.TAILLE / 2, Case.TAILLE * 2, Case.TAILLE * 2);
		ebf = new EcouteurBoutonFamille(JOUEUR2, FabriquePion.REPTILE);
		reptile2.addActionListener(ebf);
		reptile2.addMouseListener(ebf);
		reptile2.setEnabled(false);
		add(reptile2);
		oiseau2 = new JButton();
		oiseau2.setIcon(new ImageIcon(Textures.getPersonnage(Textures.ASSASSINOISEAU, Orientation.SUD)));
		oiseau2.setBounds(Case.TAILLE * 4 + Case.TAILLE / 2 + Case.TAILLE * 12, Case.TAILLE * 5 + Case.TAILLE / 2, Case.TAILLE * 2, Case.TAILLE * 2);
		oiseau2.setEnabled(false);
		ebf = new EcouteurBoutonFamille(JOUEUR2, FabriquePion.OISEAU);
		oiseau2.addActionListener(ebf);
		oiseau2.addMouseListener(ebf);
		add(oiseau2);
		felin2 = new JButton();
		felin2.setBounds(Case.TAILLE * 7 + Case.TAILLE / 2 + Case.TAILLE * 12, Case.TAILLE * 5 + Case.TAILLE / 2, Case.TAILLE * 2, Case.TAILLE * 2);
		felin2.setIcon(new ImageIcon(Textures.getPersonnage(Textures.ASSASSINFELIN, Orientation.SUD)));
		felin2.setEnabled(false);
		ebf = new EcouteurBoutonFamille(JOUEUR2, FabriquePion.FELIN);
		felin2.addActionListener(ebf);
		felin2.addMouseListener(ebf);
		add(felin2);
		
		afficherInfo1 = new JLabel();
		afficherInfo1.setBounds(Case.TAILLE, Case.TAILLE * 12, Case.TAILLE * 9, Case.TAILLE * 6);
		afficherInfo1.setVisible(false);
		add(afficherInfo1);
		
		afficherInfo2 = new JLabel();
		afficherInfo2.setBounds(Case.TAILLE + Case.TAILLE * 12, Case.TAILLE * 12, Case.TAILLE * 9, Case.TAILLE * 6);
		afficherInfo2.setVisible(false);
		add(afficherInfo2);
		
		JLabel choixClasse1 = new JLabel("<html>Choisissez un pion :</html>");
		choixClasse1.setBounds(Case.TAILLE * 3, Case.TAILLE * 8 + Case.TAILLE / 2, Case.TAILLE * 6, Case.TAILLE);
		add(choixClasse1);
		
		JLabel choixClasse2 = new JLabel("<html>Choisissez un pion :</html>");
		choixClasse2.setBounds(Case.TAILLE * 3 + Case.TAILLE * 12, Case.TAILLE * 8 + Case.TAILLE / 2, Case.TAILLE * 6, Case.TAILLE);
		add(choixClasse2);
		
		EcouteurBoutonClasse ebc;
		
		archer1 = new JButton();
		archer1.setBounds(Case.TAILLE, Case.TAILLE * 9 + Case.TAILLE / 2, Case.TAILLE, Case.TAILLE);
		ebc = new EcouteurBoutonClasse(famille1, FabriquePion.ARCHER);
		archer1.addActionListener(ebc);
		archer1.addMouseListener(ebc);
		archer1.setEnabled(false);
		add(archer1);
		guerrier1 = new JButton();
		guerrier1.setBounds(Case.TAILLE * 3, Case.TAILLE * 9 + Case.TAILLE / 2, Case.TAILLE, Case.TAILLE);
		ebc = new EcouteurBoutonClasse(famille1, FabriquePion.GUERRIER);
		guerrier1.addActionListener(ebc);
		guerrier1.addMouseListener(ebc);
		guerrier1.setEnabled(false);
		add(guerrier1);
		tank1 = new JButton();
		tank1.setBounds(Case.TAILLE * 5, Case.TAILLE * 9 + Case.TAILLE / 2, Case.TAILLE, Case.TAILLE);
		ebc = new EcouteurBoutonClasse(famille1, FabriquePion.TANK);
		tank1.addActionListener(ebc);
		tank1.addMouseListener(ebc);
		tank1.setEnabled(false);
		add(tank1);
		assassin1 = new JButton();
		assassin1.setBounds(Case.TAILLE * 7, Case.TAILLE * 9 + Case.TAILLE / 2, Case.TAILLE, Case.TAILLE);
		assassin1.setEnabled(false);
		ebc = new EcouteurBoutonClasse(famille1, FabriquePion.ASSASSIN);
		assassin1.addActionListener(ebc);
		assassin1.addMouseListener(ebc);
		add(assassin1);
		tacticien1 = new JButton();
		tacticien1.setBounds(Case.TAILLE * 9, Case.TAILLE * 9 + Case.TAILLE / 2, Case.TAILLE, Case.TAILLE);
		ebc = new EcouteurBoutonClasse(famille1, FabriquePion.TACTICIEN);
		tacticien1.addActionListener(ebc);
		tacticien1.addMouseListener(ebc);
		tacticien1.setEnabled(false);
		add(tacticien1);
		
		
		archer2 = new JButton();
		archer2.setBounds(Case.TAILLE + Case.TAILLE * 12, Case.TAILLE * 9 + Case.TAILLE / 2, Case.TAILLE, Case.TAILLE);
		ebc = new EcouteurBoutonClasse(famille2, FabriquePion.ARCHER);
		archer2.addActionListener(ebc);
		archer2.addMouseListener(ebc);
		archer2.setEnabled(false);
		add(archer2);
		guerrier2 = new JButton();
		guerrier2.setBounds(Case.TAILLE * 3 + Case.TAILLE * 12, Case.TAILLE * 9 + Case.TAILLE / 2, Case.TAILLE, Case.TAILLE);
		ebc = new EcouteurBoutonClasse(famille2, FabriquePion.GUERRIER);
		guerrier2.addActionListener(ebc);
		guerrier2.addMouseListener(ebc);
		guerrier2.setEnabled(false);
		add(guerrier2);
		tank2 = new JButton();
		tank2.setBounds(Case.TAILLE * 5 + Case.TAILLE * 12, Case.TAILLE * 9 + Case.TAILLE / 2, Case.TAILLE, Case.TAILLE);
		ebc = new EcouteurBoutonClasse(famille2, FabriquePion.TANK);
		tank2.addActionListener(ebc);
		guerrier1.addMouseListener(ebc);
		tank2.setEnabled(false);
		add(tank2);
		assassin2 = new JButton();
		assassin2.setBounds(Case.TAILLE * 7 + Case.TAILLE * 12, Case.TAILLE * 9 + Case.TAILLE / 2, Case.TAILLE, Case.TAILLE);
		assassin2.setEnabled(false);
		ebc = new EcouteurBoutonClasse(famille2, FabriquePion.ASSASSIN);
		assassin2.addActionListener(ebc);
		assassin2.addMouseListener(ebc);
		add(assassin2);
		tacticien2 = new JButton();
		tacticien2.setBounds(Case.TAILLE * 9 + Case.TAILLE * 12, Case.TAILLE * 9 + Case.TAILLE / 2, Case.TAILLE, Case.TAILLE);
		ebc = new EcouteurBoutonClasse(famille2, FabriquePion.TACTICIEN);
		tacticien2.addActionListener(ebc);
		tacticien2.addMouseListener(ebc);
		tacticien2.setEnabled(false);
		add(tacticien2);
		
		
		vie1 = new JLabel();
		vie1.setBounds(Case.TAILLE * 3, Case.TAILLE * 12, Case.TAILLE * 5, Case.TAILLE);
		vie1.setVisible(false);
		add(vie1);
		attaque1 = new JLabel();
		attaque1.setBounds(Case.TAILLE * 3, Case.TAILLE * 13, Case.TAILLE * 5, Case.TAILLE);
		attaque1.setVisible(false);
		add(attaque1);
		defense1 = new JLabel();
		defense1.setBounds(Case.TAILLE * 3, Case.TAILLE * 14, Case.TAILLE * 5, Case.TAILLE);
		defense1.setVisible(false);
		add(defense1);
		precision1 = new JLabel();
		precision1.setBounds(Case.TAILLE * 3, Case.TAILLE * 15, Case.TAILLE * 5, Case.TAILLE);
		precision1.setVisible(false);
		add(precision1);
		vitesse1 = new JLabel();
		vitesse1.setBounds(Case.TAILLE * 3, Case.TAILLE * 16, Case.TAILLE * 5, Case.TAILLE);
		vitesse1.setVisible(false);
		add(vitesse1);
		portee1 = new JLabel();
		portee1.setBounds(Case.TAILLE * 3, Case.TAILLE * 17, Case.TAILLE * 5, Case.TAILLE);
		portee1.setVisible(false);
		add(portee1);
		mouvement1 = new JLabel();
		mouvement1.setBounds(Case.TAILLE * 3, Case.TAILLE * 18, Case.TAILLE * 5, Case.TAILLE);
		mouvement1.setVisible(false);
		add(mouvement1);
		
		vie2 = new JLabel();
		vie2.setBounds(Case.TAILLE * 3 + Case.TAILLE * 12, Case.TAILLE * 12, Case.TAILLE * 5, Case.TAILLE);
		vie2.setVisible(false);
		add(vie2);
		attaque2 = new JLabel();
		attaque2.setBounds(Case.TAILLE * 3 + Case.TAILLE * 12, Case.TAILLE * 13, Case.TAILLE * 5, Case.TAILLE);
		attaque2.setVisible(false);
		add(attaque2);
		defense2 = new JLabel();
		defense2.setBounds(Case.TAILLE * 3 + Case.TAILLE * 12, Case.TAILLE * 14, Case.TAILLE * 5, Case.TAILLE);
		defense2.setVisible(false);
		add(defense2);
		precision2 = new JLabel();
		precision2.setBounds(Case.TAILLE * 3 + Case.TAILLE * 12, Case.TAILLE * 15, Case.TAILLE * 5, Case.TAILLE);
		precision2.setVisible(false);
		add(precision2);
		vitesse2 = new JLabel();
		vitesse2.setBounds(Case.TAILLE * 3 + Case.TAILLE * 12, Case.TAILLE * 16, Case.TAILLE * 5, Case.TAILLE);
		vitesse2.setVisible(false);
		add(vitesse2);
		portee2 = new JLabel();
		portee2.setBounds(Case.TAILLE * 3 + Case.TAILLE * 12, Case.TAILLE * 17, Case.TAILLE * 5, Case.TAILLE);
		portee2.setVisible(false);
		add(portee2);
		mouvement2 = new JLabel();
		mouvement2.setBounds(Case.TAILLE * 3 + Case.TAILLE * 12, Case.TAILLE * 18, Case.TAILLE * 5, Case.TAILLE);
		mouvement2.setVisible(false);
		add(mouvement2);
		
		m.aireDeJeu.add(this);
		setLocation(Case.TAILLE * 6, 0);
		setVisible(true);
		setSize(Case.TAILLE * 23, Case.TAILLE * 20);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		g.fillRect(0, 0, Case.TAILLE * 23, Case.TAILLE * 20);
		g.setColor(Color.GRAY);
		
		g.fillRect(Case.TAILLE / 2 + Case.TAILLE * 12, Case.TAILLE / 2, Case.TAILLE * 10, Case.TAILLE * 3);
		g.fillRect(Case.TAILLE / 2 + Case.TAILLE * 12, Case.TAILLE * 4, Case.TAILLE * 10, Case.TAILLE * 4);
		g.fillRect(Case.TAILLE / 2 + Case.TAILLE * 12, Case.TAILLE * 8 + Case.TAILLE / 2, Case.TAILLE * 10, Case.TAILLE * 2 + Case.TAILLE / 2);
		g.fillRect(Case.TAILLE / 2 + Case.TAILLE * 12, Case.TAILLE * 11 + Case.TAILLE / 2, Case.TAILLE * 10, Case.TAILLE * 8);
		
		g.fillRect(Case.TAILLE / 2, Case.TAILLE / 2, Case.TAILLE * 10, Case.TAILLE * 3);
		g.fillRect(Case.TAILLE / 2, Case.TAILLE * 4, Case.TAILLE * 10, Case.TAILLE * 4);
		g.fillRect(Case.TAILLE / 2, Case.TAILLE * 8 + Case.TAILLE / 2, Case.TAILLE * 10, Case.TAILLE * 2 + Case.TAILLE / 2);
		g.fillRect(Case.TAILLE / 2, Case.TAILLE * 11 + Case.TAILLE / 2, Case.TAILLE * 10, Case.TAILLE * 8);
		
		g.setColor(new Color(255, 0, 0, 150));
		if (etape == 0) {
			if (joueurCourant) {
				g.fillRect(Case.TAILLE / 2, Case.TAILLE / 2, Case.TAILLE * 10, Case.TAILLE * 3);
			}
			else {
				g.fillRect(Case.TAILLE / 2 + Case.TAILLE * 12, Case.TAILLE / 2, Case.TAILLE * 10, Case.TAILLE * 3);
			}
		}
		else if (etape == 1) {
			if (joueurCourant) {
				g.fillRect(Case.TAILLE / 2, Case.TAILLE * 4, Case.TAILLE * 10, Case.TAILLE * 4);
			}
			else {
				g.fillRect(Case.TAILLE / 2 + Case.TAILLE * 12, Case.TAILLE * 4, Case.TAILLE * 10, Case.TAILLE * 4);
			}
		}
		else if (etape == 2) {
			if (joueurCourant && !choix) {
				g.fillRect(Case.TAILLE / 2, Case.TAILLE * 8 + Case.TAILLE / 2, Case.TAILLE * 10, Case.TAILLE * 2 + Case.TAILLE / 2);
			}
			else if (!choix) {
				g.fillRect(Case.TAILLE / 2 + Case.TAILLE * 12, Case.TAILLE * 8 + Case.TAILLE / 2, Case.TAILLE * 10, Case.TAILLE * 2 + Case.TAILLE / 2);
			}
		}
		else {
			if (joueurCourant) {
				g.fillRect(Case.TAILLE / 2, Case.TAILLE * 11 + Case.TAILLE / 2, Case.TAILLE * 10, Case.TAILLE * 8);
			}
			else {
				g.fillRect(Case.TAILLE / 2 + Case.TAILLE * 12, Case.TAILLE * 11 + Case.TAILLE / 2, Case.TAILLE * 10, Case.TAILLE * 8);
				
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
		afficherFamille(joueurCourant, famille);
		reptile2.setEnabled(false);
		felin2.setEnabled(false);
		oiseau2.setEnabled(false);
		archer1.setEnabled(true);
		guerrier1.setEnabled(true);
		tank1.setEnabled(true);
		tacticien1.setEnabled(true);
		assassin1.setEnabled(true);
		afficherInfo2.setVisible(false);
		joueurCourant = !joueurCourant;
		etape++;
		afficherStatJoueur(joueurCourant);
		famille2 = famille;
		m.aireDeJeu.setJoueurCourant(joueurCourant);
		m.aireDeJeu.repaint();
		repaint();
	}
	
	private void choixFamilleJoueur1(int famille) {
		afficherFamille(joueurCourant, famille);
		reptile1.setEnabled(false);
		felin1.setEnabled(false);
		oiseau1.setEnabled(false);
		reptile2.setEnabled(true);
		felin2.setEnabled(true);
		oiseau2.setEnabled(true);
		afficherInfo1.setVisible(false);
		afficherInfo2.setVisible(true);
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
			afficherStatJoueur(joueurCourant);
			m.aireDeJeu.setJoueurCourant(joueurCourant);
		}
		m.aireDeJeu.setListeCase(null);
		m.aireDeJeu.repaint();
		
		
		
	}
	
	private void afficherStatJoueur(boolean joueurCourant) {
		vie1.setVisible(joueurCourant);
		attaque1.setVisible(joueurCourant);
		defense1.setVisible(joueurCourant);
		precision1.setVisible(joueurCourant);
		vitesse1.setVisible(joueurCourant);
		portee1.setVisible(joueurCourant);
		mouvement1.setVisible(joueurCourant);
		vie2.setVisible(!joueurCourant);
		attaque2.setVisible(!joueurCourant);
		defense2.setVisible(!joueurCourant);
		precision2.setVisible(!joueurCourant);
		vitesse2.setVisible(!joueurCourant);
		portee2.setVisible(!joueurCourant);
		mouvement2.setVisible(!joueurCourant);
	}
	
	public int getFamille1() {
		return famille1;
	}
	
	public int getFamille2() {
		return famille2;
	}
	
	public int getClasse() {
		return classeSelect;
	}
	
	private void afficherStatPerso(int famille, int classe) {
		switch(classe) {
			case FabriquePion.ARCHER:
				archer(famille);
				break;
			case FabriquePion.ASSASSIN:
				assassin(famille);
				break;
			case FabriquePion.GUERRIER:
				guerrier(famille);
				break;
			case FabriquePion.TACTICIEN:
				tacticien(famille);
				break;
			case FabriquePion.TANK:
				tank(famille);
				break;
		}
	}
	
	private void archer(int famille) {
		ArrayList<Integer> stat = new ArrayList<Integer>();
		
		switch (famille) {
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
		
		afficherStat(joueurCourant, stat);
		
	}
	
	private void assassin(int famille) {
		ArrayList<Integer> stat = new ArrayList<Integer>();
		switch (famille) {
			case FabriquePion.REPTILE:
				stat.add(Assassin.VIE + Reptile.vieAssassin);
				stat.add(Assassin.FORCE + Reptile.forceAssassin);
				stat.add(Assassin.DEFENSE + Reptile.defenseAssassin);
				stat.add(Assassin.VITESSE + Reptile.vitesseAssassin);
				stat.add(Assassin.PRECISION + Reptile.precisionAssassin);
				stat.add(Assassin.PORTEE + Reptile.porteeAssassin);
				stat.add(Assassin.MOUVEMENT + Reptile.mouvementAssassin);
				break;
			case FabriquePion.OISEAU:
				stat.add(Assassin.VIE + Oiseau.vieAssassin);
				stat.add(Assassin.FORCE + Oiseau.forceAssassin);
				stat.add(Assassin.DEFENSE + Oiseau.defenseAssassin);
				stat.add(Assassin.VITESSE + Oiseau.vitesseAssassin);
				stat.add(Assassin.PRECISION + Oiseau.precisionAssassin);
				stat.add(Assassin.PORTEE + Oiseau.porteeAssassin);
				stat.add(Assassin.MOUVEMENT + Oiseau.mouvementAssassin);
				break;
			case FabriquePion.FELIN:
				stat.add(Assassin.VIE + Felin.vieAssassin);
				stat.add(Assassin.FORCE + Felin.forceAssassin);
				stat.add(Assassin.DEFENSE + Felin.defenseAssassin);
				stat.add(Assassin.VITESSE + Felin.vitesseAssassin);
				stat.add(Assassin.PRECISION + Felin.precisionAssassin);
				stat.add(Assassin.PORTEE + Felin.porteeAssassin);
				stat.add(Assassin.MOUVEMENT + Felin.mouvementAssassin);
				break;
		}
		
		afficherStat(joueurCourant, stat);
		
	}
	
	private void guerrier(int famille) {
		ArrayList<Integer> stat = new ArrayList<Integer>();
		switch (famille) {
			case FabriquePion.REPTILE:
				stat.add(Guerrier.VIE + Reptile.vieGuerrier);
				stat.add(Guerrier.FORCE + Reptile.forceGuerrier);
				stat.add(Guerrier.DEFENSE + Reptile.defenseGuerrier);
				stat.add(Guerrier.VITESSE + Reptile.vitesseGuerrier);
				stat.add(Guerrier.PRECISION + Reptile.precisionGuerrier);
				stat.add(Guerrier.PORTEE + Reptile.porteeGuerrier);
				stat.add(Guerrier.MOUVEMENT + Reptile.mouvementGuerrier);
				break;
			case FabriquePion.OISEAU:
				stat.add(Guerrier.VIE + Oiseau.vieGuerrier);
				stat.add(Guerrier.FORCE + Oiseau.forceGuerrier);
				stat.add(Guerrier.DEFENSE + Oiseau.defenseGuerrier);
				stat.add(Guerrier.VITESSE + Oiseau.vitesseGuerrier);
				stat.add(Guerrier.PRECISION + Oiseau.precisionGuerrier);
				stat.add(Guerrier.PORTEE + Oiseau.porteeGuerrier);
				stat.add(Guerrier.MOUVEMENT + Oiseau.mouvementGuerrier);
				break;
			case FabriquePion.FELIN:
				stat.add(Guerrier.VIE + Felin.vieGuerrier);
				stat.add(Guerrier.FORCE + Felin.forceGuerrier);
				stat.add(Guerrier.DEFENSE + Felin.defenseGuerrier);
				stat.add(Guerrier.VITESSE + Felin.vitesseGuerrier);
				stat.add(Guerrier.PRECISION + Felin.precisionGuerrier);
				stat.add(Guerrier.PORTEE + Felin.porteeGuerrier);
				stat.add(Guerrier.MOUVEMENT + Felin.mouvementGuerrier);
				break;
		}
		
		afficherStat(joueurCourant, stat);
		
	}
	
	private void tank(int famille) {
		ArrayList<Integer> stat = new ArrayList<Integer>();
		switch (famille) {
			case FabriquePion.REPTILE:
				stat.add(Tank.VIE + Reptile.vieTank);
				stat.add(Tank.FORCE + Reptile.forceTank);
				stat.add(Tank.DEFENSE + Reptile.defenseTank);
				stat.add(Tank.VITESSE + Reptile.vitesseTank);
				stat.add(Tank.PRECISION + Reptile.precisionTank);
				stat.add(Tank.PORTEE + Reptile.porteeTank);
				stat.add(Tank.MOUVEMENT + Reptile.mouvementTank);
				break;
			case FabriquePion.OISEAU:
				stat.add(Tank.VIE + Oiseau.vieTank);
				stat.add(Tank.FORCE + Oiseau.forceTank);
				stat.add(Tank.DEFENSE + Oiseau.defenseTank);
				stat.add(Tank.VITESSE + Oiseau.vitesseTank);
				stat.add(Tank.PRECISION + Oiseau.precisionTank);
				stat.add(Tank.PORTEE + Oiseau.porteeTank);
				stat.add(Tank.MOUVEMENT + Oiseau.mouvementTank);
				break;
			case FabriquePion.FELIN:
				stat.add(Tank.VIE + Felin.vieTank);
				stat.add(Tank.FORCE + Felin.forceTank);
				stat.add(Tank.DEFENSE + Felin.defenseTank);
				stat.add(Tank.VITESSE + Felin.vitesseTank);
				stat.add(Tank.PRECISION + Felin.precisionTank);
				stat.add(Tank.PORTEE + Felin.porteeTank);
				stat.add(Tank.MOUVEMENT + Felin.mouvementTank);
				break;
		}
		
		afficherStat(joueurCourant, stat);
		
	}
	
	private void tacticien(int famille) {
		ArrayList<Integer> stat = new ArrayList<Integer>();
		switch (famille) {
			case FabriquePion.REPTILE:
				stat.add(Tacticien.VIE + Reptile.vieTacticien);
				stat.add(Tacticien.FORCE + Reptile.forceTacticien);
				stat.add(Tacticien.DEFENSE + Reptile.defenseTacticien);
				stat.add(Tacticien.VITESSE + Reptile.vitesseTacticien);
				stat.add(Tacticien.PRECISION + Reptile.precisionTacticien);
				stat.add(Tacticien.PORTEE + Reptile.porteeTacticien);
				stat.add(Tacticien.MOUVEMENT + Reptile.mouvementTacticien);
				break;
			case FabriquePion.OISEAU:
				stat.add(Tacticien.VIE + Oiseau.vieTacticien);
				stat.add(Tacticien.FORCE + Oiseau.forceTacticien);
				stat.add(Tacticien.DEFENSE + Oiseau.defenseTacticien);
				stat.add(Tacticien.VITESSE + Oiseau.vitesseTacticien);
				stat.add(Tacticien.PRECISION + Oiseau.precisionTacticien);
				stat.add(Tacticien.PORTEE + Oiseau.porteeTacticien);
				stat.add(Tacticien.MOUVEMENT + Oiseau.mouvementTacticien);
				break;
			case FabriquePion.FELIN:
				stat.add(Tacticien.VIE + Felin.vieTacticien);
				stat.add(Tacticien.FORCE + Felin.forceTacticien);
				stat.add(Tacticien.DEFENSE + Felin.defenseTacticien);
				stat.add(Tacticien.VITESSE + Felin.vitesseTacticien);
				stat.add(Tacticien.PRECISION + Felin.precisionTacticien);
				stat.add(Tacticien.PORTEE + Felin.porteeTacticien);
				stat.add(Tacticien.MOUVEMENT + Felin.mouvementTacticien);
				break;
		}
		
		afficherStat(joueurCourant, stat);
		
	}
	
	private void afficherStat(boolean joueurCourant, ArrayList<Integer> stat) {
		if (joueurCourant) {
			vie1.setText("Vie : " + stat.get(0));
			attaque1.setText("Attaque : " + stat.get(1));
			defense1.setText("Defense : " + stat.get(2));
			precision1.setText("Precision : " + stat.get(3));
			vitesse1.setText("Vitesse : " + stat.get(4));
			portee1.setText("Portee : " + stat.get(5));
			mouvement1.setText("Attaque : " + stat.get(6));
		}
		else {
			vie2.setText("Vie : " + stat.get(0));
			attaque2.setText("Attaque : " + stat.get(1));
			defense2.setText("Defense : " + stat.get(2));
			precision2.setText("Precision : " + stat.get(3));
			vitesse2.setText("Vitesse : " + stat.get(4));
			portee2.setText("Portee : " + stat.get(5));
			mouvement2.setText("Attaque : " + stat.get(6));
		}
	}
	
	private void cacherStatPerso() {
		if (joueurCourant) {
			vie1.setText("");
			attaque1.setText("");
			defense1.setText("");
			precision1.setText("");
			vitesse1.setText("");
			portee1.setText("");
			mouvement1.setText("");
		}
		else {
			vie2.setText("");
			attaque2.setText("");
			defense2.setText("");
			precision2.setText("");
			vitesse2.setText("");
			portee2.setText("");
			mouvement2.setText("");
		}
	}
	
	private void afficherFamille(boolean joueurCourant, int famille) {
		if (joueurCourant) {
			switch(famille) {
				case FabriquePion.FELIN:
					afficherInfo1.setText(FELIN_DESCR);
					break;
				case FabriquePion.OISEAU:
					afficherInfo1.setText(OISEAU_DESCR);
					break;
				case FabriquePion.REPTILE:
					afficherInfo1.setText(REPTILE_DESCR);
					break;
			}
			archer1.setIcon(new ImageIcon(Textures.getPersonnage(famille, FabriquePion.ARCHER, Orientation.SUD)));
			assassin1.setIcon(new ImageIcon(Textures.getPersonnage(famille, FabriquePion.ASSASSIN, Orientation.SUD)));
			guerrier1.setIcon(new ImageIcon(Textures.getPersonnage(famille, FabriquePion.GUERRIER, Orientation.SUD)));
			tacticien1.setIcon(new ImageIcon(Textures.getPersonnage(famille, FabriquePion.TACTICIEN, Orientation.SUD)));
			tank1.setIcon(new ImageIcon(Textures.getPersonnage(famille, FabriquePion.TANK, Orientation.SUD)));
		}
		else {
			switch(famille) {
				case FabriquePion.FELIN:
					afficherInfo2.setText(FELIN_DESCR);
					break;
				case FabriquePion.OISEAU:
					afficherInfo2.setText(OISEAU_DESCR);
					break;
				case FabriquePion.REPTILE:
					afficherInfo2.setText(REPTILE_DESCR);
					break;
			}
			archer2.setIcon(new ImageIcon(Textures.getPersonnage(famille, FabriquePion.ARCHER, Orientation.SUD)));
			assassin2.setIcon(new ImageIcon(Textures.getPersonnage(famille, FabriquePion.ASSASSIN, Orientation.SUD)));
			guerrier2.setIcon(new ImageIcon(Textures.getPersonnage(famille, FabriquePion.GUERRIER, Orientation.SUD)));
			tacticien2.setIcon(new ImageIcon(Textures.getPersonnage(famille, FabriquePion.TACTICIEN, Orientation.SUD)));
			tank2.setIcon(new ImageIcon(Textures.getPersonnage(famille, FabriquePion.TANK, Orientation.SUD)));
		}
	}
	
	private void cacherFamille(boolean joueurCourant) {
		if (joueurCourant) {
			afficherInfo1.setText("");
			archer1.setIcon(null);
			assassin1.setIcon(null);
			guerrier1.setIcon(null);
			tacticien1.setIcon(null);
			tank1.setIcon(null);
		}
		else {
			afficherInfo2.setText("");
			archer2.setIcon(null);
			assassin2.setIcon(null);
			guerrier2.setIcon(null);
			tacticien2.setIcon(null);
			tank2.setIcon(null);
		}
	}
	
	public void setChoix(boolean choix) {
		this.choix = choix;
	}
	
	public boolean getChoix() {
		return choix;
	}
	
	public void pionAjouteJoueur2() {
		nbPions++;
		m.aireDeJeu.setListeCase(null);
		if (nbPions % 2 == 1) {
			archer1.setEnabled(true);
			guerrier1.setEnabled(true);
			assassin1.setEnabled(true);
			tank1.setEnabled(true);
			tacticien1.setEnabled(true);
			archer2.setEnabled(false);
			guerrier2.setEnabled(false);
			assassin2.setEnabled(false);
			tank2.setEnabled(false);
			tacticien2.setEnabled(false);
			joueurCourant = !joueurCourant;
			afficherStatJoueur(joueurCourant);
			
			m.aireDeJeu.setJoueurCourant(joueurCourant);
			
		}
		m.aireDeJeu.repaint();
	}
	
	public int getNbPions() {
		return nbPions;
	}
	
	public void elireCommandant() {
		if (etape != 3) {
			etape++;
			vie2.setVisible(false);
			attaque2.setVisible(false);
			defense2.setVisible(false);
			precision2.setVisible(false);
			vitesse2.setVisible(false);
			portee2.setVisible(false);
			mouvement2.setVisible(false);
			vie1.setVisible(false);
			attaque1.setVisible(false);
			defense1.setVisible(false);
			precision1.setVisible(false);
			vitesse1.setVisible(false);
			portee1.setVisible(false);
			mouvement1.setVisible(false);
		}
		else {
			joueurCourant = !joueurCourant;
		}
		
		setListeAireDejeu();
		if (joueurCourant) {
			afficherInfo1.setText("<html><p align=\"justify\"> Veuillez choisir votre commandant !</p></html>");
			afficherInfo1.setVisible(true);
		}
		else {
			afficherInfo1.setVisible(false);
			afficherInfo2.setText("<html><p align=\"justify\"> Veuillez choisir votre commandant !</p></html>");
			afficherInfo2.setVisible(true);
		}
		m.aireDeJeu.setJoueurCourant(joueurCourant);
		m.aireDeJeu.repaint();
		repaint();
	}
	
	public void setListeAireDejeu() {
		caseJoueur.clear();
		if (joueurCourant) {
			if (etape == 2) {
				for (int i = 0; i < m.getPlateau().getNbLigne(); i++) {
					for (int j = 0; j < 6; j++) {
						if (!m.getPlateau().get(i, j).isObstacleDeplacement()
								&& !m.getPlateau().get(i, j).isOccupee()) {
							caseJoueur.add(m.getPlateau().get(i, j));
						}
					}
				}
			}
			if (etape == 3) {
				for (int i = 0; i < m.getPlateau().getNbLigne(); i++) {
					for (int j = 0; j < 6; j++) {
						if (m.getPlateau().get(i, j).getPion() != null) {
							caseJoueur.add(m.getPlateau().get(i, j));
						}
					}
				}
			}
		}
		else {
			if (etape == 2) {
				for (int i = 0; i < m.getPlateau().getNbLigne(); i++) {
					for (int j = m.getPlateau().getNbColonne() - 6; j < m.getPlateau().getNbColonne(); j++) {
						if (!m.getPlateau().get(i, j).isObstacleDeplacement()
								&& !m.getPlateau().get(i, j).isOccupee()) {
							caseJoueur.add(m.getPlateau().get(i, j));
						}
					}
				}
			}
			if (etape == 3) {
				for (int i = 0; i < m.getPlateau().getNbLigne(); i++) {
					for (int j = m.getPlateau().getNbColonne() - 6; j < m.getPlateau().getNbColonne(); j++) {
						if (m.getPlateau().get(i, j).getPion() != null) {
							caseJoueur.add(m.getPlateau().get(i, j));
						}
					}
				}
			}
		}
		
		m.aireDeJeu.setListeCase(caseJoueur);
	}
	
	public boolean choixPossible(Case c) {
		return caseJoueur.contains(c);
	}
}
