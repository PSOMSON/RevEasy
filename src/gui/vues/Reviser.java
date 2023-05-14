package gui.vues;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JSplitPane;

import Model.Fiche;
import gui.AfficheurFiche;
import gui.Editeur;
import gui.IHM;
import gui.PopUpOpenFiche;

public class Reviser extends Vue implements AfficheurFiche {

    /**
     * Constructeur.
     * @param ihm Interface de l'application
     */
    public Reviser(IHM ihm) {
        super(new BorderLayout());
        
        // Créer la zone d'ouverture
        JButton openButton = new JButton("Ouvrir une fiche a reviser");
        openButton.addActionListener(e -> {
            PopUpOpenFiche popUp = new PopUpOpenFiche(this);
            popUp.setVisible(true);
        });


        // Créer la zone de recherche
        MenuLateral menuLateral = new MenuLateral(ihm);

        // Ajouter les deux zones à la vue en les séparant
        JSplitPane layout = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, menuLateral,
        		openButton);
        layout.setSize(this.getSize());

        this.add(layout, BorderLayout.CENTER);

    }

    @Override
    public void openFiche(Fiche f) {
        System.out.println("Ouvrir la fiche " + f.getTitre() + " a reviser");
    }

}
