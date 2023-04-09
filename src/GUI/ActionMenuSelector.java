package GUI;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Map.Entry;

import javax.swing.JComponent;
import javax.swing.JFrame;

import GUI.MenuLateral.Menu;
import Model.Fiche;
import Model.ModelReveasy;

/** Classe temporaire pour tester des trucs. */
public class ActionMenuSelector extends MouseAdapter {


	private JFrame fenetre;

	private MenuLateral.Menu menu;

	private ModelReveasy modelReveasy;

	private Entry<String, Fiche> entry;

	public ActionMenuSelector(JFrame fenetre, MenuLateral.Menu menu, ModelReveasy modelReveasy) {
		super();
		this.fenetre = fenetre;
		this.menu = menu;
		this.modelReveasy = modelReveasy;
	}

	public ActionMenuSelector(JFrame fenetre, MenuLateral.Menu menu, ModelReveasy modelReveasy,Entry<String, Fiche> entry) {
		this(fenetre, menu, modelReveasy);
		this.entry = entry;
	}
	
	
	
	




	@Override
	public void mouseClicked(MouseEvent ev) {

		Component comp = ((BorderLayout) fenetre.getContentPane().getLayout()).getLayoutComponent(BorderLayout.CENTER);
		while (comp != null) {
			fenetre.remove(comp);
			comp = ((BorderLayout) fenetre.getContentPane().getLayout()).getLayoutComponent(BorderLayout.CENTER);
		}
		if (menu == MenuLateral.Menu.CREER) {
			fenetre.add(new VueMenuEditor(modelReveasy), BorderLayout.CENTER);
		} else if (menu == MenuLateral.Menu.MESFICHES) {
			fenetre.add(new VueMenuMesFiches(fenetre, modelReveasy), BorderLayout.CENTER);
		} else if (menu == MenuLateral.Menu.EDITOR) {
			fenetre.add(new VueMenuEditor(entry, modelReveasy), BorderLayout.CENTER);
		}


		// Refresh the frame
		fenetre.revalidate();
		fenetre.repaint();

	}

}
