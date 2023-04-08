package Model;
public class Question {

    private String question;

    private String[] reponses;

    private int[] indexReponseJuste;    

    /**
     * Constructeur d'une question. 
     * Il faut appeler setReponseJuste ensuite pour choisir quelles sont les
     * réponses correctes
     * @param question chaine contenant la question
     * @param reponses chaine(s) contenant(s) le(s) réponse(s)
     */
    public Question(String question, String... reponses) {
        this.question = question;
        this.reponses = reponses;
    }

    /**
     * Donner les numéro des réponses juste en commencant la numérotation à 0.
     * @param numeroReponsesJustes entier(s) désignant(s) la/les bonne(s) réponse(s)
     */
    public void setReponsesJustes(int... numeroReponsesJustes) {
        indexReponseJuste = numeroReponsesJustes;
    }

    /**
     * Obtenir le texte de la question
     * @return la question
     */
    public String getQuestion() {
        return question;
    }

    /**
     * Obtenir un tableau contenant les reponses
     * @return les réponses
     */
    public String[] getReponses() {
        return reponses;
    }

    /**
     * La réponse est-elle juste ?
     * @param reponse
     * @return
     */
    public boolean estJuste(String reponse) {
        for (int i = 0; i < indexReponseJuste.length; i++) {
            if (reponse.equals(reponses[i])) {
                return true;
            }
        }
        return false;
    }

}
