package Model;

/** Traduit le Markdown en HTML, utile pour l'enregistrement des fiches.
 * Motivation : Le panelEditor de swing affiche les pages en HTML,
 * et créer un nouvel éditor est aussi long que fastidieux (manque de documentation).*/

public class Translator {

    /** Traduit le Markdown en HTML.
     * @param markdown Markdown à traduire.
     * @return HTML traduit. */
    public static String toHTML(String markdown) {
        String html = markdown;
        html = html.replaceAll("\\*\\*(.*?)\\*\\*", "<b>$1</b>");
        html = html.replaceAll("\\*(.*?)\\*", "<i>$1</i>");
        html = html.replaceAll("\\~\\~(.*?)\\~\\~", "<s>$1</s>");
        html = html.replaceAll("\\`(.*?)\\`", "<code>$1</code>");
        html = html.replaceAll("\\n\\n", "<br><br>");
        html = html.replaceAll("\\n", "<br>");
        return html;
    }

}
