package GUI;

import javax.swing.*;
import javax.swing.text.JTextComponent;

import Model.Fiche;
import Model.ModelReveasy;

import java.awt.event.*;

import java.io.FileWriter;
import java.io.IOException;

public class ActionEnregistrer implements ActionListener {

    /** Zone de texte associé au bouton qui contient le nom de la fiche à enregistrer. */
    JTextComponent zoneTexte;

    /** Editeur associé, qui contient le contenu. */
    JEditorPane editeur;

    /** Modele. */
    ModelReveasy modele;

    public ActionEnregistrer(JTextComponent zoneTexte, JEditorPane editeur, ModelReveasy modele) {
        this.zoneTexte = zoneTexte;
        this.editeur = editeur;
        this.modele = modele;
    }

    @Override
    public void actionPerformed(ActionEvent ev) {
        String nom = this.zoneTexte.getText();
        if (nom.equals("")) {
            JOptionPane.showMessageDialog(editeur, "Le nom de la fiche ne peut pas être vide ! ");
        }
        else {
            // Enregistrer la fiche.
            String path = System.getProperty("user.dir") + ModelReveasy.FICHES_PATH + "/" + nom + "." + ModelReveasy.FICHES_EXTENSION;
            FileWriter myWriter;
            try {
                myWriter = new FileWriter(path);
                myWriter.write(editeur.getText());
                myWriter.close();

                /** Ajouter le ficher au modele si la creation du fichier a été realisée avec succes. **/
                modele.ajouterFiche(nom, new Fiche("NouvelleFiche" + "." + ModelReveasy.FICHES_EXTENSION, "Rien !"));
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

}
