package GUI;
import javax.swing.*;
import java.awt.event.*;

/** Action quitter la fênetre. */
public class ActionQuitter implements ActionListener {

    /** Fenetre à quitter. */
    private JFrame fenetre;

    public ActionQuitter(JFrame fenetre) {
        this.fenetre = fenetre;
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        fenetre.dispose();
    }
}