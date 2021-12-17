
public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int factorielleATester = 15;
		long resultat = calculerFactorielle(factorielleATester);
		System.out.println("le resultat de la factorielle de " + factorielleATester + " est de : " + resultat);
	}

	public static long calculerFactorielle(int n) {
		long resultat;
		if (n == 1 || n==0) {
			return 1;
		} else {
			resultat = calculerFactorielle(n-1)*n;
		}
		return resultat;
	}
}
