package gui.vues;

import java.awt.BorderLayout;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import Model.Fiche;
import Model.FicheSaver;

/**
 * Classe pour afficher la liste des fiches.
 */
public class Liste extends Vue {

    /** Arbre de l'arborescence. */
    private JTree tree = new JTree();

    /**
     * Initialiser la vue.
     * @param c Classe qui utilise la vue
     */
    public Liste(Consultation c) {
        super(new BorderLayout());

        // Créer la zone d'affichage des fiches
        JScrollPane scrollPane = new JScrollPane(tree);
        majTree();
        this.add(scrollPane, BorderLayout.CENTER);

        // Créer un bouton de mise a jour de l'arborescence
        JButton majButton = new JButton("Mettre a jour");
        majButton.addActionListener(e -> {
            majTree();
        });
        this.add(majButton, BorderLayout.SOUTH);

        // Ajouter un listener pour ouvrir une fiche au double clic
        tree.addTreeSelectionListener(new TreeSelectionListener() {
            public void valueChanged(TreeSelectionEvent e) {
                DefaultMutableTreeNode node =
                            (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
                if (node == null) {
                    return;
                }
                if (node.isLeaf()) {
                    String ficheName = node.toString();
                    String homePath = System.getProperty("user.home");
                    File ficheFile = new File(homePath + File.separator + "Reveasy"
                                                       + File.separator + "Fiches"
                                                       + File.separator + ficheName);
                    String fichePath = ficheFile.getAbsolutePath();
                    Fiche fiche = new FicheSaver().ouvrir(fichePath);
                    c.ouvrir(fiche);
                }
            }
        });
    }

    private void majTree() {

        DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode("Reveasy");
        String homePath = System.getProperty("user.home");
        File rootFolder = new File(homePath + File.separator + "Reveasy"
                                            + File.separator + "Fiches");
        createTreeNodes(rootFolder, rootNode);
        DefaultTreeModel treeModel = new DefaultTreeModel(rootNode);
        tree.setModel(treeModel);

        tree.setRootVisible(false);
        tree.setShowsRootHandles(true);
        tree.expandPath(tree.getPathForRow(0));

    }

    private static void createTreeNodes(File file, DefaultMutableTreeNode parentNode) {
        DefaultMutableTreeNode fileNode = new DefaultMutableTreeNode(file.getName());
        parentNode.add(fileNode);

        if (file.isDirectory()) {
            for (File child : file.listFiles()) {
                createTreeNodes(child, fileNode);
            }
        }
    }

}
