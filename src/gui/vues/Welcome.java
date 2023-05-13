package gui.vues;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import gui.IHM;

/**
 * Vue de bienvenue.
 */
public class Welcome extends Vue {

    /**
     * Constructeur.
     * @param ihm Interface de l'application
     */
    public Welcome(IHM ihm) {
        super(new GridLayout(3, 1));

        // Créer le label de bienvenue
        JLabel bienvenue = new JLabel("Bienvenue sur Reveasy");
        bienvenue.setHorizontalAlignment(JLabel.CENTER);
        bienvenue.setFont(new Font("Arial", Font.BOLD, 25));
        this.add(bienvenue);

        // Créer le la séléction de vue
        JLabel selection = new JLabel("Que souhaitez vous faire ?");
        selection.setHorizontalAlignment(JLabel.CENTER);
        this.add(selection);

        // Créer les bouton
        JButton nouvelleFiche = new JButton("Nouvelle fiche");
        JButton vueConsultation = new JButton("Parcourir les fiches");
        JButton quitter = new JButton("Quitter");

        // Ajouter les boutons à un panel
        JPanel boutons = new JPanel(new FlowLayout());
        boutons.add(nouvelleFiche);
        boutons.add(vueConsultation);
        boutons.add(quitter);

        // Ajouter le panel de boutons à la vue
        this.add(boutons);

        // Ajouter les listeners
        nouvelleFiche.addActionListener(ihm);
        vueConsultation.addActionListener(ihm);
        quitter.addActionListener(ihm);

        // Ajouter les commandes
        nouvelleFiche.setActionCommand("vueEdition");
        vueConsultation.setActionCommand("vueConsultation");
        quitter.setActionCommand("quitter");
    }

}
