package gui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Action;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.text.StyledEditorKit;

/** Le menu supérieur de la fenêtre d'édition, regroupe tous les boutons
 * comme "Gras" ou "Italique" etc...
 * Plus lisible si dans sa classe séparée.
 */

public class ActionsEdition extends JPanel{

    /** L'éditeur associé au menu */
    JTextPane textbox;

    /** Créer un nouveau menu d'édition */
    public ActionsEdition(JTextPane textbox) {
        // On lie l'éditeur à ce menu
        this.textbox = textbox;

        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

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
        this.add(leftActions);
        this.add(rightActions);
        
    }
    
}
