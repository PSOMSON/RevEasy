import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class MenuLateral extends JPanel {

    /** Zones de textes du menu.*/

    /** Créer une fiche. */
    private JLabel creer = new JLabel("Créer une fiche");

    /** Mes fiches. */
    private JLabel mesFiches = new JLabel("Mes Fiches");

    /** Réviser.*/
    private JLabel reviser = new JLabel("Reviser");

    /** Mes notes.*/
    private JLabel notes = new JLabel("Mes notes");

    private JLabel parametres = new JLabel("Paramètres");

    /** Construire un menu lateral */
    public MenuLateral() {
        super(new GridLayout(12, 1)); // TODO : Regler problème de taille du menu latéral. 
        this.add(creer);
        this.add(mesFiches);
        this.add(reviser);
        this.add(notes);
        this.add(parametres);

        creer.addMouseListener(new ActionTemp());
        mesFiches.addMouseListener(null);
    }
}
