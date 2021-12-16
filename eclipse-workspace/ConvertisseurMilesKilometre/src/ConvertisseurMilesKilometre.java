import java.util.Scanner;

public class ConvertisseurMilesKilometre {
	/* Jeu d'essai
	 	14 km, 14 mi, 0 mi, 0 km, go, quit
	 */
	public static void main(String[] args) {
		
		
		// DECLARATION
		Scanner sc = new Scanner(System.in);
		String valeurAConvertir;
		Distance[] distanceAConvertir = new Distance[100];
		int nbValeurs;
		int idValeurCourrante;
		boolean veutQuitter;
		boolean veutConvertir;
		System.out.println("Programme de conversion km<->mi"
				+ "\nVeuillez entrez des valeurs a convertir separe par l'unite de mesure (ex: 150 km)"
				+ "\nUne fois fini veuillez entrez go pour lancer la conversion ou quit pour quitter le programme");
		do {
			veutConvertir = false;
			veutQuitter = false;
			nbValeurs = 0;
			idValeurCourrante = 1;
			do {
				nbValeurs++;
				System.out.println("Valeur "+ nbValeurs + " a convertir: ");
				valeurAConvertir = sc.nextLine();
				if (valeurAConvertir.equals("go")) {
					veutConvertir = true;
				} else if (valeurAConvertir.equals("quit")) {
					veutQuitter = true;
				} else {
					distanceAConvertir[nbValeurs-1] = SeparerValeur(valeurAConvertir);					
				}
			} while (!veutConvertir && !veutQuitter);
			if (veutConvertir) {
				for (idValeurCourrante = 0; idValeurCourrante < nbValeurs-1; idValeurCourrante++) {
					System.out.print((idValeurCourrante+1)+ ": " + distanceAConvertir[idValeurCourrante].getDistance() +" " + distanceAConvertir[idValeurCourrante].getUnite());
					distanceAConvertir[idValeurCourrante].convertir();
					System.out.println(" = "+distanceAConvertir[idValeurCourrante].getDistance() +" " + distanceAConvertir[idValeurCourrante].getUnite());
				}
			}
		} while (!veutQuitter);
		
		sc.close();
	}
	

	
	private static Distance SeparerValeur(String distanceEnChaine) {
		float distance;

		String uniteMesure = "";
		distance = 0;
		
		if (distanceEnChaine.length() > 3) {
			uniteMesure = distanceEnChaine.substring(distanceEnChaine.length()-2, distanceEnChaine.length());
			if (uniteMesure.equals("km")) {
				distance = Float.parseFloat(distanceEnChaine.substring(0,distanceEnChaine.length()-3));
			} else if (uniteMesure.equals("mi")) {
				distance = Float.parseFloat(distanceEnChaine.substring(0,distanceEnChaine.length()-3));
			} else {
				distance = Float.parseFloat(distanceEnChaine);
				uniteMesure = "km";
			}
		} else {
			distance = Float.parseFloat(distanceEnChaine);
			uniteMesure = "km";
		}
		return new Distance(distance,uniteMesure);
	}
}
