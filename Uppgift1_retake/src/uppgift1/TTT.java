package uppgift1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class TTT {
	//Skapar 2 spelare med arraylistor
	static ArrayList<Integer> player1Positions = new ArrayList<Integer>();
	static ArrayList<Integer> player2Positions = new ArrayList<Integer>();

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		boolean bool = true;

		while (bool) {
			//Skapar 2 alternativ efter spelet är slut
			System.out.println("Press 1 to start game");
			System.out.println("Press 2 to exit the game");

			int game = sc.nextInt();

			switch (game) {
			case 1:
				play();
				break;
			case 2:
				bool = false;
				System.out.println("Game ended");
				break;
			}

		}

	}

	public static void play() {

		char[][] gameBoard = { 
				{ ' ', '|', ' ', '|', ' ' }, 
				{ '-', '+', '-', '+', '-' }, 
				{ ' ', '|', ' ', '|', ' ' },
				{ '-', '+', '-', '+', '-' }, 
				{ ' ', '|', ' ', '|', ' ' } 
				
		};

		printGameBoard(gameBoard);

		//Importerar en scanner metod såkallad sc som tar in användarens input 
		Scanner sc = new Scanner(System.in);

		String player1 = "X";
		String player2 = "O";
		String name1 = "";
		String name2 = "";

		Random start = new Random();
		int show = start.nextInt(2) + 1;

		if ((show % 2 == 0)) {
			player1 = "X";
			name1 = "Player 1";
			player2 = "O";
			name2 = "Player 2";
		} else {
			player1 = "O";
			name1 = "Player 2";
			player2 = "X";
			name2 = "Player 1";

		}

		while (true) {

			//Player1 move
			System.out.println(name1 + " enter your position");
			int player1Pos = sc.nextInt();
			while (player1Positions.contains(player1Pos) || player2Positions.contains(player1Pos)) {
				System.out.println("The position has already been taken, enter another position");
				player1Pos = sc.nextInt();
			}
			placePiece(gameBoard, player1Pos, player1);
			printGameBoard(gameBoard);
			;

			if (checkWinner().equals("X") || checkWinner().equals("O") || checkWinner().equals("Draw")) {
				System.out.println("Winner: " + checkWinner());
				break;
			}

			//Player2 move
			System.out.println(name2 + " enter your position");
			int player2Pos = sc.nextInt();
			while (player2Positions.contains(player2Pos) || player1Positions.contains(player2Pos)) {
				System.out.println("The position has already been taken, enter another position");
				player2Pos = sc.nextInt();
			}

			placePiece(gameBoard, player2Pos, player2);
			printGameBoard(gameBoard);

			if (checkWinner().equals("X") || checkWinner().equals("O") || checkWinner().equals("Draw")) {
				System.out.println("Winner: " + checkWinner());
				break;
			}
		}

	}

	//Skapar en metod så att programmet inte körs i main metoden
	public static void printGameBoard(char[][] gameBoard) {

		//Använder mig av en for-each loop som printar ut spelbrädan 
		for (char[] row : gameBoard) {
			for (char c : row) {
				//Printar ut brädan i en rad
				System.out.print(c);
			}
			//Printar ut tomma platser i brädan
			System.out.println();

		}
	}


	public static void placePiece(char[][] gameBoard, int pos, String user) {

		char symbol = ' ';

		if (user.equals("X")) {
			symbol = 'X';
			player1Positions.add(pos);
		} else if (user.equals("O")) {
			symbol = 'O';
			player2Positions.add(pos);
		}

		//Använder mig av switch case för att placera användarens input på brädan
		switch (pos) {
		case 1:
			gameBoard[0][0] = symbol;
			break;
		case 2:
			gameBoard[0][2] = symbol;
			break;
		case 3:
			gameBoard[0][4] = symbol;
			break;
		case 4:
			gameBoard[2][0] = symbol;
			break;
		case 5:
			gameBoard[2][2] = symbol;
			break;
		case 6:
			gameBoard[2][4] = symbol;
			break;
		case 7:
			gameBoard[4][0] = symbol;
			break;
		case 8:
			gameBoard[4][2] = symbol;
			break;
		case 9:
			gameBoard[4][4] = symbol;
			break;

		default:
			break;

		}
	}

	//Skapar en metod för att hitta vinnaren 
	public static String checkWinner() {
		
		//Kontrollerar alla möjliga vinstvillkor
		List topRow = Arrays.asList(1, 2, 3);
		List midRow = Arrays.asList(4, 5, 6);
		List botRow = Arrays.asList(7, 8, 9);
		List leftCol = Arrays.asList(1, 4, 7);
		List midCol = Arrays.asList(2, 5, 8);
		List rightCol = Arrays.asList(3, 6, 9);
		List cross1 = Arrays.asList(1, 5, 9);
		List cross2 = Arrays.asList(3, 5, 7);

		List<List> winConditions = new ArrayList<List>();
		winConditions.add(topRow);
		winConditions.add(midRow);
		winConditions.add(botRow);
		winConditions.add(leftCol);
		winConditions.add(midCol);
		winConditions.add(rightCol);
		winConditions.add(cross1);
		winConditions.add(cross2);

		for (List list : winConditions) {
			if (player1Positions.containsAll(list)) {
				return "X";
			} else if (player2Positions.containsAll(list)) {
				return "O";
			} else if (player1Positions.size() + player2Positions.size() == 9) {
				return "Draw";
			}
		}

		return "";
	}
}