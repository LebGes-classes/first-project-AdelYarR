import java.util.Scanner;

public class Menu {
	
	private final Game game;
    private final Scanner scanner;
	
	public Menu() {
        game = new Game();
        scanner = new Scanner(System.in);
    }
	
	public void loadMenu() {
		
		boolean isContinuing = true;
		
		while(isContinuing) {
			int buttonChoice = getUserChoice();
			
			switch (buttonChoice) {
			case 1:
				game.startGame();
				break;
			case 2:
				System.out.println("Exiting the game...");
				isContinuing = false;
				break;	
			}
		}
	}
		
	private int getUserChoice() {
		Scanner scanner = new Scanner(System.in);
		int buttonChoice = -1;
		
		while (buttonChoice != 1 && buttonChoice != 2) {
			Cleaner.clearScreen();
			
			System.out.println("Select a button:");
			System.out.println("Enter 1 to start the game or 2 to exit the game");
				
			buttonChoice = scanner.nextInt();
		}
		
		return buttonChoice;
	}
}