import java.awt.EventQueue;

import gui.IHM;

public final class RevEasy {

    /**
     * Lancer l'application RevEasy.
     * @param args
     */
    public static void main(String[] args) {


        // Lancer l'IHM
        EventQueue.invokeLater(new Runnable() {
			public void run() {
				new IHM();
			}
		});
    }

}
