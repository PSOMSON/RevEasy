package gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
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
        String femininPotentiel = (type.equals("Definition")) ? "e " : " ";
        this.setTitle("Ajouter un" + femininPotentiel + type);

        JPanel formulaire = new JPanel();
        formulaire.setLayout(new BoxLayout(formulaire, BoxLayout.Y_AXIS));

        JPanel formulaireNom = new JPanel(new GridLayout());
        formulaireNom.add(new JLabel("Nom : "));
        JTextField zoneNom = new JTextField(20);
        formulaireNom.add(zoneNom);

        JPanel formulaireContenu = new JPanel(new GridLayout());
        formulaireContenu.add(new JLabel("Contenu associé : "));
        JTextComponent zoneContenu = new JTextArea(5, 20);
        formulaireContenu.add(zoneContenu);

        formulaire.add(formulaireNom);
        formulaire.add(formulaireContenu);

        // Créer le bouton pour ajouter le contenu à la fiche
        JButton ajouter = new JButton("Ajouter");
        ajouter.addActionListener(ev -> {
            textbox.requestFocusInWindow();
            textbox.replaceSelection("@" + type + " " + zoneNom.getText() + "\n" +
            zoneContenu.getText());
            this.dispose();
        });

        this.add(formulaire, BorderLayout.CENTER);
        this.add(ajouter, BorderLayout.SOUTH);
        this.setVisible(true);
        this.pack();
    }

}
