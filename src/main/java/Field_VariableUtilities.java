import java.util.LinkedList;
import java.util.List;

public class Field_VariableUtilities {
  public static  List<Field_Variable> collectionOfAllElements(int size){
        List<Field_Variable> fieldVariables = new LinkedList<>();

        for (int i = 0; i < size; i++) {
            for (int k = 0; k < size; k++) {
                fieldVariables.add(new Field_Variable(i,k));
            }
        }
        return fieldVariables;
    }
}
