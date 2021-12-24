
public class MainClass {
	public static void main(String[] args) {
		Portable smartphone = new Portable(true, false, false,"Samsung", "Galaxy note 9", 0.01f, 6, 2.8f, 0);
		System.out.println("Eteint mon portable peut tenir " + smartphone.connaitreTempsBatterieEnSecondes() + "s");
		
	}
}
