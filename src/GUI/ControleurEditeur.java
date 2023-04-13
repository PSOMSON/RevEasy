package GUI;

import javax.swing.*;

import Model.ModelReveasy;

import java.awt.*;

public class ControleurEditeur extends JPanel {

    public ControleurEditeur(JFrame fenetre, JEditorPane editeur, ModelReveasy modele) {
        super(new FlowLayout());

        // Ajouter une zone pour écrire le nom de la fiche
        JTextArea zoneNom = new JTextArea();
        Dimension tailleMin = new Dimension(300, 50);
        zoneNom.setMinimumSize(tailleMin);
        this.add(zoneNom);

        // Ajouter un bouton pour enregistrer la fiche
        final JButton boutonEnregistrer = new JButton("E");
        this.add(boutonEnregistrer);
        boutonEnregistrer.addActionListener(new ActionEnregistrer(zoneNom, editeur, modele));

        // Ajouter un bouton quitter.
        final JButton boutonQuitter = new JButton("Q");
        this.add(boutonQuitter);
        boutonQuitter.addActionListener(new ActionQuitter(fenetre));
    }

}
