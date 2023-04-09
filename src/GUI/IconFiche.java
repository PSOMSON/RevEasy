package GUI;


import java.util.Map.Entry;

import javax.swing.JFrame;
import javax.swing.JLabel;

import GUI.MenuLateral.Menu;
import Model.Fiche;
import Model.ModelReveasy;

public class IconFiche extends JLabel {
	public IconFiche(JFrame fenetre, Entry<String, Fiche> entry,ModelReveasy modelReveasy) {
		super(entry.getKey());
		this.addMouseListener(new ActionMenuSelector(fenetre, Menu.EDITOR, modelReveasy, entry));
	}
}
