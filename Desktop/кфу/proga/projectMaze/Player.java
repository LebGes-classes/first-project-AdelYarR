public class Player {
    private int[] position; 

    public Player(int startX, int startY) {
        position = new int[] {startX, startY}; 
    }

    public int[] getPosition() {
        return position;
    }

    public void setPosition(int x, int y) {
        position[0] = x;
        position[1] = y;
    }
}