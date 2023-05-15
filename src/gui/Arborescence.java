package gui;

import java.awt.BorderLayout;
import java.io.File;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import Model.Fiche;
import Model.FicheSaver;

/**
 * Classe qui implémente un composant pour afficher l'arborescence des fiches.
 */
public class Arborescence extends JPanel {

    /** Arbre de l'arborescence. */
    private JTree tree = new JTree();

    /**
     * Constructeur.
     */
    public Arborescence() {
        super();

        // Créer la zone d'affichage des fiches
        JScrollPane scrollPane = new JScrollPane(tree);

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

        this.add(scrollPane, BorderLayout.CENTER);
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

    /**
     * Retourne la fiche sélectionnée dans l'arborescence.
     * @return Fiche sélectionnée
     */
    public Fiche getSelectedFiche() {

        DefaultMutableTreeNode node =
                        (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
        if (node == null) {
             return null;
        }
        if (node.isLeaf()) {
            String ficheName = node.toString();
            String homePath = System.getProperty("user.home");
            File ficheFile = new File(homePath + File.separator + "Reveasy"
                                               + File.separator + "Fiches"
                                               + File.separator + ficheName);
            String fichePath = ficheFile.getAbsolutePath();
            new FicheSaver();
            return FicheSaver.ouvrir(fichePath);
        }
        return null;
    }

    /**
     * Retourne l'arbre de l'arborescence.
     * @return Arbre de l'arborescence
     */
    public JTree getTree() {
        return tree;
    }
}
