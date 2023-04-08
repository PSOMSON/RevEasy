package GUI;

import javax.swing.*;

import Model.ModelReveasy;

import java.awt.*;

public class IHM extends JFrame {

    private final int LARGEUR_DEFAUT = 720;
    private final int HAUTEUR_DEFAUT = 480;
    
    /** Construire l'interface homme machine. */
    public IHM() {
        super("Reveasy");
        
        // Modele
        ModelReveasy modelReveasy = new ModelReveasy();
        // Vue
        Controleur controleur = new Controleur(this);
        
        MenuLateral menuLateral = new MenuLateral(this);
        this.setLayout(new BorderLayout());
        this.add(menuLateral.vueMenuMesFiches, BorderLayout.CENTER); // A Voir
        this.add(controleur, BorderLayout.SOUTH);
        this.add(menuLateral, BorderLayout.WEST);
        this.pack();
        this.setSize(LARGEUR_DEFAUT, HAUTEUR_DEFAUT);
        this.setVisible(true);
    }

}
