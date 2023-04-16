package GUI;

import javax.swing.*;
import javax.swing.text.JTextComponent;

import Model.ModelReveasy;

import java.awt.*;

public class ControleurEditeur extends JPanel {

    /** Largeur de la zone de texte pour donner le titre de la fiche. */
    private final static int LARGEUR = 40;

    public ControleurEditeur(JFrame fenetre, JEditorPane editeur, ModelReveasy modele) {
        super(new FlowLayout());

        // Ajouter une zone pour écrire le nom de la fiche
        JTextComponent zoneNom = new JTextArea(1, LARGEUR);
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
