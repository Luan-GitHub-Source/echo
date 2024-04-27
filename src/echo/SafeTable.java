package echo;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.HashMap;

public class SafeTable extends HashMap<String, String> {
    public String search(String request) {
        if (this.containsKey(request)) {
            String result = this.get(request);
            PrintWriter stdout = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)), true);
            stdout.println("Result found in the cache");
            return result;
        }
        return null;
    }

    public void update(String request, String response) {
        synchronized(this) {
            this.put(request, response);
        }
    }
}
