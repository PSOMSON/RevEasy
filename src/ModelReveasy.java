import java.util.ArrayList;
import java.util.List;

public class ModelReveasy implements ModelReveasyInterface {
    private List<Fiche> fiches;

    /**
     * Constructeur de Reveasy
     */
    public Reveasy() {
        fiches = new ArrayList<>();
    }

    /**
     * Ajouter une fiche à la collection de fiches
     * @param fiche la fiche à ajouter
     */
    public void ajouterFiche(Fiche fiche) {
        fiches.add(fiche);
    }

    /**
     * Obtenir la liste des fiches
     * @return la liste des fiches
     */
    public List<Fiche> getFiches() {
        return fiches;
    }

    /**
     * Rechercher les fiches contenant un mot clé dans leur titre ou leur contenu
     * @param motCle le mot clé à chercher
     * @return la liste des fiches correspondantes
     */
    public List<Fiche> rechercherFiches(String motCle) {
        List<Fiche> resultats = new ArrayList<>();

        for (Fiche fiche : fiches) {
            if (fiche.getTitre().toLowerCase().contains(motCle.toLowerCase())) {
                resultats.add(fiche);
            } else {
                for (Enonce enonce : fiche.getDefinitions()) {
                    if (enonce.getTitre().toLowerCase().contains(motCle.toLowerCase()) ||
                        enonce.getCorps().toLowerCase().contains(motCle.toLowerCase())) {
                        resultats.add(fiche);
                        break;
                    }
                }
            }
        }

        return resultats;
    }

    /**
     * Supprimer une fiche de la collection de fiches
     * @param fiche la fiche à supprimer
     */
    public void supprimerFiche(Fiche fiche) {
        fiches.remove(fiche);
    }
}