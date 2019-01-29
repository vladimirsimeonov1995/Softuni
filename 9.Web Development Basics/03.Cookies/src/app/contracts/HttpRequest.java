package app.contracts;

import app.impls.HttpCookie;

import java.util.HashMap;
import java.util.List;

public interface HttpRequest {

    HashMap<String, String> getHeaders();

    HashMap<String, String> getBodyParameters();


    String getMethod();


    String getRequestUrl();

    List<String> getHead();

    public List<HttpCookie> getCookies();

    boolean isResource();

}
