package GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Model.ModelReveasy;

public class VueEditeur extends JPanel {
	private JLabel texte;
	private ActionEnregistrer action;
	private JEditorPane editeur;

	// Fiche existante
	public VueEditeur(ModelReveasy modele) {
		super.setLayout(new BorderLayout());
		editeur = new JEditorPane();
		JPanel entete = new JPanel();
		entete.setLayout(new FlowLayout());
		this.add(editeur, BorderLayout.CENTER);
		this.add(entete, BorderLayout.NORTH);
		texte = new JLabel();
		entete.add(texte);
		JButton enregistrer = new JButton("Enregistrer");
		action = new ActionEnregistrer("", editeur, modele);
		enregistrer.addActionListener(action);
		entete.add(enregistrer);
	}

	public void setNomFiche(String nom) {
		texte.setText("nom fiche : "+nom);
		action.setNomFiche(nom);
	}

	public void setContenu(String contenu){
		editeur.setText(contenu);
	}
}
