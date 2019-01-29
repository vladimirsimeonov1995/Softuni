package app.impls;

import app.contracts.HttpRequest;

import java.util.*;
import java.util.stream.Collectors;

public class HttpRequestImpl implements HttpRequest {

    private String input;
    private List<String> head;
    private String method;
    private String requestUrl;
    private HashMap<String, String> headers;
    private HashMap<String, String> bodyParameters;


    public HttpRequestImpl(String input){
        setInput(input);

        this.headers = new LinkedHashMap<>();
        this.bodyParameters = new LinkedHashMap<>();

        this.setRequest();
    }

    private void setInput(String input) {
        this.input = input;
    }

    private void setRequest(){

        List<String> requestList = Arrays.stream(input.split("\r\n")).collect(Collectors.toList());

        this.head = Arrays.stream(requestList.get(0).split("\\s+")).collect(Collectors.toList());

        this.setMethod(this.head.get(0));

        this.setRequestUrl(head.get(1));

        this.setHeaders(requestList);

        this.setBodyParameters(requestList);

    }

    public List<String> getHead() {
        return head;
    }

    @Override
    public String getMethod() {
        return this.method;
    }

    private void setMethod(String method) {
        this.method = method;
    }

    private void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    @Override
    public String getRequestUrl() {
        return this.requestUrl;
    }


    @Override
    public HashMap<String, String> getHeaders() {
        return this.headers;
    }

    private void addHeader(String header, String value) {
        this.headers.put(header, value);
    }

    private void setHeaders(List<String> requestList){
        requestList.stream()
                .filter(r -> r.contains(": "))
                .map(r -> r.split(": "))
                .forEach(r -> {
                    this.addHeader(r[0], r[1]);
                });
    }


    @Override
    public HashMap<String, String> getBodyParameters() {
        return this.bodyParameters;
    }

    private void addBodyParameter(String parameter, String value) {
        this.bodyParameters.put(parameter, value);
    }

    private void setBodyParameters(List<String> reqestList){

        if(!(reqestList.get(reqestList.size() - 1).equals("")) && reqestList.contains("")) {


            Arrays.stream(reqestList.get(reqestList.size() - 1).split("&"))
                    .map(r -> r.split("="))
                    .forEach(r -> {
                        this.addBodyParameter(r[0], r[1]);
                    });

        }
    }

    @Override
    public boolean isResource() {
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(String.join(" ", this.head)).append(System.lineSeparator());

        this.getHeaders().entrySet().stream().forEach(es -> {
            sb.append(String.format("%s: %s", es.getKey(), es.getValue())).append(System.lineSeparator());
        });

        sb.append(System.lineSeparator());

        this.getBodyParameters().entrySet().stream().forEach(es -> {
            sb.append(String.format("%s=%s", es.getKey(), es.getValue())).append("&");
        });

        sb.deleteCharAt(sb.toString().length() - 1);

        return sb.toString();
    }
}
