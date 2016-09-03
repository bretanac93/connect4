package com.bretana.lib;

import java.util.HashMap;

/**
 * Board is the class that represents all the logic in the task.
 * @author Cesar Bretana
 */
public class Board {

    private long[][] board;

    public long[][] getBoard() {
        return board;
    }
    private int width, height;
    private long last_played;
    private HashMap<Integer, Integer> col_count;

    /**
     * Constructor
     * @param width Defines the width of the board
     * @param height Defines the height of the board
     */
    public Board(int width, int height) {
        this.board = new long[width][height];
        this.width = width;
        this.height = height;
        this.col_count = new HashMap<>();
        this.last_played = 0;

        for (int i = 0; i < width; i++)
            col_count.put(i, 0);
    }

    public long getLastPlayer() {
        return this.last_played;
    }
    
    public int getTokenCountByPos(int position) {
        return this.col_count.get(position);
    }

    // To determine who won the game, check the variable "last_played"

    /**
     *
     * @param player Indicates the player id, is received from the Spring app.
     * @param pos_ins Indicates the position to insert the token.
     * @return the message containing information about who won and the direction of the game, if null then it can keep receiving new playings.
     * @throws Exception indicating a cheat alert. 
     */
        public StatusMessage insertPiece(long player, int pos_ins) throws Exception {
    		
        if (player == last_played) {
                throw new Exception("This player already made his turn");
        }

        int curr_count = col_count.get(pos_ins);
        int pos_add = height - curr_count - 1;
		
        this.board[pos_add][pos_ins] = player;
        last_played = player;

        col_count.put(pos_ins, curr_count + 1);

        return this.checkWin(player);
    }

    private StatusMessage checkWin(long player) {
    		// horizontalCheck 
	    for (int j = 0; j < this.height - 3 ; j++ ) {
	        for (int i = 0; i < this.width; i++) {
	            if (this.board[i][j] == player && this.board[i][j+1] == player && this.board[i][j+2] == player && this.board[i][j+3] == player){
	                return new StatusMessage("HORIZONTAL", "");
	            }           
	        }
	    }
	    // verticalCheck
	    for (int i = 0; i < this.width - 3 ; i++) {
	        for (int j = 0; j < this.height; j++) {
	            if (this.board[i][j] == player && this.board[i+1][j] == player && this.board[i+2][j] == player && this.board[i+3][j] == player){
	                return new StatusMessage("VERTICAL", "");
	            }           
	        }
	    }
	    // ascendingDiagonalCheck 
	    for (int i = 3; i < this.width; i++) {
	        for (int j = 0; j < this.height - 3; j++) {
	            if (this.board[i][j] == player && this.board[i-1][j+1] == player && this.board[i-2][j+2] == player && this.board[i-3][j+3] == player)
	                return new StatusMessage("DIAGONAL UP", "");
	        }
	    }
	    // descendingDiagonalCheck
	    for (int i = 3; i < this.width; i++) {
	        for (int j = 3; j < this.height; j++) {
	            if (this.board[i][j] == player && this.board[i-1][j-1] == player && this.board[i-2][j-2] == player && this.board[i-3][j-3] == player)
	                return new StatusMessage("DIAGONAL DOWN", "");
	        }
	    }
	    return null;
    }
    
    /**
     * This method is used for fill the board with new information.
     * @param matrix for fill the board
     */
    public void fillBoard(long [][] matrix) {
        this.board = matrix;
        int index = 0;
        for (long[] arr : this.board) {
            int count = 0;
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] != 0)
                    count++;
            }
            col_count.put(index, count);
        }
    }
}