import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ForwardChecking extends CSP{

        private List<List<Assignment>> solution = new LinkedList<>();

        public List<List<Assignment>> getSolution() {
            return solution;
        }

    public ForwardChecking(Domain domain, Game game) {
        super(domain, game);
    }

        public List<Assignment> findSolution(List<Assignment> solution, Game game, List<Field_Variable> fieldVariableList, List<Integer> values) {

            if (solution.size() == game.getsize() * game.getsize()) {
                this.solution.add(new ArrayList<>(solution));
                return solution;
            }
            Field_Variable var = fieldVariableList.get(0);
            fieldVariableList.remove(var);

            for (Integer value : getPossibleValues(var,solution)) {
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

    private List<Integer> getPossibleValues(Field_Variable variable, List<Assignment> assigments) {
        List<Integer> result = new LinkedList<>();
        for (Integer value : domain.getDomainValues()) {
            assigments.add(new Assignment(variable,value));
            if (game.isAssignmentCorrect(assigments)) {
                result.add(value);
            }
            assigments.remove(assigments.size()-1);
        }
        return result;
    }
}


