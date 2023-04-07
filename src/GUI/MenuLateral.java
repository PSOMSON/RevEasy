import java.awt.GridLayout;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MenuLateral extends JPanel {
	public JComponent vueMenuMesFiches;

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

    /** Construire un menu lateral 
     * @param vueMenuMesFiches 
     * @param vueMenuCreer 
     * @param ihm */
    public MenuLateral(JFrame fenetre) {
        super(new GridLayout(12, 1)); // TODO : Regler problème de taille du menu latéral.
        this.add(creer);
        this.add(mesFiches);
        this.add(reviser);
        this.add(notes);
        this.add(parametres);
        
        
        

        /** Les differentes vues du programme */
        vueMenuMesFiches = new VueMenuMesFiches();
        JComponent vueMenuCreer = new VueMenuCreer();
        

        /** Controleurs pour lancer les differentes vues */
        creer.addMouseListener(new ActionMenuSelector(fenetre, vueMenuCreer));
        mesFiches.addMouseListener(new ActionMenuSelector(fenetre, vueMenuMesFiches ));

    }
}
