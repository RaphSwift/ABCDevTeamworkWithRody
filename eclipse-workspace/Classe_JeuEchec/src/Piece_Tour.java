import java.util.ArrayList;

public class Piece_Tour extends Piece{
	private boolean aBouge;
	
	public Piece_Tour(Coordonees _position, boolean _estNoir, boolean _estMorte, boolean _aBouger) {
		super(_position,_estNoir,_estMorte);
		aBouge = _aBouger;
		
	}
	
	public Piece_Tour(Piece_Tour from){
		this(from.position,from.isBlack,from.isDead,from.aBouge);
	}
	
	public Piece_Tour(Coordonees _position, boolean _estNoir) {
		this(_position,_estNoir,false,false);		
	}
	
	public Piece_Tour(Coordonees _position, boolean _estNoir, boolean _estMorte) {
		this(_position,_estNoir,_estMorte, false);
		aBouge = false;
	}
	
	@Override
	public void tuer() {
		super.tuer();
		System.out.println("La tour en " + position + " a été tué");
	}
	
	
	public Piece_Tour(Piece from) {
		super(from);
		aBouge = false;
	}
	
	public Object clone() throws CloneNotSupportedException {
		return new Piece_Tour(this);
	}
	


	@Override
	public ArrayList<Coordonees> calculerMouvement(Plateau p) {
		ArrayList<Coordonees> coords = new ArrayList<Coordonees>();
		Piece tmp=null;
		Coordonees test = new Coordonees(position);
		// A GAUCHE
		while (tmp==null && test.getX()>0) {
			test = (new Coordonees((byte)(test.getX()-1),test.getY()));
			try {
				tmp = ((Piece)p.getPiece(test).clone());
			} catch (CloneNotSupportedException i) {
			}
			if (tmp != null) {
				if (tmp.estNoir() != isBlack) {
					coords.add(test);
				}
			} else {
				coords.add(test);
			}
			
		}
		tmp = null;
		test = new Coordonees(position);
		// A DROITE
		while (tmp==null && test.getX()+1 < p.getWidth()) {
			test = (new Coordonees((byte)(test.getX()+1),test.getY()));
			try {
				tmp = ((Piece)p.getPiece(test).clone());
			} catch (CloneNotSupportedException i) {
			}
			if (tmp != null) {
				if (tmp.estNoir() != isBlack) {
					coords.add(test);
				}
			} else {
				coords.add(test);
			}
			
		}
		tmp = null;
		test = new Coordonees(position);
		// EN HAUT
		while (tmp==null && test.getY()+1 < p.getHeight()) {
			test = (new Coordonees((byte)(test.getX()),(byte)(test.getY()+1)));
			try {
				tmp = ((Piece)p.getPiece(test).clone());
			} catch (CloneNotSupportedException i) {
			}
			if (tmp != null) {
				if (tmp.estNoir() != isBlack) {
					coords.add(test);
				}
			} else {
				coords.add(test);
			}
			
		}
		tmp = null;
		test = new Coordonees(position);
		// EN BAS
		while (tmp==null && test.getY() > 0) {
			test = (new Coordonees((byte)(test.getX()),(byte)(test.getY()-1)));
			try {
				tmp = ((Piece)p.getPiece(test).clone());
			} catch (CloneNotSupportedException i) {
			}
			if (tmp != null) {
				if (tmp.estNoir() != isBlack) {
					coords.add(test);
				}
			} else {
				coords.add(test);
			}
			
		}
		
		return coords;
	}
}
