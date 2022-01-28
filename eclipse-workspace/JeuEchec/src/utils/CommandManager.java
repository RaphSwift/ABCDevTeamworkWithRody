package utils;

import java.util.ArrayList;

public class CommandManager implements java.io.Serializable{
	private ArrayList<Command> commands = new ArrayList<Command>();
	
	public boolean executeCommand(Command c) {
		if (c.deplacer()) {
			commands.add(c);
			return true;
		}
		return false;
	}
}
