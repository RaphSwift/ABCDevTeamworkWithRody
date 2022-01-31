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
	
	
	
	public void reset() {
		commands.clear();
	}
}
