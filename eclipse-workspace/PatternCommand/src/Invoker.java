import java.util.ArrayList;
import java.util.List;

public class Invoker {
	private final List<Command> commands= new ArrayList<Command>();
	
	public String executeOperations(Command command) {
		commands.add(command);
		return command.executer();
	}
}
