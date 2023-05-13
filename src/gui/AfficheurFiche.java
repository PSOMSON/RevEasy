package gui;

import Model.Fiche;

/**
 * Interface de marquage pour pouvoir lancer les popups d'ouverture de fiche.
 */
public interface AfficheurFiche {

    /**
     * Ouvrir une fiche.
     * @param f
     */
    void openFiche(Fiche f);

}
