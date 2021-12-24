
public class MainClass {
	public static void main(String[] args) {
		Oiseau o = new Oiseau();
		System.out.println(o.decrire());
		System.out.println(o.crier());
		o.voler(new float[] {10,0,0});
		System.out.println(o.decrire());
	}
}
