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

public class Piece_Roi extends Piece{
	private boolean haveMoved;
	private final static int idType = 0;
	public Piece_Roi(Piece from) {
		super(from);
		haveMoved = false;
	}
	
	@Override
	public byte getTypeId() {
		return idType;
	}
	
	@Override
	public String toString() {
		return "[Roi " + position+"]";
	}
	
	public boolean aBouge() {
		return haveMoved;
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
	public Object clone() throws CloneNotSupportedException{
		return new Piece_Roi(this);
	}
	
	public Piece_Roi(Coordonees position, boolean _isBlack, boolean _haveMoved) {
		super(position,_isBlack);
		haveMoved = _haveMoved;
		pattern = new ImagePattern(image, 0, (isBlack ? 0:64), image.getWidth(), image.getHeight(),false);
		rect.setFill(pattern);
	}
	

	public Piece_Roi(Piece_Roi from) {
		this(from.position,from.isBlack,from.haveMoved);
	}
	
	public Piece_Roi(Coordonees _position, boolean _isBlack) {
		this(_position,_isBlack,false);
	}
	
	private ArrayList<Mouvement> calculerMouvement(Plateau p, boolean simuler){
		ArrayList<Mouvement >mouvementsPossibles = new ArrayList<Mouvement>();
		Coordonees tmpCoords = null;
		Piece tmp = null;
		if (position.getY()>0) {
			tmpCoords = new Coordonees((byte)(position.getX()),(byte)(position.getY()-1));
			tmp = p.getPieceFromCoord(tmpCoords);
			if (tmp ==null || tmp.estNoir() != isBlack) {
				mouvementsPossibles.add(new Mouvement(new Coordonees(this.position), new Coordonees(tmpCoords),(tmp == null ? "move" : "eat")));
				tmp = null;
			}
		}
		if (position.getY()+1< p.getHeight()) {
			tmpCoords =new Coordonees((byte)(position.getX()),(byte)(position.getY()+1));
			tmp = p.getPieceFromCoord(tmpCoords);
			if (tmp ==null || tmp.estNoir() != isBlack) {
				mouvementsPossibles.add(new Mouvement(new Coordonees(this.position),new Coordonees(tmpCoords),(tmp == null ? "move" : "eat")));
				tmp = null;
			}
		}
		if (position.getX() > 0) {
			tmpCoords =new Coordonees((byte)(position.getX()-1),(byte)(position.getY()));
			tmp = p.getPieceFromCoord(tmpCoords);
			if (tmp ==null || tmp.estNoir() != isBlack) {
				mouvementsPossibles.add(new Mouvement(new Coordonees(this.position), new Coordonees(tmpCoords),(tmp == null ? "move" : "eat")));
				tmp = null;
			}
			if (position.getY()-1>=0) {
				tmpCoords =new Coordonees((byte)(position.getX()-1),(byte)(position.getY()-1));
				tmp = p.getPieceFromCoord(tmpCoords);
				if (tmp ==null || tmp.estNoir() != isBlack) {
					mouvementsPossibles.add(new Mouvement(new Coordonees(this.position),new Coordonees(tmpCoords),(tmp == null ? "move" : "eat")));
					tmp = null;
				}
			}
			if (position.getY()+1< p.getHeight()) {
				tmpCoords =new Coordonees((byte)(position.getX()-1),(byte)(position.getY()+1));
				tmp = p.getPieceFromCoord(tmpCoords);
				if (tmp ==null || tmp.estNoir() != isBlack) {
					mouvementsPossibles.add(new Mouvement(new Coordonees(this.position), new Coordonees(tmpCoords),(tmp == null ? "move" : "eat")));
					tmp = null;
				}
			}
		} 
		if (position.getX()+1 < p.getWidth()){
			tmpCoords =new Coordonees((byte)(position.getX()+1),(byte)(position.getY()));
			tmp = p.getPieceFromCoord(tmpCoords);
			if (tmp ==null || tmp.estNoir() != isBlack) {
				mouvementsPossibles.add(new Mouvement(new Coordonees(this.position), new Coordonees(tmpCoords),(tmp == null ? "move" : "eat")));
				tmp = null;
			}
			if (position.getY()-1>=0) {
				tmpCoords =new Coordonees((byte)(position.getX()+1),(byte)(position.getY()-1));
				tmp = p.getPieceFromCoord(tmpCoords);
				if (tmp ==null || tmp.estNoir() != isBlack) {
					mouvementsPossibles.add(new Mouvement(new Coordonees(this.position), new Coordonees(tmpCoords),(tmp == null ? "move" : "eat")));
					tmp = null;
				}
			}
			if (position.getY()+1< p.getHeight()) {
				tmpCoords = new Coordonees((byte)(position.getX()+1),(byte)(position.getY()+1));
				tmp = p.getPieceFromCoord(tmpCoords);
				if (tmp ==null || tmp.estNoir() != isBlack) {
					mouvementsPossibles.add(new Mouvement(new Coordonees(this.position),new Coordonees(tmpCoords),(tmp == null ? "move" : "eat")));
					tmp = null;
				}
			}
		}

		Plateau simulation;
		Piece_Roi tmpRoi;
		for (int i = mouvementsPossibles.size() -1 ; i >= 0; i--) {
			simulation = p.simulerMouvement(mouvementsPossibles.get(i));
			tmpRoi = (Piece_Roi)simulation.getRoi(isBlack);
			if (tmpRoi.estEnEchec(simulation).size()>0) {
				mouvementsPossibles.remove(i);
			} else {
				if (simuler) {
					if ((tmpRoi.calculerMouvement(simulation,false).size() == 0 && simulation.getPieceFromColor(isBlack).size() == 1))
						mouvementsPossibles.remove(i);
				}
			}
		}
		/* POUR LE ROQUE*/
		if (!haveMoved) {
			if (isBlack) {
				simulation = p.simulerRoque(isBlack, new Coordonees((byte)0,(byte)0));
				if (simulation != null) {
					if(simulation.getRoi(isBlack).estEnEchec(simulation).size()==0) {
						mouvementsPossibles.add(new Mouvement(new Coordonees(this.position),
								new Coordonees((byte)0,(byte)0),"roque"));
					}
				}
				simulation = p.simulerRoque(isBlack, new Coordonees((byte)7,(byte)0));
				if (simulation != null) {
					if(simulation.getRoi(isBlack).estEnEchec(simulation).size()==0) {
						mouvementsPossibles.add(new Mouvement(new Coordonees(this.position),
								new Coordonees((byte)7,(byte)0),"roque"));
					}
				}
			} else {
				simulation = p.simulerRoque(isBlack, new Coordonees((byte)0,(byte)7));
				if (simulation != null) {
					if(simulation.getRoi(isBlack).estEnEchec(simulation).size()==0) {
						mouvementsPossibles.add(new Mouvement(new Coordonees(this.position),
								new Coordonees((byte)0,(byte)7),"roque"));
					}
				}
				simulation = p.simulerRoque(isBlack, new Coordonees((byte)7,(byte)7));
				if (simulation != null) {
					if(simulation.getRoi(isBlack).estEnEchec(simulation).size()==0) {
						mouvementsPossibles.add(new Mouvement(new Coordonees(this.position),
								new Coordonees((byte)7,(byte)7),"roque"));
					}
				}
			}
			
		}
		return mouvementsPossibles;
	}
	
	@Override
	public ArrayList<Mouvement> calculerMouvement(Plateau p){
		return calculerMouvement(p,true);
		
	}
	
	public ArrayList<Mouvement> getPossiblesMouvements(Plateau p){
		ArrayList<Piece> piecesAlies = p.getPieceFromColor(isBlack);
		ArrayList<Mouvement> mouvementsPossibles = new ArrayList<Mouvement>();
		for (int i = 0; i < piecesAlies.size(); i++) {
			mouvementsPossibles.addAll(piecesAlies.get(i).calculerMouvement(p));
			if (piecesAlies.get(i) instanceof Piece_Pion){
				mouvementsPossibles.addAll(((Piece_Pion)piecesAlies.get(i)).calculerMouvementManger(p));
			}
		}
		piecesAlies.clear();
		return mouvementsPossibles;
	}
	
	@Override
	public ArrayList<Mouvement> estEnEchec(Plateau p){
		ArrayList<Mouvement> mvt = new ArrayList<Mouvement>();
		mvt.addAll(testerLignes(p));
		mvt.addAll(testerDiagonales(p));
		mvt.addAll(testerLLines(p));
		
		return mvt;
	}
	
	public boolean estEchecEtMat(Plateau p) {

		//return (getPossiblesMouvements(p).size() ==0);
		if (getPossiblesMouvements(p).size()==0) {
			if (p.getPieceFromColor(isBlack).size()==1) {
				return true;
			}
			Plateau pTmp;
			if (isBlack) {
				pTmp = p.simulerRoque(isBlack, new Coordonees((byte)0,(byte)0));
				if (pTmp != null) {
					if(pTmp.getRoi(isBlack).estEnEchec(pTmp).size()==0) {
						return false;
					}
				}
				pTmp = p.simulerRoque(isBlack, new Coordonees((byte)7,(byte)0));
				if (pTmp != null) {
					if(pTmp.getRoi(isBlack).estEnEchec(pTmp).size()==0) {
						return false;
					}
				}
			} else {
				pTmp = p.simulerRoque(isBlack, new Coordonees((byte)0,(byte)7));
				if (pTmp != null) {
					if(pTmp.getRoi(isBlack).estEnEchec(pTmp).size()==0) {
						return false;
					}
				}
				pTmp = p.simulerRoque(isBlack, new Coordonees((byte)7,(byte)7));
				if (pTmp != null) {
					if(pTmp.getRoi(isBlack).estEnEchec(pTmp).size()==0) {
						return false;
					}
				}
			}
			return true;
		}
		return false;
		
	}

	@Override
	public void dessiner(Canvas c){

		GraphicsContext gc = c.getGraphicsContext2D();
		gc.drawImage(image, 0, (isBlack ? 64:0), 64, 64,
				rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());

	}


}
