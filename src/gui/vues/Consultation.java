package gui.vues;

import java.awt.BorderLayout;

import javax.swing.JSplitPane;
import javax.swing.JTextPane;
import javax.swing.text.html.HTMLEditorKit;

import Model.Fiche;
import Model.Traducteur;
import gui.IHM;

public class Consultation extends Vue {

    /** Zone d'affichage du texte de la fiche. */
    private JTextPane zoneTexte;

    /**
     * Initialiser la vue.
     * @param ihm Interface de l'application
     */
    public Consultation(IHM ihm) {
        super(new BorderLayout());

        MenuLateral menuLateral = new MenuLateral(ihm);

        Liste fiches = new Liste(this);

        zoneTexte = new JTextPane();
        zoneTexte.setEditable(false);
        zoneTexte.setText("Salut a tous\nc'est david la farge pokemon !");

        JSplitPane layout = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, fiches,
                                                                        zoneTexte);
        layout.setSize(this.getSize());

        JSplitPane generalLayout = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                                                  menuLateral, layout);
        generalLayout.setSize(this.getSize());

        this.add(generalLayout, BorderLayout.CENTER);
    }

    /**
     * Ouvrir une fiche.
     * @param f Fiche à ouvrir
     */
    public void ouvrir(Fiche f) {
        zoneTexte.setEditorKit(new HTMLEditorKit());
        String contenu = "<html>\n" + Traducteur.mdToHtml(f.getContenu());
        zoneTexte.setText(contenu);
    }
}
