import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class temp {
    public static void main(String[] args) throws FileNotFoundException {
        
        String contenuFiche;
        try (Scanner file = new Scanner(new File("exemple_fiche.txt"))) {
            contenuFiche = file.useDelimiter("\\Z").next();
        }
        
        Fiche fiche1 = new Fiche(contenuFiche);
        System.out.println(fiche1.getTitre());
        System.out.println(fiche1.getDefinitions().get(1).getCorps());
    }
}
