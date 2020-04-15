import java.util.List;

public class ParserService {

    public Person getPerson(List<String> names){
        Person person = new Person();
        if (names.size() > 3){
            throw new RuntimeException();
        } else if (names.size() == 3){

        }
    }
}
