package ihm;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import moteur.Case;
import moteur.Moteur;
import moteur.Orientation;
import moteur.Textures;

/**
 *
 * @author Anthony
 */
public class NouvellePartiePanel extends javax.swing.JPanel {

	private static final Color selectedCol = new Color(204, 0, 0);
	private static final String FELIN_DESCR = "<html><h2>Félin</h2><p align=\"justify\">Les félins procurent un bonus en défense et en chance, et un malus en vie."
			+ "<br/><br/>Le tacticien procure aux unités 1 de mouvement supplémentaire."
			+ "<br/>Il procure la capacité spéciale \"Rage\" à chaque unité. La prochaine attaque sera un coup critique à coup sûr, au prochain tour, il aura une précision de 0. "
			+ "Elle ne peut s'utiliser qu'une fois tout les trois tours</p></html>";
	private static final String OISEAU_DESCR = "<html><h2>Oiseau</h2><p align=\"justify\">Les oiseaux procurent un bonus en attaque et en vie, et un malus en vitesse."
			+ "<br/><br/>Le tacticien procure aux unités 1 de portée supplémentaire."
			+ "<br/>Il procure la capacité spéciale \"Envol\" à chaque unité. L'oiseau vol alors jusqu'à la fin de son prochain tour, il est inattaquable durant ce temps. "
			+ "Elle ne peut s'utiliser qu'une fois tout les trois tours</p></html>";
	private static final String REPTILE_DESCR = "<html><h2>Reptile</h2><p align=\"justify\">Les reptiles procurent un bonus en attaque et en vie, et un malus en vitesse."
			+ "<br/><br/>Le tacticien procure au joueur un bonus de 3 actions,"
			+ "<br/>Il procure la capacité spéciale \"Mue\" à chaque unité. Elle rend 7 pv à chaque utilisation. "
			+ "Elle ne peut s'utiliser qu'une fois tout les trois tours</p></html>";
	
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
	 * Creates new form NewJPanel
	 */
	public NouvellePartiePanel(final Moteur m) {
		this.m = m;
		joueurCourant = JOUEUR1;
		choix = false;
		etape = 0;
		nbPions = 0;
		
//		addMouseListener(this);
//		addMouseMotionListener(this);
		
		caseJoueur = new ArrayList<Case>();
		
		initComponents();
		setEnabled(j1NomPanel, true);
		
				class EcouteurBoutonFamille extends MouseAdapter implements ActionListener {

			private boolean joueur;
			private int famille;

			public EcouteurBoutonFamille(boolean joueur, int famille) {
				this.joueur = joueur;
				this.famille = famille;
			}

			@Override
			public void actionPerformed(ActionEvent e) {
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

			private void choixFamilleJoueur1(int famille) {
				throw new UnsupportedOperationException("Not yet implemented");
			}

			private void choixFamilleJoueur2(int famille) {
				throw new UnsupportedOperationException("Not yet implemented");
			}

			private void afficherFamille(boolean joueur, int famille) {
				throw new UnsupportedOperationException("Not yet implemented");
			}

			private void cacherFamille(boolean joueur) {
				throw new UnsupportedOperationException("Not yet implemented");
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

			private void setListeAireDejeu() {
				throw new UnsupportedOperationException("Not yet implemented");
			}

			private void afficherStatPerso(int famille, int classe) {
				throw new UnsupportedOperationException("Not yet implemented");
			}

			private void cacherStatPerso() {
				throw new UnsupportedOperationException("Not yet implemented");
			}
		}

	}
	
	public NouvellePartiePanel() {
		this(new Moteur());
	}

	private void setEnabled(javax.swing.JPanel panel, boolean b) {
		for(Component c : panel.getComponents()) {
			c.setEnabled(b);
		}
		if(b) {
			panel.setBackground(selectedCol);
		}
		else {
			panel.setBackground(Color.GRAY);
		}
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

        setBackground(new java.awt.Color(64, 64, 64));
        setMinimumSize(new Dimension(Case.TAILLE * 23, Case.TAILLE * 20));
        setLayout(new java.awt.GridBagLayout());

        j1Panel.setBackground(new java.awt.Color(64, 64, 64));
        j1Panel.setPreferredSize(new Dimension(Case.TAILLE * 10, Case.TAILLE * 20));
        j1Panel.setLayout(new java.awt.GridBagLayout());

        j1NomPanel.setBackground(java.awt.Color.gray);
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
        j1TextePanel.setPreferredSize(new java.awt.Dimension(315, 200));

        javax.swing.GroupLayout j1TextePanelLayout = new javax.swing.GroupLayout(j1TextePanel);
        j1TextePanel.setLayout(j1TextePanelLayout);
        j1TextePanelLayout.setHorizontalGroup(
            j1TextePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 315, Short.MAX_VALUE)
        );
        j1TextePanelLayout.setVerticalGroup(
            j1TextePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 227, Short.MAX_VALUE)
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        j1Panel.add(j1TextePanel, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
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
        j2TextePanel.setPreferredSize(new java.awt.Dimension(315, 200));

        javax.swing.GroupLayout j2TextePanelLayout = new javax.swing.GroupLayout(j2TextePanel);
        j2TextePanel.setLayout(j2TextePanelLayout);
        j2TextePanelLayout.setHorizontalGroup(
            j2TextePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 315, Short.MAX_VALUE)
        );
        j2TextePanelLayout.setVerticalGroup(
            j2TextePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 227, Short.MAX_VALUE)
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        j2Panel.add(j2TextePanel, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(15, 15, 15, 15);
        add(j2Panel, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

	private void j1ValiderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_j1ValiderActionPerformed
		setEnabled(j1NomPanel, false);
		setEnabled(j2NomPanel, true);
		m.getJoueur1().setNom(j1Nom.getText());
		
		joueurCourant = !joueurCourant;
		System.out.println(m.getJoueur1().getNom());
		
		j2Nom.requestFocus();
	}//GEN-LAST:event_j1ValiderActionPerformed

	private void j1NomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_j1NomActionPerformed
		j1ValiderActionPerformed(evt);
	}//GEN-LAST:event_j1NomActionPerformed

	private void j2ValiderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_j2ValiderActionPerformed
		setEnabled(j2NomPanel, false);
		m.getJoueur2().setNom(j2Nom.getText());

		joueurCourant = !joueurCourant;
		System.out.println(m.getJoueur2().getNom());
		etape++;
		setEnabled(j1FamillePanel, true);

		//TODO afficherInfo1.setVisible(true);
//		repaint();
	}//GEN-LAST:event_j2ValiderActionPerformed

	private void j2NomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_j2NomActionPerformed
		j2ValiderActionPerformed(evt);
	}//GEN-LAST:event_j2NomActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton archer1;
    private javax.swing.JButton archer2;
    private javax.swing.JButton assassin1;
    private javax.swing.JButton assassin2;
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
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JButton oiseau1;
    private javax.swing.JButton oiseau2;
    private javax.swing.JButton reptile1;
    private javax.swing.JButton reptile2;
    private javax.swing.JButton tacticien1;
    private javax.swing.JButton tacticien2;
    private javax.swing.JButton tank1;
    private javax.swing.JButton tank2;
    // End of variables declaration//GEN-END:variables
}
