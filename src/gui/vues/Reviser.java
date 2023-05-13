package gui.vues;

import java.awt.BorderLayout;

import javax.swing.JButton;

import Model.Fiche;
import gui.AfficheurFiche;
import gui.IHM;
import gui.PopUpOpenFiche;

public class Reviser extends Vue implements AfficheurFiche {

    /**
     * Constructeur.
     * @param ihm Interface de l'application
     */
    public Reviser(IHM ihm) {
        super(new BorderLayout());

        JButton openButton = new JButton("Ouvrir une fiche a reviser");
        openButton.addActionListener(e -> {
            PopUpOpenFiche popUp = new PopUpOpenFiche(this);
            popUp.setVisible(true);
        });

        this.add(openButton, BorderLayout.CENTER);
    }

    @Override
    public void openFiche(Fiche f) {
        System.out.println("Ouvrir la fiche " + f.getTitre() + " a reviser");
    }

}
