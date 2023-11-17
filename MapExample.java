import java.util.HashMap;
import java.util.Map;

public class MapExample {
    public static void main(String[] args) {
        // Creating a Map using HashMap
        Map<String, Integer> studentScores = new HashMap<>();

        // Adding key-value pairs
        studentScores.put("Alice", 85);
        studentScores.put("Bob", 92);
        studentScores.put("Charlie", 78);

        // Retrieving values based on keys
        int aliceScore = studentScores.get("Alice");
        System.out.println("Alice's score: " + aliceScore);

        // Iterating over the entries
        for (Map.Entry<String, Integer> entry : studentScores.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

    }
}
