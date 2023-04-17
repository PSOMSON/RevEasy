package GUI;

import javax.swing.*;

import Model.Fiche;
import Model.ModelReveasy;

import java.awt.*;

public class IHM extends JFrame {

    /** Largeur de la fenêtre (en pixel). */
    private final int LARGEUR_DEFAUT = 720;

    /** Longueur de la fenêtre (en pixel). */
    private final int HAUTEUR_DEFAUT = 480;

    /** Panel correspondant à la vue des fiches existantes. */
    private VueMesFiches vueMesFiches;

    /** Panel correspondant à l'éditeur. */
    private VueEditeur vueEditeur;

    /** Modèle. */
    private ModelReveasy modele;

    /** Composant principal actuel. */
    private Component compActuel;
    
    /** Construire l'interface homme machine. */
    public IHM() {
        super("Reveasy");
        
        this.modele = new ModelReveasy();
        this.vueMesFiches = new VueMesFiches(this, modele);
        this.vueEditeur = new VueEditeur(modele);
        this.compActuel = vueMesFiches;
        ControleurEditeur controleur = new ControleurEditeur(this, this.vueEditeur, modele);
        MenuLateral menuLateral = new MenuLateral(this, modele);

        
        this.setLayout(new BorderLayout());
        this.add(compActuel, BorderLayout.CENTER);
        this.add(controleur, BorderLayout.SOUTH);
        this.add(menuLateral, BorderLayout.WEST);
        this.pack();
        this.setSize(LARGEUR_DEFAUT, HAUTEUR_DEFAUT);
        this.setVisible(true);
    }

    /**
     * Changer le a vue du panel principal.
     * @param composant identifiant du prochain composant.
     */
    public void setPanelPrincipal(MenuLateral.ComposantPrincipaux composant) {
        // Récupérer le composant affiché actuellement
        this.remove(compActuel);
        switch (composant) {
            case EDITOR :
                // TODO : pas compris différence entre editor et creer
                break;
            case MESFICHES :
                // mettre à jour l'affichage des fiches.
                vueMesFiches.mettreAJourVueFiches();
                this.add(this.vueMesFiches);
                compActuel = this.vueMesFiches;
                break;
            case CREER:
                String nomFiche = JOptionPane.showInputDialog("nomFiche:");
                if (nomFiche != null && nomFiche.length() > 0) {
                    // User clicked OK
                    modele.ajouterFiche(nomFiche, new Fiche(nomFiche, ""));
                } else if(nomFiche.length() == 0){
                    JOptionPane.showMessageDialog(null, "Le nom de la fiche ne peut pas être vide ! ");
                    setPanelPrincipal(composant);;
                } else {
                    // User clicked Cancel or closed the dialog box
                    setPanelPrincipal(MenuLateral.ComposantPrincipaux.MESFICHES);
                }


                // Vider l'éditeur avant de revenir dessus.
                vueEditeur.setNomFiche(nomFiche);
                vueEditeur.setContenu("");
                this.add(vueEditeur);
                compActuel = vueEditeur;
                break;
        }
        // Mettre à jour la fenêtre
        this.revalidate();
        this.repaint();
    }
}
