package ihm;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JSpinner.NumberEditor;
import javax.swing.JTextField;
import moteur.Case;
import moteur.FabriquePion;
import moteur.Moteur;
import moteur.Orientation;
import moteur.Plateau;
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
 * @author Anthony
 */

// implements utile pour empêcher l'aireDeJeu de reçevoir les clics
public class NouvellePartiePanel extends javax.swing.JPanel implements MouseListener, MouseMotionListener {

	private static final Color selectedColPanel = new Color(204, 0, 0);
	private static final Color defaultColPanel = Color.GRAY;
	private static final Color selectedColButton = Color.ORANGE;
	private static final Color defaultColButton = new Color(238,238,238);
	
	private static final String FELIN_DESCR = "<html><h2>Félin</h2><p align=\"justify\">Les félins procurent un bonus en défense et en chance, et un malus en vie."
			+ "<br/><br/>Le tacticien procure aux unités 1 de mouvement supplémentaire."
			+ "<br/>Il procure la capacité spéciale \"Rage\" à chaque unité. La prochaine attaque sera un coup critique à coup sûr, au prochain tour, il aura une précision de 0. "
			+ "Elle ne peut s'utiliser qu'une fois tout les trois tours.</p></html>";
	private static final String OISEAU_DESCR = "<html><h2>Oiseau</h2><p align=\"justify\">Les oiseaux procurent un bonus en attaque et en vie, et un malus en vitesse."
			+ "<br/><br/>Le tacticien procure aux unités 1 de portée supplémentaire."
			+ "<br/>Il procure la capacité spéciale \"Envol\" à chaque unité. L'oiseau vol alors jusqu'à la fin de son prochain tour, il est inattaquable durant ce temps. "
			+ "Elle ne peut s'utiliser qu'une fois tout les trois tours.</p></html>";
	private static final String REPTILE_DESCR = "<html><h2>Reptile</h2><p align=\"justify\">Les reptiles procurent un bonus en attaque et en vie, et un malus en vitesse."
			+ "<br/><br/>Le tacticien procure au joueur un bonus de 3 actions,"
			+ "<br/>Il procure la capacité spéciale \"Mue\" à chaque unité. Elle rend 7 pv à chaque utilisation. "
			+ "Elle ne peut s'utiliser qu'une fois tout les trois tours.</p></html>";
	
	private Moteur m;
	private ArrayList<Case> caseJoueur;
	private boolean joueurCourant;
	private final boolean JOUEUR1 = true;
	private final boolean JOUEUR2 = false;
	private int famille1;
	private int famille2;
	private int etape;
	private int nbPions;
	private int classeSelect;
	private boolean choix;

	
	/**
	 * Crée un nouveau form NouvellePartieJPanel
	 * 
	 * @param moteur 
	 */
	public NouvellePartiePanel(Moteur moteur) {
		m = moteur;
		joueurCourant = JOUEUR1;
		choix = false;
		etape = 0;
		nbPions = 0;
		
		addMouseListener(this);
		addMouseMotionListener(this);
		
		caseJoueur = new ArrayList<Case>();
		
		initComponents();
		setEnabled(j1NomPanel, true);
		setEnabled(j1FamillePanel, false);
		setEnabled(j1ClassePanel, false);
		setVisible(j1TextePanel, false);
		setEnabled(j2NomPanel, false);
		setEnabled(j2FamillePanel, false);
		setEnabled(j2ClassePanel, false);
		setVisible(j2TextePanel, false);
		
		mapComboBoxActionPerformed(null);
		
		class EcouteurBoutonFamille extends MouseAdapter implements ActionListener {

			private boolean joueur;
			private int famille;

			public EcouteurBoutonFamille(boolean joueur, int famille) {
				this.joueur = joueur;
				this.famille = famille;
			}

			@Override
			public void actionPerformed(ActionEvent e) {
				afficherFamille(joueur, famille);
				((Component)e.getSource()).setBackground(selectedColButton);
				if (joueur == JOUEUR1) {
					choixFamilleJoueur1(famille);
				} else {
					choixFamilleJoueur2(famille);
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				if (e.getComponent().isEnabled()) {
					afficherFamille(joueur, famille);
				}
			}

			@Override
			public void mouseExited(MouseEvent e) {
				if (e.getComponent().isEnabled()) {
					cacherFamille(joueur);
				}
			}
		}

		class EcouteurBoutonClasse extends MouseAdapter implements ActionListener {

			private boolean joueur;
			private int classe;
			private String nomClasse;
			private JLabel classeLabel;

			public EcouteurBoutonClasse(boolean joueur, int classe) {
				this.joueur = joueur;
				this.classe = classe;
				
				if(joueur == JOUEUR1) {
					classeLabel = classe1;
				}
				else {
					classeLabel = classe2;
				}
					
				switch(classe) {
					case FabriquePion.ARCHER:
						nomClasse = "Archer";
						break;
					case FabriquePion.ASSASSIN:
						nomClasse = "Assassin";
						break;
					case FabriquePion.GUERRIER:
						nomClasse = "Guerrier";
						break;
					case FabriquePion.TACTICIEN:
						nomClasse = "Tacticien";
						break;
					case FabriquePion.TANK:
						nomClasse = "Tank";
						break;
				}
			}

			@Override
			public void actionPerformed(ActionEvent e) {
				if(choix) {
					JButton deselect = getButtonClasse(classeSelect, joueurCourant);
					if(deselect != null) {
						deselect.setBackground(defaultColButton);
					}
				}
				((Component)e.getSource()).setBackground(selectedColButton);
				
				classeSelect = classe;
				choix = true;
				
				setListeAireDejeu();
				m.getAireDeJeu().repaint();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				if (!choix && e.getComponent().isEnabled()) {
					classeLabel.setText("<html><h3>"+nomClasse+"</h3></html>");
					afficherStatPerso(joueur ? famille1 : famille2, classe);
				}
			}

			@Override
			public void mouseExited(MouseEvent e) {
				if (!choix && e.getComponent().isEnabled()) {
					classeLabel.setText("");
					cacherStatPerso();
				}
			}
		}

		EcouteurBoutonFamille ebf;
		
		ebf = new EcouteurBoutonFamille(JOUEUR1, FabriquePion.REPTILE);
		reptile1.addActionListener(ebf);
		reptile1.addMouseListener(ebf);
		ebf = new EcouteurBoutonFamille(JOUEUR1, FabriquePion.OISEAU);
		oiseau1.addActionListener(ebf);
		oiseau1.addMouseListener(ebf);
		ebf = new EcouteurBoutonFamille(JOUEUR1, FabriquePion.FELIN);
		felin1.addActionListener(ebf);
		felin1.addMouseListener(ebf);
		
		ebf = new EcouteurBoutonFamille(JOUEUR2, FabriquePion.REPTILE);
		reptile2.addActionListener(ebf);
		reptile2.addMouseListener(ebf);
		ebf = new EcouteurBoutonFamille(JOUEUR2, FabriquePion.OISEAU);
		oiseau2.addActionListener(ebf);
		oiseau2.addMouseListener(ebf);
		ebf = new EcouteurBoutonFamille(JOUEUR2, FabriquePion.FELIN);
		felin2.addActionListener(ebf);
		felin2.addMouseListener(ebf);

		
		EcouteurBoutonClasse ebc;
		
		ebc = new EcouteurBoutonClasse(JOUEUR1, FabriquePion.ARCHER);
		archer1.addActionListener(ebc);
		archer1.addMouseListener(ebc);
		ebc = new EcouteurBoutonClasse(JOUEUR1, FabriquePion.ASSASSIN);
		assassin1.addActionListener(ebc);
		assassin1.addMouseListener(ebc);
		ebc = new EcouteurBoutonClasse(JOUEUR1, FabriquePion.GUERRIER);
		guerrier1.addActionListener(ebc);
		guerrier1.addMouseListener(ebc);
		ebc = new EcouteurBoutonClasse(JOUEUR1, FabriquePion.TACTICIEN);
		tacticien1.addActionListener(ebc);
		tacticien1.addMouseListener(ebc);
		ebc = new EcouteurBoutonClasse(JOUEUR1, FabriquePion.TANK);
		tank1.addActionListener(ebc);
		tank1.addMouseListener(ebc);
		
		ebc = new EcouteurBoutonClasse(JOUEUR2, FabriquePion.ARCHER);
		archer2.addActionListener(ebc);
		archer2.addMouseListener(ebc);
		ebc = new EcouteurBoutonClasse(JOUEUR2, FabriquePion.ASSASSIN);
		assassin2.addActionListener(ebc);
		assassin2.addMouseListener(ebc);
		ebc = new EcouteurBoutonClasse(JOUEUR2, FabriquePion.GUERRIER);
		guerrier2.addActionListener(ebc);
		guerrier2.addMouseListener(ebc);
		ebc = new EcouteurBoutonClasse(JOUEUR2, FabriquePion.TACTICIEN);
		tacticien2.addActionListener(ebc);
		tacticien2.addMouseListener(ebc);
		ebc = new EcouteurBoutonClasse(JOUEUR2, FabriquePion.TANK);
		tank2.addActionListener(ebc);
		tank2.addMouseListener(ebc);
		
		j1Panel.setVisible(false);
		j2Panel.setVisible(false);
		m.getAireDeJeu().add(this);
		setLocation(Case.TAILLE * 6, 0);
		setVisible(true);
		setSize(Case.TAILLE * 23, Case.TAILLE * 20);
	}
	
	/**
	 * Constructeur sans paramètre pour l'aperçu du form
	 */
	public NouvellePartiePanel() {
		this(new Moteur());
	}

	private void setEnabled(javax.swing.JPanel panel, boolean b) {
		for(Component c : panel.getComponents()) {
			c.setEnabled(b);
		}
		if(b) {
			panel.setBackground(selectedColPanel);
		}
		else {
			panel.setBackground(defaultColPanel);
		}
	}

	private void setVisible(javax.swing.JPanel panel, boolean b){
		for(Component c : panel.getComponents()) {
			c.setVisible(b);
		}
	}
	
	private JButton getButtonClasse(int classe, boolean joueur) {
		JButton button;
		switch(classe) {
			case FabriquePion.ARCHER:
				button = joueur ? archer1 : archer2;
				break;
			case FabriquePion.ASSASSIN:
				button = joueur ? assassin1 : assassin2;
				break;
			case FabriquePion.GUERRIER:
				button = joueur ? guerrier1 : guerrier2;
				break;
			case FabriquePion.TACTICIEN:
				button = joueur ? tacticien1 : tacticien2;
				break;
			case FabriquePion.TANK:
				button = joueur ? tank1 : tank2;
				break;
			default:
				button = null;
		}
		return button;
	}

	/**
	 * Accesseur de l'étape
	 * @return
	 */
	public int getEtape() {
		return etape;
	}

	/**
	 * Affecteur de l'étape
	 * @param etape
	 */
	public void setEtape(int etape) {
		this.etape = etape;
	}

	/**
	 * Renvoie true si joueur 1 et false si joueur 2
	 * @return Booléen joueurCourant
	 */
	public boolean isJoueurCourant() {
		return joueurCourant;
	}

	/**
	 * Affecteur booléen joueur courant
	 * @param joueurCourant
	 */
	public void setJoueurCourant(boolean joueurCourant) {
		this.joueurCourant = joueurCourant;
	}

	/**
	 * Accesseur du booléen si choix de la classe du pion
	 * @return
	 */
	public boolean getChoix() {
		return choix;
	}

	/**
	 * Affecteur du booléen si choix de la classe du pion
	 * @param choix
	 */
	public void setChoix(boolean choix) {
		this.choix = choix;
		if(!choix) {
			JButton deselect = getButtonClasse(classeSelect, (nbPions % 2 == 0 ? joueurCourant : !joueurCourant));
			if(deselect != null) {
				deselect.setBackground(defaultColButton);
			}
		}
	}

	/**
	 * Accesseur du nombre total de pion posé sur le plateau
	 * @return nbPions
	 */
	public int getNbPions() {
		return nbPions;
	}

	/**
	 * Accesseur de la famille du joueur 1
	 * @return famille1
	 */
	public int getFamille1() {
		return famille1;
	}

	/**
	 * Accesseur de la famille du joueur 2
	 * @return famille2
	 */
	public int getFamille2() {
		return famille2;
	}

	/**
	 * Accesseur de la classe de pion choisie
	 * @return
	 */
	public int getClasse() {
		return classeSelect;
	}
	
	/**
	 * Renvoie vrai si le pion peut être créé sur la case c
	 * @param c
	 * @return c dans caseJoueur
	 */
	public boolean choixPossible(Case c) {
		return caseJoueur.contains(c);
	}
	
	/**
	 * Fin de l'ajout de pion au joueur 1
	 */
	public void pionAjouteJoueur1() {
		nbPions++;
		if (m.getJoueur1().getTacticien() != null) {
			tacticien1.setEnabled(false);
		}
		if (nbPions % 2 == 1) {
			joueurCourant = !joueurCourant;
			setEnabled(j1ClassePanel, false);
			setEnabled(j2ClassePanel, true);
			if (m.getJoueur2().getTacticien() != null) {
				tacticien2.setEnabled(false);
			}
			afficherStatJoueur(joueurCourant);
			m.getAireDeJeu().setJoueurCourant(joueurCourant);
		}
		m.getAireDeJeu().setListeCase(null);
		m.getAireDeJeu().repaint();
	}
	
	/**
	 * Fin de l'ajout de pion au joueur 2
	 */
	public void pionAjouteJoueur2() {
		nbPions++;
		m.getAireDeJeu().setListeCase(null);
		if (m.getJoueur2().getTacticien() != null) {
			tacticien2.setEnabled(false);
		}
		if (nbPions % 2 == 1) {
			joueurCourant = !joueurCourant;
			setEnabled(j1ClassePanel, true);
			setEnabled(j2ClassePanel, false);
			if (m.getJoueur1().getTacticien() != null) {
				tacticien1.setEnabled(false);
			}
			afficherStatJoueur(joueurCourant);
			m.getAireDeJeu().setJoueurCourant(joueurCourant);
		}
		//m.getAireDeJeu().setListeCase(null);
		m.getAireDeJeu().repaint();
	}
	
	/**
	 * Choix du commandant
	 */
	public void elireCommandant() {
		if (etape != 3) {
			etape++;
			setEnabled(j1ClassePanel, false);
			setVisible(j1TextePanel, false);
			setVisible(j2TextePanel, false);
		} else {
			joueurCourant = !joueurCourant;
		}

		setListeAireDejeu();
		if (joueurCourant) {
			afficherInfo1.setText("<html><h2> Veuillez choisir votre commandant !</h2></html>");
			afficherInfo1.setVisible(true);
			j1TextePanel.setBackground(selectedColPanel);
		} else {
			afficherInfo2.setText("<html><h2> Veuillez choisir votre commandant !</h2></html>");
			afficherInfo2.setVisible(true);
			j2TextePanel.setBackground(selectedColPanel);
			
			afficherInfo1.setVisible(false);
			j1TextePanel.setBackground(defaultColPanel);
		}
		m.getAireDeJeu().setJoueurCourant(joueurCourant);
		m.getAireDeJeu().repaint();
//		repaint();
	}

	private void setListeAireDejeu() {
		caseJoueur.clear();
		final int jmin, jmax;
		if (joueurCourant) {
			jmin = 0;
			jmax = 6;
		}
		else {
			jmin = Plateau.NB_COLONNE - 6;
			jmax = Plateau.NB_COLONNE;
		}
		
		if (etape == 2) {
			for (int i = 0; i < Plateau.NB_LIGNE; i++) {
				for (int j = jmin; j < jmax; j++) {
					if (!m.getPlateau().get(i, j).isObstacleDeplacement()
							&& !m.getPlateau().get(i, j).contientPion()) {
						caseJoueur.add(m.getPlateau().get(i, j));
					}
				}
			}
		}
		if (etape == 3) {
			for (int i = 0; i < Plateau.NB_LIGNE; i++) {
				for (int j = jmin; j < jmax; j++) {
					if (m.getPlateau().get(i, j).getPion() != null) {
						caseJoueur.add(m.getPlateau().get(i, j));
					}
				}
			}
		}
			
		m.getAireDeJeu().setListeCase(caseJoueur);
	}

	private void choixFamilleJoueur1(int famille) {
		setEnabled(j1FamillePanel, false);
		setEnabled(j2FamillePanel, true);
		
		afficherInfo1.setVisible(false);
		afficherInfo2.setVisible(true);
		
		joueurCourant = !joueurCourant;
		famille1 = famille;
	}
	
	private void choixFamilleJoueur2(int famille) {
		setEnabled(j2FamillePanel, false);
		setEnabled(j1ClassePanel, true);
		
		afficherInfo2.setVisible(false);
		joueurCourant = !joueurCourant;
		famille2 = famille;
		etape++;
		
		afficherStatJoueur(joueurCourant);
		m.getAireDeJeu().setJoueurCourant(joueurCourant);
		m.getAireDeJeu().repaint();
	}
	
	private void afficherFamille(boolean joueurCourant, int famille) {
		if (joueurCourant) {
			switch (famille) {
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
		} else {
			switch (famille) {
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
		} else {
			afficherInfo2.setText("");
			archer2.setIcon(null);
			assassin2.setIcon(null);
			guerrier2.setIcon(null);
			tacticien2.setIcon(null);
			tank2.setIcon(null);
		}
	}
	
	private void afficherStatJoueur(boolean joueurCourant) {
		setVisible(j1TextePanel, joueurCourant);
		setVisible(j2TextePanel, !joueurCourant);
		afficherInfo1.setVisible(false);
		afficherInfo2.setVisible(false);
	}
	
	private void afficherStatPerso(int famille, int classe) {
		switch (classe) {
			case FabriquePion.ARCHER:
				afficherStatArcher(famille);
				break;
			case FabriquePion.ASSASSIN:
				afficherStatAssassin(famille);
				break;
			case FabriquePion.GUERRIER:
				afficherStatGuerrier(famille);
				break;
			case FabriquePion.TACTICIEN:
				afficherStatTacticien(famille);
				break;
			case FabriquePion.TANK:
				afficherStatTank(famille);
				break;
		}
	}
	
	private void afficherStatArcher(int famille) {
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
		afficherStatPerso(joueurCourant, stat);
	}

	private void afficherStatAssassin(int famille) {
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
		afficherStatPerso(joueurCourant, stat);
	}

	private void afficherStatGuerrier(int famille) {
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
		afficherStatPerso(joueurCourant, stat);
	}

	private void afficherStatTank(int famille) {
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
		afficherStatPerso(joueurCourant, stat);
	}

	private void afficherStatTacticien(int famille) {
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
		afficherStatPerso(joueurCourant, stat);
	}
	
	private void afficherStatPerso(boolean joueurCourant, ArrayList<Integer> stat) {
		if (joueurCourant) {
			vie1.setText("Vie : " + stat.get(0));
			attaque1.setText("Attaque : " + stat.get(1));
			defense1.setText("Défense : " + stat.get(2));
			vitesse1.setText("Vitesse : " + stat.get(3));
			precision1.setText("Précision : " + stat.get(4));
			portee1.setText("Portée : " + stat.get(5));
			mouvement1.setText("Mouvement : " + stat.get(6));
		} else {
			vie2.setText("Vie : " + stat.get(0));
			attaque2.setText("Attaque : " + stat.get(1));
			defense2.setText("Défense : " + stat.get(2));
			vitesse2.setText("Vitesse : " + stat.get(3));
			precision2.setText("Précision : " + stat.get(4));
			portee2.setText("Portée : " + stat.get(5));
			mouvement2.setText("Mouvement : " + stat.get(6));
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
		} else {
			vie2.setText("");
			attaque2.setText("");
			defense2.setText("");
			precision2.setText("");
			vitesse2.setText("");
			portee2.setText("");
			mouvement2.setText("");
		}
	}
	
	private ComboBoxModel getFichiersMap() {
		ArrayList<Couple<String, String>> liste = new ArrayList<Couple<String, String>>();
		File f = new File("map/");
		String[] l = f.list(new FilenameFilter() {

			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith(".map");
			}
		});
		
		for(String s : l) {
			liste.add(new Couple<String, String>("map/"+s, s.substring(0, s.length()-4)));
		}
		return new DefaultComboBoxModel(liste.toArray());
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        mapPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        mapComboBox = new javax.swing.JComboBox();
        mapValider = new javax.swing.JButton();
        nbPionsSpinner = new javax.swing.JSpinner();
        jPanel2 = new javax.swing.JPanel();
        previsualisation = new ihm.Previsualisation();
        jLabel2 = new javax.swing.JLabel();
        j1Panel = new javax.swing.JPanel();
        j1NomPanel = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        j1Nom = new javax.swing.JTextField();
        j1Valider = new javax.swing.JButton();
        j1FamillePanel = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        reptile1 = new javax.swing.JButton();
        oiseau1 = new javax.swing.JButton();
        felin1 = new javax.swing.JButton();
        j1ClassePanel = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        archer1 = new javax.swing.JButton();
        assassin1 = new javax.swing.JButton();
        guerrier1 = new javax.swing.JButton();
        tacticien1 = new javax.swing.JButton();
        tank1 = new javax.swing.JButton();
        j1TextePanel = new javax.swing.JPanel();
        classe1 = new javax.swing.JLabel();
        vie1 = new javax.swing.JLabel();
        attaque1 = new javax.swing.JLabel();
        defense1 = new javax.swing.JLabel();
        precision1 = new javax.swing.JLabel();
        vitesse1 = new javax.swing.JLabel();
        portee1 = new javax.swing.JLabel();
        mouvement1 = new javax.swing.JLabel();
        afficherInfo1 = new javax.swing.JLabel();
        j2Panel = new javax.swing.JPanel();
        j2NomPanel = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        j2Nom = new javax.swing.JTextField();
        j2Valider = new javax.swing.JButton();
        j2FamillePanel = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        reptile2 = new javax.swing.JButton();
        oiseau2 = new javax.swing.JButton();
        felin2 = new javax.swing.JButton();
        j2ClassePanel = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        archer2 = new javax.swing.JButton();
        assassin2 = new javax.swing.JButton();
        guerrier2 = new javax.swing.JButton();
        tacticien2 = new javax.swing.JButton();
        tank2 = new javax.swing.JButton();
        j2TextePanel = new javax.swing.JPanel();
        classe2 = new javax.swing.JLabel();
        vie2 = new javax.swing.JLabel();
        attaque2 = new javax.swing.JLabel();
        defense2 = new javax.swing.JLabel();
        precision2 = new javax.swing.JLabel();
        vitesse2 = new javax.swing.JLabel();
        portee2 = new javax.swing.JLabel();
        mouvement2 = new javax.swing.JLabel();
        afficherInfo2 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(64, 64, 64));
        setMinimumSize(new Dimension(Case.TAILLE * 23, Case.TAILLE * 20));
        setLayout(new java.awt.GridBagLayout());

        mapPanel.setBackground(new java.awt.Color(128, 128, 128));
        mapPanel.setLayout(new java.awt.GridBagLayout());

        jLabel1.setText("Choisissez la carte :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        mapPanel.add(jLabel1, gridBagConstraints);

        mapComboBox.setModel(getFichiersMap());
        mapComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mapComboBoxActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 5, 10);
        mapPanel.add(mapComboBox, gridBagConstraints);

        mapValider.setText("Valider");
        mapValider.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mapValiderActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 5, 10);
        mapPanel.add(mapValider, gridBagConstraints);

        nbPionsSpinner.setModel(new javax.swing.SpinnerNumberModel(6, 2, 15, 2));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 5, 10);
        mapPanel.add(nbPionsSpinner, gridBagConstraints);

        jPanel2.setBackground(new java.awt.Color(128, 128, 128));
        jPanel2.setLayout(new java.awt.BorderLayout());
        jPanel2.add(previsualisation, java.awt.BorderLayout.CENTER);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 5.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        mapPanel.add(jPanel2, gridBagConstraints);

        jLabel2.setText("<html>Nombre de personnages par équipe (pair) :</html>");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 5, 10);
        mapPanel.add(jLabel2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(15, 15, 15, 15);
        add(mapPanel, gridBagConstraints);

        j1Panel.setBackground(new java.awt.Color(64, 64, 64));
        j1Panel.setPreferredSize(new Dimension(Case.TAILLE * 10, Case.TAILLE * 20));
        j1Panel.setLayout(new java.awt.GridBagLayout());

        j1NomPanel.setBackground(new java.awt.Color(128, 128, 128));
        j1NomPanel.setLayout(new java.awt.GridBagLayout());

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Entrez le nom du joueur 1 :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 20;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        j1NomPanel.add(jLabel8, gridBagConstraints);

        j1Nom.setText("Joueur 1");
        j1Nom.setMargin(new java.awt.Insets(5, 5, 5, 5));
        j1Nom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                j1NomActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.7;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 10, 10);
        j1NomPanel.add(j1Nom, gridBagConstraints);

        j1Valider.setText("Ok");
        j1Valider.setMargin(new java.awt.Insets(4, 4, 4, 4));
        j1Valider.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                j1ValiderActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.2;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 10, 10);
        j1NomPanel.add(j1Valider, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        j1Panel.add(j1NomPanel, gridBagConstraints);

        j1FamillePanel.setBackground(new java.awt.Color(128, 128, 128));
        j1FamillePanel.setLayout(new java.awt.GridBagLayout());

        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Choisissez une famille :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        j1FamillePanel.add(jLabel10, gridBagConstraints);

        reptile1.setIcon(new ImageIcon(Textures.getPersonnage(Textures.ASSASSINREPTILE, Orientation.SUD)));
        reptile1.setToolTipText("Reptile");
        reptile1.setMargin(new java.awt.Insets(2, 2, 2, 2));
        reptile1.setMaximumSize(new java.awt.Dimension(45, 45));
        reptile1.setMinimumSize(new java.awt.Dimension(40, 40));
        reptile1.setPreferredSize(new java.awt.Dimension(45, 45));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 10, 10);
        j1FamillePanel.add(reptile1, gridBagConstraints);

        oiseau1.setIcon(new ImageIcon(Textures.getPersonnage(Textures.ASSASSINOISEAU, Orientation.SUD)));
        oiseau1.setToolTipText("Oiseau");
        oiseau1.setMargin(new java.awt.Insets(2, 2, 2, 2));
        oiseau1.setMaximumSize(new java.awt.Dimension(45, 45));
        oiseau1.setMinimumSize(new java.awt.Dimension(40, 40));
        oiseau1.setPreferredSize(new java.awt.Dimension(45, 45));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 10, 10);
        j1FamillePanel.add(oiseau1, gridBagConstraints);

        felin1.setIcon(new ImageIcon(Textures.getPersonnage(Textures.ASSASSINFELIN, Orientation.SUD)));
        felin1.setToolTipText("Félin");
        felin1.setMargin(new java.awt.Insets(2, 2, 2, 2));
        felin1.setMaximumSize(new java.awt.Dimension(45, 45));
        felin1.setMinimumSize(new java.awt.Dimension(40, 40));
        felin1.setPreferredSize(new java.awt.Dimension(45, 45));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 10, 10);
        j1FamillePanel.add(felin1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 10, 0);
        j1Panel.add(j1FamillePanel, gridBagConstraints);

        j1ClassePanel.setBackground(new java.awt.Color(128, 128, 128));
        j1ClassePanel.setLayout(new java.awt.GridBagLayout());

        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Choisissez un pion :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.ipadx = 5;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        j1ClassePanel.add(jLabel11, gridBagConstraints);

        archer1.setToolTipText("Archer");
        archer1.setMargin(new java.awt.Insets(2, 2, 2, 2));
        archer1.setMaximumSize(new java.awt.Dimension(40, 40));
        archer1.setMinimumSize(new java.awt.Dimension(35, 35));
        archer1.setPreferredSize(new java.awt.Dimension(35, 35));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 10, 5);
        j1ClassePanel.add(archer1, gridBagConstraints);

        assassin1.setToolTipText("Assassin");
        assassin1.setMargin(new java.awt.Insets(2, 2, 2, 2));
        assassin1.setMaximumSize(new java.awt.Dimension(40, 40));
        assassin1.setMinimumSize(new java.awt.Dimension(35, 35));
        assassin1.setPreferredSize(new java.awt.Dimension(35, 35));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 10, 5);
        j1ClassePanel.add(assassin1, gridBagConstraints);

        guerrier1.setToolTipText("Guerrier");
        guerrier1.setMargin(new java.awt.Insets(2, 2, 2, 2));
        guerrier1.setMaximumSize(new java.awt.Dimension(40, 40));
        guerrier1.setMinimumSize(new java.awt.Dimension(35, 35));
        guerrier1.setPreferredSize(new java.awt.Dimension(35, 35));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 10, 5);
        j1ClassePanel.add(guerrier1, gridBagConstraints);

        tacticien1.setToolTipText("Tacticien");
        tacticien1.setMargin(new java.awt.Insets(2, 2, 2, 2));
        tacticien1.setMaximumSize(new java.awt.Dimension(40, 40));
        tacticien1.setMinimumSize(new java.awt.Dimension(35, 35));
        tacticien1.setPreferredSize(new java.awt.Dimension(35, 35));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 10, 5);
        j1ClassePanel.add(tacticien1, gridBagConstraints);

        tank1.setToolTipText("Tank");
        tank1.setMargin(new java.awt.Insets(2, 2, 2, 2));
        tank1.setMaximumSize(new java.awt.Dimension(40, 40));
        tank1.setMinimumSize(new java.awt.Dimension(35, 35));
        tank1.setPreferredSize(new java.awt.Dimension(35, 35));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 10, 5);
        j1ClassePanel.add(tank1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 10, 0);
        j1Panel.add(j1ClassePanel, gridBagConstraints);

        j1TextePanel.setBackground(new java.awt.Color(128, 128, 128));
        j1TextePanel.setMinimumSize(new java.awt.Dimension(200, 300));
        j1TextePanel.setPreferredSize(new java.awt.Dimension(315, 200));
        j1TextePanel.setLayout(new java.awt.GridBagLayout());

        classe1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        j1TextePanel.add(classe1, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(0, 30, 10, 10);
        j1TextePanel.add(vie1, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(10, 30, 10, 10);
        j1TextePanel.add(attaque1, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(10, 30, 10, 10);
        j1TextePanel.add(defense1, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(10, 30, 10, 10);
        j1TextePanel.add(precision1, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.PAGE_END;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 10, 10);
        j1TextePanel.add(vitesse1, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.PAGE_END;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        j1TextePanel.add(portee1, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.PAGE_END;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        j1TextePanel.add(mouvement1, gridBagConstraints);

        afficherInfo1.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridheight = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        j1TextePanel.add(afficherInfo1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        j1Panel.add(j1TextePanel, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(15, 15, 15, 15);
        add(j1Panel, gridBagConstraints);

        j2Panel.setBackground(new java.awt.Color(64, 64, 64));
        j2Panel.setPreferredSize(new Dimension(Case.TAILLE * 10, Case.TAILLE * 20));
        j2Panel.setLayout(new java.awt.GridBagLayout());

        j2NomPanel.setBackground(new java.awt.Color(128, 128, 128));
        j2NomPanel.setLayout(new java.awt.GridBagLayout());

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Entrez le nom du joueur 2 :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 20;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        j2NomPanel.add(jLabel6, gridBagConstraints);

        j2Nom.setText("Joueur 2");
        j2Nom.setMargin(new java.awt.Insets(5, 5, 5, 5));
        j2Nom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                j2NomActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.7;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 10, 10);
        j2NomPanel.add(j2Nom, gridBagConstraints);

        j2Valider.setText("Ok");
        j2Valider.setMargin(new java.awt.Insets(4, 4, 4, 4));
        j2Valider.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                j2ValiderActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.2;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 10, 10);
        j2NomPanel.add(j2Valider, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        j2Panel.add(j2NomPanel, gridBagConstraints);

        j2FamillePanel.setBackground(new java.awt.Color(128, 128, 128));
        j2FamillePanel.setLayout(new java.awt.GridBagLayout());

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Choisissez une famille :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        j2FamillePanel.add(jLabel7, gridBagConstraints);

        reptile2.setIcon(new ImageIcon(Textures.getPersonnage(Textures.ASSASSINREPTILE, Orientation.SUD)));
        reptile2.setToolTipText("Reptile");
        reptile2.setMargin(new java.awt.Insets(2, 2, 2, 2));
        reptile2.setMaximumSize(new java.awt.Dimension(45, 45));
        reptile2.setMinimumSize(new java.awt.Dimension(40, 40));
        reptile2.setPreferredSize(new java.awt.Dimension(45, 45));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 10, 10);
        j2FamillePanel.add(reptile2, gridBagConstraints);

        oiseau2.setIcon(new ImageIcon(Textures.getPersonnage(Textures.ASSASSINOISEAU, Orientation.SUD)));
        oiseau2.setToolTipText("Oiseau");
        oiseau2.setMargin(new java.awt.Insets(2, 2, 2, 2));
        oiseau2.setMaximumSize(new java.awt.Dimension(45, 45));
        oiseau2.setMinimumSize(new java.awt.Dimension(40, 40));
        oiseau2.setPreferredSize(new java.awt.Dimension(45, 45));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 10, 10);
        j2FamillePanel.add(oiseau2, gridBagConstraints);

        felin2.setIcon(new ImageIcon(Textures.getPersonnage(Textures.ASSASSINFELIN, Orientation.SUD)));
        felin2.setToolTipText("Félin");
        felin2.setMargin(new java.awt.Insets(2, 2, 2, 2));
        felin2.setMaximumSize(new java.awt.Dimension(45, 45));
        felin2.setMinimumSize(new java.awt.Dimension(40, 40));
        felin2.setPreferredSize(new java.awt.Dimension(45, 45));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 10, 10);
        j2FamillePanel.add(felin2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 10, 0);
        j2Panel.add(j2FamillePanel, gridBagConstraints);

        j2ClassePanel.setBackground(new java.awt.Color(128, 128, 128));
        j2ClassePanel.setLayout(new java.awt.GridBagLayout());

        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Choisissez un pion :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.ipadx = 5;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        j2ClassePanel.add(jLabel13, gridBagConstraints);

        archer2.setToolTipText("Archer");
        archer2.setMargin(new java.awt.Insets(2, 2, 2, 2));
        archer2.setMaximumSize(new java.awt.Dimension(40, 40));
        archer2.setMinimumSize(new java.awt.Dimension(35, 35));
        archer2.setPreferredSize(new java.awt.Dimension(35, 35));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 10, 5);
        j2ClassePanel.add(archer2, gridBagConstraints);

        assassin2.setToolTipText("Assassin");
        assassin2.setMargin(new java.awt.Insets(2, 2, 2, 2));
        assassin2.setMaximumSize(new java.awt.Dimension(40, 40));
        assassin2.setMinimumSize(new java.awt.Dimension(35, 35));
        assassin2.setPreferredSize(new java.awt.Dimension(35, 35));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 10, 5);
        j2ClassePanel.add(assassin2, gridBagConstraints);

        guerrier2.setToolTipText("Guerrier");
        guerrier2.setMargin(new java.awt.Insets(2, 2, 2, 2));
        guerrier2.setMaximumSize(new java.awt.Dimension(40, 40));
        guerrier2.setMinimumSize(new java.awt.Dimension(35, 35));
        guerrier2.setPreferredSize(new java.awt.Dimension(35, 35));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 10, 5);
        j2ClassePanel.add(guerrier2, gridBagConstraints);

        tacticien2.setToolTipText("Tacticien");
        tacticien2.setMargin(new java.awt.Insets(2, 2, 2, 2));
        tacticien2.setMaximumSize(new java.awt.Dimension(40, 40));
        tacticien2.setMinimumSize(new java.awt.Dimension(35, 35));
        tacticien2.setPreferredSize(new java.awt.Dimension(35, 35));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 10, 5);
        j2ClassePanel.add(tacticien2, gridBagConstraints);

        tank2.setToolTipText("Tank");
        tank2.setMargin(new java.awt.Insets(2, 2, 2, 2));
        tank2.setMaximumSize(new java.awt.Dimension(40, 40));
        tank2.setMinimumSize(new java.awt.Dimension(35, 35));
        tank2.setPreferredSize(new java.awt.Dimension(35, 35));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 10, 5);
        j2ClassePanel.add(tank2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 10, 0);
        j2Panel.add(j2ClassePanel, gridBagConstraints);

        j2TextePanel.setBackground(new java.awt.Color(128, 128, 128));
        j2TextePanel.setMinimumSize(new java.awt.Dimension(200, 300));
        j2TextePanel.setPreferredSize(new java.awt.Dimension(315, 200));
        j2TextePanel.setLayout(new java.awt.GridBagLayout());

        classe2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        j2TextePanel.add(classe2, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(0, 30, 10, 10);
        j2TextePanel.add(vie2, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(10, 30, 10, 10);
        j2TextePanel.add(attaque2, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(10, 30, 10, 10);
        j2TextePanel.add(defense2, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(10, 30, 10, 10);
        j2TextePanel.add(precision2, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 10, 10);
        j2TextePanel.add(vitesse2, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        j2TextePanel.add(portee2, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        j2TextePanel.add(mouvement2, gridBagConstraints);

        afficherInfo2.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridheight = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        j2TextePanel.add(afficherInfo2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        j2Panel.add(j2TextePanel, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(15, 15, 15, 15);
        add(j2Panel, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

	private void j1ValiderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_j1ValiderActionPerformed
		setEnabled(j1NomPanel, false);
		m.getJoueur1().setNom(j1Nom.getText());
		
		joueurCourant = !joueurCourant;
		setEnabled(j2NomPanel, true);
		
		j2Nom.requestFocus();
		j2Nom.setSelectionStart(0);
		j2Nom.setSelectionEnd(j2Nom.getText().length());
	}//GEN-LAST:event_j1ValiderActionPerformed

	private void j1NomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_j1NomActionPerformed
		j1ValiderActionPerformed(evt);
	}//GEN-LAST:event_j1NomActionPerformed

	private void j2ValiderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_j2ValiderActionPerformed
		setEnabled(j2NomPanel, false);
		m.getJoueur2().setNom(j2Nom.getText());

		joueurCourant = !joueurCourant;
		setEnabled(j1FamillePanel, true);
		etape++;

		j2Nom.setSelectionStart(0);
		j2Nom.setSelectionEnd(0);
		afficherInfo1.setVisible(true);
	}//GEN-LAST:event_j2ValiderActionPerformed

	private void j2NomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_j2NomActionPerformed
		j2ValiderActionPerformed(evt);
	}//GEN-LAST:event_j2NomActionPerformed

	private void mapValiderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mapValiderActionPerformed
		if(previsualisation.getPlateau() != null)
		{
			int nb = Integer.parseInt(nbPionsSpinner.getValue().toString());
			if(nb % 2 != 0){
				nbPionsSpinner.getEditor().requestFocus();
			}
			else {
				m.setNbPionParJoueur(nb);

				m.setPlateau(previsualisation.getPlateau());
				m.lierChateaux();

				mapPanel.setVisible(false);
				j1Panel.setVisible(true);
				j2Panel.setVisible(true);

				j1Nom.requestFocus();
				j1Nom.setSelectionStart(0);
				j1Nom.setSelectionEnd(j1Nom.getText().length());
			}
		}
	}//GEN-LAST:event_mapValiderActionPerformed

	private void mapComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mapComboBoxActionPerformed
		previsualisation.setPlateau(new Plateau(((Couple<String, String>)mapComboBox.getSelectedItem()).getKey()));
	}//GEN-LAST:event_mapComboBoxActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel afficherInfo1;
    private javax.swing.JLabel afficherInfo2;
    private javax.swing.JButton archer1;
    private javax.swing.JButton archer2;
    private javax.swing.JButton assassin1;
    private javax.swing.JButton assassin2;
    private javax.swing.JLabel attaque1;
    private javax.swing.JLabel attaque2;
    private javax.swing.JLabel classe1;
    private javax.swing.JLabel classe2;
    private javax.swing.JLabel defense1;
    private javax.swing.JLabel defense2;
    private javax.swing.JButton felin1;
    private javax.swing.JButton felin2;
    private javax.swing.JButton guerrier1;
    private javax.swing.JButton guerrier2;
    private javax.swing.JPanel j1ClassePanel;
    private javax.swing.JPanel j1FamillePanel;
    private javax.swing.JTextField j1Nom;
    private javax.swing.JPanel j1NomPanel;
    private javax.swing.JPanel j1Panel;
    private javax.swing.JPanel j1TextePanel;
    private javax.swing.JButton j1Valider;
    private javax.swing.JPanel j2ClassePanel;
    private javax.swing.JPanel j2FamillePanel;
    private javax.swing.JTextField j2Nom;
    private javax.swing.JPanel j2NomPanel;
    private javax.swing.JPanel j2Panel;
    private javax.swing.JPanel j2TextePanel;
    private javax.swing.JButton j2Valider;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JComboBox mapComboBox;
    private javax.swing.JPanel mapPanel;
    private javax.swing.JButton mapValider;
    private javax.swing.JLabel mouvement1;
    private javax.swing.JLabel mouvement2;
    private javax.swing.JSpinner nbPionsSpinner;
    private javax.swing.JButton oiseau1;
    private javax.swing.JButton oiseau2;
    private javax.swing.JLabel portee1;
    private javax.swing.JLabel portee2;
    private javax.swing.JLabel precision1;
    private javax.swing.JLabel precision2;
    private ihm.Previsualisation previsualisation;
    private javax.swing.JButton reptile1;
    private javax.swing.JButton reptile2;
    private javax.swing.JButton tacticien1;
    private javax.swing.JButton tacticien2;
    private javax.swing.JButton tank1;
    private javax.swing.JButton tank2;
    private javax.swing.JLabel vie1;
    private javax.swing.JLabel vie2;
    private javax.swing.JLabel vitesse1;
    private javax.swing.JLabel vitesse2;
    // End of variables declaration//GEN-END:variables

	//Désactive l'écouteur sur la fenetre de nouvelle partie
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
