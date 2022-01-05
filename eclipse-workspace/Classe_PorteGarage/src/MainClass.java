
public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PorteGarage porte = new PorteGarage("scs sentinel","nk48",true,0f);
		System.out.println(porte.toString());
		if (porte.deverouillerPorte()) {
			System.out.println("J'ai reussi a me deverouiller");
			if (!porte.deverouillerPorte()) {
				System.out.println("Je suis deja deverouillée");
			}
			if (porte.ouvrir(0.5f)) {
				System.out.println(porte.toString());
				if (!porte.ouvrir(0.4f)) {
					System.out.println("impossible de m'ouvrir à un taux de 40% peut être suis-je verouillée ou ai-je un taux déja suppérieur");
				}
				if (!porte.ouvrir(1.2f)) {
					System.out.println("impossible de m'ouvrir à un taux de 120% c'est trop");
				}
				porte.fermer(0.3f);
				if (!porte.ouvrir(0.4f)) {
					System.out.println("impossible de m'ouvrir à un taux de 40% peut être suis-je verouillée ou ai-je un taux déja suppérieur");
				} else {
					System.out.println("Porte ouverte a 40%");
				}
			}
		} else {
			System.out.println("J'ai reussi a me deverouiller");			
		}
	}

}
