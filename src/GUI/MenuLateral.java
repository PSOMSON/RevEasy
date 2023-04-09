package GUI;

import java.awt.GridLayout;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Model.Fiche;
import Model.ModelReveasy;

public class MenuLateral extends JPanel {
	public JComponent vueMenuMesFiches;
	
	public enum Menu {
        MESFICHES,
        CREER,
        EDITOR,
    }
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
     * @param modelReveasy 
     * @param vueMenuMesFiches 
     * @param vueMenuCreer 
     * @param ihm */
    public MenuLateral(JFrame fenetre, ModelReveasy modelReveasy) {
        super(new GridLayout(12, 1)); // TODO : Regler problème de taille du menu latéral.
        this.add(creer);
        this.add(mesFiches);
        this.add(reviser);
        this.add(notes);
        this.add(parametres);
        

        

        /** Controleurs pour lancer les differentes vues */
        creer.addMouseListener(new ActionMenuSelector(fenetre, Menu.CREER, modelReveasy));
        mesFiches.addMouseListener(new ActionMenuSelector(fenetre, Menu.MESFICHES, modelReveasy));

    }
}
