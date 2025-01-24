import java.util.Random;

public class Field {
	
	private int fieldSize;
	private char[][] fieldArray;
	private int[] startPoint;
	private int[] endPoint;
	
	public Field(int n) {
		fieldSize = n;
		fieldArray = new char[fieldSize][fieldSize];
		
		startPoint = new int[2];
		endPoint = new int[2]; 
		
		setStartPoint(1, 1);
		setEndPoint(fieldSize-2, fieldSize-2);
	}
	
	
    public int getFieldSize() {
        return fieldSize;
    }

    public void setFieldSize(int fieldSize) {
        this.fieldSize = fieldSize;
        fieldArray = new char[fieldSize][fieldSize]; 
    }
	

    public char getFieldValue(int row, int col) {
        if (isWithinBounds(row, col)) {
            return fieldArray[col][row];
        } 
		return '#';
    }
	
	public char[][] getFieldArray() {
		return fieldArray;
	}

    public void setFieldValue(int row, int col, char value) {
        if (isWithinBounds(row, col)) {
            fieldArray[col][row] = value;
        }
    }


    public int[] getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(int x, int y) {
        this.startPoint[0] = x;
        this.startPoint[1] = y;
    }


    public int[] getEndPoint() {
        return endPoint;
    }	

    public void setEndPoint(int x, int y) {
        this.endPoint[0] = x;
        this.endPoint[1] = y;
    }
	
	public boolean isWithinBounds(int row, int col) {
		return row >= 0 && row < fieldSize && col >= 0 && col < fieldSize;
	}
	
	
	public void generateField() {
		Random random = new Random();
		
		int[][] directions = {
			{0, 2}, // ВНИЗ
			{2, 0} // ВПРАВО
		};
		
		int[] currentPoint = {startPoint[0], startPoint[1]};
		fieldArray[1][1] = '0';
		fieldArray[1][0] = '0';
		fieldArray[endPoint[1]][endPoint[0]+1] = '0';	
		
		// Построение основного маршрута
		do {
			int move = random.nextInt(directions.length);
			int x = directions[move][0];
			int y = directions[move][1];
			
			int newX = currentPoint[0] + x;
			int newY = currentPoint[1] + y;
			if (isWithinBounds(newX, newY)) {
				if (fieldArray[newY][newX] != '0') {
					currentPoint[0] = newX;
					currentPoint[1] = newY;
					
					if (y == 0) {
						fieldArray[newY][newX-1] = '0';
						fieldArray[newY][newX] = '0';
					} else {
						fieldArray[newY-1][newX] = '0';
						fieldArray[newY][newX] = '0';
					}
				}
			}
		} while (currentPoint[0] != endPoint[0] || currentPoint[1] != endPoint[1]);
		
		// Построение тупиковых путей и стен
		int[][] extDirections = {
			{0, -2}, // ВВЕРХ
			{0, 2}, // ВНИЗ
			{-2, 0}, // ВЛЕВО
			{2, 0} // ВПРАВО
		};
		
		for (int y = 1; y <= fieldSize-2; y += 2) {
			for (int x = 1; x <= fieldSize-2; x += 2) {
				if (fieldArray[y][x] != '0' && fieldArray[y][x] != '0') {
					fieldArray[y][x] = '0';
					int[] cellPosition = {x, y};
					
					boolean isReached = false;
					
					while (!isReached) {
						int move = random.nextInt(extDirections.length);
						int randX = extDirections[move][0];
						int randY = extDirections[move][1];
						
						int newX = cellPosition[0] + randX;
						int newY = cellPosition[1] + randY;
						
						if (isWithinBounds(newX, newY)) {
							if (fieldArray[newY][newX] == '0') {
								isReached = true;
							} 
							switch(move) {
							case 0:
								fieldArray[newY+1][newX] = '0';
								fieldArray[newY][newX] = '0';
								break;
							case 1:
								fieldArray[newY-1][newX] = '0';
								fieldArray[newY][newX] = '0';
								break;
							case 2:
								fieldArray[newY][newX+1] = '0';
								fieldArray[newY][newX] = '0';
								break;
							case 3:
								fieldArray[newY][newX-1] = '0';
								fieldArray[newY][newX] = '0';
								break;
							}
							cellPosition[0] = newX;
							cellPosition[1] = newY;
						}
					}
				}
			}
		}
	}
	
	private void makeMainPath() {
		
	}
	
	private void makeDeadEndPath() {
		
	}
	
	public static void printField(int fieldSize, char[][] fieldArray) { 
        for (int i = 0; i < fieldSize; i++) {
            for (int j = 0; j < fieldSize; j++) {
                char cell = fieldArray[i][j];

                if (cell != '0') {
                    System.out.print((cell == '*') ? '*' : '█'); 
                } else {
                    System.out.print(' ');
                }
            }
            System.out.println(); 
        }
    }
}
