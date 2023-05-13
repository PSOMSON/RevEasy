package gui.vues;

import java.awt.LayoutManager;

import javax.swing.JPanel;

/**
 * Classe abstraite de vue.
 */
public abstract class Vue extends JPanel {

    /**
     * Initialiser la vue.
     */
    public Vue() {
        super();
    }

    /**
     * Initialiser la vue avec un layout.
     * @param layout Layout de la vue
     */
    public Vue(LayoutManager layout) {
        super(layout);
    }

}
