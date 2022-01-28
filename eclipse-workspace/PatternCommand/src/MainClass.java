
public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Invoker inv = new Invoker();
		System.out.println(inv.executeOperations(new FlyCommand()));
		System.out.println(inv.executeOperations(new MoveCommand()));

	}

}
