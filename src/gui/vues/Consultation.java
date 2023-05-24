package gui.vues;

import java.awt.BorderLayout;

import javax.swing.JScrollPane;
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
        zoneTexte.setText("Pour consulter une fiche : cliquer sur une des fiches dans l'explorateur de fiches.");
        JScrollPane ficheScrollPane = new JScrollPane(zoneTexte);
        
        JSplitPane layout = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, fiches,
        		ficheScrollPane);
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

        String contenu = Traducteur.mdToHtml(f.getContenu());
        contenu = Traducteur.balises(contenu);
        contenu = "<h><b><center>"+f.getTitre()+"</center></b></h><br><br>" + contenu;
        contenu = "<html>" + contenu;
        zoneTexte.setText(contenu);
    }
}
