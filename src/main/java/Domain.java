import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Domain {
    private final List<Integer> domainValues;

    public Domain(int upperRange) {
        domainValues = IntStream.rangeClosed(1, upperRange).boxed().collect(Collectors.toList());
    }

    public List<Integer> getDomainValues() {
        return domainValues;
    }

//    public List<Integer> getPossibleValues(){
//    }
}
