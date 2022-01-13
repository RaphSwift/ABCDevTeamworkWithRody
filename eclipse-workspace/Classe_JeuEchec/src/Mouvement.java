
public class Mouvement {
	protected Coordonees from;
	protected Coordonees to;
	
	public Mouvement(Coordonees _from,Coordonees _to) {
		from = _from;
		to = _to;
	}
	
	public Coordonees getFrom() {
		return from;
	}
	
	public Coordonees getTo() {
		return to;
	}
}
