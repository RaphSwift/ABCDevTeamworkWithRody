
public class MainClass {
	public static void main(String[] args) {
		System.out.println(getRetired(58));
	}
	
	public static String getRetired(int age) {
		if (age < 0) 
			return "Vous n'êtes pas encore né";
		
			
		 if (age > 60) {
			 int ecartAge = age-60;
			 return "Vous êtes à la retraite depuis " + ecartAge + " an" + (ecartAge > 1 ? "s":"");
		} else if (age < 60) {
			int ecartAge = 60-age;
			return "Il vous reste " + ecartAge + " an" + (ecartAge > 1 ? "s":"") + " avant la retraite";
		} else {
			return "Vous êtes à la retraite cette année";
		}
		
	}
}
