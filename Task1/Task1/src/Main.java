import java.util.List;


public class Main {

    public static void main(String[] args) {
        List<Object> people = ParserJson.parse("FILE.json", new People());
        for (Object p : people) {
            System.out.println(p);
        }
    }
}
