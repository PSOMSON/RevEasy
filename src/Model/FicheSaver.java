package Model;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

/**
 * Classe qui sert d'inteface entre les fiches et les fichiers
 */
public class FicheSaver {
    
    public static final String REVEASY_FOLDER = "Reveasy";
    public static final String FICHES_FOLDER = "Fiches";

    private File fichesDir;

    /**
     * Constructeur, verifie l'architecture du dossier de sauvegarde
     */
    public FicheSaver() {
        
        String homepath = System.getProperty("user.home");

        // Verifier l'architecture du dossier de sauvegarde
        // Si le dossier Reveasy n'existe pas, le creer
        // Si le dossier Fiches n'existe pas, le creer
        // Sinon, ne rien faire

        File home = new File(homepath);
        
        if (home.exists()) {
            File reveasyDir = new File(homepath + File.separator + REVEASY_FOLDER);
            if (!reveasyDir.exists()) {
                reveasyDir.mkdir();
            }
            fichesDir = new File(homepath + File.separator + REVEASY_FOLDER + File.separator + FICHES_FOLDER);
            if (!fichesDir.exists()) {
                fichesDir.mkdir();
            }
        } else {
            System.out.println("Erreur : impossible de trouver le dossier utilisateur");
        } 
    }

    /**
     * Sauvegarder une fiche
     * @param fiche
     */
    public void sauvegarder(Fiche fiche) {

        File ficheFile = new File(fichesDir.toPath() + File.separator + fiche.getTitre() + ".txt");

        try (FileWriter writer = new FileWriter(ficheFile)) {
            
            writer.write(fiche.getContenu());

        } catch (Exception e) {
            // TODO: handle exception
        }        
    }

    public Fiche ouvrir(String path) {
        
        File ficheFile = new File(path);

        if (!ficheFile.exists()) {
            // TODO: Exception
            System.out.println("Erreur : impossible de trouver la fiche");
            return null;
        }

        try (FileReader reader = new FileReader(ficheFile)) {
            
            String contenu = "";
            int c;
            while ((c = reader.read()) != -1) {
                contenu += (char) c;
            }

            // Extraire le titre de la fiche du path
            final String FOLDER = "Reveasy/Fiches/";  
            String titre = path.substring(path.indexOf(FOLDER) + FOLDER.length(), path.length() - ".txt".length());

            return new Fiche(titre, contenu);

        } catch (Exception e) {
            // TODO: handle exception
        }
        return null;
    }

}