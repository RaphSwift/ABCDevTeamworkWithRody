package utils;

import especes.Algue;
import especes.Poisson;

public class Command_ReproduireAlgue implements Command{
	private Algue from;
	private Aquarium in;
	private boolean rt;
	
	public Command_ReproduireAlgue(Algue _from, Aquarium _in) {
		from = _from;
		in = _in;
	}
	
	public Command_ReproduireAlgue(Algue _from,
			Aquarium _in,
			boolean _rt) {
		from = _from;
		in = _in;
		rt = _rt;
	}
	
	public Command_ReproduireAlgue(Command_ReproduireAlgue _from) {
		this(_from.from,_from.in,_from.rt);
	}
	
	public Object clone() throws CloneNotSupportedException{
		return new Command_ReproduireAlgue(this);
	}
	@Override
	public boolean exec() {
		rt = from.seDiviser(in);
		return rt;
	}
	
	@Override
	public String toString() {
		if (rt) {
			return from + " s'est divisé";
		} else {
			return from+ " n'as pas réussi à se diviser";
		}
	}
}
