package GUI;
import javax.swing.*;
import java.awt.*;

public class Controleur extends JPanel {

    public Controleur(Vue vue, JFrame fenetre) {
        super(new FlowLayout());

        final JButton boutonQuitter = new JButton("Q");
        this.add(boutonQuitter);
        boutonQuitter.addActionListener(new ActionQuitter(fenetre));
    }

}
