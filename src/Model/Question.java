package Model;

import java.util.Arrays;
import java.util.stream.IntStream;

public class Question {

    private String question;

    private String[] reponses;

    private int[] indexReponseJuste;    

    /**
     * Constructeur d'une question.
     * @param titreq
     * @param reponsesJustes
     * @param reponsesFausses
     */
    public Question(String question, String[] reponsesJustes, String[] reponsesFausses) {
        this.question = question;

        this.reponses = concatWithArrayCopy(reponsesJustes, reponsesFausses);

        this.indexReponseJuste = IntStream.range(0, reponsesJustes.length).toArray();
    }

    private static <T> T[] concatWithArrayCopy(T[] array1, T[] array2) {
        T[] result = Arrays.copyOf(array1, array1.length + array2.length);
        System.arraycopy(array2, 0, result, array1.length, array2.length);
        return result;
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
