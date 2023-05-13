package Model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Classe représentant les fiches créées dans l'application.
 */
public class Fiche {

    private String titre;
    private List<String> texte;

    private final Map<Enonce.Type, String> balises = Map.of(Enonce.Type.DEFINITION, "@Definition", Enonce.Type.THEOREME,
            "@Theoreme");

    /**
     * Constructeur d'une Fiche.
     * 
     * @param titre   titre de la fiche
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
            i = contenu.indexOf("\n", i + 1);
        }
        this.texte.add(contenu.substring(debut));
    }

    /**
     * Obtenir la liste de tout les theoremes de la fiche.
     * 
     * @return Liste d'Enonce de type THEOREME
     */
    public List<Enonce> getTheoremes() {
        return getEnonces(Enonce.Type.THEOREME);
    }

    /**
     * Obtenir la liste de toute les definitions de la fiche.
     * 
     * @return Liste d'Enonce de type DEFINITION
     */
    public List<Enonce> getDefinitions() {
        return getEnonces(Enonce.Type.DEFINITION);
    }

    /**
     * Obtenir la liste de toute les questions
     * 
     * @return Liste de Question
     */
    public List<Question> getQuestions() {
        List<Question> questions = new LinkedList<>();
        String balise = "@Question";
        String rep = "@Reponse";

        int taille = texte.size();
        int i = 0;
        while (i < taille) {
            if (texte.get(i).contains(balise)) {
                // Titre de la question
                String titreq = texte.get(i).substring(balise.length() + 1).strip();

                List<String> repsV = new LinkedList<>();
                List<String> repsF = new LinkedList<>();

                // Reponses
                while (i + 1 < taille && texte.get(i + 1).contains(rep)) {
                    i++;
                    if (texte.get(i).charAt(rep.length() + 1) == 'V') {
                        repsV.add(texte.get(i).substring(rep.length() + 2).strip());
                    } else if (texte.get(i).charAt(rep.length() + 1) == 'F') {
                        repsF.add(texte.get(i).substring(rep.length() + 2).strip());
                    } else {
                        throw new Error("Mauvaise balise");
                        // TODO: Creer une exception personalisée
                    }
                }

                questions.add(new Question(titreq, repsV.toArray(new String[repsV.size()]),
                        repsF.toArray(new String[repsF.size()])));
            }
            i++;
        }
        return questions;
    }

    /**
     * Acceder au titre d'une fiche.
     * 
     * @return Le titre de la fiche
     */
    public String getTitre() {
        return titre;
    }

    /**
     * Obtenir un itérateur sur les lignes d'une fiche.
     * 
     * @return iterator
     */
    public Iterator<String> getContenuIterator() {
        return texte.iterator();
    }

    /**
     * Obtenir le contenu (brut) de la fiche.
     * 
     * @return
     */
    public String getContenu() {
        StringBuilder contenu = new StringBuilder();

        for (String ligne : texte) {
            contenu.append(ligne);
        }

        return contenu.toString();
    }

    /**
     * Factorisation du code de getThm et getDef.
     * 
     * @param type Type d'enoncé à chercher dans la fiche.
     * @return Liste d'enoncé de type @param type
     */
    private List<Enonce> getEnonces(Enonce.Type type) {
        List<Enonce> enonces = new LinkedList<>();
        String balise = balises.get(type);

        for (int i = 0; i < texte.size(); i++) {
            String titredef, contenudef;
            if (texte.get(i).contains(balise)) {
                try {
                    titredef = texte.get(i).substring(balise.length() + 1).strip();
                    contenudef = texte.get(i + 1).strip();
                    enonces.add(new Enonce(type, titredef, contenudef));
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Mauvaise usage d'une balise " + balise);
                }
            }
        }
        return enonces;
    }

}