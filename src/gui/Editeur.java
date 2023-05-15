package gui;

import java.awt.BorderLayout;
import java.io.File;
import java.io.Writer;
import java.util.HashMap;

import javax.swing.Action;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.event.CaretListener;
import javax.swing.text.EditorKit;
import javax.swing.text.JTextComponent;
import javax.swing.text.StyledEditorKit;
import javax.swing.text.html.HTMLEditorKit;

import Model.Fiche;
import Model.FicheSaver;

/**
 * Classe pour afficher la zone d'édition d'une fiche.
 */
public class Editeur extends JPanel {
    
    /** Contenu temporaire de la fiche */
    private String contenu;

    /** Zone d'édition */
    private JTextPane textbox;

    /** Titre temporaire */
    private String tempTitle;

    /** Chemin où se situe le fichier temporaire */
    private String tempPath;

    /**
     * Initialiser le composant.
     */
    public Editeur() {
        super(new BorderLayout());

        // Creation du nom temporaire
        tempTitle = "temp" + System.currentTimeMillis();
        tempPath = FicheSaver.HOME_FOLDER + File.separator + FicheSaver.REVEASY_FOLDER + File.separator
                + "temp" + File.separator + tempTitle + ".html";

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
        boldAction.putValue(Action.NAME, "Bold");
        JButton bold = new JButton(boldAction);
        actions.add(bold);

        Action italicAction = new StyledEditorKit.ItalicAction();
        italicAction.putValue(Action.NAME, "Italic");
        JButton italic = new JButton(italicAction);
        actions.add(italic);

        Action underlineAction = new StyledEditorKit.UnderlineAction();
        underlineAction.putValue(Action.NAME, "Underline");
        JButton underline = new JButton(underlineAction);
        actions.add(underline);

        JButton refreshButton = new JButton("Refresh");
        refreshButton.addActionListener(e -> {
            refresh();
        });
        actions.add(refreshButton);

        this.add(actions, BorderLayout.NORTH);

    }

    private void refresh() {
        
    }

    private void writeTemp(){
        try {
            Writer writer = new java.io.FileWriter(tempPath);
            textbox.write(writer);
        } catch (Exception e) {
            System.out.println("Erreur : impossible d'écrire dans le fichier temporaire");
        }

    }

    /** Crée le fichier temporaire et son dossier s'il n'existe pas */
    private void createFile(){

        String homepath = FicheSaver.HOME_FOLDER;

        File home = new File(homepath);

        // Créer le dossier temp si il n'existe pas
        if (home.exists()) {
            File reveasyDir = new File(homepath + File.separator + FicheSaver.REVEASY_FOLDER);
            if (!reveasyDir.exists()) {
                reveasyDir.mkdir();
            }
            File tempFileDir = new File(homepath + File.separator + FicheSaver.REVEASY_FOLDER + File.separator + "temp");
            if (!tempFileDir.exists()) {
                tempFileDir.mkdir();
            }
        } else {
            System.out.println("Erreur : impossible de trouver le dossier utilisateur");
        }

        // Créer le fichier temporaire
        File tempFile = new File(homepath + File.separator + FicheSaver.REVEASY_FOLDER + File.separator + "temp" + File.separator + tempTitle + ".html");
        try {
            tempFile.createNewFile();
        } catch (Exception e) {
            System.out.println("Erreur : impossible de créer le fichier temporaire");
        }
    }
}
