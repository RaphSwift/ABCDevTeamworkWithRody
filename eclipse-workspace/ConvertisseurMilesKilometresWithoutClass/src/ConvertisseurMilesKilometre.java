import java.util.Scanner;

public class ConvertisseurMilesKilometre {
	public static void main(String[] args) {
		
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
		float[] distanceEnReel = new float[100];
		String[] uniteDistance = new String[100];
		int nbValeurs;
		int idValeurCourrante;
		boolean veutQuitter;
		boolean veutConvertir;
		float valeurTmpPostConversion;
		String uniteMesureTmp;
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
					

					if (valeurAConvertir.length() > 3) {
						uniteMesureTmp = valeurAConvertir.substring(valeurAConvertir.length()-2, valeurAConvertir.length());
						if (uniteMesureTmp.equals("km")) {
							distanceEnReel[nbValeurs-1] = Float.parseFloat(valeurAConvertir.substring(0,valeurAConvertir.length()-3));
							uniteDistance[nbValeurs-1] = uniteMesureTmp;
						} else if (uniteMesureTmp.equals("mi")) {
							distanceEnReel[nbValeurs-1] = Float.parseFloat(valeurAConvertir.substring(0,valeurAConvertir.length()-3));
							uniteDistance[nbValeurs-1] = uniteMesureTmp;
						} else {
							distanceEnReel[nbValeurs-1] = Float.parseFloat(valeurAConvertir);
							uniteDistance[nbValeurs-1] = "km";
						}
					} else {
						distanceEnReel[nbValeurs-1] = Float.parseFloat(valeurAConvertir);
						uniteDistance[nbValeurs-1] = "km";
					}
				}
			} while (!veutConvertir && !veutQuitter);
			if (veutConvertir) {
				for (idValeurCourrante = 0; idValeurCourrante < nbValeurs-1; idValeurCourrante++) {
					System.out.print((idValeurCourrante+1)+ ": " + distanceEnReel[idValeurCourrante] + " " + uniteDistance[idValeurCourrante]);
					if (uniteDistance[idValeurCourrante].equals("mi")) {
						distanceEnReel[idValeurCourrante] = distanceEnReel[idValeurCourrante] * 1.609f;
						uniteDistance[idValeurCourrante] = "km";
					} else {
						distanceEnReel[idValeurCourrante] = distanceEnReel[idValeurCourrante] / 1.609f;
						uniteDistance[idValeurCourrante] = "mi";
					}
					System.out.println(" = " + distanceEnReel[idValeurCourrante] + " " + uniteDistance[idValeurCourrante]);
				}
				
			}
		} while (!veutQuitter);
		
		sc.close();
	}
	
	

}
