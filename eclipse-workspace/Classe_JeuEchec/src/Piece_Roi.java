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
		Plateau tmp = null;
		ArrayList<Coordonees> coords = new ArrayList<Coordonees>();
		Coordonees tmpCoords = null;
		if (position.getY()-1>=0) {
			tmpCoords = new Coordonees((byte)(position.getX()),(byte)(position.getY()-1));
			tmp = (Plateau)p.clone();
			tmp.deplacerPiece(new Mouvement(this.position,tmpCoords), isBlack);
			if (tmp.getPiece(tmpCoords) != null && tmp.getPiece(tmpCoords).estEnEchec(p).size() == 0) {
				coords.add(new Coordonees(tmpCoords));
			}
		}
		if (position.getY()+1< p.getHeight()) {
			tmpCoords =new Coordonees((byte)(position.getX()),(byte)(position.getY()+1));
			tmp = (Plateau)p.clone();
			tmp.deplacerPiece(new Mouvement(this.position,tmpCoords), isBlack);
			if (tmp.getPiece(tmpCoords) != null && tmp.getPiece(tmpCoords).estEnEchec(p).size() == 0) {
				coords.add(new Coordonees(tmpCoords));
			}
		}
		if (position.getX()-1 >= 0) {
			tmpCoords =new Coordonees((byte)(position.getX()-1),(byte)(position.getY()));
			tmp = (Plateau)p.clone();
			tmp.deplacerPiece(new Mouvement(this.position,tmpCoords), isBlack);
			if (tmp.getPiece(tmpCoords) != null && tmp.getPiece(tmpCoords).estEnEchec(p).size() == 0) {
				coords.add(new Coordonees(tmpCoords));
			}
			if (position.getY()-1>=0) {
				tmpCoords =new Coordonees((byte)(position.getX()-1),(byte)(position.getY()-1));
				tmp = (Plateau)p.clone();
				tmp.deplacerPiece(new Mouvement(this.position,tmpCoords), isBlack);
				if (tmp.getPiece(tmpCoords) != null && tmp.getPiece(tmpCoords).estEnEchec(p).size() == 0) {
					coords.add(new Coordonees(tmpCoords));
				}
			}
			if (position.getY()+1< p.getHeight()) {
				tmpCoords =new Coordonees((byte)(position.getX()-1),(byte)(position.getY()+1));
				tmp = (Plateau)p.clone();
				tmp.deplacerPiece(new Mouvement(this.position,tmpCoords), isBlack);
				if (tmp.getPiece(tmpCoords) != null && tmp.getPiece(tmpCoords).estEnEchec(p).size() == 0) {
					coords.add(new Coordonees(tmpCoords));
				}
			}
		} else if (position.getX()+1 < p.getWidth()){
			tmpCoords =new Coordonees((byte)(position.getX()+1),(byte)(position.getY()));
			tmp = (Plateau)p.clone();
			tmp.deplacerPiece(new Mouvement(this.position,tmpCoords), isBlack);
			if (tmp.getPiece(tmpCoords) != null && tmp.getPiece(tmpCoords).estEnEchec(p).size() == 0) {
				coords.add(new Coordonees(tmpCoords));
			}
			if (position.getY()-1>=0) {
				tmpCoords =new Coordonees((byte)(position.getX()+1),(byte)(position.getY()-1));
				tmp = (Plateau)p.clone();
				tmp.deplacerPiece(new Mouvement(this.position,tmpCoords), isBlack);
				if (tmp.getPiece(tmpCoords) != null && tmp.getPiece(tmpCoords).estEnEchec(p).size() == 0) {
					coords.add(new Coordonees(tmpCoords));
				}
			}
			if (position.getY()+1< p.getHeight()) {
				tmpCoords = new Coordonees((byte)(position.getX()+1),(byte)(position.getY()+1));
				tmp = (Plateau)p.clone();
				tmp.deplacerPiece(new Mouvement(this.position,tmpCoords), isBlack);
				if (tmp.getPiece(tmpCoords) != null && tmp.getPiece(tmpCoords).estEnEchec(p).size() == 0) {
					coords.add(new Coordonees(tmpCoords));
				}
			}
		}
		return coords;
	}
	
	public boolean estEchecEtMat(Plateau p) {
		// SOUS QUEL CAS LA PIECE EST EN ECHEC ET MAT
		ArrayList<Mouvement> misEnEchec = estEnEchec(p);
		if (misEnEchec.size() == 0) {
			return false;
		} else {
			if (misEnEchec.size() == 1) {
				// PEUT-ON MANGER LA PIECE QUI PLACE LE ROI EN ECHEC
				Piece tmp = p.getPiece(misEnEchec.get(0).getFrom());
				if (tmp != null) {
					if (tmp.estEnEchec(p).size() >0) {
						return false;
					}
				}
			} else {
				// UN DEPLACEMENT DU ROI N'EST PAS EN ECHEC
				ArrayList<Coordonees> mvt = calculerMouvement(p);
				if (mvt.size() > 0) {
					return false;
				}
				// LE SACRIFICE D'UNE PIECE PEUT IL MODIFIER CELA
				return true;
			}
		}
		return false;
		
	}
	
	
}
