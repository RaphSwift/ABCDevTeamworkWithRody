
public class FlyCommand implements Command{
	private Receiver receiver = new Receiver();
	
	@Override
	public String executer() {
		return receiver.fly();
	}


}
