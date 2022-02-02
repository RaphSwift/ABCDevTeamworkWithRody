package utils;

import java.util.ArrayList;

public class CommandManager implements java.io.Serializable, Cloneable{
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
	
	public CommandManager(ArrayList<Command> from) {
		commands = new ArrayList<Command>();
		for (int i = 0; i < from.size(); i++) {
			try {
				commands.add((Command)from.get(i).clone());
			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public int getSize() {
		return commands.size();
	}
	
	
	public void addFromCommandManager(CommandManager from) {
		for (int i = 0; i < from.commands.size(); i++) {
			try {
				commands.add((Command)from.commands.get(i).clone());
			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public CommandManager getBetween(int min, int max) {
		if (min <= max && max <= commands.size()) {
			ArrayList<Command> cmd = new ArrayList<Command>();
			for (int i = min; i < max; i++) {
				cmd.add(commands.get(i));
			}
			CommandManager rt = new CommandManager(cmd);
			return rt;
		}
		return null;
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
