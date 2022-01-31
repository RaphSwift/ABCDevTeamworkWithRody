
public class MainClass {
	public static void main(String[] args) {
		System.out.println(getRetired(42));
	}
	
	public static String getRetired(int age) {
		if (age < 0) 
			return "Vous n'�tes pas encore n�";
		
			
		 if (age > 60) {
			 int ecartAge = Math.abs(60- age);
			if (ecartAge > 1)
				return "Vous �tes � la retraite depuis " + ecartAge + " ans";
			else
				return "Vous �tes � la retraite depuis " + ecartAge + " an";
		} else if (age < 60) {
			int ecartAge = Math.abs(60- age);
			if (ecartAge > 1)
				return "Il vous reste " + ecartAge + " ans avant la retraite";
			else
				return "Il vous reste " + ecartAge + " an avant la retraite";
		} else {
			return "Vous �tes � la retraite cette ann�e";
		}
		
	}
}
