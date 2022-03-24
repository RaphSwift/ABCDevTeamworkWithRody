
public class MainClass {
	public static void main(String[] args) {
		String str = "rbounatirou@gmail.com";
		boolean rt = str.matches("^[[A-Z][a-z][0-9][-*_.]]{4,16}@gmail[.](com|fr)$");
		
		System.out.println((rt ? "Ok": "Non"));
	}
}
