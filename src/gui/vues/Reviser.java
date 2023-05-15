package gui.vues;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JSplitPane;
import javax.swing.JPanel;

import Model.Enonce;
import Model.Fiche;
import gui.AfficheurFiche;
import gui.Editeur;
import gui.IHM;
import gui.PopUpOpenFiche;

public class Reviser extends Vue implements AfficheurFiche {
	
	private MenuLateral menuLateral;
	private JButton openButton;
	
	private JLabel theoremLabel;
    private JButton revealButton;
    private JButton nextButton;
    private JButton exitButton;
    private List<Enonce> theoremList;
    private int currentTheoremIndex;
	
    /**
     * Constructeur.
     * @param ihm Interface de l'application
     */
    public Reviser(IHM ihm) {
        super(new BorderLayout());
        
        // Créer la zone d'ouverture
        openButton = new JButton("Ouvrir une fiche a reviser");
        openButton.addActionListener(e -> {
            PopUpOpenFiche popUp = new PopUpOpenFiche(this);
            popUp.setVisible(true);
        });


        // Créer la zone de recherche
        menuLateral = new MenuLateral(ihm);

        // Ajouter les deux zones à la vue en les séparant
        JSplitPane layout = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, menuLateral,
        		openButton);
        layout.setSize(this.getSize());

        this.add(layout, BorderLayout.CENTER);

    }

    @Override
    public void openFiche(Fiche f) {

        // Créer la zone du choix de test
        JPanel panelTest = new JPanel(new BorderLayout());
        JButton testFlashCards = new JButton("Flash Cards");
        JButton testTexteAtrou = new JButton("Texte A trou");
        JPanel choixTest = new JPanel(new FlowLayout());
        panelTest.add(choixTest, BorderLayout.CENTER);
        choixTest.add(testFlashCards);
        choixTest.add(testTexteAtrou);
        testFlashCards.addActionListener(e->{startTestFlashCards(f);});
        
        
        // Ajouter les deux zones à la vue en les séparant
        JSplitPane layout = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, menuLateral,
        		panelTest);
        layout.setSize(this.getSize());
        
        // Change les elements de la vue
        this.removeAll();
        this.add(layout, BorderLayout.CENTER);
        this.revalidate();
        this.repaint();
    }

	private void startTestFlashCards(Fiche fiche) {
		// Créer la zone du test
		JPanel testZone = new JPanel(new BorderLayout());

	    theoremList = fiche.getTheoremes();
        currentTheoremIndex = 0;


        // Create UI components
        theoremLabel = new JLabel(theoremList.get(currentTheoremIndex).getTitre());
        revealButton = new JButton("Révéler la réponse");
        nextButton = new JButton("Théorème suivant");
        exitButton = new JButton("Retour");

        // Set layout
        testZone.setLayout(new BorderLayout());

        // Add components to content pane
        JPanel centerPanel = new JPanel(new FlowLayout());
        centerPanel.add(theoremLabel);
        testZone.add(centerPanel, BorderLayout.CENTER);

        JPanel southPanel = new JPanel(new FlowLayout());
        southPanel.add(revealButton);
        southPanel.add(nextButton);
        southPanel.add(exitButton);
        testZone.add(southPanel, BorderLayout.SOUTH);

        // Add event listeners
        revealButton.addActionListener(e->{buttonClicked(e);});
        nextButton.addActionListener(e->{buttonClicked(e);});
        exitButton.addActionListener(e->{buttonClicked(e);});
		
		// Ajouter les deux zones à la vue en les séparant
        JSplitPane layout = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, menuLateral,
        		testZone);
        layout.setSize(this.getSize());
        
        // Change les elements de la vue
        this.removeAll();
        this.add(layout, BorderLayout.CENTER);
        this.revalidate();
        this.repaint();
		
	}
	
	private void buttonClicked(ActionEvent e) {
		if (e.getSource() == revealButton) {
            // Show the answer
            String theorem = theoremList.get(currentTheoremIndex).getTitre();
            String answer =  theoremList.get(currentTheoremIndex).getCorps();
            theoremLabel.setText(answer);
        } else if (e.getSource() == nextButton) {
            // Go to the next theorem
            currentTheoremIndex = (currentTheoremIndex + 1) % theoremList.size();
            theoremLabel.setText(theoremList.get(currentTheoremIndex).getTitre());
        } else if (e.getSource() == exitButton) {
            // Ajouter les deux zones à la vue en les séparant
            JSplitPane layout = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, menuLateral,
            		openButton);
            layout.setSize(this.getSize());

            this.removeAll();
            this.add(layout, BorderLayout.CENTER);
            this.revalidate();
            this.repaint();
        }
	}
        
    

}
