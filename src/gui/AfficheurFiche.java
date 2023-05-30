package gui;

import model.Fiche;

/**
 * Interface pour pouvoir lancer les popups d'ouverture de fiche.
 */
public interface AfficheurFiche {

    /**
     * Ouvrir une fiche.
     * @param f
     */
    void openFiche(Fiche f);


}
