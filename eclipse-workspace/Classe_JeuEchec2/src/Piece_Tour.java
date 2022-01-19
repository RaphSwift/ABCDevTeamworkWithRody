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
	public boolean deplacer(Plateau p,Coordonees to) {
		int i = 0;
		boolean finded = false;
		ArrayList<Coordonees> coordsPossible = calculerMouvement(p);
		while(i < coordsPossible.size() && !finded) {
			if (to.equals(coordsPossible.get(i))) {
				finded = true;
			}
		}
		if (finded) {
			this.position = to;
			aBouge = true;
		}
		return finded;
	}
	


	@Override
	public ArrayList<Coordonees> calculerMouvement(Plateau p) {
		ArrayList<Coordonees> coords = new ArrayList<Coordonees>();
		Piece tmp=null;
		Coordonees coordoneesTestes = new Coordonees(position);
		// A GAUCHE
		while (tmp==null && coordoneesTestes.getX()>0) {
			coordoneesTestes = (new Coordonees((byte)(coordoneesTestes.getX()-1),coordoneesTestes.getY()));
			try {
				tmp = ((Piece)p.getPiece(coordoneesTestes).clone());
			} catch (Exception e) {
				tmp = null;
			}
			if (tmp != null) {
				if (tmp.estNoir() != isBlack) {
					coords.add(coordoneesTestes);
				}
			} else {
				coords.add(coordoneesTestes);
			}
			
		}
		tmp = null;
		coordoneesTestes = new Coordonees(position);
		// A DROITE
		while (tmp==null && coordoneesTestes.getX()+1 < p.getWidth()) {
			coordoneesTestes = (new Coordonees((byte)(coordoneesTestes.getX()+1),coordoneesTestes.getY()));
			try {
				tmp = ((Piece)p.getPiece(coordoneesTestes).clone());
			} catch (Exception e) {
				tmp = null;
			}
			if (tmp != null) {
				if (tmp.estNoir() != isBlack) {
					coords.add(coordoneesTestes);
				}
			} else {
				coords.add(coordoneesTestes);
			}
			
		}
		tmp = null;
		coordoneesTestes = new Coordonees(position);
		// EN HAUT
		while (tmp==null && coordoneesTestes.getY()+1 < p.getHeight()) {
			coordoneesTestes = (new Coordonees((byte)(coordoneesTestes.getX()),(byte)(coordoneesTestes.getY()+1)));
			try {
				tmp = ((Piece)p.getPiece(coordoneesTestes).clone());
			} catch (CloneNotSupportedException i) {
			}
			if (tmp != null) {
				if (tmp.estNoir() != isBlack) {
					coords.add(coordoneesTestes);
				}
			} else {
				coords.add(coordoneesTestes);
			}
			
		}
		tmp = null;
		coordoneesTestes = new Coordonees(position);
		// EN BAS
		while (tmp==null && coordoneesTestes.getY() > 0) {
			coordoneesTestes = (new Coordonees((byte)(coordoneesTestes.getX()),(byte)(coordoneesTestes.getY()-1)));
			try {
				tmp = ((Piece)p.getPiece(coordoneesTestes).clone());
			} catch (CloneNotSupportedException i) {
			}
			if (tmp != null) {
				if (tmp.estNoir() != isBlack) {
					coords.add(coordoneesTestes);
				}
			} else {
				coords.add(coordoneesTestes);
			}
			
		}
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
}
