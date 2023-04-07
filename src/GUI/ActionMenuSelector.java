import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComponent;
import javax.swing.JFrame;


/** Classe temporaire pour tester des trucs. */
public class ActionMenuSelector extends MouseAdapter{
	
	private JComponent nouvelleVue;

	private JFrame fenetre;

	
	public ActionMenuSelector(JFrame fenetre, JComponent nouvelleVue) {
		super();

		this.nouvelleVue = nouvelleVue;
		this.fenetre = fenetre;

	}
    @Override
    public void mouseClicked(MouseEvent ev) {
        
    	Component comp = ((BorderLayout) fenetre.getContentPane().getLayout()).getLayoutComponent(BorderLayout.CENTER);
    	if (comp != null) {
    		fenetre.remove(comp);;
    	}
    	fenetre.add(nouvelleVue, BorderLayout.CENTER);
    	
    	// Refresh the frame
        fenetre.revalidate();
        fenetre.repaint();

    }
}
