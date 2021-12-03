import java.util.Scanner;

public class ConvertisseurMilesKilometre {
	public static void main(String[] args) {
		
		String unite="";
		
		SeparerValeur("14 mi",unite);
		
		//System.out.println("1522 mi->" + SeparerValeur("1522 mi",true));
		/*String reference = "";
		Distance distance;
		String valeurAConvertir;
		Scanner sc = new Scanner(System.in);
		System.out.println("Veuillez entrez la valeur a convertir: ");
		valeurAConvertir = sc.nextLine();
		distance = SeparerValeur(valeurAConvertir);
		System.out.println("La valeur a converir est " + distance.getDistance() + " son unite de mesure est " + 
		distance.getUnite());
		sc.close();*/
		
		
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
	
	/* CETTE FONCTION EST CHARGE DE CONVERTIR UNE DISTANCE EN
	 KM VERS MI OU INVERSEMENT SELON SON TYPE FOURNI EN ARGUMENT
	 */
	private static float Convertir(float distanceEnReel, String uniteDistance) {
		float retour;
		if (uniteDistance.equals("mi")) {
			return distanceEnReel*1.609f;
		} else {
			return distanceEnReel/1.609f;
		}
	}
	
	/* CETTE FONCTION SEPARE LA CHAINE DE CARACTERES
	 AFIN D'AVOIR D'UN COTE SA VALEUR ET
	 DE L'AUTRE SON UNITE DE DISTANCE SI IL
	 N'Y A PAS D'UNITE PAR DEFAUT ELLE ENVERRA
	 KM,
	 L'unite de distance est stocké en reference (arg2)
	 tandis que la distance est la valeur de retour
	 */
	private static float SeparerValeur(String distanceEnChaine, String referenceEstEnKm) {
		float distance;
		boolean estEnKm;
		int iteratorChaineDistance;
		iteratorChaineDistance = 1;
		String uniteMesure;
		distance = 0;
		if (distanceEnChaine.length() > 3) {
			uniteMesure = distanceEnChaine.substring(distanceEnChaine.length()-2, distanceEnChaine.length());
			if (uniteMesure.equals("km")) {
				distance = Float.parseFloat(distanceEnChaine.substring(0,distanceEnChaine.length()-3));
				referenceEstEnKm = "km";
			} else if (uniteMesure.equals("mi")) {
				distance = Float.parseFloat(distanceEnChaine.substring(0,distanceEnChaine.length()-3));
				referenceEstEnKm = "mi";
			} else {
				distance = Float.parseFloat(distanceEnChaine);
				referenceEstEnKm = "km";
			}
		} else {
			distance = Float.parseFloat(distanceEnChaine);
			referenceEstEnKm = "km";
		}
		return distance;
	}
	
	private static Distance SeparerValeur(String distanceEnChaine) {
		float distance;
		boolean estEnKm;
		int iteratorChaineDistance;
		iteratorChaineDistance = 1;
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
