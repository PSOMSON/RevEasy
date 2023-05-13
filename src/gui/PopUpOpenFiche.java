package gui;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;

/**
 * Implémente une fenêtre pour ouvrir une fiche existante.
 * Les classes qui utlisent cette fenêtre doivent implémenter l'interface AfficheurFiche.
 * @see AfficheurFiche
 */
public class PopUpOpenFiche extends JDialog {

    /**
     * Constructeur.
     * @param object Classe qui instancie la fenêtre implémentant AfficheurFiche
     */
    public PopUpOpenFiche(AfficheurFiche object) {
        super();
        this.setLayout(new BorderLayout());

        this.setTitle("Ouvrir une fiche");

        Arborescence arborescence = new Arborescence();
        JButton openButton = new JButton("Ouvrir");
        JButton cancelButton = new JButton("Annuler");

        openButton.addActionListener(e -> {
            if (arborescence.getSelectedFiche() != null) {
                object.openFiche(arborescence.getSelectedFiche());
                this.dispose();
            }
        });

        cancelButton.addActionListener(e -> {
            this.dispose();
        });

        this.add(arborescence, BorderLayout.CENTER);
        this.add(openButton, BorderLayout.SOUTH);

        this.setResizable(false);
        this.setVisible(true);
        this.pack();
    }

}
