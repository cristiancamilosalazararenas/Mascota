package autonoma.mascota.elements;

public class Snack {

    private final int x;
    private final int y;
    private final int size;
    private boolean eaten;

    public Snack(int x, int y, int size) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.eaten = false;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getSize() {
        return size;
    }

    public boolean isEaten() {
        return eaten;
    }

    public void eat() {
        this.eaten = true;
    }
}
