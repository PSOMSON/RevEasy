package Model;

import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Compte {

    /* compte utilisateur : */
    private String nom;
    private String prenom;
    private String email;
    private String motDePasse;
    private String photoDeProfil;
    private String themeDeCouleur;
    private Fiche fiches;

    public Compte(String nom, String prenom, String email, String motDePasse, String themeDeCouleur, Fiche fiches) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.motDePasse = motDePasse;
        this.themeDeCouleur = themeDeCouleur;
        this.fiches = fiches;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getEmail() {
        return email;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public Fiche getFiches() {
        return fiches;
    }

    /* modifier le compte */
    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public void setThemeDeCouleur(String themeDeCouleur) {
        this.themeDeCouleur = themeDeCouleur;
    }

    /*
     * créer des cases à remplir pour mettre son nom et son prénom et récuper les
     * informations données au clavier
     */
    public enregistrer(){
        System.out.println("Veuillez entrer votre nom : ");
        Scanner sc = new Scanner(System.in);
        String nom = sc.nextLine();
        System.out.println("Veuillez entrer votre prénom : ");
        String prenom = sc.nextLine();
        System.out.println("Veuillez entrer votre email : ");
        String email = sc.nextLine();
        System.out.println("Veuillez entrer votre mot de passe : ");
        String motDePasse = sc.nextLine();
    }

    /* afficher le compte sur une fenetre */
    public void afficherCompte() {
        JFrame fenetre = new JFrame();
        fenetre.setTitle("Compte");
        fenetre.setSize(400, 500);
        fenetre.setLocationRelativeTo(null);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.setVisible(true);
    }

    /* afficher les fiches de révision sur une fenetre */
    public void afficherFiches() {
        JFrame fenetre = new JFrame();
        fenetre.setTitle("Fiches de révision");
        fenetre.setSize(400, 500);
        fenetre.setLocationRelativeTo(null);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.setVisible(true);
    }

    /* afficher les infos du compte sur la fentre principale */
    public void afficherInfosCompte(){
        JFrame fenetre = new JFrame();
        fenetre.setTitle("Infos du compte");
        fenetre.setSize(400, 500);
        fenetre.setLocationRelativeTo(null);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.setVisible(true);
        //afficher le nom le prénom et l'email :
        JLabel label = new JLabel("Nom : " + nom + " Prénom : " + prenom + " Email : " + email);
        fenetre.getContentPane().add(label);
        //permettre de modifier le nom le prénom l'email et le mot de passe :
        JButton bouton = new JButton("Modifier");
        fenetre.getContentPane().add(bouton);
        // quand on clique sur le bouton modifier, on peut modifier les infos du compte :
        bouton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // on peut modifier le nom le prénom l'email et le mot de passe :
                System.out.println("Veuillez entrer votre nom : ");
                Scanner sc = new Scanner(System.in);
                String nom = sc.nextLine();
                System.out.println("Veuillez entrer votre prénom : ");
                String prenom = sc.nextLine();
                System.out.println("Veuillez entrer votre email : ");
                String email = sc.nextLine();
                System.out.println("Veuillez entrer votre mot de passe : ");
                String motDePasse = sc.nextLine();
            }
        });
        });
}
}
