package Model;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Fiche {

    private String titre;

    private List<Enonce> definitions;

    private List<Enonce> theoremes;

    private List<Question> questions;

    private final Map<Enonce.Type, String> balises = 
            Map.of(Enonce.Type.DEFINITION, "@Definition", Enonce.Type.THEOREME, "@Theoreme");

    /**
     * Constructeur d'une Fiche, extrait les differents constituant de la fiche
     * @param contenu chaine de caractere contenant la fiche
     */
    public Fiche(String contenu) {
        
        // Parser le titre
        this.titre = contenu.split("\n", 2)[0];

        // Parser les enonces
        definitions = parser_enonces(contenu, Enonce.Type.DEFINITION);
        theoremes = parser_enonces(contenu, Enonce.Type.THEOREME);

        // Parser les questions
        //questions = parser_questions(contenu);
    }

    private List<Enonce> parser_enonces(String contenu, Enonce.Type type) {
        String balise = balises.get(type);
        LinkedList<Enonce> enonces = new LinkedList<>();
        int i = contenu.indexOf(balise);
        while (i != -1) {
            int fin = contenu.indexOf("\n", i);

            enonces.add(new Enonce(
                type, 
                contenu.substring(i, fin), 
                contenu.substring(fin+1, contenu.indexOf("\n", fin+1))));

            i = contenu.indexOf(balise, i+1);
        }
        return enonces;
    }

    //private List<Question> parser_questions(String contenu) {
    //    LinkedList<Question> questions = new LinkedList<>();
    //    int i = contenu.indexOf("@Question");
    //    while (i != -1) {
    //        int fin = contenu.indexOf("\n", i);
//
    //        String tq = contenu.substring(i, fin);
    //        i = contenu.indexOf("@Question");
    //        List<String> reps;
//
    //        int j = contenu.indexOf("@ReponseV", fin);
    //        int f = contenu.indexOf("@ReponseF", fin);
    //        while ((j != -1 || f != -1) && (j<i) || (f<i)) {
    //            int r = Math.min(j, f);
    //            fin = contenu.indexOf("\n", fin);
    //            reps.add(contenu.substring(r, fin));
//
    //            j = contenu.indexOf("@ReponseV", fin);
    //            f = contenu.indexOf("@ReponseF", fin);
    //        }
//
    //        
    //    }
//
    //    return questions
    //}

    public String getTitre() {
        return titre;
    }

    public List<Enonce> getDefinitions() {
        return definitions;
    }
    
}