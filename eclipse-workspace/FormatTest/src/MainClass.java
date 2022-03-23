
public class MainClass {
	public static void main(String[] args) {
		String str = "rbounatirou@gmail.fr";
		boolean rt = str.matches(".*@.*.fr");
		
		System.out.println((rt ? "Ok": "Non"));
	}
}
