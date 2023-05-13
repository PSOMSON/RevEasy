package gui.vues;

import java.awt.BorderLayout;

import javax.swing.JSplitPane;

import gui.Editeur;
import gui.IHM;

/**
 * Classe pour afficher la zone d'édition d'une fiche.
 */
public class Edition extends Vue {

    /**
     * Initialiser la vue.
     * @param ihm Interface de l'application
     */
    public Edition(IHM ihm) {
        super(new BorderLayout());

        // Créer la zone d'édition
        Editeur zoneEdition = new Editeur();

        // Créer la zone de recherche
        MenuLateral menuLateral = new MenuLateral(ihm);

        // Ajouter les deux zones à la vue en les séparant
        JSplitPane layout = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, menuLateral,
                                                                        zoneEdition);
        layout.setSize(this.getSize());

        this.add(layout, BorderLayout.CENTER);
    }
}
