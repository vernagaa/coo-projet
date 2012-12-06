package ihm;

import ecouteur.EcouteurPlateau;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import moteur.Moteur;

/**
 *
 * @author disavinr
 */
public class FenetrePrincipale extends javax.swing.JFrame {

    private Moteur moteur;

    /** Creates new form FenetrePrincipale */
    public FenetrePrincipale(Moteur m) {
        moteur = m;
//		new NouvellePartie(this, true);
        initComponents();
        //position
        setLocationRelativeTo(getParent());
        //écouteur sur l'aire de jeu
        EcouteurPlateau ecouterPlateau = new EcouteurPlateau(moteur);
        aireDeJeu.addMouseListener(ecouterPlateau);
        aireDeJeu.addMouseMotionListener(ecouterPlateau);
        setVisible(true);
    }
	
	private void quitterApplication() {
		//TODO rétablir à la fin
//		int confirm = JOptionPane.showConfirmDialog(this,
//				"Voulez-vous vraiment quitter Animosity ?",
//				"Quitter",
//				JOptionPane.YES_NO_OPTION);
//
//		if (confirm == JOptionPane.YES_OPTION) {
			System.exit(0);
//		}
	}
	
	/** This method is called from within the constructor to
	 * initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
    public AireDeJeu getAireDeJeu() {
        return aireDeJeu;
    }


    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        aireDeJeu = new ihm.AireDeJeu(moteur.getPlateau());
        labelAction = new javax.swing.JLabel();
        labelJoueur = new javax.swing.JLabel();
        labelTour = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        menuBarFichier = new javax.swing.JMenu();
        menuNouvellePartie = new javax.swing.JMenuItem();
        menuQuitter = new javax.swing.JMenuItem();
        menuBarOption = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Animosity");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        javax.swing.GroupLayout aireDeJeuLayout = new javax.swing.GroupLayout(aireDeJeu);
        aireDeJeu.setLayout(aireDeJeuLayout);
        aireDeJeuLayout.setHorizontalGroup(
            aireDeJeuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1051, Short.MAX_VALUE)
        );
        aireDeJeuLayout.setVerticalGroup(
            aireDeJeuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 601, Short.MAX_VALUE)
        );

        labelAction.setText("Actions restantes : 5");

        labelJoueur.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelJoueur.setText("Joueur : Joureur_1");

        labelTour.setText("Tour : 0");

        menuBarFichier.setText("Fichier");

        menuNouvellePartie.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        menuNouvellePartie.setText("Nouvelle Partie");
        menuNouvellePartie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuNouvellePartieActionPerformed(evt);
            }
        });
        menuBarFichier.add(menuNouvellePartie);

        menuQuitter.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_MASK));
        menuQuitter.setText("Quitter");
        menuQuitter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuQuitterActionPerformed(evt);
            }
        });
        menuBarFichier.add(menuQuitter);

        jMenuBar1.add(menuBarFichier);

        menuBarOption.setText("Option");
        jMenuBar1.add(menuBarOption);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(aireDeJeu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(labelAction)
                .addGap(18, 18, 18)
                .addComponent(labelJoueur, javax.swing.GroupLayout.DEFAULT_SIZE, 658, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(labelTour)
                .addGap(131, 131, 131))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelAction)
                    .addComponent(labelTour)
                    .addComponent(labelJoueur))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(aireDeJeu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

	private void menuNouvellePartieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuNouvellePartieActionPerformed
//		moteur.getPlateau().vider();
            new NouvellePartie(this, true);
	}//GEN-LAST:event_menuNouvellePartieActionPerformed

	private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
            quitterApplication();
	}//GEN-LAST:event_formWindowClosing

	private void menuQuitterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuQuitterActionPerformed
            quitterApplication();
	}//GEN-LAST:event_menuQuitterActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private ihm.AireDeJeu aireDeJeu;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JLabel labelAction;
    private javax.swing.JLabel labelJoueur;
    private javax.swing.JLabel labelTour;
    private javax.swing.JMenu menuBarFichier;
    private javax.swing.JMenu menuBarOption;
    private javax.swing.JMenuItem menuNouvellePartie;
    private javax.swing.JMenuItem menuQuitter;
    // End of variables declaration//GEN-END:variables

	public Moteur getMoteur() {
		return moteur;
	}

	public void setLabelTour(int tour) {
		labelTour.setText("Tour : "+tour);
	}

	public void setLabelAction(int actions) {
		if(actions==0){
			labelAction.setText("Action restante : "+ actions);
		}
		else{
			labelAction.setText("Actions restantes : "+ actions);
		}
	}

	public void setLabelJoueur(String joueur) {
		labelJoueur.setText("Joueur : "+joueur);
	}
	
	
	
}
