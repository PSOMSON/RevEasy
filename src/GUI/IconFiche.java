package GUI;


import java.util.Map.Entry;


import javax.swing.JLabel;

import GUI.MenuLateral.ComposantPrincipaux;;
import Model.Fiche;
import Model.ModelReveasy;

public class IconFiche extends JLabel {
	public IconFiche(IHM fenetre, Entry<String, Fiche> entry,ModelReveasy modelReveasy) {
		super(entry.getKey());
		this.addMouseListener(new ActionMenuSelector(fenetre, ComposantPrincipaux.EDITOR));
	}
}
