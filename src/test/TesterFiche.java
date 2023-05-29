package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import Model.Fiche;

/**
 * Classe de test de la classe Model.Fiche.java.
 */
public class TesterFiche {

    private Fiche fiche1, fiche2;

    @Before
    public void setUp() {
        String contenu1 = "@Theoreme Pythagore\nA carre\nSalut\n\n@Definition Feur\nBlague\n@Theoreme Deux\n\n";
        String contenu2 = "@Theoreme J\nlala\n@Question une question?\n@ReponseV juste\n@ReponseF faux\n@ReponseV j\n@Question q?\n@ReponseV oui";

        fiche1 = new Fiche("Titre!", contenu1);

        fiche2 = new Fiche("Deuxieme fiche", contenu2);
    }

    private void assertStrEqual(String msg, String s1, String s2) {
        assertTrue(msg, s1.equals(s2));
    }

    @Test
    public void testerRecupererTitre() {
        assertStrEqual("Le titre est mal récupéré.", fiche1.getTitre(), "Titre!");
    }

    @Test
    public void testerRecupererDefinitions1() {
        assertEquals(fiche1.getDefinitions().size(), 1);
    }

    @Test
    public void testerRecupererDefinitions2() {
        assertStrEqual("Titre de la définition mal récupéré", fiche1.getDefinitions().get(0).getTitre(), "Feur");
        assertStrEqual("Corps de la définition mal récupéré", fiche1.getDefinitions().get(0).getCorps(), "Blague");
    }

    @Test
    public void testerRecupererTheoreme1() {
        assertEquals(fiche1.getTheoremes().size(), 2);
    }

    @Test
    public void testerRecupererTheoreme2() {
        assertStrEqual("Titre de la définition mal récupéré", fiche1.getTheoremes().get(0).getTitre(), "Pythagore");
        assertStrEqual("Titre de la définition mal récupéré", fiche1.getTheoremes().get(1).getTitre(), "Deux");
    }

    @Test
    public void testerRecupererQuestion1() {
        assertEquals(fiche2.getQuestions().size(), 2);
    } 
    
    @Test
    public void testerRecupererQuestion2() {
        assertStrEqual("Question mal récupérée", fiche2.getQuestions().get(0).getQuestion(), "une question?");
        assertStrEqual("Question mal récupérée", fiche2.getQuestions().get(1).getQuestion(), "q?");
    }

    @Test
    public void testerRecupererQuestion3() {
        System.out.println(fiche2.getQuestions().get(1).getReponses().length);
        assertStrEqual("Reponse mal récupérée", fiche2.getQuestions().get(0).getReponses()[0], "juste");
        assertStrEqual("Reponse mal récupérée", fiche2.getQuestions().get(0).getReponses()[2], "faux");
        assertStrEqual("Reponse mal récupérée", fiche2.getQuestions().get(1).getReponses()[0], "oui");
        assertTrue(fiche2.getQuestions().get(1).estJuste("oui"));
    }
}