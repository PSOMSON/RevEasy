package Model;

import javax.swing.Action;

/** Classe qui décrit une action de style (mettre en gras, surligner, centrer du texte etc.). Contient l'indice de départ ainsi que l'indice de fin
 * de la sélection concernée par l'action.
 * Exemple : je met en gras les mots "font" dans "Ansi font font sont les petites marionnettes !".
 * L'indice de départ est 5 (le "f" du premier "font") et l'indice de fin est 13 (le "t" du dernier "font").
 * 
 * Chaque action de style est ordonnée par son indice de début.
 */
public class ActionStyle implements Comparable<ActionStyle> {
    private int debut;
    private int fin;
    private Action action;
    private String baliseGauche;
    private String baliseDroite;

    /**
     * Constructeur d'une action de style.
     * @param debut indice de départ de la sélection concernée par l'action
     * @param fin indice de fin de la sélection concernée par l'action
     * @param action action à effectuer
     */
    public ActionStyle(int debut, int fin, Action action, String baliseGauche, String baliseDroite) {
        this.debut = debut;
        this.fin = fin;
        this.action = action;
        this.baliseGauche = baliseGauche;
        this.baliseDroite = baliseDroite;
    }

    /**
     * Obtenir l'indice de départ de la sélection concernée par l'action.
     * @return indice de départ
     */
    public int getDebut() {
        return debut;
    }

    /**
     * Obtenir l'indice de fin de la sélection concernée par l'action.
     * @return indice de fin
     */
    public int getFin() {
        return fin;
    }

    /**
     * Obtenir l'action à effectuer.
     * @return action
     */
    public Action getAction() {
        return action;
    }

    /**
     * Obtenir la balise de gauche.
     * @return balise de gauche
     */
    public String getBaliseGauche() {
        return baliseGauche;
    }

    /**
     * Obtenir la balise de droite.
     * @return balise de droite
     */
    public String getBaliseDroite() {
        return baliseDroite;
    }

    @Override
    public int compareTo(ActionStyle o) {
        if (o == null) {
            throw new NullPointerException();
        }
        return this.debut - o.debut;
    }

}
