package app;

import app.contracts.HttpRequest;
import app.contracts.HttpResponse;
import app.impls.HttpRequestImpl;
import app.impls.HttpResponseImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Application {

    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {

        List<String> validUrls = getValidUrls();

        List<String> request = getRequest();

        String requestString = "";

        for (String s : request) {
            requestString = requestString + s + "\r\n";
        }

        HttpRequest httpRequest = new HttpRequestImpl(requestString);

        HttpResponse httpResponse = new HttpResponseImpl(httpRequest, validUrls);

        httpRequest.getCookies()
                .forEach(System.out::println);

    }




    private static List<String> getRequest() throws IOException {

        List<String> request = new ArrayList<>();

        String line;

        while ((line = reader.readLine()) != null && !line.equals("")){
            request.add(line);
        }

        request.add("");

        if((line = reader.readLine()) != null && !line.equals("")){
            request.add(line);
        }

        return request;

    }

    private static List<String> getValidUrls() throws IOException {
        //return Arrays.stream(reader.readLine().split("\\s+")).collect(Collectors.toList());

        return new ArrayList<>(){{
            add("/url");
        }};
    }
}
