package GUI;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

import javax.swing.*;
import javax.swing.text.html.HTMLEditorKit;

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
		Fiche fiche = modelReveasy.getFiches().get(nomFiche);

		// TODO : afficher la fiche.
		// Tentative :
		this.removeAll();
		JTextPane textPane = new JTextPane();
		textPane.setEditorKit(new HTMLEditorKit());

		try {
			FileReader reader = new FileReader(fiche.getTitre());
			textPane.read(reader, null);
		} catch (FileNotFoundException e) {
			System.out.println("Fichier non trouvé");
		} catch (IOException e) {
			System.out.println("Erreur d'entrée/sortie");
		}


		textPane.setText(fiche.getContenu());
		this.add(textPane);
		this.validate();
	}
}
