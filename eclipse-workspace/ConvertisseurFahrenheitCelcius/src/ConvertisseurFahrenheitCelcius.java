import java.util.Scanner;

public class ConvertisseurFahrenheitCelcius {
	
	/* JEU D'essai
	 	 17 c, 17C, 17 f, -273,16 c, 2777760 c, 2777761 c, 460 f, 5000001 f, 12
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		String saisieUtilisateur;
		boolean saisieCorrecte;
		float temperatureAConvertir = 0;
		String chaineTemperatureAConvertir;
		char uniteMesure = 'C';
		float valeurPostConversion;
		saisieCorrecte = false;
		System.out.println("Programme de conversion Celcius <-> Fahrenheit");
		do {
			saisieCorrecte = false;
			System.out.println("Veuillez entrez la valeur a convertir avec C pour Celcius \n ou"
			+ " F pour fahrenheit (ex: 17 C)");
			saisieUtilisateur = sc.nextLine().toUpperCase();
			if (saisieUtilisateur.length() >= 3) {
				uniteMesure = saisieUtilisateur.charAt(saisieUtilisateur.length()-1);
				if (uniteMesure == 'C' ||
					uniteMesure == 'F') {
					if (saisieUtilisateur.charAt(saisieUtilisateur.length()-2) == ' ') 
						chaineTemperatureAConvertir = saisieUtilisateur.substring(0,saisieUtilisateur.length()-2);
					else
						chaineTemperatureAConvertir = saisieUtilisateur.substring(0,saisieUtilisateur.length()-1);
					temperatureAConvertir= Float.parseFloat(chaineTemperatureAConvertir.replace(',', '.'));
					saisieCorrecte = verifierPlageSaisie(uniteMesure, temperatureAConvertir);
						
				}
				
			}
		} while (!saisieCorrecte);
		direValeurConvertie(uniteMesure, temperatureAConvertir);
		sc.close();
	}
	
	
	public static boolean verifierPlageSaisie(char uniteMesure, float temperature) {
		if (uniteMesure == 'C' && 
			temperature >= -273.15f &&
			temperature <= 2777760) {
			return true;
		} else if (uniteMesure == 'F' &&
			temperature >= -459.67 &&
			temperature <= 5000000) {
				return true;
		}
		return false;
	}
	
	public static void direValeurConvertie(char uniteMesure, float temperatureAConvertir) {
		float valeurPostConversion;
		if (uniteMesure== 'C') {
			valeurPostConversion = (temperatureAConvertir*9.0f/5.0f)+32.0f;
			System.out.println(temperatureAConvertir + "�C = " + valeurPostConversion + "�F");
		} else {
			valeurPostConversion = (temperatureAConvertir-32.0f)*5.0f/9.0f;
			System.out.println(temperatureAConvertir + "�F = " + valeurPostConversion + "�C");
		}
	}
}
