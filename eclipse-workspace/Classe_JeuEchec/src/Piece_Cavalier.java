import java.util.ArrayList;

public class Piece_Cavalier extends Piece{
	public Piece_Cavalier(Piece_Cavalier from){
		this(from.position,from.isBlack,from.isDead);
	}
	
	public Piece_Cavalier(Coordonees _position, boolean _estNoir, boolean _estMorte) {
		super(_position,_estNoir,_estMorte);		
	}
	
	
	public Piece_Cavalier(Piece from) {
		super(from);
	}
	
	public Piece_Cavalier(Coordonees _position, boolean _estNoir) {
		super(_position,_estNoir);		
	}
	

	
	public Object clone() throws CloneNotSupportedException {
		return new Piece_Cavalier(this);
	}

	@Override
	public ArrayList<Coordonees> calculerMouvement(Plateau p) {
		ArrayList<Coordonees> coords = new ArrayList<Coordonees>();
		Coordonees test;
		Piece tmp = null;
		if (position.getX()-2 >= 0) {
			if (position.getY()+2 < p.getHeight()) {
				test = new Coordonees((byte)(position.getX()-1),(byte)(position.getY()+2));
				tmp = p.getPiece(test);
				if (tmp.estNoir() != isBlack) {
					coords.add(new Coordonees(test));
				}					
				tmp = null;
				test = new Coordonees((byte)(position.getX()-2),(byte)(position.getY()+1));
				tmp = p.getPiece(test);
				if (tmp.estNoir() != isBlack) {
					coords.add(new Coordonees(test));
				}
				tmp = null;
			} else if (position.getY()+1< p.getHeight()) {
				test = new Coordonees((byte)(position.getX()-2),(byte)(position.getY()+1));
				tmp = p.getPiece(test);
				if (tmp.estNoir() != isBlack) {
					coords.add(new Coordonees(test));
				}
				tmp = null;
			}

			if (position.getY()-2>=0) {
				test = new Coordonees((byte)(position.getX()-1),(byte)(position.getY()-2));
				tmp = p.getPiece(test);
				if (tmp.estNoir() != isBlack) {
					coords.add(new Coordonees(test));
				}					
				tmp = null;
				test = new Coordonees((byte)(position.getX()-2),(byte)(position.getY()+1));
				tmp = p.getPiece(test);
				if (tmp.estNoir() != isBlack) {
					coords.add(new Coordonees(test));
				}
				tmp = null;
			} else if (position.getY()-1 >= 0) {
				test = new Coordonees((byte)(position.getX()-2),(byte)(position.getY()-1));
				tmp = p.getPiece(test);
				if (tmp.estNoir() != isBlack) {
					coords.add(new Coordonees(test));
				}
				tmp = null;
			}
		} else if (position.getX()-1 >= 0) {
			if (position.getY()+2 < p.getHeight()) {
				test = new Coordonees((byte)(position.getX()-1),(byte)(position.getY()+2));
				tmp = p.getPiece(test);
				if (tmp.estNoir() != isBlack) {
					coords.add(new Coordonees(test));
				}					
				tmp = null;
			}
			if (position.getY()-2>=0) {
				test = new Coordonees((byte)(position.getX()-1),(byte)(position.getY()-2));
				tmp = p.getPiece(test);
				if (tmp.estNoir() != isBlack) {
					coords.add(new Coordonees(test));
				}					
				tmp = null;
			}
		}
		if (position.getX()+2<p.getWidth()) {
			if (position.getY()+2 < p.getHeight()) {
				test = new Coordonees((byte)(position.getX()+1),(byte)(position.getY()+2));
				tmp = p.getPiece(test);
				if (tmp.estNoir() != isBlack) {
					coords.add(new Coordonees(test));
				}					
				tmp = null;
				test = new Coordonees((byte)(position.getX()+2),(byte)(position.getY()+1));
				tmp = p.getPiece(test);
				if (tmp.estNoir() != isBlack) {
					coords.add(new Coordonees(test));
				}
				tmp = null;
			} else if (position.getY()+1< p.getHeight()) {
				test = new Coordonees((byte)(position.getX()+2),(byte)(position.getY()+1));
				tmp = p.getPiece(test);
				if (tmp.estNoir() != isBlack) {
					coords.add(new Coordonees(test));
				}
				tmp = null;
			}

			if (position.getY()-2>=0) {
				test = new Coordonees((byte)(position.getX()+1),(byte)(position.getY()-2));
				tmp = p.getPiece(test);
				if (tmp.estNoir() != isBlack) {
					coords.add(new Coordonees(test));
				}					
				tmp = null;
				test = new Coordonees((byte)(position.getX()+2),(byte)(position.getY()+1));
				tmp = p.getPiece(test);
				if (tmp.estNoir() != isBlack) {
					coords.add(new Coordonees(test));
				}
				tmp = null;
			} else if (position.getY()-1 >= 0) {
				test = new Coordonees((byte)(position.getX()+2),(byte)(position.getY()-1));
				tmp = p.getPiece(test);
				if (tmp.estNoir() != isBlack) {
					coords.add(new Coordonees(test));
				}
				tmp = null;
			} else if (position.getX()+1<p.getWidth()) {
				if (position.getY()+2 < p.getHeight()) {
					test = new Coordonees((byte)(position.getX()+1),(byte)(position.getY()+2));
					tmp = p.getPiece(test);
					if (tmp.estNoir() != isBlack) {
						coords.add(new Coordonees(test));
					}					
					tmp = null;
				}
				if (position.getY()-2>=0) {
					test = new Coordonees((byte)(position.getX()+1),(byte)(position.getY()-2));
					tmp = p.getPiece(test);
					if (tmp.estNoir() != isBlack) {
						coords.add(new Coordonees(test));
					}					
					tmp = null;
				}
			}			
		}
		return coords;
	}
	
	@Override
	public void tuer() {
		super.tuer();
		System.out.println("Le fou en " + position + " a été tué");
	}
	

}
