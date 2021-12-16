
public class MainClass {
	public static void main(String[] args) {
		String maChaineATester="il etait une fois, dans la ville de foix, une marchande de foie, qui vendais du foie, elle se dit 'ma foi, c'est la premiere fois et la dernière fois que je vens du foie dans la ville de foix'";
		if (maChaineATester.length() >= 120) {
			char[] lettresATester = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o',
					'p','q','r','s','t','u','v','w','x','y','z'};
			int[] nbOccurencesLettres = new int[26];
			//{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
		

			for (int iterateurLettreTeste = 0; iterateurLettreTeste < maChaineATester.length(); iterateurLettreTeste++) {
				int iterateurAlphabet;
				for (iterateurAlphabet = 0; iterateurAlphabet < lettresATester.length;iterateurAlphabet++) {
					if (maChaineATester.charAt(iterateurLettreTeste) == lettresATester[iterateurAlphabet]) {
						nbOccurencesLettres[iterateurAlphabet]++;
					}
				}
			}
			for (int iterateurAlphabet = 0; iterateurAlphabet < nbOccurencesLettres.length; iterateurAlphabet++) {
				System.out.println("Le caractere '" + lettresATester[iterateurAlphabet] + "' à été trouvé " + 
						nbOccurencesLettres[iterateurAlphabet] + " fois.");
			}
			
		} else {
			System.out.println("La chaine doit faire au moins 120 caracteres");
		}
	}
}
