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
        JComponent vue_par_defaut = new VueMenuMesFiches(this, modelReveasy);
        Controleur controleur = new Controleur(this);
        
        MenuLateral menuLateral = new MenuLateral(this, modelReveasy);
        this.setLayout(new BorderLayout());
        this.add(vue_par_defaut, BorderLayout.CENTER);
        this.add(controleur, BorderLayout.SOUTH);
        this.add(menuLateral, BorderLayout.WEST);
        this.pack();
        this.setSize(LARGEUR_DEFAUT, HAUTEUR_DEFAUT);
        this.setVisible(true);
    }

}
