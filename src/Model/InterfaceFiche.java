package Model;

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;

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
    
    	/* Méthode qui permet de mettre en couleur la police 
	 * @param text
	 * @param couleur
	 */
	public void setColor(String text ,Color couleur) {
		
		// création d'un JLabel
		JLabel label = new JLabel(text);
		label.setForeground(couleur);
		// ajouter le texte coloré à la fenêtre
		// pour que la modification soit visible à l'utilisateur
		frame.add(label);
		
		}
	
	
	/* Méthode qui modifie la taille du texte
	 * @param text
	 * @param size
	 */
	public void setSize(String text, int size) {
		
		// création d'un JLabel
		JLabel label = new JLabel(text);
		Font text1 = label.getFont();  // la police actuelle
		Font Font1 = new Font(text1.getFontName(), text1.getStyle(), size);
		// Défibir la taille actuel du texte 
		label.setFont(Font1);
		// ajouter le texte coloré à la fenêtre
		// pour que la modification soit visible à l'utilisateur
		jFrame.add(label);
		
		}
	
	/* Méthode qui modifie la police du texte
	 * @param text
	 * @param police
	 */
	public void setPolice(String text, String police) {
		
		// création d'un JLabel
		JLabel label = new JLabel(text);
		Font actual = label.getFont();
		Font newFont = new Font(police,actual.getStyle(),actual.getSize());
		label.setFont(newFont);
		// ajouter le texte coloré à la fenêtre
		// pour que la modification soit visible à l'utilisateur
		// Changer après jFrame par le nom de mon objet JFrame 
		jFrame.add(label);
		
		}

}
