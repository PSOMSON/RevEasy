package GUI;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import GUI.MenuLateral.ComposantPrincipaux;
import Model.Fiche;
import Model.ModelReveasy;

public class VueMesFiches extends JPanel {
	private JLabel selectedIcon;
	private IHM fenetre;
	private ModelReveasy modelReveasy;
	

	public VueMesFiches(IHM fenetre, ModelReveasy modelReveasy) {
		super();
		this.fenetre = fenetre;
		this.modelReveasy = modelReveasy;

		Map<String, Fiche> fiches = modelReveasy.getFiches();
		if (fiches.size() == 0) {
			this.add(new JLabel("<html><i>Aucune  fiche !<i>", SwingConstants.CENTER));
		} else {
			this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

			for (Map.Entry<String, Fiche> entry : fiches.entrySet()) {
				JLabel label = new JLabel(entry.getKey());
				label.addMouseListener(new LabelMouseListener(entry));
				this.add(label);
			}
		}
	}

	private class LabelMouseListener extends MouseAdapter {
		private Entry<String, Fiche> entry;
		
		public LabelMouseListener(Entry<String, Fiche> entry) {
			this.entry = entry;
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			JLabel clickedIcon = (JLabel) e.getSource();
			if (e.getClickCount() == 1) {
				// Single click
				if (selectedIcon != null) {
					// Clear the previous selection
					selectedIcon.setForeground(Color.BLACK);
				}
				selectedIcon = clickedIcon;
				selectedIcon.setForeground(Color.WHITE);
			} else if (e.getClickCount() == 2) {
				ActionMenuSelector open = new ActionMenuSelector(fenetre, ComposantPrincipaux.EDITOR);
				open.mouseClicked(e);
			}
		}
	}

}
