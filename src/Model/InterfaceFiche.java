package Model;

import javax.swing.JFrame;

public class InterfaceFiche extends JFrame {

    /* Si markdown signigie souligner on souligne le texte */
    private String souligner(String text) {
        String result = text;
        if (text.contains("**")) {
            result = "<html><u>" + text + "</u></html>"; // on met le texte en html pour pouvoir le souligner
        }
        return result;
    }

    /* on encadre */
    private String encadrer(String text) {
        String result = text;
        if (text.contains("::::")) {
            result = "<html><u>" + text + "</u></html>";
        }
        return result;
    }

    /* on met en gras */
    private String gras(String text) {
        String result = text;
        if (text.contains("****")) {
            result = "<html><u>" + text + "</u></html>";
        }
        return result;
    }

    /* on met en italique */
    private String italique(String text) {
        String result = text;
        if (text.contains("**")) {
            result = "<html><u>" + text + "</u></html>";
        }
        return result;
    }

    /* on met une couleur */
    private String couleur(String text) {
        String result = text;
        if (text.contains("##")) {
            result = "<html><u>" + text + "</u></html>";
        }
        return result;
    }

    /* on met une citation */
    private String citation(String text) {
        String result = text;
        if (text.contains(">>")) {
            result = "<html><u>" + text + "</u></html>";
        }
        return result;
    }

    /* on met une division */
    private String division(String text) {
        String result = text;
        if (text.contains("----")) {
            result = "<html><u>" + text + "</u></html>";
        }
        return result;
    }

}
