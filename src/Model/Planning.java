import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

public class Planning {

    private String[][] planning;

    /**
     * Créer un planning de révision sous forme de tableau avec les jours de la
     * semaine et des cases que l'utilisateur devra remplir puis permettre
     * d'afficher le tableau
     *
     * @param args
     */
    public static void main(String[] args) {
        String[][] planning = new String[7][24];
        for (int i = 0; i < planning.length; i++) {
            for (int j = 0; j < planning[i].length; j++) {
                planning[i][j] = " ";
            }
        }
        planning[0][0] = "Lundi";
        planning[1][0] = "Mardi";
        planning[2][0] = "Mercredi";
        planning[3][0] = "Jeudi";
        planning[4][0] = "Vendredi";
        planning[5][0] = "Samedi";
        planning[6][0] = "Dimanche";
        for (int i = 0; i < planning.length; i++) {
            for (int j = 0; j < planning[i].length; j++) {
                System.out.print(planning[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * Obtenir le planning
     *
     * @return le planning
     */

    public String[][] getPlanning() {
        return planning;
    }

    /* permettre à l'utilisateur de remplir */
    public void remplirPlanning() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Veuillez entrer le jour de la semaine");
        String jour = sc.nextLine();
        System.out.println("Veuillez entrer l'heure");
        String heure = sc.nextLine();
        System.out.println("Veuillez entrer la matière");
        String matiere = sc.nextLine();
        planning[0][0] = "Lundi";
        planning[1][0] = "Mardi";
        planning[2][0] = "Mercredi";
        planning[3][0] = "Jeudi";
        planning[4][0] = "Vendredi";
        planning[5][0] = "Samedi";
        planning[6][0] = "Dimanche";
        for (int i = 0; i < planning.length; i++) {
            for (int j = 0; j < planning[i].length; j++) {
                if (planning[i][j].equals(jour)) {
                    planning[i][j] = matiere;
                }
            }
        }
    }

    /* afficher le planning sur une fenetre */
    public void afficherPlanning() {
        JFrame fenetre = new JFrame();
        fenetre.setTitle("Planning");
        fenetre.setSize(500, 500);
        fenetre.setLocationRelativeTo(null);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel pan = new JPanel();
        pan.setLayout(new GridLayout(7, 24));
        for (int i = 0; i < planning.length; i++) {
            for (int j = 0; j < planning[i].length; j++) {
                pan.add(new JButton(planning[i][j]));
            }
        }
        fenetre.setContentPane(pan);
        fenetre.setVisible(true);
    }
}
