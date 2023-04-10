package GUI;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Map.Entry;
import java.util.Random;

import javax.swing.JEditorPane;

import Model.Fiche;
import Model.ModelReveasy;

public class VueMenuEditor extends JEditorPane {
	
	
	// Fiche existante
	public VueMenuEditor(Entry<String, Fiche> entry,ModelReveasy modelReveasy) {
		super();
		this.setText(entry.getValue().getTitre());
		
	}
	
	
	//Nouvelle Fiche
	public VueMenuEditor(ModelReveasy modelReveasy) {
		int rand_int = (new Random()).nextInt(100000);
		String nom_fichier = "NouvelleFiche"+ rand_int;
				
		String path = System.getProperty("user.dir") + ModelReveasy.FICHES_PATH + "/" + nom_fichier + "." + ModelReveasy.FICHES_EXTENSION;
		FileWriter myWriter;
		try {
			myWriter = new FileWriter(path);
			myWriter.write("Modifier ce texte !");
			myWriter.close();
			
			/** Ajouter le ficher au modele si la creation du fichier a ete realise avec succes**/
			modelReveasy.ajouterFiche(nom_fichier, new Fiche("NouvelleFiche" + "." + ModelReveasy.FICHES_EXTENSION, "Rien !"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		

	}
}
