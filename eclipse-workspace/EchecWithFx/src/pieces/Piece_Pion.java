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

public class Piece_Pion extends Piece{
	private boolean haveMoved;
	private final static int idType = 5;
	
	public Piece_Pion(Piece from) {
		super(from);
		haveMoved = false;
	}
	
	@Override
	public byte getTypeId() {
		return idType;
	}
	
	
	public boolean aBouge() {
		return haveMoved;
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException{
		return new Piece_Pion(this);
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
	
	@Override
	public String toString() {
		return "[Pion " + position+"]";
	}
	
	public Piece_Pion(Coordonees position, boolean _isBlack, boolean _haveMoved) {
		super(position,_isBlack);
		haveMoved = _haveMoved;
		pattern = new ImagePattern(image, 320, (isBlack ? 0:64), image.getWidth(), image.getHeight(),false);
		rect.setFill(pattern);
	}
	

	public Piece_Pion(Piece_Pion from) {
		this(from.position,from.isBlack,from.haveMoved);
	}
	
	public Piece_Pion(Coordonees _position, boolean _isBlack) {
		this(_position,_isBlack,false);
	}
	
	@Override
	public boolean deplacerPiece(Coordonees coord, Plateau plateau) {
		Piece tmp = plateau.getPieceFromCoord(coord);
		ArrayList<Mouvement> mvt = new ArrayList<Mouvement>();
		if (tmp != null && tmp.estNoir() != isBlack) {
			mvt = calculerMouvementManger(plateau);
		} else {
			mvt = calculerMouvement(plateau);
		}
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
	public ArrayList<Mouvement> calculerMouvement(Plateau p){
		ArrayList<Mouvement> mouvementsPossibles = new ArrayList<Mouvement>();
		Coordonees test;
		Coordonees test2;

		Piece tmp = null;
		Piece tmp2 = null;
		if (isBlack) {
			if (!haveMoved) {
				if (position.getY()+2 >= 0) {
					test = new Coordonees((byte)(position.getX()),(byte)(position.getY()+1));
					tmp = p.getPieceFromCoord(test);
					test2 = new Coordonees((byte)(position.getX()),(byte)(position.getY()+2));
					tmp2 = p.getPieceFromCoord(test2);
					if (tmp == null) {
						mouvementsPossibles.add(new Mouvement(new Coordonees(this.position), new Coordonees(test)));
						if (tmp2 == null) {
							mouvementsPossibles.add(new Mouvement(new Coordonees(this.position), new Coordonees(test2)));
						}
					}
				} else if (position.getY()+1 >= 0) {
					test = new Coordonees((byte)(position.getX()),(byte)(position.getY()+1));
					tmp = p.getPieceFromCoord(test);
					if (tmp == null) {
						mouvementsPossibles.add(new Mouvement(new Coordonees(this.position), new Coordonees(test)));
					}
				}
			} else {
				if (position.getY()+1 >= 0) {
					test = new Coordonees((byte)(position.getX()),(byte)(position.getY()+1));
					tmp = p.getPieceFromCoord(test);
					if (tmp == null) {
						mouvementsPossibles.add(new Mouvement(new Coordonees(this.position), new Coordonees(test)));
					}
				}
			}
		} else {
			//Y +2
			if (!haveMoved) {
				if (position.getY()-2 < p.getHeight()) {
					test = new Coordonees((byte)(position.getX()),(byte)(position.getY()-1));
					tmp = p.getPieceFromCoord(test);
					test2 = new Coordonees((byte)(position.getX()),(byte)(position.getY()-2));
					tmp2 = p.getPieceFromCoord(test2);
					if (tmp == null) {
						mouvementsPossibles.add(new Mouvement(new Coordonees(this.position), new Coordonees(test)));
						if (tmp2 == null) {
							mouvementsPossibles.add(new Mouvement(new Coordonees(this.position), new Coordonees(test2)));
						}
					}
				} else if (position.getY()-1 < p.getHeight()) {
					test = new Coordonees((byte)(position.getX()),(byte)(position.getY()-1));
					tmp = p.getPieceFromCoord(test);
					if (tmp == null) {
						mouvementsPossibles.add(new Mouvement(new Coordonees(this.position), new Coordonees(test)));
					}
				}
			} else {
				if (position.getY()-1 < p.getHeight()) {
					test = new Coordonees((byte)(position.getX()),(byte)(position.getY()-1));
					tmp = p.getPieceFromCoord(test);
					if (tmp == null) {
						mouvementsPossibles.add(new Mouvement(new Coordonees(this.position), new Coordonees(test)));
					}
				}
			}
			
		}
		Plateau simulation;
		Piece tmpRoi;
		ArrayList<Mouvement> roiMv = new ArrayList<Mouvement>();
		for (int i = mouvementsPossibles.size() -1 ; i >= 0; i--) {
			simulation = p.simulerMouvement(mouvementsPossibles.get(i));
			if (simulation != null) {
				tmpRoi = simulation.getRoi(isBlack);
				roiMv = tmpRoi.estEnEchec(simulation);
				
				if (roiMv.size()>0) {
					
					mouvementsPossibles.remove(i);
				}
				roiMv.clear();
			}
		}
		return mouvementsPossibles;
	}
	
	public ArrayList<Mouvement> calculerMouvementManger(Plateau p){
		ArrayList<Mouvement> mouvementsPossibles = new ArrayList<Mouvement>();
		Coordonees test;
		Piece tmp = null;
		if (position.getX()-1 >= 0) {
			// A GAUCHE C'EST POSSIBLE
			if (isBlack) {
				if (position.getY()+1 >= 0) {
					// VERS LE BAS SI NOIR AUSSI
					test = new Coordonees((byte)(position.getX()-1),(byte)(position.getY()+1));
					tmp = p.getPieceFromCoord(test);
					if (tmp != null && tmp.estNoir() != estNoir()) {
						mouvementsPossibles.add(new Mouvement(new Coordonees(this.position), new Coordonees(test), "eat"));
					}
				}
			} else {
				// VERS LE HAUT SI BLANC
				if (position.getY()-1 < p.getHeight()) {
					test = new Coordonees((byte)(position.getX()-1),(byte)(position.getY()-1));
					tmp = p.getPieceFromCoord(test);
					if (tmp != null && tmp.estNoir() != estNoir()) {
						mouvementsPossibles.add(new Mouvement(new Coordonees(this.position), new Coordonees(test),"eat"));
					}
				}
			}
		}
		if (position.getX()+1 < p.getWidth()) {
			if (isBlack) {
				if (position.getY()+1 >= 0) {
					// VERS LE BAS SI NOIR AUSSI
					test = new Coordonees((byte)(position.getX()+1),(byte)(position.getY()+1));
					tmp = p.getPieceFromCoord(test);
					if (tmp != null && tmp.estNoir() != isBlack) {
						mouvementsPossibles.add(new Mouvement(new Coordonees(this.position), new Coordonees(test),"eat"));
					}
				}
			} else {
				if (position.getY()-1 < p.getHeight()) {
					// VERS LE HAUT SI BLANCE
					test = new Coordonees((byte)(position.getX()+1),(byte)(position.getY()-1));
					tmp = p.getPieceFromCoord(test);
					if (tmp != null && tmp.estNoir() != isBlack) {
						mouvementsPossibles.add(new Mouvement(new Coordonees(this.position), new Coordonees(test),"eat"));
					}
				}
			}
		}

		Plateau plateauSimule = null;
		Piece roi = null;
		for (int i = mouvementsPossibles.size()-1; i >= 0;i--) {
			plateauSimule = p.simulerMouvement(mouvementsPossibles.get(i));
			if (plateauSimule != null) {
				roi = plateauSimule.getRoi(isBlack);
				if (roi.estEnEchec(plateauSimule).size() > 0) {
					mouvementsPossibles.remove(i);
				}
			}
		}
		
		return mouvementsPossibles;
	}

	@Override
	public void dessiner(Canvas c){

		GraphicsContext gc = c.getGraphicsContext2D();
		gc.drawImage(image, 5*getImageWidth(), (isBlack ? getImageHeight():0), 64, 64,
				rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());
	}

}
