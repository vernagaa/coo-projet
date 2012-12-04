package moteur;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
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

	private Moteur m;
	private ArrayList<Case> caseJoueur1;
	private boolean joueurCourant;
	private int etape;
	/*
	 * Pour le joueur 1
	 */
	private JTextField nomJoueur1;
	JButton validez1;
	JButton reptile1;
	JButton oiseau1;
	JButton felin1;
	String famille1;
	JButton archer1;
	JButton guerrier1;
	JButton tank1;
	JButton assassin1;
	JButton tacticien1;/*
	 * Pour le joueur 2
	 */

	private JTextField nomJoueur2;
	JButton validez2;
	JButton reptile2;
	JButton oiseau2;
	JButton felin2;
	String famille2;


	public NouvellePartieGraphique(final Moteur m) {
		this.m = m;
		joueurCourant = true;
		etape = 0;

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
				reptile1.setEnabled(false);
				felin1.setEnabled(false);
				oiseau1.setEnabled(false);
				reptile2.setEnabled(true);
				felin2.setEnabled(true);
				oiseau2.setEnabled(true);
				joueurCourant = !joueurCourant;
				famille1 = "reptile";
				repaint();
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
				reptile1.setEnabled(false);
				felin1.setEnabled(false);
				oiseau1.setEnabled(false);
				reptile2.setEnabled(true);
				felin2.setEnabled(true);
				oiseau2.setEnabled(true);
				joueurCourant = !joueurCourant;
				famille1 = "oiseau";
				repaint();
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
				reptile1.setEnabled(false);
				felin1.setEnabled(false);
				oiseau1.setEnabled(false);
				reptile2.setEnabled(true);
				felin2.setEnabled(true);
				oiseau2.setEnabled(true);
				joueurCourant = !joueurCourant;
				famille1 = "felin";
				repaint();
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
				reptile2.setEnabled(false);
				felin2.setEnabled(false);
				oiseau2.setEnabled(false);
				archer1.setEnabled(true);
				guerrier1.setEnabled(true);
				tank1.setEnabled(true);
				tacticien1.setEnabled(true);
				assassin1.setEnabled(true);
				joueurCourant = !joueurCourant;
				famille2 = "reptile";
				repaint();
				etape++;
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
				reptile2.setEnabled(false);
				felin2.setEnabled(false);
				oiseau2.setEnabled(false);
				archer1.setEnabled(true);
				guerrier1.setEnabled(true);
				tank1.setEnabled(true);
				tacticien1.setEnabled(true);
				assassin1.setEnabled(true);
				joueurCourant = !joueurCourant;
				famille2 = "oiseau";
				etape++;
				repaint();
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
				reptile2.setEnabled(false);
				felin2.setEnabled(false);
				oiseau2.setEnabled(false);
				archer1.setEnabled(true);
				guerrier1.setEnabled(true);
				tank1.setEnabled(true);
				tacticien1.setEnabled(true);
				assassin1.setEnabled(true);
				joueurCourant = !joueurCourant;
				famille2 = "felin";
				repaint();
			}
		});
		add(felin2);


		archer1 = new JButton("archer 1");
		archer1.setBounds(Case.TAILLE, Case.TAILLE * 9, Case.TAILLE, Case.TAILLE);
		archer1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Archer");
			}
		});
		archer1.setEnabled(false);
		add(archer1);
		guerrier1 = new JButton("guerrier 1");
		guerrier1.setBounds(Case.TAILLE * 3, Case.TAILLE * 9, Case.TAILLE, Case.TAILLE);
		guerrier1.setEnabled(false);
		add(guerrier1);
		tank1 = new JButton("tank 1");
		tank1.setBounds(Case.TAILLE * 5, Case.TAILLE * 9, Case.TAILLE, Case.TAILLE);
		tank1.setEnabled(false);
		add(tank1);
		assassin1 = new JButton("guerrier 1");
		assassin1.setBounds(Case.TAILLE * 7, Case.TAILLE * 9, Case.TAILLE, Case.TAILLE);
		assassin1.setEnabled(false);
		add(assassin1);
		tacticien1 = new JButton("tacticien 1");
		tacticien1.setBounds(Case.TAILLE * 9, Case.TAILLE * 9, Case.TAILLE, Case.TAILLE);
		tacticien1.setEnabled(false);
		add(tacticien1);

		JButton vie1 = new JButton("vie 1");
		vie1.setBounds(Case.TAILLE * 3, Case.TAILLE * 12, Case.TAILLE * 5, Case.TAILLE);
		add(vie1);
		JButton attaque1 = new JButton("attaque 1");
		attaque1.setBounds(Case.TAILLE * 3, Case.TAILLE * 13, Case.TAILLE * 5, Case.TAILLE);
		add(attaque1);
		JButton defense1 = new JButton("defense 1");
		defense1.setBounds(Case.TAILLE * 3, Case.TAILLE * 14, Case.TAILLE * 5, Case.TAILLE);
		add(defense1);
		JButton precision1 = new JButton("precision 1");
		precision1.setBounds(Case.TAILLE * 3, Case.TAILLE * 15, Case.TAILLE * 5, Case.TAILLE);
		add(precision1);
		JButton vitesse1 = new JButton("vitesse 1");
		vitesse1.setBounds(Case.TAILLE * 3, Case.TAILLE * 16, Case.TAILLE * 5, Case.TAILLE);
		add(vitesse1);
		JButton portee1 = new JButton("portee 1");
		portee1.setBounds(Case.TAILLE * 3, Case.TAILLE * 17, Case.TAILLE * 5, Case.TAILLE);
		add(portee1);
		JButton mouvement1 = new JButton("mouvement 1");
		mouvement1.setBounds(Case.TAILLE * 3, Case.TAILLE * 18, Case.TAILLE * 5, Case.TAILLE);
		add(mouvement1);





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
	
}
