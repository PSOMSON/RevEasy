package GUI;

import java.awt.GridLayout;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import Model.Fiche;
import Model.ModelReveasy;

public class VueMenuMesFiches extends JPanel {
	public VueMenuMesFiches(JFrame fenetre, ModelReveasy modelReveasy) {
		super();
		Map<String, Fiche> fiches = modelReveasy.getFiches();
		if (fiches.size() == 0) {
			this.add(new JLabel("<html><i>Aucune  fiche !<i>", SwingConstants.CENTER));
		} else {
			this.setLayout(new GridLayout(3, 3));
			 for (Map.Entry<String, Fiche> entry : fiches.entrySet()) {
				 this.add(new IconFiche(fenetre, entry, modelReveasy));
			 }
				
		}
	}


}
