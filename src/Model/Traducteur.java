package Model;

/** Classe de traduction du markdown vers le html (encore très simple)
 * Bientôt obscolète
 */

public class Traducteur {
    
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

}
