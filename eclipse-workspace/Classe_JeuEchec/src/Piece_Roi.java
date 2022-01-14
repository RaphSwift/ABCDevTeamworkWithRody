import java.util.ArrayList;

public class Piece_Roi extends Piece{
	private boolean aBouge;
	
	public Piece_Roi(Piece_Roi from){
		this(from.position,from.isBlack,from.isDead,from.aBouge);
	}
	
	public Piece_Roi(Coordonees _position, boolean _estNoir, boolean _estMorte, boolean _aBouger) {
		super(_position,_estNoir,_estMorte);
		aBouge = _aBouger;
		
	}
	
	public Piece_Roi(Piece from) {
		super(from);
		aBouge = false;
	}
	
	public Piece_Roi(Coordonees _position, boolean _estNoir, boolean _estMorte) {
		this(_position,_estNoir,_estMorte, false);
	}
	
	public Piece_Roi(Coordonees _position, boolean _estNoir) {
		this(_position,_estNoir,false,false);
	}
	
	public Object clone() throws CloneNotSupportedException {
		return new Piece_Roi(this);
	}
	

	@Override
	public ArrayList<Coordonees> calculerMouvement(Plateau p) {
		ArrayList<Coordonees> coords = new ArrayList<Coordonees>();
		if (position.getY()-1>=0) {
			coords.add(new Coordonees((byte)(position.getX()),(byte)(position.getY()-1)));
		}
		if (position.getY()+1< p.getHeight()) {
			coords.add(new Coordonees((byte)(position.getX()),(byte)(position.getY()+1)));
		}
		if (position.getX()-1 >= 0) {
			coords.add(new Coordonees((byte)(position.getX()-1),(byte)(position.getY())));
			if (position.getY()-1>=0) {
				coords.add(new Coordonees((byte)(position.getX()-1),(byte)(position.getY()-1)));
			}
			if (position.getY()+1< p.getHeight()) {
				coords.add(new Coordonees((byte)(position.getX()-1),(byte)(position.getY()+1)));
			}
		} else if (position.getX()+1 < p.getWidth()){
			coords.add(new Coordonees((byte)(position.getX()+1),(byte)(position.getY())));
			if (position.getY()-1>=0) {
				coords.add(new Coordonees((byte)(position.getX()+1),(byte)(position.getY()-1)));
			}
			if (position.getY()+1< p.getHeight()) {
				coords.add(new Coordonees((byte)(position.getX()+1),(byte)(position.getY()+1)));
			}
		}
		return coords;
	}
	
	
}
