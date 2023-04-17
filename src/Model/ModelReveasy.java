package Model;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ModelReveasy {
	static public String FICHES_PATH = "/src/fiches/"; //A voir car sur Eclipse: FICHES_PATH = "/src/fiches/", sinon ca ne marche pas
	static public String FICHES_EXTENSION = "txt";

    /** TODO : considérer plutôt un ensemble (ou liste) de fiche ? la map n'a pas de sens
     *  puisque l'on a déjà l'information des clés (String) dans la Fiche.
     */
    private Map<String, Fiche> fiches;

    /**
     * Constructeur de Reveasy
     */
    public ModelReveasy() {
    	fiches = new HashMap<String, Fiche>();
    	String path = System.getProperty("user.dir") + FICHES_PATH ;
    	File directory = new File(path);
        File[] files = directory.listFiles();
        if (files != null) {
	        for (File file : files) {
	            if (file.isFile() && file.getName().endsWith("."+ FICHES_EXTENSION)) {
	            	String contenu = "";
	            	try {
	            		contenu = new String(java.nio.file.Files.readAllBytes(file.toPath()));
	            		
	            	} catch (Exception e) {
	            		;
	            	}
	            	String filename = file.getName().substring(0, file.getName().length()-FICHES_EXTENSION.length()-1);
	                fiches.put(filename, new Fiche("titre",contenu));
	            }
	        }
        }
    }

    
    /**
     * Creer une fiche et le fichier associe dans le dossier fiches
     * @param nom nom de la fiche à creer
     * @param contenu contenu de la fiche
     */
    public void ajouterFiche(String nomFiche,Fiche fiche) {
        FileWriter myWriter;
		try {
			// Il faut que le dossier fiches existe
			myWriter = new FileWriter(new File(System.getProperty("user.dir") + FICHES_PATH, nomFiche+ "." + FICHES_EXTENSION));
			myWriter.write(fiche.getContenu());
			myWriter.close();

			/** Ajouter le ficher au modele si la creation du fichier a ete realise avec succes**/
			fiches.put(nomFiche, fiche);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    /**
     * Obtenir la liste des fiches
     * @return la liste des fiches
     */
    public Map<String, Fiche> getFiches() {
        return fiches;
    }

    /**
     * Rechercher les fiches contenant un mot clé dans leur titre ou leur contenu
     * @param motCle le mot clé à chercher
     * @return la liste des fiches correspondantes
     */
    /**public List<Fiche> rechercherFiches(String motCle) {
        List<Fiche> resultats = new ArrayList<>();

        for (Fiche fiche : fiches) {
            if (fiche.getTitre().toLowerCase().contains(motCle.toLowerCase())) {
                resultats.add(fiche);
            } else {
                for (Enonce enonce : fiche.getDefinitions()) {
                    if (enonce.getTitre().toLowerCase().contains(motCle.toLowerCase()) ||
                        enonce.getCorps().toLowerCase().contains(motCle.toLowerCase())) {
                        resultats.add(fiche);
                        break;
                    }
                }
            }
        }

        return resultats;
    }**/

    /**
     * Supprimer une fiche de la collection de fiches
     * @param fiche la fiche à supprimer
     */
    public void supprimerFiche(String nomFiche) {
        fiches.remove(nomFiche);
    }
}
