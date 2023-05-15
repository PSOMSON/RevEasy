package Model;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Classe représentant un texte avec des trous aléatoires.
 */
public class TexteAvecTrous {
    private final List<String> phrasesOriginales;
    private final int nombreDeTrous = 5; // Le nombre de trous par phrase.

    /**
     * Constructeur prenant en paramètre la liste de phrases initiales.
     * @param phrasesOriginales la liste de phrases initiales.
     */
    public TexteAvecTrous(List<String> phrasesOriginales) {
        this.phrasesOriginales = phrasesOriginales;
    }

    /**
     * Méthode permettant de découper une phrase en mots.
     * @param phrase la phrase à découper en mots.
     * @return une liste de mots.
     */
    private List<String> decouperEnMots(String phrase) {
        List<String> mots = new ArrayList<>();
        String[] motsTableau = phrase.split(" ");

        for (String mot : motsTableau) {
            mots.add(mot);
        }

        return mots;
    }

    /**
     * Méthode permettant de générer un texte avec des trous aléatoires.
     * @return une liste de phrases avec des trous aléatoires.
     */
    public List<String> genererTexteAvecTrous() {
        List<String> phrasesAvecTrous = new ArrayList<>();

        for (String phraseOriginale : phrasesOriginales) {
            List<String> motsOriginaux = decouperEnMots(phraseOriginale);
            List<String> motsAvecTrous = new ArrayList<>();
            int nombreDeTrousDone = 0; // Le nombre de trous déjà ajoutés par phrase.

            for (String motOriginal : motsOriginaux) {
                Random random = new Random();
                double nombreAleatoire = random.nextDouble();

                if (nombreAleatoire < 0.25 && nombreDeTrousDone < nombreDeTrous) {
                    motsAvecTrous.add(" ...");
                    nombreDeTrousDone++;
                } else {
                    motsAvecTrous.add(motOriginal);
                }
            }

            phrasesAvecTrous.add(String.join(" ", motsAvecTrous) + ".");
        }

        return phrasesAvecTrous;
    }

}
