import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {

        //MAGIC SQUARE
        //backtracking
//        long time = System.currentTimeMillis();
//        Game magicsquare = new MagicSquare(3);
//        Backtracking backtracking = new Backtracking(new Domain(magicsquare.getsize() * magicsquare.getsize()),magicsquare);
//
//        backtracking.findSolution(new LinkedList<>(), magicsquare, Field_VariableUtilities.collectionOfAllElements(magicsquare.getsize()), backtracking.domain.getDomainValues());
//
//        System.out.println(System.currentTimeMillis() - time);
//
//        System.out.println(backtracking.getSolution());

//        //forwardchecking
//        long time = System.currentTimeMillis();
//        Game magicsquare = new MagicSquare(2);
//        ForwardChecking backtracking = new ForwardChecking(new Domain(magicsquare.getsize() * magicsquare.getsize()),magicsquare);
//
//        backtracking.findSolution(new LinkedList<>(), magicsquare, Field_VariableUtilities.collectionOfAllElements(magicsquare.getsize()), backtracking.domain.getDomainValues());
//
//        System.out.println(System.currentTimeMillis() - time);
//
//        System.out.println(backtracking.getSolution());






        //PYRAMIDS
        //backtracking


//        int size = 4;
//
//        long timePyramid = System.currentTimeMillis();
//        Map<Field_Variable, Integer> topConstraint = new HashMap<>();
//        topConstraint.put(new Field_Variable(0,1), 2);
//        topConstraint.put(new Field_Variable(0,3), 1);
//
//        Map<Field_Variable, Integer> bottomConstraint = new HashMap<>();
//        bottomConstraint.put(new Field_Variable(size-1,0),1);
//
//        Map<Field_Variable, Integer> rightConstraint = new HashMap<>();
////        rightConstraint.put(new Field_Variable(1,size-1), 4);
//
//        Map<Field_Variable, Integer> leftConstraint = new HashMap<>();
//        leftConstraint.put(new Field_Variable(2,0), 3);
//
//        Game pyramids = new PyramidsGame(size, topConstraint, bottomConstraint, leftConstraint, rightConstraint);
//        Backtracking backtrackingPyramids = new Backtracking(new Domain(pyramids.getsize()),pyramids);
//        backtrackingPyramids.findSolution(new LinkedList<>(),pyramids,Field_VariableUtilities.collectionOfAllElements(pyramids.getsize()),backtrackingPyramids.domain.getDomainValues());
//        System.out.println(System.currentTimeMillis() - timePyramid);
//        System.out.println(backtrackingPyramids.getSolution());






        //forwardchecking

        int size = 3;

        long timePyramid = System.currentTimeMillis();
        Map<Field_Variable, Integer> topConstraint = new HashMap<>();
        topConstraint.put(new Field_Variable(0,1), 2);
//        topConstraint.put(new Field_Variable(0,1), 4);
//        topConstraint.put(new Field_Variable(0,3), 1);

        Map<Field_Variable, Integer> bottomConstraint = new HashMap<>();
        bottomConstraint.put(new Field_Variable(size-1,0),1);

        Map<Field_Variable, Integer> rightConstraint = new HashMap<>();
//        rightConstraint.put(new Field_Variable(1,size-1), 3);

        Map<Field_Variable, Integer> leftConstraint = new HashMap<>();
//        leftConstraint.put(new Field_Variable(3,0), 3);

        Game pyramids = new PyramidsGame(size, topConstraint, bottomConstraint, leftConstraint, rightConstraint);
        ForwardChecking backtrackingPyramids = new ForwardChecking(new Domain(pyramids.getsize()),pyramids);
        backtrackingPyramids.findSolution(new LinkedList<>(),pyramids,Field_VariableUtilities.collectionOfAllElements(pyramids.getsize()),backtrackingPyramids.domain.getDomainValues());
        System.out.println(System.currentTimeMillis() - timePyramid);

        System.out.println(backtrackingPyramids.getSolution());

    }
}

