
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MaClasse mc = new MaClasse(new MonObjet("j"));
		MonObjet test = mc.getMonObjet();
		test = null;
		System.out.println(mc.getMonObjet().getNom());
	}

}
