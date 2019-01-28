package app;

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

        List<String> response = getResponse(request, validUrls);

        response.forEach(System.out::println);

    }

    private static List<String> getResponse(List<String> request, List<String> validUrls) {

        List<String> response ;

        List<String> requestHead = getRequestHead(request.get(0));

        Map<String, String> requestHeaders = getRequestHeaders(request);

        Map<String, String> requestParams = getRequestParams(request);

        String responseStatus;

        String responseBody;

        if(!validUrls.contains(requestHead.get(1))){
            responseStatus = String.format("%s 404 Not Found", requestHead.get(2));
            responseBody = "The requested functionality was not found.";
        }else if(!requestHeaders.containsKey("Authorization")){
            responseStatus = String.format("%s 401 Unauthorized", requestHead.get(2));
            responseBody = "You are not authorized to access the requested functionality.";
        }else if("POST".equals(requestHead.get(0)) && requestParams == null){
            responseStatus = String.format("%s 400 Unauthorized", requestHead.get(2));
            responseBody = "There was an error with the requested functionality due to malformed request.";
        }else {
            responseStatus = String.format("%s 200 OK", requestHead.get(2));
            responseBody = getOKBody(requestHeaders, requestParams, requestHead);
        }


        List<String> responseHeaders = getResponseHeaders(requestHeaders);

        response = new ArrayList<>() {{
            add(responseStatus);
            this.addAll(responseHeaders);
            add("");
            add(responseBody);
        }};

        return response;

    }

    private static List<String> getResponseHeaders(Map<String, String> requestHeaders) {

        List<String> responseHeaders = new ArrayList<>();

        requestHeaders.entrySet()
                .stream()
                .filter(h -> "Date".equals(h.getKey()) || "Host".equals(h.getKey()) || "Content-Type".equals(h.getKey()))
                .forEach(h -> {
                    responseHeaders.add(String.format("%s: %s", h.getKey(), h.getValue()));
                });

        return responseHeaders;

    }

    private static String getOKBody(Map<String, String> requestHeaders, Map<String, String> requestParams, List<String> requestHead) {

        StringBuilder body = new StringBuilder();

        body.append("Greetings ")
                .append(new String(Base64.getDecoder().decode(requestHeaders.get("Authorization").split("\\s+")[1])))
                .append("!");

        if(requestHead.get(0).equals("POST")) {

            body.append(" You have successfully created ")
                    .append(requestParams.get("name"))
                    .append(" with ");

            requestParams.entrySet()
                    .stream()
                    .filter(p -> !p.getKey().equals("name"))
                    .forEach(p -> {
                        body.append(String.format("%s – %s, ", p.getKey(), p.getValue()));
                    });

            body.replace(body.length() - 2, body.length() - 1, ".");

        }

        System.out.println(body);



        return body.toString();

    }

    private static Map<String, String> getRequestParams(List<String> request) {

        Map<String, String> requestParams = new LinkedHashMap<>();

        if(request.get(request.size() -1).equals("")){
            return null;
        }

        Arrays.stream(request.get(request.size() - 1).split("&"))
                .map(r -> r.split("="))
                .forEach(r -> {
                    requestParams.put(r[0], r[1]);
                });

        return requestParams;

    }

    private static Map<String, String> getRequestHeaders(List<String> request) {

        Map<String, String> requestHeaders = new LinkedHashMap<>();

        request.stream()
                .filter(r -> r.contains(": "))
                .map(r -> r.split(": "))
                .forEach(r -> {
                    requestHeaders.put(r[0], r[1]);
                });

        return requestHeaders;

    }

    private static List<String> getRequestHead(String s) {
        return Arrays.stream(s.split("\\s+")).collect(Collectors.toList());
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
        return Arrays.stream(reader.readLine().split("\\s+")).collect(Collectors.toList());
    }
}
