import reference.RefObject;

public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RefObject<String> maChaineAInverser = new RefObject<String>("je n'ai pas bu mon café ce matin, je suis fatigué");
		System.out.println(maChaineAInverser.argValue);
		inverserChaine(maChaineAInverser);
		System.out.println(maChaineAInverser.argValue);
		
		
	}

	public static void inverserChaine(RefObject<String> arg) {
		String inverser = "";
		for (int i = arg.argValue.length()-1; i >= 0; i--) {
			inverser+= arg.argValue.charAt(i);
		}
		arg.argValue = inverser;
	}
}
