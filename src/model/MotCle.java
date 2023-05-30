package model;


import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class MotCle implements Iterable {
	
	/** Liste de mots clés*/
	public static  Map<String, List<String>>  MotsCles;
	
	
	/** Constructeur */
	public MotCle () {
		MotsCles = new HashMap<>();
	}
	
	/** Méthode pour récupérer les titres des énoncés de type définition
	 * @return ListTitres
	 */
	public List<String> getTitreDefinition () {
		List<String> ListeTitres = new ArrayList<String>();
		List<Enonce> ListeEnonces = getEnonces(Enonce.Type.DEFINITION);
		for (Enonce element : ListeEnonces) {
			String titre = element.getTitre();
			ListeTitres.add(titre);
		}
		return ListeTitres;
	}
	
	
	/** Ajouter des mots clés à partir des titres des fiches
	 * les mots clés seront le titre de la fiche et de la définition
	 * @param fiche
	 */
	public void ajouterMotsCles (Fiche fiche) {
		List<String> ListeTitres = new ArrayList<String>();
		ListeTitres = getTitreDefinition();
		MotsCles.put(fiche.getTitre(),ListeTitres);
	}
	
	/** Afficher les mots clés relatifs à une fiche
	 * @return Motscles
	 */
	public Map<String, String> getMotsCles(){
		return MotsCles;
	}
}



