package GUI;

import javax.swing.*;

import Model.ModelReveasy;

import java.awt.*;

public class Controleur extends JPanel {

    public Controleur(JFrame fenetre, JPanel editeur, ModelReveasy modele) {
        super(new FlowLayout());

        // Ajouter un bouton quitter.
        final JButton boutonQuitter = new JButton("Quitter");
        this.add(boutonQuitter);
        boutonQuitter.addActionListener(new ActionQuitter(fenetre));
    }

}
