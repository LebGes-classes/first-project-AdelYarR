import java.util.Scanner;

public class Game {
	
	private final Field field;
    private final Player player;
    private final Scanner scanner;

    public Game() {
        this.field = new Field(29);
        this.player = new Player(0, 1);
        this.scanner = new Scanner(System.in);
        field.generateField();
    }
	
	public void startGame() {
        field.setFieldValue(player.getPosition()[0], player.getPosition()[1], '*');
		
		int fieldSize = field.getFieldSize();
		
		while (field.getFieldValue(fieldSize-1, fieldSize-2) != '*') {
			Cleaner.clearScreen();
			field.printField(fieldSize, field.getFieldArray());
			handlePlayerMovement();
		}
		
		System.out.println("Congratulations, you have completed the level! Write any symbol to continue.");
		scanner.next();
	}
	
	private void handlePlayerMovement() {
		System.out.print("Press w, a, s or d to select move direction: ");
		char moveChoice = scanner.next().charAt(0);
		
		int x = player.getPosition()[0];
		int y = player.getPosition()[1];
		
		char cell;
		
		switch(moveChoice) {
		case 'w':
			cell = field.getFieldValue(x, y - 1);
			
			if (y > 0 && (cell == '0' || cell == '9')) {
				field.setFieldValue(x, y, '0');
				field.setFieldValue(x, y - 1, '*');
				player.setPosition(x, y - 1);
			}
			break;

		case 'a': 
			cell = field.getFieldValue(x - 1, y);
			
			if (x > 0 && (cell == '0' || cell == '9')) {
				field.setFieldValue(x, y, '0'); 
				field.setFieldValue(x - 1, y, '*');
				player.setPosition(x - 1, y);
			}
			break;

		case 's':
			cell = field.getFieldValue(x, y + 1);
			
			if (y < field.getFieldSize() - 1 && (cell == '0' || cell == '9')) {
				field.setFieldValue(x, y, '0'); 
				field.setFieldValue(x, y + 1, '*');
				player.setPosition(x, y + 1);
			}
			break;

		case 'd': 
			cell = field.getFieldValue(x + 1, y);
			
			if (x < field.getFieldSize() - 1 && (cell == '0' || cell == '9')) {
				field.setFieldValue(x, y, '0'); 
				field.setFieldValue(x + 1, y, '*');
				player.setPosition(x + 1, y);
			}
			break;

		}
	}
}

