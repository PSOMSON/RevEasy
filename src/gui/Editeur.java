package gui;


import java.awt.BorderLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.text.html.HTMLEditorKit;

import model.Fiche;
import model.FicheSaver;

/**
 * Classe pour afficher la zone d'édition d'une fiche.
 */
public class Editeur extends JPanel implements AfficheurFiche {
    
    /** Zone d'édition */
    private JTextPane textbox;

    /** Zone du titre. */
    private JTextField title;

    /**
     * Initialiser le composant.
     */
    public Editeur() {
        super(new BorderLayout());

        // Créer la zone d'édition
        textbox = new JTextPane();
        textbox.setEditorKit(new HTMLEditorKit());
        this.add(textbox, BorderLayout.CENTER);

        // Créer la zone de controleurs
        JPanel controleurs = new JPanel();
        controleurs.setLayout(new BoxLayout(controleurs, BoxLayout.X_AXIS));

        // Ajout de la sauvegarde et du titre dans les controlleurs
        this.title = new JTextField("Titre");
        JButton save = new JButton("Sauvegarder");
        save.addActionListener(e -> {
            Fiche fiche = new Fiche(title.getText(), textbox.getText());
            FicheSaver.sauvegarder(fiche);
        });

        // Ajout d'un bouton pour ouvrir une fiche.
        JButton open = new JButton("Ouvrir");
        open.addActionListener(e -> {
            PopUpOpenFiche popUp = new PopUpOpenFiche(this);
            popUp.setVisible(true);
        });

        controleurs.add(title);
        controleurs.add(save);
        controleurs.add(open);
        this.add(controleurs, BorderLayout.SOUTH);

        // Ajout du menu supérieur d'édition
        JPanel actions = new ActionsEdition(this.textbox); 
        this.add(actions, BorderLayout.NORTH);
    }
    
    public Editeur(Fiche f) {
    	this();
    	openFiche(f);
    }

    @Override
    public void openFiche(Fiche f) {
        textbox.setText(f.getContenu());
        title.setText(f.getTitre());
    }

}
