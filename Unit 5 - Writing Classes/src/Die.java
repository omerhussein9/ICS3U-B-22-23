public class Die {
    private int numSides;
    private int topSide;

    public Die(int numSides) {
        this.numSides = numSides;
        roll();
    }

    private void roll() {
        topSide = (int) (Math.random() * numSides) + 1;
    }
}
