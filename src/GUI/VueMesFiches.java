package GUI;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import Model.Fiche;
import Model.ModelReveasy;

public class VueMesFiches extends JPanel {

	private IHM fenetre; // On va peut-etre s'en servir selon l'implémentation de visionnerFiche.
	private ModelReveasy modelReveasy;
	

	public VueMesFiches(IHM fenetre, ModelReveasy modelReveasy) {
		super();
		this.fenetre = fenetre;
		this.modelReveasy = modelReveasy;
		mettreAJourVueFiches();
	}

	public void mettreAJourVueFiches() {
		this.removeAll();
		Map<String, Fiche> fiches = modelReveasy.getFiches();
		if (fiches.size() == 0) {
			this.add(new JLabel("<html><i>Aucune  fiche !<i>", SwingConstants.CENTER));
		} else {
			this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

			for (Map.Entry<String, Fiche> entry : fiches.entrySet()) {
				JLabel label = new JLabel(entry.getKey());
				label.addMouseListener(new LabelMouseListener());
				this.add(label);
			}
		}
	}

	private class LabelMouseListener extends MouseAdapter {

		@Override
		public void mouseClicked(MouseEvent e) {
			JLabel clickedIcon = (JLabel) e.getSource();
			visionnerFiche(clickedIcon);
		}
	}

	/**
	 * Visionner une fiche (TODO : Pour l'instant on peut afficher le contenu brut, modifier ?)
	 * @param clickedIcon fiche que l'on a selectionner en cliquand dessus
	 */
	protected void visionnerFiche(JLabel clickedIcon) {
		String nomFiche = clickedIcon.getText();
		System.out.println(nomFiche);
		Fiche fiche = modelReveasy.getFiches().get(nomFiche);

		// TODO : afficher la fiche.
		System.out.println(fiche.getContenu());
	}
}
