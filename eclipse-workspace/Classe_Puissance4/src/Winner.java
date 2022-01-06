
public enum Winner {
	NO_WINNER(' '),
	RED_WINNER('R'),
	YELLOW_WINNER('J');
	
	private final char letter;
	
	Winner(final char l) {
		letter = l;
	}
	
	public final char getLetter() { return letter; }
};
