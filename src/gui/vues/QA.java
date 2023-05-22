package gui.vues;

import java.util.LinkedList;
import java.util.List;

import Model.Fiche;
import Model.Question;

public class QA {
   
	Fiche fiche;
    private List<String> texte;
    
	public QA(Fiche fiche) {
		super();
		this.fiche = fiche;
	}
   
	public List<Question> getQuestions() {
        List<Question> questions = new LinkedList<>();
        String balise = "@Question";
        String rep = "@Reponse";

        int taille = texte.size();
        int i = 0;
        while (i < taille) {
            if (texte.get(i).contains(balise)) {
                // Titre de la question
                String titreq = texte.get(i).substring(balise.length() + 1).strip();

                List<String> repsV = new LinkedList<>();
                List<String> repsF = new LinkedList<>();

                // Reponses
                while (i + 1 < taille && texte.get(i + 1).contains(rep)) {
                    i++;
                    if (texte.get(i).charAt(rep.length() + 1) == 'V') {
                        repsV.add(texte.get(i).substring(rep.length() + 2).strip());
                    } else if (texte.get(i).charAt(rep.length() + 1) == 'F') {
                        repsF.add(texte.get(i).substring(rep.length() + 2).strip());
                    } else {
                        throw new Error("Mauvaise balise");
                        // TODO: Creer une exception personalisée
                    }
                }

                questions.add(new Question(titreq, repsV.toArray(new String[repsV.size()]),
                        repsF.toArray(new String[repsF.size()])));
            }
            i++;
        }
        return questions;
    }
}
