package utils;

import java.util.ArrayList;

public class CommandManager implements Cloneable{
	private ArrayList<Command> commands;
	
	public CommandManager() {
		commands = new ArrayList<Command>();
		
	}
	
	public CommandManager(CommandManager from) {
		commands = new ArrayList<Command>();
		for (int i = 0; i < from.commands.size(); i++) {
			try {
				commands.add((Command)from.commands.get(i).clone());
			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException{
		return new CommandManager(this);
	}
	
	public boolean execute(Command c) {
		commands.add(c);
		return c.exec();
	}
	
	@Override
	public String toString() {
		String str = "";
		for (int i = 0; i<commands.size();i++) {
			str+=commands.get(i);
			if (i+1<commands.size())
				str+="\n";
		}
		return str;
	}
}
