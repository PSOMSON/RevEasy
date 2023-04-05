import org.junit.*;
import static org.junit.Assert.*;

import java.io.FileNotFoundException;

public class TesterFiche {

    private Fiche fiche1;
    private final String contenu = "Titre de la fiche\n\n@Definition mot\nla definition du mot sur cette ligne\n@Theoreme nom du théorème\nénoncé du théorème sur cette ligne\\nLorem ipsum dolor sit amet, consectetur adipiscing elit. \nNullam bibendum tortor est, vitae consequat massa imperdiet nec. \nSed eu sem magna. Vestibulum iaculis dui sapien, in porta lorem luctus ac. \nDonec ac sem viverra, dapibus arcu non, auctor erat. Suspendisse non eros eget augue congue fringilla. \nMaecenas sapien dolor, aliquet at elit quis, tristique vulputate magna. Sed tempus nunc leo, at auctor eros dictum quis. \nPhasellus ac viverra nulla. Proin fermentum neque placerat tincidunt laoreet. \nInteger eu lacus ut ante semper facilisis in pulvinar neque. Praesent non leo euismod, \nfringilla velit ac, hendrerit elit. Morbi blandit ex in est tempor ornare. \nEtiam vulputate metus nibh, sed lobortis sem consequat nec. \n\n\n@Question Le latin est une langue morte ?\n@Reponse Oui\n@Reponse Non";

    @Before
    public void setUp() throws FileNotFoundException {
        fiche1 = new Fiche(contenu);
    }

    @Test
    public void testerRecupererTitre() {
        assertTrue("Le titre est mal récupéré.", fiche1.getTitre().equals("Titre de la fiche"));
    }

    @Test
    public void testerRecupererDefinitions() {

    }


}