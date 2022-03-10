package pieces;

import utils.Coordonees;
import utils.Mouvement;
import utils.Plateau;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import java.util.ArrayList;

public class Piece_Tour extends Piece {
	private boolean haveMoved;
	private final static int idType = 4;
	public Piece_Tour(Piece from) {
		super(from);
		haveMoved = false;
	}
	
	@Override
	public byte getTypeId() {
		return idType;
	}
	
	public Piece_Tour(Coordonees position, boolean _isBlack) {
		super(position,_isBlack);
		haveMoved = false;
		pattern = new ImagePattern(image, 256, (isBlack ? 0:64), image.getWidth(), image.getHeight(),false);
		rect.setFill(pattern);
	}

	public Piece_Tour(Piece_Tour from) {
		this(from.position,from.isBlack,from.haveMoved);
	}
	
	
	public boolean aBouge() {
		return haveMoved;
	}
	
	@Override
	public String toString() {
		return "[Tour " + position+"]";
	}
	
	@Override
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
		if (finded) {
			haveMoved=true;
		}
		return finded;
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		return new Piece_Tour(this);
	}
	
	@Override
	public boolean setCoord(Coordonees c, Plateau p){
		if (c.getX() >= 0 && c.getX() < p.getWidth() && c.getY() >= 0 && c.getY() < p.getHeight()) {
			position = c;
			haveMoved = true;
			rect = new Rectangle(position.getX()*getImageWidth(),position.getY() * getImageHeight(),getImageWidth(),
					getImageHeight());
			return true;
		}
		return false;
	}
	
	
	public Piece_Tour(Coordonees position, boolean _isBlack, boolean _haveMoved) {
		super(position,_isBlack);
		haveMoved = _haveMoved;
	}
	

	@Override
	public ArrayList<Mouvement> calculerMouvement(Plateau p){
		ArrayList<Mouvement> mouvementsPossibles = new ArrayList<Mouvement>();
		Piece tmp=null;
		Coordonees coordoneesTestes = new Coordonees(position);
		// A GAUCHE
		while (tmp==null && coordoneesTestes.getX()>0) {
			coordoneesTestes = (new Coordonees((byte)(coordoneesTestes.getX()-1),coordoneesTestes.getY()));
			tmp =p.getPieceFromCoord(coordoneesTestes);
			if (tmp != null) {
				if (tmp.estNoir() != isBlack) {
					mouvementsPossibles.add(new Mouvement(new Coordonees(this.position), new Coordonees(coordoneesTestes),"eat"));
				}
			} else {
				mouvementsPossibles.add(new Mouvement(new Coordonees(this.position), new Coordonees(coordoneesTestes)));
			}
			
		}
		tmp = null;
		coordoneesTestes = new Coordonees(position);
		// A DROITE
		while (tmp==null && coordoneesTestes.getX()+1 < p.getWidth()) {
			coordoneesTestes = (new Coordonees((byte)(coordoneesTestes.getX()+1),coordoneesTestes.getY()));
			tmp = p.getPieceFromCoord(coordoneesTestes);
			if (tmp != null) {
				if (tmp.estNoir() != isBlack) {
					mouvementsPossibles.add(new Mouvement(new Coordonees(this.position), new Coordonees(coordoneesTestes),"eat"));
				}
			} else {
				mouvementsPossibles.add(new Mouvement(new Coordonees(this.position), new Coordonees(coordoneesTestes)));
			}
			
		}
		tmp = null;
		coordoneesTestes = new Coordonees(position);
		// EN HAUT
		while (tmp==null && coordoneesTestes.getY()+1 < p.getHeight()) {
			coordoneesTestes = (new Coordonees((byte)(coordoneesTestes.getX()),(byte)(coordoneesTestes.getY()+1)));
			tmp = p.getPieceFromCoord(coordoneesTestes);
			if (tmp != null) {
				if (tmp.estNoir() != isBlack) {
					mouvementsPossibles.add(new Mouvement(new Coordonees(this.position), new Coordonees(coordoneesTestes),"eat"));
				}
			} else {
				mouvementsPossibles.add(new Mouvement(new Coordonees(this.position), new Coordonees(coordoneesTestes)));
			}
			
		}
		tmp = null;
		coordoneesTestes = new Coordonees(position);
		// EN BAS
		while (tmp==null && coordoneesTestes.getY() > 0) {
			coordoneesTestes = (new Coordonees((byte)(coordoneesTestes.getX()),(byte)(coordoneesTestes.getY()-1)));
			tmp = p.getPieceFromCoord(coordoneesTestes);
			if (tmp != null) {
				if (tmp.estNoir() != isBlack) {
					mouvementsPossibles.add(new Mouvement(new Coordonees(this.position), new Coordonees(coordoneesTestes),"eat"));
				}
			} else {
				mouvementsPossibles.add(new Mouvement(new Coordonees(this.position), new Coordonees(coordoneesTestes)));
			}
			
		}
		
		Plateau simulation;
		Piece tmpRoi;
		for (int i = mouvementsPossibles.size() -1 ; i >= 0; i--) {
			simulation = p.simulerMouvement(mouvementsPossibles.get(i));
			tmpRoi = simulation.getRoi(isBlack);
			if (tmpRoi.estEnEchec(simulation).size()>0) {
				mouvementsPossibles.remove(i);
			}
		}
		return mouvementsPossibles;
	}

	@Override
	public void dessiner(Canvas c){

		GraphicsContext gc = c.getGraphicsContext2D();
		gc.drawImage(image, 4*getImageWidth(), (isBlack ? getImageHeight():0), 64, 64,
				rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());

	}

}
