package GUI;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class ActionMenuSelector extends MouseAdapter {

	/** Fenêtre. */
	private IHM fenetre;

	/** Identifiant correspondant au nouveau composant que l'on souhaite afficher. */
	private MenuLateral.ComposantPrincipaux nouveauComposant;

	public ActionMenuSelector(IHM fenetre, MenuLateral.ComposantPrincipaux nouveauComposant) {
		super();
		this.fenetre = fenetre;
		this.nouveauComposant = nouveauComposant;
	}

	@Override
	public void mouseClicked(MouseEvent ev) {
		fenetre.setPanelPrincipal(nouveauComposant);
	}

}
