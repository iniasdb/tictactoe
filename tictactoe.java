import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class tictactoe {

	static ArrayList<Integer> playerPositions = new ArrayList<Integer>();
	static ArrayList<Integer> cpuPositions = new ArrayList<Integer>();
	static ArrayList<Integer> player2Positions = new ArrayList<Integer>();
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		//board
		char[][] board = {{' ', '|', ' ', '|', ' '},
						 {'-', '+', '-', '+', '-'},
						 {' ', '|', ' ', '|', ' '},
						 {'-', '+', '-', '+', '-'},
						 {' ', '|', ' ', '|', ' '}
		};
		
		System.out.println("single player (s) or multiplayer (m)?  s or m");
		char gameMode = scanner.next().charAt(0);
		
		//game loop
		while (true) {
			printBoard(board);
			
			//player chooses where to place X
			System.out.println("where do you want to place your X");
			int playerPos = scanner.nextInt();
			//checks if position is taken
			while (playerPositions.contains(playerPos) || cpuPositions.contains(playerPos)) {
				System.out.println("positions taken");
				playerPos = scanner.nextInt();
			}
			choosePlace(board, playerPos, "player");
			printBoard(board);
						
			//checks if won
			String result = checkWinner();
			if (result.length() > 0) {
				System.out.println(result);
				break;
			}
			
			if (gameMode == 's' || gameMode == 'S') {
				
				//cpu chooses where to place O
				int cpuPos = (int) Math.floor((Math.random()*9)+1);
				//checks if place is taken
				while (playerPositions.contains(cpuPos) || cpuPositions.contains(cpuPos)) {
					cpuPos = (int) Math.floor((Math.random()*9)+1);
				}
				
				choosePlace(board, cpuPos, "cpu");
				printBoard(board);
				
				//checks if won
				System.out.println();
				result = checkWinner();
				if (result.length() > 0) {
					System.out.println(result);
					break;
				}
				
			} else if (gameMode == 'm' || gameMode == 'S') {
				
				//player2 chooses where to place O
				System.out.println("where do you want to place your O");
				int player2Pos = scanner.nextInt();
				//checks if position is taken
				while (playerPositions.contains(player2Pos) || player2Positions.contains(player2Pos)) {
					System.out.println("positions taken");
					player2Pos = scanner.nextInt();
				}
				
				//checks if winning condition
				choosePlace(board, player2Pos, "player2");
				result = checkWinner();
				if (result.length() > 0) {
					System.out.println(result);
					break;
				}
				
			}
		}
		
		scanner.close();
	}
	
	
	public static void printBoard(char[][] board) {
		for (char[] row : board) {
			for (char c : row) {
				System.out.print(c);
			}
			System.out.println();
		}
	}
	
	public static void choosePlace(char[][] board, int place, String player) {
		
		char symbol = 'X';
		
		if (player.equals("player")) {
			symbol = 'X';
			playerPositions.add(place);
		} else if (player.equals("cpu")) {
			symbol = 'O';
			cpuPositions.add(place);
		} else if (player.equals("player2")) {
			symbol = 'O';
			player2Positions.add(place);
		}
		
		switch(place) {
		case 1:
			board[0][0] = symbol;
			break;
		case 2:
			board[0][2] = symbol;
			break;
		case 3:
			board[0][4] = symbol;
			break;
		case 4:
			board[2][0] = symbol;
			break;
		case 5:
			board[2][2] = symbol;
			break;
		case 6:
			board[2][4] = symbol;
			break;
		case 7:
			board[4][0] = symbol;
			break;
		case 8:
			board[4][2] = symbol;
			break;
		case 9:
			board[4][4] = symbol;
			break;
		default:
			System.out.println("error");
	}
	}
	
	public static String checkWinner() {
		List<Integer> topRow = Arrays.asList(1, 2, 3);
		List<Integer> midRow = Arrays.asList(4, 5, 6);
		List<Integer> botRow = Arrays.asList(7, 8, 9);
		List<Integer> lefCol = Arrays.asList(1, 4, 7);
		List<Integer> midCol = Arrays.asList(2, 5, 8);
		List<Integer> rigCol = Arrays.asList(3, 6, 9);
		List<Integer> diaOne = Arrays.asList(1, 5, 9);
		List<Integer> diaTwo = Arrays.asList(3, 5, 7);
		
		List<List> winCond = new ArrayList<List>();
		winCond.add(topRow);
		winCond.add(midRow);
		winCond.add(botRow);
		winCond.add(lefCol);
		winCond.add(midCol);
		winCond.add(rigCol);
		winCond.add(diaOne);
		winCond.add(diaTwo);
		
		for (List l : winCond) {
			if (playerPositions.containsAll(l)) {
				return "Player 1 wins!";
			} else if (cpuPositions.containsAll(l)) {
				return "You lose!";
			} else if (player2Positions.containsAll(l)) {
				return "Player 2 wins!";
			} else if (playerPositions.size() + cpuPositions.size() == 9) {
				return "Draw!";
			} else if (playerPositions.size() + player2Positions.size() == 9) {
				return "Draw!";
			}
		}
		return "";
		
		
	}
}