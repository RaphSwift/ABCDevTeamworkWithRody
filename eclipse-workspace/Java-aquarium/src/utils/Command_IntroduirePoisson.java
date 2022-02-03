package utils;

import especes.Poisson;

public class Command_IntroduirePoisson implements Command {
	private Aquarium in;
	private Poisson from;
	private boolean rt;
	private boolean parReproduction;
	
	public Command_IntroduirePoisson(Aquarium _in,
			Poisson _from,
			boolean _parReproduction) {
		in =_in;
		from = _from;
		parReproduction = _parReproduction;
	}
	
	
	public Command_IntroduirePoisson(Aquarium _in,
			Poisson _from,
			boolean _parReproduction,
			boolean _rt) {
		in =_in;
		from = _from;
		parReproduction = _parReproduction;
		rt = _rt;
	}
	
	public Command_IntroduirePoisson(Command_IntroduirePoisson _from) {
		this(_from.in,_from.from,_from.parReproduction,_from.rt);
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException{
		return new Command_IntroduirePoisson(this);
	}
	public boolean exec() {
		rt = in.insererEtreVivant(from);
		return rt;
	}
	
	@Override
	public String toString() {
		if (rt) {
			return from + " a été introduit dans l'aquarium " + (parReproduction ? "par reproduction":"par l'utilisateur");
		}
		return from+" n'a pas pu etre mit dans l'aquarium";
	}
	
}
