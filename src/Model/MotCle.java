package Model;

// classe qui génère des mots clés à partir d'une fiche

public class MotCle {

    private String motCle;
    private int nbOccurence;

    public MotCle(String motCle, int nbOccurence) {
        this.motCle = motCle;
        this.nbOccurence = nbOccurence;
    }

    public int getNbOccurence(Fiche fiche) {
        // on parcourt le contenu de la fiche et on compte le nombre de fois où le mot
        // clé apparait
        int nbOccurence = 0;
        String contenu = fiche.getContenu();
        int index = contenu.indexOf(motCle);
        while (index != -1) {
            nbOccurence++;
            index = contenu.indexOf(motCle, index + 1);
        }
        return nbOccurence;
    }

    // choisir le mot clé renvoyé en fonction du nombre d'occurence
    public String getMotCle(Fiche fiche) {
        if (getNbOccurence(fiche) > 5) { // si le mot clé apparait plus de 5 fois dans la fiche
            return motCle;
        } else {
            return null;
        }
    }

    public void incrementerNbOccurence() {
        this.nbOccurence++;
    }

    @Override
    public String toString(Fiche fiche) {
        // choisir le mot clé renvoyé par la méthode getMotCle() en fonction du nombre
        // d'occurence
        motCle = getMotCle(fiche);
        return "MotCle{" + "motCle=" + motCle + ", nbOccurence=" + nbOccurence + '}';
    }

}
