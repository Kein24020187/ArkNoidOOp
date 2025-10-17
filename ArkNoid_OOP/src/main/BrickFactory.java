package main;

public class BrickFactory {
    public static Brick createBrick(String type, int x, int y) {
        switch(type) {
            case "strong": return new StrongBrick(x, y);
            default: return new NormalBrick(x, y);
        }
    }
}
