package pieces;

import java.util.ArrayList;

import utils.Coordonees;
import utils.Mouvement;
import utils.Plateau;

public abstract class Piece implements java.io.Serializable, Cloneable{
	protected boolean isBlack;
	protected Coordonees position;
	
	public Piece(Piece from) {
		this(from.position, from.isBlack);
	}
	
	
	public abstract Object clone() throws CloneNotSupportedException;
	
	public Piece(Coordonees _position, boolean _isBlack) {
		position = _position;
		isBlack = _isBlack;
	}

	
	public boolean estNoir(){
		return isBlack;
	}
	
	
	public Coordonees getPosition() {
		return position;
	}
	
	public boolean setCoord(Coordonees c, Plateau p){
		if (c.getX() >= 0 && c.getX() < p.getWidth() && c.getY() >= 0 && c.getY() < p.getHeight()) {
			position = c;
			return true;
		}
		return false;
	}
	
	@Override
	public abstract String toString();
	
	public boolean deplacerPiece(Coordonees coord, Plateau plateau) {
		ArrayList<Mouvement> mvt = calculerMouvement(plateau);
		int i = 0;
		boolean finded = false;
		while (!finded && i < mvt.size()) {
			if (coord.equals(mvt.get(i).getTo())) {				
				finded = true;
			}
			i++;
		}
		return finded;
	}
	
	public abstract ArrayList<Mouvement> calculerMouvement(Plateau p);
	
	
	/*public ArrayList<Mouvement> estEnEchec(Plateau p) {
		ArrayList<Mouvement> mvtFinded = new ArrayList<Mouvement>();
		ArrayList<Piece> pieces = new ArrayList<Piece>();
		pieces = p.getPieceFromColor(!isBlack);
		

		ArrayList<Mouvement> mouvements = new ArrayList<Mouvement>();
		for (int i = 0; i < pieces.size();i++){
			if (pieces.get(i) instanceof Piece_Pion) {
				mouvements = ((Piece_Pion) (pieces.get(i))).calculerMouvementManger(p);
			} else {
				mouvements = pieces.get(i).calculerMouvement(p);
			}
			for (int j = 0; j < mouvements.size(); j++){
				if (mouvements.get(j).getTo().equals(this.position)) {
					mvtFinded.add(new Mouvement(new Coordonees(pieces.get(i).getPosition()), new Coordonees(this.position)));
				}
			}			
		}
		return mvtFinded;
	}*/
	
	
	public ArrayList<Mouvement> estEnEchec(Plateau p){
		ArrayList<Mouvement> misEnEchec = new ArrayList<Mouvement>();
		misEnEchec.addAll(testLines(p));
		misEnEchec.addAll(testDiagonales(p));
		misEnEchec.addAll(testLLines(p));
		misEnEchec.addAll(testerRoi(p));
		ArrayList<Coordonees> pionLines = new ArrayList<Coordonees>();
		
		// SI ON EST NOIR
		if (isBlack) {
			// LES BLANCS MANGENT VERS LE HAUT
			if (position.getY()-1 < p.getHeight()) {
				if (position.getX()-1 >= 0) {
					pionLines.add(new Coordonees((byte)(position.getX()-1),(byte)(position.getY()-1)));
				}
				if (position.getX()+1 < p.getWidth()) {
					pionLines.add(new Coordonees((byte)(position.getX()+1),(byte)(position.getY()-1)));
				}
			}
		} else {
			// LES NOIRS MANGENT VERS LE BAS
			if (position.getY()+1 >= 0) {
				if (position.getX()-1 >= 0) {
					pionLines.add(new Coordonees((byte)(position.getX()-1),(byte)(position.getY()+1)));
				}
				if (position.getX()+1 < p.getWidth()) {
					pionLines.add(new Coordonees((byte)(position.getX()+1),(byte)(position.getY()+1)));
				}
			}
		}
		Piece tmp;
		for (int i = 0; i < pionLines.size();i++) {
			tmp = p.getPieceFromCoord(pionLines.get(i));
			if (tmp != null && tmp instanceof Piece_Pion && tmp.estNoir() != isBlack) {
				misEnEchec.add(new Mouvement(new Coordonees(pionLines.get(i)), new Coordonees(position)));
			}
		}
		
		return misEnEchec;
	}
	
	private ArrayList<Mouvement> testLines(Plateau p){
		ArrayList<Mouvement> rt = new ArrayList<Mouvement>();

		Piece pieceTmp= null;
		int i = position.getX()-1;
		Coordonees coordTmp;
		while (i >= 0 && pieceTmp == null){
			// ligne gauche
			coordTmp = new Coordonees((byte)i,(byte)position.getY());
			pieceTmp = p.getPieceFromCoord(coordTmp);
			if (pieceTmp != null && pieceTmp.estNoir() != isBlack) {
				if (pieceTmp instanceof Piece_Reine || pieceTmp instanceof Piece_Tour) {
					rt.add(new Mouvement(new Coordonees(coordTmp), new Coordonees(position)));
				}
			}
			i--;
		
		}
		i = position.getX()+1;
		coordTmp = null;
		pieceTmp = null;
		while (i <p.getWidth() && pieceTmp == null){
			// ligne droite
			coordTmp = new Coordonees((byte)i,(byte)position.getY());
			pieceTmp = p.getPieceFromCoord(coordTmp);
			if (pieceTmp != null && pieceTmp.estNoir() != isBlack) {
				if (pieceTmp instanceof Piece_Reine || pieceTmp instanceof Piece_Tour) {
					rt.add(new Mouvement(new Coordonees(coordTmp), new Coordonees(position)));
				}
			}
			i++;
		
		}
		i = position.getY()+1;
		coordTmp = null;
		pieceTmp = null;
		while (i <p.getHeight() && pieceTmp == null){
			// ligne droite
			coordTmp = new Coordonees((byte)position.getX(),(byte)i);
			pieceTmp = p.getPieceFromCoord(coordTmp);
			if (pieceTmp != null && pieceTmp.estNoir() != isBlack) {
				if (pieceTmp instanceof Piece_Reine || pieceTmp instanceof Piece_Tour) {
					rt.add(new Mouvement(new Coordonees(coordTmp), new Coordonees(position)));
				}
			}
			i++;
		
		}
		i = position.getY()-1;
		coordTmp = null;
		pieceTmp = null;
		while (i >= 0 && pieceTmp == null){
			// ligne droite
			coordTmp = new Coordonees((byte)position.getX(),(byte)i);
			pieceTmp = p.getPieceFromCoord(coordTmp);
			if (pieceTmp != null && pieceTmp.estNoir() != isBlack) {
				if (pieceTmp instanceof Piece_Reine || pieceTmp instanceof Piece_Tour) {
					rt.add(new Mouvement(new Coordonees(coordTmp), new Coordonees(position)));
				}
			}
			i--;
		
		}
		return rt;
	}
	
	private ArrayList<Mouvement> testDiagonales(Plateau p){
		ArrayList<Mouvement> rt = new ArrayList<Mouvement>();
		
		Coordonees coordTest;
		Coordonees directionTeste = new Coordonees((byte)-1,(byte)1);
		int i=1;
		Piece tmp = null;
		// supperieur gauche
		coordTest = new Coordonees((byte)(position.getX()+directionTeste.getX()*i),(byte)(position.getY()+directionTeste.getY()*i));	
		while(coordTest.getX() >= 0 && coordTest.getY() < p.getHeight() && tmp == null) {
			tmp = p.getPieceFromCoord(coordTest);
			if (tmp != null && tmp.estNoir() != isBlack) {
				if (tmp instanceof Piece_Reine || tmp instanceof Piece_Fou) {
					rt.add(new Mouvement(new Coordonees(coordTest), new Coordonees(position)));
				}
			}
			i++;
			coordTest = new Coordonees((byte)(position.getX()+directionTeste.getX()*i),(byte)(position.getY()+directionTeste.getY()*i));
		}
		tmp = null;
		// supperieur droit
		directionTeste = new Coordonees((byte)1,(byte)1);
		i=1;
		coordTest = new Coordonees((byte)(position.getX()+directionTeste.getX()*i),(byte)(position.getY()+directionTeste.getY()*i));	
		while(coordTest.getX() >= 0 && coordTest.getY() < p.getHeight() && tmp==null) {
			tmp = p.getPieceFromCoord(coordTest);
			if (tmp != null && tmp.estNoir() != isBlack) {
				if (tmp instanceof Piece_Reine || tmp instanceof Piece_Fou) {
					rt.add(new Mouvement(new Coordonees(coordTest), new Coordonees(position)));
				}
			}
			i++;
			coordTest = new Coordonees((byte)(position.getX()+directionTeste.getX()*i),(byte)(position.getY()+directionTeste.getY()*i));
		}
		tmp =null;
		// inferieur droit
		directionTeste = new Coordonees((byte)1,(byte)-1);
		i=1;
		coordTest = new Coordonees((byte)(position.getX()+directionTeste.getX()*i),(byte)(position.getY()+directionTeste.getY()*i));	
		while(coordTest.getX() >= 0 && coordTest.getY() < p.getHeight() && tmp == null) {
			tmp = p.getPieceFromCoord(coordTest);
			if (tmp != null && tmp.estNoir() != isBlack) {
				if (tmp instanceof Piece_Reine || tmp instanceof Piece_Fou) {
					rt.add(new Mouvement(new Coordonees(coordTest), new Coordonees(position)));
				}
			}
			i++;
			coordTest = new Coordonees((byte)(position.getX()+directionTeste.getX()*i),(byte)(position.getY()+directionTeste.getY()*i));
		}
		tmp=null;
		// inferieur gauche
		directionTeste = new Coordonees((byte)-1,(byte)-1);
		i=1;
		coordTest = new Coordonees((byte)(position.getX()+directionTeste.getX()*i),(byte)(position.getY()+directionTeste.getY()*i));	
		while(coordTest.getX() >= 0 && coordTest.getY() < p.getHeight() && tmp == null) {
			tmp = p.getPieceFromCoord(coordTest);
			if (tmp != null && tmp.estNoir() != isBlack) {
				if (tmp instanceof Piece_Reine || tmp instanceof Piece_Fou) {
					rt.add(new Mouvement(new Coordonees(coordTest), new Coordonees(position)));
				}
			}
			i++;
			coordTest = new Coordonees((byte)(position.getX()+directionTeste.getX()*i),(byte)(position.getY()+directionTeste.getY()*i));
		}
		return rt;
	}
	
	private ArrayList<Mouvement> testLLines(Plateau p){
		ArrayList<Coordonees> coordATester = new ArrayList<Coordonees>();
		ArrayList<Mouvement> rt = new ArrayList<Mouvement>();
		if (position.getX()-2 >= 0) {
			if (position.getY()+2 < p.getHeight()) {
				coordATester.add(new Coordonees((byte)(position.getX()-2),(byte)(position.getY()+1)));
				coordATester.add(new Coordonees((byte)(position.getX()-1),(byte)(position.getY()+2)));
			} else if (position.getY() + 1 < p.getHeight()) {
				coordATester.add(new Coordonees((byte)(position.getX()-2),(byte)(position.getY()+1)));
			}
			if (position.getY()-2 >= 0) {
				coordATester.add(new Coordonees((byte)(position.getX()-2),(byte)(position.getY()-1)));
				coordATester.add(new Coordonees((byte)(position.getX()-1),(byte)(position.getY()-2)));
			} else if (position.getY()-1 >= 0) {
				coordATester.add(new Coordonees((byte)(position.getX()-2),(byte)(position.getY()-1)));
			}
		} else if (position.getX()-1 >= 0) {
			if (position.getY()+2 < p.getHeight()) {
				coordATester.add(new Coordonees((byte)(position.getX()-1),(byte)(position.getY()+2)));
			}
			if (position.getY()-2 >= 0) {
				coordATester.add(new Coordonees((byte)(position.getX()-1),(byte)(position.getY()-2)));
			}
		}
		
		if (position.getX()-+2 >= 0) {
			if (position.getY()+2 < p.getHeight()) {
				coordATester.add(new Coordonees((byte)(position.getX()+2),(byte)(position.getY()+1)));
				coordATester.add(new Coordonees((byte)(position.getX()+1),(byte)(position.getY()+2)));
			} else if (position.getY() + 1 < p.getHeight()) {
				coordATester.add(new Coordonees((byte)(position.getX()+2),(byte)(position.getY()+1)));
			}
			if (position.getY()-2 >= 0) {
				coordATester.add(new Coordonees((byte)(position.getX()+2),(byte)(position.getY()-1)));
				coordATester.add(new Coordonees((byte)(position.getX()+1),(byte)(position.getY()-2)));
			} else if (position.getY()-1 >= 0) {
				coordATester.add(new Coordonees((byte)(position.getX()+2),(byte)(position.getY()-1)));
			}
		} else if (position.getX()+1 >= 0) {
			if (position.getY()+2 < p.getHeight()) {
				coordATester.add(new Coordonees((byte)(position.getX()+1),(byte)(position.getY()+2)));
			}
			if (position.getY()-2 >= 0) {
				coordATester.add(new Coordonees((byte)(position.getX()+1),(byte)(position.getY()-2)));
			}
		}
		Piece tmp;
		for (int i = 0; i < coordATester.size(); i++) {
			tmp = p.getPieceFromCoord(coordATester.get(i));
			if (tmp != null && tmp instanceof Piece_Cavalier && tmp.estNoir() != isBlack) {
				rt.add(new Mouvement(new Coordonees(coordATester.get(i)), new Coordonees(position)));
			}
		}
		coordATester.clear();
		return rt;
	}
	
	private ArrayList<Mouvement> testerRoi(Plateau p){
		ArrayList<Mouvement> rt = new ArrayList<Mouvement>();
		Piece tmp = p.getRoi(!isBlack);
		if (tmp != null) {
			Coordonees dst = position.getDistanceFrom(tmp.getPosition());
			if (Math.abs(dst.getX()) <= 1 && Math.abs(dst.getY()) <=1) {
				rt.add(new Mouvement(new Coordonees(tmp.getPosition()), new Coordonees(position)));
			}
		}
		return rt;
		
	}
}
