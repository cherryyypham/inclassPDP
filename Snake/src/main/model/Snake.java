package main.model;

import java.util.ArrayList;

public class Snake extends Entity {
    private ArrayList<Tile> body;

    public Snake(int x, int y) {
        super(x, y);
        this.body = new ArrayList<Tile>();
    }

    public ArrayList<Tile> getBody() {
        return body;
    }

    public void addBody(int x, int y) {
        body.add(new Tile(x, y));
    }

    public int getBodySize() {
        return body.size();
    }
}