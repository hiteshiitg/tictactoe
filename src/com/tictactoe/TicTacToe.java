package com.tictactoe;

import java.util.Random;
import java.util.Scanner;

public class TicTacToe {

	private char[][] board;
	private int[] moves;
	
	public TicTacToe(int size){
		board=new char[size][size];
		for(int i=0;i<size;i++)
			for(int j=0;j<size;j++)
				board[i][j]=' ';
		moves=new int[size*size];
		for(int i=0;i<size*size;i++)
			moves[i]=i;
		shuffleArray(moves);
	}
	
	private void swap(int[] arr, int index1,int index2){
		int temp=arr[index1];
		arr[index1]=arr[index2];
		arr[index2]=temp;
	}
	
	private void shuffleArray(int[] arr){
		Random rand=new Random();	
		for(int i=0;i<arr.length;i++){
			int index=rand.nextInt(arr.length);
			swap(arr,i,index);
		}
	}
	
	private void printBoard(){
		for(int i=0;i<board.length;i++){
			System.out.println("------------------------------");
			for(int j=0;j<board.length;j++){
				System.out.print(board[i][j]);
				System.out.print("\t|");
			}
			System.out.println("\n");
		}
		System.out.println("------------------------------");
	}
	
	private boolean gameOver(){
		// check line
		for(int i=0;i<board.length;i++){
			char c=board[i][0];
			if(c=='X' || c=='O'){
				int j=1;
				for(;j<board.length;j++){
					if(c!=board[i][j])
						break;
				}
				if(j==board.length)
					// found
					return true;
			}
		}
		
		// check column
		for(int i=0;i<board.length;i++){
			char firstCharInColumn=board[0][i];
			if(firstCharInColumn=='X' || firstCharInColumn=='O'){
				int j=1;
				for(;j<board.length;j++){
					if(firstCharInColumn!=board[j][i])
						break;
				}
				if(j==board.length)
					return true;
			}
		}
		
		//check left diagnol 
		int i=0;
		char firstchar=board[i][i++];
		if(firstchar=='X' || firstchar=='O'){
			for(;i<board.length;i++){
				if(board[i][i]!=firstchar)
					break;
			}
			if(i==board.length)
				return true;
		}
		
		//check right diagnol 
		i=board.length-1;
		firstchar=board[i][i--];
		if(firstchar=='X' || firstchar=='O'){
			for(;i>=0;i--){
				if(board[i][i]!=firstchar)
					break;
			}
			if(i<0)
				return true;
		}
			
		return false;
	}
	
	public void playGame(){
		
		String input="";
		System.out.println("Welcome to TicTacToe.");
		System.out.println("Choose your coin X or O");
		printBoard();
		char computer;
		char player;
		while(true){
			Scanner sc = new Scanner(System.in);
			input = sc.next();
			if(!input.equals("X")&&!input.equals("O"))
				System.out.println("Choose either X or O");
			else{
				System.out.println("You have chosen "+input);
				if(input.equals("X")){
					player='X';
					computer='O';
				}else{
					player='O';
					computer='X';
				}
				break;
			}
		}
		char turn =computer;
		int moveIndex=0;
		while(moveIndex<moves.length&&!gameOver()){
			if(turn==computer){
				System.out.println("Computer's turn");
			}else{
				System.out.println("Player's turn");
			}
			int cell=moves[moveIndex++];
			int row=cell/board.length;
			int col=cell%board.length;
			board[row][col]=turn;
			printBoard();
			turn=turn==computer?player:computer;
		}
		
		if(moveIndex==moves.length)
			System.out.println("Draw");
		else{
			if(turn==computer)
				System.out.println("Player won the match");
			else 
				System.out.println("computer won the match");
		}
	}
	
	public static void main(String[] args) {
		TicTacToe game=new TicTacToe(4);
		game.playGame();

	}

}
