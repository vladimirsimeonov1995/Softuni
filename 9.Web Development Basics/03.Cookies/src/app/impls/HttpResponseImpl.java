package app.impls;

import app.contracts.HttpRequest;
import app.contracts.HttpResponse;

import java.util.*;

public class HttpResponseImpl implements HttpResponse {

    private final List<String> validUrls;
    private int statusCode;
    private byte[] content;
    private byte[] bytes;
    private HashMap<String, String> headers;
    private String responseBody;
    private String responseStatus;

    private HttpRequest request;

    public HttpResponseImpl(HttpRequest request, List<String> validUrls) {
        this.headers = new LinkedHashMap<>();

        this.request = request;
        this.validUrls = validUrls;

        this.setStatus();
        this.setHeaders();
    }

    @Override
    public int getStatusCode() {
        return this.statusCode;
    }

    @Override
    public byte[] getContent() {
        return this.content;
    }

    @Override
    public byte[] getBytes() {
        return this.bytes;
    }

    private void setStatus() {

        if(!validUrls.contains(request.getRequestUrl())){
            responseStatus = String.format("%s 404 Not Found", request.getHead().get(2));
            responseBody = "The requested functionality was not found.";
            this.setStatusCode(404);
        }else if(!request.getHeaders().containsKey("Authorization")){
            responseStatus = String.format("%s 401 Unauthorized", request.getHead().get(2));
            responseBody = "You are not authorized to access the requested functionality.";
            this.setStatusCode(401);
        }else if("POST".equals(request.getMethod()) && request.getBodyParameters().size() == 0){
            responseStatus = String.format("%s 400 Unauthorized", request.getHead().get(2));
            responseBody = "There was an error with the requested functionality due to malformed request.";
            this.setStatusCode(400);
        }else {
            responseStatus = String.format("%s 200 OK", request.getHead().get(2));
            responseBody = getOKBody(request.getHeaders(), request.getBodyParameters(), request.getHead());
            this.setStatusCode(200);
        }

        System.out.println();

    }

    @Override
    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;

    }

    private String getOKBody(Map<String, String> requestHeaders, Map<String, String> requestParams, List<String> requestHead) {

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
        return body.toString();

    }

    @Override
    public void setContent(byte[] content) {

    }

    @Override
    public void addHeader(String header, String value) {
        this.headers.put(header, value);
    }

    private void setHeaders(){
        request.getHeaders().entrySet()
                .stream()
                .filter(h -> "Date".equals(h.getKey()) || "Host".equals(h.getKey()) || "Content-Type".equals(h.getKey()))
                .forEach(h -> {
                    this.addHeader(h.getKey(), h.getValue());
                });
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(responseStatus).append(System.lineSeparator());

        for (Map.Entry<String, String> entry : headers.entrySet()) {
            sb.append(String.format("%s: %s", entry.getKey(), entry.getValue())).append(System.lineSeparator());
        }

        sb.append(request.getHeaders().get("Cookie"));

        sb.append(System.lineSeparator());
        sb.append(responseBody);

        return sb.toString();
    }
}
