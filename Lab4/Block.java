package Cryptography.Lab4;

/**
 * Created by @kmartin62
 */
public class Block {

    private String stringBits;

    public Block(String stringBits) {
        this.stringBits = stringBits;
    }

    public String getStringBits() {
        return stringBits;
    }

    @Override
    public String toString() {
        return this.stringBits;
    }
}
