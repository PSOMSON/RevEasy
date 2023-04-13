package GUI;

import javax.swing.*;

import Model.ModelReveasy;

import java.awt.*;

public class IHM extends JFrame {

    /** Largeur de la fenêtre (en pixel). */
    private final int LARGEUR_DEFAUT = 720;

    /** Longueur de la fenêtre (en pixel). */
    private final int HAUTEUR_DEFAUT = 480;

    /** Panel correspondant à la vue des fiches existantes. */
    private JPanel vueMesFiches;

    /** Panel correspondant à l'éditeur */
    private JEditorPane editeur;

    /** Modèle. */
    private ModelReveasy modele;

    private Component compActuel;
    
    /** Construire l'interface homme machine. */
    public IHM() {
        super("Reveasy");
        
        this.modele = new ModelReveasy();

        this.vueMesFiches = new VueMesFiches(this, modele);
        this.compActuel = vueMesFiches;
        this.editeur = new VueEditeur();
        ControleurEditeur controleur = new ControleurEditeur(this, this.editeur, modele);
        MenuLateral menuLateral = new MenuLateral(this, modele);

        
        this.setLayout(new BorderLayout());
        this.add(compActuel, BorderLayout.CENTER);
        this.add(controleur, BorderLayout.SOUTH);
        this.add(menuLateral, BorderLayout.WEST);
        this.pack();
        this.setSize(LARGEUR_DEFAUT, HAUTEUR_DEFAUT);
        this.setVisible(true);
    }

    public void setPanelPrincipal(MenuLateral.ComposantPrincipaux composant) {
        // Récupérer le composant affiché actuellement
        this.remove(compActuel);
        switch (composant) {
            case EDITOR :
                // TODO : pas compris différence entre editor et creer
                break;
            case MESFICHES :
                this.add(this.vueMesFiches);
                compActuel = this.vueMesFiches;
                break;
            case CREER:
                // Vider l'éditeur avant de revenir dessus.
                editeur.setText("");
                this.add(editeur);
                compActuel = editeur;
                break;
        }
        // Mettre à jour la fenêtre
        this.revalidate();
        this.repaint();
    }
}
