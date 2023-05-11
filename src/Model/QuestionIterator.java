// est-ce que ce ne serait pas mieux d'utiliser un Iterator ?

package Model;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Question implements Iterator<String> {

    private String question;
    private List<String> reponses;
    private int currentIndex;

    /**
     * Constructeur d'une question.
     *
     * @param question        le texte de la question
     * @param reponsesJustes  les réponses correctes
     * @param reponsesFausses les réponses incorrectes
     */
    public Question(String question, String[] reponsesJustes, String[] reponsesFausses) {
        this.question = question;
        this.reponses = concatWithArrayCopy(Arrays.asList(reponsesJustes), Arrays.asList(reponsesFausses));
        this.currentIndex = 0;
    }

    private static <T> List<T> concatWithArrayCopy(List<T> list1, List<T> list2) {
        List<T> result = new ArrayList<>(list1);
        result.addAll(list2);
        return result;
    }

    /**
     * Obtenir le texte de la question.
     *
     * @return la question
     */
    public String getQuestion() {
        return question;
    }

    /**
     * Implémentation de la méthode hasNext() de l'interface Iterator.
     * Vérifie s'il reste des réponses non parcourues.
     *
     * @return true s'il reste des réponses, false sinon
     */
    @Override
    public boolean hasNext() {
        return currentIndex < reponses.size();
    }

    /**
     * Implémentation de la méthode next() de l'interface Iterator.
     * Renvoie la prochaine réponse et avance l'index.
     *
     * @return la prochaine réponse
     */
    @Override
    public String next() {
        String reponse = reponses.get(currentIndex);
        currentIndex++;
        return reponse;
    }

    /**
     * La réponse est-elle juste ?
     *
     * @param reponse la réponse à vérifier
     * @return true si la réponse est correcte, false sinon
     */
    public boolean estJuste(String reponse) {
        return Arrays.asList(reponses).contains(reponse);
    }
}
