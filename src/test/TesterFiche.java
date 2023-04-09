package test;

import org.junit.*;

import Model.Fiche;

import static org.junit.Assert.*;

import java.io.IOException;

public class TesterFiche {

    private Fiche fiche1;

    @Before
    public void setUp() throws IOException {
        String contenu = "Titre!\n\n@Theoreme Pythagore\nA carre\nSalut\n\n@Definition Feur\nBlague\n@Theoreme Deux\n\n";
        fiche1 = new Fiche("Titre!", contenu);
    }

    @Test
    public void testerRecupererTitre() {
        assertTrue("Le titre est mal récupéré.", fiche1.getTitre().equals("Titre!"));
    }

    @Test
    public void testerRecupererDefinitions1() {
        assertEquals(fiche1.getDefinitions().size(), 1);
    }

    public void testerRecupererDefinitions2() {
        assertTrue(fiche1.getDefinitions().get(0).getTitre().equals("Feur"));
        assertTrue(fiche1.getDefinitions().get(0).getCorps().equals("Blague"));
    }

    @Test
    public void testerRecupererTheoreme1() {
        assertEquals(fiche1.getTheoremes().size(), 2);
    }

    @Test
    public void testerRecupererTheoreme2() {
        System.out.println(fiche1.getTheoremes().get(1).getTitre());
        //assertTrue(fiche1.getTheoremes().get(0).getTitre().equals("Pythagore"));
        assertTrue(fiche1.getTheoremes().get(1).getTitre().equals("Deux"));
    }

    //TODO: Ajouter les test des questions

}