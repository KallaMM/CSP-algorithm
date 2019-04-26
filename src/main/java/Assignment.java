
public class Assignment {
    private Field_Variable fieldVariable;
    private int value;

    public Assignment(Field_Variable fieldVariable, int value) {
        this.fieldVariable = fieldVariable;
        this.value = value;
    }

    public Field_Variable getFieldVariable() {
        return fieldVariable;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Assignment{" +
                "fieldVariable=" + fieldVariable +
                ", value=" + value +
                '}' + "\n";
    }
}
