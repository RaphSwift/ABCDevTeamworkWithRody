import java.util.ArrayList;

public class Piece_Pion extends Piece{
	private boolean aBouge;
	
	public Piece_Pion(Piece_Pion from){
		this(from.position,from.isBlack,from.isDead,from.aBouge);
	}
	
	public Piece_Pion(Coordonees _position, boolean _estNoir, boolean _estMorte, boolean _aBouger) {
		super(_position,_estNoir,_estMorte);
		aBouge = _aBouger;
		
	}
	
	public Object clone() throws CloneNotSupportedException {
		return new Piece_Pion(this);
	}
	
	public Piece_Pion(Piece from) {
		super(from);
		aBouge = false;
	}
	
	public Piece_Pion(Coordonees _position, boolean _estNoir, boolean _estMorte) {
		this(_position,_estNoir,_estMorte, false);
		aBouge = false;
		
	}
	
	@Override
	public void tuer() {
		super.tuer();
		System.out.println("Le pion en "+ position +" a été tué");
	}
	
	public Piece_Pion(Coordonees _position, boolean _estNoir) {
		this(_position,_estNoir, false, false);
	}
	
	
	@Override
	public ArrayList<Coordonees> calculerMouvement(Plateau p){
		ArrayList<Coordonees> coords = new ArrayList<Coordonees>();
		Coordonees test;
		Coordonees test2;

		Piece tmp = null;
		Piece tmp2 = null;
		if (isBlack) {
			if (!aBouge) {
				if (position.getY()-2 >= 0) {
					test = new Coordonees((byte)(position.getX()),(byte)(position.getY()-1));
					tmp = p.getPiece(test);
					test2 = new Coordonees((byte)(position.getX()),(byte)(position.getY()-2));
					tmp2 = p.getPiece(test);					
					if (tmp == null) {
						coords.add(test);
						if (tmp2 == null) {
							coords.add(test2);
						}
					}
				} else if (position.getY()-1 >= 0) {
					test = new Coordonees((byte)(position.getX()),(byte)(position.getY()-1));
					tmp = p.getPiece(test);
					if (tmp == null) {
						coords.add(test);
					}
				}
			}
		} else {
			//Y +2
			if (!aBouge) {
				if (position.getY()+2 < p.getHeight()) {
					test = new Coordonees((byte)(position.getX()),(byte)(position.getY()+1));
					tmp = p.getPiece(test);
					test2 = new Coordonees((byte)(position.getX()),(byte)(position.getY()+2));
					tmp2 = p.getPiece(test);					
					if (tmp == null) {
						coords.add(test);
						if (tmp2 == null) {
							coords.add(test2);
						}
					}
				} else if (position.getY()+1 < p.getHeight()) {
					test = new Coordonees((byte)(position.getX()),(byte)(position.getY()+1));
					tmp = p.getPiece(test);
					if (tmp == null) {
						coords.add(test);
					}
				}
			}
			
		}
		return coords;
	}
	
	public ArrayList<Coordonees> calculerMouvementManger(Plateau p){
		ArrayList<Coordonees> coords = new ArrayList<Coordonees>();
		Coordonees test;
		Piece tmp = null;
		if (position.getX()-1 >= 0) {
			// A GAUCHE C'EST POSSIBLE
			if (isBlack) {
				if (position.getY()-1 >= 0) {
					// VERS LE BAS AUSSI
					test = new Coordonees((byte)(position.getX()-1),(byte)(position.getY()-1));
					tmp = p.getPiece(test);
					if (tmp != null) {
						coords.add(new Coordonees(test));
					}
				}
			} else {
				if (position.getY()+1 < p.getHeight()) {
					test = new Coordonees((byte)(position.getX()-1),(byte)(position.getY()+1));
					tmp = p.getPiece(test);
					if (tmp != null) {
						coords.add(new Coordonees(test));
					}
				}
			}
		}
		if (position.getX()+1 < p.getWidth()) {
			if (isBlack) {
				if (position.getY()-1 >= 0) {
					// VERS LE BAS AUSSI
					test = new Coordonees((byte)(position.getX()+1),(byte)(position.getY()-1));
					tmp = p.getPiece(test);
					if (tmp != null) {
						coords.add(new Coordonees(test));
					}
				}
			} else {
				if (position.getY()+1 < p.getHeight()) {
					test = new Coordonees((byte)(position.getX()+1),(byte)(position.getY()+1));
					tmp = p.getPiece(test);
					if (tmp != null) {
						coords.add(new Coordonees(test));
					}
				}
			}
		}
		return coords;
	}
}
