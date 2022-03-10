package pieces;

import java.util.ArrayList;

import utils.Coordonees;
import utils.Mouvement;
import utils.Plateau;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class Piece_Cavalier extends Piece {
	private final static byte idType = 3;
	public Piece_Cavalier(Piece from) {
		super(from);
	}
	
	@Override
	public byte getTypeId() {
		return idType;
	}
	@Override
	public String toString() {
		return "[Cavalier " + position+"]";
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		return new Piece_Cavalier(this);
	}
	

	public Piece_Cavalier(Piece_Cavalier from) {
		this(from.position,from.isBlack);
	}
	
	public Piece_Cavalier(Coordonees _position, boolean _isBlack) {

		super(_position,_isBlack);
		pattern = new ImagePattern(image, 192, (isBlack ? 0:64), image.getWidth(), image.getHeight(), false);
		rect.setFill(pattern);
	}
	

	
	@Override
	public ArrayList<Mouvement> calculerMouvement(Plateau p) {
		ArrayList<Mouvement> mouvementsPossibles = new ArrayList<Mouvement>();
		Coordonees test;
		Piece tmp = null;
		if (position.getX()-2 >= 0) {
			if (position.getY()+2 < p.getHeight()) {
				test = new Coordonees((byte)(position.getX()-1),(byte)(position.getY()+2));
				tmp = p.getPieceFromCoord(test);
				if (tmp == null || tmp.estNoir() != isBlack) {
					mouvementsPossibles.add((new Mouvement(new Coordonees(this.position), new Coordonees(test),(tmp == null ? "move" : "eat"))));
				}					
				tmp = null;
				test = new Coordonees((byte)(position.getX()-2),(byte)(position.getY()+1));
				tmp = p.getPieceFromCoord(test);
				if (tmp == null || tmp.estNoir() != isBlack) {
					mouvementsPossibles.add(new Mouvement(new Coordonees(this.position), new Coordonees(test),(tmp == null ? "move" : "eat")));
				}
				tmp = null;
			} else if (position.getY()+1< p.getHeight()) {
				test = new Coordonees((byte)(position.getX()-2),(byte)(position.getY()+1));
				tmp = p.getPieceFromCoord(test);
				if (tmp == null || tmp.estNoir() != isBlack) {
					mouvementsPossibles.add(new Mouvement(new Coordonees(this.position), new Coordonees(test),(tmp == null ? "move" : "eat")));
				}
				tmp = null;
			}

			if (position.getY()-2>=0) {
				test = new Coordonees((byte)(position.getX()-1),(byte)(position.getY()-2));
				tmp = p.getPieceFromCoord(test);
				if (tmp == null || tmp.estNoir() != isBlack) {
					mouvementsPossibles.add(new Mouvement(new Coordonees(this.position), new Coordonees(test),(tmp == null ? "move" : "eat")));
				}					
				tmp = null;
				test = new Coordonees((byte)(position.getX()-2),(byte)(position.getY()-1));
				tmp = p.getPieceFromCoord(test);
				if (tmp == null || tmp.estNoir() != isBlack) {
					mouvementsPossibles.add(new Mouvement(new Coordonees(this.position), new Coordonees(test),(tmp == null ? "move" : "eat")));
				}
				tmp = null;
			} else if (position.getY()-1 >= 0) {
				test = new Coordonees((byte)(position.getX()-2),(byte)(position.getY()-1));
				tmp = p.getPieceFromCoord(test);
				if (tmp == null || tmp.estNoir() != isBlack) {
					mouvementsPossibles.add(new Mouvement(new Coordonees(this.position), new Coordonees(test),(tmp == null ? "move" : "eat")));
				}
				tmp = null;
			}
		} else if (position.getX()-1 >= 0) {
			if (position.getY()+2 < p.getHeight()) {
				test = new Coordonees((byte)(position.getX()-1),(byte)(position.getY()+2));
				tmp = p.getPieceFromCoord(test);
				if (tmp == null || tmp.estNoir() != isBlack) {
					mouvementsPossibles.add(new Mouvement(new Coordonees(this.position), new Coordonees(test),(tmp == null ? "move" : "eat")));
				}					
				tmp = null;
			}
			if (position.getY()-2>=0) {
				test = new Coordonees((byte)(position.getX()-1),(byte)(position.getY()-2));
				tmp = p.getPieceFromCoord(test);
				if (tmp == null || tmp.estNoir() != isBlack) {
					mouvementsPossibles.add(new Mouvement(new Coordonees(this.position), new Coordonees(test),(tmp == null ? "move" : "eat")));
				}					
				tmp = null;
			}
		}
		if (position.getX()+2<p.getWidth()) {
			if (position.getY()+2 < p.getHeight()) {
				test = new Coordonees((byte)(position.getX()+1),(byte)(position.getY()+2));
				tmp = p.getPieceFromCoord(test);
				if (tmp == null || tmp.estNoir() != isBlack) {
					mouvementsPossibles.add(new Mouvement(new Coordonees(this.position), new Coordonees(test),(tmp == null ? "move" : "eat")));
				}					
				tmp = null;
				test = new Coordonees((byte)(position.getX()+2),(byte)(position.getY()+1));
				tmp = p.getPieceFromCoord(test);
				if (tmp == null || tmp.estNoir() != isBlack) {
					mouvementsPossibles.add(new Mouvement(new Coordonees(this.position), new Coordonees(test),(tmp == null ? "move" : "eat")));
				}
				tmp = null;
			} else if (position.getY()+1< p.getHeight()) {
				test = new Coordonees((byte)(position.getX()+2),(byte)(position.getY()+1));
				tmp = p.getPieceFromCoord(test);
				if (tmp == null || tmp.estNoir() != isBlack) {
					mouvementsPossibles.add(new Mouvement(new Coordonees(this.position), new Coordonees(test),(tmp == null ? "move" : "eat")));
				}
				tmp = null;
			}

			if (position.getY()-2>=0) {
				test = new Coordonees((byte)(position.getX()+1),(byte)(position.getY()-2));
				tmp = p.getPieceFromCoord(test);
				if (tmp == null || tmp.estNoir() != isBlack) {
					mouvementsPossibles.add(new Mouvement(new Coordonees(this.position), new Coordonees(test),(tmp == null ? "move" : "eat")));
				}					
				tmp = null;
				test = new Coordonees((byte)(position.getX()+2),(byte)(position.getY()-1));
				tmp = p.getPieceFromCoord(test);
				if (tmp == null || tmp.estNoir() != isBlack) {
					mouvementsPossibles.add(new Mouvement(new Coordonees(this.position), new Coordonees(test),
							(tmp == null ? "move" : "eat")));
				}
				tmp = null;
			} else if (position.getY()-1 >= 0) {
				test = new Coordonees((byte)(position.getX()+2),(byte)(position.getY()-1));
				tmp = p.getPieceFromCoord(test);
				if (tmp == null || tmp.estNoir() != isBlack) {
					mouvementsPossibles.add(new Mouvement(new Coordonees(this.position), new Coordonees(test),(tmp == null ? "move" : "eat")));
				}
				tmp = null;
			} 
		} else if (position.getX()+1<p.getWidth()) {
			if (position.getY()+2 < p.getHeight()) {
				test = new Coordonees((byte)(position.getX()+1),(byte)(position.getY()+2));
				tmp = p.getPieceFromCoord(test);
				if (tmp == null || tmp.estNoir() != isBlack) {
					mouvementsPossibles.add(new Mouvement(new Coordonees(this.position), new Coordonees(test),(tmp == null ? "move" : "eat")));
				}					
				tmp = null;
			}
			if (position.getY()-2>=0) {
				test = new Coordonees((byte)(position.getX()+1),(byte)(position.getY()-2));
				tmp = p.getPieceFromCoord(test);
				if (tmp == null || tmp.estNoir() != isBlack) {
					mouvementsPossibles.add(new Mouvement(new Coordonees(this.position), new Coordonees(test),(tmp == null ? "move" : "eat")));
				}					
				tmp = null;
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
		gc.drawImage(image, 3*getImageWidth(), (isBlack ? getImageHeight():0), 64, 64,
				rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());

	}

}
