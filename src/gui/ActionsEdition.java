package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Action;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JTextPane;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeListener;
import javax.swing.text.Document;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.swing.text.StyledEditorKit;

/** Le menu supérieur de la fenêtre d'édition, regroupe tous les boutons
 * comme "Gras" ou "Italique" etc...
 * Plus lisible si dans sa classe séparée.
 */

public class ActionsEdition extends JPanel{

    /** L'éditeur associé au menu */
    JTextPane textbox;

    /** Le bandeau supérieur, composé d'un menu en haut et des sous-menus en bas
     * (un peu comme dans Word avec les menus "Fichier", "Edition", "Affichage" etc...)
     */
    JPanel bandeau;

    /** Le menu (Fichiers, Edition, Reveasy) qui sépare les actions */
    JPanel menu;

    /** Le sous-menu affiché */
    JPanel sousmenu;

    /** Un sélecteur de couleur qui se doit de garder en mémoir les couleurs utilisées */
    JColorChooser colorChooser;

    /** Encore un sélecteur de couleur, mais pour le surlignage */
    JColorChooser backgroundChooser;

    /** Créer un nouveau menu d'édition */
    public ActionsEdition(JTextPane textbox) {

        // On lie la zone d'édition à ce menu pour pouvoir la modifier
        this.textbox = textbox;

        // On crée le bandeau
        this.bandeau = new JPanel(new BorderLayout());
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        // Ajout du menu d'édition
        this.menu = menuSelection();
        this.bandeau.add(menu, BorderLayout.NORTH);

        // Ajout du sous menu (initialement d'édition)
        this.sousmenu = new JPanel(new FlowLayout(FlowLayout.LEFT));
        this.bandeau.add(sousmenu, BorderLayout.SOUTH);
        peuplerEdition();

        // Séparation des deux menus pour une meilleure lisibilité
        this.bandeau.add(new JSeparator(SwingConstants.HORIZONTAL), BorderLayout.CENTER);

        // Enfin, ajout du bandeau
        this.add(bandeau);

        // Création d'un sélecteur de couleur pour la police
        colorChooser = new JColorChooser(textbox.getForeground());
        colorChooser.getSelectionModel().addChangeListener(e -> {
            textbox.requestFocusInWindow();
            // Récupérer la couleur sélectionnée
            Color color = colorChooser.getColor();
            // Ne pas modifier TOUS les attributs de style du texte
            MutableAttributeSet attr = new SimpleAttributeSet();
            // Modifier la couleur du texte
            StyleConstants.setForeground(attr, color);
            // Modifier le texte sélectionné
            textbox.setCharacterAttributes(attr, false);
        });

        // Création d'un sélecteur de couleur pour le surlignage
        backgroundChooser = new JColorChooser(textbox.getBackground());
        backgroundChooser.getSelectionModel().addChangeListener(e -> {
            textbox.requestFocusInWindow();
            // Récupérer la couleur sélectionnée
            Color color = backgroundChooser.getColor();
            // Ne pas modifier TOUS les attributs de style du texte
            MutableAttributeSet attr = new SimpleAttributeSet();
            // Modifier la couleur du texte
            StyleConstants.setBackground(attr, color);
            // Modifier le texte sélectionné
            textbox.setCharacterAttributes(attr, false);
        });
        
    }

    /** Créer le menu principal avec les boutons "Fichiers", "Edition", "RevEasy" 
     * @return le menu principal
    */
    private JPanel menuSelection() {
        // Création du menu
        JPanel menu = new JPanel(new FlowLayout(FlowLayout.LEFT));
        menu.setLayout(new BoxLayout(menu, BoxLayout.X_AXIS));

        // Le bouton fichier ouvre le sous-menu fichier
        JButton fichiers = new JButton("Fichiers");
        fichiers.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Changement de menu
                sousmenu.removeAll();
                peuplerFichiers();
                sousmenu.revalidate();
                sousmenu.repaint();
            }
        });

        // Le bouton édition ouvre le sous-menu édition
        JButton edition = new JButton("Edition");
        edition.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Changement de menu
                sousmenu.removeAll();
                peuplerEdition();
                sousmenu.revalidate();
                sousmenu.repaint();
            }
        });

        // Le bouton balises ouvre le sous-menu balises
        JButton balises = new JButton("RevEasy");
        balises.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Changement de menu
                sousmenu.removeAll();
                peuplerBalises();
                sousmenu.revalidate();
                sousmenu.repaint();
            }
        });

        // Ajout des boutons au menu
        menu.add(fichiers);
        menu.add(edition);
        menu.add(balises);
        return menu;
    }

    /** Peuple le sous-menu fichier avec toutes les commandes concernées. 
     * On parle ici des commandes de sauvegarde, d'ouverture, etc...
    */
    private void peuplerFichiers(){}

    /** Peuple le sous-menu édition avec toutes les commandes concernées.
     * On parle ici des commandes de mise en forme du texte. (Gras, italique, etc...)
     * Attention : la méthode est TRÈS LONGUE !
     */
    private void peuplerEdition(){
        // Personnalisation de la disposition des boutons
        this.sousmenu.setLayout(new GridLayout(0, 3));

        // Séparation en trois parties
        JPanel gauche = new JPanel();
        JPanel centre = new JPanel();
        JPanel droite = new JPanel();

        // A gauche

        // Gras
        Action boldAction = new StyledEditorKit.BoldAction();
        boldAction.putValue(Action.NAME, "Gras");
        JButton bold = new JButton(boldAction);
        gauche.add(bold);

        // Italique
        Action italicAction = new StyledEditorKit.ItalicAction();
        italicAction.putValue(Action.NAME, "Italique");
        JButton italic = new JButton(italicAction);
        gauche.add(italic);

        // Souligné
        Action underlineAction = new StyledEditorKit.UnderlineAction();
        underlineAction.putValue(Action.NAME, "Souligné");
        JButton underline = new JButton(underlineAction);
        gauche.add(underline);

        // Déclaration du controlleur de police 
        String[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
        JComboBox<String> font = new JComboBox<>(fonts);

        // Ajout d'un bouton pour changer la couleur de la police
        JButton color = new JButton("Couleur");
        color.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Choix de la couleur");
                frame.add(colorChooser);
                frame.pack();
                frame.setAlwaysOnTop (true);
                frame.setVisible(true);
            }
        });
        gauche.add(color);

        // Et un autre pour changer la couleur de fond
        JButton background = new JButton("Surlignage");
        background.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Choix de la couleur");
                frame.add(backgroundChooser);
                frame.pack();
                frame.setAlwaysOnTop (true);
                frame.setVisible(true);
            }
        });
        gauche.add(background);

        // Au centre

    // Ajout d'un spinner de taille de police pour le texte sélectionné
        SpinnerNumberModel size = new SpinnerNumberModel(12, 1, 100, 1);
        size.addChangeListener(new ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent e) {
                textbox.requestFocusInWindow();
                // Petite ruse pour pouvoir modifier les attributs de la police de la sélection
                Document doc = textbox.getDocument();
                // On récupère la sélection
                int start = textbox.getSelectionStart();
                int end = textbox.getSelectionEnd();
                if (end > start) {
                    // On garde les mêmes attributs qu'avant
                    MutableAttributeSet attr = new SimpleAttributeSet();
                    // On change juste la taille
                    StyleConstants.setFontSize(attr, size.getNumber().intValue());
                    ((StyledDocument) doc).setCharacterAttributes(start, end - start, attr, false);
                }
            }
        });
        // Ajout d'un label pour le spinner
        JSpinner taille = new JSpinner(size);
        JPanel taillePanel = new JPanel();
        JLabel nom = new JLabel("Taille : ");
        // Ajout du label et du spinner dans un panel
        taillePanel.add(nom);
        taillePanel.add(taille);
        // Ajout de la somme des deux dans le centre
        centre.add(taillePanel);

        
    // Ajout d'un spinner pour la police d'écriture
        font.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textbox.requestFocusInWindow();
                // Petite ruse pour pouvoir modifier les attributs de la police de la sélection
                Document doc = textbox.getDocument();
                // On récupère la sélection
                int start = textbox.getSelectionStart();
                int end = textbox.getSelectionEnd();
                if (end > start) {
                    // On garde les mêmes attributs qu'avant
                    MutableAttributeSet attr = new SimpleAttributeSet();
                    // On change juste la police
                    StyleConstants.setFontFamily(attr, font.getSelectedItem().toString());
                    ((StyledDocument) doc).setCharacterAttributes(start, end - start, attr, false);
                }
            }
        });
        // Ajout au centre
        centre.add(font);


        // A droite
        
        // On y met les alignements de texte (gauche, centre, droite, justifié)
        JButton alignGauche = new JButton(new StyledEditorKit.AlignmentAction("Gauche", StyleConstants.ALIGN_LEFT));
        JButton alignCentre = new JButton(new StyledEditorKit.AlignmentAction("Centre", StyleConstants.ALIGN_CENTER));
        JButton alignDroite = new JButton(new StyledEditorKit.AlignmentAction("Droite", StyleConstants.ALIGN_RIGHT));
        JButton alignJustifie = new JButton(new StyledEditorKit.AlignmentAction("Justifié", StyleConstants.ALIGN_JUSTIFIED));
        droite.add(alignGauche);
        droite.add(alignCentre);
        droite.add(alignDroite);
        droite.add(alignJustifie);



        // Ajout des trois parties
        this.sousmenu.add(gauche);
        this.sousmenu.add(centre);
        this.sousmenu.add(droite);
    }


    /** Peuple le sous-menu balises avec toutes les commandes concernées. 
     * On parle ici des balises théorèmes, définitions, questions, etc...
    */
    private void peuplerBalises(){

        // Ajout des actions
        JButton definition = new JButton("@Definition");
        JButton theoreme = new JButton("@Theoreme");
        JButton qcm = new JButton("@QCM");

        // Redéfinission de la disposition
        this.sousmenu.setLayout(new FlowLayout(FlowLayout.CENTER));
        
        // @Définition
        definition.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textbox.requestFocusInWindow();
                textbox.replaceSelection("\n\n@Definition mot\nla definition du mot sur cette ligne");
            }
        });

        // @Theoreme
        theoreme.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textbox.requestFocusInWindow();
                textbox.replaceSelection("\n\n@Theoreme nom du théorème\nénoncé du théorème sur cette ligne");
            }
        });

        // @Question
        qcm.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textbox.requestFocusInWindow();
                textbox.replaceSelection("\n\n@Question question ?\n@ReponseV Réponse vraie\n@ReponseF Réponse fausse\n@ReponseF Réponse fausse");
            }
        });

        // Ajout des boutons au menu
        this.sousmenu.add(definition);
        this.sousmenu.add(theoreme);
        this.sousmenu.add(qcm);
    }
}
