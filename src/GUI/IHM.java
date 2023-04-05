package GUI;
import javax.swing.*;
import java.awt.*;

public class IHM extends JFrame {

    private final int LARGEUR_DEFAUT = 720;
    private final int HAUTEUR_DEFAUT = 480;

    /** Construire l'interface homme machine. */
    public IHM() {
        super("Reveasy");
        Vue vue = new Vue();
        Controleur controleur = new Controleur(vue, this);
        MenuLateral menuLateral = new MenuLateral();
        this.setLayout(new BorderLayout());
        this.add(vue, BorderLayout.CENTER);
        this.add(controleur, BorderLayout.SOUTH);
        this.add(menuLateral, BorderLayout.WEST);
        this.pack();
        this.setSize(LARGEUR_DEFAUT, HAUTEUR_DEFAUT);
        this.setVisible(true);
    }

    public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				new IHM();
			}
		});
	}
}
