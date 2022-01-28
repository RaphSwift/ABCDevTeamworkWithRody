package utils;

import java.util.ArrayList;

public class CommandManager implements java.io.Serializable{
	private ArrayList<Command> commands = new ArrayList<Command>();
	
	public boolean executeCommand(Command c) {
		commands.add(c);
		return c.deplacer();
	}
}
