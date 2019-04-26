
import com.google.common.collect.Lists;
import java.util.*;
import java.util.stream.Collectors;

public class PyramidsGame implements Game {

    int size;
    private final Map<Field_Variable, Integer> upConstraints;
    private final Map<Field_Variable, Integer> downConstraints;
    private final Map<Field_Variable, Integer> leftConstraints;
    private final Map<Field_Variable, Integer> rightConstraints;

    public PyramidsGame(
            int size,
            Map<Field_Variable, Integer> upConstraints,
            Map<Field_Variable, Integer> downConstraints,
            Map<Field_Variable, Integer> leftConstraints,
            Map<Field_Variable, Integer> rightConstraints)
    {
        this.size = size;
        this.upConstraints = upConstraints;
        this.downConstraints = downConstraints;
        this.leftConstraints = leftConstraints;
        this.rightConstraints = rightConstraints;
    }

    public Map<Field_Variable, Integer> getUpConstraints() {
        return upConstraints;
    }

    public Map<Field_Variable, Integer> getDownConstraints() {
        return downConstraints;
    }

    public Map<Field_Variable, Integer> getLeftConstraints() {
        return leftConstraints;
    }

    public Map<Field_Variable, Integer> getRightConstraints() {
        return rightConstraints;
    }

    @Override
    public int getsize() {
        return size;
    }

    @Override
    public boolean isAssignmentCorrect(List<Assignment> actualAssignmentList) {
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

        Set<Integer> setOfDistinctValues = new LinkedHashSet<>();
        for (Map.Entry<Integer, List<Assignment>> mapOfAssigments: sumOfFilterLists) {
            for (int i = 0; i < mapOfAssigments.getValue().size(); i++) {
                if(!setOfDistinctValues.contains(mapOfAssigments.getValue().get(i).getValue())){
                    setOfDistinctValues.add(mapOfAssigments.getValue().get(i).getValue());
                }
            }
            if(!(setOfDistinctValues.size() == mapOfAssigments.getValue().size())){
                return false;
            }
            setOfDistinctValues.clear();
        }

// correct till this time

        for (Map.Entry<Integer, List<Assignment>> mapOfAssigments: listFilterByRow) {

            //left constraints
            Integer isVisible = numberOfVisiblePyramids(mapOfAssigments.getValue());
            Integer shouldBeVisible = leftConstraints.get(getField_VariableByRowAndColumn(0, mapOfAssigments.getKey(), leftConstraints));
            if(shouldBeVisible != null){
                boolean result = isConstraintsCorrect(isVisible,shouldBeVisible);
                if(!result){
                    return false;
                }
            }

            //right constraints
//            Collections.reverse(mapOfAssigments.getValue());
            Integer inverseIsVisible = numberOfVisiblePyramids(Lists.reverse(mapOfAssigments.getValue()));
            Integer inverseShouldBeVisible = rightConstraints.get(getField_VariableByRowAndColumn(size-1, mapOfAssigments.getKey(), rightConstraints));
            if(inverseShouldBeVisible != null){
                boolean result = isConstraintsCorrect(inverseIsVisible,inverseShouldBeVisible);
                if (!result){
                    return false;
                }
            }
        }

        for(Map.Entry<Integer, List<Assignment>> mapOfAssigments: listFilterByColumn){

            //top constraints
            Integer isVisible = numberOfVisiblePyramids(mapOfAssigments.getValue());
            Integer shouldBeVisible = upConstraints.get(getField_VariableByRowAndColumn(mapOfAssigments.getKey(), 0, upConstraints));
            if(shouldBeVisible != null){
                boolean result = isConstraintsCorrect(isVisible,shouldBeVisible);
                if(!result){
                    return false;
                }
            }

            //bottom constraints
//            Collections.reverse(mapOfAssigments.getValue());
            Integer inverseIsVisible = numberOfVisiblePyramids(Lists.reverse(mapOfAssigments.getValue()));
            Integer inverseShouldBeVisible = downConstraints.get(getField_VariableByRowAndColumn(mapOfAssigments.getKey(), size-1, downConstraints));
            if(inverseShouldBeVisible != null){
                boolean result = isConstraintsCorrect(inverseIsVisible,inverseShouldBeVisible);
                if (!result){
                    return false;
                }
            }
        }
        return true;
    }

    private Integer numberOfVisiblePyramids(List<Assignment> assigmentInLine) {
        int result = 0;
        if (!assigmentInLine.isEmpty()) {
            int currentHighest = assigmentInLine.get(0).getValue();
            result++;

            for (Assignment assigment : assigmentInLine) {
                if (assigment.getValue() > currentHighest) {
                    currentHighest = assigment.getValue();
                    result++;
                }
            }
        }
        return result;
    }

    private boolean isConstraintsCorrect(Integer numberOfPiramidsThatISee, Integer correctNumberOfPiramidsVisible ){
        if(numberOfPiramidsThatISee == correctNumberOfPiramidsVisible){
            return true;
        }
        return false;
    }

    private Field_Variable getField_VariableByRowAndColumn(
            Integer column,
            Integer row,
            Map<Field_Variable, Integer> Field_VariableIntegerMap)
    {
        for (Field_Variable varEntry : Field_VariableIntegerMap.keySet()) {
            if (varEntry.getRow() == row && varEntry.getColumn() == column) {
                return varEntry;
            }
        }
        return null;
    }

}
