import java.util.ArrayList;

public class Piece_Fou extends Piece{

	public Piece_Fou(Piece_Fou from){
		this(from.position,from.isBlack,from.isDead);
	}
	
	public Piece_Fou(Coordonees _position, boolean _estNoir, boolean _estMorte) {
		super(_position,_estNoir,_estMorte);		
	}
	
	public Piece_Fou(Coordonees _position, boolean _estNoir) {
		super(_position,_estNoir);		
	}
	
	
	public Piece_Fou(Piece from) {
		super(from);
	}
	

	
	public Object clone() throws CloneNotSupportedException {
		return new Piece_Fou(this);
	}
	

	@Override
	public ArrayList<Coordonees> calculerMouvement(Plateau p) {
		return verifierDiagonales(p);
	}
	
	@Override
	public void tuer() {
		super.tuer();
		System.out.println("Le fou en " + position + " a été tué");
	}
	
	protected ArrayList<Coordonees> verifierDiagonales(Plateau p){
		ArrayList<Coordonees> coords = new ArrayList<Coordonees>();
		Piece tmp=null;
		Coordonees test = new Coordonees(position);
		// SUPPERIEUR GAUCHE
		while (tmp==null && test.getX()>0 && test.getY()+1< p.getHeight()) {
			test = (new Coordonees((byte)(test.getX()-1),(byte)(test.getY()+1)));
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
		// SUPPERIEUR DROITE
		while (tmp==null && test.getX()+1 < p.getWidth() && test.getY()+1 < p.getHeight()) {
			test = (new Coordonees((byte)(test.getX()+1),(byte)(test.getY()+1)));
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
		// INFERIEUR GAUCHE
		while (tmp==null && test.getY()-1 >0 && test.getX() > 0) {
			test = (new Coordonees((byte)(test.getX()-1),(byte)(test.getY()-1)));
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
		// INFERIEUR DROITE
		while (tmp==null && test.getY() > 0 && test.getX()+1<p.getWidth()) {
			test = (new Coordonees((byte)(test.getX()+1),(byte)(test.getY()-1)));
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
