package Model;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Fiche {

    //TODO: Ajouter les questions
    //TODO: javadoc

    private String titre;
    private List<String> texte; 

    private final Map<Enonce.Type, String> balises = 
            Map.of(Enonce.Type.DEFINITION, "@Definition", Enonce.Type.THEOREME, "@Theoreme");

    /**
     * Constructeur d'une Fiche.
     * @param titre titre de la fiche
     * @param contenu chaine de caractere contenant la fiche
     */
    public Fiche(String titre, String contenu) {
        this.titre = titre;
        this.texte = new ArrayList<>();

        // Découper le contenu en ligne de contenu
        int i = contenu.indexOf("\n");
        int debut = 0;
        while (i != -1) {
            this.texte.add(contenu.substring(debut, i));
            debut = i;
            i = contenu.indexOf("\n",i+1);
        }
    }


    public List<Enonce> getTheoremes() {
        List<Enonce> theoremes = new LinkedList<>();

        String balise = balises.get(Enonce.Type.THEOREME);

        for (int i = 0; i < texte.size(); i++) {
            String titrethm, contenuthm;
            if (texte.get(i).contains(balise)) {
                try {
                    titrethm = texte.get(i).substring(balise.length()+1).strip();
                    contenuthm = texte.get(i+1);
                    theoremes.add(new Enonce(Enonce.Type.THEOREME, titrethm, contenuthm));
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Mauvaise usage d'une balise @Theoreme");
                }
            }
        }
        return theoremes;
    }

    public List<Enonce> getDefinitions() {
        List<Enonce> definitions = new LinkedList<>();

        String balise = balises.get(Enonce.Type.DEFINITION);

        for (int i = 0; i < texte.size(); i++) {
            String titredef, contenudef;
            if (texte.get(i).contains(balise)) {
                try {
                    titredef = texte.get(i).substring(balise.length()+1).strip();
                    contenudef = texte.get(i+1);
                    definitions.add(new Enonce(Enonce.Type.DEFINITION, titredef, contenudef));
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Mauvaise usage d'une balise @Definition");
                }
            }
        }
        return definitions;
    }

    public String getTitre() {
        return titre;
    }

    public Iterator<String> getContenuIterator() {
        return texte.iterator();
    }
    
}