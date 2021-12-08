
public class MainClass {
	public static void main(String[] args) {
		String maChaineATester="Rodolphe.";
		char caractereRecherche='o';
		int nbOccurenceCaractere=0;
		int iterateurPhrase;
		if (maChaineATester.length() > 0 && 
			maChaineATester.charAt(maChaineATester.length()-1) == '.') {
			if (!maChaineATester.equals(".")) {
				for (iterateurPhrase = 0; iterateurPhrase < maChaineATester.length(); iterateurPhrase++) {
					if (caractereRecherche == maChaineATester.charAt(iterateurPhrase))
						nbOccurenceCaractere++;
				}
				System.out.println("le caractere '" +caractereRecherche + "' a ete trouvé "+nbOccurenceCaractere + " fois." );
			} else {
				System.out.println("LA CHAINE EST VIDE");
			}
		} else {
			System.out.println("Une phrase doit contenir au moins une lettre et se terminer par un '.'");
		}
	}
}
