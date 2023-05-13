package gui.vues;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import gui.IHM;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

/**
 * Classe pour afficher le menu latéral qui donne accès aux différentes vues.
 */
public class MenuLateral extends Vue {

    /** Nombre de boutons. */
    private static final int NOMBRE_DE_BOUTONS = 4;
    /** Largeur des boutons. */
    private static final int LARGEUR_BOUTON = 200;

    /**
     * Liste des boutons.
     */
    private JButton[] buttons = new JButton[NOMBRE_DE_BOUTONS];

    /**
     * Initialiser la vue.
     * @param ihm Interface de l'application
     */
    public MenuLateral(IHM ihm) {
        super(new BorderLayout());

        // Créer le layout
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));;

        // Ajouter les boutons
        buttons[0] = new JButton("Edition");
        buttons[0].setActionCommand("vueEdition");
        buttons[0].addActionListener(ihm);

        buttons[1] = new JButton("Consultation");
        buttons[1].setActionCommand("vueConsultation");
        buttons[1].addActionListener(ihm);

        buttons[2] = new JButton("Revision");
        buttons[2].setActionCommand("vueRevision");
        buttons[2].addActionListener(ihm);

        // TODO: Factoriser le code des boutons
        buttons[3] = new JButton("Quitter");
        buttons[3].setActionCommand("quitter");
        buttons[3].addActionListener(ihm);


        for (int i = 0; i < NOMBRE_DE_BOUTONS; i++) {
            buttons[i].setAlignmentX(CENTER_ALIGNMENT);
            buttons[i].setBorderPainted(false);
            buttons[i].setBackground(Color.WHITE);
            buttons[i].setFont(new Font("Arial", Font.BOLD, 18));
            buttons[i].setPreferredSize(new Dimension(LARGEUR_BOUTON,
                                                buttons[i].getPreferredSize().height));
            JPanel container = new JPanel(new BorderLayout());
            container.add(buttons[i], BorderLayout.CENTER);
            panel.add(container);
        }

        // Ajouter le layout à la vue
        this.add(panel, BorderLayout.CENTER);
    }

}
