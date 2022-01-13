import java.util.ArrayList;

public class Piece_Pion extends Piece{
	private boolean aBouger;
	
	public Piece_Pion(Piece_Pion from){
		this(from.position,from.isBlack,from.isDead);
		aBouger = false;
	}
	
	public Piece_Pion(Piece from) {
		super(from);
		aBouger = false;
	}
	
	public Piece_Pion(Coordonees _position, boolean _estNoir, boolean _estMorte) {
		this(_position,_estNoir,_estMorte, false);
		aBouger = false;
		
	}
	
	@Override
	public void tuer() {
		super.tuer();
		System.out.println("Le pion en [" + position.getX() +  ";" + position.getY() + "] a été tué");
	}
	
	public Piece_Pion(Coordonees _position, boolean _estNoir) {
		this(_position,_estNoir, false, false);
	}
	
	
	public Piece_Pion(Coordonees _position, boolean _estNoir, boolean _estMorte, boolean _aBouger) {
		super(_position,_estNoir,_estMorte);
		aBouger = _aBouger;
		
	}
	

	public ArrayList<Coordonees> calculerMouvement(Plateau p){
		ArrayList<Coordonees> coords = new ArrayList<Coordonees>();
		
		return coords;
	}
	
	public ArrayList<Coordonees> calculerMouvementManger(Plateau p){
		ArrayList<Coordonees> coords = new ArrayList<Coordonees>();
		
		return coords;
	}
}
