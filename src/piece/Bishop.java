package piece;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.List;
import java.util.ArrayList;
import main.Board;
import main.GamePanel;
import main.Type;
import main.FlashMode;

public class Bishop  extends Piece{

	public Bishop(int color, int col, int row) {
		super(color,col,row);
		type = Type.BISHOP;
		if(color == GamePanel.WHITE) {
			image = getImage("/piece/w-bishop");
		}
		else {
			image = getImage("/piece/b-bishop");
		}
	}
	  public List<int[]> getValidMoves() {
	        List<int[]> validMoves = new ArrayList<>();

	        // Bishop Move 
	        int[][] directions = {
	            {-1, -1}, {-1, 1}, {1, -1}, {1, 1}
	        };

	        for (int[] direction : directions) {
	            for (int i = 1; i < 8; i++) {
	                int targetCol = col + i * direction[0];
	                int targetRow = row + i * direction[1];

	                if (!canMove(targetCol, targetRow)) {
	                    break;
	                }

	                validMoves.add(new int[]{targetCol, targetRow});
	            }
	        }

	        return validMoves;
	    }

	    public void drawValidMoves(Graphics2D g) {
	        List<int[]> validMoves = getValidMoves();

	        g.setColor(Color.GREEN); // Valid Color

	        for (int[] move : validMoves) {
	            int x = getX(move[0]);
	            int y = getY(move[1]);

	            g.fillOval(x + Board.SQUARE_SIZE / 2, y + Board.SQUARE_SIZE / 2, Board.SQUARE_SIZE / 4, Board.SQUARE_SIZE / 4);
	        }
	    }
	
	public boolean canMove(int targetCol, int targetRow) {
		if(isWithinBoard(targetCol, targetRow) && isSameSquare(targetCol,targetRow) == false) {
			if(Math.abs(targetCol - preCol) == Math.abs(targetRow - preRow)) {
				if(isValidSquare(targetCol, targetRow) && pieceIsOnDiagonalLine(targetCol,targetRow) == false) {
					return true;
				}
			}
		}
		return false;
	}
	

}
