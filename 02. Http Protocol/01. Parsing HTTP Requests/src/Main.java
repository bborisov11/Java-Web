import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        List<String> validUrls = Arrays.stream(reader.readLine().split("\\s+"))
                .collect(Collectors.toList());

        String[] urlInfo = reader.readLine().split("\\s+");

        String requestType = urlInfo[0];
        String path = urlInfo[1];
        String http = urlInfo[2];

        List<String> headers = new ArrayList<>();
        String status = "200 OK";
        String message = "";
        String authorizationHeaderInfo = "";
        boolean isAuthorized = false;
        String input;

        while(!(input = reader.readLine()).equals("")) {
            String headerType = input.split("[: ]+")[0];
            if(headerType.equals("Date")
                    || headerType.equals("Host")
                    || headerType.equals("Content-Type")) {
                headers.add(input);
            }
            if(headerType.equals("Authorization")) {
                isAuthorized = true;
                authorizationHeaderInfo = input.split("[:]+")[1];
            }
        }

        String bodyParameters = reader.readLine();

        if(!validUrls.contains(path)) {
            status = "404 Not Found";
            message  = "The requested functionality was not found.";
        }
        else if(!isAuthorized) {
            status = "401 Unauthorized";
            message  = "You are not authorized to access the requested functionality.";
        }
        else if (requestType.equals("POST") && bodyParameters.equals("")) {
            status = "400 Bad Request";
            message  = "There was an error with the requested functionality due to malformed request.";
        } else {
            byte[] decodedBytes = Base64.getDecoder().decode(authorizationHeaderInfo.trim()
                    .split("\\s+")[1]);
            String name = new String(decodedBytes);

            String[] parameters = Arrays.stream(bodyParameters.split("[=&]"))
                    .skip(1).toArray(String[]::new);
            if(requestType.equals("POST")) {
                message = String
                        .format("Greetings %s! You have successfully created %s with %s – %s, %s – %s.",
                                name, parameters[0], parameters[1], parameters[2], parameters[3],
                                parameters[4]);
            } else {
                message = "Greetings" + name + "!";
            }
        }

        StringBuilder response = new StringBuilder();
        response.append(http).append(" ").append(status).append(System.lineSeparator())
                .append(headers.stream().anyMatch(x -> x.split("[: ]+")[0].equals("Date")) ?
                                "" : "Date: 17/01/2019\n")
                .append(headers.stream().anyMatch(x -> x.split("[: ]+")[0].equals("Host")) ?
                               "" : "Host: localhost:8000\n")
                .append(String.join("\n", headers))
                .append(System.lineSeparator())
                .append(message);

        System.out.println(response);
    }

}
