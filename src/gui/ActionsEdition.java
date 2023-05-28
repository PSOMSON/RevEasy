package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Action;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextPane;
import javax.swing.SpinnerNumberModel;
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

        // Enfin, ajout du bandeau
        this.add(bandeau);
        
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
     */
    private void peuplerEdition(){
        // Personnalisation de la disposition des boutons
        this.sousmenu.setLayout(new GridLayout(0, 3));

        // Séparation en trois parties
        JPanel gauche = new JPanel();
        JPanel centre = new JPanel();
        JPanel droite = new JPanel();

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

        // Ajout d'un spinner de taille de police pour le texte sélectionné
        SpinnerNumberModel size = new SpinnerNumberModel(12, 1, 100, 1);
        size.addChangeListener(new ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent e) {
                textbox.requestFocusInWindow();
                Document doc = textbox.getDocument();
                int start = textbox.getSelectionStart();
                int end = textbox.getSelectionEnd();
                if (end > start) {
                    MutableAttributeSet attr = new SimpleAttributeSet();
                    StyleConstants.setFontSize(attr, size.getNumber().intValue());
                    ((StyledDocument) doc).setCharacterAttributes(start, end - start, attr, false);
                }
            }
        });
        JSpinner taille = new JSpinner(size);
        JPanel taillePanel = new JPanel();
        JLabel nom = new JLabel("Taille : ");
        taillePanel.add(nom);
        taillePanel.add(taille);
        centre.add(taillePanel);
        
        // Ajout d'un spinner pour la police d'écriture
        font.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textbox.requestFocusInWindow();
                Document doc = textbox.getDocument();
                int start = textbox.getSelectionStart();
                int end = textbox.getSelectionEnd();
                if (end > start) {
                    MutableAttributeSet attr = new SimpleAttributeSet();
                    StyleConstants.setFontFamily(attr, font.getSelectedItem().toString());
                    ((StyledDocument) doc).setCharacterAttributes(start, end - start, attr, false);
                }
            }
        });
        centre.add(font);



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
