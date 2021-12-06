import java.util.Scanner;

public class ConversionPlageFahrenheitCelcius {

	public static void main(String[] args) {
		
		float[] valeursAConvertir = new float[2];
		float valeurAConvertir;
		
		int nombreValeurAConvertir = 0;
		float valeurTmpConvertie;
		String chaineTmpAConvertir;
		char uniteConversion = ' ';
		boolean saisieCorrecte;
		boolean veutQuitter;

		

		Scanner sc = new Scanner(System.in);
		System.out.println("Programmme de conversion Fahrenheit <-> Celcius v2");
		// CONTROLE DE L'UNITE DE SAISIE
		do {
			saisieCorrecte = true;
			veutQuitter = false;
			// CONTROLE DE L'UNITE DE CONVERSION
			do {
				saisieCorrecte = true;
				System.out.println("Quel est l'unite de conversion (C pour Celcius, F pour Fahrenheit)");
				chaineTmpAConvertir = sc.nextLine();
				if (chaineTmpAConvertir.equals("quit")) {
					veutQuitter = true;
				} else {
					uniteConversion = chaineTmpAConvertir.toUpperCase().charAt(0);
					if (uniteConversion != 'C' &&
						uniteConversion != 'F') {
						System.out.println("Veuillez entrez 'C' ou 'F' uniquement svp");
						saisieCorrecte = false;
					}
				}
			} while (!saisieCorrecte && veutQuitter);
			// AFFICHAGE
			if (!veutQuitter) {
				System.out.print("Rentrez une valeur a convertir en ");
				if (uniteConversion == 'C')
					System.out.print("Celsius");
				else
					System.out.print("Fahrenheit");
				System.out.println(" ou entrez quit pour quitter");

				do {
					if (nombreValeurAConvertir == 0)
						System.out.println("Saisir la valeur minimale");
					else
						System.out.println("Saisir la valeur maximale");
					chaineTmpAConvertir = sc.nextLine();

					if (chaineTmpAConvertir.equals("quit")) {
						veutQuitter = true;
					} else {
						valeurTmpConvertie= Float.parseFloat(chaineTmpAConvertir);
						if (uniteConversion == 'C' && valeurTmpConvertie >= -273.15 && valeurTmpConvertie <= 2777760) {
							valeursAConvertir[nombreValeurAConvertir] = valeurTmpConvertie;
							nombreValeurAConvertir++;
						} else if (uniteConversion == 'F' && valeurTmpConvertie >= -459.67 && valeurTmpConvertie <= 5000000) {
							valeursAConvertir[nombreValeurAConvertir] = valeurTmpConvertie;
							nombreValeurAConvertir++;
						}  else {
							System.out.println("Valeur invalide");
						}
					}
				} while (nombreValeurAConvertir<2 && !veutQuitter);
				if (!veutQuitter) {
					for (valeurAConvertir = valeursAConvertir[0]; valeurAConvertir <= valeursAConvertir[1]; valeurAConvertir++) {
						if (uniteConversion == 'C') {
							valeurTmpConvertie = (valeurAConvertir*9.f/5.0f)+32;						
							System.out.println(valeurAConvertir + "°C = " + valeurTmpConvertie + "°F");
						} else {
							valeurTmpConvertie = (valeurAConvertir-32)*5.0f/9.0f;
							System.out.println(valeurAConvertir + "°F = " + valeurTmpConvertie + "°C");						
						}
					}
				}
				nombreValeurAConvertir = 0;
			}
		} while (!veutQuitter);
		sc.close();
	}

}
