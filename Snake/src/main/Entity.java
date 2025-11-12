package main;

public abstract class Entity {
    protected Tile tile;

    public Entity(int x, int y) {
        this.tile = new Tile(x, y);
    }

    public int getX() {
        return tile.x;
    }

    public int getY() {
        return tile.y;
    }

    public void setX(int x) {
        tile.x = x;
    }

    public void setY(int y) {
        tile.y = y;
    }

    public Tile getTile() {
        return tile;
    }
}