package gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.text.JTextComponent;

/** Pop up pour ajouter un théorème, une définition, une remarque (pas de qcm) etc... à
 * notre fiche.*/
public class PopUpAjout extends JDialog {

    /**
     * Créer une fenêtre pop-up.
     * @param textbox zone de texte à modifier
     * @param type type de contenu à ajouter (theorème, définition)
     */
    public PopUpAjout(JTextPane textbox, String type) {
        super();
        this.setLayout(new BorderLayout());
        this.setTitle("Ajouter un(e) " + type);

        JPanel contenu = new JPanel(new GridLayout(2,2));

        contenu.add(new JLabel("Nom : "));
        JTextField zoneNom = new JTextField();
        contenu.add(zoneNom);
        contenu.add(new JLabel("Contenu associé : "));
        JTextComponent zoneContenu = new JTextArea();
        contenu.add(zoneContenu);

        // Créer le bouton pour ajouter le contenu à la fiche
        JButton ajouter = new JButton("Ajouter");
        ajouter.addActionListener(ev -> {
            textbox.requestFocusInWindow();
            textbox.replaceSelection("\n\n@" + type + " " + zoneNom.getText() + "\n" +
            zoneContenu.getText());
            this.dispose();
        });

        this.add(contenu, BorderLayout.CENTER);
        this.add(ajouter, BorderLayout.SOUTH);
        this.setVisible(true);
        this.pack();
    }

}
