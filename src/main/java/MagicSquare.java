import java.util.*;
import java.util.stream.Collectors;

public class MagicSquare implements Game {
    int sum;
    private int size;

    public MagicSquare(int size) {
        this.size = size;
        this.sum = (size * (size * size + 1))/2;
    }

    @Override
    public int getsize() {
        return size;
    }

    @Override
    public boolean isAssignmentCorrect(List<Assignment> actualAssignmentList) {

        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < actualAssignmentList.size(); i++) {
            if(!set.contains(actualAssignmentList.get(i).getValue())){
            set.add(actualAssignmentList.get(i).getValue());
            }
        }
        if(!(set.size() == actualAssignmentList.size())){
            return false;
        }

        List<Assignment> leftCross = actualAssignmentList.stream()
                .filter(assignment -> assignment.getFieldVariable().getColumn() == assignment.getFieldVariable()
                        .getRow()).collect(Collectors.toList());

        int summ = 0;
        if(leftCross.size() == size){
            for (Assignment assignmentOne : leftCross) {
                summ = summ + assignmentOne.getValue();
            }
            if (summ != this.sum){
                return false;
            }
        }

        List<Assignment> rightCross = new LinkedList<>();
        int row;
        int collumn;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < actualAssignmentList.size(); j++) {
               row = actualAssignmentList.get(j).getFieldVariable().getRow();
               collumn = actualAssignmentList.get(j).getFieldVariable().getColumn();
               if (row == i && collumn == (size - 1) - i){
                   rightCross.add(actualAssignmentList.get(j));
               }
            }
        }

        if(rightCross.size() == size){
            summ = 0;
            for (Assignment assignmentOne : rightCross) {
                summ = summ + assignmentOne.getValue();
            }
            if (summ != this.sum){
                return false;
            }
        }

        Set<Map.Entry<Integer, List<Assignment>>> assigmentByRow = actualAssignmentList
                .stream().collect(Collectors.groupingBy(assignment -> assignment.getFieldVariable().getRow())).entrySet();

        Set<Map.Entry<Integer, List<Assignment>>> assigmentByColumn = actualAssignmentList
                .stream().collect(Collectors.groupingBy(assignment -> assignment.getFieldVariable().getColumn())).entrySet();

        Set<Map.Entry<Integer, List<Assignment>>> listFilterByRow = assigmentByRow
                .stream().filter(assignments -> assignments.getValue().size() == size).collect(Collectors.toSet());

        Set<Map.Entry<Integer, List<Assignment>>> listFilterByColumn = assigmentByColumn
                .stream().filter(assignments -> assignments.getValue().size() == size).collect(Collectors.toSet());

        Set<Map.Entry<Integer, List<Assignment>>> sumOfFilterLists = listFilterByRow;
        sumOfFilterLists.addAll(listFilterByColumn);

        for(Map.Entry<Integer, List<Assignment>> assignment : sumOfFilterLists){
          int sumFromList = assignment.getValue().stream().mapToInt(assignment1 -> assignment1.getValue()).sum();
            if(sumFromList != this.sum){
                return false;
            }
        }

        return true;
    }
}
