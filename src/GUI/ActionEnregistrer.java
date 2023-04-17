package GUI;

import javax.swing.*;


import Model.Fiche;
import Model.ModelReveasy;

import java.awt.event.*;

/** Action d'enregistrer une fiche. */
public class ActionEnregistrer implements ActionListener {

    /** Zone de texte associé au bouton qui contient le nom de la fiche à enregistrer. */
    String nomFiche;

    /** Editeur associé, qui contient le contenu. */
    JEditorPane editeur;

    /** Modele. */
    ModelReveasy modele;

    public ActionEnregistrer(String nomFichier, JEditorPane editeur, ModelReveasy modele) {
        this.nomFiche = nomFichier;
        this.editeur = editeur;
        this.modele = modele;
    }

    public void setNomFiche(String nomFiche) {
        this.nomFiche = nomFiche;
    }

    @Override
    public void actionPerformed(ActionEvent ev) {
        String contenu = this.editeur.getText();
        // Enregistrer la fiche.
        modele.ajouterFiche(nomFiche, new Fiche(nomFiche, contenu));
    }

}
