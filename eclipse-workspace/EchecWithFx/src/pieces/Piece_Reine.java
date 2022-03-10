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

public class Piece_Reine extends Piece{
	
	private final static int idType = 1;
	
	public Piece_Reine(Piece from) {
		super(from);
	}
	
	@Override
	public byte getTypeId() {
		return idType;
	}
	
	@Override
	public String toString() {
		return "[Reine " + position+"]";
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		return new Piece_Reine(this);
	}
	
	public Piece_Reine(Coordonees position, boolean _isBlack) {

		super(position,_isBlack);
		pattern = new ImagePattern(image, 64, (isBlack ? 0:64), image.getWidth(), image.getHeight(),false);
		rect.setFill(pattern);
	}

	public Piece_Reine(Piece_Reine from) {
		this(from.position,from.isBlack);
	}
	
	
	@Override
	public ArrayList<Mouvement> calculerMouvement(Plateau p){
		ArrayList<Mouvement> mouvementsPossibles = new ArrayList<Mouvement>();
		mouvementsPossibles.addAll(calculerLignes(p));
		mouvementsPossibles.addAll(calculerDiagonales(p));

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
	

	private ArrayList<Mouvement> calculerDiagonales(Plateau p){
		ArrayList<Mouvement> mouvementsPossibles = new ArrayList<Mouvement>();
		Piece tmp=null;
		Coordonees test = new Coordonees(position);
		// SUPPERIEUR DROITE
		while (tmp==null && test.getX()>0 && test.getY()+1< p.getHeight()) {
			test = (new Coordonees((byte)(test.getX()-1),(byte)(test.getY()+1)));
			try {
				tmp = ((Piece)p.getPieceFromCoord(test).clone());
			} catch (Exception e) {
				tmp = null;
			}

			if (tmp != null) {
				if (tmp.estNoir() != isBlack) {
					mouvementsPossibles.add(new Mouvement(new Coordonees(this.position), new Coordonees(test),"eat"));
				}
			} else {
				mouvementsPossibles.add(new Mouvement(new Coordonees(this.position), new Coordonees(test)));
			}
			
		}
		tmp = null;
		test = new Coordonees(position);
		// SUPPERIEUR GAUCHE
		while (tmp==null && test.getX()+1 < p.getWidth() && test.getY()+1 < p.getHeight()) {
			test = (new Coordonees((byte)(test.getX()+1),(byte)(test.getY()+1)));
			try {
				tmp = ((Piece)p.getPieceFromCoord(test).clone());
			} catch (Exception e) {
				tmp = null;
			}
			if (tmp != null) {
				if (tmp.estNoir() != isBlack) {
					mouvementsPossibles.add(new Mouvement(new Coordonees(this.position), new Coordonees(test),"eat"));
				}
			} else {
				mouvementsPossibles.add(new Mouvement(new Coordonees(this.position), new Coordonees(test)));
			}
			
		}
		tmp = null;
		test = new Coordonees(position);
		// INFERIEUR GAUCHE
		while (tmp==null && test.getY() >0 && test.getX() > 0) {
			test = (new Coordonees((byte)(test.getX()-1),(byte)(test.getY()-1)));
			try {
				tmp = ((Piece)p.getPieceFromCoord(test).clone());
			} catch (Exception e) {
				tmp = null;
			}
			if (tmp != null) {
				if (tmp.estNoir() != isBlack) {
					mouvementsPossibles.add(new Mouvement(new Coordonees(this.position), new Coordonees(test),"eat"));
				}
			} else {
				mouvementsPossibles.add(new Mouvement(new Coordonees(this.position), new Coordonees(test)));
			}
			
		}
		tmp = null;
		test = new Coordonees(position);
		// INFERIEUR DROITE
		while (tmp==null && test.getY() > 0 && test.getX()+1<p.getWidth()) {
			test = (new Coordonees((byte)(test.getX()+1),(byte)(test.getY()-1)));
			try {
				tmp = ((Piece)p.getPieceFromCoord(test).clone());
			} catch (Exception e) {
				tmp = null;
			}
			if (tmp != null) {
				if (tmp.estNoir() != isBlack) {
					mouvementsPossibles.add(new Mouvement(new Coordonees(this.position), new Coordonees(test),"eat"));
				}
			} else {
				mouvementsPossibles.add(new Mouvement(new Coordonees(this.position), new Coordonees(test)));
			}
			
		}
		return mouvementsPossibles;
	}
	
	private ArrayList<Mouvement> calculerLignes(Plateau p){
		ArrayList<Mouvement> mouvementsPossibles = new ArrayList<Mouvement>();
		Piece tmp=null;
		Coordonees coordoneesTestes = new Coordonees(position);
		// A GAUCHE
		while (tmp==null && coordoneesTestes.getX()>0) {
			coordoneesTestes = (new Coordonees((byte)(coordoneesTestes.getX()-1),coordoneesTestes.getY()));
			try {
				tmp = ((Piece)p.getPieceFromCoord(coordoneesTestes).clone());
			} catch (Exception e) {
				tmp = null;
			}
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
			try {
				tmp = ((Piece)p.getPieceFromCoord(coordoneesTestes).clone());
			} catch (Exception e) {
				tmp = null;
			}
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
			try {
				tmp = ((Piece)p.getPieceFromCoord(coordoneesTestes).clone());
			} catch (Exception i) {
			}
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
			try {
				tmp = ((Piece)p.getPieceFromCoord(coordoneesTestes).clone());
			} catch (Exception i) {
			}
			if (tmp != null) {
				if (tmp.estNoir() != isBlack) {
					mouvementsPossibles.add(new Mouvement(new Coordonees(this.position), new Coordonees(coordoneesTestes),"eat"));
				}
			} else {
				mouvementsPossibles.add(new Mouvement(new Coordonees(this.position), new Coordonees(coordoneesTestes)));
			}
			
		}
		return mouvementsPossibles;
	}

	@Override
	public void dessiner(Canvas c){

		GraphicsContext gc = c.getGraphicsContext2D();
		gc.drawImage(image, 1*getImageWidth(), (isBlack ? getImageHeight():0), 64, 64,
				rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());

	}

}
