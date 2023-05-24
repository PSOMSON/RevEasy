package gui.vues;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextPane;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.ImageIcon;

import Model.Fiche;
import Model.FicheSaver;
import Model.Traducteur;
import Model.PDFConverter;

import gui.IHM;

public class Consultation extends Vue {

	private Fiche fiche;

	/** Zone d'affichage du texte de la fiche. */
	private JTextPane zoneTexte;

	/**
	 * Initialiser la vue.
	 * 
	 * @param ihm Interface de l'application
	 */
	public Consultation(IHM ihm) {
		super(new BorderLayout());

		MenuLateral menuLateral = new MenuLateral(ihm);

		Liste fiches = new Liste(this);

		zoneTexte = new JTextPane();
		zoneTexte.setEditable(false);
		zoneTexte.setText("Pour consulter une fiche : cliquer sur une des fiches dans l'explorateur de fiches.");
		JPanel minilayout = new JPanel(new BorderLayout());
		JScrollPane zoneTexteScrollPane = new JScrollPane(zoneTexte);
		minilayout.add(zoneTexteScrollPane, BorderLayout.CENTER);
		JPanel options = new JPanel();
		options.setLayout(new FlowLayout(FlowLayout.TRAILING));

		// Création de l'icône pour le bouton ExportPDF
		ImageIcon icon = new ImageIcon("assets/exportPDF.png");
		Image img = icon.getImage();
		Image newimg = img.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
		icon = new ImageIcon(newimg);

		JButton exportButton = new JButton("ExportPDF", icon);
		exportButton.setBackground(Color.WHITE);
		exportButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exportPDF(fiche);
			}
		});
		options.add(exportButton);
		minilayout.add(options, BorderLayout.NORTH);

		JSplitPane layout = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, fiches, minilayout);
		layout.setSize(this.getSize());

		JSplitPane generalLayout = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, menuLateral, layout);
		generalLayout.setSize(this.getSize());

		this.add(generalLayout, BorderLayout.CENTER);
	}

	/**
	 * Ouvrir une fiche.
	 * 
	 * @param f Fiche à ouvrir
	 */
	public void ouvrir(Fiche f) {
		fiche = f;
		zoneTexte.setEditorKit(new HTMLEditorKit());

		String contenu = f.getContenu();
		contenu = Traducteur.balises(contenu);
		zoneTexte.setText(contenu);
	}

	/**
	 * Export en PDF.
	 * 
	 * @param f Fiche à exporter
	 */
	public void exportPDF(Fiche f) {
		if (f == null) {
			JOptionPane.showMessageDialog(null, "Veuillez ouvrir une fiche d'abord.", "Alert",
					JOptionPane.INFORMATION_MESSAGE);
		} else {
			String contenu = f.getContenu();
			contenu = Traducteur.balises(contenu);

			String homePath = System.getProperty("user.home");
			String outputPath = homePath + File.separator + FicheSaver.REVEASY_FOLDER + File.separator + "FichesPDF"
					+ File.separator + f.getTitre() + ".pdf";

			PDFConverter.convertToPDF(contenu, outputPath);

			File file = new File(outputPath);
			Desktop desktop = Desktop.getDesktop();

			File folder = new File(homePath + File.separator + FicheSaver.REVEASY_FOLDER + File.separator + "FichesPDF"
					+ File.separator);
			try {
				// Ouvrir le dossier contenant le PDF
				desktop.open(folder);
				// Ouvrir le PDF
				desktop.open(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
