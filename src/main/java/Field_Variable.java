
public class Field_Variable {
   private int row;
   private int column;

    public Field_Variable(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    @Override
    public String toString() {
        return "Field_Variable{" +
                "row=" + row +
                ", column=" + column +
                '}';
    }
}
