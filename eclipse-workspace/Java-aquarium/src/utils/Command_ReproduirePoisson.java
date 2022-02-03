package utils;

import especes.Poisson;
import especes.Poisson_Carnivore;

public class Command_ReproduirePoisson implements Command {
	private Poisson from;
	private Poisson with;
	private Aquarium in;
	private boolean rt;
	
	public Command_ReproduirePoisson(Poisson _from,
			Poisson _with,
			Aquarium _in) {
		from = _from;
		with = _with;
		in = _in;
	}
	
	public Command_ReproduirePoisson(Poisson _from,
			Poisson _with,
			Aquarium _in,
			boolean _rt) {
		from = _from;
		with = _with;
		in = _in;
		rt = _rt;
	}
	
	public Command_ReproduirePoisson(Command_ReproduirePoisson _from) {
		this(_from.from,_from.with,_from.in,_from.rt);
	}
	
	public Object clone() throws CloneNotSupportedException{
		return new Command_ReproduirePoisson(this);
	}
	
	
	@Override
	public boolean exec() {
		rt = from.seReproduire(with, in);
		return rt;
	}
	
	@Override
	public String toString() {
		String str="";
		if (rt) {
			str += "Le poisson : " + from + " s'est reproduit avec succès avec " + with;
		} else {
			str += "Le poisson: " + from + " a porté son dévolu sur " + with + " mais il ne sont pas passer à l'acte, c'est ballot!";
		}
		return str;
	}
	
	
}
