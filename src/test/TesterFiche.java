import org.junit.*;

import Model.Fiche;

import static org.junit.Assert.*;

import java.io.IOException;

public class TesterFiche {

    private Fiche fiche1;

    @Before
    public void setUp() throws IOException {
        String contenu = "Titre!\n\n@Theoreme Pythagore\nA carre\nSalut\n\n@Definition Feur\nBlague\n";
        fiche1 = new Fiche(contenu);
    }

    @Test
    public void testerRecupererTitre() {
        assertTrue("Le titre est mal récupéré.", fiche1.getTitre().equals("Titre!"));
    }

    @Test
    public void testerRecupererDefinitions() {
        
    }


}