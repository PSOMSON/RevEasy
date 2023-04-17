package Model;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Classe représentant un texte avec des trous aléatoires.
 */
public class TexteAvecTrous {
    private final String texteInitial; 
    private final int nombreDeTrous = 5; // Le nombre de trous par phrase.


    /**
     * Constructeur prenant en paramètre le texte initial.
     * @param texteInitial le texte initial.
     */
    public TexteAvecTrous(String texteInitial) {
        this.texteInitial = texteInitial;

    }

    /**
     * Méthode permettant de découper un texte en phrases.
     * @param text le texte à découper en phrases.
     * @return une liste de phrases.
     */
    private List<String> decouperEnPhrases(String text){
        List<String> phrases = new ArrayList<>();
        String[] phrasesTableau = text.split("\\.");

        for (String phrase : phrasesTableau) {
            phrases.add(phrase.trim());
        }

        return phrases;
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
     * @return le texte avec des trous aléatoires.
     */
    public String genererTexteAvecTrous() {
        String texteAvecTrous = "";

        List<String> phrases = decouperEnPhrases(texteInitial);

        for (String phrase : phrases) {
            List<String> mots = decouperEnMots(phrase);
            int nombreDeTrousDone = 0; // Le nombre de trous déjà ajoutés par phrase.

            for (String mot : mots) {
                Random random = new Random();
                double nombreAleatoire = random.nextDouble();

                if (nombreAleatoire < 0.25   && nombreDeTrousDone < nombreDeTrous) {
                    texteAvecTrous += "___ ";
                    nombreDeTrousDone++;
                }
                else {
                    texteAvecTrous += mot + " ";
                }
            }

            texteAvecTrous += ". ";
        }

        return texteAvecTrous;
    }
    /**
     * Méthode permettant de vérifier si l'utilisateur a bien entré les mots correctement.
     * @param texteAvecTrous le texte avec les trous aléatoires.
     * @return true si l'utilisateur a bien entré les mots correctement, false sinon.
     */
    public boolean verifierTexte(String texteAvecTrous) {
        List<String> phrasesAvecTrous = decouperEnPhrases(texteAvecTrous);
        List<String> phrasesOriginales = decouperEnPhrases(texteInitial);

        for (int i = 0; i < phrasesAvecTrous.size(); i++) {
            String phraseAvecTrous = phrasesAvecTrous.get(i);
            String phraseOriginale = phrasesOriginales.get(i);

            List<String> motsAvecTrous = decouperEnMots(phraseAvecTrous);
            List<String> motsOriginaux = decouperEnMots(phraseOriginale);

            for (int j = 0; j < motsAvecTrous.size(); j++) {
                String motAvecTrous = motsAvecTrous.get(j);
                String motOriginal = motsOriginaux.get(j);

                if (!motAvecTrous.equals(motOriginal) && !motAvecTrous.equals("___")) {
                    return false;
                }
            }
        }
        return true;
    }

    }