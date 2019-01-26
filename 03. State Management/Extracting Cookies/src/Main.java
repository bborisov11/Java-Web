import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Map<String, String> cookies = new LinkedHashMap<>();
        //List<HttpCookie> cookieList = new ArrayList<>();
        String input;
        while(!(input = reader.readLine()).equals("")) {
                if(input.startsWith("Cookie")) {
                    Arrays.stream(input.substring(8).split("; "))
                            .forEach(x -> cookies.put(x.split("=")[0],x.split("=")[1]));
                          //.forEach(x -> cookieList.add(new HttpCookie(x.split("=")[0], x.split("=")[1])));
                }
        }
        cookies.forEach((key,value) -> {
            System.out.println(key + " <-> " + value);
        });
    }
}
