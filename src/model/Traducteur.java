package model;

/** Classe de traduction du markdown vers le html (encore très simple).
 * Bientôt obscolète
 */

public class Traducteur {

    /**
     * Traduire du markdown en html.
     * @param texte texte à traduire
     * @return texte traduit en html
     */
    public static String mdToHtml(String texte) {
        String html = texte;
        html = html.replaceAll("\n", "<br>");
        html = html.replaceAll("\\*\\*(.*?)\\*\\*", "<b>$1</b>");
        html = html.replaceAll("\\*(.*?)\\*", "<i>$1</i>");
        html = html.replaceAll("\\[(.*?)\\]\\((.*?)\\)", "<a href=\"$2\">$1</a>");
        html = html.replaceAll("```(.*?)```", "<code>$1</code>");
        html = html.replaceAll("`(.*?)`", "<code>$1</code>");
        html = html.replaceAll("~~(.*?)~~", "<del>$1</del>");
        html = html.replaceAll("__(.*?)__", "<u>$1</u>");
        return html;
    }

    /**
     * Traduire les balises données par le modèle en html.
     * @param texte texte à traduire
     * @return texte traduit en html
     */
    public static String balises(String texte) {
        String html = texte;
        html = html.replaceAll("@Definition(.*?)", "<b>Definition : </b>$1");
        html = html.replaceAll("@Exemple(.*?)", "<b>Exemple : </b>$1");
        html = html.replaceAll("@Remarque(.*?)", "<b>Remarque : </b>$1");
        html = html.replaceAll("@Theoreme(.*?)", "<b>Theoreme : </b>$1");
        html = html.replaceAll("@Question(.*?)", "<b>Question : </b>$1");
        html = html.replaceAll("@ReponseV(.*?)", "<font color=\"green\"><b> "
        + "--> </b></font>$1");
        html = html.replaceAll("@ReponseF(.*?)", "<font color=\"red\"><b> "
        + "--> </b></font>$1");
        return html;
    }

}
