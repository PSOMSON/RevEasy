public interface ModelReveasyInterface {
    /**
     * Ajouter une fiche à la collection de fiches
     * @param fiche la fiche à ajouter
     */
    public void ajouterFiche(Fiche fiche);

    /**
     * Obtenir la liste des fiches
     * @return la liste des fiches
     */
    public List<Fiche> getFiches();

    /**
     * Rechercher les fiches contenant un mot clé dans leur titre ou leur contenu
     * @param motCle le mot clé à chercher
     * @return la liste des fiches correspondantes
     */
    public List<Fiche> rechercherFiches(String motCle);

    /**
     * Supprimer une fiche de la collection de fiches
     * @param fiche la fiche à supprimer
     */
    public void supprimerFiche(Fiche fiche);
}
