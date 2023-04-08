import GUI.IHM;
import java.awt.EventQueue;

public class Reveasy {

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
			public void run() {
				new IHM();
			}
		});
    }

}