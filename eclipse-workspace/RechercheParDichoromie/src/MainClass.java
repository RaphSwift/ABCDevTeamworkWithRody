
public class MainClass {
	public static void main(String[] args) {
		String chaineRecherche="sidonie";
		int iterateurPlageMin;
		int iterateurPlageMax;
		int iterateurMedian;
		int iterateurTrouve;
		String[] tableauNom = {"agathe","berthe","chloé","cunégonde","olga","raymonde","sidonie"};
		boolean rechercheFinie;
		rechercheFinie = false;
		iterateurPlageMin = 0;
		iterateurPlageMax = tableauNom.length;
		iterateurTrouve = -1;
		while (!rechercheFinie) {
			iterateurMedian= (int)(iterateurPlageMin+(iterateurPlageMax-iterateurPlageMin)/2.0f);
			if (iterateurPlageMin==iterateurPlageMax) {
				rechercheFinie = true;
				
			} else {
				if (chaineRecherche.compareTo(tableauNom[iterateurMedian]) > 0) {
					iterateurPlageMin = iterateurMedian;
				} else if (chaineRecherche.compareTo(tableauNom[iterateurMedian]) < 0) {
					iterateurPlageMax = iterateurMedian;
				}
			}
			if (chaineRecherche.compareTo(tableauNom[iterateurMedian])==0) {
				iterateurTrouve = iterateurMedian;
				rechercheFinie = true;
			}
		}
		if (iterateurTrouve != -1) {
			System.out.println("la chaine \"" + chaineRecherche + "\" a été trouvée à l'iterateur " + iterateurTrouve);
		} else {
			System.out.println("la chaine \"" + chaineRecherche + "\" n'a pas été trouvée");
		}
	}
}
