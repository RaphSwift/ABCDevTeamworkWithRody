
public class MainClass {
	public static void main(String[] args) {
		String palindromeATester;
		palindromeATester = "LAVAL.";
		if (palindromeATester.length()== 1 &&
			palindromeATester.charAt(0)=='.') {
			System.out.println("LA CHAINE EST VIDE");
		} else {
			if (palindromeATester.charAt(palindromeATester.length()-1)=='.') {
				int iterateurPalindrome=0;
				int iterateurMax;
				char caractereDebut;
				char caractereFin;
				boolean estUnPalindrome = true;
				iterateurMax = (palindromeATester.length()-1)/2;
				while (estUnPalindrome && iterateurPalindrome <= iterateurMax) {
					caractereDebut=palindromeATester.charAt(iterateurPalindrome);
					caractereFin=palindromeATester.charAt(palindromeATester.length()-(2 +iterateurPalindrome));
					if (caractereDebut != caractereFin)
						estUnPalindrome = false;
					iterateurPalindrome++;
				}
				if (estUnPalindrome) {
					System.out.println("La chaine \""+ palindromeATester + "\" est un palindrome");
				} else {
					System.out.println("La chaine \""+ palindromeATester + "\" n'est pas un palindrome");
				}
			} else {
				System.out.println("Veuillez terminer le mot par un '.'");
			}
		}
	}
}

