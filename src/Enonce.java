public class Enonce {
    
    /**
     * Type represente le format de l'enoncé
     */
    public enum Type {
        DEFINITION,
        THEOREME
    }

    private Type type; 
    private String titre;
    private String corps;

    /**
     * Constructeur d'un enoncé.
     * @param type enum representant le type d'enoncé a créer
     * @param titre Titre de l'énoncé
     * @param corps Contenu de l'énoncé
     */
    public Enonce(Type type, String titre, String corps) {
        this.type = type;
        this.titre = titre;
        this.corps = corps; 
    }

    /**
     * Obtenir le type d'enoncé
     * @return type
     */
    public Type getType() {
        return type;
    }

    /**
     * Obtenir le titre de l'enoncé
     * @return titre
     */
    public String getTitre() {
        return titre;
    }

    /**
     * Obtenir le corps de l'énoncé
     * @return corps
     */
    public String getCorps() {
        return corps;
    }

}
