package model;


import java.util.Collection;
import java.util.List;

import nutsAndBolts.PieceSquareColor;

/**
 * @author francoise.perrin
 * 
 * Cete classe fabrique et stocke toutes les PieceModel du Model dans une collection 
 * elle est donc responsable de rechercher et mettre à jour les PieceModel (leur position)
 * En réalité, elle délègue à une fabrique le soin de fabriquer les bonnes PieceModel 
 * avec les bonnes coordonnées
 * 
 * En revanche, elle n'est pas responsable des algorithme métiers liés au déplacement des pièces
 * (responsabilité de la classe Model)
 */
public class ModelImplementor {

	// la collection de pièces en jeu - mélange noires et blanches
	private Collection<PieceModel> pieces ;	

	public ModelImplementor() {
		super();

		pieces = ModelFactory.createPieceModelCollection();
	}

	public PieceSquareColor getPieceColor(Coord coord) {
		PieceSquareColor color = null;

		PieceModel piece = findPiece(coord);
		if(piece != null)
			color = piece.getPieceColor();
		
		return color;
	}

	public boolean isPiecehere(Coord coord) {
		boolean isPiecehere = false;

		PieceModel piece = findPiece(coord);
		if(piece != null)
			isPiecehere = true;
		
		return isPiecehere;
	}

	public boolean isMovePieceOk(Coord initCoord, Coord targetCoord, boolean isPieceToTake) {

		boolean isMovePieceOk = false;

		PieceModel piece = findPiece(initCoord);
		if(piece != null)
			isMovePieceOk = piece.isMoveOk(targetCoord, isPieceToTake);
		
		return isMovePieceOk;
	}


	public boolean movePiece(Coord initCoord, Coord targetCoord) {

		boolean isMovePieceDone = false;

		PieceModel piece = findPiece(initCoord);
		if(piece != null && !isPiecehere(targetCoord))
		{
			piece.move(targetCoord);
			isMovePieceDone = true;
		}
		
		return isMovePieceDone;
	}

	public void removePiece(Coord pieceToTakeCoord) {

		// TODO Atelier 2
		
	}

	
	public List<Coord> getCoordsOnItinerary(Coord initCoord, Coord targetCoord) {
		List<Coord> coordsOnItinerary = null;
		
		// TODO Atelier 2
		
		return coordsOnItinerary;
	}

	
	/**
	 * @param coord
	 * @return la pièce qui se trouve aux coordonnées indiquées
	 */
	private PieceModel findPiece(Coord coord) {		// TODO : mettre en "private" après test unitaires
		 
		PieceModel findPiece = null;

		for(PieceModel piece : this.pieces)
		{
			if(piece.getColonne() == coord.getColonne() && piece.getLigne() == coord.getLigne())
				findPiece = piece;
		}
		
		return findPiece;
	}



	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 * 
	 * La méthode toStrong() retourne une représentation 
	 * de la liste de pièces sous forme d'un tableau 2D
	 * 
	 */
	public String toString() {


		String st = "";
		String[][] damier = new String[ModelConfig.LENGTH][ModelConfig.LENGTH];

		// création d'un tableau 2D avec les noms des pièces à partir de la liste de pièces
		for(PieceModel piece : this.pieces) {
			PieceSquareColor color = piece.getPieceColor();
			String stColor = (PieceSquareColor.WHITE.equals(color) ? "--B--" : "--N--" );

			int col = piece.getColonne() -'a';
			int lig = piece.getLigne() -1;
			damier[lig][col ] = stColor ;
		}

		// Affichage du tableau formatté
		st = "     a      b      c      d      e      f      g      h      i      j\n";
		for ( int lig = 9; lig >=0 ; lig--) {
			st += (lig+1) + "  ";
			for ( int col = 0; col <= 9; col++) {					 
				String stColor = damier[lig][col];				
				if (stColor != null) {						
					st += stColor + "  ";	
				} 
				else {
					st += "-----  ";
				}
			}
			st +="\n";
		}
		
		return "\nDamier du model \n" + st;	
	}

}
