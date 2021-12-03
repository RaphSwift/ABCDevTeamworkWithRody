import java.util.Scanner;

public class ConvertisseurFahrenheitCelcius {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int numCaractereDansSaisieUtilisateur;
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
			saisieUtilisateur = sc.nextLine();
			if (saisieUtilisateur.length() >= 3) {
				uniteMesure = saisieUtilisateur.charAt(saisieUtilisateur.length()-1);
				if (uniteMesure == 'C' ||
					uniteMesure == 'F') {
					chaineTemperatureAConvertir = saisieUtilisateur.substring(0,saisieUtilisateur.length()-2);
					temperatureAConvertir= Float.parseFloat(chaineTemperatureAConvertir);
					if (uniteMesure == 'C' && 
						temperatureAConvertir >= -273.15f &&
						temperatureAConvertir <= 2777760) {
						saisieCorrecte = true;
					} else if (uniteMesure == 'F' &&
							temperatureAConvertir >= -459.67 &&
							temperatureAConvertir <= 5000000) {
						saisieCorrecte = true;
					}
						
				}
				
			}
		} while (!saisieCorrecte);
		if (uniteMesure== 'C') {
			valeurPostConversion = (temperatureAConvertir*9.0f/5.0f)+32.0f;
			System.out.println(temperatureAConvertir + "°C = " + valeurPostConversion + "°F");
		} else {
			valeurPostConversion = (temperatureAConvertir-32.0f)*5.0f/9.0f;
			System.out.println(temperatureAConvertir + "°F = " + valeurPostConversion + "°C");
		}
		sc.close();
	}
}
