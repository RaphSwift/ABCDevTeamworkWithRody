package utils;

import especes.Algue;
import especes.Poisson;

public class Command_IntroduireAlgue implements Command {
	private Aquarium in;
	private Algue from;
	private boolean rt;
	private boolean parReproduction;
	public Command_IntroduireAlgue(Aquarium _in,
			Algue _from,
			boolean _parReproduction,
			boolean _rt) {
		in =_in;
		from = _from;
		rt=_rt;
		parReproduction = _parReproduction;
	}
	
	public Command_IntroduireAlgue(Aquarium _in,
			Algue _from,
			boolean _parReproduction) {
		in =_in;
		from = _from;
		parReproduction = _parReproduction;
	}
	
	public Command_IntroduireAlgue(Command_IntroduireAlgue _from) {
		this(_from.in,_from.from,_from.parReproduction,_from.rt);
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException{
		return new Command_IntroduireAlgue(this);
	}
	public boolean exec() {
		rt = in.insererEtreVivant(from);
		return rt;
	}
	
	@Override
	public String toString() {
		if (rt) {
			return from + " a été introduit dans l'aquarium " + (parReproduction ? "par division":"par l'utilisateur");
		}
		return from+" n'a pas pu etre mit dans l'aquarium";
	}
	
}
