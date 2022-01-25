package pieces;

import java.util.ArrayList;

import utils.Coordonees;
import utils.Mouvement;
import utils.Plateau;

public class Piece_Reine extends Piece{
	
	public Piece_Reine(Piece from) {
		super(from);
	}
	
	@Override
	public String toString() {
		return "[Reine" + position+"]";
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		return new Piece_Reine(this);
	}
	
	public Piece_Reine(Coordonees position, boolean _isBlack, boolean _isDead) {
		super(position,_isBlack,_isDead);
	}

	public Piece_Reine(Piece_Reine from) {
		this(from.position,from.isBlack,from.isDead);
	}
	
	public Piece_Reine(Coordonees _position, boolean _isBlack) {
		this(_position,_isBlack,false);
	}
	
	@Override
	public ArrayList<Mouvement> calculerMouvement(Plateau p){
		ArrayList<Mouvement> mouvementsPossibles = new ArrayList<Mouvement>();
		mouvementsPossibles.addAll(calculerLignes(p));
		mouvementsPossibles.addAll(calculerDiagonales(p));

		Plateau simulation;
		Piece tmpRoi;
		for (int i = mouvementsPossibles.size() -1 ; i >= 0; i--) {
			simulation = p.simulerMouvement(mouvementsPossibles.get(i));
			tmpRoi = simulation.getRoi(isBlack);
			if (tmpRoi.estEnEchec(simulation).size()>0) {
				mouvementsPossibles.remove(i);
			}
		}
		return mouvementsPossibles;
	}
	

	private ArrayList<Mouvement> calculerDiagonales(Plateau p){
		ArrayList<Mouvement> mouvementsPossibles = new ArrayList<Mouvement>();
		Piece tmp=null;
		Coordonees test = new Coordonees(position);
		// SUPPERIEUR DROITE
		while (tmp==null && test.getX()>0 && test.getY()+1< p.getHeight()) {
			test = (new Coordonees((byte)(test.getX()-1),(byte)(test.getY()+1)));
			try {
				tmp = ((Piece)p.getPieceFromCoord(test).clone());
			} catch (Exception e) {
				tmp = null;
			}

			if (tmp != null) {
				if (tmp.estNoir() != isBlack) {
					mouvementsPossibles.add(new Mouvement(new Coordonees(this.position), new Coordonees(test)));
				}
			} else {
				mouvementsPossibles.add(new Mouvement(new Coordonees(this.position), new Coordonees(test)));
			}
			
		}
		tmp = null;
		test = new Coordonees(position);
		// SUPPERIEUR GAUCHE
		while (tmp==null && test.getX()+1 < p.getWidth() && test.getY()+1 < p.getHeight()) {
			test = (new Coordonees((byte)(test.getX()+1),(byte)(test.getY()+1)));
			try {
				tmp = ((Piece)p.getPieceFromCoord(test).clone());
			} catch (Exception e) {
				tmp = null;
			}
			if (tmp != null) {
				if (tmp.estNoir() != isBlack) {
					mouvementsPossibles.add(new Mouvement(new Coordonees(this.position), new Coordonees(test)));
				}
			} else {
				mouvementsPossibles.add(new Mouvement(new Coordonees(this.position), new Coordonees(test)));
			}
			
		}
		tmp = null;
		test = new Coordonees(position);
		// INFERIEUR GAUCHE
		while (tmp==null && test.getY() >0 && test.getX() > 0) {
			test = (new Coordonees((byte)(test.getX()-1),(byte)(test.getY()-1)));
			try {
				tmp = ((Piece)p.getPieceFromCoord(test).clone());
			} catch (Exception e) {
				tmp = null;
			}
			if (tmp != null) {
				if (tmp.estNoir() != isBlack) {
					mouvementsPossibles.add(new Mouvement(new Coordonees(this.position), new Coordonees(test)));
				}
			} else {
				mouvementsPossibles.add(new Mouvement(new Coordonees(this.position), new Coordonees(test)));
			}
			
		}
		tmp = null;
		test = new Coordonees(position);
		// INFERIEUR DROITE
		while (tmp==null && test.getY() > 0 && test.getX()+1<p.getWidth()) {
			test = (new Coordonees((byte)(test.getX()+1),(byte)(test.getY()-1)));
			try {
				tmp = ((Piece)p.getPieceFromCoord(test).clone());
			} catch (Exception e) {
				tmp = null;
			}
			if (tmp != null) {
				if (tmp.estNoir() != isBlack) {
					mouvementsPossibles.add(new Mouvement(new Coordonees(this.position), new Coordonees(test)));
				}
			} else {
				mouvementsPossibles.add(new Mouvement(new Coordonees(this.position), new Coordonees(test)));
			}
			
		}
		return mouvementsPossibles;
	}
	
	private ArrayList<Mouvement> calculerLignes(Plateau p){
		ArrayList<Mouvement> mouvementsPossibles = new ArrayList<Mouvement>();
		Piece tmp=null;
		Coordonees coordoneesTestes = new Coordonees(position);
		// A GAUCHE
		while (tmp==null && coordoneesTestes.getX()>0) {
			coordoneesTestes = (new Coordonees((byte)(coordoneesTestes.getX()-1),coordoneesTestes.getY()));
			try {
				tmp = ((Piece)p.getPieceFromCoord(coordoneesTestes).clone());
			} catch (Exception e) {
				tmp = null;
			}
			if (tmp != null) {
				if (tmp.estNoir() != isBlack) {
					mouvementsPossibles.add(new Mouvement(new Coordonees(this.position), new Coordonees(coordoneesTestes)));
				}
			} else {
				mouvementsPossibles.add(new Mouvement(new Coordonees(this.position), new Coordonees(coordoneesTestes)));
			}
			
		}
		tmp = null;
		coordoneesTestes = new Coordonees(position);
		// A DROITE
		while (tmp==null && coordoneesTestes.getX()+1 < p.getWidth()) {
			coordoneesTestes = (new Coordonees((byte)(coordoneesTestes.getX()+1),coordoneesTestes.getY()));
			try {
				tmp = ((Piece)p.getPieceFromCoord(coordoneesTestes).clone());
			} catch (Exception e) {
				tmp = null;
			}
			if (tmp != null) {
				if (tmp.estNoir() != isBlack) {
					mouvementsPossibles.add(new Mouvement(new Coordonees(this.position), new Coordonees(coordoneesTestes)));
				}
			} else {
				mouvementsPossibles.add(new Mouvement(new Coordonees(this.position), new Coordonees(coordoneesTestes)));
			}
			
		}
		tmp = null;
		coordoneesTestes = new Coordonees(position);
		// EN HAUT
		while (tmp==null && coordoneesTestes.getY()+1 < p.getHeight()) {
			coordoneesTestes = (new Coordonees((byte)(coordoneesTestes.getX()),(byte)(coordoneesTestes.getY()+1)));
			try {
				tmp = ((Piece)p.getPieceFromCoord(coordoneesTestes).clone());
			} catch (Exception i) {
			}
			if (tmp != null) {
				if (tmp.estNoir() != isBlack) {
					mouvementsPossibles.add(new Mouvement(new Coordonees(this.position), new Coordonees(coordoneesTestes)));
				}
			} else {
				mouvementsPossibles.add(new Mouvement(new Coordonees(this.position), new Coordonees(coordoneesTestes)));
			}
			
		}
		tmp = null;
		coordoneesTestes = new Coordonees(position);
		// EN BAS
		while (tmp==null && coordoneesTestes.getY() > 0) {
			coordoneesTestes = (new Coordonees((byte)(coordoneesTestes.getX()),(byte)(coordoneesTestes.getY()-1)));
			try {
				tmp = ((Piece)p.getPieceFromCoord(coordoneesTestes).clone());
			} catch (Exception i) {
			}
			if (tmp != null) {
				if (tmp.estNoir() != isBlack) {
					mouvementsPossibles.add(new Mouvement(new Coordonees(this.position), new Coordonees(coordoneesTestes)));
				}
			} else {
				mouvementsPossibles.add(new Mouvement(new Coordonees(this.position), new Coordonees(coordoneesTestes)));
			}
			
		}
		return mouvementsPossibles;
	}
}
