package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

        // Créer la zone d'actions d'édition
        JPanel actions = new JPanel();
        actions.setLayout(new BoxLayout(actions, BoxLayout.X_AXIS));

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

        // Ajout des actions d'édition
        JPanel leftActions = new JPanel(new FlowLayout(FlowLayout.LEFT));
        Action boldAction = new StyledEditorKit.BoldAction();
        boldAction.putValue(Action.NAME, "Gras");
        JButton bold = new JButton(boldAction);
        leftActions.add(bold);

        Action italicAction = new StyledEditorKit.ItalicAction();
        italicAction.putValue(Action.NAME, "Italique");
        JButton italic = new JButton(italicAction);
        leftActions.add(italic);

        Action underlineAction = new StyledEditorKit.UnderlineAction();
        underlineAction.putValue(Action.NAME, "Souligné");
        JButton underline = new JButton(underlineAction);
        leftActions.add(underline);
        
        // Ajout des actions
        JPanel rightActions = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton definition = new JButton("@Definition");
        JButton theoreme = new JButton("@Theoreme");
        JButton qcm = new JButton("@QCM");
        
        definition.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textbox.requestFocusInWindow();
                textbox.replaceSelection("\n\n@Definition mot\nla definition du mot sur cette ligne");
            }
        });

        theoreme.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textbox.requestFocusInWindow();
                textbox.replaceSelection("\n\n@Theoreme nom du théorème\nénoncé du théorème sur cette ligne");
            }
        });

        qcm.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textbox.requestFocusInWindow();
                textbox.replaceSelection("\n\n@Question question ?\n@ReponseV Réponse vraie\n@ReponseF Réponse fausse\n@ReponseF Réponse fausse");
            }
        });
        rightActions.add(definition);
        rightActions.add(theoreme);
        rightActions.add(qcm);
        actions.add(leftActions);
        actions.add(rightActions);
        
        
        this.add(actions, BorderLayout.NORTH);
    }

    @Override
    public void openFiche(Fiche f) {
        textbox.setText(f.getContenu());
        title.setText(f.getTitre());
    }

}
