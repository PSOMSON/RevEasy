package gui;

import java.awt.BorderLayout;
import java.io.File;
import java.io.Writer;
import java.util.SortedSet;
import java.util.TreeSet;

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
import Model.ActionStyle;

/**
 * Classe pour afficher la zone d'édition d'une fiche.
 */
public class Editeur extends JPanel {
    
    /** Zone d'édition */
    private JTextPane textbox;

    /** tableau des actions de style. Contient l'action à réaliser ainsi que 
     * les indices de début et de fin de la séléction concernée.*/
    private SortedSet<ActionStyle> actionsStyle;

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
            FicheSaver.sauvegarder(new Fiche(title.getText(), textbox.getText()));
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

        // Initialisation de l'ensemble des actions de style
        this.actionsStyle = new TreeSet<>();
    }

    /**
     * Ajouter une action de style à la liste des actions de style.
     * @param actionStyle Action de style à ajouter
     */
    public void addActionStyle(ActionStyle actionStyle) {
        this.actionsStyle.add(actionStyle);
    }
    
}
