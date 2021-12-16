import java.util.Scanner;

public class ConversionPlageFahrenheitCelcius {

	/* Jeu d'essai
	 * {'C','180'190'}, {'C',"10,5","15.5"}, {'C',"-273.15","2777760"}, {'F',"10,5","15.5"}
	 */
	public static void main(String[] args) {
		
		float[] valeursAConvertir = new float[2];
		
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
				chaineTmpAConvertir = sc.nextLine().toUpperCase();
				if (chaineTmpAConvertir.equals("QUIT")) {
					veutQuitter = true;
				} else {
					uniteConversion = chaineTmpAConvertir.toUpperCase().charAt(0);
					if (uniteConversion != 'C' &&
						uniteConversion != 'F') {
						System.out.println("Veuillez entrez 'C' ou 'F' uniquement svp");
						saisieCorrecte = false;
					}
				}
			} while (!saisieCorrecte && !veutQuitter);
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
					chaineTmpAConvertir = sc.nextLine().replace(',','.').toUpperCase();

					if (chaineTmpAConvertir.equals("QUIT")) {
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
					convertirPlage(valeursAConvertir[0], valeursAConvertir[1],uniteConversion);
				}
				nombreValeurAConvertir = 0;
			}
		} while (!veutQuitter);
		sc.close();
	}
	
	public static void convertirPlage(float min, float max, char uniteConversion) {
		float valeurTmpConvertie;
		for (float valeurAConvertir = min; valeurAConvertir <= max; valeurAConvertir++) {
			if (uniteConversion == 'C') {
				valeurTmpConvertie = convertToFarenheit(valeurAConvertir);					
				System.out.println(valeurAConvertir + "°C = " + valeurTmpConvertie + "°F");
			} else {
				valeurTmpConvertie = convertToCelcius(valeurAConvertir);
				System.out.println(valeurAConvertir + "°F = " + valeurTmpConvertie + "°C");						
			}
		}
	}
	
	public static float convertToFarenheit(float celcius) {
		return (celcius*9.f/5.0f)+32;
	}
	
	public static float convertToCelcius(float farenheit) {
		return (farenheit-32)*5.0f/9.0f;
	}
	

}
