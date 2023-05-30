package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import model.Question;

/**
 * Classe de test de la classe Model.Question.java.
 */
public class TesterQuestion {
    Question q1, q2;

    @Before
    public void setUp() {
        String[] rep1 = {"un", "deux", "trois"};
        String[] rep2 = {};
        String[] rep3 = {"janvier"};
        q1 = new Question("question 1", rep1, rep3);
        q2 = new Question("", rep2, rep3);
    }

    private void assertStrEqual(String msg, String s1, String s2) {
        assertTrue(msg, s1.equals(s2));
    }

    @Test
    public void testerGetQuestion() {
        assertStrEqual("Titre invalide !", q1.getQuestion(), "question 1");
        assertStrEqual("Titre vide attendu !", q2.getQuestion(), "");
    }

    @Test
    public void testerGetReponses() {
        assertStrEqual("Reponse attendu differente de reponse obtenu",
            q1.getReponses()[0], "un");
        assertStrEqual("Reponse attendu differente de reponse obtenu",
            q1.getReponses()[1], "deux");
        assertStrEqual("Reponse attendu differente de reponse obtenu",
            q1.getReponses()[2], "trois");

        assertEquals("Nombre de reponses incorrecte", q2.getReponses().length, 1);
    }

    @Test
    public void testerEstJuste() {
        assertFalse(q1.estJuste(" un "));
        assertTrue(q1.estJuste("deux"));
        assertFalse(q1.estJuste("janvier"));
        assertFalse(q2.estJuste(null));
    }
}
