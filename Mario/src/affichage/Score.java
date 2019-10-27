package affichage;

public class Score {
	private static final int NNBRE_TOTAL_PIECES = 10;
	private int nbrePieces;
	
	public Score() {
		nbrePieces = 0;
	}

	public int getNbrePieces() {
		return nbrePieces;
	}

	public void setNbrePieces(int nbrePieces) {
		this.nbrePieces = nbrePieces;
	}

	public int getNBRE_TOTAL_PIECES() {
		return NNBRE_TOTAL_PIECES;
	}
}
