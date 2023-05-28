package gui.vues;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JSplitPane;
import javax.swing.JPanel;
import javax.swing.JOptionPane;

import Model.Enonce;
import Model.Fiche;
import Model.TexteAvecTrous;
import gui.AfficheurFiche;
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
    	panelTest.setBackground(Color.WHITE);
    	
    	// Créer les boutons de test
    	JButton testFlashCards = new JButton();
    	testFlashCards.setBackground(Color.WHITE);
    	ImageIcon icon = new ImageIcon("assets/flashcards.png"); // redimensionnement de l'image
		Image img = icon.getImage();
		Image newimg = img.getScaledInstance(150, 100, java.awt.Image.SCALE_SMOOTH);
		icon = new ImageIcon(newimg);
    	testFlashCards.setIcon(icon);
    	testFlashCards.setText("Flash Cards");
    	testFlashCards.setHorizontalTextPosition(JButton.CENTER);
    	testFlashCards.setVerticalTextPosition(JButton.BOTTOM);

    	JButton testTexteAtrou = new JButton();
    	testTexteAtrou.setBackground(Color.WHITE);
    	icon = new ImageIcon("assets/texteatrou.png"); // redimensionnement de l'image
		img = icon.getImage();
		newimg = img.getScaledInstance(150, 100, java.awt.Image.SCALE_SMOOTH);
		icon = new ImageIcon(newimg);
    	testTexteAtrou.setIcon(icon);
    	testTexteAtrou.setText("Texte A trou");
    	testTexteAtrou.setHorizontalTextPosition(JButton.CENTER);
    	testTexteAtrou.setVerticalTextPosition(JButton.BOTTOM);

    	// Créer le panel pour les boutons de test
    	JPanel choixTest = new JPanel(new FlowLayout());
    	panelTest.add(choixTest, BorderLayout.CENTER);

    	// Ajouter les boutons de test au panel
    	choixTest.add(testFlashCards);
    	choixTest.add(testTexteAtrou);
        
        testFlashCards.addActionListener(e->{startTestFlashCards(f);});
        
        TexteAvecTrous TexteAvecTrous = new TexteAvecTrous(f.getTexteATrouver());
        ReviserText reviserText= new ReviserText(f.getTexteATrouver(), TexteAvecTrous.genererTexteAvecTrous()) ;
        testTexteAtrou.addActionListener(e->{startTestAtROU( f, reviserText);});
        
        // Ajout du bouton "retour au choix de fichier a reviser"
        exitButton = new JButton("Retour");
        panelTest.add(exitButton, BorderLayout.SOUTH);
        exitButton.addActionListener(e->{buttonClicked(e);});
       
        
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
	    if (theoremList.size() == 0) {
	    	JOptionPane.showMessageDialog(null, "La liste des theoremes est vide.", "Alert", JOptionPane.INFORMATION_MESSAGE);
	    	return;
	    }
        currentTheoremIndex = 0;


        // Create UI components
        theoremLabel = new JLabel(theoremList.get(currentTheoremIndex).getTitre());
        revealButton = new JButton("Révéler la réponse");
        nextButton = new JButton("Théorème suivant");
        exitButton = new JButton("Retour");
        
        // Changer bouton en Recommencer si fin de theroemes
        if (currentTheoremIndex == theoremList.size()-1)
    		nextButton.setText("Recommencer");
        
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
    private void startTestAtROU(Fiche fiche,ReviserText reviserText) {
	

		
		// Ajouter les deux zones à la vue en les séparant
        JSplitPane layout = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, menuLateral,
        reviserText.afficher());
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
            String answer =  theoremList.get(currentTheoremIndex).getCorps();
            theoremLabel.setText(answer);
        } else if (e.getSource() == nextButton) {
            // Go to the next theorem
        	// Changer bouton en Recommencer si fin de theroemes
        	if (currentTheoremIndex == theoremList.size()-1)
        		nextButton.setText("Recommencer");
        	else
        		nextButton.setText("Théorème suivant");
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
