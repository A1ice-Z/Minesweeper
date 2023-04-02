package minesweeperproject.game.celler;

public class BombCell extends Cell {

    public BombCell(int row, int column) {
        super(row, column);
    }

    @Override
    public Integer display() {
        return -1;
    }

    @Override
    public void open() {
        System.out.println("game over");
        this.open = true;
    }

}
