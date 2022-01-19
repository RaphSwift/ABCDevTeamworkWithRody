import java.util.ArrayList;

public class Piece_Reine extends Piece{
	public Piece_Reine(Piece_Reine from){
		this(from.position,from.isBlack,from.isDead);
	}
	
	public Piece_Reine(Coordonees _position, boolean _estNoir, boolean _estMorte) {
		super(_position,_estNoir,_estMorte);		
	}
	
	public Piece_Reine(Coordonees _position, boolean _estNoir) {
		super(_position,_estNoir);		
	}
	
	
	public Piece_Reine(Piece from) {
		super(from);
	}
	

	
	public Object clone() throws CloneNotSupportedException {
		return new Piece_Reine(this);
	}
	

	@Override
	public ArrayList<Coordonees> calculerMouvement(Plateau p) {
		ArrayList<Coordonees> coords = new ArrayList<Coordonees>();
		coords.addAll(verifierDiagonales(p));
		coords.addAll(verifierLignes(p));
		Plateau tmpPlateau = null;
		Piece tmpRoi = null;
		for (int i = 0; i < coords.size(); i++) {
			tmpPlateau = (Plateau)p.clone();
			tmpPlateau.deplacerPiece(new Mouvement(this.position, coords.get(i)),isBlack);
			tmpRoi = (Piece_Roi)tmpPlateau.getRoi(isBlack);
			if (tmpRoi.estEnEchec(tmpPlateau).size() >0) {
				coords.remove(i);
			}
			
		}
		return coords;
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
	
	protected ArrayList<Coordonees> verifierLignes(Plateau p){
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
