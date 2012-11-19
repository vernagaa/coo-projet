package ihm;

import ecouteur.EcouteurPlateau;
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
		initComponents();
		//position
		setLocationRelativeTo(getParent());
		//écouteur sur l'aire de jeu
		aireDeJeu.addMouseListener(new EcouteurPlateau(aireDeJeu));
		setVisible(true);
	}

	private void quitterApplication() {
		int confirm = JOptionPane.showConfirmDialog(this,
				"Voulez-vous vraiment quitter Animosity ?",
				"Quitter",
				JOptionPane.YES_NO_OPTION);

		if (confirm == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
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
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
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
    private javax.swing.JMenu menuBarFichier;
    private javax.swing.JMenu menuBarOption;
    private javax.swing.JMenuItem menuNouvellePartie;
    private javax.swing.JMenuItem menuQuitter;
    // End of variables declaration//GEN-END:variables
}
