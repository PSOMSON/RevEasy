package gui.vues;

import java.awt.BorderLayout;

import javax.swing.JSplitPane;

import Model.Fiche;
import gui.Editeur;
import gui.IHM;

/**
 * Classe pour afficher la zone d'édition d'une fiche.
 */
public class Edition extends Vue {

    /** Zone d'édtition. */
    private Editeur zoneEdition;

    /** Menu latéral. */
    private MenuLateral menuLateral;
    /**
     * Initialiser la vue.
     * @param ihm Interface de l'application
     */
    public Edition(IHM ihm) {
        super(new BorderLayout());

        // Créer la zone d'édition
        this.zoneEdition = new Editeur();

        // Créer la zone de recherche
        menuLateral = new MenuLateral(ihm);

        // Ajouter les deux zones à la vue en les séparant
        JSplitPane layout = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, menuLateral,
                                                                        zoneEdition);
        layout.setSize(this.getSize());

        this.add(layout, BorderLayout.CENTER);
    }

    /**
     * Editer une fiche.
     * @param fiche fiche à éditer.
     */
    public void editer(Fiche fiche) {
        this.zoneEdition = new Editeur(fiche);
        JSplitPane layout = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, menuLateral,
                                                                        zoneEdition);
        layout.setSize(this.getSize());

        this.add(layout, BorderLayout.CENTER);
    }
}
