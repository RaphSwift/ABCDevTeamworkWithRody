package utils;

import java.util.ArrayList;

public class CommandManager implements java.io.Serializable{
	private ArrayList<Command> commands = new ArrayList<Command>();

	
	public boolean executeCommand(Command c) {
		if (c.executer()) {
			commands.add(c);
			return true;
		}
		return false;
	}
	
	
	
	@Override
	public final String toString() {
		String str="";
		for (int i = 0; i < commands.size();i++) {
			str += commands.get(i);
			if (i+1 < commands.size())
				str+="\n";
		}
		return str;
	}
	
	public final Command getLastCommand() {
		return commands.get(commands.size()-1);
	}
	
	
	public void reset() {
		commands.clear();
	}
}
