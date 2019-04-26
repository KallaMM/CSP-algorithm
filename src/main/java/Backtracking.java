import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Backtracking extends CSP {

    private List<List<Assignment>> solution = new LinkedList<>();

    public List<List<Assignment>> getSolution() {
        return solution;
    }

    public Backtracking(Domain domain, Game game) {
        super(domain, game);
    }

    public List<Assignment> findSolution(List<Assignment> solution, Game game, List<Field_Variable> fieldVariableList, List<Integer> values) {

        if (!game.isAssignmentCorrect(solution)){
            return null;
        }

        if (solution.size() == game.getsize() * game.getsize()) {
            this.solution.add(new ArrayList<>(solution));
            return solution;
        }
        Field_Variable var = fieldVariableList.get(0);
        fieldVariableList.remove(var);

        for (Integer value : values) {
            Assignment assignment = new Assignment(var, value);
            solution.add(assignment);
            List<Assignment> result = findSolution(solution,game, fieldVariableList, values);
            if (result != null){
                return solution;
            }
            solution.remove(assignment);
        }

        fieldVariableList.add(0,var);
        return null;
    }

}
