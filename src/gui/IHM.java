package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import gui.vues.Consultation;
import gui.vues.Edition;
import gui.vues.Reviser;
import gui.vues.Vue;
import gui.vues.Welcome;
import model.Fiche;

/**
 * Classe principale de l'interface graphique, permet de gérer les vues.
 */
public class IHM extends JFrame implements ActionListener {

    /** Largeur de l'interface. */
    private static final int WIDTH = 800;
    /** Hauteur de l'interface. */
    private static final int HEIGHT = 600;

    /** Vue de bienvenue. */
    private Welcome welcome;
    /** Vue d'édition. */
    private Edition edition;
    /** Vue de consultation. */
    private Consultation consultation;
    /** Vue de révision. */
    private Reviser reviser;

    /**
     * Constructeur.
     */
    public IHM() {
        super("RevEasy");

        // Créer les vues
        welcome = new Welcome(this);
        edition = new Edition(this);
        consultation = new Consultation(this);
        reviser = new Reviser(this);

        // Créer la fenêtre
        this.getContentPane().add(welcome);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(WIDTH, HEIGHT);
        this.setVisible(true);
    }

    /**
     * Changer la vue affichée.
     * @param vue
     */
    private void changeVue(Vue vue) {
        this.getContentPane().removeAll();
        this.getContentPane().add(vue);
        this.revalidate();
        this.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {

        switch (arg0.getActionCommand()) {
            case "vueEdition":
                changeVue(edition);
                break;
            case "vueConsultation":
                changeVue(consultation);
                break;
            case "vueRevision":
                changeVue(reviser);
                break;
            case "quitter":
                this.dispose();
                break;
            default:
                throw new IllegalArgumentException("Unexpected value: "
                                                    + arg0.getActionCommand());
        }
    }

    /**
     * Editer une fiche.
     * @param fiche fiche à éditer.
     */
    public void editer(Fiche fiche) {
        edition.editer(fiche);
        changeVue(edition);
    }
}
