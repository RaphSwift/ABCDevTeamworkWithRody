import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		String[] fruitsEtLegumes = new String[100] ;
		float[] prix = new float[fruitsEtLegumes.length] ;
		int index = 0;
		int nVal = 0;
		String saisieUtilisateur ;
		Scanner sc = new Scanner(System.in) ;
		String[] split ;
		System.out.println("Veuillez saisir");
		do {
			saisieUtilisateur=  sc.nextLine().trim();
			split = saisieUtilisateur.split(" ");
			if(!saisieUtilisateur.equals("go")) {
				if (split.length == 2) {
					fruitsEtLegumes[index] = split[0];
					prix[index] = Float.valueOf(split[1]);
					index = (index+1)%prix.length; // Si l'indice depasse le tableau on reinitialise
				} else {
					System.out.println("Saisie incorrecte");
				}
			}
			//nval = Math.min(nval+1, fruitsEtLegumes.length);
		} while (!saisieUtilisateur.toLowerCase().equals("go"));
		int i=0;
		while (fruitsEtLegumes[i] != null) {
			System.out.println("Les " + fruitsEtLegumes[i] + " coutent " + prix[i]);
			i++;
		}
		int lowerPriceId = getIdOfLowCost(fruitsEtLegumes, prix);
		System.out.println("- cher:" + fruitsEtLegumes[lowerPriceId] + " a " + prix[lowerPriceId]);
		sc.close() ;
		
	}
	
	public static int getIdOfLowCost(String[] fruits, float[] price) {
		int lower = 0;
		int i=0;
		while(fruits[i] != null && i< price.length) {
			if (price[i] < price[lower]) {
				lower = i;
			}
			i++;
		}
		return lower;
	}

}
