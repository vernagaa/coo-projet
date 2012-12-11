package ihm;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import moteur.Case;

/**
 *
 * @author chappelk
 */
public abstract class BoutonAction extends JButton {

	/**
	 * booléen du survol du bouton
	 */
	protected boolean survol;
	/**
	 * nom du bouton
	 */
	protected String nom;
	/**
	 * Fenetre du menu d'interaction
	 */
	protected FenetreChoixPion fenetre;

	/**
	 * Constructeur du bouton d'action
	 * @param nom
	 * @param numero
	 * @param f
	 */
	public BoutonAction(String nom, int numero, FenetreChoixPion f) {
		super(nom);
		this.fenetre = f;
		this.nom = nom;
		survol = false;
		setBounds(0, Case.TAILLE * numero, Case.TAILLE * 3, Case.TAILLE);
		setBorder(null);
		addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent e) {
				survol = true;
				fenetre.getMoteur().getAireDeJeu().repaint();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				survol = false;
				fenetre.getMoteur().getAireDeJeu().repaint();
			}
		});
	}

	/**
	 * Surligne le bouton ou non s'il est survolé ou non et affiche son nom
	 * @param g 
	 */
	@Override
	public void paintComponent(Graphics g) {
		Graphics2D gd = (Graphics2D) g;
		if (survol) {
			gd.setColor(new Color(170, 170, 170, 100));
			gd.fillRect(0, 0, Case.TAILLE * 3, Case.TAILLE);
		} else {
			gd.setColor(new Color(200, 200, 200, 100));
			gd.fillRect(0, 0, Case.TAILLE * 3, Case.TAILLE);
		}
		gd.setColor(Color.WHITE);
		//TODO Placer mieux
		gd.drawString(nom, Case.TAILLE / 5, Case.TAILLE / 2);
	}

	/**
	 * Affecteur du numéro de la position
	 * @param numero
	 */
	public void setPosition(int numero) {
		setBounds(0, Case.TAILLE * numero, Case.TAILLE * 3, Case.TAILLE);
	}
}
