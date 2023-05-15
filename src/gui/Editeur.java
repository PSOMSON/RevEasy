package gui;

import java.awt.BorderLayout;

import javax.swing.Action;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.text.StyledEditorKit;
import javax.swing.text.html.HTMLEditorKit;

import Model.Fiche;
import Model.FicheSaver;

/**
 * Classe pour afficher la zone d'édition d'une fiche.
 */
public class Editeur extends JPanel {
    
    /** Zone d'édition */
    private JTextPane textbox;

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

        // Créer la zone d'actions d'édition
        JPanel actions = new JPanel();
        actions.setLayout(new BoxLayout(actions, BoxLayout.X_AXIS));

        // Ajout de la sauvegarde et du titre dans les controlleurs
        JTextField title = new JTextField("Titre");
        JButton save = new JButton("Sauvegarder");
        save.addActionListener(e -> {
            Fiche fiche = new Fiche(title.getText(), textbox.getText());
            FicheSaver.sauvegarder(fiche);
        });

        controleurs.add(title);
        controleurs.add(save);
        this.add(controleurs, BorderLayout.SOUTH);

        // Ajout des actions d'édition
        Action boldAction = new StyledEditorKit.BoldAction();
        boldAction.putValue(Action.NAME, "Gras");
        JButton bold = new JButton(boldAction);
        actions.add(bold);

        Action italicAction = new StyledEditorKit.ItalicAction();
        italicAction.putValue(Action.NAME, "Italique");
        JButton italic = new JButton(italicAction);
        actions.add(italic);

        Action underlineAction = new StyledEditorKit.UnderlineAction();
        underlineAction.putValue(Action.NAME, "Souligné");
        JButton underline = new JButton(underlineAction);
        actions.add(underline);

        this.add(actions, BorderLayout.NORTH);
    }


}
