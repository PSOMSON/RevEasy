import java.util.HashSet;

public class Fiche {

    String titre;

    HashSet<Enonce> definitions;

    HashSet<Enonce> theoremes;

    /**
     * Constructeur d'une Fiche
     * @param contenu
     */
    public Fiche(String contenu) {
        String monContenu = contenu;
        
        // Parser le titre
        String[] separation = monContenu.split("\n", 2);
        this.titre = separation[0];
        monContenu = separation[1];

        // Parser les enonces
        int i = monContenu.indexOf("@Definition");
        while (i != -1) {
            int finDuTitre = monContenu.indexOf("\n", i);
            String titreEnonce = monContenu.substring(i, finDuTitre);
            String contenuEnonce = monContenu.substring(finDuTitre+1, monContenu.indexOf("\n", finDuTitre+1));
            
            definitions.add(new Enonce(Enonce.Type.DEFINITION, titreEnonce, contenuEnonce));

            i = monContenu.indexOf("@Definition");
        }
        
        // TODO : finir le parsing
    }
    
}