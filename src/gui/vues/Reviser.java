package gui.vues;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JSplitPane;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;

import Model.Enonce;
import Model.Fiche;
import Model.TexteAvecTrous;
import gui.AfficheurFiche;
import gui.IHM;
import gui.PopUpOpenFiche;

public class Reviser extends Vue implements AfficheurFiche {
	
	private MenuLateral menuLateral;
	private JButton openButton;
	
	private JTextPane enonceLabel;
    private JButton revealButton;
    private JButton nextButton;
    private JButton exitButton;
    private List<Enonce> enonceList;
    private int currentEnonceIndex;
    private JPanel zoneOuverture;
	private int theoremeOUdefinition;
	
    /**
     * Constructeur.
     * @param ihm Interface de l'application
     */
    public Reviser(IHM ihm) {
        super(new BorderLayout());
        
        // Créer la zone d'ouverture
        zoneOuverture = new JPanel(new GridBagLayout());
        openButton = new JButton("Ouvrir une fiche à reviser");
        openButton.addActionListener(e -> {
            PopUpOpenFiche popUp = new PopUpOpenFiche(this);
            popUp.setVisible(true);
        });
        zoneOuverture.add(openButton);


        // Créer la zone de recherche
        menuLateral = new MenuLateral(ihm);

        // Ajouter les deux zones à la vue en les séparant
        JSplitPane layout = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, menuLateral,
        		zoneOuverture);
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

        JButton questionAnswer = new JButton("Q/A");
        questionAnswer.setBackground(Color.WHITE);
    	icon = new ImageIcon("assets/texteatrou.png"); // redimensionnement de l'image
		img = icon.getImage();
		newimg = img.getScaledInstance(150, 100, java.awt.Image.SCALE_SMOOTH);
		icon = new ImageIcon(newimg);
    	questionAnswer.setIcon(icon);
    	questionAnswer.setText("Q/A");
    	questionAnswer.setHorizontalTextPosition(JButton.CENTER);
    	questionAnswer.setVerticalTextPosition(JButton.BOTTOM);

        // Créer le panel de l'onglet révision
        JPanel revision = new JPanel(new BorderLayout());

    	// Créer le panel pour les boutons de test
    	JPanel choixTest = new JPanel(new GridBagLayout());

    	// Ajouter les boutons de test au panel
    	choixTest.add(testFlashCards);
    	choixTest.add(testTexteAtrou);
        choixTest.add(questionAnswer);

        // Ajouter ses composants à l'onglet révision
        revision.add(new JLabel("Fiche selectionée : " + f.getTitre()), BorderLayout.NORTH);
        revision.add(choixTest, BorderLayout.CENTER);
        
        testFlashCards.addActionListener(e->{startTestFlashCards(f);});
        ReviserText reviserQA= new ReviserText(f.getQuestions());
        questionAnswer.addActionListener(e->{startQuestionAnswer( f, reviserQA);});
        
        TexteAvecTrous TexteAvecTrous = new TexteAvecTrous(f.getTexteATrouver());
        ReviserText reviserText= new ReviserText(f.getTexteATrouver(), TexteAvecTrous.genererTexteAvecTrous()) ;
        testTexteAtrou.addActionListener(e->{startTestAtROU( f, reviserText);});
        
        // Ajout du bouton "retour au choix de fichier a reviser"
        exitButton = new JButton("Retour");
        revision.add(exitButton, BorderLayout.SOUTH);
        exitButton.addActionListener(e->{buttonClicked(e);});
       
        
        // Ajouter les deux zones à la vue en les séparant
        JSplitPane layout = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, menuLateral,
        		revision);
        layout.setSize(this.getSize());
        
        // Change les elements de la vue
        this.removeAll();
        this.add(layout, BorderLayout.CENTER);
        this.revalidate();
        this.repaint();
    }

	private void startTestFlashCards(Fiche fiche) {
		String[] options = {"Theoremes", "Definitions",};
        theoremeOUdefinition = JOptionPane.showOptionDialog(
            this,
            "Que souhaitez-vous réviser ?",
            "Que souhaitez-vous réviser ?",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            options,
            options[0]
        );
        
		// Créer la zone du test
		JPanel testZone = new JPanel(new BorderLayout());
		
	    enonceList = theoremeOUdefinition == 0? fiche.getTheoremes() : fiche.getDefinitions();
	    if (enonceList.size() == 0) {
	    	JOptionPane.showMessageDialog(null, "La liste des " + (theoremeOUdefinition == 0? "Theoremes":"Definitions") + " est vide.", "Alert", JOptionPane.INFORMATION_MESSAGE);
	    	return;
	    }
        currentEnonceIndex = 0;

        // Create UI components
        enonceLabel = new JTextPane();
        enonceLabel.setEditorKit(new HTMLEditorKit());
        enonceLabel.setText("<b>"+(theoremeOUdefinition == 0? "Theoreme":"Definition")+" : </b><br>" + enonceList.get(currentEnonceIndex).getTitre());
        revealButton = new JButton("Révéler la réponse");
        nextButton = new JButton((theoremeOUdefinition == 0? "Theoreme":"Definition")+" suivant");
        exitButton = new JButton("Retour");
        
        // Changer bouton en Recommencer si fin de theroemes
        if (currentEnonceIndex == enonceList.size()-1)
    		nextButton.setText("Recommencer");
        
        // Set layout
        testZone.setLayout(new BorderLayout());

        // Add components to content pane
        JPanel centerPanel = new JPanel(new FlowLayout());
        centerPanel.add(enonceLabel);
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
    private void startTestAtROU(Fiche fiche, ReviserText reviserText) {
	

		if (!reviserText.estPossible()) {
            JOptionPane.showMessageDialog(null, "Il n'est pas possible de réviser avec " +
            "un texte à trous pour cette fiche.", "Alert", JOptionPane.INFORMATION_MESSAGE);
	    	return;
        }
		
		// Ajouter les deux zones à la vue en les séparant
		JPanel revision = reviserText.afficher();
        JSplitPane layout = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, menuLateral,
        revision);
        layout.setSize(this.getSize());
        
        // Ajout du bouton "retour au choix de fichier a reviser"
        exitButton = new JButton("Retour");
        revision.add(exitButton, BorderLayout.NORTH);
        exitButton.addActionListener(e->{buttonClicked(e);});
        
        // Change les elements de la vue
        this.removeAll();
        this.add(layout, BorderLayout.CENTER);
        this.revalidate();
        this.repaint();
		
	}
    

	
	private void buttonClicked(ActionEvent e) {
		if (e.getSource() == revealButton) {
            // Show the answer
            String answer =  enonceList.get(currentEnonceIndex).getCorps();
            enonceLabel.setText(answer);
        } else if (e.getSource() == nextButton) {
            // Go to the next theorem
        	// Changer bouton en Recommencer si fin de theroemes
        	if (currentEnonceIndex == enonceList.size()-1)
        		nextButton.setText("Recommencer");
        	else
        		nextButton.setText((theoremeOUdefinition == 0? "Théorème suivant":"Definition suivante"));
            currentEnonceIndex = (currentEnonceIndex + 1) % enonceList.size();
            enonceLabel.setText("<b>"+(theoremeOUdefinition == 0? "Théorème":"Definition")+" : </b><br>" + enonceList.get(currentEnonceIndex).getTitre());
        } else if (e.getSource() == exitButton) {
            // Ajouter les deux zones à la vue en les séparant
            JSplitPane layout = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, menuLateral,
            		zoneOuverture);
            layout.setSize(this.getSize());

            this.removeAll();
            this.add(layout, BorderLayout.CENTER);
            this.revalidate();
            this.repaint();
        }
	}
    private void startQuestionAnswer(Fiche fiche,ReviserText reviserText) {
		// Ajouter les deux zones à la vue en les séparant
        JSplitPane layout = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, menuLateral,
        reviserText.afficherForQA());
        layout.setSize(this.getSize());


        
        // Change les elements de la vue
        this.removeAll();
        this.add(layout, BorderLayout.CENTER);
        this.revalidate();
        this.repaint();
		
	}

}
