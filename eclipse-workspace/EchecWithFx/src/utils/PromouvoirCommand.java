package utils;

import pieces.Piece;

public class PromouvoirCommand implements Command{
	Plateau plateau;
	Piece from;
	Piece to;
	byte type;
	
	public PromouvoirCommand(PromouvoirCommand c) {
		this(c.plateau, c.from, c.to, c.type);
	}
	
	public PromouvoirCommand(Plateau _plateau, Piece _from,  byte _type) {
		this(_plateau,_from,null,_type);
	}
	
	public PromouvoirCommand(Plateau _plateau, Piece _from, Piece _to, byte _type) {
		plateau = _plateau;
		from = _from;
		to = _to;
		type = _type;
	}
	
	@Override
	public boolean executer() {
		// fou tour reine cavalier
		Piece tmp = from;
		try {
			from  = (Piece) from.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		boolean rt = false;
		switch(type) {
			case 0:
				rt = plateau.promouvoirFou(tmp);
				break;
			case 1:
				rt = plateau.promouvoirTour(tmp);
				break;
			case 2:
				rt = plateau.promouvoirReine(tmp);
				break;
			case 3:
				rt = plateau.promouvoirCavalier(tmp);
				break;
			default:
				rt = false;
				break;
		}
		if (rt) {
			
			try {
				to = (Piece) plateau.getPieceFromCoord(from.getPosition()).clone();
			} catch (CloneNotSupportedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return rt;
	}
	
	@Override
	public String toString() {
		return  from + " à été promu en " + to;
	}
	
	public final Piece getFrom() {
		return from;
	}
	
	public final Piece getTo() {
		return to;
	}

}
