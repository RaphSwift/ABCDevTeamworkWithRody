import java.math.BigDecimal;

public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		references.RefObject<BigDecimal> aire = new references.RefObject<BigDecimal>(null);
		references.RefObject<BigDecimal> perimetre = new references.RefObject<BigDecimal>(null);
		float cotes[] = {10.0f,15.0f,30.0f};
		calculerAireEtPerimetre(aire,perimetre,cotes[0],cotes[1],cotes[2]);
		System.out.println("Perimetre: " + perimetre.argValue + "\nAire: " + aire.argValue);
	}	
	
	public static void calculerAireEtPerimetre(references.RefObject<BigDecimal> aire, references.RefObject<BigDecimal> perimetre, float cote1, float cote2, float cote3) {
		perimetre.argValue = new BigDecimal(cote1+cote2+cote3);
		aire.argValue = new BigDecimal((perimetre.argValue.doubleValue()/2.0f-cote1)*(perimetre.argValue.doubleValue()/2.0f-cote2)*(perimetre.argValue.doubleValue()/2.0f-cote3));
	}
}
