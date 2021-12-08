
public class MainClass {
	public static void main(String[] args) {
		int[] monTableau = {0,1,2,3,4};
		int iterateurTableau;
		int nombreRecherche =3;
		boolean nombreTrouve;
		iterateurTableau = 0;
		nombreTrouve = false;
		while(!nombreTrouve && iterateurTableau != monTableau.length) {
			if (monTableau[iterateurTableau] == nombreRecherche) {
				System.out.println("Nombre trouvé en indice " + iterateurTableau);
				nombreTrouve = true;
			}
			iterateurTableau++;
		}
		if (!nombreTrouve) {
			System.out.println("Le nombre n'a pas été trouvé");
		}
	}
}
