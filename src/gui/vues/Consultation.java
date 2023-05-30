package gui.vues;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
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

import gui.IHM;
import model.Fiche;
import model.FicheSaver;
import model.PDFConverter;
import model.Traducteur;

public class Consultation extends Vue {

	private Fiche fiche;

	/** Zone d'affichage du texte de la fiche. */
	private JTextPane zoneTexte;

	private JSplitPane generalLayout;
	private MenuLateral menuLateral;
	private IHM ihm;

	/**
	 * Initialiser la vue.
	 * 
	 * @param ihm Interface de l'application
	 */
	public Consultation(IHM ihm) {
		super(new BorderLayout());
		this.ihm = ihm;

		menuLateral = new MenuLateral(ihm);

		Liste fiches = new Liste(this);

		zoneTexte = new JTextPane();
		zoneTexte.setEditable(false);
		zoneTexte.setText("Pour consulter une fiche : cliquer sur une des fiches dans l'explorateur de fiches.");
		JPanel minilayout = new JPanel(new BorderLayout());
		JScrollPane zoneTexteScrollPane = new JScrollPane(zoneTexte);
		minilayout.add(zoneTexteScrollPane, BorderLayout.CENTER);
		JPanel options = new JPanel();
		options.setLayout(new FlowLayout(FlowLayout.TRAILING));
		
		// Création de l'icône pour le bouton modifier
		ImageIcon icon = new ImageIcon("assets/edit.png");
		Image img = icon.getImage();
		Image newimg = img.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
		icon = new ImageIcon(newimg);
				
		JButton editButton = new JButton("Modifier", icon);
		editButton.setBackground(Color.WHITE);
		editButton.setPreferredSize(new Dimension(editButton.getPreferredSize().width,30));
		editButton.addActionListener(e->{modifierFiche(fiche);});
		options.add(editButton);

		// Création de l'icône pour le bouton ExportPDF
		icon = new ImageIcon("assets/exportPDF.png");
		img = icon.getImage();
		newimg = img.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
		icon = new ImageIcon(newimg);
		
		JButton exportButton = new JButton("ExportPDF", icon);
		exportButton.setBackground(Color.WHITE);
		exportButton.setPreferredSize(new Dimension(exportButton.getPreferredSize().width,30));
		exportButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exportPDF(fiche);
			}
		});
		options.add(exportButton);
		
		// Création de l'icône pour le bouton supprimer
		icon = new ImageIcon("assets/delete.png");
		img = icon.getImage();
		newimg = img.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
		icon = new ImageIcon(newimg);
				
		JButton deleteButton = new JButton(icon);
		deleteButton.setBackground(Color.WHITE);
		deleteButton.setPreferredSize(new Dimension(30,30));
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				supprimer(fiche);
			}
		});
		options.add(deleteButton);

		minilayout.add(options, BorderLayout.NORTH);

		JSplitPane layout = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, fiches, minilayout);
		layout.setSize(this.getSize());

		generalLayout = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, menuLateral, layout);
		generalLayout.setSize(this.getSize());

		this.add(generalLayout, BorderLayout.CENTER);
	}
	
	
	/**
	 * Modifier une fiche.
	 * @param fiche Fiche à modifier
	 */
	private void modifierFiche(Fiche fiche) {
		if (fiche == null) 
			JOptionPane.showMessageDialog(null, "Veuillez ouvrir une fiche d'abord.", "Alert",
					JOptionPane.INFORMATION_MESSAGE);
		else {
			ihm.editer(fiche);
		}
		
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
		if (f == null)
			JOptionPane.showMessageDialog(null, "Veuillez ouvrir une fiche d'abord.", "Alert",
					JOptionPane.INFORMATION_MESSAGE);
		else {
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
	
	
	/**
	 * Supprimer une fiche.
	 * 
	 * @param f Fiche à supprimer
	 */
	public void supprimer(Fiche f) {
	    if (f == null) {
	        JOptionPane.showMessageDialog(null, "Veuillez ouvrir une fiche d'abord.", "Alert",
	                JOptionPane.INFORMATION_MESSAGE);
	    } else {
	    	int choix = JOptionPane.showConfirmDialog(null, "Êtes-vous sûr de vouloir supprimer la fiche ?", "Confirmation de suppression",
	                JOptionPane.YES_NO_OPTION);
	        
	        if (choix == JOptionPane.YES_OPTION) {
	            String homePath = System.getProperty("user.home");
	            File path = new File(homePath + File.separator + FicheSaver.REVEASY_FOLDER
		                + File.separator + FicheSaver.FICHES_FOLDER + File.separator + f.getTitre() + ".txt");

	            // Supprimer le fichier s'il existe
	            if (path.exists()) {
	                boolean deleted = path.delete();
	                if (deleted) {
	                    JOptionPane.showMessageDialog(null, "La fiche a été supprimée avec succès.", "Succès",
	                            JOptionPane.INFORMATION_MESSAGE);
	                } else {
	                    JOptionPane.showMessageDialog(null, "Impossible de supprimer la fiche.", "Erreur",
	                            JOptionPane.ERROR_MESSAGE);
	                }
	            } else {
	                JOptionPane.showMessageDialog(null, "La fiche n'existe pas.", "Erreur",
	                        JOptionPane.ERROR_MESSAGE);
	            }
	        }
	    }
	}

	
}
