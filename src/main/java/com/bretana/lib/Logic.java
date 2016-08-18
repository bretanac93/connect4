package com.bretana.lib;

import java.util.HashMap;

public class Logic {

    private int[][] board;
    private int width, height, last_played;
    private HashMap<Integer, Integer> col_count;

    public Logic(int width, int height) {
        this.board = new int[width][height];
        this.width = width;
        this.height = height;
        this.col_count = new HashMap<>();
        this.last_played = 0;

        for (int i = 0; i < width; i++)
            col_count.put(i, 0);
    }

    public int getLastPlayer() {
    		return this.last_played;
    }

    // Returns true if the game was won by someone.
    // Returns false otherwise.
    // To determine who won the game, check the variable "last_played"
    public boolean insertPiece(int player, int pos_ins) throws Exception {
    		
    		if (player == last_played) {
    			throw new Exception("This player already made his turn");
    		}

    		int curr_count = col_count.get(pos_ins);
        int pos_add = height - curr_count - 1;
		
		this.board[pos_add][pos_ins] = player;
		last_played = player;

		col_count.put(pos_ins, curr_count + 1);
		
		if (this.checkWin(player)) {
			return true;
		}

		return false;
    }

    private boolean checkWin(int player) {
    		// horizontalCheck 
	    for (int j = 0; j < this.height - 3 ; j++ ) {
	        for (int i = 0; i < this.width; i++) {
	            if (this.board[i][j] == player && this.board[i][j+1] == player && this.board[i][j+2] == player && this.board[i][j+3] == player){
	                return true;
	            }           
	        }
	    }
	    // verticalCheck
	    for (int i = 0; i < this.width - 3 ; i++) {
	        for (int j = 0; j < this.height; j++) {
	            if (this.board[i][j] == player && this.board[i+1][j] == player && this.board[i+2][j] == player && this.board[i+3][j] == player){
	                return true;
	            }           
	        }
	    }
	    // ascendingDiagonalCheck 
	    for (int i = 3; i < this.width; i++) {
	        for (int j = 0; j < this.height - 3; j++) {
	            if (this.board[i][j] == player && this.board[i-1][j+1] == player && this.board[i-2][j+2] == player && this.board[i-3][j+3] == player)
	                return true;
	        }
	    }
	    // descendingDiagonalCheck
	    for (int i = 3; i < this.width; i++) {
	        for (int j = 3; j < this.height; j++) {
	            if (this.board[i][j] == player && this.board[i-1][j-1] == player && this.board[i-2][j-2] == player && this.board[i-3][j-3] == player)
	                return true;
	        }
	    }
	    return false;
    }
}